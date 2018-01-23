package com.cert.service.holdinfo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.base.page.Page;
import com.base.service.BaseService;
import com.cert.entity.HoldInfo;

public interface HoldInfoService extends BaseService<HoldInfo> {

	public Page<HoldInfo> findStaffCert(HoldInfo holdInfo, Page<HoldInfo> page);

	public Page<HoldInfo> check(HoldInfo holdInfo, Page<HoldInfo> page);

	public List<HoldInfo> checkinfo(HoldInfo holdInfo);

	public void pass(HoldInfo holdInfo);

	public List<HoldInfo> findOrglist(HoldInfo holdInfo);

	public List<HoldInfo> userCertInfo(HoldInfo holdInfo);

	public List<HoldInfo> findHoldCertInfo(HoldInfo holdInfo);

	// 导出用户持证详情表
	public void export(HoldInfo holdInfo, HttpServletResponse response);

}
