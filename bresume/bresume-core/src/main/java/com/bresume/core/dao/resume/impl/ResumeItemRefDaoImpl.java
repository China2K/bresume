package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.IResumeItemRefDao;
import com.bresume.core.model.entity.resume.ResumeItemRef;
@Repository
public class ResumeItemRefDaoImpl extends SimpleHibernateDao<ResumeItemRef, String> implements IResumeItemRefDao {

	
}
