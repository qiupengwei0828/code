package com.cert.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.cert.entity.HoldInfo;

/**
 * 系统数据层
 */
@MyBatis
public interface HoldInfoDao extends BaseDao<HoldInfo> {

	public List<HoldInfo> findStaffCert(@Param("param") HoldInfo holdInfo, Page<HoldInfo> page);

	public List<HoldInfo> check(@Param("param") HoldInfo holdInfo, Page<HoldInfo> page);

	public List<HoldInfo> checkinfo(HoldInfo holdInfo);

	public void pass(HoldInfo holdInfo);

	public List<HoldInfo> findOrglist(HoldInfo holdInfo);

	public List<HoldInfo> userCertInfo(HoldInfo holdInfo);

	public List<HoldInfo> findHoldCertInfo(HoldInfo holdInfo);
}
