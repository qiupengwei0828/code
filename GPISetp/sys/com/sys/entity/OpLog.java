package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("OpLog")
public class OpLog extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String appCode;
	private String userId;
	private String servletPath;
	private String opObj;
	private String opIp;
	private String opClass;
	private String opClassName;
	/** 打开、增、删、改、查、 */
	private String opType;
	private String opTypeName;
	private String opCnt;
	private String opTime;
	private String beginDate;
	private String endDate;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return T_SYS_OP_LOG.ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            ： T_SYS_OP_LOG.ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return T_SYS_OP_LOG.APP_CODE
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode
	 *            ： T_SYS_OP_LOG.APP_CODE
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode == null ? null : appCode.trim();
	}

	/**
	 * @return T_SYS_OP_LOG.USER_ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            ： T_SYS_OP_LOG.USER_ID
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * @return T_SYS_OP_LOG.OP_IP
	 */
	public String getOpIp() {
		return opIp;
	}

	/**
	 * @param opIp
	 *            ： T_SYS_OP_LOG.OP_IP
	 */
	public void setOpIp(String opIp) {
		this.opIp = opIp == null ? null : opIp.trim();
	}

	/**
	 * @return T_SYS_OP_LOG.OP_MOD
	 */

	/**
	 * @return T_SYS_OP_LOG.OP_TYPE
	 */
	public String getOpType() {
		return opType;
	}

	/**
	 * @param opType
	 *            ： T_SYS_OP_LOG.OP_TYPE
	 */
	public void setOpType(String opType) {
		this.opType = opType == null ? null : opType.trim();
	}

	/**
	 * @return T_SYS_OP_LOG.OP_CNT
	 */
	public String getOpCnt() {
		return opCnt;
	}

	/**
	 * @param opCnt
	 *            ： T_SYS_OP_LOG.OP_CNT
	 */
	public void setOpCnt(String opCnt) {
		this.opCnt = opCnt == null ? null : opCnt.trim();
	}

	/**
	 * @return T_SYS_OP_LOG.OP_TIME
	 */

	public String getOpClass() {
		return opClass;
	}

	public void setOpClass(String opClass) {
		this.opClass = opClass;
	}

	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public String getServletPath() {
		return servletPath;
	}

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	public String getOpObj() {
		return opObj;
	}

	public void setOpObj(String opObj) {
		this.opObj = opObj;
	}

	public String getOpClassName() {
		return opClassName;
	}

	public void setOpClassName(String opClassName) {
		this.opClassName = opClassName;
	}

	public String getOpTypeName() {
		return opTypeName;
	}

	public void setOpTypeName(String opTypeName) {
		this.opTypeName = opTypeName;
	}

}