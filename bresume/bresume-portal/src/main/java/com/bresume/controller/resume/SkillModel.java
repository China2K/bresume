package com.bresume.controller.resume;

import java.io.Serializable;
import java.util.List;

import com.bresume.core.model.entity.resume.item.Skill;

public class SkillModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Skill> items;

	public List<Skill> getItems() {
		return items;
	}

	public void setItems(List<Skill> items) {
		this.items = items;
	}
	

}
