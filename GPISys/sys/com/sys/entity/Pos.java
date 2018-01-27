package com.sys.entity;

import org.apache.ibatis.type.Alias;
import com.base.entity.BaseEntity;

@Alias("Pos")
public class Pos extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;

	private String posCode;

	private String posName;
	// 岗位级别
	private String orgLevel;
	// 岗位职责
	private String duty;
	// 是否重要岗位（是否轮岗轮岗）
	private String primary;
	// 轮岗期限
	private String limitDate;

	private String remark;

	private String status;

	/*
	 * 
	 */

	private String appCode;

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

}
