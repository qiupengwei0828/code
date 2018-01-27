package com.base.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.page.Page;


public interface BaseService<T> {
	
	

	
	
	/**
	 * 保存一个对象
	 * @param o 对象
	 * @return 对象的ID
	 */
	public void insert(T o);	
	/**
	 * 删除一个对象
	 * @param o  对象
	 */
	public void delete(T o);
	/**
	 * 批量删除一个对象
	 * @param s (主键)数组
	 */
	public void deleteBatch(List<T> os);
	/**
	 * 更新一个对象
	 * @param o 对象       
	 */
	public void update(T o);
	
	/**
	 * 获得一个对象
	 * @param o 对象       
	 * @return List
	 */
	public T get(T o);	
	
	
	/**
	 * 获得对象列表
	 * @param o 对象       
	 * @return List
	 */
	public List<T> find(T o);	
	/**
	 * 获得对象列表
	 * @param o 对象       
	 * @param page 分页对象
	 * @return List
	 */
	public Page<T> findByPage(T o,Page<T> page);	
	

	/**
	 * 统计数目
	 * @param o 对象      
	 * @return long
	 */
	public int count(T o);
	
	
	
	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);
	
	
	/**
	 * 取一个主键
	 * @param @return 
	 * @return Long
	 * @throws
	 */
	public Long getKey();	
	
	
	/**
	 * 以Map形式返回结果集，不使用实体对象映射，Mapper中要设置resultType="java.util.HashMap"
	 * @param @param entity
	 * @param @return 
	 * @return List<Map<String,Object>>
	 * @throws
	 */
	public  List<Map<String, Object>> mapQuery(T entity);
	
	
	
	/**
	 * 以Map形式返回结果集，不使用实体对象映射
	 * @param @param entity
	 * @return List<Map<String,Object>>
	 * @param page 分页对象
	 */
	public List<Map<String, Object>> mapQueryByPage(@Param("param")T entity,Page<T> page);	
	
}
