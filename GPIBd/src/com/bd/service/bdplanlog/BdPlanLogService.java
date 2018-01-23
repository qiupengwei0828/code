package com.bd.service.bdplanlog;

import com.base.page.Page;
import com.base.service.BaseService;
import com.bd.entity.BdProcess;
import com.sys.entity.Org;

public interface BdPlanLogService extends BaseService<BdProcess> {


	Page<BdProcess> findByPage(BdProcess bdProcess, Org org,
			Page<BdProcess> page);

	Page<BdProcess> findByPageDetail(BdProcess bdProcess, Page<BdProcess> page);

}
