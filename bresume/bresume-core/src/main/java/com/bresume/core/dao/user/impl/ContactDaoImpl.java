package com.bresume.core.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.user.IContactDao;
import com.bresume.core.model.entity.user.Contact;

@Repository
public class ContactDaoImpl extends SimpleHibernateDao<Contact,String> implements IContactDao {

}
