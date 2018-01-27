package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("AttachInfo")
public class AttachInfo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long fileId;

	private String appCode;

	private Long cfgCode;

	private String tab;

	private String tabKey;

	private String fileName;

	private String fileType;

	/** 路径/应用模块/日期 */
	private String fileSaveName;

	private String crtDate;

	private Long fileSize;

	private String remark;

	/**
	 * @return T_SYS_ATT_INFO.FILE_ID
	 */
	public Long getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            ： T_SYS_ATT_INFO.FILE_ID
	 */
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return T_SYS_ATT_INFO.APP_CODE
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode
	 *            ： T_SYS_ATT_INFO.APP_CODE
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode == null ? null : appCode.trim();
	}

	/**
	 * @return T_SYS_ATT_INFO.CFG_CODE
	 */
	public Long getCfgCode() {
		return cfgCode;
	}

	/**
	 * @param cfgCode
	 *            ： T_SYS_ATT_INFO.CFG_CODE
	 */
	public void setCfgCode(Long cfgCode) {
		this.cfgCode = cfgCode;
	}

	/**
	 * @return T_SYS_ATT_INFO.TAB
	 */
	public String getTab() {
		return tab;
	}

	/**
	 * @param tab
	 *            ： T_SYS_ATT_INFO.TAB
	 */
	public void setTab(String tab) {
		this.tab = tab == null ? null : tab.trim();
	}

	/**
	 * @return T_SYS_ATT_INFO.TAB_KEY
	 */
	public String getTabKey() {
		return tabKey;
	}

	/**
	 * @param tabKey
	 *            ： T_SYS_ATT_INFO.TAB_KEY
	 */
	public void setTabKey(String tabKey) {
		this.tabKey = tabKey == null ? null : tabKey.trim();
	}

	/**
	 * @return T_SYS_ATT_INFO.FILE_NAME
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            ： T_SYS_ATT_INFO.FILE_NAME
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	/**
	 * @return T_SYS_ATT_INFO.FILE_TYPE
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            ： T_SYS_ATT_INFO.FILE_TYPE
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}

	/**
	 * @return T_SYS_ATT_INFO.FILE_SAVE_NAME
	 */
	public String getFileSaveName() {
		return fileSaveName;
	}

	/**
	 * @param fileSaveName
	 *            ： T_SYS_ATT_INFO.FILE_SAVE_NAME
	 */
	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName == null ? null : fileSaveName.trim();
	}

	/**
	 * @return T_SYS_ATT_INFO.CRT_DATE
	 */
	public String getCrtDate() {
		return crtDate;
	}

	/**
	 * @param crtDate
	 *            ： T_SYS_ATT_INFO.CRT_DATE
	 */
	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate == null ? null : crtDate.trim();
	}

	/**
	 * @return T_SYS_ATT_INFO.FILE_SIZE
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 *            ： T_SYS_ATT_INFO.FILE_SIZE
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}