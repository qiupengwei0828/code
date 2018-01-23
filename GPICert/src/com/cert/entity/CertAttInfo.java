package com.cert.entity;

import org.apache.ibatis.type.Alias;
import com.base.entity.BaseEntity;

/*
 *  图片附件表
 */
@Alias("CertAttInfo")
public class CertAttInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long fileId;
	private String appCode;
	private Long cfgId;
	private String tab;
	private Long tabId;
	private String fileName;
	private String fileType;
	private String fileSaveName;
	private String crtDate;
	private Long fileSize;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Long getCfgId() {
		return cfgId;
	}

	public void setCfgId(Long cfgId) {
		this.cfgId = cfgId;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSaveName() {
		return fileSaveName;
	}

	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public Long getTabId() {
		return tabId;
	}

	public void setTabId(Long tabId) {
		this.tabId = tabId;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}
