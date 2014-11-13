package com.bresume.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;

/**
 * 地区省实体
 * @author 2k
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BR_SYS_HAT_PROVINCE")
public class Province extends BaseEntity
{
	private String name;
	
	private String code;

	@Column(name="`NAME`")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name="`CODE`")
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
	
}
