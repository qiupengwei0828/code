package com.sys.dao;

import java.util.List;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.Dep;
@MyBatis
public interface DepDao   extends BaseDao<Dep> {
	public List<Dep> existsDepCode(Dep dep);
}
