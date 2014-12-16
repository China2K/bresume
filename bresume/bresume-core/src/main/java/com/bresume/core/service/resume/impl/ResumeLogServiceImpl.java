/** 
 * Project Name:bresume-core 
 * File Name:ResumeLogServiceImpl.java 
 * Package Name:com.bresume.core.service.resume.impl 
 * Date:2014-12-13下午10:11:13 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
 */

package com.bresume.core.service.resume.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.resume.IResumeLogDao;
import com.bresume.core.model.entity.resume.ResumeLog;
import com.bresume.core.service.resume.IResumeLogService;

/**
 * ClassName:ResumeLogServiceImpl Description: TODO ADD REASON. Date: 2014-12-13
 * 下午10:11:13
 * 
 * @author Administrator
 */
@Service
@Transactional
public class ResumeLogServiceImpl extends GenericService<ResumeLog, String>
		implements IResumeLogService {

	@Resource
	private IResumeLogDao resumeLogDao;

	@Override
	public IGenericDao<ResumeLog, String> getDao() {
		return resumeLogDao;
	}

}
