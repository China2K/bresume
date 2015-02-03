package com.bresume.core.model.dto;

import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.entity.resume.ResumeItem;

public class ResumeItemDto extends BaseDto<ResumeItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3699065016004382293L;

	private String name;
	private String sn;
	private String desc;
	private int order;
	private boolean isDefault;
	private boolean required;
	private String createdTime;
	
	@Override
	public ResumeItemDto convert(ResumeItem item) {

		if (item != null && item.getId() != null) {
			CommonUtils.copyPropBetweenBeans(item, this);
			this.setCreatedTime(DateUtils.date2String(item.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
		}
		return this;
	}

	public static ResumeItemDto convert2Dto(ResumeItem item) {

		ResumeItemDto dto = new ResumeItemDto();
		if (item != null && item.getId() != null) {
			CommonUtils.copyPropBetweenBeans(item, dto);
			dto.setCreatedTime(DateUtils.date2String(item.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
		}
		return dto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean getRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

}
