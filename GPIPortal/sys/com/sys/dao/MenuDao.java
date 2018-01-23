package com.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.Menu;


/**
 * 系统数据层
 */
@MyBatis
public interface MenuDao extends BaseDao<Menu>{
	 /**
     * 菜单树
     * @param menuId
     * @param userId
     * @return
     */
	public List<Menu> findMenuTree(@Param("userId")String userId,@Param("appCode")String appCode,@Param("common")String common);
	
	
	public List<Menu>  grantTreeData(Menu menu);
}