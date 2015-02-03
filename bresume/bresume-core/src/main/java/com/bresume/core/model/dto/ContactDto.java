package com.bresume.core.model.dto;

import com.bresume.core.common.utils.DateUtils;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.entity.user.Contact;

public class ContactDto extends BaseDto<Contact> {

	private static final long serialVersionUID = 1832428897252111730L;
	private UserDto user;
	private String name;
	private String EMAIL;
	private String cellPhone;
	private int status;
	private String message;
	private String createdTime;
	
	public static ContactDto convert2Dto(Contact contact){
		
		ContactDto dto= new ContactDto();
		if(contact!=null&&contact.getId()!=null){
			dto.setId(contact.getId());
			dto.setUser(UserDto.convert2Dto(contact.getUser()));
			dto.setName(contact.getName());
			dto.setEMAIL(contact.getEMAIL());
			dto.setCellPhone(contact.getCellPhone());
			dto.setStatus(contact.getStatus());
			dto.setMessage(contact.getMessage());
			dto.setCreatedTime(DateUtils.date2String(contact.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
		}
		return  dto;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public ContactDto convert(Contact contact) {
		if(contact!=null&&contact.getId()!=null){
			this.setId(contact.getId());
			this.setUser(UserDto.convert2Dto(contact.getUser()));
			this.setName(contact.getName());
			this.setEMAIL(contact.getEMAIL());
			this.setCellPhone(contact.getCellPhone());
			this.setStatus(contact.getStatus());
			this.setMessage(contact.getMessage());
			this.setCreatedTime(DateUtils.date2String(contact.getCreatedTime(),
					DateUtils.YYYY_MM_DD_HH_MM_SS_PATTERN));
		}
		return  this;
	}

}
