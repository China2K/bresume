package com.bresume.core.common.constant.enums;

public enum AuthType {
	/** 0-bresume；1-qq；2-weibo */
	bresume(0, RegisterType.PORTAL_REGISTER), QQ(1, RegisterType.QQ_REGISTER), SINA(
			2, RegisterType.SINA_REGISTER);
	private int code;
	private RegisterType rt;

	AuthType(int code, RegisterType rt) {
		this.code = code;
		this.rt = rt;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	public RegisterType getRt() {
		return rt;
	}

	public void setRt(RegisterType rt) {
		this.rt = rt;
	}

	public static AuthType fromCode(int code) {
		for (AuthType at : AuthType.values()) {
			if (at.getCode() == code)
				return at;
		}
		return AuthType.bresume;
	}

}
