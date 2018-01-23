package com.sys.service.userRole;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.sys.dao.UserRoleDao;
import com.sys.entity.UserRole;

@Service("UserRoleService")
public class UserRoleServiceImp extends BaseServiceImp<UserRole> implements UserRoleService {

	@Override
	public void saveUserRole(UserRole userRole) {
		((UserRoleDao) baseDao).saveUserRole(userRole);
	}
}
