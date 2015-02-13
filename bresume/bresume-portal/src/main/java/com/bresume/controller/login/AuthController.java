package com.bresume.controller.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;

import com.bresume.core.common.base.controller.PortalController;
import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IAdminConstants;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.AuthType;
import com.bresume.core.common.utils.PropertiesLoader;
import com.bresume.core.common.utils.mail.Email;
import com.bresume.core.common.utils.mail.MailUtils;
import com.bresume.core.model.dto.LoginUser;
import com.bresume.core.model.entity.user.BAuth;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.user.IBAuthService;

public abstract class AuthController extends PortalController {
	@Resource
	private JavaMailSender mailSender;

	@Resource
	private IBAuthService authService;

	protected void sendRegisterMail(User user, String code) {
		PropertiesLoader loader = new PropertiesLoader("mail.properties");

		Map<String, Object> map = new HashMap<String, Object>();
		Email email = new Email();
		email.setSender(loader.getProperty("mail.from"));
		email.setAddress(user.getEmail());

		email.setSubject(loader.getProperty("mail.register.success.subject"));
		// 从模板生成
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userName", user.getUserName());
		param.put("userId", user.getId());
		param.put("code", code);
		email.setContent(MailUtils.getMailText(param,
				loader.getProperty("mail.register.success.content")));
		map.put("email", email);
		MailUtils.sendMailByAsynchronousMode(map, mailSender);

	}
	
	protected void sendForgetMail(String emailAdd,String code) {
		PropertiesLoader loader = new PropertiesLoader("mail.properties");

		Map<String, Object> map = new HashMap<String, Object>();
		Email email = new Email();
		email.setSender(loader.getProperty("mail.from"));
		email.setAddress(emailAdd);

		email.setSubject("密码重置确认通知");
		// 从模板生成
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("ucode", code);
		email.setContent(MailUtils.getMailText(param,
				loader.getProperty("mail.forget.content")));
		map.put("email", email);
		MailUtils.sendMailByAsynchronousMode(map, mailSender);

	}

	protected String callBack(Model model, BAuth newAuth) {
		BAuth oldAuth = authService.findOne(newAuth.getOpenId(),
				newAuth.getType());
		if (oldAuth != null && oldAuth.getUser() != null) {
			// 判定有登录记录
			// 刷新accessToken
			oldAuth.setAccessToken(newAuth.getAccessToken());
			oldAuth.setExpiresIn(newAuth.getExpiresIn());
			oldAuth.setIcon(newAuth.getIcon());
			oldAuth.setNickName(newAuth.getNickName());
			oldAuth.setRefreshAccessTime(new Date());
			authService.update(oldAuth);
			this.setUser2Session(oldAuth);
			return "redirect:/index";
		} else if (oldAuth == null) {
			// 判定首次登录，记录
			oldAuth = new BAuth();
			oldAuth.setAccessToken(newAuth.getAccessToken());
			oldAuth.setExpiresIn(newAuth.getExpiresIn());
			oldAuth.setCreatedTime(new Date());
			oldAuth.setIcon(newAuth.getIcon());
			oldAuth.setNickName(newAuth.getNickName());
			oldAuth.setOpenId(newAuth.getOpenId());
			oldAuth.setRefreshAccessTime(new Date());
			oldAuth.setType(newAuth.getType());
			authService.save(oldAuth);
			// 用户绑定,跳转页面
			model.addAttribute("openId", newAuth.getOpenId());
			model.addAttribute("loginFrom", newAuth.getType());
			return "site/bindAuth.jsp";
		} else {
			// 登录过但因某种原因为绑定账户
			oldAuth.setAccessToken(newAuth.getAccessToken());
			oldAuth.setExpiresIn(newAuth.getExpiresIn());
			oldAuth.setIcon(newAuth.getIcon());
			oldAuth.setNickName(newAuth.getNickName());
			oldAuth.setRefreshAccessTime(new Date());
			authService.update(oldAuth);
			// 用户绑定,跳转页面
			model.addAttribute("openId", newAuth.getOpenId());
			model.addAttribute("loginFrom", newAuth.getType());
			return "site/bindAuth.jsp";
		}

	}

}
