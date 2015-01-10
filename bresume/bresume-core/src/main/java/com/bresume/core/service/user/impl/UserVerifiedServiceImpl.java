package com.bresume.core.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.user.IUserVerifiedDao;
import com.bresume.core.model.entity.user.UserVerified;
import com.bresume.core.service.user.IUserVerifiedService;

@Service
@Transactional
public class UserVerifiedServiceImpl extends GenericService<UserVerified, String> implements
		IUserVerifiedService {
	@Resource
	private IUserVerifiedDao verifiedDao;

	@Override
	public IGenericDao<UserVerified, String> getDao() {
		return verifiedDao;
	}

	@Override
	public UserVerified findOne(String userName, String code) {
		return verifiedDao.findUnique("from UserVerified where user.userName=? and code =? and verifiedTime is NULL", new Object[]{userName,code});
	}
	
	@Override
	public UserVerified findOneByIdAndCode(String userId, String code) {
		return verifiedDao.findUnique("from UserVerified where user.id=? and code =? and verifiedTime is NULL", new Object[]{userId,code});
	}

}
