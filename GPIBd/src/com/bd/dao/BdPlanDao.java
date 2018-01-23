package com.bd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.bd.entity.BdPlan;
import com.sys.entity.Org;

/**
 * 系统数据层
 */
@MyBatis
public interface BdPlanDao extends BaseDao<BdPlan> {

	public List<BdPlan> selectData(BdPlan bdPlan);

	public List<BdPlan> findByPage(@Param("param")BdPlan bdPlan, @Param("org")Org org, Page<BdPlan> page);

	public List<BdPlan> findQuarter(String dd);

	public List<BdPlan> findByPageQuery(@Param("param")BdPlan bdPlan, @Param("org")Org org,
			Page<BdPlan> page);

	public void set(BdPlan bdPlan);
	
}
