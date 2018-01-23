package com.bd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.bd.entity.BdProcess;
import com.sys.entity.Org;

/**
 * 系统数据层
 */
@MyBatis
public interface BdPlanLogDao extends BaseDao<BdProcess> {


	public List<BdProcess> findByPage(@Param("param")BdProcess bdProcess, @Param("org")Org org, Page<BdProcess> page);

	public List<BdProcess> findByPageDetail(@Param("param")BdProcess bdProcess,
			Page<BdProcess> page);
	
}
