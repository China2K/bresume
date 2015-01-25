package com.bresume.core.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.common.constant.enums.UserStatus;
import com.bresume.core.common.utils.GeneralUtils;
import com.bresume.core.dao.sys.ISysUserDao;
import com.bresume.core.model.entity.sys.SysUser;

@Repository
public class SysUserDaoImpl extends  SimpleHibernateDao<SysUser,String> implements ISysUserDao {

	public void updateStatus(String userName, UserStatus status) {
		String hql = "update SysUser set status=? where userName=?";
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
				"from SysUser where status!=? and ");
		hql.append(propertyName);
		hql.append("=?");

		// 如果是更新，则相同记录的值可以重复
		if (GeneralUtils.isNotNull(uuid)) {
			hql.append(" and id !='");
			hql.append(uuid);
			hql.append("'");
		}

		return !this.find(hql.toString(),
				UserStatus.DELETED.getCode(), value).isEmpty();
	}
	
}
