package com.bresume.core.model.dto.item;

import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.entity.resume.item.ProjectExperience;

public class ProjectExperienceDto extends BaseDto<ProjectExperience> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String projectName;
	private String projectDesc;
	private String respDesc;
	private String order;
	private String siteUrl;
	private String createdTime;
	private String updatedTime;
	
	
	@Override
	public ProjectExperienceDto convert(ProjectExperience experience) {
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
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
