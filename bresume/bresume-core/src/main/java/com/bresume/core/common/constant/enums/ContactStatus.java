/** 
 * Project Name:bresume-core 
 * File Name:ContactStatus.java 
 * Package Name:com.bresume.core.common.constant.enums 
 * Date:2014-11-26下午10:19:40 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
*/  
  
package com.bresume.core.common.constant.enums;  
/** 
 * ClassName:ContactStatus
 * Description:   TODO ADD REASON.
 * Date:     2014-11-26 下午10:19:40
 * @author   Administrator 
     
 */
public enum ContactStatus{
	INTITAL(0), REPLIED(1), DELETED(4);
	private int code;

	ContactStatus(int code) {
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
  