package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("Que")
public class Que extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;
	private String title;
	private String content;
	private String sysId;
	private String status;
	// --------------------------
	private String sysName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

}
