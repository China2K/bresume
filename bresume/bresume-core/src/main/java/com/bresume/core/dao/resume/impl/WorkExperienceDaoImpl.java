package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.IWorkExperienceDao;
import com.bresume.core.model.entity.resume.item.WorkExperience;
@Repository
public class WorkExperienceDaoImpl extends SimpleHibernateDao<WorkExperience, String> implements IWorkExperienceDao {

	
}
