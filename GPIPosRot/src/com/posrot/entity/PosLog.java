package com.posrot.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("PosLog")
public class PosLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String proCode;
	private String proName;
	private Long planId;
	private String actionCode;
	private String actionName;
	private String opeOpinion;
	private String opeContent;
	private String opeResult;
	private String opeUserId;
	private String opeUserName;
	private String opeDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getOpeOpinion() {
		return opeOpinion;
	}

	public void setOpeOpinion(String opeOpinion) {
		this.opeOpinion = opeOpinion;
	}

	public String getOpeContent() {
		return opeContent;
	}

	public void setOpeContent(String opeContent) {
		this.opeContent = opeContent;
	}

	public String getOpeResult() {
		return opeResult;
	}

	public void setOpeResult(String opeResult) {
		this.opeResult = opeResult;
	}

	public String getOpeUserId() {
		return opeUserId;
	}

	public void setOpeUserId(String opeUserId) {
		this.opeUserId = opeUserId;
	}

	public String getOpeUserName() {
		return opeUserName;
	}

	public void setOpeUserName(String opeUserName) {
		this.opeUserName = opeUserName;
	}

	public String getOpeDate() {
		return opeDate;
	}

	public void setOpeDate(String opeDate) {
		this.opeDate = opeDate;
	}

	public PosLog() {
		super();
	}

}
