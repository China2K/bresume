package com.bresume.core.service.resume.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.dao.resume.IResumeItemRefDao;
import com.bresume.core.model.entity.resume.ResumeItemRef;
import com.bresume.core.service.resume.IResumeItemRefService;

@Service
@Transactional
public class ResumeItemRefServiceImpl extends GenericService<ResumeItemRef, String>
		implements IResumeItemRefService {

	@Resource
	private IResumeItemRefDao resumeItemRefDao;

	@Override
	public IGenericDao<ResumeItemRef, String> getDao() {
		return resumeItemRefDao;
	}

	@Override
	public ResumeItemRef findByResumeAndSn(String resumeId, String sn) {
		List<ResumeItemRef> list = resumeItemRefDao.findAll(new SearchBean("resume.id", resumeId, "="),new SearchBean("itemSn", sn, "="));
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

}
