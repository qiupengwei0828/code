package com.bd.service.bdplanlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.bd.dao.PlanDao;
import com.bd.entity.BdPlanEntity;
import com.sys.entity.Org;

@Service("PlanService")
public class PlanServiceImp extends BaseServiceImp<BdPlanEntity> implements PlanService {

	
	@Autowired
	private PlanDao planDao;

	@Override
	public Page<BdPlanEntity> findByPage(BdPlanEntity bdPlanEntity, Org org,
			Page<BdPlanEntity> page) {
		page.setList(((PlanDao)baseDao).findByPage(bdPlanEntity,org, page));
		return page;
	}
	


}
