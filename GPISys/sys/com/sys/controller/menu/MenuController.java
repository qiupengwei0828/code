package com.sys.controller.menu;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.base.controller.BaseController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.Menu;
import com.sys.service.app.AppService;
import com.sys.service.menu.MenuService;

/**
 * @ClassName:
 * 
 * @Description:
 * 
 * @author: wind
 * @version: V1.0
 * @Createdate: 2016年2月16日
 * 
 */

@Controller
@RequestMapping("/sys/menu/")
public class MenuController extends BaseController<Menu> {

	@Autowired
	public MenuService service;

	@Autowired
	public AppService appService;

	@RequestMapping(value = { "index" })
	public String list(Menu menu, Model model) {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		model.addAttribute("list", service.findAllList(menu));
		model.addAttribute("appCodeList", appService.findAllApp());
		return "/sys/menu/list";
	}

	@RequestMapping(value = "form")
	public String form(Menu menu, Model model) {
		// 1. 判断主键 是否为空，如果为空则为新增，如果不为空则为修改；
		if (null != menu.getMenuId() && menu.getMenuId() > 1) {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			menu = service.get(menu); // 取得完整的数据进行修改。
		}
		model.addAttribute("menu", menu);// 返回需要修改的数据
		model.addAttribute("appCodeList", appService.findAllApp());
		return "/sys/menu/form";
	}

	@RequestMapping(value = "save")
	public String save(Menu menu, Model model, RedirectAttributes redirectAttributes) {

		/*
		 * if (!beanValidator(model, Org)){ return form(Org, model); }
		 */
		/**
		 * 判断主键是否为空，为空插入，不为空则修改； 字符串主键用StringUtils.isBlank(entity.key) 数字主键用
		 * 大于0进行判断
		 */

		if (null != menu.getMenuId() && menu.getMenuId() > 0) {
			service.update(menu);
			addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
		} else {
			service.insert(menu);
			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
		}
		addMessage(redirectAttributes, "保存机构'" + menu.getMenuName() + "'成功");
		return "redirect:" + "/sys/menu/index";
	}

	@RequestMapping(value = "delete")
	public String delete(Menu menu, RedirectAttributes redirectAttributes) {
		// if (Org.isRoot(id)){
		// addMessage(redirectAttributes, "删除机构失败, 不允许删除顶级机构或编号空");
		// }else{
		addLog(OP_CLASS_OPER, OP_TYPE_DEL);
		service.delete(menu);
		addMessage(redirectAttributes, "删除成功");
		// }
		return "redirect:" + "/sys/menu/index";
	}

	/**
	 * 获取系统菜单。
	 * 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response, Menu menu) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		// 选取用户所在本级机构所属的本级及下级所有机构
		// addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Menu> list = service.findAllList(menu);
		for (int i = 0; i < list.size(); i++) {
			Menu e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getMenuId());
			map.put("pId", e.getpMenuId());
			map.put("name", e.getMenuName());
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 获取菜单授权JSON数据。
	 * 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "grantTreeData")
	public List<Map<String, Object>> grantTreeData(Menu menu, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Menu> list = service.grantTreeData(menu);
		for (int i = 0; i < list.size(); i++) {
			Menu e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getMenuId());
			map.put("pId", e.getpMenuId());
			map.put("name", e.getMenuName());

			// 菜单图标
			// map.put("open", "true");
			// map.put("icon", e.getIcon());
			// map.put("iconSkin", e.getIcon());

			if (org.apache.commons.lang3.StringUtils.isNoneBlank(e.getRoleCode())) {
				map.put("checked", "true"); // 如果角色不为空则表示已授权
			}
			mapList.add(map);
		}
		return mapList;
	}

}
