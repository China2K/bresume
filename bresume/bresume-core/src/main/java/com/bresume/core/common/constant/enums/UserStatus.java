package com.bresume.core.common.constant.enums;

public enum UserStatus {
	INTITAL(0), ACTIVE(1), INACTIVE(2), DELETED(4);
	private int code;

	UserStatus(int code) {
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
