package com.bresume.core.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.user.IBAuthDao;
import com.bresume.core.model.entity.user.BAuth;
import com.bresume.core.service.user.IBAuthService;

@Service
@Transactional
public class BAuthServiceImpl extends GenericService<BAuth, String> implements
		IBAuthService {
	@Resource
	private IBAuthDao authDao;

	@Override
	public IGenericDao<BAuth, String> getDao() {
		return authDao;
	}

	@Override
	public BAuth findOne(String openId, int type) {
		return authDao.findUnique("FROM BAuth where openId= ? and type = ?",
				openId, type);
	}

}
