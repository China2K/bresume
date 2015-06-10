package com.bresume.core.model.base;


import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.bresume.core.common.lucene.model.BaseIndexBean;

/**
 *
 * @author 2k
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseDto<T extends BaseEntity> extends BaseIndexBean implements Serializable 
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
