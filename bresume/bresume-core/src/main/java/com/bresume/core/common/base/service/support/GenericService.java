package com.bresume.core.common.base.service.support;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.bresume.core.common.base.dao.IGenericDao;
import com.bresume.core.common.base.service.IGenericService;

public class GenericService<T, ID extends Serializable> implements
		IGenericService<T, ID> {
	public  IGenericDao<T, ID> getDao(){
		return null;
	}

	@Override
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
	public Iterable<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public Iterable<T> find(Collection<ID> ids) {
		return getDao().find(ids);
	}

	@Override
	public Iterable<T> findAll(Sort sort) {
		return getDao().findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return getDao().findAll(pageable);
	}

	@Override
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


}
