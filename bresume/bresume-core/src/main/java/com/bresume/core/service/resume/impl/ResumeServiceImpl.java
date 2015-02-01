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
import com.bresume.core.dao.resume.IResumeDao;
import com.bresume.core.model.dto.ResumeDto;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.service.resume.IResumeService;

@Service
@Transactional
public class ResumeServiceImpl extends GenericService<Resume, String> implements
		IResumeService {

	@Resource
	private IResumeDao resumeDao;

	@Override
	public IGenericDao<Resume, String> getDao() {
		return resumeDao;
	}

	@Override
	public List<Resume> findHostResumes(Integer status) {
		if (status != null) {
			return resumeDao.findAll(new Sort(Direction.ASC, "order"),
					new SearchBean("recommended", "1", "="), new SearchBean(
							"status", status.toString(), "="), new SearchBean(
							"isPublic", "1", "="));
		}
		return resumeDao.findAll(new Sort(Direction.ASC, "order"),
				new SearchBean("recommended", "1", "="), new SearchBean(
						"isPublic", "1", "="));

	}

	@Override
	public Page<ResumeDto> find(Pageable pageable, SearchBean... searchBeans) {
		Page<Resume> list = resumeDao.findAll(pageable, searchBeans);
		List<ResumeDto> content = new ArrayList<ResumeDto>();
		for (Resume Resume : list.getContent()) {
			content.add(ResumeDto.convert(Resume));
		}
		return new PageImpl<ResumeDto>(content, pageable,
				list.getTotalElements());
	}

	@Override
	public boolean isExist(String id, String name) {
		return resumeDao.isNoDeleteExist("name", name, id);
	}

}
