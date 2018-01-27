package com.sys.entity;

import org.apache.ibatis.type.Alias;

@Alias("Role")
public class Role {
	private String roleCode;

	private String roleName;

	private String remark;

	private String status;

	private String appCode;

	private String appName;

	/**
	 * @return T_SYS_ROLE.ROLE_CODE
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode
	 *            ： T_SYS_ROLE.ROLE_CODE
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode == null ? null : roleCode.trim();
	}

	/**
	 * @return T_SYS_ROLE.ROLE_NAME
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            ： T_SYS_ROLE.ROLE_NAME
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	/**
	 * @return T_SYS_ROLE.REMARK
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            ： T_SYS_ROLE.REMARK
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}