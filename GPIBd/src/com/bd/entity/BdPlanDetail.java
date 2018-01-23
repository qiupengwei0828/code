package com.bd.entity;

import java.math.BigDecimal;

public class BdPlanDetail {
    private BigDecimal id;

    private BigDecimal planId;

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
     * @return  T_BD_PLAN_DETAIL.ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id ： T_BD_PLAN_DETAIL.ID
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return  T_BD_PLAN_DETAIL.PLAN_ID
     */
    public BigDecimal getPlanId() {
        return planId;
    }

    /**
     * @param planId ： T_BD_PLAN_DETAIL.PLAN_ID
     */
    public void setPlanId(BigDecimal planId) {
        this.planId = planId;
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
}