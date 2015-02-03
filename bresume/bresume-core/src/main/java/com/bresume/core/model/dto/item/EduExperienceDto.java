package com.bresume.core.model.dto.item;

import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.entity.resume.item.EduExperience;

public class EduExperienceDto extends BaseDto<EduExperience> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String schoolName;
	private String majorName;
	private String degree;
	private String order;
	private String desc;
	private String createdTime;
	private String updatedTime;

	
	@Override
	public EduExperienceDto convert(EduExperience experience) {
		if (experience != null) {
			CommonUtils.copyPropBetweenBeans(experience, this);
			this.setStartDate(DateUtils.date2String(experience.getStartDate(),
					DateUtils.YYYY_MM_DD_PATTERN));
			this.setEndDate(DateUtils.date2String(experience.getEndDate(),
					DateUtils.YYYY_MM_DD_PATTERN));

			this.setCreatedTime(DateUtils.date2String(
					experience.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));

			this.setUpdatedTime(DateUtils.date2String(
					experience.getUpdatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
		}

		return this;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

}
