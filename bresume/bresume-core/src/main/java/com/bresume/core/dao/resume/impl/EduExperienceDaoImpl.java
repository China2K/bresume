package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.IEduExperienceDao;
import com.bresume.core.model.entity.resume.item.EduExperience;

@Repository
public class EduExperienceDaoImpl extends
		SimpleHibernateDao<EduExperience, String> implements IEduExperienceDao {
}
