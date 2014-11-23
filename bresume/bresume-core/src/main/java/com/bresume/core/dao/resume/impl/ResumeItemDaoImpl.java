package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.IResumeItemDao;
import com.bresume.core.model.entity.resume.ResumeItem;
@Repository
public class ResumeItemDaoImpl extends SimpleHibernateDao<ResumeItem, String> implements IResumeItemDao {

	
}
