package com.sfxc.entity;

import java.math.BigDecimal;

public class CourtCheckWsinfo {
    private BigDecimal keyid;

    private String xh;

    private String wjmc;

    private String wjlx;

    private String queryId;

    /**
     * @return  COURT_CHECK_WSINFO.KEYID
     */
    public BigDecimal getKeyid() {
        return keyid;
    }

    /**
     * @param keyid ： COURT_CHECK_WSINFO.KEYID
     */
    public void setKeyid(BigDecimal keyid) {
        this.keyid = keyid;
    }

    /**
     * @return  COURT_CHECK_WSINFO.XH
     */
    public String getXh() {
        return xh;
    }

    /**
     * @param xh ： COURT_CHECK_WSINFO.XH
     */
    public void setXh(String xh) {
        this.xh = xh == null ? null : xh.trim();
    }

    /**
     * @return  COURT_CHECK_WSINFO.WJMC
     */
    public String getWjmc() {
        return wjmc;
    }

    /**
     * @param wjmc ： COURT_CHECK_WSINFO.WJMC
     */
    public void setWjmc(String wjmc) {
        this.wjmc = wjmc == null ? null : wjmc.trim();
    }

    /**
     * @return  COURT_CHECK_WSINFO.WJLX
     */
    public String getWjlx() {
        return wjlx;
    }

    /**
     * @param wjlx ： COURT_CHECK_WSINFO.WJLX
     */
    public void setWjlx(String wjlx) {
        this.wjlx = wjlx == null ? null : wjlx.trim();
    }

    /**
     * @return  COURT_CHECK_WSINFO.QUERY_ID
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * @param queryId ： COURT_CHECK_WSINFO.QUERY_ID
     */
    public void setQueryId(String queryId) {
        this.queryId = queryId == null ? null : queryId.trim();
    }
}