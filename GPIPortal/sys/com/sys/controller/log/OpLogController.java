package com.sys.controller.log;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.base.controller.BaseController;
import com.base.page.Page;
import com.sys.entity.App;
import com.sys.entity.OpLog;
import com.sys.service.app.AppService;
import com.sys.service.log.OpLogService;

@Controller
@RequestMapping("/sys/log/")
public class OpLogController extends BaseController<OpLog> {

	@Autowired
	private OpLogService service;

	@Autowired
	public AppService appService;

	@RequestMapping("index")
	public String index(OpLog opLog, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);

		List<App> appList = appService.findAllApp();
		model.addAttribute("appList", appList);

		Page<OpLog> page = service.findByPage(opLog, new Page<OpLog>(request, response));
		model.addAttribute("page", page);
		return "/sys/log/list";
	}

	/*
	 * 测试
	 */
	// @RequestMapping("testHashMap")
	// @ResponseBody
	// public String testHashMap(OpLog opLog, Model model) {
	// List<Map<String, Object>> mm = service.mapQuery(opLog);
	// JSONArray jsonmap = JSONArray.fromObject(mm);
	// logger.info(mm.toString());
	// logger.info(jsonmap.toString());
	// return jsonmap.toString();
	// }

}
