package com.bresume.core.common.constant.enums;

public enum SkillLevel {
	/*
	 * <option value="1">无</option> <option value="2">了解</option> <option
	 * value="3">一般</option> <option value="4">熟练</option> <option
	 * value="5">精通</option>
	 */
	L_1(1, "无"), L_2(2, "了解"), L_3(3, "一般"), L_4(4, "熟练"), L_5(5, "精通");
	private int code;
	private String name;

	SkillLevel(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static int getScore(int code) {

		return 100 *code/ SkillLevel.values().length;
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

	public static SkillLevel fromCode(int code) {
		for (SkillLevel rit : SkillLevel.values()) {
			if (rit.getCode() == code)
				return rit;
		}
		return null;
	}

	public static String getName(int code) {
		for (SkillLevel rit : SkillLevel.values()) {
			if (rit.getCode() == code)
				return rit.getName();
		}
		return null;
	}
}
