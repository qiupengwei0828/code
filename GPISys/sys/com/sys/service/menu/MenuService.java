package com.sys.service.menu;

import java.util.List;

import com.sys.entity.Menu;
import com.base.service.BaseService;

public interface MenuService extends BaseService<Menu>{
	
	 /**
    * 菜单树
    * @param menuId
    * @param userId
    * @return
    */
	public List<Menu> findMenuTree(String userId);

	
	/**
	 * 加载角色授权菜单权
	 * @param @param menu
	 * @param @return 
	 * @return List<Menu>
	 * @throws
	 */
	public List<Menu>  grantTreeData(Menu menu);
}
