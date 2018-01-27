package com.posrot.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

/*
 * 人员履历表
 */
@Alias("ResInfo")
public class ResInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String userId;
	private String orgNo;
	private String posCode;
	private String beginDate;
	private String endDate;
	private String status;
	private String remark;
	private String creUserId;
	private String creDate;
	/*
	 * 传参使用
	 */
	private String orgName;
	private String userName;

	private String nowPosCode;// 当前岗位
	private String nowPosName;

	private String pos;

	private String posName;

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

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreUserId() {
		return creUserId;
	}

	public void setCreUserId(String creUserId) {
		this.creUserId = creUserId;
	}

	public String getCreDate() {
		return creDate;
	}

	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNowPosCode() {
		return nowPosCode;
	}

	public void setNowPosCode(String nowPosCode) {
		this.nowPosCode = nowPosCode;
	}

	public String getNowPosName() {
		return nowPosName;
	}

	public void setNowPosName(String nowPosName) {
		this.nowPosName = nowPosName;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

}
