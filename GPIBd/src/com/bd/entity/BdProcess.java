package com.bd.entity;

import java.math.BigDecimal;

public class BdProcess {
    private BigDecimal logId;

    private BigDecimal planNumber;

    private String orgNo;

    /** 流程到达该节点的时间 */
    private String  operatTime;

    private String nodeName;

    private String orgName;

    /** 操作人员ID */
    private String userId;

    private String operatResu;

    private String operatRemark;

    /**
     * @return  T_BD_PROCESS.LOG_ID
     */
    public BigDecimal getLogId() {
        return logId;
    }

    /**
     * @param logId ： T_BD_PROCESS.LOG_ID
     */
    public void setLogId(BigDecimal logId) {
        this.logId = logId;
    }

    /**
     * @return  T_BD_PROCESS.PLAN_NUMBER
     */
    public BigDecimal getPlanNumber() {
        return planNumber;
    }

    /**
     * @param planNumber ： T_BD_PROCESS.PLAN_NUMBER
     */
    public void setPlanNumber(BigDecimal planNumber) {
        this.planNumber = planNumber;
    }

    /**
     * @return  T_BD_PROCESS.ORG_NO
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * @param orgNo ： T_BD_PROCESS.ORG_NO
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    /**
     * @return  T_BD_PROCESS.OPERAT_TIME
     */
    public String getOperatTime() {
        return operatTime;
    }

    /**
     * @param operatTime ： T_BD_PROCESS.OPERAT_TIME
     */
    public void setOperatTime(String operatTime) {
        this.operatTime = operatTime == null ? null : operatTime.trim();
    }

    /**
     * @return  T_BD_PROCESS.NODE_NAME
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName ： T_BD_PROCESS.NODE_NAME
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    /**
     * @return  T_BD_PROCESS.ORG_NAME
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName ： T_BD_PROCESS.ORG_NAME
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * @return  T_BD_PROCESS.USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId ： T_BD_PROCESS.USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return  T_BD_PROCESS.OPERAT_RESU
     */
    public String getOperatResu() {
        return operatResu;
    }

    /**
     * @param operatResu ： T_BD_PROCESS.OPERAT_RESU
     */
    public void setOperatResu(String operatResu) {
        this.operatResu = operatResu == null ? null : operatResu.trim();
    }

    /**
     * @return  T_BD_PROCESS.OPERAT_REMARK
     */
    public String getOperatRemark() {
        return operatRemark;
    }

    /**
     * @param operatRemark ： T_BD_PROCESS.OPERAT_REMARK
     */
    public void setOperatRemark(String operatRemark) {
        this.operatRemark = operatRemark == null ? null : operatRemark.trim();
    }
}