package com.bd.entity;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("Store")
public class Store extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal id;
	private Long storenumber;

    /** 机构代码 */
    private String orgNo;
    private String orgName;

    /** 1:尾箱存量;  2:单位库存 */
    private String repType;
    private String certactType;

    private String certactCode;

    private String certactName;

    /** 库存数量 */
    private Long storeNum;
    

    private String certactStore;

    private String certactSplit;

    private String businessScope;

    /** 在库状态 */
    private String status;

    private String remark;

    

    /**
     * @return  T_BD_STORE.ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id ： T_BD_STORE.ID
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return  T_BD_STORE.ORG_NO
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * @param orgNo ： T_BD_STORE.ORG_NO
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    /**
     * @return  T_BD_STORE.REP_TYPE
     */
    public String getRepType() {
        return repType;
    }

    /**
     * @param repType ： T_BD_STORE.REP_TYPE
     */
    public void setRepType(String repType) {
        this.repType = repType == null ? null : repType.trim();
    }

    /**
     * @return  T_BD_STORE.CERTACT_CODE
     */
    public String getCertactCode() {
        return certactCode;
    }

    /**
     * @param certactCode ： T_BD_STORE.CERTACT_CODE
     */
    public void setCertactCode(String certactCode) {
        this.certactCode = certactCode == null ? null : certactCode.trim();
    }

    /**
     * @return  T_BD_STORE.CERTACT_NAME
     */
    public String getCertactName() {
        return certactName;
    }

    /**
     * @param certactName ： T_BD_STORE.CERTACT_NAME
     */
    public void setCertactName(String certactName) {
        this.certactName = certactName == null ? null : certactName.trim();
    }

    /**
     * @return  T_BD_STORE.STORE_NUM
     */
    public Long getStoreNum() {
        return storeNum;
    }

    /**
     * @param storeNum ： T_BD_STORE.STORE_NUM
     */
    public void setStoreNum(Long storeNum) {
        this.storeNum = storeNum;
    }

    /**
     * @return  T_BD_STORE.CERTACT_STORE
     */
    public String getCertactStore() {
        return certactStore;
    }

    /**
     * @param certactStore ： T_BD_STORE.CERTACT_STORE
     */
    public void setCertactStore(String certactStore) {
        this.certactStore = certactStore == null ? null : certactStore.trim();
    }

    /**
     * @return  T_BD_STORE.CERTACT_SPLIT
     */
    public String getCertactSplit() {
        return certactSplit;
    }

    /**
     * @param certactSplit ： T_BD_STORE.CERTACT_SPLIT
     */
    public void setCertactSplit(String certactSplit) {
        this.certactSplit = certactSplit == null ? null : certactSplit.trim();
    }

    /**
     * @return  T_BD_STORE.BUSINESS_SCOPE
     */
    public String getBusinessScope() {
        return businessScope;
    }

    /**
     * @param businessScope ： T_BD_STORE.BUSINESS_SCOPE
     */
    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    /**
     * @return  T_BD_STORE.STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status ： T_BD_STORE.STATUS
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return  T_BD_STORE.REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark ： T_BD_STORE.REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

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
     * @return  T_BD_STORE.CERTACT_TYPE
     */
    public String getCertactType() {
        return certactType;
    }

    /**
     * @param certactType ： T_BD_STORE.CERTACT_TYPE
     */
    public void setCertactType(String certactType) {
        this.certactType = certactType == null ? null : certactType.trim();
    }

	public Long getStorenumber() {
		return storenumber;
	}

	public void setStorenumber(Long storenumber) {
		this.storenumber = storenumber;
	}
}