package com.sfxc.entity;

import java.math.BigDecimal;

public class CourtDealInfo {
    private BigDecimal keyid;

    private String bdhm;

    private String receivedtime;

    private String receiver;

    private String examiner;

    private String examinetime;

    private String backer;

    private String backtime;

    private String dealstaus;
    
    private String remark;

    /**
     * @return  COURT_DEAL_INFO.KEYID
     */
    public BigDecimal getKeyid() {
        return keyid;
    }

    /**
     * @param keyid ： COURT_DEAL_INFO.KEYID
     */
    public void setKeyid(BigDecimal keyid) {
        this.keyid = keyid;
    }

    /**
     * @return  COURT_DEAL_INFO.BDHM
     */
    public String getBdhm() {
        return bdhm;
    }

    /**
     * @param bdhm ： COURT_DEAL_INFO.BDHM
     */
    public void setBdhm(String bdhm) {
        this.bdhm = bdhm == null ? null : bdhm.trim();
    }

    /**
     * @return  COURT_DEAL_INFO.RECEIVEDTIME
     */
    public String getReceivedtime() {
        return receivedtime;
    }

    /**
     * @param receivedtime ： COURT_DEAL_INFO.RECEIVEDTIME
     */
    public void setReceivedtime(String receivedtime) {
        this.receivedtime = receivedtime == null ? null : receivedtime.trim();
    }

    /**
     * @return  COURT_DEAL_INFO.RECEIVER
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @param receiver ： COURT_DEAL_INFO.RECEIVER
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    /**
     * @return  COURT_DEAL_INFO.EXAMINER
     */
    public String getExaminer() {
        return examiner;
    }

    /**
     * @param examiner ： COURT_DEAL_INFO.EXAMINER
     */
    public void setExaminer(String examiner) {
        this.examiner = examiner == null ? null : examiner.trim();
    }

    /**
     * @return  COURT_DEAL_INFO.EXAMINETIME
     */
    public String getExaminetime() {
        return examinetime;
    }

    /**
     * @param examinetime ： COURT_DEAL_INFO.EXAMINETIME
     */
    public void setExaminetime(String examinetime) {
        this.examinetime = examinetime == null ? null : examinetime.trim();
    }

    /**
     * @return  COURT_DEAL_INFO.BACKER
     */
    public String getBacker() {
        return backer;
    }

    /**
     * @param backer ： COURT_DEAL_INFO.BACKER
     */
    public void setBacker(String backer) {
        this.backer = backer == null ? null : backer.trim();
    }

    /**
     * @return  COURT_DEAL_INFO.BACKTIME
     */
    public String getBacktime() {
        return backtime;
    }

    /**
     * @param backtime ： COURT_DEAL_INFO.BACKTIME
     */
    public void setBacktime(String backtime) {
        this.backtime = backtime == null ? null : backtime.trim();
    }

    /**
     * @return  COURT_DEAL_INFO.DEALSTAUS
     */
    public String getDealstaus() {
        return dealstaus;
    }

    /**
     * @param dealstaus ： COURT_DEAL_INFO.DEALSTAUS
     */
    public void setDealstaus(String dealstaus) {
        this.dealstaus = dealstaus == null ? null : dealstaus.trim();
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}