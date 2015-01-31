package com.bresume.controller.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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
			user.setNickName(email.substring(0,email.indexOf("@")));
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

	@RequestMapping("/login")
	public @ResponseBody JSONObject login(
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password,
			ModelMap model, HttpServletResponse response) {

		try {
			// 登陆校验
			User loginUser = userService.loginCheck(loginName, password);
			// 记录session
			setUser2Session(loginUser);

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
		return "redirect:/index";
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
	public String settings(ModelMap model) {
		LoginUser loginUser = this.getCurrentLoginUser();
		User user = userService.findOne(loginUser.getId());
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
		return "site/settings.jsp";
	}
	
	
	@RequestMapping("/updatePWD")
	public @ResponseBody JSONObject uptPSW(
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "new_password", required = true) String new_password,
			ModelMap model, HttpServletResponse response) {
		LoginUser loginUser = this.getCurrentLoginUser();
		
		try {
			userService.updatePasswordById(loginUser.getId(), password, new_password);
		} catch (PortalException e) {
			e.printStackTrace();
			this.toJSONResult(false, this.getMessage(e));
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
