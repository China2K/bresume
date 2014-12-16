/** 
 * Project Name:bresume-core 
 * File Name:WorkExperienceServiceImpl.java 
 * Package Name:com.bresume.core.service.resume.impl 
 * Date:2014-12-13下午10:17:26 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
 */

package com.bresume.core.service.resume.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.resume.IWorkExperienceDao;
import com.bresume.core.model.entity.resume.item.WorkExperience;
import com.bresume.core.service.resume.IWorkExperienceService;

/**
 * ClassName:WorkExperienceServiceImpl Description: TODO ADD REASON. Date:
 * 2014-12-13 下午10:17:26
 * 
 * @author Administrator
 */
@Service
@Transactional
public class WorkExperienceServiceImpl extends
		GenericService<WorkExperience, String> implements
		IWorkExperienceService {

	@Resource
	private IWorkExperienceDao workExperienceDao;

	@Override
	public IGenericDao<WorkExperience, String> getDao() {
		return workExperienceDao;
	}

}
