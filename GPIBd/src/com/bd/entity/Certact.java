package com.bd.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("Certact")
public class Certact extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 单证CODE */
    private String certactCode;

    /** 单证名称 */
    private String certactName;

    private String certactType;

    /** 状态:1 有效；2无效 */
    private String status;

    private Short entryDays;

    /**
     * @return  T_BD_CERTACT.CERTACT_CODE
     */
    public String getCertactCode() {
        return certactCode;
    }

    /**
     * @param certactCode ： T_BD_CERTACT.CERTACT_CODE
     */
    public void setCertactCode(String certactCode) {
        this.certactCode = certactCode == null ? null : certactCode.trim();
    }

    /**
     * @return  T_BD_CERTACT.CERTACT_NAME
     */
    public String getCertactName() {
        return certactName;
    }

    /**
     * @param certactName ： T_BD_CERTACT.CERTACT_NAME
     */
    public void setCertactName(String certactName) {
        this.certactName = certactName == null ? null : certactName.trim();
    }

    /**
     * @return  T_BD_CERTACT.CERTACT_TYPE
     */
    public String getCertactType() {
        return certactType;
    }

    /**
     * @param certactType ： T_BD_CERTACT.CERTACT_TYPE
     */
    public void setCertactType(String certactType) {
        this.certactType = certactType == null ? null : certactType.trim();
    }

    /**
     * @return  T_BD_CERTACT.STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status ： T_BD_CERTACT.STATUS
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return  T_BD_CERTACT.ENTRY_DAYS
     */
    public Short getEntryDays() {
        return entryDays;
    }

    /**
     * @param entryDays ： T_BD_CERTACT.ENTRY_DAYS
     */
    public void setEntryDays(Short entryDays) {
        this.entryDays = entryDays;
    }
}