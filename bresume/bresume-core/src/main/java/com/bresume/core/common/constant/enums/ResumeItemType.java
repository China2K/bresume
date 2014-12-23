/** 
 * Project Name:bresume-core 
 * File Name:CommonStatus.java 
 * Package Name:com.bresume.core.common.constant.enums 
 * Date:2014-11-26下午10:20:15 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
 */

package com.bresume.core.common.constant.enums;

import com.bresume.core.model.entity.resume.item.EduExperience;
import com.bresume.core.model.entity.resume.item.JobIntension;
import com.bresume.core.model.entity.resume.item.PersionalInfo;
import com.bresume.core.model.entity.resume.item.ProjectExperience;
import com.bresume.core.model.entity.resume.item.Skill;
import com.bresume.core.model.entity.resume.item.WorkExperience;

/**
 * ClassName:CommonStatus Description: TODO ADD REASON. Date: 2014-11-26
 * 下午10:20:15
 * 
 * @author 2k
 */
public enum ResumeItemType {
	EDU_EXPERIENCE("eduExperience", "ITEM-0001", EduExperience.class,
			"eduExperience.jsp", 1), JOB_INTENSION("jobIntension", "ITEM-0002",
			JobIntension.class, "jobIntension.jsp", 0), PERSIONAL_INFO(
			"persionalInfo", "ITEM-0003", PersionalInfo.class,
			"persionalInfo.jsp", 0), PROJECT_EXPERIENCE("projectExperience",
			"ITEM-0004", ProjectExperience.class, "projectExperience.jsp", 1), WORK_EXPERIENCE(
			"workExperience", "ITEM-0005", WorkExperience.class,
			"workExperience.jsp", 1), SKILL("skill", "ITEM-0006", Skill.class,
			"skill.jsp", 1);
	private String name;
	private String sn;
	private Class<?> clazz;
	private String page;

	/** 0:单个的;1:多个的 */
	private int type;

	ResumeItemType(String name, String sn, Class<?> clazz, String page, int type) {
		this.name = name;
		this.sn = sn;
		this.clazz = clazz;
		this.page = page;

	}

	public static ResumeItemType fromSn(String sn) {
		for (ResumeItemType rit : ResumeItemType.values()) {
			if (rit.getSn().equalsIgnoreCase(sn))
				return rit;
		}
		return null;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
