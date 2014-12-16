package com.bresume.core.dao.sys;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.model.entity.sys.Province;

/**
 * 省级操作dao
 * 
 * @author gavin
 * 
 */
public interface IProvinceDao extends IGenericDao<Province, String> {

	Province getProvinceByName(String name);

}
