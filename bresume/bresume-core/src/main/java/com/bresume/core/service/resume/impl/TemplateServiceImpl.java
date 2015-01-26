package com.bresume.core.service.resume.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.dao.resume.ITemplateDao;
import com.bresume.core.model.dto.TemplateDto;
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
	public List<Template> findHostTemplates(Integer status) {
		if(status!=null){
			return templateDao.findAll(new Sort(Direction.ASC, "order"), new SearchBean("recommended","1", "="),new SearchBean("status",status.toString(), "="));
		}
		return templateDao.findAll(new Sort(Direction.ASC, "order"), new SearchBean("recommended","1", "="));
	}
	@Override
	public Page<TemplateDto> find(Pageable pageable, SearchBean... searchBeans) {
		Page<Template> list = templateDao.findAll(pageable, searchBeans);
		List<TemplateDto> content = new ArrayList<TemplateDto>();
		for (Template Template : list.getContent()) {
			content.add(TemplateDto.convert(Template));
		}
		return new PageImpl<TemplateDto>(content, pageable, list.getTotalElements());
	}

}
