package com.bresume.admin.controller.sysUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.AdminController;
import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IAdminConstants;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.exception.PortalErrorCode;
import com.bresume.core.common.utils.EncryptionUtils;
import com.bresume.core.model.entity.sys.SysUser;
import com.bresume.core.service.sys.ISysUserService;
import com.bresume.core.service.user.IUserVerifiedService;

@RequestMapping("/sys/user")
@Controller
public class UserController extends AdminController {

	@Resource
	private ISysUserService sysUserService;

	@Resource
	private IUserVerifiedService verifiedService;

	@RequestMapping("/register.do")
	public @ResponseBody JSONObject register(
			// @RequestParam(value = "userName", required = true) String
			// userName,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			HttpServletResponse response, HttpServletRequest request) {
		SysUser user = new SysUser();
		// user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);

		try {
			sysUserService.register(user);
			return this.toJSONResult(true);
		} catch (CoreException e) {
			return this.toJSONResult(false, this.getMessage(e));
		}
	}

	@RequestMapping("/login.do")
	public @ResponseBody JSONObject login(
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password,
			ModelMap model, HttpServletResponse response) {

		try {
			// 登陆校验
			SysUser loginUser = sysUserService.loginCheck(loginName, password);
			// 记录session
			SessionContextHolder.getSession().setAttribute(
					IAdminConstants.SESSION_KEY_LOGIN_USER, loginUser);
			SessionContextHolder.getSession().setAttribute(
							IAdminConstants.SESSION_USER_UPLOAD_INFO, getUploadAuthInfo(loginUser.getId(), loginUser.getPassword()));
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

	@RequestMapping("/logout.do")
	public String logout(String loginName, ModelMap model,
			HttpServletResponse response) {
		// 删除session
		SessionContextHolder.getSession().removeAttribute(
				IAdminConstants.SESSION_KEY_LOGIN_USER);
		SessionContextHolder.getSession().removeAttribute(
				IAdminConstants.SESSION_USER_UPLOAD_INFO);
		return "redirect:/index.do";
	}

}
