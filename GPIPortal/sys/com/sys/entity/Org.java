package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("Org")
public class Org extends BaseEntity {
	/** @Fields serialVersionUID : */
	private static final long serialVersionUID = 1L;

	private String orgNo;

	private String pOrgNo;

	private String orgName;

	/** 1：省；2：市；3：县区；4：网点； */
	private String orgLevel;

	private String orgNo1st;

	private String orgName1st;

	private String orgNo2nd;

	private String orgName2nd;

	private String orgNo3rd;

	private String orgName3rd;

	private String orgNo4th;

	private String orgName4th;

	/** 1：正常；2：注销 */
	private String status;

	private String crtTime;

	private String crtUser;

	/** 机构分类 */
	private String orgType;
	// 排序
	private String dispNo;
	
	
	/* 查询用字段**/
	
	/* 维度字段名**/
	private String orgDimField;  
	/* 维度字段值**/
	private String ownDimOrgNo;
	

	/**
	 * @return T_SYS_ORG_INFO.ORG_NO
	 * 
	 */
	
	
	
	public String getLevelOrgNo() {
		if (orgLevel == "1") {
			return orgNo1st;
		} else if (orgLevel == "2") {
			return orgNo2nd;
		} else if (orgLevel == "3") {
			return orgNo3rd;
		} else if (orgLevel == "4") {
			return orgNo4th;
		} else {
			return orgNo1st;
		}
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






	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * @param orgNo
	 *            ： T_SYS_ORG_INFO.ORG_NO
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo == null ? null : orgNo.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.P_ORG_NO
	 */
	public String getpOrgNo() {
		return pOrgNo;
	}

	/**
	 * @param pOrgNo
	 *            ： T_SYS_ORG_INFO.P_ORG_NO
	 */
	public void setpOrgNo(String pOrgNo) {
		this.pOrgNo = pOrgNo == null ? null : pOrgNo.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_NAME
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName
	 *            ： T_SYS_ORG_INFO.ORG_NAME
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName == null ? null : orgName.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_LEVEL
	 */
	public String getOrgLevel() {
		return orgLevel;
	}

	/**
	 * @param orgLevel
	 *            ： T_SYS_ORG_INFO.ORG_LEVEL
	 */
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_NO_1ST
	 */
	public String getOrgNo1st() {
		return orgNo1st;
	}

	/**
	 * @param orgNo1st
	 *            ： T_SYS_ORG_INFO.ORG_NO_1ST
	 */
	public void setOrgNo1st(String orgNo1st) {
		this.orgNo1st = orgNo1st == null ? null : orgNo1st.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_NAME_1ST
	 */
	public String getOrgName1st() {
		return orgName1st;
	}

	/**
	 * @param orgName1st
	 *            ： T_SYS_ORG_INFO.ORG_NAME_1ST
	 */
	public void setOrgName1st(String orgName1st) {
		this.orgName1st = orgName1st == null ? null : orgName1st.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_NO_2ND
	 */
	public String getOrgNo2nd() {
		return orgNo2nd;
	}

	/**
	 * @param orgNo2nd
	 *            ： T_SYS_ORG_INFO.ORG_NO_2ND
	 */
	public void setOrgNo2nd(String orgNo2nd) {
		this.orgNo2nd = orgNo2nd == null ? null : orgNo2nd.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_NAME_2ND
	 */
	public String getOrgName2nd() {
		return orgName2nd;
	}

	/**
	 * @param orgName2nd
	 *            ： T_SYS_ORG_INFO.ORG_NAME_2ND
	 */
	public void setOrgName2nd(String orgName2nd) {
		this.orgName2nd = orgName2nd == null ? null : orgName2nd.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_NO_3RD
	 */
	public String getOrgNo3rd() {
		return orgNo3rd;
	}

	/**
	 * @param orgNo3rd
	 *            ： T_SYS_ORG_INFO.ORG_NO_3RD
	 */
	public void setOrgNo3rd(String orgNo3rd) {
		this.orgNo3rd = orgNo3rd == null ? null : orgNo3rd.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_NAME_3RD
	 */
	public String getOrgName3rd() {
		return orgName3rd;
	}

	/**
	 * @param orgName3rd
	 *            ： T_SYS_ORG_INFO.ORG_NAME_3RD
	 */
	public void setOrgName3rd(String orgName3rd) {
		this.orgName3rd = orgName3rd == null ? null : orgName3rd.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_NO_4TH
	 */
	public String getOrgNo4th() {
		return orgNo4th;
	}

	/**
	 * @param orgNo4th
	 *            ： T_SYS_ORG_INFO.ORG_NO_4TH
	 */
	public void setOrgNo4th(String orgNo4th) {
		this.orgNo4th = orgNo4th == null ? null : orgNo4th.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_NAME_4TH
	 */
	public String getOrgName4th() {
		return orgName4th;
	}

	/**
	 * @param orgName4th
	 *            ： T_SYS_ORG_INFO.ORG_NAME_4TH
	 */
	public void setOrgName4th(String orgName4th) {
		this.orgName4th = orgName4th == null ? null : orgName4th.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.STATUS
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            ： T_SYS_ORG_INFO.STATUS
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.CRT_TIME
	 */
	public String getCrtTime() {
		return crtTime;
	}

	/**
	 * @param crtTime
	 *            ： T_SYS_ORG_INFO.CRT_TIME
	 */
	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime == null ? null : crtTime.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.CRT_USER
	 */
	public String getCrtUser() {
		return crtUser;
	}

	/**
	 * @param crtUser
	 *            ： T_SYS_ORG_INFO.CRT_USER
	 */
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser == null ? null : crtUser.trim();
	}

	/**
	 * @return T_SYS_ORG_INFO.ORG_TYPE
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType
	 *            ： T_SYS_ORG_INFO.ORG_TYPE
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getDispNo() {
		return dispNo;
	}

	public void setDispNo(String dispNo) {
		this.dispNo = dispNo;
	}

	@Override
	public String toString() {
		return "Org [orgNo=" + orgNo + ", pOrgNo=" + pOrgNo + ", orgName=" + orgName + ", orgLevel=" + orgLevel + ", orgNo1st=" + orgNo1st + ", orgName1st=" + orgName1st + ", orgNo2nd=" + orgNo2nd
				+ ", orgName2nd=" + orgName2nd + ", orgNo3rd=" + orgNo3rd + ", orgName3rd=" + orgName3rd + ", orgNo4th=" + orgNo4th + ", orgName4th=" + orgName4th + ", status=" + status
				+ ", crtTime=" + crtTime + ", crtUser=" + crtUser + ", orgType=" + orgType + ", dispNo=" + dispNo + "]";
	}

}