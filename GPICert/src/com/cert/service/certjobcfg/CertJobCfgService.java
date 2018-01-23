package com.cert.service.certjobcfg;

import java.util.List;
import com.cert.entity.CertJobCfg;
import com.base.page.Page;
import com.base.service.BaseService;

public interface CertJobCfgService extends BaseService<CertJobCfg> {

	public List<CertJobCfg> findCertJobCfg(CertJobCfg certJobCfg);

	public List<CertJobCfg> findCertCode(CertJobCfg certJobCfg);

	// 岗位持证情况检查
	public Page<CertJobCfg> findCertList(CertJobCfg certJobCfg, Page<CertJobCfg> page);

	// 查询详情
	public List<CertJobCfg> findinfo(CertJobCfg certJobCfg);

	// 岗位持证配置
	public Page<CertJobCfg> findJobCfg(CertJobCfg certJobCfg, Page<CertJobCfg> page);

	// 岗位持证查询证书详情
	public List<CertJobCfg> jobCertHoldInfo(CertJobCfg certJobCfg);
}
