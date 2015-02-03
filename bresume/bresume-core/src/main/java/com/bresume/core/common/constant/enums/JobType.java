package com.bresume.core.common.constant.enums;

public enum JobType {
	/**
	 * 1 全职 2 兼职 3 实习
	 */
	fullTime(1), partTime(2), internship(3);
	private int code;

	JobType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
