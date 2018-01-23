package com.cert.service.holdinfo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.cert.dao.HoldInfoDao;
import com.cert.entity.HoldInfo;

@Service("HoldInfoService")
public class HoldInfoServiceImp extends BaseServiceImp<HoldInfo> implements HoldInfoService {

	@Override
	public Page<HoldInfo> findStaffCert(HoldInfo holdInfo, Page<HoldInfo> page) {
		page.setList(((HoldInfoDao) baseDao).findStaffCert(holdInfo, page));
		return page;
	}

	/*
	 * 员工持证审核
	 */
	@Override
	public Page<HoldInfo> check(HoldInfo holdInfo, Page<HoldInfo> page) {
		page.setList(((HoldInfoDao) baseDao).check(holdInfo, page));
		return page;
	}

	/*
	 * 员工持证审核详情
	 */
	@Override
	public List<HoldInfo> checkinfo(HoldInfo holdInfo) {
		return ((HoldInfoDao) baseDao).checkinfo(holdInfo);
	}

	/*
	 * 审核通过
	 */
	@Override
	public void pass(HoldInfo holdInfo) {
		((HoldInfoDao) baseDao).pass(holdInfo);
	}

	/*
	 * 查询用户持证
	 */
	@Override
	public List<HoldInfo> userCertInfo(HoldInfo holdInfo) {
		return ((HoldInfoDao) baseDao).userCertInfo(holdInfo);
	}

	/*
	 * 查询机构持证
	 */
	@Override
	public List<HoldInfo> findOrglist(HoldInfo holdInfo) {
		return ((HoldInfoDao) baseDao).findOrglist(holdInfo);
	}

	@Override
	public List<HoldInfo> findHoldCertInfo(HoldInfo holdInfo) {
		return ((HoldInfoDao) baseDao).findHoldCertInfo(holdInfo);
	}

	/*
	 * 导出报表（用户持证详情表）
	 */
	@Override
	public void export(HoldInfo holdInfo, HttpServletResponse response) {
		List<HoldInfo> list = ((HoldInfoDao) baseDao).userCertInfo(holdInfo);
		Export export = new Export();
		try {
			export.export(list, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
