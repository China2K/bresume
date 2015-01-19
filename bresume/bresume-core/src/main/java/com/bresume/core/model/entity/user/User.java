package com.bresume.core.model.entity.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;

@Entity
@Table(name = "br_user")
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8146779019717630117L;

	private String userName;
	private String password;
	/** 0-个人用户；1-企业用户；2-企业成员 */
	private Integer type;
	private Integer status;
	private Integer level;
	/** 0-门户；1-管理平台 */
	private Integer registerType;

	private String cellPhone;
	private String email;
	private Boolean isPhoneVerified;
	private Boolean isEmailVerified;

	private String createdBy;
	private Date createdTime;
	private String updatedBy;
	private Date updatedTime;

	private int pwdErrorTimes;
	private Date lastPwdErrorTime;
	
	private String nickName;
	private String icon;
	
	private List<BAuth> auths = new ArrayList<BAuth>();
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user",fetch=FetchType.LAZY)
	public List<BAuth> getAuths() {
		return auths;
	}

	public void setAuths(List<BAuth> auths) {
		this.auths = auths;
	}

	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "`TYPE`")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "`STATUS`")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "`LEVEL`")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "`REGISTER_TYPE`")
	public Integer getRegisterType() {
		return registerType;
	}

	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}

	@Column(name = "CELL_PHONE")
	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IS_PHONE_VERIFIED")
	public Boolean getIsPhoneVerified() {
		return isPhoneVerified;
	}

	public void setIsPhoneVerified(Boolean isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
	}

	@Column(name = "IS_EMAIL_VERIFIED")
	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
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

	@Column(name = "PWD_ERROR_TIMES")
	public int getPwdErrorTimes() {
		return pwdErrorTimes;
	}

	public void setPwdErrorTimes(int pwdErrorTimes) {
		this.pwdErrorTimes = pwdErrorTimes;
	}

	@Column(name = "LAST_PWD_ERROR_TIME")
	public Date getLastPwdErrorTime() {
		return lastPwdErrorTime;
	}

	public void setLastPwdErrorTime(Date lastPwdErrorTime) {
		this.lastPwdErrorTime = lastPwdErrorTime;
	}

	/**
	 * @return the nickName
	 */
	@Column(name = "NICK_NAME")
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "ICON")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	

}
