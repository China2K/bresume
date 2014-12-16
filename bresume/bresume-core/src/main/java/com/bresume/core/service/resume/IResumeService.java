package com.bresume.core.service.resume;

import java.util.List;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.model.entity.resume.Resume;

public interface IResumeService extends IGenericService<Resume,String>{
	List<Resume> findHostResumes();
}
