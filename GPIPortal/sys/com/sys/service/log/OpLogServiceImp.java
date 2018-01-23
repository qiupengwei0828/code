package com.sys.service.log;

import java.util.List;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImp;
import com.sys.dao.OpLogDao;
import com.sys.entity.OpLog;

@Service("OpLogService")
public class OpLogServiceImp extends BaseServiceImp<OpLog> implements OpLogService {

	@Override
	public List<OpLog> countLogin(OpLog opLog) {
		return ((OpLogDao) baseDao).countLogin(opLog);
	}

}
