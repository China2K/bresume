package com.bresume.core.model.dto.item;

import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.entity.resume.item.PersionalInfo;

public class PersionalInfoDto extends BaseDto<PersionalInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8773318282373963247L;

	private String name;
	private String sex;
	private String birthday;
	private String profession;
	private String experienceYear;
	private String credentialsType;
	private String credentialsNumber;
	private String cellPhone;
	private String email;
	private String jobStatus;
	private String salary;
	private String salaryUnit;

	private String address;
	private String provinceCode;
	private String cityCode;
	private String areaCode;

	private String siteUrl;

	private String createdBy;
	private String createdTime;
	private String updatedBy;
	private String updatedTime;

	@Override
	public PersionalInfoDto convert(PersionalInfo pi) {
		if (pi != null) {
			CommonUtils.copyPropBetweenBeans(pi, this);

			this.setCreatedTime(DateUtils.date2String(pi.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));

			this.setUpdatedTime(DateUtils.date2String(pi.getUpdatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
			
			
			this.setBirthday(DateUtils.date2String(pi.getBirthday(),
					DateUtils.YYYY_MM_DD_PATTERN));

			
			
			int s = pi.getSex();
			this.setSex(s==1?"男":"女");
			
			int c = pi.getCredentialsType();
			this.setCredentialsType(c==1?"身份证":"学生证");
			
			int jobStatus = pi.getJobStatus();
			this.setJobStatus(getJobStatus(jobStatus));
		}

		return this;
	}
	
	public String getJobStatus(int i){
		switch (i) {
		case 1:
			return "正在找工作";
		case 2:
			return "观望有机会在考虑";
		case 3:
			return "暂时不考虑换工作";

		default:
			return null;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(String experienceYear) {
		this.experienceYear = experienceYear;
	}

	public String getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
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

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getSalaryUnit() {
		return salaryUnit;
	}

	public void setSalaryUnit(String salaryUnit) {
		this.salaryUnit = salaryUnit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
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

}
