package com.bresume.core.model.entity.resume;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;
import com.bresume.core.model.entity.user.User;

@Entity
@Table(name = "br_resume")
public class Resume extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3029851435697979789L;

	private String name;
	private User user;
	/** 0-初始；1-激活；2-注销，3-删除 */
	private Integer status;
	private Boolean isPublic;

	private Date createdTime;
	private Date updatedTime;

	private String templateSn;
	private String coverUrl;
	private Boolean recommended;

	private Integer order;

	private String desc;

	private Integer score;

	private List<ResumeItemRef> refs;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SCORE")
	public Integer getScore() {
		if(score==null)
			return 0;
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		if (status == null)
			return 0;
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "IS_PUBLIC")
	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
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

	@Column(name = "TEMPLATE_SN")
	public String getTemplateSn() {
		return templateSn;
	}

	public void setTemplateSn(String templateSn) {
		this.templateSn = templateSn;
	}

	@Column(name = "COVER_URL")
	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	@Column(name = "RECOMMENDED")
	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

	@Column(name = "`ORDER`")
	public Integer getOrder() {
		if (order == null)
			return 0;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resume")
	public List<ResumeItemRef> getRefs() {
		return refs;
	}

	public void setRefs(List<ResumeItemRef> refs) {
		this.refs = refs;
	}
}
