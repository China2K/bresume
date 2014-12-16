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
import com.bresume.core.dao.resume.IResumeDao;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.service.resume.IResumeService;

@Service
@Transactional
public class ResumeServiceImpl extends GenericService<Resume, String> implements IResumeService {

	@Resource
	private IResumeDao resumeDao;
	
	@Override
	public IGenericDao<Resume, String> getDao() {
		return resumeDao;
	}

	@Override
	public List<Resume> findHostResumes() {
		return resumeDao.findAll(new Sort(Direction.ASC, "order"), new SearchBean("recommended","1", "="));
	}

}
