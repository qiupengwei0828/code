package com.sys.service.que;

import com.base.service.BaseService;
import com.sys.entity.Que;

public interface QueService extends BaseService<Que> {

	public void updateStatus(Que que);

}
