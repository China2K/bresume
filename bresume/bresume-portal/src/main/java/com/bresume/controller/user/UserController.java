package com.bresume.controller.user;

import java.util.Date;

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
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.RegisterType;
import com.bresume.core.common.constant.enums.UserStatus;
import com.bresume.core.common.constant.enums.UserType;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.exception.PortalErrorCode;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.model.entity.user.UserVerified;
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

	@RequestMapping("/register")
	public @ResponseBody JSONObject register(
			// @RequestParam(value = "userName", required = true) String
			// userName,
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
