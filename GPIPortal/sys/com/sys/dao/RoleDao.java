package com.sys.dao;

import java.util.List;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.Role;

/**
 * 系统数据层
 */
@MyBatis
public interface RoleDao extends BaseDao<Role> {

	public List<Role> existsRoleCode(Role app);

	// 查询角色
	public List<Role> findRoleList(Role app);
}
