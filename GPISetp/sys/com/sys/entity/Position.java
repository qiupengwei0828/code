package com.sys.entity;

public class Position {
    private String posCode;

    private String posName;

    private String remark;

    private String status;

    /**
     * @return  T_SYS_POSITION_INFO.POS_CODE
     */
    public String getPosCode() {
        return posCode;
    }

    /**
     * @param posCode ： T_SYS_POSITION_INFO.POS_CODE
     */
    public void setPosCode(String posCode) {
        this.posCode = posCode == null ? null : posCode.trim();
    }

    /**
     * @return  T_SYS_POSITION_INFO.POS_NAME
     */
    public String getPosName() {
        return posName;
    }

    /**
     * @param posName ： T_SYS_POSITION_INFO.POS_NAME
     */
    public void setPosName(String posName) {
        this.posName = posName == null ? null : posName.trim();
    }

    /**
     * @return  T_SYS_POSITION_INFO.REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark ： T_SYS_POSITION_INFO.REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return  T_SYS_POSITION_INFO.STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status ： T_SYS_POSITION_INFO.STATUS
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}