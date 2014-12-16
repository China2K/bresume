/** 
 * Project Name:bresume-core 
 * File Name:ProjectExperienceServiceImpl.java 
 * Package Name:com.bresume.core.service.resume.impl 
 * Date:2014-12-13下午10:08:52 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
 */

package com.bresume.core.service.resume.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.resume.IProjectExperienceDao;
import com.bresume.core.model.entity.resume.item.ProjectExperience;
import com.bresume.core.service.resume.IProjectExperienceService;

/**
 * ClassName:ProjectExperienceServiceImpl Description: TODO ADD REASON. Date:
 * 2014-12-13 下午10:08:52
 * 
 * @author 2k
 */
@Service
@Transactional
public class ProjectExperienceServiceImpl extends
		GenericService<ProjectExperience, String> implements
		IProjectExperienceService {
	@Resource
	private IProjectExperienceDao projectExperienceDao;

	@Override
	public IGenericDao<ProjectExperience, String> getDao() {
		return projectExperienceDao;
	}

}
