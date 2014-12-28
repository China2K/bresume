package com.bresume.core.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.sys.ISysFileDao;
import com.bresume.core.model.entity.sys.SysFile;
import com.bresume.core.service.sys.IFileService;

@Service
@Transactional
public class FileServiceImpl extends GenericService<SysFile, String>
		implements IFileService {
	@Resource
	private ISysFileDao sysFileDao;

	@Override
	public IGenericDao<SysFile, String> getDao() {
		return sysFileDao;
	}

}
