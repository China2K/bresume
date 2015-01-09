package com.bresume.controller.login;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.AuthType;
import com.bresume.core.model.dto.LoginUser;
import com.bresume.core.model.entity.user.BAuth;


public abstract class AuthController extends BaseController {

	protected LoginUser getU(BAuth auth){
		LoginUser loginUser= new LoginUser();
		loginUser.setAccessToken(auth.getAccessToken());
		loginUser
				.setIcon(auth.getIcon() == null ? IPortalConstants.defaultIconUrl
						: auth.getIcon());
		loginUser.setId(auth.getUser().getId());
		loginUser.setLoginType(AuthType.QQ.getCode());
		loginUser.setNickName(auth.getNickName() == null ? auth.getUser()
				.getNickName() : auth.getNickName());
		loginUser.setOpenId(auth.getOpenId());
		return loginUser;
	}
	
	protected boolean setUser2Session(BAuth auth){
		LoginUser loginuser = this.getU(auth);
		SessionContextHolder.getSession().setAttribute(IPortalConstants.SESSION_KEY_LOGIN_USER, loginuser);
		return true;
	}
}
