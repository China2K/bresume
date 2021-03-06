package com.bresume.core.common.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.bresume.core.common.utils.search.SearchBean;

public interface IGenericService<T, ID extends Serializable> {
	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	public ID save(T entity);

	
	/**
	 * Update a given entity. Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 * 
	 * @param entity
	 * @return the updated entity
	 */
	public void update(T entity);

	/**
	 * Retrives an entity by its id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	T findOne(ID id);


	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	Iterable<T> findAll();

	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return
	 */
	Iterable<T> find(Collection<ID> ids);

	/**
	 * Returns all entities sorted by the given options.
	 * 
	 * @param sort
	 * @return all entities sorted by the given options
	 */
	Iterable<T> findAll(Sort sort);

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction
	 * provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of entities
	 */
	Page<T> findAll(Pageable pageable);

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	long count();

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */
	void delete(ID id);

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException
	 *             in case the given entity is (@literal null}.
	 */
	void delete(T entity);

	/**
	 * 高级查询取得记录数
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	int count(SearchBean... searchBeans);

	/**
	 * 分页查询
	 * 
	 * @param pageable
	 * @return
	 */
	Page<T> findPage(Pageable pageable, SearchBean... searchBeans);
	
	/**
	 * 查询
	 * 
	 * @param sort
	 * @param SearchBean[]
	 * @return
	 */
	List<T> findAll(Sort sort, SearchBean... searchBeans);
	
	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 */
	T findUniqueBy(String propertyName, Object value);

}
