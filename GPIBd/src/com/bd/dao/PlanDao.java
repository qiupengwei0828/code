package com.bd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.bd.entity.BdPlanEntity;
import com.sys.entity.Org;

/**
 * 系统数据层
 */
@MyBatis
public interface PlanDao extends BaseDao<BdPlanEntity> {



	public List<BdPlanEntity> findByPage(@Param("param")BdPlanEntity bdPlanEntity, @Param("org")Org org, Page<BdPlanEntity> page);

	
}
