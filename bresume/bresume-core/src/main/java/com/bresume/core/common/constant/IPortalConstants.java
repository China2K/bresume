package com.bresume.core.common.constant;

public interface IPortalConstants extends IConstants
{
	// 0A-未激活；0B-启用；0C-停用；0D-注销
	public static final String	USER_STATUS_UN_ACTIVE		= "0A";
	public static final String	USER_STATUS_ACTIVEED		= "0B";
	public static final String	USER_STATUS_STOP			= "0C";
	public static final String	USER_STATUS_DELETED			= "0D";

	public static final String	SESSION_KEY_LOGIN_USER			= "loginUser";
	public static final String  VSITE_HOME_ADDRESS 				= "vsiteHomeAddress"; 
	public static final String	SESSION_KEY_REGISTER_RAND_CODE	= "register.randcode";
	public static final String	SESSION_KEY_HISTORY_REQPATH		= "history.reqpath";
	
	
	public static final String SESSION_KEY_QQ_LOGIN_USER_ACCESS_TOKEN ="qq_access_token";
	public static final String SESSION_KEY_QQ_LOGIN_USER_TOKEN_EXPIREIN ="qq_token_expirein";
	public static final String SESSION_KEY_QQ_LOGIN_USER_OPENID ="qq_openid";

}
