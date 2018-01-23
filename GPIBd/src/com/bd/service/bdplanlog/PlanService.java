package com.bd.service.bdplanlog;

import com.base.page.Page;
import com.base.service.BaseService;
import com.bd.entity.BdPlanEntity;
import com.sys.entity.Org;

public interface PlanService extends BaseService<BdPlanEntity> {


	Page<BdPlanEntity> findByPage(BdPlanEntity bdPlanEntity, Org org,
			Page<BdPlanEntity> page);

}
