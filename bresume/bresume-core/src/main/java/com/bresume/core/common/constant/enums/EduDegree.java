package com.bresume.core.common.constant.enums;

public enum EduDegree {
	/**
	 * <s:option value="1">专科</s:option> <s:option value="2">本科</s:option>
	 * <s:option value="3">硕士</s:option> <s:option value="4">博士</s:option>
	 * <s:option value="9">技能培训</s:option>
	 */
	SPECIALTY(1), UNDERGRADUATE(2), POSTGRADUATE(3), DOCTOR(4), TRAIN(0);
	private int code;

	EduDegree(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
