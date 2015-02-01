package com.bresume.core.dao.resume.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.common.constant.enums.CommonStatus;
import com.bresume.core.common.utils.GeneralUtils;
import com.bresume.core.dao.resume.IResumeDao;
import com.bresume.core.model.entity.resume.Resume;

@Repository
public class ResumeDaoImpl extends SimpleHibernateDao<Resume, String> implements
		IResumeDao {
	@Override
	public boolean isNoDeleteExist(String propertyName, Object value,
			String uuid) {
		StringBuffer hql = new StringBuffer("from Resume where status!=? and ");
		hql.append(propertyName);
		hql.append("=?");

		// 如果是更新，则相同记录的值可以重复
		if (GeneralUtils.isNotNull(uuid)) {
			hql.append(" and id !='");
			hql.append(uuid);
			hql.append("'");
		}

		return !this
				.find(hql.toString(), CommonStatus.DELETED.getCode(), value)
				.isEmpty();
	}

}
