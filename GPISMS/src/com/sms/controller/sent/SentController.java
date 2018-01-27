package com.sms.controller.sent;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.sms.entity.Sent;
import com.sms.service.sent.SentService;
import com.sys.service.app.AppService;

@Controller
@RequestMapping("/sms/sent/")
public class SentController extends BaseController<Sent> {

	@Autowired
	public SentService sentService;

	@Autowired
	public AppService appService;

	@RequestMapping("list")
	public String list(Sent sent, Model model) {
		model.addAttribute("applist", appService.findAllList(null));
		return "sms/sent/list";
	}

	@ResponseBody
	@RequestMapping("sentlist")
	public List<Sent> msglist(Sent sent, Model model, HttpServletRequest request, HttpServletResponse response) {

		String beginDate = request.getParameter("beginDate");
		String endsDate = request.getParameter("endsDate");
		if (beginDate != null || endsDate != null) {
			model.addAttribute("beginDate", beginDate);
			model.addAttribute("endsDate", endsDate);
		}

		List<Sent> list = sentService.findAllList(sent);
		return list;
	}

	@RequestMapping("info")
	public String info(Sent sent, Model model) {
		sent = sentService.find(sent).get(0);
		model.addAttribute("sent", sent);
		return "sms/sent/info";
	}

	@ResponseBody
	@RequestMapping("mapQuery")
	public String mapQuery(Sent sent, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> mm = sentService.mapQuery(null);
		JSONArray jsonmap = JSONArray.fromObject(mm);
		logger.info("================================" + mm.toString());
		logger.info("================================" + jsonmap.toString());
		return jsonmap.toString();
	}
}
