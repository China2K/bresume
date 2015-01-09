package com.bresume.core.model.dto;

import com.bresume.core.model.base.BaseDto;

public class LoginUser extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -407460787403113510L;

	private String icon;
	private int loginType;
	private String nickName;
	private String openId;
	private String accessToken;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
