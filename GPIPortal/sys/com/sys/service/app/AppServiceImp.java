package com.sys.service.app;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sys.dao.AppDao;
import com.sys.entity.App;
import com.base.service.BaseServiceImp;

@Service("AppService")
public class AppServiceImp extends BaseServiceImp<App> implements AppService {

	/**
	 * @todo:
	 * @return
	 * @see com.sys.service.app.AppService#findAllApp()
	 */
	@Override
	public List<App> findAllApp() {
		// TODO Auto-generated method stub
		return ((AppDao) baseDao).findAllApp();
	}

	/**
	 * ajax查询要添加的appCode是否存在
	 * 
	 * @todo:
	 * @return
	 * @see com.sys.service.app.AppService#existsAppCode()
	 */
	@Override
	public List<App> existsAppCode(App app) {
		return ((AppDao) baseDao).existsAppCode(app);
	}

	@Override
	public List<App> findAppCode() {
		return ((AppDao) baseDao).findAppCode();
	}

	@Override
	public List<App> findAppListUser(App app) {
		return ((AppDao) baseDao).findAppListUser(app);
	}

}
