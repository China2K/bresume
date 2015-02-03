/** 
 * Project Name:bresume-core 
 * File Name:CommonStatus.java 
 * Package Name:com.bresume.core.common.constant.enums 
 * Date:2014-11-26下午10:20:15 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
 */

package com.bresume.core.common.constant.enums;

import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.base.BaseEntity;
import com.bresume.core.model.dto.item.EduExperienceDto;
import com.bresume.core.model.dto.item.JobIntensionDto;
import com.bresume.core.model.dto.item.PersionalInfoDto;
import com.bresume.core.model.dto.item.ProjectExperienceDto;
import com.bresume.core.model.dto.item.SkillDto;
import com.bresume.core.model.dto.item.WorkExperienceDto;
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
	EDU_EXPERIENCE("eduExperience", "ITEM-0001", EduExperience.class,EduExperienceDto.class, "eduExperience.jsp", 1),
	JOB_INTENSION("jobIntension", "ITEM-0002", JobIntension.class,JobIntensionDto.class, "jobIntension.jsp", 0), 
	PERSIONAL_INFO("persionalInfo", "ITEM-0003", PersionalInfo.class,PersionalInfoDto.class, "persionalInfo.jsp", 0), 
	PROJECT_EXPERIENCE("projectExperience", "ITEM-0004", ProjectExperience.class,ProjectExperienceDto.class, "projectExperience.jsp", 1), 
	WORK_EXPERIENCE("workExperience", "ITEM-0005", WorkExperience.class,WorkExperienceDto.class, "workExperience.jsp", 1),
	SKILL("skill","ITEM-0006", Skill.class, SkillDto.class, "skill.jsp", 1);
	private String name;
	private String sn;
	private Class<? extends BaseEntity> clazz;
	private Class<? extends BaseDto> dtoClazz;
	private String page;

	/** 0:单个的;1:多个的 */
	private int type;

	ResumeItemType(String name, String sn, Class<? extends BaseEntity> clazz,
			Class<? extends BaseDto> dtoClazz, String page, int type) {
		this.name = name;
		this.sn = sn;
		this.clazz = clazz;
		this.page = page;
		this.type = type;
		this.dtoClazz = dtoClazz;

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

	public Class<? extends BaseEntity> getClazz() {
		return clazz;
	}

	public void setClazz(Class<? extends BaseEntity> clazz) {
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

	public Class<? extends BaseDto> getDtoClazz() {
		return dtoClazz;
	}

	public void setDtoClazz(Class<? extends BaseDto> dtoClazz) {
		this.dtoClazz = dtoClazz;
	}

}
