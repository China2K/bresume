package com.bresume.core.dao.user;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.constant.enums.UserStatus;
import com.bresume.core.model.entity.user.User;

public interface IUserDao extends IGenericDao<User, String> {
	/**
	 * 根据用户名，更新状态
	 * 
	 * @param userName
	 * @param status
	 */
	void updateStatus(String userName, UserStatus status);

	/**
	 * 查找没有删除的商户，是否有指定属性的记录存在
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	boolean isNoDeleteExist(String propertyName, Object value, String uuid);
}
