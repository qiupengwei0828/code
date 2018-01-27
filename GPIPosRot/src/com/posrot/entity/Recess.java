package com.posrot.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("Recess")
public class Recess extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String recessNo;
	private String recUserId;
	private String recOrgNo;
	private String recPos;

	private String recBeginDate;
	private String recEndDate;

	private String repUserId;
	private String repOrgNo;
	private String repPos;
	private String hanDate;
	private String creUserId;
	private String creDate;
	private String status;
	private String remark;

	/*
	 * 传参
	 */

	private String recUserName;// 强修员工姓名
	private String recOrgName;// 强修员工所在机构
	private String recPosName;// 强修员工岗位

	private String repOrgName;// 顶岗人机构
	private String repUserName;// 顶岗人姓名
	private String repPosName;// 顶岗人岗位

	private String creUserName;// 创建人

	private int sum;// 计划在各个状态下的数量

	private String opeOpinion;// 流程日志意见

	private String actionCode;// 流程日志环节代码

	private String tempUserId;// 传送顶岗or强修员工id

	private String vacationDay;// 休假天数

	private String orgNo;

	private String orgName2nd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecessNo() {
		return recessNo;
	}

	public void setRecessNo(String recessNo) {
		this.recessNo = recessNo;
	}

	public String getRecUserId() {
		return recUserId;
	}

	public void setRecUserId(String recUserId) {
		this.recUserId = recUserId;
	}

	public String getRecOrgNo() {
		return recOrgNo;
	}

	public void setRecOrgNo(String recOrgNo) {
		this.recOrgNo = recOrgNo;
	}

	public String getRecPos() {
		return recPos;
	}

	public void setRecPos(String recPos) {
		this.recPos = recPos;
	}

	public String getRecBeginDate() {
		return recBeginDate;
	}

	public void setRecBeginDate(String recBeginDate) {
		this.recBeginDate = recBeginDate;
	}

	public String getRecEndDate() {
		return recEndDate;
	}

	public void setRecEndDate(String recEndDate) {
		this.recEndDate = recEndDate;
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

	public String getRecUserName() {
		return recUserName;
	}

	public void setRecUserName(String recUserName) {
		this.recUserName = recUserName;
	}

	public String getRecOrgName() {
		return recOrgName;
	}

	public void setRecOrgName(String recOrgName) {
		this.recOrgName = recOrgName;
	}

	public String getRecPosName() {
		return recPosName;
	}

	public void setRecPosName(String recPosName) {
		this.recPosName = recPosName;
	}

	public String getRepOrgName() {
		return repOrgName;
	}

	public void setRepOrgName(String repOrgName) {
		this.repOrgName = repOrgName;
	}

	public String getRepUserName() {
		return repUserName;
	}

	public void setRepUserName(String repUserName) {
		this.repUserName = repUserName;
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

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getOpeOpinion() {
		return opeOpinion;
	}

	public void setOpeOpinion(String opeOpinion) {
		this.opeOpinion = opeOpinion;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getTempUserId() {
		return tempUserId;
	}

	public void setTempUserId(String tempUserId) {
		this.tempUserId = tempUserId;
	}

	public String getVacationDay() {
		return vacationDay;
	}

	public void setVacationDay(String vacationDay) {
		this.vacationDay = vacationDay;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getOrgName2nd() {
		return orgName2nd;
	}

	public void setOrgName2nd(String orgName2nd) {
		this.orgName2nd = orgName2nd;
	}

	@Override
	public String toString() {
		return "Recess [id=" + id + ", recessNo=" + recessNo + ", recUserId=" + recUserId + ", recOrgNo=" + recOrgNo + ", recPos=" + recPos + ", recBeginDate=" + recBeginDate + ", recEndDate="
				+ recEndDate + ", repUserId=" + repUserId + ", repOrgNo=" + repOrgNo + ", repPos=" + repPos + ", hanDate=" + hanDate + ", creUserId=" + creUserId + ", creDate=" + creDate
				+ ", status=" + status + ", remark=" + remark + ", recUserName=" + recUserName + ", recOrgName=" + recOrgName + ", recPosName=" + recPosName + ", repOrgName=" + repOrgName
				+ ", repUserName=" + repUserName + ", repPosName=" + repPosName + ", creUserName=" + creUserName + ", sum=" + sum + ", opeOpinion=" + opeOpinion + ", actionCode=" + actionCode
				+ ", tempUserId=" + tempUserId + ", vacationDay=" + vacationDay + ", orgNo=" + orgNo + ", orgName2nd=" + orgName2nd + "]";
	}

}
