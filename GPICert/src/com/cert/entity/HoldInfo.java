package com.cert.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("HoldInfo")
public class HoldInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String userId;
	private String userCert;
	private String certCode;
	private String certNo;
	private String issueDate;
	private String issueOrg;
	private String certLevel;
	/** 优秀、合格 */
	private String certScore;
	/** 1：待审核 ；2：已认证；3：无效；4:已过期； */
	private String status;
	private String remark;
	private String certName;
	private String industry;
	private String admClass;
	private String usefulLife;
	private String fileId;
	// 用户和机构信息
	private String userName;
	private String birthday;
	private String orgNo;
	private String pos;
	private String orgName;
	private String tel;
	private String userClass;
	private String depCode;
	private String sex;
	// 用于提取文件
	private String fileSaveName;
	// 持证要求信息
	private String holdReq;
	// 行号变量,用于表示前面的行号
	private String rownum;
	// 发证机构
	private String certUnit;
	// T_CERT_USER_HOLD_INFO.id = holdId
	private String holdId;
	// t_cert_job_cfg.id=cfgId
	private String cfgId;
	// 职业资格证书
	private String zyzg;
	// 从业资格证书
	private String cyzg;
	// 岗位资格证书
	private String gwzg;
	// 技能鉴定
	private String jnjd;

	private String orgNo1st;
	private String orgName1st;
	private String orgNo2nd;
	private String orgName2nd;
	private String orgNo3rd;
	private String orgName3rd;
	private String orgNo4th;
	private String orgName4th;

	/* 查询用字段* */

	/* 维度字段名* */
	private String orgDimField;
	/* 维度字段值* */
	private String ownDimOrgNo;

	/* 机构等级 */
	private String orgLevel;

	public String getFileSaveName() {
		return fileSaveName;
	}

	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCertUnit() {
		return certUnit;
	}

	public void setCertUnit(String certUnit) {
		this.certUnit = certUnit;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getHoldReq() {
		return holdReq;
	}

	public void setHoldReq(String holdReq) {
		this.holdReq = holdReq;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAdmClass() {
		return admClass;
	}

	public void setAdmClass(String admClass) {
		this.admClass = admClass;
	}

	public String getUsefulLife() {
		return usefulLife;
	}

	public void setUsefulLife(String usefulLife) {
		this.usefulLife = usefulLife;
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.USER_ID
	 */
	public String getUserId() {
		return userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param userId
	 *            ： T_CERT_USER_HOLD_INFO.USER_ID
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.USER_CERT
	 */
	public String getUserCert() {
		return userCert;
	}

	/**
	 * @param userCert
	 *            ： T_CERT_USER_HOLD_INFO.USER_CERT
	 */
	public void setUserCert(String userCert) {
		this.userCert = userCert == null ? null : userCert.trim();
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.CERT_CODE
	 */
	public String getCertCode() {
		return certCode;
	}

	/**
	 * @param certCode
	 *            ： T_CERT_USER_HOLD_INFO.CERT_CODE
	 */
	public void setCertCode(String certCode) {
		this.certCode = certCode == null ? null : certCode.trim();
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.CERT_NO
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * @param certNo
	 *            ： T_CERT_USER_HOLD_INFO.CERT_NO
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo == null ? null : certNo.trim();
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.issue_date
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate
	 *            ： T_CERT_USER_HOLD_INFO.issue_date
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate == null ? null : issueDate.trim();
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.issue_org
	 */
	public String getIssueOrg() {
		return issueOrg;
	}

	/**
	 * @param issueOrg
	 *            ： T_CERT_USER_HOLD_INFO.issue_org
	 */
	public void setIssueOrg(String issueOrg) {
		this.issueOrg = issueOrg == null ? null : issueOrg.trim();
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.CERT_LEVEL
	 */
	public String getCertLevel() {
		return certLevel;
	}

	/**
	 * @param certLevel
	 *            ： T_CERT_USER_HOLD_INFO.CERT_LEVEL
	 */
	public void setCertLevel(String certLevel) {
		this.certLevel = certLevel == null ? null : certLevel.trim();
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.CERT_score
	 */
	public String getCertScore() {
		return certScore;
	}

	/**
	 * @param certScore
	 *            ： T_CERT_USER_HOLD_INFO.CERT_score
	 */
	public void setCertScore(String certScore) {
		this.certScore = certScore == null ? null : certScore.trim();
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.STATUS
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            T_CERT_USER_HOLD_INFO.STATUS
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * @return T_CERT_USER_HOLD_INFO.REMARK
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            ： T_CERT_USER_HOLD_INFO.REMARK
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getHoldId() {
		return holdId;
	}

	public String getCfgId() {
		return cfgId;
	}

	public void setHoldId(String holdId) {
		this.holdId = holdId;
	}

	public void setCfgId(String cfgId) {
		this.cfgId = cfgId;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getZyzg() {
		return zyzg;
	}

	public String getCyzg() {
		return cyzg;
	}

	public String getGwzg() {
		return gwzg;
	}

	public String getJnjd() {
		return jnjd;
	}

	public void setZyzg(String zyzg) {
		this.zyzg = zyzg;
	}

	public void setCyzg(String cyzg) {
		this.cyzg = cyzg;
	}

	public void setGwzg(String gwzg) {
		this.gwzg = gwzg;
	}

	public void setJnjd(String jnjd) {
		this.jnjd = jnjd;
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

	public String getOrgDimField() {
		return orgDimField;
	}

	public void setOrgDimField(String orgDimField) {
		this.orgDimField = orgDimField;
	}

	public String getOwnDimOrgNo() {
		return ownDimOrgNo;
	}

	public void setOwnDimOrgNo(String ownDimOrgNo) {
		this.ownDimOrgNo = ownDimOrgNo;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getOrgName1st() {
		return orgName1st;
	}

	public void setOrgName1st(String orgName1st) {
		this.orgName1st = orgName1st;
	}

	public String getOrgName2nd() {
		return orgName2nd;
	}

	public void setOrgName2nd(String orgName2nd) {
		this.orgName2nd = orgName2nd;
	}

	public String getOrgName3rd() {
		return orgName3rd;
	}

	public void setOrgName3rd(String orgName3rd) {
		this.orgName3rd = orgName3rd;
	}

	public String getOrgName4th() {
		return orgName4th;
	}

	public void setOrgName4th(String orgName4th) {
		this.orgName4th = orgName4th;
	}

}