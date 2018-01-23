package com.bd.service.bdplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.bd.dao.BdPlanDao;
import com.bd.entity.BdPlan;
import com.sys.entity.Org;

@Service("BdPlanService")
public class BdPlanServiceImp extends BaseServiceImp<BdPlan> implements BdPlanService {


	@Autowired
	private BdPlanDao bdPlanDao;

	@Override
	public List<BdPlan> selectData(BdPlan bdPlan) {
		List<BdPlan> list = bdPlanDao.selectData(bdPlan);
		return list;
	}

	@Override
	public Page<BdPlan> findByPage(BdPlan bdPlan, Org org, Page<BdPlan> page) {
		page.setList(((BdPlanDao)baseDao).findByPage(bdPlan,org, page));
		return page;
	}

	@Override
	public List<BdPlan> findQuarter(String dd) {
		List<BdPlan> list = bdPlanDao.findQuarter(dd);
		return list;
	}

	@Override
	public Page<BdPlan> findByPageQuery(BdPlan bdPlan, Org org,
			Page<BdPlan> page) {
		page.setList(((BdPlanDao)baseDao).findByPageQuery(bdPlan,org, page));
		return page;
	}

	@Override
	public void set(BdPlan bdPlan) {
		bdPlanDao.set(bdPlan);
		
	}



}
