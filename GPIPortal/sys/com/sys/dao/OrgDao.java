package com.sys.dao;

import java.util.List;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.Org;

/**
 * 系统数据层
 */
@MyBatis
public interface OrgDao extends BaseDao<Org> {

	public List<Org> findAllTree(Org org);

}