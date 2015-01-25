package com.bresume.core.common.base.controller;

import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IAdminConstants;
import com.bresume.core.model.entity.sys.SysUser;

/**
 * Controller基类，所有的Controller必须要继承此类
 * 
 */
public class AdminController extends BaseController {

	@Override
	public SysUser getCurrentLoginUser() {
		Object obj = SessionContextHolder.getSession().getAttribute(
				IAdminConstants.SESSION_KEY_LOGIN_USER);
		if (obj == null)
			return null;
		return SysUser.class.cast(obj);
	}
	@Override
	public String getCurrentUserId() {
		SysUser user= getCurrentLoginUser();
		return user==null?null:user.getId();
	}
	
}
