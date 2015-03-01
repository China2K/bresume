package com.bresume.controller.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bresume.core.common.constant.enums.AuthType;
import com.bresume.core.model.entity.user.BAuth;
import com.bresume.core.service.user.IBAuthService;
import com.bresume.core.service.user.IUserService;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.Topic;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.GeneralResultBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

@RequestMapping("/")
@Controller
public class QQController extends AuthController {

	@Resource
	private IBAuthService authService;

	@Resource
	private IUserService userService;

	@RequestMapping("/qqlogin")
	public void index(HttpServletRequest request, HttpServletResponse response,
			Model model) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
			LOGGER.info("login by qq");
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/qq_callback")
	public String callback(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			AccessToken accessTokenObj = (new Oauth())
					.getAccessTokenByRequest(request);

			String accessToken = null, openID = null;
			long tokenExpireIn = 0L;

			if (accessTokenObj.getAccessToken().equals("")) {
				LOGGER.error("QQ Login failed,caused by 没有获取到响应参数");
				model.addAttribute("message", "QQ 登录失败,由于没有获取到响应参数");
				return "site/error.jsp";
			}

			accessToken = accessTokenObj.getAccessToken();
			tokenExpireIn = accessTokenObj.getExpireIn();
			LOGGER.info("Get accessToken from qq,accessToken:" + accessToken
					+ ",tokenExpireIn" + tokenExpireIn);

			// 利用获取到的accessToken 去获取当前用的openid
			OpenID openIDObj = new OpenID(accessToken);
			openID = openIDObj.getUserOpenID();
			LOGGER.info("利用获取到的accessToken:" + accessToken
					+ ", 去获取到当前用户openid:" + openID + ".");

			String icon = null, nickName = null;
			// 去获取用户在Qzone的昵称等信息
			UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
			UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();

			if (userInfoBean.getRet() == 0) {
				nickName = userInfoBean.getNickname();
				// userInfoBean.getGender();

				icon = userInfoBean.getAvatar().getAvatarURL30();
				// userInfoBean.getAvatar().getAvatarURL50();
				// userInfoBean.getAvatar().getAvatarURL100();
			} else {
				LOGGER.error("很抱歉，我们没能正确获取到您的信息，原因是：" + userInfoBean.getMsg());
			}

			BAuth newAuth = new BAuth();
			newAuth.setAccessToken(accessToken);
			newAuth.setExpiresIn(tokenExpireIn);
			newAuth.setIcon(icon);
			newAuth.setNickName(nickName);
			newAuth.setOpenId(openID);
			newAuth.setType(AuthType.QQ.getCode());
			return this.callBack(model, newAuth);
			
			// 通过openid判断首次登录与否
		/*	BAuth bauth = authService.findOne(openID, AuthType.QQ.getCode());
			if (bauth != null && bauth.getUser() != null) {
				// 判定有登录记录
				//刷新accessToken
				bauth.setAccessToken(accessToken);
				bauth.setExpiresIn(tokenExpireIn);
				bauth.setIcon(icon);
				bauth.setNickName(nickName);
				bauth.setRefreshAccessTime(new Date());
				authService.update(bauth);
				this.setUser2Session(bauth);
				return "redirect:/index";
			} else if(bauth==null) {
				// 判定首次登录，记录
				bauth = new BAuth();
				bauth.setAccessToken(accessToken);
				bauth.setCreatedTime(new Date());
				bauth.setExpiresIn(tokenExpireIn);
				bauth.setIcon(icon);
				bauth.setNickName(nickName);
				bauth.setOpenId(openID);
				bauth.setRefreshAccessTime(new Date());
				bauth.setType(AuthType.QQ.getCode());
				authService.save(bauth);
				//用户绑定,跳转页面
				model.addAttribute("openId", openID);
				model.addAttribute("loginFrom", AuthType.QQ.getCode());
				return "site/bindAuth.jsp";
			}else{
				// 登录过但因某种原因为绑定账户
				bauth.setAccessToken(accessToken);
				bauth.setExpiresIn(tokenExpireIn);
				bauth.setIcon(icon);
				bauth.setNickName(nickName);
				bauth.setRefreshAccessTime(new Date());
				authService.update(bauth);
				//用户绑定,跳转页面
				model.addAttribute("openId", openID);
				model.addAttribute("loginFrom", AuthType.QQ.getCode());
				return "site/bindAuth.jsp";
			}*/

		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		return "redirect:/index";
	}

	@RequestMapping("/qqss")
	public void talk(HttpServletRequest request, HttpServletResponse response,
			Model model) throws IOException {
		response.setContentType("text/html;charset=utf-8");

		request.setCharacterEncoding("utf-8");
		String con = request.getParameter("con");
		HttpSession session = request.getSession();
		String accessToken = (String) session.getAttribute("demo_access_token");
		String openID = (String) session.getAttribute("demo_openid");
		System.out.println(accessToken);
		System.out.println(openID);
		// 请开发者自行校验获取的con值是否有效
		if (con != "") {
			Topic topic = new Topic(accessToken, openID);
			try {
				GeneralResultBean grb = topic.addTopic(con);
				if (grb.getRet() == 0) {
					response.getWriter()
							.println(
									"<a href=\"http://www.qzone.com\" target=\"_blank\">您的说说已发表成功，请登录Qzone查看</a>");
				} else {
					response.getWriter().println(
							"很遗憾的通知您，发表说说失败！原因： " + grb.getMsg());
				}
			} catch (QQConnectException e) {
				System.out.println("抛异常了？");
			}
		} else {
			System.out.println("获取到的值为空？");
		}
	}
}
