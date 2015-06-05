package com.bresume.controller.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.controller.login.AuthController;
import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IConstants;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.AuthType;
import com.bresume.core.common.constant.enums.RegisterType;
import com.bresume.core.common.constant.enums.UserStatus;
import com.bresume.core.common.constant.enums.UserType;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.exception.PortalErrorCode;
import com.bresume.core.common.exception.impl.PortalException;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.EncryptionUtils;
import com.bresume.core.common.utils.security.Encrypt;
import com.bresume.core.model.dto.LoginUser;
import com.bresume.core.model.entity.user.BAuth;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.model.entity.user.UserVerified;
import com.bresume.core.service.user.IBAuthService;
import com.bresume.core.service.user.IUserService;
import com.bresume.core.service.user.IUserVerifiedService;

@RequestMapping("/user")
@Controller
public class UserController extends AuthController {

	@Resource
	private IUserService userService;

	@Resource
	private IUserVerifiedService verifiedService;

	@Resource
	private JavaMailSender mailSender;

	@Resource
	private IBAuthService authService;

	@RequestMapping("/register")
	public @ResponseBody JSONObject register(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			HttpServletResponse response, HttpServletRequest request) {
		User user = new User();
		// user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);

		try {
			user.setRegisterType(RegisterType.PORTAL_REGISTER.getType());
			user.setType(UserType.PERSIONAL.getCode());
			user.setLevel(0);
			user.setIcon(IConstants.DEFAULT_USER_ICON);
			user.setNickName(email.substring(0, email.indexOf("@")));
			userService.register(user);
			// 生成邮箱验证码
			UserVerified uv = new UserVerified(user);
			verifiedService.save(uv);
			// 发送注册成功的邮件
			if (CommonUtils.isNotEmpty(user.getEmail())) {
				sendRegisterMail(user, uv.getCode());
			}

			return this.toJSONResult(true);
		} catch (CoreException e) {
			return this.toJSONResult(false, this.getMessage(e));
		}
	}

	@RequestMapping("/forget")
	public String forget() {
		return "site/forget.jsp";
	}

	@RequestMapping("/send_forget")
	public @ResponseBody JSONObject login(
			@RequestParam(value = "email", required = true) String email,
			ModelMap model, HttpServletResponse response) {
		User user = userService.findUniqueBy("email", email);
		if (user == null || user.getId() == null) {
			return this.toJSONResult(false, "该邮箱未注册!");
		}
		long time = System.currentTimeMillis();
		String code = email + "_t_" + time;
		String ucode = Encrypt.encryptSSO(code, IConstants.HELLO_WORD);
		sendForgetMail(email, ucode);
		return this.toJSONResult(true, "邮件已发送至您的邮箱，请确认");
	}

