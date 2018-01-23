package com.base.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dao.BaseDao;
import com.base.page.Page;


public class BaseServiceImp<T> implements BaseService<T>{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected BaseDao<T> baseDao;

	
	
	
	@Override
	public void insert(T o) {
		baseDao.insert(o);
	}

	@Override
	public void delete(T o) {
		baseDao.delete(o);
	}
	
	@Override
	public void deleteBatch(List<T> os){
		baseDao.deleteBatch(os);
	}

	@Override
	public void update(T o) {
		baseDao.update(o);
	}
	
	@Override
	public T get(T o)
	{
		List<T>  entityList= baseDao.find(o);
		if(entityList.size()>0)
		   return entityList.get(0);
		return null;
	}

	@Override
	public List<T> find(T o) {
		return baseDao.find(o);
	}

	@Override
	public Page<T> findByPage(T o, Page<T> page) {
		page.setList(baseDao.findByPage(o, page));
		return page;
	}

	@Override
	public int count(T o) {
		return baseDao.count(o);
	}

	/**
	 * @todo:
	 * @param entity
	 * @return
	 * @see com.base.service.BaseService#findAllList(java.lang.Object)
	 */
	@Override
	public List<T> findAllList(T entity) {
		// TODO Auto-generated method stub
		return baseDao.findAllList(entity);
	}
	
	
	
	/**
	 * 取一个主键
	 * @param @return 
	 * @return Long
	 * @throws
	 */
	public Long getKey()
	{
		return baseDao.getKey();
	}
	
	
	/**
	 * 以Map形式返回结果集，不使用实体对象映射，Mapper中要设置resultType="java.util.HashMap"
	 * @param @param entity
	 * @param @return 
	 * @return List<Map<String,Object>>
	 * @throws
	 */
	public  List<Map<String, Object>> mapQuery(T entity)
	{
		return baseDao.mapQuery(entity);
	}
	
	
	
	/**
	 * 以Map形式返回结果集，不使用实体对象映射 
	 * @param @param entity
	 * @return List<Map<String,Object>>
	 * @param page 分页对象
	 */
	public List<Map<String, Object>> mapQueryByPage(T entity,Page<T> page){
		return baseDao.mapQueryByPage(entity, page);
	}	
	
	
	
	
}
