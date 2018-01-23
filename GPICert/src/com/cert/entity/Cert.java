package com.cert.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("Cert")
public class Cert extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String certCode;

	private String certName;

	/* 银行、证券、基金、期货、会计、统计、外语、保险、…… */
	private String industry;

	private String certUnit;

	/* 职业资格、从业资格、岗位资格、技能鉴定…… */
	private String admClass;

	/* 年 */
	private String usefulLife;

	private String remark;
	/* 1.可用，2.禁用 */
	private String status;

	/* DISP FIELD* */
	private String codeName;

	/* posCode */
	private String posCode;

	/**
	 * @return T_CERT_INFO.cert_code
	 */
	public String getCertCode() {
		return certCode;
	}

	/**
	 * @param certCode
	 *            ： T_CERT_INFO.cert_code
	 */
	public void setCertCode(String certCode) {
		this.certCode = certCode == null ? null : certCode.trim();
	}

	/**
	 * @return T_CERT_INFO.cert_name
	 */
	public String getCertName() {
		return certName;
	}

	/**
	 * @param certName
	 *            ： T_CERT_INFO.cert_name
	 */
	public void setCertName(String certName) {
		this.certName = certName == null ? null : certName.trim();
	}

	/**
	 * @return T_CERT_INFO.Industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            ： T_CERT_INFO.Industry
	 */
	public void setIndustry(String industry) {
		this.industry = industry == null ? null : industry.trim();
	}

	/**
	 * @return T_CERT_INFO.cert_unit
	 */
	public String getCertUnit() {
		return certUnit;
	}

	/**
	 * @param certUnit
	 *            ： T_CERT_INFO.cert_unit
	 */
	public void setCertUnit(String certUnit) {
		this.certUnit = certUnit == null ? null : certUnit.trim();
	}

	/**
	 * @return T_CERT_INFO.adm_class
	 */
	public String getAdmClass() {
		return admClass;
	}

	/**
	 * @param admClass
	 *            ： T_CERT_INFO.adm_class
	 */
	public void setAdmClass(String admClass) {
		this.admClass = admClass == null ? null : admClass.trim();
	}

	/**
	 * @return T_CERT_INFO.useful_life
	 */
	public String getUsefulLife() {
		return usefulLife;
	}

	/**
	 * @param usefulLife
	 *            ： T_CERT_INFO.useful_life
	 */
	public void setUsefulLife(String usefulLife) {
		this.usefulLife = usefulLife == null ? null : usefulLife.trim();
	}

	/**
	 * @return T_CERT_INFO.remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            ： T_CERT_INFO.remark
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

}