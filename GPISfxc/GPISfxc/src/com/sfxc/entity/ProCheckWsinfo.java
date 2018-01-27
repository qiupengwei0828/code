package com.sfxc.entity;

import java.math.BigDecimal;
/**
 * 
 * @author Panwf
 * @date 2016年7月4日
 * @since:
 */
public class ProCheckWsinfo {
    private BigDecimal keyid;

    private String xh;

    private String wjmc;

    private String wjlx;

    private String queryId;

    private String filepath;

    /**
     * @return  PRO_CHECK_WSINFO.KEYID
     */
    public BigDecimal getKeyid() {
        return keyid;
    }

    /**
     * @param keyid ： PRO_CHECK_WSINFO.KEYID
     */
    public void setKeyid(BigDecimal keyid) {
        this.keyid = keyid;
    }

    /**
     * @return  PRO_CHECK_WSINFO.XH
     */
    public String getXh() {
        return xh;
    }

    /**
     * @param xh ： PRO_CHECK_WSINFO.XH
     */
    public void setXh(String xh) {
        this.xh = xh == null ? null : xh.trim();
    }

    /**
     * @return  PRO_CHECK_WSINFO.WJMC
     */
    public String getWjmc() {
        return wjmc;
    }

    /**
     * @param wjmc ： PRO_CHECK_WSINFO.WJMC
     */
    public void setWjmc(String wjmc) {
        this.wjmc = wjmc == null ? null : wjmc.trim();
    }

    /**
     * @return  PRO_CHECK_WSINFO.WJLX
     */
    public String getWjlx() {
        return wjlx;
    }

    /**
     * @param wjlx ： PRO_CHECK_WSINFO.WJLX
     */
    public void setWjlx(String wjlx) {
        this.wjlx = wjlx == null ? null : wjlx.trim();
    }

    /**
     * @return  PRO_CHECK_WSINFO.QUERY_ID
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * @param queryId ： PRO_CHECK_WSINFO.QUERY_ID
     */
    public void setQueryId(String queryId) {
        this.queryId = queryId == null ? null : queryId.trim();
    }

    /**
     * @return  PRO_CHECK_WSINFO.FILEPATH
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath ： PRO_CHECK_WSINFO.FILEPATH
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }
}