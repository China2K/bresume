package com.bresume.core.model.entity.resume;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;
import com.bresume.core.model.entity.user.User;

@Entity
@Table(name = "br_resume_log")
public class ResumeLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3094826720694018262L;

	private Resume resume;
	private User user;
	private Date createdTime;
	/* 0-浏览；1-收藏；2-下载 */
	private Integer eventType;

	private Integer userType;
	private String userIp;

	public Resume getResume() {
		return resume;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESUME_ID")
	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CREATED_TIME")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "EVENT_TYPE")
	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	@Column(name = "USER_TYPE")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Column(name = "USER_IP")
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

}
