package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;


/**
 * 字典类型表
 */

@Alias("DicType")
public class DicType extends BaseEntity{
    /** @Fields serialVersionUID :           */
	private static final long serialVersionUID = 1L;

	private String typeCode;

    private String typeName;

    private String status;

    private String crtUser;

    private String crtTime;

    private String pcode;

    /**
     * @return  T_SYS_DIC_TYPE.TYPE_CODE
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * @param typeCode ： T_SYS_DIC_TYPE.TYPE_CODE
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * @return  T_SYS_DIC_TYPE.TYPE_NAME
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName ： T_SYS_DIC_TYPE.TYPE_NAME
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * @return  T_SYS_DIC_TYPE.STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status ： T_SYS_DIC_TYPE.STATUS
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return  T_SYS_DIC_TYPE.CRT_USER
     */
    public String getCrtUser() {
        return crtUser;
    }

    /**
     * @param crtUser ： T_SYS_DIC_TYPE.CRT_USER
     */
    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser == null ? null : crtUser.trim();
    }

    /**
     * @return  T_SYS_DIC_TYPE.CRT_TIME
     */
    public String getCrtTime() {
        return crtTime;
    }

    /**
     * @param crtTime ： T_SYS_DIC_TYPE.CRT_TIME
     */
    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime == null ? null : crtTime.trim();
    }

    /**
     * @return  T_SYS_DIC_TYPE.PCODE
     */
    public String getPcode() {
        return pcode;
    }

    /**
     * @param pcode ： T_SYS_DIC_TYPE.PCODE
     */
    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }
}