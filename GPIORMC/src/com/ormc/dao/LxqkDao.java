package com.ormc.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.ormc.entity.Lxqk;

@MyBatis
public interface LxqkDao extends BaseDao<Lxqk> {

	public List<Map<String, Object>> queryAllData(Map<String, Object> map);

}
