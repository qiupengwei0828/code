package com.ormc.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.ormc.entity.Xdyzjwl;

@MyBatis
public interface XdyzjwlDao extends BaseDao<Xdyzjwl> {

	public List<Map<String, Object>> queryAllData(Map<String, Object> map);

}
