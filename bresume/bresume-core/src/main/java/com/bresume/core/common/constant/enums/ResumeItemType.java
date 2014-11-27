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
public enum ResumeItemType {
	EDU_EXPERIENCE("ITEM-0001"), JON_INTENSION("ITEM-0002"), PERSIONAL_INFO("ITEM-0003"), PROJECT_EXPERIENCE("ITEM-0004"),
	WORK_EXPERIENCE("ITEM-0005"),SKILL("ITEM-0006");
	private String sn;

	ResumeItemType(String sn) {
		this.sn = sn;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
  