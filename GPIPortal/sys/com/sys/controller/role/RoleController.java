package com.sys.controller.role;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.base.controller.BaseController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.App;
import com.sys.entity.Role;
import com.sys.service.role.RoleService;
import com.sys.service.app.AppService;

@Controller
@RequestMapping("/sys/role/")
public class RoleController extends BaseController<Role> {

	@Autowired
	public RoleService service;

	@Autowired
	public AppService appService;

	/**
	 * 角色管理首页
	 */
	@RequestMapping("index")
	public String index(Role role, Model model) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Role> list = service.findAllList(role);
		model.addAttribute("list", list);
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<App> applist = appService.findAllApp();
		model.addAttribute("applist", applist);
		return "/sys/role/list";
	}

	/**
	 * 角色编辑首页
	 */

	@RequestMapping("form")
	public String form(Role role, Model model) {
		if (role.getRoleCode() != null) {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			role = service.find(role).get(0);
			model.addAttribute("role", role);
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			List<App> applist = appService.findAllApp();
			model.addAttribute("applist", applist);
		}
		List<App> applist = appService.findAllApp();
		model.addAttribute("applist", applist);
		return "/sys/role/form";
	}

	/*
	 * ajax验证添加应用的AppCode是否存在
	 */
	@RequestMapping("existsRoleCode")
	@ResponseBody
	public Map<String, Object> existsRoleCode(Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Role> roleinfo = service.existsRoleCode(role);
		if (roleinfo.size() > 0) {
			map.put("msg", "角色编码已存在，请重试！");
		} else {
			map.put("msg", "none");
		}
		return map;
	}

	/*
	 * 用户的应用序号
	 */
	@RequestMapping("findAppCode")
	@ResponseBody
	public List<App> findAppCode() {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<App> appList = appService.findAllApp();
		return appList;
	}

	/**
	 * 添加角色
	 */
	@RequestMapping("addRole")
	public String addApp(Role role) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			service.insert(role);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/role/index";
	}

	/**
	 * 删除角色
	 */
	@RequestMapping("delete")
	public String delete(Role role) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			service.delete(role);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/role/index";
	}

	/**
	 * 更新应用
	 */
	@RequestMapping("update")
	public String update(Role role) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
			service.update(role);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/role/index";
	}

	/**
	 * 为前台ZTREE返回树形数据(查询用户)
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(Role role, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Role> list = service.findAllList(role);
		for (int i = 0; i < list.size(); i++) {
			Role e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getRoleCode());
			map.put("pId", e.getAppCode());
			map.put("name", e.getRoleName());
			mapList.add(map);
		}
		return mapList;
	}

	/*
	 * 为前台ZTREE返回树形数据(查询角色)
	 */
	@ResponseBody
	@RequestMapping(value = "treeDataRole")
	public List<Map<String, Object>> treeDataRole(Role role, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Role> list = service.findRoleList(role);
		for (int i = 0; i < list.size(); i++) {
			Role e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getRoleCode());
			map.put("pId", e.getAppCode());
			map.put("name", e.getRoleName());
			mapList.add(map);
		}
		return mapList;
	}
}
