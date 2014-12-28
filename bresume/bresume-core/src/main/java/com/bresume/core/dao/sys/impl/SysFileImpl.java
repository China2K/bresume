package com.bresume.core.dao.sys.impl;
import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.sys.ISysFileDao;
import com.bresume.core.model.entity.sys.SysFile;

/**
 * @author 2k
 * 
 */
@Repository
public class SysFileImpl extends SimpleHibernateDao<SysFile, String> implements
		ISysFileDao {
}
