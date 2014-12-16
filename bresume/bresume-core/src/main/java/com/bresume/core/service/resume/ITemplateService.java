package com.bresume.core.service.resume;

import java.util.List;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.model.entity.resume.Template;

public interface ITemplateService extends IGenericService<Template,String>{
	List<Template> findHostTemplates();
}
