package com.bresume.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.RegisterType;
import com.bresume.core.common.constant.enums.UserType;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.exception.PortalErrorCode;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.PropertiesLoader;
import com.bresume.core.common.utils.mail.Email;
import com.bresume.core.common.utils.mail.MailUtils;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.model.entity.user.UserVerified;
import com.bresume.core.service.user.IUserService;
import com.bresume.core.service.user.IUserVerifiedService;

@RequestMapping("/user")
@Controller
public class UserController extends BaseController {
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IUserVerifiedService verifiedService;

	@Resource
	private JavaMailSender mailSender;


	@RequestMapping("/register")
	public @ResponseBody
	JSONObject register(
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password, 
			HttpServletResponse response, HttpServletRequest request) {
		User user=new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);

		try {
			user.setRegisterType(RegisterType.PORTAL_REGISTER.getType());
			user.setType(UserType.PERSIONAL.getCode());
			user.setLevel(0);
			userService.register(user);
			//生成邮箱验证码
			UserVerified uv = new UserVerified(user);
			verifiedService.save(uv);
			// 发送注册成功的邮件
			if (CommonUtils.isNotEmpty(user.getEmail())) {
				sendRegisterMail(user,uv.getCode());
			}

			return this.toJSONResult(true);
		} catch (CoreException e) {
			return this.toJSONResult(false, this.getMessage(e));
		}
	}

	private void sendRegisterMail(User user,String code) {
		PropertiesLoader loader = new PropertiesLoader("mail.properties");

		Map<String, Object> map = new HashMap<String, Object>();
		Email email = new Email();
		email.setSender(loader.getProperty("mail.from"));
		email.setAddress(user.getEmail());
		
		email.setSubject(loader.getProperty("mail.register.success.subject"));
		// 从模板生成
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userName", user.getUserName());
		param.put("code", code);
		email.setContent(MailUtils.getMailText(param,
				loader.getProperty("mail.register.success.content")));

		map.put("email", email);
		MailUtils.sendMailByAsynchronousMode(map, mailSender);

	}

	
	@RequestMapping("/login")
	public @ResponseBody
	JSONObject login(@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "password", required = true) String password, 
			ModelMap model,HttpServletResponse response) {

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

	@RequestMapping("/logout")
	public String logout(String loginName, ModelMap model,
			HttpServletResponse response) {
		// 删除session
		SessionContextHolder.getSession().removeAttribute(IPortalConstants.SESSION_KEY_LOGIN_USER);
		return "redirect:/index";
	}

	
	@RequestMapping("/verified")
	public String active(@RequestParam(value = "user", required = true) String userName,
			@RequestParam(value = "code", required = true) String code) {
		UserVerified uv = verifiedService.findOne(userName, code);
		if(uv!=null&&uv.getVerifiedTime()==null){
			try {
				userService.active(userName);
				uv.setVerifiedTime(new Date());
				verifiedService.save(uv);
			} catch (CoreException e) {
				LOGGER.error(e.getErrorMsg());
			}
			
			// 记录session
			Object obj = SessionContextHolder.getSession().getAttribute(IPortalConstants.SESSION_KEY_LOGIN_USER);
			if(obj==null){
				try {
					User loginUser = userService.find(userName);
					SessionContextHolder.getSession().setAttribute(
							IPortalConstants.SESSION_KEY_LOGIN_USER, loginUser);
				} catch (CoreException e) {
					e.printStackTrace();
				}
				
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
	 *//*
	@RequestMapping(value = "/update")
	@ResponseBody
	public JSONObject update(@RequestBody user user) {
		try {
			userService.update(user);
			return this.toJSONResult(true);
		} catch (CoreException e) {
			return this.toJSONResult(false, this.getMessage(e));
		}
	}

	@RequestMapping(value = "/changePwd")
	@ResponseBody
	public JSONObject changePwd(String oldPwd, String newPwd,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			user loginUser = (user) SessionContextHolder
					.getSession().getAttribute(
							IPortalConstant.SESSION_KEY_LOGIN_USER);
			newPwd = userService.updatePassword(loginUser.getLoginName(),
					oldPwd, newPwd);

			loginUser.setPassword(newPwd);
			SessionContextHolder.getSession().setAttribute(
					IPortalConstant.SESSION_KEY_LOGIN_USER, loginUser);

			// add by gavin 更新cache中的user信息
			String loginUserKey = this.getFromCookie(
					IPortalConstant.COOKIE_KEY_TOKEN, request);
			this.syncSSOAddCache(loginUserKey, loginUser, response);
			// end

			return this.toJSONResult(true);
		} catch (CoreException e) {
			return this.toJSONResult(false, this.getMessage(e));
		}
	}*/
	
	/*@RequestMapping("/myInfo")
	public String myInfo() {
		return "/mcenter/myInfo.jsp";
	}

	@RequestMapping("/find")
	public @ResponseBody
	String find(@RequestParam("loginName") String loginName)
			throws CoreException {
		user user = userService.find(loginName);
		return this.toStringResultFromJson(true, user);
	}

	@RequestMapping("/loadSelf")
	public @ResponseBody
	JSONObject loadSelf() throws CoreException {
		user user = (user) SessionContextHolder
				.getSession().getAttribute(
						IPortalConstant.SESSION_KEY_LOGIN_USER);
		return this.toJSONResult(true, user);
	}

	@RequestMapping("/updateSelf")
	public @ResponseBody
	JSONObject updateSelf(@ModelAttribute user user,
			HttpServletRequest request, HttpServletResponse response) {
		user persist = (user) SessionContextHolder.getSession()
				.getAttribute(IPortalConstant.SESSION_KEY_LOGIN_USER);
		persist.setRealName(user.getRealName());
		persist.setMobilePhone(user.getMobilePhone());
		persist.setEmail(user.getEmail());
		persist.setQq(user.getQq());
		persist.setProvince(user.getProvince());
		persist.setCity(user.getCity());
		persist.setCounty(user.getCounty());
		try {
			userService.updateSelf(persist);
			SessionContextHolder.getSession().setAttribute(
					IPortalConstant.SESSION_KEY_LOGIN_USER, persist);

			// add by gavin 更新cache中的user信息
			String loginUserKey = this.getFromCookie(
					IPortalConstant.COOKIE_KEY_TOKEN, request);
			this.syncSSOAddCache(loginUserKey, persist, response);
			// end

			return this.toJSONResult(true);
		} catch (CoreException e) {
			return this.toJSONResult(false, this.getMessage(e));
		}
	}
*/
	
	
	/*

	@RequestMapping("/updatePassword")
	public @ResponseBody
	JSONObject updatePassword(String loginName, String oldPassword,
			String newPassword) {
		try {
			userService.updatePassword(loginName, oldPassword, newPassword);
			return this.toJSONResult(true);
		} catch (CoreException e) {
			return this.toJSONResult(false, this.getMessage(e));
		}
	}
*/
}
