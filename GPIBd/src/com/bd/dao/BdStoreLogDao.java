package com.bd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.bd.entity.StoreLog;
import com.sys.entity.Org;

@MyBatis
public interface BdStoreLogDao extends BaseDao<StoreLog> {

	List<StoreLog> findByPage(@Param("param")StoreLog storeLog, @Param("org")Org org, Page<StoreLog> page);

}
