package com.bresume.core.dao.area;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.model.entity.Province;

/**
 * 省级操作dao
 * 
 * @author gavin
 * 
 */
public interface IProvinceDao extends IGenericDao<Province, String> {

	Province getProvinceByName(String name);

}
