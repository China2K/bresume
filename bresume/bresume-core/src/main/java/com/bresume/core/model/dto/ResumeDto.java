package com.bresume.core.model.dto;

import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.entity.resume.Resume;

public class ResumeDto extends BaseDto<Resume> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String name;
	private UserDto user;
	/** 0-初始；1-激活；2-注销，3-删除 */
	private int status;
	private boolean isPublic;

	private String createdTime;
	private String updatedTime;

	private String templateSn;
	private String coverUrl;
	private boolean recommended;

	private String position;

	private int order;

	private String desc;

	@Override
	public ResumeDto convert(Resume resume) {
		if (resume != null && resume.getId() != null) {
			this.setId(resume.getId());
			this.setName(resume.getName());
			this.setIsPublic(resume.getIsPublic());
			this.setStatus(resume.getStatus());
			this.setCreatedTime(DateUtils.date2String(resume.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));

			this.setUpdatedTime(DateUtils.date2String(resume.getUpdatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
			this.setUser(UserDto.convert2Dto(resume.getUser()));

			this.setTemplateSn(resume.getTemplateSn());
			this.setCoverUrl(resume.getCoverUrl());
			this.setRecommended(resume.getRecommended());
		}
		return this;
	}

	public static ResumeDto convert2Dto(Resume resume) {

		ResumeDto dto = new ResumeDto();
		if (resume != null && resume.getId() != null) {
			dto.setId(resume.getId());
			dto.setName(resume.getName());
			dto.setIsPublic(resume.getIsPublic());
			dto.setStatus(resume.getStatus());
			dto.setCreatedTime(DateUtils.date2String(resume.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));

			dto.setUpdatedTime(DateUtils.date2String(resume.getUpdatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
			dto.setUser(UserDto.convert2Dto(resume.getUser()));

			dto.setTemplateSn(resume.getTemplateSn());
			dto.setCoverUrl(resume.getCoverUrl());
			dto.setRecommended(resume.getRecommended());
		}

		return dto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
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

	public String getTemplateSn() {
		return templateSn;
	}

	public void setTemplateSn(String templateSn) {
		this.templateSn = templateSn;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public boolean isRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
