/** 
 * Project Name:bresume-core 
 * File Name:SkillServiceImpl.java 
 * Package Name:com.bresume.core.service.resume.impl 
 * Date:2014-12-13下午10:15:49 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
 */

package com.bresume.core.service.resume.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.resume.ISkillDao;
import com.bresume.core.model.entity.resume.item.Skill;
import com.bresume.core.service.resume.ISkillService;

/**
 * ClassName:SkillServiceImpl Description: TODO ADD REASON. Date: 2014-12-13
 * 下午10:15:49
 * 
 * @author 2k
 */
@Service
@Transactional
public class SkillServiceImpl extends GenericService<Skill, String> implements
		ISkillService {

	@Resource
	private ISkillDao skillDao;

	@Override
	public IGenericDao<Skill, String> getDao() {
		return skillDao;
	}

}
