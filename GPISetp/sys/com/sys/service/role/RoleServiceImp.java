package com.sys.service.role;

import java.util.List;

import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImp;
import com.sys.dao.RoleDao;
import com.sys.entity.Role;

@Service("RoleService")
public class RoleServiceImp extends BaseServiceImp<Role> implements RoleService {

	@Override
	public List<Role> existsRoleCode(Role role) {
		return ((RoleDao) baseDao).existsRoleCode(role);
	}

	// 查询角色
	@Override
	public List<Role> findRoleList(Role role) {
		return ((RoleDao) baseDao).findRoleList(role);
	}
}
