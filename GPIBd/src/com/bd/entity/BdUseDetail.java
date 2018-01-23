package com.bd.entity;

import java.math.BigDecimal;

public class BdUseDetail {
    private BigDecimal id;

    /** 机构ID-来自sys中的机构表 */
    private String orgNo;

    private String repCode;

    private BigDecimal storeId;

    /** 单证CODE */
    private String certactCode;

    /** 单证名称 */
    private String certactName;

    /** 1：业务办理，2：下发；3：调拨 */
    private String useType;

    /** 用量 */
    private BigDecimal useNum;

    /** 使用日期 */
    private String useDate;
    
    private String date;

    /** 操作人员ID-来自sys中的用户表 */
    private String userId;

    private String airRepCode;
    
    private String certactType;
    
    
    private String storeNum;

    /**
     * @return  T_BD_USE_DETAIL.ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id ： T_BD_USE_DETAIL.ID
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return  T_BD_USE_DETAIL.ORG_NO
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * @param orgNo ： T_BD_USE_DETAIL.ORG_NO
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    /**
     * @return  T_BD_USE_DETAIL.REP_CODE
     */
    public String getRepCode() {
        return repCode;
    }

    /**
     * @param repCode ： T_BD_USE_DETAIL.REP_CODE
     */
    public void setRepCode(String repCode) {
        this.repCode = repCode == null ? null : repCode.trim();
    }

    /**
     * @return  T_BD_USE_DETAIL.STORE_ID
     */
    public BigDecimal getStoreId() {
        return storeId;
    }

    /**
     * @param storeId ： T_BD_USE_DETAIL.STORE_ID
     */
    public void setStoreId(BigDecimal storeId) {
        this.storeId = storeId;
    }

    /**
     * @return  T_BD_USE_DETAIL.CERTACT_CODE
     */
    public String getCertactCode() {
        return certactCode;
    }

    /**
     * @param certactCode ： T_BD_USE_DETAIL.CERTACT_CODE
     */
    public void setCertactCode(String certactCode) {
        this.certactCode = certactCode == null ? null : certactCode.trim();
    }

    /**
     * @return  T_BD_USE_DETAIL.CERTACT_NAME
     */
    public String getCertactName() {
        return certactName;
    }

    /**
     * @param certactName ： T_BD_USE_DETAIL.CERTACT_NAME
     */
    public void setCertactName(String certactName) {
        this.certactName = certactName == null ? null : certactName.trim();
    }

    /**
     * @return  T_BD_USE_DETAIL.USE_TYPE
     */
    public String getUseType() {
        return useType;
    }

    /**
     * @param useType ： T_BD_USE_DETAIL.USE_TYPE
     */
    public void setUseType(String useType) {
        this.useType = useType == null ? null : useType.trim();
    }

    /**
     * @return  T_BD_USE_DETAIL.USE_NUM
     */
    public BigDecimal getUseNum() {
        return useNum;
    }

    /**
     * @param useNum ： T_BD_USE_DETAIL.USE_NUM
     */
    public void setUseNum(BigDecimal useNum) {
        this.useNum = useNum;
    }

    /**
     * @return  T_BD_USE_DETAIL.USE_DATE
     */
    public String getUseDate() {
        return useDate;
    }

    /**
     * @param useDate ： T_BD_USE_DETAIL.USE_DATE
     */
    public void setUseDate(String useDate) {
        this.useDate = useDate == null ? null : useDate.trim();
    }

    /**
     * @return  T_BD_USE_DETAIL.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId ： T_BD_USE_DETAIL.USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return  T_BD_USE_DETAIL.AIR_REP_CODE
     */
    public String getAirRepCode() {
        return airRepCode;
    }

    /**
     * @param airRepCode ： T_BD_USE_DETAIL.AIR_REP_CODE
     */
    public void setAirRepCode(String airRepCode) {
        this.airRepCode = airRepCode == null ? null : airRepCode.trim();
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

	public String getCertactType() {
		return certactType;
	}

	public void setCertactType(String certactType) {
		this.certactType = certactType;
	}

	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}