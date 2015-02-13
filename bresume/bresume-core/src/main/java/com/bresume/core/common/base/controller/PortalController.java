package com.bresume.core.common.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IConstants;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.AuthType;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.security.Encrypt;
import com.bresume.core.model.dto.LoginUser;
import com.bresume.core.model.entity.user.BAuth;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.user.IUserService;

/**
 * Controller基类，所有的Controller必须要继承此类
 * 
 */
public class PortalController extends BaseController {

	@Resource
	private IUserService userService;

	@Override
	public LoginUser getCurrentLoginUser() {
		Object obj = SessionContextHolder.getSession().getAttribute(
				IPortalConstants.SESSION_KEY_LOGIN_USER);
		if (obj == null)
			return null;
		return LoginUser.class.cast(obj);
	}

	@Override
	public String getCurrentUserId() {
		LoginUser user = getCurrentLoginUser();
		return user == null ? null : user.getId();
	}

	protected LoginUser getU(BAuth auth) {
		LoginUser loginUser = new LoginUser();
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

	protected boolean setUser2Session(BAuth auth) {
		LoginUser loginuser = this.getU(auth);
		SessionContextHolder.getSession().setAttribute(
				IPortalConstants.SESSION_KEY_LOGIN_USER, loginuser);

		User user = auth.getUser();
		SessionContextHolder.getSession().setAttribute(
				IPortalConstants.SESSION_USER_UPLOAD_INFO,
				getUploadAuthInfo(user.getId(), user.getPassword()));
		return true;
	}

	protected boolean setUser2Session(User user) {
		LoginUser loginuser = this.getU(user);
		SessionContextHolder.getSession().setAttribute(
				IPortalConstants.SESSION_KEY_LOGIN_USER, loginuser);
		SessionContextHolder.getSession().setAttribute(
				IPortalConstants.SESSION_USER_UPLOAD_INFO,
				getUploadAuthInfo(user.getId(), user.getPassword()));
		return true;
	}

	protected void setUserFromCookie(HttpServletRequest request) {
		if (CommonUtils.isEmpty(getCurrentUserId())) {
			String uname = getFromCookie(
					IPortalConstants.COOKIE_KEY_LOGIN_USER,
					(HttpServletRequest) request);
			String upsw = getFromCookie(IPortalConstants.COOKIE_KEY_LOGIN_PSW,
					(HttpServletRequest) request);
			if (CommonUtils.isNotEmpty(upsw) && CommonUtils.isNotEmpty(uname)) {
				// 登陆校验
				try {
					User user = userService.loginCheck(uname,
							Encrypt.decryptSSO(upsw, IConstants.HELLO_WORD));
					setUser2Session(user);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void deleteFromCookie(String cookieKey, HttpServletResponse res) {
		Cookie token = new Cookie(cookieKey, null);
		token.setDomain(IPortalConstants.PORTAL_DOMAIN);
		token.setPath("/");
		token.setMaxAge(0);
		res.addCookie(token);
	}
}
