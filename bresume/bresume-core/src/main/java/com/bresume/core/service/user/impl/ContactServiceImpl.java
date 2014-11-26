package com.bresume.core.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.user.IContactDao;
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

}
