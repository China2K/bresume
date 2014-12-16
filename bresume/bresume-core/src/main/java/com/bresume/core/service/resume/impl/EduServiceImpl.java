package com.bresume.core.service.resume.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.resume.IEduExperienceDao;
import com.bresume.core.model.entity.resume.item.EduExperience;
import com.bresume.core.service.resume.IEduService;

@Service
@Transactional
public class EduServiceImpl extends GenericService<EduExperience, String> implements IEduService {

	@Resource
	private IEduExperienceDao eduDao;
	
	@Override
	public IGenericDao<EduExperience, String> getDao() {
		return eduDao;
	}


}
