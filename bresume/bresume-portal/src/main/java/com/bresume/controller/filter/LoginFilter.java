package com.bresume.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IConstants;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.AuthType;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.security.Encrypt;
import com.bresume.core.model.dto.LoginUser;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.user.IUserService;

public class LoginFilter extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1688042899772490402L;

	private FilterConfig filterConfig;
	
	private WebApplicationContext wac;


	private void deleteFromCookie(String cookieKey, HttpServletResponse res) {
		Cookie token = new Cookie(cookieKey, null);
		token.setDomain(IPortalConstants.PORTAL_DOMAIN);
		token.setPath("/");
		token.setMaxAge(0);
		res.addCookie(token);
	}

	/**
	 * 根据cookie名称，获取cookie的值
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	private String getFromCookie(String cookieName, HttpServletRequest request) {
		String cookieStr = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					cookieStr = cookie.getValue();
				}
			}
		}
		return cookieStr;
	}

	public String getUploadAuthInfo(String userId,String userPSW){
		String info = userId/*+"_"+userPSW*/;
		return Encrypt.encryptSSO(info, IConstants.HELLO_WORD);
	}
	protected LoginUser getU(User user) {
		LoginUser loginUser = new LoginUser();
		loginUser
				.setIcon(user.getIcon() == null ? IPortalConstants.defaultIconUrl
						: user.getIcon());
		loginUser.setId(user.getId());
		loginUser.setLoginType(AuthType.bresume.getCode());
		loginUser.setNickName(user.getNickName());
		return loginUser;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		if (CommonUtils.isEmpty(session
				.getAttribute(IPortalConstants.SESSION_KEY_LOGIN_USER))) {
			String uname = getFromCookie(
					IPortalConstants.COOKIE_KEY_LOGIN_USER,
					(HttpServletRequest) request);
			String upsw = getFromCookie(IPortalConstants.COOKIE_KEY_LOGIN_PSW,
					(HttpServletRequest) request);
			if (CommonUtils.isNotEmpty(upsw) && CommonUtils.isNotEmpty(uname)) {
				// 登陆校验
				IUserService userService = (IUserService) wac.getBean("userServiceImpl");
				try {
					User _loginUser = userService.loginCheck(uname,
							Encrypt.decryptSSO(upsw, IConstants.HELLO_WORD));
					
					LoginUser loginuser = this.getU(_loginUser);
					session.setAttribute(
							IPortalConstants.SESSION_KEY_LOGIN_USER, loginuser);
					session.setAttribute(
							IPortalConstants.SESSION_USER_UPLOAD_INFO,
							getUploadAuthInfo(_loginUser.getId(), _loginUser.getPassword()));
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}

		// 把session放到上下文中
		SessionContextHolder.setSession(session);

		String path = req.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		String requestPath = req.getServletPath();

		boolean flag = false;
		// 不用登陆的URL 开头
		String excludeStr = filterConfig.getInitParameter("excludePrefix");
		if (excludeStr != null && !"".equals(excludeStr)) {
			String[] excludeUrlBeginnings = excludeStr.split(",");
			for (String str : excludeUrlBeginnings) {
				if (requestPath.startsWith(str)) {
					flag = true;
					break;
				}
			}
		}

		if (!flag) {
			flag = ((requestPath.indexOf("login") != -1)
					|| (requestPath.indexOf("logout") != -1)
					|| requestPath.indexOf("login.html") != -1
					|| (requestPath.indexOf("register") != -1) || (requestPath
					.indexOf("makeRandCode") != -1));
		}
		if (!flag) {
			// 如果session不为空，则可以浏览其他页面
			if (session.getAttribute(IPortalConstants.SESSION_KEY_LOGIN_USER) != null) {
				filterChain.doFilter(request, response);
				return;
			} else {
				// 跳转到登陆页
				res.sendRedirect(basePath + "login");
				return;
			}
		} else {
			filterChain.doFilter(request, response);
			return;
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.wac = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
	}
}
