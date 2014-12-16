package com.bresume.core.model.entity.user;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;

@Entity
@Table(name = "br_user_verified")
public class UserVerified extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3858321447968030594L;

	private User user;
	private String code;
	private Date createdTime;
	private Date verifiedTime;
	
	

	public UserVerified() {
		super();
	}

	public UserVerified(User user) {
		this.user = user;
		this.code = UUID.randomUUID().toString().replace("-", "");
		this.createdTime = new Date();
	}

	/**
	 * @return the user
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the code
	 */
	@Column(name="`CODE`")
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the createdTime
	 */
	@Column(name="CREATED_TIME")
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime
	 *            the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the verifiedTime
	 */
	@Column(name="VERIFIED_TIME")
	public Date getVerifiedTime() {
		return verifiedTime;
	}

	/**
	 * @param verifiedTime
	 *            the verifiedTime to set
	 */
	public void setVerifiedTime(Date verifiedTime) {
		this.verifiedTime = verifiedTime;
	}

}
