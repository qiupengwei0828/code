package com.cert.service.certjobcfg;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cert.dao.CertJobCfgDao;
import com.cert.entity.CertJobCfg;
import com.base.page.Page;
import com.base.service.BaseServiceImp;

@Service("CertJobCfgService")
public class CertJobCfgServiceImp extends BaseServiceImp<CertJobCfg> implements CertJobCfgService {

	@Override
	public List<CertJobCfg> findCertJobCfg(CertJobCfg certJobCfg) {
		return ((CertJobCfgDao) baseDao).findCertJobCfg(certJobCfg);
	}

	@Override
	public List<CertJobCfg> findCertCode(CertJobCfg certJobCfg) {
		return ((CertJobCfgDao) baseDao).findCertCode(certJobCfg);
	}

	/*
	 * 岗位持证情况检查
	 */
	@Override
	public Page<CertJobCfg> findCertList(CertJobCfg certJobCfg, Page<CertJobCfg> page) {
		page.setList(((CertJobCfgDao) baseDao).findCertList(certJobCfg, page));
		return page;
	}

	/*
	 * 查询详情
	 */
	@Override
	public List<CertJobCfg> findinfo(CertJobCfg certJobCfg) {
		return ((CertJobCfgDao) baseDao).findinfo(certJobCfg);
	}

	/*
	 * 岗位持证配置
	 */
	@Override
	public Page<CertJobCfg> findJobCfg(CertJobCfg certJobCfg, Page<CertJobCfg> page) {
		page.setList(((CertJobCfgDao) baseDao).findJobCfg(certJobCfg, page));
		return page;
	}

	@Override
	public List<CertJobCfg> jobCertHoldInfo(CertJobCfg certJobCfg) {
		return ((CertJobCfgDao) baseDao).jobCertHoldInfo(certJobCfg);
	}

}
