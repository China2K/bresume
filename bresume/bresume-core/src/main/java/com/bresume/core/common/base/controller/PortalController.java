package com.bresume.core.common.base.controller;

import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.model.dto.LoginUser;

/**
 * Controller基类，所有的Controller必须要继承此类
 * 
 */
public class PortalController extends BaseController {

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
		LoginUser user= getCurrentLoginUser();
		return user==null?null:user.getId();
	}
	
}
