package com.bresume.core.dao.resume;

import java.util.List;

import org.hibernate.criterion.Order;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.entity.resume.ResumeItem;

public interface IResumeItemDao extends IGenericDao<ResumeItem, String> {

	List<?> findResumeItem(Class<?> entity, SearchBean[] searchBean, Order order);
}
