package com.bd.service.bdplanlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.bd.dao.BdPlanLogDao;
import com.bd.dao.CertactDao;
import com.bd.entity.BdProcess;
import com.sys.entity.Org;

@Service("BdPlanLogService")
public class BdPlanLogServiceImp extends BaseServiceImp<BdProcess> implements BdPlanLogService {

	
	@Autowired
	private CertactDao certactDao;

	@Override
	public Page<BdProcess> findByPage(BdProcess bdProcess, Org org,
			Page<BdProcess> page) {
		page.setList(((BdPlanLogDao)baseDao).findByPage(bdProcess,org, page));
		return page;
	}

	@Override
	public Page<BdProcess> findByPageDetail(BdProcess bdProcess,
			Page<BdProcess> page) {
		page.setList(((BdPlanLogDao)baseDao).findByPageDetail(bdProcess,page));
		return page;
	}
	


}
