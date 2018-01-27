package com.sys.entity;

import com.base.entity.BaseEntity;

public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String userId;

	private String roleCode;

	/**
	 * 
	 * @return t_sys_user_role
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
