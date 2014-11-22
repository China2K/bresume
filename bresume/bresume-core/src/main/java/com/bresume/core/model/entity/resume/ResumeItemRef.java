package com.bresume.core.model.entity.resume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;

@Entity
@Table(name = "br_resume_ref_item")
public class ResumeItemRef extends BaseEntity {

	private static final long serialVersionUID = 1832428897252111730L;
	private Resume resume;
	private ResumeItem resumeItem;
	private Integer order;

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public ResumeItem getResumeItem() {
		return resumeItem;
	}

	public void setResumeItem(ResumeItem resumeItem) {
		this.resumeItem = resumeItem;
	}

	@Column(name = "`ORDER`")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
