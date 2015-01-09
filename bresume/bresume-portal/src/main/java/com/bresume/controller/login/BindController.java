package com.bresume.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.AuthType;
import com.bresume.core.common.constant.enums.RegisterType;
import com.bresume.core.common.constant.enums.UserType;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.exception.PortalErrorCode;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.model.dto.LoginUser;
import com.bresume.core.model.entity.user.BAuth;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.model.entity.user.UserVerified;
import com.bresume.core.service.user.IBAuthService;
import com.bresume.core.service.user.IUserService;

@RequestMapping("/user")
@Controller
public class BindController extends AuthController {

	@Resource
	private IUserService userService;

	@Resource
	private IBAuthService authService;

	@RequestMapping("/login")
	public @ResponseBody JSONObject login(
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password,
			ModelMap model, HttpServletResponse response) {

		try {
			// 登陆校验
			userService.loginCheck(loginName, password);

			User loginUser = userService.find(loginName);
			// 记录session
			SessionContextHolder.getSession().setAttribute(
					IPortalConstants.SESSION_KEY_LOGIN_USER, loginUser);

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

	@RequestMapping("/ingore-bind")
	public String ingore_bind(
			@RequestParam(value = "loginFrom", required = true) Integer loginFrom,
			@RequestParam(value = "openId", required = true) String openId,
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password,
			ModelMap model, HttpServletResponse response) {
		BAuth auth = authService.findOne(openId, loginFrom);
		if (auth == null) {
			return "404";
		}
		if (auth.getUser() == null) {
			User user = new User();
			/*
			 * user.setUserName(userName); user.setPassword(password);
			 */
			// user.setEmail(email);
			user.setNickName(auth.getNickName());

			user.setRegisterType(RegisterType.PORTAL_REGISTER.getType());
			user.setType(UserType.PERSIONAL.getCode());
			user.setLevel(0);
			userService.registerFromAuth(user);
			auth.setUser(user);
			authService.save(auth);
		}

		this.setUser2Session(auth);
		return "redirect:/index";
	}
	
	@RequestMapping("/login-bind")
	public @ResponseBody JSONObject bind(
			@RequestParam(value = "loginFrom", required = true) Integer loginFrom,
			@RequestParam(value = "openId", required = true) String openId,
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password,
			ModelMap model, HttpServletResponse response) {
		BAuth auth = authService.findOne(openId, loginFrom);
		if (auth == null) {
			return this.toJSONResult(false);
		}
		if (auth.getUser() == null) {
			User user = new User();
			/*
			 * user.setUserName(userName); user.setPassword(password);
			 */
			// user.setEmail(email);
			user.setNickName(auth.getNickName());

			user.setRegisterType(RegisterType.PORTAL_REGISTER.getType());
			user.setType(UserType.PERSIONAL.getCode());
			user.setLevel(0);
			userService.registerFromAuth(user);
			auth.setUser(user);
			authService.save(auth);
		}

		this.setUser2Session(auth);
		return this.toJSONResult(false);
	}
	
	@RequestMapping("/regist-bind")
	public @ResponseBody JSONObject registBind(
			@RequestParam(value = "loginFrom", required = true) Integer loginFrom,
			@RequestParam(value = "openId", required = true) String openId,
			@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password,
			ModelMap model, HttpServletResponse response) {
		BAuth auth = authService.findOne(openId, loginFrom);
		if (auth == null) {
			return this.toJSONResult(false);
		}
		if (auth.getUser() == null) {
			User user = new User();
			/*
			 * user.setUserName(userName); user.setPassword(password);
			 */
			// user.setEmail(email);
			user.setNickName(auth.getNickName());

			user.setRegisterType(RegisterType.PORTAL_REGISTER.getType());
			user.setType(UserType.PERSIONAL.getCode());
			user.setLevel(0);
			userService.registerFromAuth(user);
			auth.setUser(user);
			authService.save(auth);
		}

		this.setUser2Session(auth);
		return this.toJSONResult(false);
	}

}
