package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.ISkillDao;
import com.bresume.core.model.entity.resume.Skill;
@Repository
public class SkillDaoImpl extends SimpleHibernateDao<Skill, String> implements ISkillDao {

	
}
