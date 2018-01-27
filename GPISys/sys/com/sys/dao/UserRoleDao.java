package com.sys.dao;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.UserRole;

/**
 * 系统数据层
 */
@MyBatis
public interface UserRoleDao extends BaseDao<UserRole> {

	public void saveUserRole(UserRole userRole);

}
