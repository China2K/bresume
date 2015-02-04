package com.bresume.core.common.constant.enums;

public enum EduDegree {
	/**
	 * <s:option value="1">专科</s:option> <s:option value="2">本科</s:option>
	 * <s:option value="3">硕士</s:option> <s:option value="4">博士</s:option>
	 * <s:option value="9">技能培训</s:option>
	 */
	SPECIALTY(1,"专科"), UNDERGRADUATE(2,"本科"), POSTGRADUATE(3,"硕士"), DOCTOR(4,"博士"), TRAIN(0,"技能培训");
	private int code;
	private String name;

	EduDegree(int code,String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static EduDegree fromCode(int code) {
		for (EduDegree rit : EduDegree.values()) {
			if (rit.getCode() == code)
				return rit;
		}
		return null;
	}

	public static String getName(int code) {
		for (EduDegree rit : EduDegree.values()) {
			if (rit.getCode() == code)
				return rit.getName();
		}
		return null;
	}
}
