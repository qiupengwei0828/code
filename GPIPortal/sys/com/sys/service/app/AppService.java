package com.sys.service.app;

import java.util.List;
import com.sys.entity.App;
import com.base.service.BaseService;

public interface AppService extends BaseService<App> {

	public List<App> findAllApp();

	// 添加app时，ajax验证appCode是否存在
	public List<App> existsAppCode(App app);

	public List<App> findAppCode();

	/*
	 * 查询用户可用的系统
	 */
	public List<App> findAppListUser(App app);
}
