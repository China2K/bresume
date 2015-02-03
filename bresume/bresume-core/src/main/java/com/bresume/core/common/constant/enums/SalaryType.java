/** 
 * Project Name:bresume-core 
 * File Name:CommonStatus.java 
 * Package Name:com.bresume.core.common.constant.enums 
 * Date:2014-11-26下午10:20:15 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
 */

package com.bresume.core.common.constant.enums;


/**
 * <s:option value="0">面议</s:option> <s:option value="1">2000以下</s:option>
 * <s:option value="2">2000-3000</s:option> <s:option
 * value="3">3000-4500</s:option> <s:option value="4">4500-6000</s:option>
 * <s:option value="5">6000-8000</s:option> <s:option
 * value="6">8k-10k</s:option> <s:option value="7">10k-15k</s:option> <s:option
 * value="8">15k-20k</s:option> <s:option value="9">20k-30k</s:option> <s:option
 * value="10">30k以上</s:option>
 * 
 * @author 2k
 */
public enum SalaryType {

	S_1(1, "2000以下"), S_2(2, "2000-3000"), S_3(3, "3000-4500"), S_4(4,
			"4500-6000"), S_5(5, "6000-8000"), S_6(6, "8k-10k"), S_7(7,
			"10k-15k"), S_8(8, "15k-20k"), S_9(9, "20k-30k"), S_10(10, "10k以上"), ;
	SalaryType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	private int code;
	private String name;

	public static SalaryType fromCode(int code) {
		for (SalaryType rit : SalaryType.values()) {
			if (rit.getCode() == code)
				return rit;
		}
		return null;
	}

	public static String getName(int code) {
		for (SalaryType rit : SalaryType.values()) {
			if (rit.getCode() == code)
				return rit.getName();
		}
		return null;
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

}
