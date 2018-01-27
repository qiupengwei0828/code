package com.setp.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("SetpCount")
public class SetpCount extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userId;
	private Long setpNum;
	private String dealTime;
	private String remark1;
	private String remark2;

	/*
	 * 传参使用
	 */
	private String rownum;

	private String userName;

	private String orgNo;

	private String orgName;

	private String num;

	private String beginDate;

	private String endDate;

	private String dim;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getSetpNum() {
		return setpNum;
	}

	public void setSetpNum(Long setpNum) {
		this.setpNum = setpNum;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

}