	@RequestMapping("/reset_forget")
	public @ResponseBody JSONObject reset_forget(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "psw", required = true) String psw,
			ModelMap model, HttpServletResponse response) {
		User user = userService.findOne(id);
		if (user == null || user.getId() == null) {
			return this.toJSONResult(false, "该邮箱未注册!");
		}
		user.setPassword(EncryptionUtils.encryptBasedMd5(psw));
		user.setUpdatedTime(new Date());
		userService.update(user);
		return this.toJSONResult(true, "重置成功");
	}

	@RequestMapping("/confirm_forget")
	public String ucode(
			@RequestParam(value = "ucode", required = true) String ucode,
			ModelMap model, HttpServletResponse response) {
		String email = null;
		try {
			String res = Encrypt.decryptSSO(ucode, IConstants.HELLO_WORD);
			res = new String(res.getBytes(), "UTF-8");
			int index = res.lastIndexOf("_t_");
			String t = res.substring(index + "_t_".length(), res.length());
			long reqTime = Long.parseLong(t);
			long interval = System.currentTimeMillis() - reqTime;
			// 设置阀值15分钟
			long threshold = 15 * 60 * 1000;
			if (threshold >= interval) {
				email = res.substring(0, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "系统错误");
			return "site/error.jsp";
		}
		if (CommonUtils.isEmpty(email)) {
			LOGGER.error("email为空！");
			model.addAttribute("message", "email为空！");
			return "site/error.jsp";
		}
		User user = userService.findUniqueBy("email", email);
		if (user == null || user.getId() == null) {
			LOGGER.error("用户未找到！");
			model.addAttribute("message", "用户未找到！");
			return "site/error.jsp";
		}
		User _user = new User();
		_user.setId(user.getId());
		_user.setEmail(email);
		model.addAttribute("user", _user);
		return "site/reset_psw.jsp";

	}

	@RequestMapping("/login")
	public @ResponseBody JSONObject login(
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "remember_me", required = false, defaultValue = "0") int remember_me,
			ModelMap model, HttpServletResponse response) {

		try {
			// 登录校验
			User loginUser = userService.loginCheck(loginName, password);
			// 记录session
			setUser2Session(loginUser);
			if (remember_me == 1) {
				setUserCookie(loginName, password, response);
			}

			return this.toJSONResult(true);

		} catch (CoreException e) {
			if (e.getErrorCode() == PortalErrorCode.USER_PASSWORD_ERROR_TIMES_EXCEED_ERROR) {
				return this
						.toJSONResult(false, this.getMessage(e, e.getArgs()));
			} else {

				return this.toJSONResult(false, this.getMessage(e));
			}
		}
	}

	@RequestMapping("/logout")
	public String logout(String loginName, ModelMap model,
			HttpServletResponse response) {
		// 删除session
		SessionContextHolder.getSession().removeAttribute(
				IPortalConstants.SESSION_KEY_LOGIN_USER);
		return "redirect:/index?from=logout";
	}

	@RequestMapping("/verified")
	public String active(
			@RequestParam(value = "user", required = true) String userId,
			@RequestParam(value = "code", required = true) String code) {
		UserVerified uv = verifiedService.findOneByIdAndCode(userId, code);
		User loginUser;
		if (uv != null && uv.getVerifiedTime() == null) {
			loginUser = userService.findOne(userId);
			loginUser.setStatus(UserStatus.ACTIVE.getCode());
			loginUser.setIsEmailVerified(true);
			userService.update(loginUser);
			uv.setVerifiedTime(new Date());
			verifiedService.save(uv);

			// 记录session
			Object obj = SessionContextHolder.getSession().getAttribute(
					IPortalConstants.SESSION_KEY_LOGIN_USER);
			if (obj == null) {
				setUser2Session(loginUser);

			}
		}

		return "redirect:/index";
	}

	private void setUserCookie(String name, String psw,
			HttpServletResponse response) {
		String host = IPortalConstants.PORTAL_DOMAIN;
		Cookie cookie = new Cookie(IPortalConstants.COOKIE_KEY_LOGIN_USER, name); // 保存用户名到Cookie
		cookie.setPath("/");
		cookie.setDomain(host);
		cookie.setMaxAge(99999999);
		response.addCookie(cookie);
		// 保存密码到Cookie，注意需要加密一下
		Cookie cookie_p = new Cookie(IPortalConstants.COOKIE_KEY_LOGIN_PSW,
				Encrypt.encryptSSO(psw, IConstants.HELLO_WORD));
		cookie_p.setPath("/");
		cookie_p.setDomain(host);
		cookie_p.setMaxAge(99999999);
		response.addCookie(cookie_p);
	}

	/**
	 * 修改邮箱接口只针对第三方登录，为注册账户的用户开放
	 * */
	@RequestMapping("/email")
	public @ResponseBody JSONObject email(
			@RequestParam(value = "email", required = true) String email,
			ModelMap model, HttpServletResponse response) {
		LoginUser loginUser = this.getCurrentLoginUser();
		boolean b = userService.isEmailUsed(email, loginUser.getId());
		if (b) {
			return this.toJSONResult(false, "该邮箱已被其他账户绑定");
		}

		User user = userService.findOne(loginUser.getId());
		user.setEmail(email);
		user.setUpdatedBy(user.getId());
		user.setUpdatedTime(new Date());
		userService.update(user);

		UserVerified uv = new UserVerified(user);
		verifiedService.save(uv);
		// 发送注册成功的邮件
		if (CommonUtils.isNotEmpty(user.getEmail())) {
			sendRegisterMail(user, uv.getCode());
		}

		return this.toJSONResult(true, "保存成功");
	}

	@RequestMapping("/settings")
	public String settings(ModelMap model,@RequestParam(value = "errorMsg", required = false) String errorMsg) {
		LoginUser loginUser = this.getCurrentLoginUser();
		User user = userService.findOne(loginUser.getId());
		if (user.getStatus().intValue() == UserStatus.INTITAL.getCode()) {
			model.addAttribute("reActive", true);
		}else{
			model.addAttribute("reActive", false);
		}
		List<BAuth> auths = user.getAuths();

		model.addAttribute("email", user.getEmail());
		model.addAttribute("qq", false);
		model.addAttribute("sina", false);
		for (BAuth bAuth : auths) {
			if (bAuth.getType().intValue() == AuthType.QQ.getCode()) {
				model.addAttribute("qq", true);
			} else if (bAuth.getType().intValue() == AuthType.SINA.getCode()) {
				model.addAttribute("sina", true);
			}
		}
		model.addAttribute("errorMsg", errorMsg);
		return "site/settings.jsp";
	}

	@RequestMapping("/reEmail")
	public @ResponseBody JSONObject reActiveEmail(ModelMap model) {
		LoginUser loginUser = this.getCurrentLoginUser();
		if (loginUser == null)
			return this.toJSONResult(false, "非法操作");
		User user = userService.findOne(loginUser.getId());
		if (user.getStatus().intValue() != UserStatus.INTITAL.getCode()) {
			return this.toJSONResult(false, "该账户已被管理员禁用，请联系管理员");
		}
		UserVerified uv = verifiedService.findUniqueBy("user.id", user.getId());
		if (uv == null) {
			uv = new UserVerified(user);
			verifiedService.save(uv);
		}
		// 发送注册成功的邮件
		if (CommonUtils.isNotEmpty(user.getEmail())) {
			sendRegisterMail(user, uv.getCode());
		}else{
			return this.toJSONResult(false, "请先完善您的邮箱信息！");
		}
		return this.toJSONResult(true, "激活邮件已经重新发送，请查阅");
	}

	@RequestMapping("/updatePWD")
	public @ResponseBody JSONObject uptPSW(
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "new_password", required = true) String new_password,
			ModelMap model, HttpServletResponse response) {
		LoginUser loginUser = this.getCurrentLoginUser();

		try {
			userService.updatePasswordById(loginUser.getId(), password,
					new_password);
		} catch (PortalException e) {
			e.printStackTrace();
			return this.toJSONResult(false, this.getMessage(e));
		}
		return this.toJSONResult(true, "保存成功");
	}

	/*
	*//**
	 * 请求需要用json格式
	 * 
	 * @param user
	 * @return
	 * @throws CoreException
	 */
	/*
	 * @RequestMapping(value = "/update")
	 * 
	 * @ResponseBody public JSONObject update(@RequestBody user user) { try {
	 * userService.update(user); return this.toJSONResult(true); } catch
	 * (CoreException e) { return this.toJSONResult(false, this.getMessage(e));
	 * } }
	 * 
	 * @RequestMapping(value = "/changePwd")
	 * 
	 * @ResponseBody public JSONObject changePwd(String oldPwd, String newPwd,
	 * HttpServletRequest request, HttpServletResponse response) { try { user
	 * loginUser = (user) SessionContextHolder .getSession().getAttribute(
	 * IPortalConstant.SESSION_KEY_LOGIN_USER); newPwd =
	 * userService.updatePassword(loginUser.getLoginName(), oldPwd, newPwd);
	 * 
	 * loginUser.setPassword(newPwd);
	 * SessionContextHolder.getSession().setAttribute(
	 * IPortalConstant.SESSION_KEY_LOGIN_USER, loginUser);
	 * 
	 * // add by gavin 更新cache中的user信息 String loginUserKey = this.getFromCookie(
	 * IPortalConstant.COOKIE_KEY_TOKEN, request);
	 * this.syncSSOAddCache(loginUserKey, loginUser, response); // end
	 * 
	 * return this.toJSONResult(true); } catch (CoreException e) { return
	 * this.toJSONResult(false, this.getMessage(e)); } }
	 */

	/*
	 * @RequestMapping("/myInfo") public String myInfo() { return
	 * "/mcenter/myInfo.jsp"; }
	 * 
	 * @RequestMapping("/find") public @ResponseBody String
	 * find(@RequestParam("loginName") String loginName) throws CoreException {
	 * user user = userService.find(loginName); return
	 * this.toStringResultFromJson(true, user); }
	 * 
	 * @RequestMapping("/loadSelf") public @ResponseBody JSONObject loadSelf()
	 * throws CoreException { user user = (user) SessionContextHolder
	 * .getSession().getAttribute( IPortalConstant.SESSION_KEY_LOGIN_USER);
	 * return this.toJSONResult(true, user); }
	 * 
	 * @RequestMapping("/updateSelf") public @ResponseBody JSONObject
	 * updateSelf(@ModelAttribute user user, HttpServletRequest request,
	 * HttpServletResponse response) { user persist = (user)
	 * SessionContextHolder.getSession()
	 * .getAttribute(IPortalConstant.SESSION_KEY_LOGIN_USER);
	 * persist.setRealName(user.getRealName());
	 * persist.setMobilePhone(user.getMobilePhone());
	 * persist.setEmail(user.getEmail()); persist.setQq(user.getQq());
	 * persist.setProvince(user.getProvince()); persist.setCity(user.getCity());
	 * persist.setCounty(user.getCounty()); try {
	 * userService.updateSelf(persist);
	 * SessionContextHolder.getSession().setAttribute(
	 * IPortalConstant.SESSION_KEY_LOGIN_USER, persist);
	 * 
	 * // add by gavin 更新cache中的user信息 String loginUserKey = this.getFromCookie(
	 * IPortalConstant.COOKIE_KEY_TOKEN, request);
	 * this.syncSSOAddCache(loginUserKey, persist, response); // end
	 * 
	 * return this.toJSONResult(true); } catch (CoreException e) { return
	 * this.toJSONResult(false, this.getMessage(e)); } }
	 */

	/*
	 * 
	 * @RequestMapping("/updatePassword") public @ResponseBody JSONObject
	 * updatePassword(String loginName, String oldPassword, String newPassword)
	 * { try { userService.updatePassword(loginName, oldPassword, newPassword);
	 * return this.toJSONResult(true); } catch (CoreException e) { return
	 * this.toJSONResult(false, this.getMessage(e)); } }
	 */
}
