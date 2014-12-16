package com.bresume.core.service.resume.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.common.constant.enums.ResumeItemType;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.dao.resume.IResumeItemDao;
import com.bresume.core.model.entity.resume.ResumeItem;
import com.bresume.core.service.resume.IResumeItemService;

@Service
@Transactional
public class ResumeItemServiceImpl extends GenericService<ResumeItem, String>
		implements IResumeItemService {

	@Resource
	private IResumeItemDao resumeItemDao;

	@Override
	public IGenericDao<ResumeItem, String> getDao() {
		return resumeItemDao;
	}

	@Override
	public  List<?> findResumeItem(ResumeItemType rit, String resumeId) {
		return resumeItemDao
				.findResumeItem(rit.getClazz(),
						new SearchBean[] { new SearchBean("resume.id",
								resumeId, "=") }, null);
	}

}
