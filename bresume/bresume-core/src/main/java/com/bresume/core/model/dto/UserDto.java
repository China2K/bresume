package com.bresume.core.model.dto;

import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.entity.user.User;

public class UserDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String userName;
	private String password;
	/** 0-个人用户；1-企业用户；2-企业成员 */
	private String type;
	private String status;
	private String level;
	/** 0-门户；1-管理平台 */
	private String registerType;

	private String cellPhone;
	private String email;
	private String isPhoneVerified;
	private String isEmailVerified;

	private String createdBy;
	private String createdTime;
	private String updatedBy;
	private String updatedTime;

	private int pwdErrorTimes;
	private String lastPwdErrorTime;

	private String nickName;
	private String icon;
	
	public static UserDto convert(User user){
		
		
		UserDto dto= new UserDto();
		if(user!=null&&user.getId()!=null){
			CommonUtils.copyPropBetweenBeans(user, dto);
			dto.setCreatedTime(DateUtils.date2String(user.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
		}
		return  dto;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsPhoneVerified() {
		return isPhoneVerified;
	}

	public void setIsPhoneVerified(String isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
	}

	public String getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(String isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getPwdErrorTimes() {
		return pwdErrorTimes;
	}

	public void setPwdErrorTimes(int pwdErrorTimes) {
		this.pwdErrorTimes = pwdErrorTimes;
	}

	public String getLastPwdErrorTime() {
		return lastPwdErrorTime;
	}

	public void setLastPwdErrorTime(String lastPwdErrorTime) {
		this.lastPwdErrorTime = lastPwdErrorTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}


}
