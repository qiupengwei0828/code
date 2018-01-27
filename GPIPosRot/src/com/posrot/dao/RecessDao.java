package com.posrot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.posrot.entity.Recess;

@MyBatis
public interface RecessDao extends BaseDao<Recess> {

	// 统计计划在各个状态下数量
	public int countStatusNum(Recess recess);

	// 修改计划状态
	public void updateStatus(Recess recess);

	public List<Recess> findNotPass(@Param("param") Recess recess, Page<Recess> page);

}
