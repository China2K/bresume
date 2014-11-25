package com.bresume.core.service.user;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.model.entity.user.UserVerified;

public interface IUserVerifiedService extends IGenericService<UserVerified,String> {
	
	UserVerified findOne(String userName,String code);
	
}
