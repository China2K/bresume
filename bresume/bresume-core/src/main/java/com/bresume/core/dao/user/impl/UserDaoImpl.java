package com.bresume.core.dao.user.impl;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.common.constant.enums.UserStatus;
import com.bresume.core.common.utils.GeneralUtils;
import com.bresume.core.dao.user.IUserDao;
import com.bresume.core.model.entity.user.User;

public class UserDaoImpl extends SimpleHibernateDao<User,String> implements IUserDao {

	public void updateStatus(String userName, UserStatus status) {
		String hql = "update User set status=? where userName=?";
		this.batchExecute(hql, status.getCode(), userName);
	}

	/**
	 * 查找没有删除的商户，是否有指定属性的记录存在<br>
	 * 用于新增或者更新
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@Override
	public boolean isNoDeleteExist(String propertyName, Object value,
			String uuid) {
		StringBuffer hql = new StringBuffer(
				"from User where status!=? and ");
		hql.append(propertyName);
		hql.append("=?");

		// 如果是更新，则相同记录的值可以重复
		if (GeneralUtils.isNotNull(uuid)) {
			hql.append(" and id !='");
			hql.append(uuid);
			hql.append("'");
		}

		return !this.find(hql.toString(),
				UserStatus.DELETED, value).isEmpty();
	}
	
	

}
