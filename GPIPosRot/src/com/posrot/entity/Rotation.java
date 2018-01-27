package com.posrot.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("Rotation")
public class Rotation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String rotateNo;
	private String rotUserId;
	private String rotOrgNo;
	private String rotPos;
	private String toPos;
	private String toOrgNo;
	private String toDate;
	private String repUserId;
	private String repOrgNo;
	private String repPos;
	private String hanDate;
	private String creUserId;
	private String creDate;
	private String status;
	private String remark;

	// 只作为传值
	private String rotUserName;
	private String rotOrgName;
	private String rotPosName;

	private String toOrgName;
	private String toPosName;

	private String repUserName;// 顶岗人姓名
	private String repOrgName;// 顶岗人机构名称
	private String repPosName;// 顶岗人机构名称
	private String creUserName;// 计划制定人

	private String statusNum;// 当前状态数量

	private Long planId;

	private String actionCode;

	private String opeOpinion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRotateNo() {
		return rotateNo;
	}

	public void setRotateNo(String rotateNo) {
		this.rotateNo = rotateNo;
	}

	public String getRotUserId() {
		return rotUserId;
	}

	public void setRotUserId(String rotUserId) {
		this.rotUserId = rotUserId;
	}

	public String getRotOrgNo() {
		return rotOrgNo;
	}

	public void setRotOrgNo(String rotOrgNo) {
		this.rotOrgNo = rotOrgNo;
	}

	public String getRotPos() {
		return rotPos;
	}

	public void setRotPos(String rotPos) {
		this.rotPos = rotPos;
	}

	public String getToPos() {
		return toPos;
	}

	public void setToPos(String toPos) {
		this.toPos = toPos;
	}

	public String getToOrgNo() {
		return toOrgNo;
	}

	public void setToOrgNo(String toOrgNo) {
		this.toOrgNo = toOrgNo;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getRepUserId() {
		return repUserId;
	}

	public void setRepUserId(String repUserId) {
		this.repUserId = repUserId;
	}

	public String getRepOrgNo() {
		return repOrgNo;
	}

	public void setRepOrgNo(String repOrgNo) {
		this.repOrgNo = repOrgNo;
	}

	public String getRepPos() {
		return repPos;
	}

	public void setRepPos(String repPos) {
		this.repPos = repPos;
	}

	public String getHanDate() {
		return hanDate;
	}

	public void setHanDate(String hanDate) {
		this.hanDate = hanDate;
	}

	public String getCreUserId() {
		return creUserId;
	}

	public void setCreUserId(String creUserId) {
		this.creUserId = creUserId;
	}

	public String getCreDate() {
		return creDate;
	}

	public void setCreDate(String creDate) {
		this.creDate = creDate;
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

	public String getRotUserName() {
		return rotUserName;
	}

	public void setRotUserName(String rotUserName) {
		this.rotUserName = rotUserName;
	}

	public String getRotOrgName() {
		return rotOrgName;
	}

	public void setRotOrgName(String rotOrgName) {
		this.rotOrgName = rotOrgName;
	}

	public String getRotPosName() {
		return rotPosName;
	}

	public void setRotPosName(String rotPosName) {
		this.rotPosName = rotPosName;
	}

	public String getToOrgName() {
		return toOrgName;
	}

	public void setToOrgName(String toOrgName) {
		this.toOrgName = toOrgName;
	}

	public String getToPosName() {
		return toPosName;
	}

	public void setToPosName(String toPosName) {
		this.toPosName = toPosName;
	}

	public String getRepUserName() {
		return repUserName;
	}

	public void setRepUserName(String repUserName) {
		this.repUserName = repUserName;
	}

	public String getRepOrgName() {
		return repOrgName;
	}

	public void setRepOrgName(String repOrgName) {
		this.repOrgName = repOrgName;
	}

	public String getRepPosName() {
		return repPosName;
	}

	public void setRepPosName(String repPosName) {
		this.repPosName = repPosName;
	}

	public String getCreUserName() {
		return creUserName;
	}

	public void setCreUserName(String creUserName) {
		this.creUserName = creUserName;
	}

	public String getStatusNum() {
		return statusNum;
	}

	public void setStatusNum(String statusNum) {
		this.statusNum = statusNum;
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

	public String getOpeOpinion() {
		return opeOpinion;
	}

	public void setOpeOpinion(String opeOpinion) {
		this.opeOpinion = opeOpinion;
	}

	public Rotation() {
		super();
	}

}