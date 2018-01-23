package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

/**
 * 系统应用表
 */

@Alias("App")
public class App extends BaseEntity {

	/** @Fields serialVersionUID : */
	private static final long serialVersionUID = 1L;

	private String appCode;

	private String appName;

	/** 系统所属业务条线 */
	private String depCode;

	private String depName;

	private String orgNo;

	private String orgName;

	/** 访问地址 */
	private String accUrl;

	/** 1：上线；2：下线； */
	private String status;

	private String remark;

	private String miniName;

	/** 系统图标名 */
	private String icon;

	/** 打开页面 */
	private String openPage;

	/** 禁用图标 */
	private String disIcon;
	/** 排序 */
	private String sort;

	/** 用户使用权限 */
	private String hasRole;

	/** 用户id */
	private String userId;

	/**
	 * @return T_SYS_APP_INFO.APP_CODE
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode
	 *            ： T_SYS_APP_INFO.APP_CODE
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode == null ? null : appCode.trim();
	}

	/**
	 * @return T_SYS_APP_INFO.APP_NAME
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName
	 *            ： T_SYS_APP_INFO.APP_NAME
	 */
	public void setAppName(String appName) {
		this.appName = appName == null ? null : appName.trim();
	}

	/**
	 * @return T_SYS_APP_INFO.DEP_CODE
	 */
	public String getDepCode() {
		return depCode;
	}

	/**
	 * @param depCode
	 *            ： T_SYS_APP_INFO.DEP_CODE
	 */
	public void setDepCode(String depCode) {
		this.depCode = depCode == null ? null : depCode.trim();
	}

	/**
	 * @return T_SYS_APP_INFO.ACC_URL
	 */
	public String getAccUrl() {
		return accUrl;
	}

	/**
	 * @param accUrl
	 *            ： T_SYS_APP_INFO.ACC_URL
	 */
	public void setAccUrl(String accUrl) {
		this.accUrl = accUrl == null ? null : accUrl.trim();
	}

	/**
	 * @return T_SYS_APP_INFO.STATUS
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            ： T_SYS_APP_INFO.STATUS
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * @return T_SYS_APP_INFO.REMARK
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            ： T_SYS_APP_INFO.REMARK
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * @return T_SYS_APP_INFO.ICON
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            ： T_SYS_APP_INFO.ICON
	 */
	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	/** @return: String */
	public String getDepName() {
		return depName;
	}

	/** @param String */
	public void setDepName(String depName) {
		this.depName = depName;
	}

	/** @return: String */
	public String getOrgName() {
		return orgName;
	}

	/** @param String */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/** @return: String */
	public String getOrgNo() {
		return orgNo;
	}

	/** @param String */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getHasRole() {
		return hasRole;
	}

	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenPage() {
		return openPage;
	}

	public void setOpenPage(String openPage) {
		this.openPage = openPage;
	}

	public String getDisIcon() {
		return disIcon;
	}

	public void setDisIcon(String disIcon) {
		this.disIcon = disIcon;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getMiniName() {
		return miniName;
	}

	public void setMiniName(String miniName) {
		this.miniName = miniName;
	}

}