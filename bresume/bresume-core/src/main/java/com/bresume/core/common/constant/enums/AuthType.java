package com.bresume.core.common.constant.enums;

public enum AuthType {
	/** 0-bresume；1-qq；2-weibo */
	bresume(0), QQ(1), SINA(2);
	private int code;

	AuthType(int code) {
		this.code = code;
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

}
