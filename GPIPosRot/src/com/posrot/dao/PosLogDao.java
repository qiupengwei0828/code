package com.posrot.dao;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.posrot.entity.PosLog;

@MyBatis
public interface PosLogDao extends BaseDao<PosLog> {

	public void eInsert(PosLog posLog);

}
