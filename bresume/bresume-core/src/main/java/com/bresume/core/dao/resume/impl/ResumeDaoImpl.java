package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.IResumeDao;
import com.bresume.core.model.entity.resume.Resume;
@Repository
public class ResumeDaoImpl extends SimpleHibernateDao<Resume, String> implements IResumeDao {

	
}
