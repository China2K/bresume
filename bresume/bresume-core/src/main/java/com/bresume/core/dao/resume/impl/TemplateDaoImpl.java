package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.ITemplateDao;
import com.bresume.core.model.entity.resume.Template;
@Repository
public class TemplateDaoImpl extends SimpleHibernateDao<Template, String> implements ITemplateDao {

	
}
