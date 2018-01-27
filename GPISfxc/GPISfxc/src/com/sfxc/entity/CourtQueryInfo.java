package com.sfxc.entity;

public class CourtQueryInfo {
    private String queryId;

    private String bankType;

    private String queryQu;

    private String queryName;

    private String nationArea;

    private String certType;

    private String certNo;

    private String issuctfAhrLo;

    private String courtName;

    private String judgeName;

    private String caseId;

    private String startDt;

    private String endDt;
    
    private String status;
    
    private String remark;
    
    

    /**
     * @return  COURT_QUERY_INFO.QUERY_ID
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * @param queryId ： COURT_QUERY_INFO.QUERY_ID
     */
    public void setQueryId(String queryId) {
        this.queryId = queryId == null ? null : queryId.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.BANK_TYPE
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * @param bankType ： COURT_QUERY_INFO.BANK_TYPE
     */
    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.QUERY_QU
     */
    public String getQueryQu() {
        return queryQu;
    }

    /**
     * @param queryQu ： COURT_QUERY_INFO.QUERY_QU
     */
    public void setQueryQu(String queryQu) {
        this.queryQu = queryQu == null ? null : queryQu.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.QUERY_NAME
     */
    public String getQueryName() {
        return queryName;
    }

    /**
     * @param queryName ： COURT_QUERY_INFO.QUERY_NAME
     */
    public void setQueryName(String queryName) {
        this.queryName = queryName == null ? null : queryName.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.NATION_AREA
     */
    public String getNationArea() {
        return nationArea;
    }

    /**
     * @param nationArea ： COURT_QUERY_INFO.NATION_AREA
     */
    public void setNationArea(String nationArea) {
        this.nationArea = nationArea == null ? null : nationArea.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.CERT_TYPE
     */
    public String getCertType() {
        return certType;
    }

    /**
     * @param certType ： COURT_QUERY_INFO.CERT_TYPE
     */
    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.CERT_NO
     */
    public String getCertNo() {
        return certNo;
    }

    /**
     * @param certNo ： COURT_QUERY_INFO.CERT_NO
     */
    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.ISSUCTF_AHR_LO
     */
    public String getIssuctfAhrLo() {
        return issuctfAhrLo;
    }

    /**
     * @param issuctfAhrLo ： COURT_QUERY_INFO.ISSUCTF_AHR_LO
     */
    public void setIssuctfAhrLo(String issuctfAhrLo) {
        this.issuctfAhrLo = issuctfAhrLo == null ? null : issuctfAhrLo.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.COURT_NAME
     */
    public String getCourtName() {
        return courtName;
    }

    /**
     * @param courtName ： COURT_QUERY_INFO.COURT_NAME
     */
    public void setCourtName(String courtName) {
        this.courtName = courtName == null ? null : courtName.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.JUDGE_NAME
     */
    public String getJudgeName() {
        return judgeName;
    }

    /**
     * @param judgeName ： COURT_QUERY_INFO.JUDGE_NAME
     */
    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName == null ? null : judgeName.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.CASE_ID
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * @param caseId ： COURT_QUERY_INFO.CASE_ID
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.START_DT
     */
    public String getStartDt() {
        return startDt;
    }

    /**
     * @param startDt ： COURT_QUERY_INFO.START_DT
     */
    public void setStartDt(String startDt) {
        this.startDt = startDt == null ? null : startDt.trim();
    }

    /**
     * @return  COURT_QUERY_INFO.END_DT
     */
    public String getEndDt() {
        return endDt;
    }

    /**
     * @param endDt ： COURT_QUERY_INFO.END_DT
     */
    public void setEndDt(String endDt) {
        this.endDt = endDt == null ? null : endDt.trim();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}