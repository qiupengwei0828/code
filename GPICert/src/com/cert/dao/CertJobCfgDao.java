package com.cert.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cert.entity.CertJobCfg;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;

/**
 * 系统数据层
 */
@MyBatis
public interface CertJobCfgDao extends BaseDao<CertJobCfg> {

	public List<CertJobCfg> findCertJobCfg(CertJobCfg certJobCfg);

	public List<CertJobCfg> findCertCode(CertJobCfg certJobCfg);

	// 岗位持证情况检查
	public List<CertJobCfg> findCertList(@Param("param") CertJobCfg certJobCfg, Page<CertJobCfg> page);

	// 查询详情
	public List<CertJobCfg> findinfo(CertJobCfg certJobCfg);

	// 岗位持证配置
	public List<CertJobCfg> findJobCfg(@Param("param") CertJobCfg certJobCfg, Page<CertJobCfg> page);

	// 岗位持证证书详情
	public List<CertJobCfg> jobCertHoldInfo(CertJobCfg certJobCfg);
}