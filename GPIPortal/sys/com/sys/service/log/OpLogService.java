package com.sys.service.log;

import java.util.List;

import com.base.service.BaseService;
import com.sys.entity.OpLog;

public interface OpLogService extends BaseService<OpLog> {

	public List<OpLog> countLogin(OpLog opLog);
}
