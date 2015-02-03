package com.bresume.core.model.base;


import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author 2k
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseDto<T extends BaseEntity> implements Serializable
{
	protected String id;
	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}
	
	
	public abstract Object convert(T t);
	
}
