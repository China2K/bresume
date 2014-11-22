package com.bresume.core.dao.sys.impl;
import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.sys.IAreaDao;
import com.bresume.core.model.entity.sys.Area;

/**
 * 区级Dao实现类
 * 
 * @author 2k
 * 
 */
@Repository("areaDao")
public class AreaDaoImpl extends SimpleHibernateDao<Area, String> implements
		IAreaDao {
}
