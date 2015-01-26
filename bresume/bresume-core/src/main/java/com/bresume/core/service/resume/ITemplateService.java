package com.bresume.core.service.resume;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.TemplateDto;
import com.bresume.core.model.entity.resume.Template;

public interface ITemplateService extends IGenericService<Template,String>{
	List<Template> findHostTemplates(Integer status);
	
	Page<TemplateDto> find(Pageable pageable, SearchBean... searchBeans) ;
}
