package com.bresume.core.service.resume;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.common.constant.enums.ResumeItemType;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.ResumeItemDto;
import com.bresume.core.model.entity.resume.ResumeItem;

public interface IResumeItemService extends IGenericService<ResumeItem,String>{
	
	public List<?>findResumeItem(ResumeItemType rit,String resumeId); 
	
	public List<ResumeItem> findDefaultItems(String resumeId);
	
	public List<ResumeItem> findExtraItems(String resumeId);
	
	public Page<ResumeItemDto> find(Pageable pageable, SearchBean... searchBeans) ;
	
	public List<?> findResumeItemDto(ResumeItemType rit, String resumeId);
	
	
	
}
