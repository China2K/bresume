package com.bresume.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.constant.enums.AuthType;
import com.bresume.core.common.constant.enums.RegisterType;
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

@RequestMapping("/")
@Controller
public class BindController extends AuthController {

	@Resource
	private IUserService userService;

	@Resource
	private IBAuthService authService;

	@Resource
	private IUserVerifiedService verifiedService;

	@Resource
	private JavaMailSender mailSender;

	@RequestMapping("/ingore-bind")
	public String ingore_bind(
			@RequestParam(value = "loginFrom", required = true) Integer loginFrom,
			@RequestParam(value = "openId", required = true) String openId,
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
			user.setIcon(auth.getIcon());

			user.setRegisterType(AuthType.fromCode(loginFrom).getRt().getType());
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
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			ModelMap model, HttpServletResponse response) {

		BAuth auth = authService.findOne(openId, loginFrom);
		if (auth == null) {
			return this.toJSONResult(false, "404");
		}

		if (auth.getUser() == null) {
			try {
				// 登陆校验
				User user = userService.loginCheck(email, password);
				auth.setUser(user);
				authService.update(auth);
			} catch (CoreException e) {
				if (e.getErrorCode() == PortalErrorCode.USER_PASSWORD_ERROR_TIMES_EXCEED_ERROR) {
					return this.toJSONResult(false,
							this.getMessage(e, e.getArgs()));
				} else {

					return this.toJSONResult(false, this.getMessage(e));
				}
			}
		}

		this.setUser2Session(auth);
		return this.toJSONResult(true);
	}

	@RequestMapping("/regist-bind")
	public @ResponseBody JSONObject registBind(
			@RequestParam(value = "loginFrom", required = true) Integer loginFrom,
			@RequestParam(value = "openId", required = true) String openId,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			ModelMap model, HttpServletResponse response) {
		BAuth auth = authService.findOne(openId, loginFrom);
		if (auth == null) {
			return this.toJSONResult(false);
		}
		if (auth.getUser() == null) {
			User user = new User();
			// user.setUserName(userName);
			user.setPassword(password);
			user.setEmail(email);

			try {
				user.setRegisterType(RegisterType.PORTAL_REGISTER.getType());
				user.setType(UserType.PERSIONAL.getCode());
				user.setLevel(0);
				user.setNickName(auth.getNickName());
				user.setIcon(auth.getIcon());
				userService.register(user);
				// 生成邮箱验证码
				UserVerified uv = new UserVerified(user);
				verifiedService.save(uv);
				// 发送注册成功的邮件
				if (CommonUtils.isNotEmpty(user.getEmail())) {
					sendRegisterMail(user, uv.getCode());
				}
			} catch (CoreException e) {
				return this.toJSONResult(false, this.getMessage(e));
			}
			auth.setUser(user);
			authService.save(auth);
		}

		this.setUser2Session(auth);
		return this.toJSONResult(true);
	}

	@RequestMapping(value="/remove-bind/{authType}")
	public void removeBind(@PathVariable Integer authType,
			ModelMap model, HttpServletResponse response) {
		LoginUser user = this.getCurrentLoginUser();
		authService.removeBind(user.getId(), authType);
	}

}
