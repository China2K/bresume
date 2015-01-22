package com.bresume.core.model.dto;

import com.bresume.core.common.upload.FileUploadHandler;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseEntity;
import com.bresume.core.model.entity.resume.Template;

public class TemplateDto extends BaseEntity {

	/**
	 * 
	 */
	private String name;
	private String sn;
	private String siteUrl;
	private String desc;
	private int type;
	private boolean recommended;
	private int usedCount;
	private int status;
	private String source;
	private int order;
	private String coverUrl;

	private String createdTime;
	private String updatedTime;
	private String createdBy;
	private String updatedBy;

	public static TemplateDto convert(Template template) {

		TemplateDto dto = new TemplateDto();
		CommonUtils.copyPropBetweenBeans(template, dto);
		if (template != null&&template.getId()!=null) {
			dto.setCreatedTime(DateUtils.date2String(template.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));

			dto.setUpdatedTime(DateUtils.date2String(template.getUpdatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
		}
		
//		String coverUrl= template.getCoverUrl();
//		if(CommonUtils.isNotEmpty(coverUrl)&&!coverUrl.startsWith("http")){
//			coverUrl =  FileUploadHandler.UPLOAD_CONFIG.getStaticUrlPrefix() + coverUrl;
//		}
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

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}

	public int getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(int usedCount) {
		this.usedCount = usedCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
