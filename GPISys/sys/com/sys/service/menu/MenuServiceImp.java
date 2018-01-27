package com.sys.service.menu;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.base.utils.comm.Global;
import com.sys.dao.MenuDao;
import com.sys.entity.Menu;

@Service("MenuService")
public class MenuServiceImp extends BaseServiceImp<Menu> implements MenuService {

	@Override
	public List<Menu> findMenuTree(String userId) {
		return ((MenuDao) baseDao).findMenuTree(userId, Global.getConfig("gpi.sys.code"),Global.getConfig("gpi.common.role"));
	}

	@Override
	public List<Menu> grantTreeData(Menu menu) {
		return ((MenuDao) baseDao).grantTreeData(menu);
	}
}
