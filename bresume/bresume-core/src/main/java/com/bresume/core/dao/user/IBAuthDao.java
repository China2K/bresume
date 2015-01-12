package com.bresume.core.dao.user;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.model.entity.user.BAuth;

public interface IBAuthDao extends IGenericDao<BAuth, String> {
	boolean delete(String userId,int type);
}
