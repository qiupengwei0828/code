package com.bd.entity;

import java.math.BigDecimal;

import com.base.entity.BaseEntity;

public class BdPlanEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private BigDecimal id;

    private String orgNo;

    /** 操作人员ID */
    private String userId;

    private String year;

    /** 季度 */
    private String quarter;

    /** 请领时间 */
    private String planDate;

    private String remark;

    private String status;
    /** 请领机构代码 */
    private String planOrgno;

    /** 请领机构名称 */
    private String planOrgname;

    /** 请领单编号 */
    private BigDecimal planNumber;

    /**
     * @return  T_BD_PLAN.ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id ： T_BD_PLAN.ID
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return  T_BD_PLAN.ORG_NO
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * @param orgNo ： T_BD_PLAN.ORG_NO
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    /**
     * @return  T_BD_PLAN.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId ： T_BD_PLAN.USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return  T_BD_PLAN.YEAR
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year ： T_BD_PLAN.YEAR
     */
    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    /**
     * @return  T_BD_PLAN.QUARTER
     */
    public String getQuarter() {
        return quarter;
    }

    /**
     * @param quarter ： T_BD_PLAN.QUARTER
     */
    public void setQuarter(String quarter) {
        this.quarter = quarter == null ? null : quarter.trim();
    }

    /**
     * @return  T_BD_PLAN.PLAN_DATE
     */
    public String getPlanDate() {
        return planDate;
    }

    /**
     * @param planDate ： T_BD_PLAN.PLAN_DATE
     */
    public void setPlanDate(String planDate) {
        this.planDate = planDate == null ? null : planDate.trim();
    }

    /**
     * @return  T_BD_PLAN.REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark ： T_BD_PLAN.REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return  T_BD_PLAN.STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status ： T_BD_PLAN.STATUS
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    private String planId;

    /** 单证CODE */
    private String certactCode;

    /** 单证名称 */
    private String certactName;

    /** 凭证种类 */
    private String certactType;

    /** 凭证库存量 */
    private BigDecimal storeNum;

    /** 请领量 */
    private Long planNum;

    private String increases;


    /**
     * @return  T_BD_PLAN_DETAIL.PLAN_ID
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * @param planId ： T_BD_PLAN_DETAIL.PLAN_ID
     */
    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    /**
     * @return  T_BD_PLAN_DETAIL.CERTACT_CODE
     */
    public String getCertactCode() {
        return certactCode;
    }

    /**
     * @param certactCode ： T_BD_PLAN_DETAIL.CERTACT_CODE
     */
    public void setCertactCode(String certactCode) {
        this.certactCode = certactCode == null ? null : certactCode.trim();
    }

    /**
     * @return  T_BD_PLAN_DETAIL.CERTACT_NAME
     */
    public String getCertactName() {
        return certactName;
    }

    /**
     * @param certactName ： T_BD_PLAN_DETAIL.CERTACT_NAME
     */
    public void setCertactName(String certactName) {
        this.certactName = certactName == null ? null : certactName.trim();
    }

    /**
     * @return  T_BD_PLAN_DETAIL.CERTACT_TYPE
     */
    public String getCertactType() {
        return certactType;
    }

    /**
     * @param certactType ： T_BD_PLAN_DETAIL.CERTACT_TYPE
     */
    public void setCertactType(String certactType) {
        this.certactType = certactType == null ? null : certactType.trim();
    }

    /**
     * @return  T_BD_PLAN_DETAIL.STORE_NUM
     */
    public BigDecimal getStoreNum() {
        return storeNum;
    }

    /**
     * @param storeNum ： T_BD_PLAN_DETAIL.STORE_NUM
     */
    public void setStoreNum(BigDecimal storeNum) {
        this.storeNum = storeNum;
    }

    /**
     * @return  T_BD_PLAN_DETAIL.PLAN_NUM
     */
    public Long getPlanNum() {
        return planNum;
    }

    /**
     * @param planNum ： T_BD_PLAN_DETAIL.PLAN_NUM
     */
    public void setPlanNum(Long planNum) {
        this.planNum = planNum;
    }

    /**
     * @return  T_BD_PLAN_DETAIL.INCREASES
     */
    public String getIncreases() {
        return increases;
    }

    /**
     * @param increases ： T_BD_PLAN_DETAIL.INCREASES
     */
    public void setIncreases(String increases) {
        this.increases = increases == null ? null : increases.trim();
    }
    private String orgName;
    /**
     * @return  T_BD_STORE.ORG_NAME
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName ： T_BD_STORE.ORG_NAME
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }
    /**
     * @return  T_BD_PLAN.PLAN_ORGNO
     */
    public String getPlanOrgno() {
        return planOrgno;
    }

    /**
     * @param planOrgno ： T_BD_PLAN.PLAN_ORGNO
     */
    public void setPlanOrgno(String planOrgno) {
        this.planOrgno = planOrgno == null ? null : planOrgno.trim();
    }

    /**
     * @return  T_BD_PLAN.PLAN_ORGNAME
     */
    public String getPlanOrgname() {
        return planOrgname;
    }

    /**
     * @param planOrgname ： T_BD_PLAN.PLAN_ORGNAME
     */
    public void setPlanOrgname(String planOrgname) {
        this.planOrgname = planOrgname == null ? null : planOrgname.trim();
    }

    /**
     * @return  T_BD_PLAN.PLAN_NUMBER
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
}
