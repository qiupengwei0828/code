package com.portal.controller.echarts;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.base.controller.BaseController;
import com.sys.entity.OpLog;
import com.sys.service.log.OpLogService;

@Controller
@RequestMapping("/echarts/")
public class EchartsController extends BaseController<Object> {

	@Autowired
	public OpLogService opLogService;

	// 日历
	@RequestMapping("calendar")
	public String calendar(Model model) throws UnsupportedEncodingException {
		return "/portal/calendar/date";
	}

	// 统计登录信息
	@RequestMapping("countLogin")
	public String countLogin(Model model) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<OpLog> log = opLogService.countLogin(null);
		String title = "";
		String cntNum = "";
		for (int i = 0; i < log.size(); i++) {
			title = title + "'" + log.get(i).getMiniName() + "'";
			cntNum = cntNum + log.get(i).getCountnum();
			if (i < log.size() - 1) {
				title = title + ",";
				cntNum = cntNum + ",";
			}
		}
		model.addAttribute("log", log);
		model.addAttribute("title", title);
		model.addAttribute("cntNum", cntNum);
		return "/portal/countLogin/countLogin";
	}

	// 甘肃地图
	@RequestMapping("gsMap")
	public String gsMap(Model model) throws UnsupportedEncodingException {
		return "/portal/map/gansu_map";
	}

	@RequestMapping("data/data2")
	public String data2(Model model) throws UnsupportedEncodingException {
		return "/portal/data/data2";
	}

	@RequestMapping("data/data1")
	public String data1(Model model) throws UnsupportedEncodingException {
		return "/portal/data/data1";
	}

}
