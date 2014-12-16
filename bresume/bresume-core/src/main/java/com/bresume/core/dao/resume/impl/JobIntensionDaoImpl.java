package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.IJobIntensionDao;
import com.bresume.core.model.entity.resume.item.JobIntension;
@Repository
public class JobIntensionDaoImpl extends SimpleHibernateDao<JobIntension, String> implements IJobIntensionDao {

	
}
