package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.resume.IPersionalInfoDao;
import com.bresume.core.model.entity.resume.PersionalInfo;
@Repository
public class PersionalInfoDaoImpl extends SimpleHibernateDao<PersionalInfo, String> implements IPersionalInfoDao {

	
}
