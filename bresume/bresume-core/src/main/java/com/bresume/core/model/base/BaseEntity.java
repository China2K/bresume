package com.bresume.core.model.base;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author 2k
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable
{
	protected String id;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}
	
}
