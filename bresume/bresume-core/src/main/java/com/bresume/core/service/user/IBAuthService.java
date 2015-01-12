package com.bresume.core.service.user;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.model.entity.user.BAuth;

/**
 * ClassName:IAuthService Description: TODO ADD REASON. Date: 2014-01-09
 * 下午10:13:51
 * 
 * @author 2k
 */
public interface IBAuthService extends IGenericService<BAuth, String> {
	BAuth findOne(String openId, int type);
	boolean removeBind(String userId,int type);
}
