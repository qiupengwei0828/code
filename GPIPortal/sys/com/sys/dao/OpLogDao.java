package com.sys.dao;

import java.util.List;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.OpLog;

@MyBatis
public interface OpLogDao extends BaseDao<OpLog> {

	public List<OpLog> countLogin(OpLog opLog);

}
