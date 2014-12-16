/** 
 * Project Name:bresume-core 
 * File Name:JobIntenServiceImpl.java 
 * Package Name:com.bresume.core.service.resume.impl 
 * Date:2014-12-13下午9:50:15 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
 */

package com.bresume.core.service.resume.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.resume.IJobIntensionDao;
import com.bresume.core.model.entity.resume.item.JobIntension;
import com.bresume.core.service.resume.IJobIntenService;

/**
 * ClassName:JobIntenServiceImpl Description: TODO ADD REASON. Date: 2014-12-13
 * 下午9:50:15
 * 
 * @author Administrator
 */
@Service
@Transactional
public class JobIntenServiceImpl extends GenericService<JobIntension, String>
		implements IJobIntenService {

	@Resource
	private IJobIntensionDao jbIntensionDao;

	@Override
	public IGenericDao<JobIntension, String> getDao() {
		return jbIntensionDao;
	}

}
