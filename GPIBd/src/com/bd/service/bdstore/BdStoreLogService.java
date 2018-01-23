package com.bd.service.bdstore;

import com.base.page.Page;
import com.base.service.BaseService;
import com.bd.entity.StoreLog;
import com.sys.entity.Org;

public interface BdStoreLogService extends BaseService<StoreLog>  {

	Page<StoreLog> findByPage(StoreLog storeLog, Org org, Page<StoreLog> page);

}
