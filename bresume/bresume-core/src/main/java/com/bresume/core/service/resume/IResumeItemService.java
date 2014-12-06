package com.bresume.core.service.resume;

import java.util.List;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.common.constant.enums.ResumeItemType;
import com.bresume.core.model.entity.resume.ResumeItem;

public interface IResumeItemService extends IGenericService<ResumeItem,String>{
	
	public List findResumeItem(ResumeItemType rit,String resumeId); 
}
