package com.bresume.core.service.resume.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.dao.resume.ITemplateDao;
import com.bresume.core.model.entity.resume.Template;
import com.bresume.core.service.resume.ITemplateService;

@Service
@Transactional
public class TemplateServiceImpl extends GenericService<Template, String> implements ITemplateService {
	@Resource
	private ITemplateDao templateDao;
	@Override
	public IGenericDao<Template, String> getDao() {
		return templateDao;
	}
	@Override
	public List<Template> findHostTemplates() {
		return templateDao.findAll(new Sort(Direction.ASC, "order"), new SearchBean("recommended","1", "="));
	}

}
