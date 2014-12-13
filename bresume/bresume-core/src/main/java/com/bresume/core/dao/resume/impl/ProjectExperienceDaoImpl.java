package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.IProjectExperienceDao;
import com.bresume.core.model.entity.resume.item.ProjectExperience;
@Repository
public class ProjectExperienceDaoImpl extends SimpleHibernateDao<ProjectExperience, String> implements IProjectExperienceDao {

	
}
