package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;


/**
 * 字典类型表
 */

@Alias("RoleMenu")
public class RoleMenu extends BaseEntity{
    /** @Fields serialVersionUID :           */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String menuId;

    private String roleCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


  
}