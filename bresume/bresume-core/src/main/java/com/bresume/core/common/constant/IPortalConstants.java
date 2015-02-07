package com.bresume.core.common.constant;

public interface IPortalConstants extends IConstants {
	// 0A-未激活；0B-启用；0C-停用；0D-注销
	public static final String USER_STATUS_UN_ACTIVE = "0A";
	public static final String USER_STATUS_ACTIVEED = "0B";
	public static final String USER_STATUS_STOP = "0C";
	public static final String USER_STATUS_DELETED = "0D";

	public static final String SESSION_KEY_LOGIN_USER = "loginUser";
	public static final String VSITE_HOME_ADDRESS = "vsiteHomeAddress";
	public static final String SESSION_KEY_REGISTER_RAND_CODE = "register.randcode";
	public static final String SESSION_KEY_HISTORY_REQPATH = "history.reqpath";

	public static final String SESSION_USER_UPLOAD_INFO = "upload_info";

	public static final String SESSION_KEY_QQ_LOGIN_USER_ACCESS_TOKEN = "qq_access_token";
	public static final String SESSION_KEY_QQ_LOGIN_USER_TOKEN_EXPIREIN = "qq_token_expirein";
	public static final String SESSION_KEY_QQ_LOGIN_USER_OPENID = "qq_openid";

	public static final String defaultIconUrl = "http://tp4.sinaimg.cn/2465082087/50/5617595072/1";

	public static final String defaultPSW = "123456";

	public static final String VPORTAL_DOMAIN = "http://www.bresume.com";
	
	public static final String PORTAL_DOMAIN = "www.bresume.com";
	
	

	public static final String COOKIE_KEY_LOGIN_USER = "hello";
	public static final String COOKIE_KEY_LOGIN_PSW = "kiddy";

}
