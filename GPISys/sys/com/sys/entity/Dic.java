package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

/**
 * 系统应用表
 */

@Alias("Dic")
public class Dic extends BaseEntity {

	/** @Fields serialVersionUID : */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String typeCode;

	/** 显示使用，添加时不作为参数 **/
	private String typeName;

	private String pName;

	private String pValue;

	private String remark;

	private String status;

	private String crtUser;

	private String crtTime;

	/**
	 * @return T_SYS_DIC_INFO.ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            ： T_SYS_DIC_INFO.ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return T_SYS_DIC_INFO.TYPE_CODE
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode
	 *            ： T_SYS_DIC_INFO.TYPE_CODE
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode == null ? null : typeCode.trim();
	}

	/**
	 * @return T_SYS_DIC_INFO.P_NAME
	 */
	public String getpName() {
		return pName;
	}

	/**
	 * @param pName
	 *            ： T_SYS_DIC_INFO.P_NAME
	 */
	public void setpName(String pName) {
		this.pName = pName == null ? null : pName.trim();
	}

	/**
	 * @return T_SYS_DIC_INFO.P_VALUE
	 */
	public String getpValue() {
		return pValue;
	}

	/**
	 * @param pValue
	 *            ： T_SYS_DIC_INFO.P_VALUE
	 */
	public void setpValue(String pValue) {
		this.pValue = pValue == null ? null : pValue.trim();
	}

	/**
	 * @return T_SYS_DIC_INFO.REMARK
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            ： T_SYS_DIC_INFO.REMARK
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * @return T_SYS_DIC_INFO.STATUS
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            ： T_SYS_DIC_INFO.STATUS
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * @return T_SYS_DIC_INFO.CRT_USER
	 */
	public String getCrtUser() {
		return crtUser;
	}

	/**
	 * @param crtUser
	 *            ： T_SYS_DIC_INFO.CRT_USER
	 */
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser == null ? null : crtUser.trim();
	}

	/**
	 * @return T_SYS_DIC_INFO.CRT_TIME
	 */
	public String getCrtTime() {
		return crtTime;
	}

	/**
	 * @param crtTime
	 *            ： T_SYS_DIC_INFO.CRT_TIME
	 */
	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime == null ? null : crtTime.trim();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "Dic [id=" + id + ", typeCode=" + typeCode + ", typeName=" + typeName + ", pName=" + pName + ", pValue=" + pValue + ", remark=" + remark + ", status=" + status + ", crtUser=" + crtUser
				+ ", crtTime=" + crtTime + "]";
	}
}