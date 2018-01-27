package com.sys.dao;

import java.util.List;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.App;

/**
 * 系统数据层
 */
@MyBatis
public interface AppDao extends BaseDao<App> {

	public List<App> findAllApp();

	public List<App> existsAppCode(App app);

	public List<App> findAppCode();

}