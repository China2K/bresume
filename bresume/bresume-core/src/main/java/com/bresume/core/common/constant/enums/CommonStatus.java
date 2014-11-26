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
 * ClassName:CommonStatus
 * Description:   TODO ADD REASON.
 * Date:     2014-11-26 下午10:20:15
 * @author   Administrator 
     
 */
public enum CommonStatus {
	INTITAL(0), ACTIVE(1), INACTIVE(2), DELETED(4);
	private int code;

	CommonStatus(int code) {
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
  