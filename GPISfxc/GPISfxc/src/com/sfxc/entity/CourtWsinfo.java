package com.sfxc.entity;

import java.math.BigDecimal;

public class CourtWsinfo {
    private BigDecimal keyid;

    private String queryId;

    private String resultmsg;

    private String msg;

    /**
     * @return  COURT_WSINFO.KEYID
     */
    public BigDecimal getKeyid() {
        return keyid;
    }

    /**
     * @param keyid ： COURT_WSINFO.KEYID
     */
    public void setKeyid(BigDecimal keyid) {
        this.keyid = keyid;
    }

    /**
     * @return  COURT_WSINFO.QUERY_ID
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * @param queryId ： COURT_WSINFO.QUERY_ID
     */
    public void setQueryId(String queryId) {
        this.queryId = queryId == null ? null : queryId.trim();
    }

    /**
     * @return  COURT_WSINFO.RESULTMSG
     */
    public String getResultmsg() {
        return resultmsg;
    }

    /**
     * @param resultmsg ： COURT_WSINFO.RESULTMSG
     */
    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg == null ? null : resultmsg.trim();
    }

    /**
     * @return  COURT_WSINFO.MSG
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg ： COURT_WSINFO.MSG
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }
}