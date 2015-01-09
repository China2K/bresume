package com.bresume.controller.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

import com.bresume.core.common.base.controller.BaseController;



@RequestMapping("/")
@Controller
public class SinaController extends AuthController {

	@RequestMapping("/sinalogin")
	public void index(HttpServletRequest request, HttpServletResponse response,
			Model model) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			response.sendRedirect(new Oauth().authorize("code"));
			LOGGER.info("login by weibo");
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	
	}

	@RequestMapping("/weibo_callback")
	public void callback(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		try{
		Oauth oauth = new Oauth();
		String code = request.getParameter("code");
		LOGGER.info("code: " + code);
		AccessToken accessTokenObj = oauth.getAccessTokenByCode(code);
		if(accessTokenObj==null){
			LOGGER.error("AccessToken 获取失败，code:"+code);
		}
		String accessToken = accessTokenObj.getAccessToken();
		String openId=accessTokenObj.getUID();
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				LOGGER.error("Unable to get the access token.");
			}else{
				e.printStackTrace();
			}
		}

		
	}


}
