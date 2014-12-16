/** 
 * Project Name:bresume-core 
 * File Name:PersionalInfoService.java 
 * Package Name:com.bresume.core.service.resume.impl 
 * Date:2014-12-13下午9:57:41 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
 */

package com.bresume.core.service.resume.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.support.GenericService;
import com.bresume.core.dao.resume.IPersionalInfoDao;
import com.bresume.core.model.entity.resume.item.PersionalInfo;
import com.bresume.core.service.resume.IPersionalInfoService;

/**
 * ClassName:PersionalInfoService Description: TODO ADD REASON. Date: 2014-12-13
 * 下午9:57:41
 * 
 * @author Administrator
 */
@Service
@Transactional
public class PersionalInfoService extends GenericService<PersionalInfo, String>
		implements IPersionalInfoService {

	private IPersionalInfoDao persionalInfoDao;

	@Override
	public IGenericDao<PersionalInfo, String> getDao() {
		
		new PersionalInfo();
		return persionalInfoDao;
	}

}
