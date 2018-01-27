package com.sys.service.role;

import java.util.List;

import com.base.service.BaseService;
import com.sys.entity.Role;

public interface RoleService extends BaseService<Role> {

	public List<Role> existsRoleCode(Role role);

	// 查询角色
	public List<Role> findRoleList(Role role);
}
