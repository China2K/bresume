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
@Table(name = "br_job_intension")
public class JobIntension extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	private Resume resume;
	private Integer jobType;
	private String address;
	private String trade;
	private String profession;
	private Integer expertSalary;
	private Integer readyTime;
	private String selfEvaluation;
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

	@Column(name = "JOB_TYPE")
	public Integer getJobType() {
		return jobType;
	}

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TRADE")
	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	@Column(name = "PROFESSION")
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Column(name = "EXPERT_SALARY")
	public Integer getExpertSalary() {
		return expertSalary;
	}

	public void setExpertSalary(Integer expertSalary) {
		this.expertSalary = expertSalary;
	}

	@Column(name = "READY_TIME")
	public Integer getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(Integer readyTime) {
		this.readyTime = readyTime;
	}

	@Column(name = "SELF_EVALUATION")
	public String getSelfEvaluation() {
		return selfEvaluation;
	}

	public void setSelfEvaluation(String selfEvaluation) {
		this.selfEvaluation = selfEvaluation;
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
