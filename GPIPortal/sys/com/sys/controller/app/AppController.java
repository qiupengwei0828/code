package com.sys.controller.app;

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
import com.base.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.App;
import com.sys.service.app.AppService;

/**
 * @ClassName: AppController
 * 
 * @Description:
 * 
 * @author: wind
 * @version: V1.0
 * @Createdate: 2016年2月16日
 * 
 */

@Controller
@RequestMapping("/sys/app/")
public class AppController extends BaseController<App> {

	@Autowired
	public AppService service;

	/**
	 * 应用管理首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<App> list = service.findAllApp();
		model.addAttribute("list", list);
		return "/sys/app/list";
	}

	/**
	 * 应用编辑
	 */
	@RequestMapping("form")
	public String form(App app, Model model) {
		// 1. 判断主键 是否为空，如果为空则为新增，如果不为空则为修改；
		if (StringUtils.isNotEmpty(app.getAppCode())) {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			app = service.get(app); // 取得完整的数据进行修改。
		}
		model.addAttribute("app", app);// 返回需要修改的数据
		return "/sys/app/form";
	}

	// ajax验证添加应用的AppCode是否存在
	@RequestMapping("existsAppCode")
	@ResponseBody
	public Map<String, Object> existsAppCode(App app) {
		Map<String, Object> map = new HashMap<String, Object>();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<App> appinfo = service.existsAppCode(app);
		if (appinfo.size() > 0) {
			map.put("msg", "应用编码已存在，请重试！");
		} else {
			map.put("msg", "none");
		}
		return map;
	}

	/**
	 * 添加应用
	 */
	@RequestMapping("addApp")
	public String addApp(App app) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
			service.insert(app);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/sys/app/index";
	}

	/**
	 * 删除应用
	 */
	@RequestMapping("delete")
	public String delete(App app) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			service.delete(app);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/app/index";
	}

	/**
	 * 修改应用
	 */
	@RequestMapping("update")
	public String update(App app) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
			service.update(app);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/app/index";
	}

	/**
	 * 为前台ZTREE返回树形数据
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<App> list = service.findAllApp();
		for (int i = 0; i < list.size(); i++) {
			App e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getAppCode());
			map.put("pId", "GPI");
			map.put("name", e.getAppName());
			mapList.add(map);
		}
		return mapList;
	}
}
