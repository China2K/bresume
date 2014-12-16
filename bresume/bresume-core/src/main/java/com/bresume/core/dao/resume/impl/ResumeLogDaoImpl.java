package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.IResumeLogDao;
import com.bresume.core.model.entity.resume.ResumeLog;
@Repository
public class ResumeLogDaoImpl extends SimpleHibernateDao<ResumeLog, String> implements IResumeLogDao {

	
}
