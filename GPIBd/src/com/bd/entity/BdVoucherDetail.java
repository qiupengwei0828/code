package com.bd.entity;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;
@Alias("BdVoucherDetail")
public class BdVoucherDetail extends BaseEntity {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal id;

	    /** 请领计划ID */
	    private BigDecimal planId;

	    /** 机构NO */
	    private String orgNo;

	    /** 单证CODE */
	    private String certactCode;

	    /** 单证名称 */
	    private String certactName;

	    /** 购入数量 */
	    private Long purNum;

	    /** 购入时间 */
	    private String purTime;

	    private String userId;

	    private String orgName;

	    private BigDecimal planNumber;

	    /** 季度 */
	    private String quarter;

	    /** 请领时间 */
	    private String planDate;

	    /** 请领量 */
	    private Long planNum;

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.ID
	     */
	    public BigDecimal getId() {
	        return id;
	    }

	    /**
	     * @param id ： T_BD_VOUCHER_DETAIL.ID
	     */
	    public void setId(BigDecimal id) {
	        this.id = id;
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.PLAN_ID
	     */
	    public BigDecimal getPlanId() {
	        return planId;
	    }

	    /**
	     * @param planId ： T_BD_VOUCHER_DETAIL.PLAN_ID
	     */
	    public void setPlanId(BigDecimal planId) {
	        this.planId = planId;
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.ORG_NO
	     */
	    public String getOrgNo() {
	        return orgNo;
	    }

	    /**
	     * @param orgNo ： T_BD_VOUCHER_DETAIL.ORG_NO
	     */
	    public void setOrgNo(String orgNo) {
	        this.orgNo = orgNo == null ? null : orgNo.trim();
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.CERTACT_CODE
	     */
	    public String getCertactCode() {
	        return certactCode;
	    }

	    /**
	     * @param certactCode ： T_BD_VOUCHER_DETAIL.CERTACT_CODE
	     */
	    public void setCertactCode(String certactCode) {
	        this.certactCode = certactCode == null ? null : certactCode.trim();
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.CERTACT_NAME
	     */
	    public String getCertactName() {
	        return certactName;
	    }

	    /**
	     * @param certactName ： T_BD_VOUCHER_DETAIL.CERTACT_NAME
	     */
	    public void setCertactName(String certactName) {
	        this.certactName = certactName == null ? null : certactName.trim();
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.PUR_NUM
	     */
	    public Long getPurNum() {
	        return purNum;
	    }

	    /**
	     * @param purNum ： T_BD_VOUCHER_DETAIL.PUR_NUM
	     */
	    public void setPurNum(Long purNum) {
	        this.purNum = purNum;
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.PUR_TIME
	     */
	    public String getPurTime() {
	        return purTime;
	    }

	    /**
	     * @param purTime ： T_BD_VOUCHER_DETAIL.PUR_TIME
	     */
	    public void setPurTime(String purTime) {
	        this.purTime = purTime == null ? null : purTime.trim();
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.USER_ID
	     */
	    public String getUserId() {
	        return userId;
	    }

	    /**
	     * @param userId ： T_BD_VOUCHER_DETAIL.USER_ID
	     */
	    public void setUserId(String userId) {
	        this.userId = userId == null ? null : userId.trim();
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.ORG_NAME
	     */
	    public String getOrgName() {
	        return orgName;
	    }

	    /**
	     * @param orgName ： T_BD_VOUCHER_DETAIL.ORG_NAME
	     */
	    public void setOrgName(String orgName) {
	        this.orgName = orgName == null ? null : orgName.trim();
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.PLAN_NUMBER
	     */
	    public BigDecimal getPlanNumber() {
	        return planNumber;
	    }

	    /**
	     * @param planNumber ： T_BD_PLAN.PLAN_NUMBER
	     */
	    public void setPlanNumber(BigDecimal planNumber) {
	        this.planNumber = planNumber;
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.QUARTER
	     */
	    public String getQuarter() {
	        return quarter;
	    }

	    /**
	     * @param quarter ： T_BD_VOUCHER_DETAIL.QUARTER
	     */
	    public void setQuarter(String quarter) {
	        this.quarter = quarter == null ? null : quarter.trim();
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.PLAN_DATE
	     */
	    public String getPlanDate() {
	        return planDate;
	    }

	    /**
	     * @param planDate ： T_BD_VOUCHER_DETAIL.PLAN_DATE
	     */
	    public void setPlanDate(String planDate) {
	        this.planDate = planDate == null ? null : planDate.trim();
	    }

	    /**
	     * @return  T_BD_VOUCHER_DETAIL.PLAN_NUM
	     */
	    public Long getPlanNum() {
	        return planNum;
	    }

	    /**
	     * @param planNum ： T_BD_VOUCHER_DETAIL.PLAN_NUM
	     */
	    public void setPlanNum(Long planNum) {
	        this.planNum = planNum;
	    }
}