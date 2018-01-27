package com.sfxc.entity;

import java.math.BigDecimal;

public class CourtResultGlzhxx {
    private BigDecimal id;

    private String bdhm;

    private Long ccxh;

    private Long glxh;

    private String glzhlb;

    private String glzhhm;

    private String glzhmc;

    private String provider;

    private String providetime;

    /**
     * @return  COURT_RESULT_GLZHXX.ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id ： COURT_RESULT_GLZHXX.ID
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return  COURT_RESULT_GLZHXX.BDHM
     */
    public String getBdhm() {
        return bdhm;
    }

    /**
     * @param bdhm ： COURT_RESULT_GLZHXX.BDHM
     */
    public void setBdhm(String bdhm) {
        this.bdhm = bdhm == null ? null : bdhm.trim();
    }

    /**
     * @return  COURT_RESULT_GLZHXX.CCXH
     */
    public Long getCcxh() {
        return ccxh;
    }

    /**
     * @param ccxh ： COURT_RESULT_GLZHXX.CCXH
     */
    public void setCcxh(Long ccxh) {
        this.ccxh = ccxh;
    }

    /**
     * @return  COURT_RESULT_GLZHXX.GLXH
     */
    public Long getGlxh() {
        return glxh;
    }

    /**
     * @param glxh ： COURT_RESULT_GLZHXX.GLXH
     */
    public void setGlxh(Long glxh) {
        this.glxh = glxh;
    }

    /**
     * @return  COURT_RESULT_GLZHXX.GLZHLB
     */
    public String getGlzhlb() {
        return glzhlb;
    }

    /**
     * @param glzhlb ： COURT_RESULT_GLZHXX.GLZHLB
     */
    public void setGlzhlb(String glzhlb) {
        this.glzhlb = glzhlb == null ? null : glzhlb.trim();
    }

    /**
     * @return  COURT_RESULT_GLZHXX.GLZHHM
     */
    public String getGlzhhm() {
        return glzhhm;
    }

    /**
     * @param glzhhm ： COURT_RESULT_GLZHXX.GLZHHM
     */
    public void setGlzhhm(String glzhhm) {
        this.glzhhm = glzhhm == null ? null : glzhhm.trim();
    }

    /**
     * @return  COURT_RESULT_GLZHXX.GLZHMC
     */
    public String getGlzhmc() {
        return glzhmc;
    }

    /**
     * @param glzhmc ： COURT_RESULT_GLZHXX.GLZHMC
     */
    public void setGlzhmc(String glzhmc) {
        this.glzhmc = glzhmc == null ? null : glzhmc.trim();
    }

    /**
     * @return  COURT_RESULT_GLZHXX.PROVIDER
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider ： COURT_RESULT_GLZHXX.PROVIDER
     */
    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    /**
     * @return  COURT_RESULT_GLZHXX.PROVIDETIME
     */
    public String getProvidetime() {
        return providetime;
    }

    /**
     * @param providetime ： COURT_RESULT_GLZHXX.PROVIDETIME
     */
    public void setProvidetime(String providetime) {
        this.providetime = providetime == null ? null : providetime.trim();
    }
}