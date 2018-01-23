package com.bd.entity;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("StoreLog")
public class StoreLog extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal id;

    private BigDecimal storeId;

    /** 机构 */
    private String orgNo;

    private String certactCode;

    /** 出库 */
    private Long outNum;

    /** 入库 */
    private Long inNum;

    /** 操作时间 */
    private String changeTime;

    /** 操作员ID */
    private String userId;

    /** 备注 */
    private String remark;

    private String orgName;

    private String certactName;

    /**
     * @return  T_BD_STORE_LOG.ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id ： T_BD_STORE_LOG.ID
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return  T_BD_STORE_LOG.STORE_ID
     */
    public BigDecimal getStoreId() {
        return storeId;
    }

    /**
     * @param storeId ： T_BD_STORE_LOG.STORE_ID
     */
    public void setStoreId(BigDecimal storeId) {
        this.storeId = storeId;
    }

    /**
     * @return  T_BD_STORE_LOG.ORG_NO
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * @param orgNo ： T_BD_STORE_LOG.ORG_NO
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    /**
     * @return  T_BD_STORE_LOG.CERTACT_CODE
     */
    public String getCertactCode() {
        return certactCode;
    }

    /**
     * @param certactCode ： T_BD_STORE_LOG.CERTACT_CODE
     */
    public void setCertactCode(String certactCode) {
        this.certactCode = certactCode == null ? null : certactCode.trim();
    }

    /**
     * @return  T_BD_STORE_LOG.OUT_NUM
     */
    public Long getOutNum() {
        return outNum;
    }

    /**
     * @param outNum ： T_BD_STORE_LOG.OUT_NUM
     */
    public void setOutNum(Long outNum) {
        this.outNum = outNum;
    }

    /**
     * @return  T_BD_STORE_LOG.IN_NUM
     */
    public Long getInNum() {
        return inNum;
    }

    /**
     * @param inNum ： T_BD_STORE_LOG.IN_NUM
     */
    public void setInNum(Long inNum) {
        this.inNum = inNum;
    }

    /**
     * @return  T_BD_STORE_LOG.CHANGE_TIME
     */
    public String getChangeTime() {
        return changeTime;
    }

    /**
     * @param changeTime ： T_BD_STORE_LOG.CHANGE_TIME
     */
    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime == null ? null : changeTime.trim();
    }

    /**
     * @return  T_BD_STORE_LOG.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId ： T_BD_STORE_LOG.USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return  T_BD_STORE_LOG.REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark ： T_BD_STORE_LOG.REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return  T_BD_STORE_LOG.ORG_NAME
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName ： T_BD_STORE_LOG.ORG_NAME
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * @return  T_BD_STORE_LOG.CERTACT_NAME
     */
    public String getCertactName() {
        return certactName;
    }

    /**
     * @param certactName ： T_BD_STORE_LOG.CERTACT_NAME
     */
    public void setCertactName(String certactName) {
        this.certactName = certactName == null ? null : certactName.trim();
    }
}