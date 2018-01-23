package com.sys.controller.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.sys.entity.Menu;
import com.sys.entity.Role;
import com.sys.entity.RoleMenu;
import com.sys.service.role.RoleMenuService;
import com.sys.service.role.RoleService;

@Controller
@RequestMapping("/sys/grant/")
public class GrantController extends BaseController<Role> {

	@Autowired
	public RoleService service;

	@Autowired
	public RoleMenuService roleMenuService;

	/**
	 * 角色管理首页
	 */
	@RequestMapping("index")
	public String index(Model model) {
		return "/sys/role/grantmenu";
	}

	/*
	 * 保存角色授权信息
	 */
	@ResponseBody
	@RequestMapping(value = "saveGrant")
	public String saveGrant(Menu menu) {
		String res = "";
		String ids[] = menu.getMenuIds().split(",");
		try {
			// 1.删除原角色权限
			RoleMenu rMenu = new RoleMenu();
			rMenu.setRoleCode(menu.getRoleCode());
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			roleMenuService.delete(rMenu);
			// 循环插入角色菜单表
			for (int i = 0; i < ids.length; i++) {
				RoleMenu rm = new RoleMenu();
				rm.setMenuId(ids[i]);
				rm.setRoleCode(menu.getRoleCode());
				addLog(OP_CLASS_OPER, OP_TYPE_ADD);
				roleMenuService.insert(rm);
			}
			res = "保存成功";
		} catch (Exception e) {
			res = "保存失败";
		}
		return res;
	}

}
