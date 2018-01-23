package com.bd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.bd.entity.Store;
import com.sys.entity.Org;

/**
 * 系统数据层
 */
@MyBatis
public interface BdStoreDao extends BaseDao<Store> {

	public List<Store> export(@Param("param")Store store, @Param("org")Org org);
	public List<Store> findByPage(@Param("param")Store store,@Param("org")Org org,Page<Store> page);
	public List<Store> findNum(@Param("param")Store store,@Param("org")Org org);
	public void updateStroreNum(Store store);
	}
