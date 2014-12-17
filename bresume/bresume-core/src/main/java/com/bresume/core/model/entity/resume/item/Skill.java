package com.bresume.core.model.entity.resume.item;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;
import com.bresume.core.model.entity.resume.Resume;

@Entity
@Table(name = "br_skill")
public class Skill extends BaseEntity {

	private static final long serialVersionUID = 3029851435697979789L;

	private Resume resume;
	private String name;
	private Integer level;
	private Integer masterTime;
	private String TimeUnitCode;
	private Integer order;
	private String desc;

	private Date createdTime;
	private Date updatedTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESUME_ID")
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "`LEVEL`")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "MASTER_TIME")
	public Integer getMasterTime() {
		return masterTime;
	}

	public void setMasterTime(Integer masterTime) {
		this.masterTime = masterTime;
	}

	@Column(name = "TIME_UNIT_CODE")
	public String getTimeUnitCode() {
		return TimeUnitCode;
	}

	public void setTimeUnitCode(String timeUnitCode) {
		TimeUnitCode = timeUnitCode;
	}

	@Column(name = "`ORDER`")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name = "`DESC`")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "CREATED_TIME")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "UPDATED_TIME")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
