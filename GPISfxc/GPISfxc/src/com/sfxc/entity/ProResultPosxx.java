package com.sfxc.entity;

import java.math.BigDecimal;
/**
 * POS机商户信息
 * @author Panwf
 * @date 2016年7月5日
 * @since:
 */
public class ProResultPosxx {
    private BigDecimal keyid;

    private String bdhm;

    private String posxh;

    private String sh;

    private String khzh;

    private String dldz;

    private String txfs;

    private String txsj;

    private String hm;

    private String fksj;

    private String provider;

    private String providetime;

    /**
     * @return  PRO_RESULT_POSXX.KEYID
     */
    public BigDecimal getKeyid() {
        return keyid;
    }

    /**
     * @param keyid ： PRO_RESULT_POSXX.KEYID
     */
    public void setKeyid(BigDecimal keyid) {
        this.keyid = keyid;
    }

    /**
     * @return  PRO_RESULT_POSXX.BDHM
     */
    public String getBdhm() {
        return bdhm;
    }

    /**
     * @param bdhm ： PRO_RESULT_POSXX.BDHM
     */
    public void setBdhm(String bdhm) {
        this.bdhm = bdhm == null ? null : bdhm.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.POSXH
     */
    public String getPosxh() {
        return posxh;
    }

    /**
     * @param posxh ： PRO_RESULT_POSXX.POSXH
     */
    public void setPosxh(String posxh) {
        this.posxh = posxh == null ? null : posxh.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.SH
     */
    public String getSh() {
        return sh;
    }

    /**
     * @param sh ： PRO_RESULT_POSXX.SH
     */
    public void setSh(String sh) {
        this.sh = sh == null ? null : sh.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.KHZH
     */
    public String getKhzh() {
        return khzh;
    }

    /**
     * @param khzh ： PRO_RESULT_POSXX.KHZH
     */
    public void setKhzh(String khzh) {
        this.khzh = khzh == null ? null : khzh.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.DLDZ
     */
    public String getDldz() {
        return dldz;
    }

    /**
     * @param dldz ： PRO_RESULT_POSXX.DLDZ
     */
    public void setDldz(String dldz) {
        this.dldz = dldz == null ? null : dldz.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.TXFS
     */
    public String getTxfs() {
        return txfs;
    }

    /**
     * @param txfs ： PRO_RESULT_POSXX.TXFS
     */
    public void setTxfs(String txfs) {
        this.txfs = txfs == null ? null : txfs.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.TXSJ
     */
    public String getTxsj() {
        return txsj;
    }

    /**
     * @param txsj ： PRO_RESULT_POSXX.TXSJ
     */
    public void setTxsj(String txsj) {
        this.txsj = txsj == null ? null : txsj.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.HM
     */
    public String getHm() {
        return hm;
    }

    /**
     * @param hm ： PRO_RESULT_POSXX.HM
     */
    public void setHm(String hm) {
        this.hm = hm == null ? null : hm.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.FKSJ
     */
    public String getFksj() {
        return fksj;
    }

    /**
     * @param fksj ： PRO_RESULT_POSXX.FKSJ
     */
    public void setFksj(String fksj) {
        this.fksj = fksj == null ? null : fksj.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.PROVIDER
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider ： PRO_RESULT_POSXX.PROVIDER
     */
    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    /**
     * @return  PRO_RESULT_POSXX.PROVIDETIME
     */
    public String getProvidetime() {
        return providetime;
    }

    /**
     * @param providetime ： PRO_RESULT_POSXX.PROVIDETIME
     */
    public void setProvidetime(String providetime) {
        this.providetime = providetime == null ? null : providetime.trim();
    }
}