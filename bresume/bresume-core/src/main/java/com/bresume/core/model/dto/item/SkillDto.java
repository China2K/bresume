package com.bresume.core.model.dto.item;

import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.entity.resume.item.Skill;

public class SkillDto extends BaseDto<Skill> {

	private static final long serialVersionUID = 3029851435697979789L;

	private String name;
	private String level;
	private String masterTime;
	private String TimeUnitCode;
	private String order;
	private String desc;

	private String status;

	private String createdTime;
	private String updatedTime;

	@Override
	public SkillDto convert(Skill experience) {
		if (experience != null) {
			CommonUtils.copyPropBetweenBeans(experience, this);

			this.setCreatedTime(DateUtils.date2String(
					experience.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));

			this.setUpdatedTime(DateUtils.date2String(
					experience.getUpdatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
		}

		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMasterTime() {
		return masterTime;
	}

	public void setMasterTime(String masterTime) {
		this.masterTime = masterTime;
	}

	public String getTimeUnitCode() {
		return TimeUnitCode;
	}

	public void setTimeUnitCode(String timeUnitCode) {
		TimeUnitCode = timeUnitCode;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
