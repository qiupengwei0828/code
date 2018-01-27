package com.sfxc.entity;

import java.math.BigDecimal;
/**
 * 网银登录日志信息
 * @author Panwf
 * @date 2016年7月5日
 * @since:
 */
public class ProResultDlrzxx {
    private BigDecimal keyid;

    private String bdhm;

    private Long rzxh;

    private String dlsj;

    private String khzh;

    private String dldz;

    private String dlcz;

    private String dlms;

    private String fksj;

    private String provider;

    private String providetime;

    /**
     * @return  PRO_RESULT_DLRZXX.KEYID
     */
    public BigDecimal getKeyid() {
        return keyid;
    }

    /**
     * @param keyid ： PRO_RESULT_DLRZXX.KEYID
     */
    public void setKeyid(BigDecimal keyid) {
        this.keyid = keyid;
    }

    /**
     * @return  PRO_RESULT_DLRZXX.BDHM
     */
    public String getBdhm() {
        return bdhm;
    }

    /**
     * @param bdhm ： PRO_RESULT_DLRZXX.BDHM
     */
    public void setBdhm(String bdhm) {
        this.bdhm = bdhm == null ? null : bdhm.trim();
    }

    /**
     * @return  PRO_RESULT_DLRZXX.RZXH
     */
    public Long getRzxh() {
        return rzxh;
    }

    /**
     * @param rzxh ： PRO_RESULT_DLRZXX.RZXH
     */
    public void setRzxh(Long rzxh) {
        this.rzxh = rzxh;
    }

    /**
     * @return  PRO_RESULT_DLRZXX.DLSJ
     */
    public String getDlsj() {
        return dlsj;
    }

    /**
     * @param dlsj ： PRO_RESULT_DLRZXX.DLSJ
     */
    public void setDlsj(String dlsj) {
        this.dlsj = dlsj == null ? null : dlsj.trim();
    }

    /**
     * @return  PRO_RESULT_DLRZXX.KHZH
     */
    public String getKhzh() {
        return khzh;
    }

    /**
     * @param khzh ： PRO_RESULT_DLRZXX.KHZH
     */
    public void setKhzh(String khzh) {
        this.khzh = khzh == null ? null : khzh.trim();
    }

    /**
     * @return  PRO_RESULT_DLRZXX.DLDZ
     */
    public String getDldz() {
        return dldz;
    }

    /**
     * @param dldz ： PRO_RESULT_DLRZXX.DLDZ
     */
    public void setDldz(String dldz) {
        this.dldz = dldz == null ? null : dldz.trim();
    }

    /**
     * @return  PRO_RESULT_DLRZXX.DLCZ
     */
    public String getDlcz() {
        return dlcz;
    }

    /**
     * @param dlcz ： PRO_RESULT_DLRZXX.DLCZ
     */
    public void setDlcz(String dlcz) {
        this.dlcz = dlcz == null ? null : dlcz.trim();
    }

    /**
     * @return  PRO_RESULT_DLRZXX.DLMS
     */
    public String getDlms() {
        return dlms;
    }

    /**
     * @param dlms ： PRO_RESULT_DLRZXX.DLMS
     */
    public void setDlms(String dlms) {
        this.dlms = dlms == null ? null : dlms.trim();
    }

    /**
     * @return  PRO_RESULT_DLRZXX.FKSJ
     */
    public String getFksj() {
        return fksj;
    }

    /**
     * @param fksj ： PRO_RESULT_DLRZXX.FKSJ
     */
    public void setFksj(String fksj) {
        this.fksj = fksj == null ? null : fksj.trim();
    }

    /**
     * @return  PRO_RESULT_DLRZXX.PROVIDER
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider ： PRO_RESULT_DLRZXX.PROVIDER
     */
    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    /**
     * @return  PRO_RESULT_DLRZXX.PROVIDETIME
     */
    public String getProvidetime() {
        return providetime;
    }

    /**
     * @param providetime ： PRO_RESULT_DLRZXX.PROVIDETIME
     */
    public void setProvidetime(String providetime) {
        this.providetime = providetime == null ? null : providetime.trim();
    }
}