package com.bresume.controller.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.AuthType;
import com.bresume.core.common.utils.PropertiesLoader;
import com.bresume.core.common.utils.mail.Email;
import com.bresume.core.common.utils.mail.MailUtils;
import com.bresume.core.model.dto.LoginUser;
import com.bresume.core.model.entity.user.BAuth;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.user.IBAuthService;


public abstract class AuthController extends BaseController {
	@Resource
	private JavaMailSender mailSender;
	
	@Resource
	private IBAuthService authService;

	protected LoginUser getU(BAuth auth){
		LoginUser loginUser= new LoginUser();
		loginUser.setAccessToken(auth.getAccessToken());
		loginUser
				.setIcon(auth.getIcon() == null ? IPortalConstants.defaultIconUrl
						: auth.getIcon());
		loginUser.setId(auth.getUser().getId());
		loginUser.setLoginType(auth.getType());
		loginUser.setNickName(auth.getNickName() == null ? auth.getUser()
				.getNickName() : auth.getNickName());
		loginUser.setOpenId(auth.getOpenId());
		return loginUser;
	}
	
	protected LoginUser getU(User user){
		LoginUser loginUser= new LoginUser();
		loginUser
				.setIcon(user.getIcon() == null ? IPortalConstants.defaultIconUrl
						: user.getIcon());
		loginUser.setId(user.getId());
		loginUser.setLoginType(AuthType.bresume.getCode());
		loginUser.setNickName( user.getNickName());
		return loginUser;
	}
	
	protected boolean setUser2Session(BAuth auth){
		LoginUser loginuser = this.getU(auth);
		SessionContextHolder.getSession().setAttribute(IPortalConstants.SESSION_KEY_LOGIN_USER, loginuser);
		return true;
	}
	
	protected boolean setUser2Session(User user){
		LoginUser loginuser = this.getU(user);
		SessionContextHolder.getSession().setAttribute(IPortalConstants.SESSION_KEY_LOGIN_USER, loginuser);
		return true;
	}
	
	protected void sendRegisterMail(User user,String code) {
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
	
	protected String callBack(Model model,BAuth newAuth){
		BAuth oldAuth = authService.findOne(newAuth.getOpenId(),newAuth.getType());
		if (oldAuth != null && oldAuth.getUser() != null) {
			// 判定有登录记录
			//刷新accessToken
			oldAuth.setAccessToken(newAuth.getAccessToken());
			oldAuth.setExpiresIn(newAuth.getExpiresIn());
			oldAuth.setIcon(newAuth.getIcon());
			oldAuth.setNickName(newAuth.getNickName());
			oldAuth.setRefreshAccessTime(new Date());
			authService.update(oldAuth);
			this.setUser2Session(oldAuth);
			return "redirect:/index";
		} else if(oldAuth==null) {
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
			//用户绑定,跳转页面
			model.addAttribute("openId", newAuth.getOpenId());
			model.addAttribute("loginFrom", newAuth.getType());
			return "site/bindAuth.jsp";
		}else{
			// 登录过但因某种原因为绑定账户
			oldAuth.setAccessToken(newAuth.getAccessToken());
			oldAuth.setExpiresIn(newAuth.getExpiresIn());
			oldAuth.setIcon(newAuth.getIcon());
			oldAuth.setNickName(newAuth.getNickName());
			oldAuth.setRefreshAccessTime(new Date());
			authService.update(oldAuth);
			//用户绑定,跳转页面
			model.addAttribute("openId", newAuth.getOpenId());
			model.addAttribute("loginFrom", newAuth.getType());
			return "site/bindAuth.jsp";
		}

	}
	
}
