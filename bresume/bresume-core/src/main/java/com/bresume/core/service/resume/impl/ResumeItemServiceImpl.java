package com.bresume.core.service.resume.impl;

import java.lang.reflect.Method;
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
import com.bresume.core.common.constant.enums.ResumeItemType;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.dao.resume.IResumeItemDao;
import com.bresume.core.dao.resume.IResumeItemRefDao;
import com.bresume.core.model.base.BaseDto;
import com.bresume.core.model.base.BaseEntity;
import com.bresume.core.model.dto.ResumeItemDto;
import com.bresume.core.model.entity.resume.ResumeItem;
import com.bresume.core.service.resume.IResumeItemService;

@Service
@Transactional
public class ResumeItemServiceImpl extends GenericService<ResumeItem, String>
		implements IResumeItemService {

	@Resource
	private IResumeItemDao resumeItemDao;

	@Resource
	private IResumeItemRefDao resumeItemRefDao;

	@Override
	public IGenericDao<ResumeItem, String> getDao() {
		return resumeItemDao;
	}

	@Override
	public List<?> findResumeItem(ResumeItemType rit, String resumeId) {
		return resumeItemDao
				.findResumeItem(rit.getClazz(),
						new SearchBean[] { new SearchBean("resume.id",
								resumeId, "=") }, null);
	}
	
	@Override
	public List<?> findResumeItemDto(ResumeItemType rit, String resumeId) {
		List<?> eList = resumeItemDao
		.findResumeItem(rit.getClazz(),
				new SearchBean[] { new SearchBean("resume.id",
						resumeId, "=") }, null);
		if(CommonUtils.isEmpty(eList)){
			return null;
		}
		List<Object> list = new ArrayList<Object>();
		try {
			Class<? extends BaseDto> dtoClass = rit.getDtoClazz();
			Class<? extends BaseEntity> entityClass = rit.getClazz();
			System.out.println(dtoClass+"------"+entityClass);
			Method m_convert = dtoClass.getDeclaredMethod("convert", entityClass);
			for(Object obj:eList){
				
				Object invoke = m_convert.invoke(dtoClass.newInstance(), obj);
				list.add(dtoClass.cast(invoke));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ResumeItem> findDefaultItems(String resumeId) {
		if (CommonUtils.isNotEmpty(resumeId)) {
			return resumeItemDao.findResumeItems(resumeId);
		}
		return resumeItemDao.findAll(new Sort(Direction.ASC, "order"),
				new SearchBean("isDefault", "true", "="));
	}

	@Override
	public List<ResumeItem> findExtraItems(String resumeId) {
		List<ResumeItem> all = resumeItemDao.findAll(new Sort(Direction.ASC,
				"order"));

		List<ResumeItem> defaultItems = findDefaultItems(resumeId);
		all.removeAll(defaultItems);
		return all;
	}

	@Override
	public Page<ResumeItemDto> find(Pageable pageable,
			SearchBean... searchBeans) {
		Page<ResumeItem> list = resumeItemDao.findAll(pageable, searchBeans);
		List<ResumeItemDto> content = new ArrayList<ResumeItemDto>();
		for (ResumeItem item : list.getContent()) {
			content.add(ResumeItemDto.convert2Dto(item));
		}
		return new PageImpl<ResumeItemDto>(content, pageable,
				list.getTotalElements());
	}

}
