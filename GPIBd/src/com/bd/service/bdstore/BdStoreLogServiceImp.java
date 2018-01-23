package com.bd.service.bdstore;

import org.springframework.stereotype.Service;

import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.bd.dao.BdStoreLogDao;
import com.bd.entity.StoreLog;
import com.sys.entity.Org;

@Service("BdStoreLogService")
public class BdStoreLogServiceImp extends BaseServiceImp<StoreLog> implements BdStoreLogService {

	
	@Override
	public Page<StoreLog> findByPage(StoreLog storeLog, Org org,
			Page<StoreLog> page) {
		page.setList(((BdStoreLogDao)baseDao).findByPage(storeLog,org, page));
		return page;
	}
	
}
