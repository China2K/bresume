package com.bresume.core.common.constant.enums;

public enum UserType {
	/**0-个人用户；1-企业用户；2-企业成员*/
	PERSIONAL(0), ENTERPRISE(1), ENTERPRISE_MEMBERS(2);
	private int code;

	UserType(int code) {
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
