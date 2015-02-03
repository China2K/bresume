package com.bresume.core.model.dto.item;

import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.entity.resume.item.JobIntension;

public class JobIntensionDto extends BaseDto<JobIntension> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	private String jobType;
	private String address;
	private String trade;
	private String profession;
	private String expertSalary;
	private String readyTime;
	private String selfEvaluation;
	private String createdTime;
	private String updatedTime;

	@Override
	public JobIntensionDto convert(JobIntension ji) {
		if (ji != null) {
			CommonUtils.copyPropBetweenBeans(ji, this);

			this.setCreatedTime(DateUtils.date2String(ji.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));

			this.setUpdatedTime(DateUtils.date2String(ji.getUpdatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
		}

		return this;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getExpertSalary() {
		return expertSalary;
	}

	public void setExpertSalary(String expertSalary) {
		this.expertSalary = expertSalary;
	}

	public String getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(String readyTime) {
		this.readyTime = readyTime;
	}

	public String getSelfEvaluation() {
		return selfEvaluation;
	}

	public void setSelfEvaluation(String selfEvaluation) {
		this.selfEvaluation = selfEvaluation;
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
