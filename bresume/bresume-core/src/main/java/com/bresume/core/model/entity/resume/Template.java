package com.bresume.core.model.entity.resume;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;

@Entity
@Table(name = "br_template")
public class Template extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1832428897252111730L;
	private String name;
	private String sn;
	private String siteUrl;
	private String desc;
	private Integer type;
	private Boolean recommended;
	private Integer usedCount;
	private Integer status;
	private String source;
	private Integer order;
	private String coverUrl;
	
	private Date createdTime;
	private Date updatedTime;
	private String createdBy;
	private String updatedBy;

	@Column(name = "`NAME`")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "`SN`")
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "`SITE_URL`")
	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	@Column(name = "`DESC`")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "`TYPE`")
	public Integer getType() {
		if(type==null)
			return 0;
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "`RECOMMENDED`")
	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

	@Column(name = "`USED_COUNT`")
	public Integer getUsedCount() {
		if(usedCount==null)
			return 0;
		return usedCount;
	}

	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}


	@Column(name = "`STATUS`")
	public Integer getStatus() {
		if(status==null)
			return 0;
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "`SOURCE`")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "`ORDER`")
	public Integer getOrder() {
		if(order==null)
			return 0;
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name = "`COVER_URL`")
	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_TIME")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "UPDATED_TIME")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}
