package com.cert.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("CertJobCfg")
public class CertJobCfg extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String certCode;
	private String posCode;
	/** 要求持有、自选持有、鼓励持有 */
	private String holdReq;
	private String remark;
	// 证书所属的行业
	private String industry;
	// 以下参数只在传数据时有效
	private String certName;
	private String hold1;
	private String hold2;
	private String hold3;
	private String userId;
	private String userName;
	private String hrNo;
	private String pos;
	private String posName;
	private String orgNo;
	private String yqcyCert;
	private String ycyCert;
	private String wcyCert;
	private String certNo;
	private String issueDate;
	private String status;
	private String admClass;
	private String orgName;

	private String orgNo1st;
	private String orgNo2nd;
	private String orgNo3rd;
	private String orgNo4th;

	/**
	 * @return T_CERT_JOB_CFG.ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            ： T_CERT_JOB_CFG.ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return T_CERT_JOB_CFG.CERT_CODE
	 */
	public String getCertCode() {
		return certCode;
	}

	/**
	 * @param certCode
	 *            ： T_CERT_JOB_CFG.CERT_CODE
	 */
	public void setCertCode(String certCode) {
		this.certCode = certCode == null ? null : certCode.trim();
	}

	/**
	 * @return T_CERT_JOB_CFG.POS_CODE
	 */
	public String getPosCode() {
		return posCode;
	}

	/**
	 * @param posCode
	 *            ： T_CERT_JOB_CFG.POS_CODE
	 */
	public void setPosCode(String posCode) {
		this.posCode = posCode == null ? null : posCode.trim();
	}

	/**
	 * @return T_CERT_JOB_CFG.HOLD_REQ
	 */
	public String getHoldReq() {
		return holdReq;
	}

	/**
	 * @param holdReq
	 *            ： T_CERT_JOB_CFG.HOLD_REQ
	 */
	public void setHoldReq(String holdReq) {
		this.holdReq = holdReq == null ? null : holdReq.trim();
	}

	/**
	 * @return T_CERT_JOB_CFG.REMARK
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            ： T_CERT_JOB_CFG.REMARK
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/*
	 * *************************************************************
	 */

	public String getCertName() {
		return certName;
	}

	public String getHold1() {
		return hold1;
	}

	public String getHold2() {
		return hold2;
	}

	public String getHold3() {
		return hold3;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getHrNo() {
		return hrNo;
	}

	public String getPos() {
		return pos;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public String getYqcyCert() {
		return yqcyCert;
	}

	public String getYcyCert() {
		return ycyCert;
	}

	public String getWcyCert() {
		return wcyCert;
	}

	public String getCertNo() {
		return certNo;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public void setHold1(String hold1) {
		this.hold1 = hold1;
	}

	public void setHold2(String hold2) {
		this.hold2 = hold2;
	}

	public void setHold3(String hold3) {
		this.hold3 = hold3;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setHrNo(String hrNo) {
		this.hrNo = hrNo;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public void setYqcyCert(String yqcyCert) {
		this.yqcyCert = yqcyCert;
	}

	public void setYcyCert(String ycyCert) {
		this.ycyCert = ycyCert;
	}

	public void setWcyCert(String wcyCert) {
		this.wcyCert = wcyCert;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdmClass() {
		return admClass;
	}

	public void setAdmClass(String admClass) {
		this.admClass = admClass;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOrgNo1st() {
		return orgNo1st;
	}

	public void setOrgNo1st(String orgNo1st) {
		this.orgNo1st = orgNo1st;
	}

	public String getOrgNo2nd() {
		return orgNo2nd;
	}

	public void setOrgNo2nd(String orgNo2nd) {
		this.orgNo2nd = orgNo2nd;
	}

	public String getOrgNo3rd() {
		return orgNo3rd;
	}

	public void setOrgNo3rd(String orgNo3rd) {
		this.orgNo3rd = orgNo3rd;
	}

	public String getOrgNo4th() {
		return orgNo4th;
	}

	public void setOrgNo4th(String orgNo4th) {
		this.orgNo4th = orgNo4th;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

}