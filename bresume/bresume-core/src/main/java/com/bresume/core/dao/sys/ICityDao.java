package com.bresume.core.dao.sys;


import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.model.entity.sys.City;

/**
 * 市级操作dao
 * 
 * @author gavin
 * 
 */
public interface ICityDao extends IGenericDao<City, String> {

	City getCityByName(String city);
	
}
