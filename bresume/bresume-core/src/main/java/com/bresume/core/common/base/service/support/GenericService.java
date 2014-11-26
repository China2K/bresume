package com.bresume.core.common.base.service.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.common.utils.search.SearchBean;

@Transactional
public  abstract class GenericService<T, ID extends Serializable> implements
		IGenericService<T, ID> {
	public abstract IGenericDao<T, ID> getDao();

	@Override
	@Transactional(readOnly=true)
	public long count() {
		return getDao().count();
	}

	@Override
	@Transactional
	public void delete(ID id) {
		getDao().delete(id);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		getDao().delete(entity);
	}


	@Override
	@Transactional(readOnly=true)
	public Iterable<T> findAll() {
		return getDao().findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<T> find(Collection<ID> ids) {
		return getDao().find(ids);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<T> findAll(Sort sort) {
		return getDao().findAll(sort);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<T> findAll(Pageable pageable) {
		return getDao().findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public T findOne(ID id) {
		return getDao().findById(id);
	}

	@Override
	public ID save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void update(T entity) {
		getDao().update(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public int count(SearchBean... searchBeans) {
		return getDao().count(searchBeans);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<T> findPage(Pageable pageable, SearchBean... searchBeans) {
		return getDao().findAll(pageable, searchBeans);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<T> findAll(Sort sort, SearchBean... searchBeans) {
		return getDao().findAll(sort, searchBeans);
	}
}
