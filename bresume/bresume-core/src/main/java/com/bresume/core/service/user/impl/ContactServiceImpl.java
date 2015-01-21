package com.bresume.core.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.dao.user.IContactDao;
import com.bresume.core.model.dto.ContactDto;
import com.bresume.core.model.entity.user.Contact;
import com.bresume.core.service.user.IContactService;

@Service
@Transactional
public class ContactServiceImpl extends GenericService<Contact, String>
		implements IContactService {
	@Resource
	private IContactDao contactDao;

	@Override
	public IGenericDao<Contact, String> getDao() {
		return contactDao;
	}

	@Override
	public Page<ContactDto> find(Pageable pageable, SearchBean... searchBeans) {
		Page<Contact> list = contactDao.findAll(pageable, searchBeans);
		List<ContactDto> content = new ArrayList<ContactDto>();
		for (Contact con : list.getContent()) {
			content.add(ContactDto.convert(con));
		}
		return new PageImpl<ContactDto>(content, pageable, list.getTotalElements());
	}

}
