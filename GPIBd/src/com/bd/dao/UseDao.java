package com.bd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.bd.entity.BdUseDetail;
import com.sys.entity.Org;

/**
 * 系统数据层
 */
@MyBatis
public interface UseDao extends BaseDao<BdUseDetail> {

	List<BdUseDetail> findByPage(@Param("param")BdUseDetail bdUseDetail, @Param("org")Org org,
			Page<BdUseDetail> page);

	List<BdUseDetail> find(@Param("param")BdUseDetail bdUseDetail, @Param("quarter")String quarter, @Param("year")String year);

	List<BdUseDetail> findByPageReport(@Param("param")BdUseDetail bdUseDetail, @Param("org")Org org,
			Page<BdUseDetail> page);

	List<BdUseDetail> export(@Param("param")BdUseDetail bdUseDetail, @Param("org")Org org);


	

}
