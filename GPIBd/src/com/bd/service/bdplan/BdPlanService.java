package com.bd.service.bdplan;

import java.util.List;

import com.base.page.Page;
import com.base.service.BaseService;
import com.bd.entity.BdPlan;
import com.sys.entity.Org;

public interface BdPlanService extends BaseService<BdPlan> {

	public List<BdPlan> selectData(BdPlan bdPlan);

	public Page<BdPlan> findByPage(BdPlan bdPlan, Org org, Page<BdPlan> page);

	public List<BdPlan> findQuarter(String dd);

	public Page<BdPlan> findByPageQuery(BdPlan bdPlan, Org org,
			Page<BdPlan> page);

	public void set(BdPlan bdPlan);

}
