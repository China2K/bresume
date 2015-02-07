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
import com.bresume.core.common.constant.enums.ResumeItemType;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.dao.resume.IResumeDao;
import com.bresume.core.model.dto.ResumeDto;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.model.entity.resume.ResumeItemRef;
import com.bresume.core.model.entity.resume.item.JobIntension;
import com.bresume.core.service.resume.IJobIntenService;
import com.bresume.core.service.resume.IResumeItemService;
import com.bresume.core.service.resume.IResumeService;

@Service
@Transactional
public class ResumeServiceImpl extends GenericService<Resume, String> implements
		IResumeService {

	@Resource
	private IResumeDao resumeDao;

	@Resource
	private IResumeItemService resumeItemService;

	@Resource
	private IJobIntenService jobIntenService;

	@Override
	public IGenericDao<Resume, String> getDao() {
		return resumeDao;
	}

	@Override
	public List<ResumeDto> findHostResumes(Integer status) {
		List<Resume> list = null;
		if (status != null) {
			list = resumeDao.findAll(new Sort(Direction.ASC, "order"),
					new SearchBean("recommended", "1", "="), new SearchBean(
							"status", status.toString(), "="), new SearchBean(
							"isPublic", "1", "="));
		}
		list = resumeDao.findAll(new Sort(Direction.ASC, "order"),
				new SearchBean("recommended", "1", "="), new SearchBean(
						"isPublic", "1", "="));
		List<ResumeDto> result = new ArrayList<ResumeDto>();
		for (Resume resume : list) {
			ResumeDto dto = ResumeDto.convert2Dto(resume);
			JobIntension ji = jobIntenService.findUniqueBy("resume.id",
					resume.getId());
			if (ji != null) {
				dto.setPosition(ji.getProfession());
			}
			result.add(dto);
		}

		return result;

	}

	@Override
	public Page<ResumeDto> find(Pageable pageable, SearchBean... searchBeans) {
		Page<Resume> list = resumeDao.findAll(pageable, searchBeans);
		List<ResumeDto> content = new ArrayList<ResumeDto>();
		for (Resume resume : list.getContent()) {
			ResumeDto dto = ResumeDto.convert2Dto(resume);
			JobIntension ji = jobIntenService.findUniqueBy("resume.id",
					resume.getId());
			if (ji != null) {
				dto.setPosition(ji.getProfession());
			}
			content.add(dto);
		}
		return new PageImpl<ResumeDto>(content, pageable,
				list.getTotalElements());
	}

	@Override
	public boolean isExist(String id, String name) {
		return resumeDao.isNoDeleteExist("name", name, id);
	}

	@Override
	public int getScore(Resume resume) {
		if (resume == null)
			return 0;
		List<ResumeItemRef> refs = resume.getRefs();
		double rate = 50.00d;
		if (refs != null) {
			int size = refs.size();
			for (ResumeItemRef ref : refs) {
				String sn = ref.getItemSn();
				ResumeItemType resumeItem = ResumeItemType.fromSn(sn);
				List<?> objItems = resumeItemService.findResumeItem(resumeItem,
						resume.getId());
				if (CommonUtils.isNotEmpty(objItems)) {
					rate += 50.00 / size;
				}
			}
		}
		return Integer.parseInt(new java.text.DecimalFormat("0").format(rate));
	}

}
