package com.setp.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("SetpUser")
public class SetpUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String pwd;
	private String orgNo;
	private String depCode;
	private String mobile;
	private String email;
	private String mobile2;
	private String status;
	private String crt_time;
	private String remark;
	/*
	 * 传参
	 */

	private String rownum;

	private String rank1;

	private String rank2;

	private String setpNum;

	private String orgName;

	private String beginDate;

	private String endDate;

	private String orgNo2nd;

	private String orgName2nd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCrt_time() {
		return crt_time;
	}

	public void setCrt_time(String crt_time) {
		this.crt_time = crt_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRank1() {
		return rank1;
	}

	public void setRank1(String rank1) {
		this.rank1 = rank1;
	}

	public String getRank2() {
		return rank2;
	}

	public void setRank2(String rank2) {
		this.rank2 = rank2;
	}

	public String getSetpNum() {
		return setpNum;
	}

	public void setSetpNum(String setpNum) {
		this.setpNum = setpNum;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getOrgNo2nd() {
		return orgNo2nd;
	}

	public void setOrgNo2nd(String orgNo2nd) {
		this.orgNo2nd = orgNo2nd;
	}

	public String getOrgName2nd() {
		return orgName2nd;
	}

	public void setOrgName2nd(String orgName2nd) {
		this.orgName2nd = orgName2nd;
	}

}
