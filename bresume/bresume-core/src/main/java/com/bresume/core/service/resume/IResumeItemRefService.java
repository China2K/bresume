package com.bresume.core.service.resume;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.model.entity.resume.ResumeItemRef;

public interface IResumeItemRefService extends IGenericService<ResumeItemRef,String>{
	
	ResumeItemRef findByResumeAndSn(String resumeId,String sn);
	
}
