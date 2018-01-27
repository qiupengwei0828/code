package com.sys.dao;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.Que;

@MyBatis
public interface QueDao extends BaseDao<Que> {

	public void updateStatus(Que que);

}