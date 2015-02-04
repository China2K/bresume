package com.bresume.core.service.resume;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.ResumeDto;
import com.bresume.core.model.entity.resume.Resume;

public interface IResumeService extends IGenericService<Resume,String>{
	List<Resume> findHostResumes( Integer status);
	
	public Page<ResumeDto> find(Pageable pageable, SearchBean... searchBeans) ;
	
	
	boolean isExist(String id,String name);
	
	
	int getScore(Resume resume) ;
}
