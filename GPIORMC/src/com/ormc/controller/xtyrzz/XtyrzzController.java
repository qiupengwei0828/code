package com.ormc.controller.xtyrzz;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.ormc.entity.Xtyrzz;
import com.ormc.service.xtyrzz.XtyrzzService;

@Controller
@RequestMapping("/ormc/xtyrzz/")
public class XtyrzzController extends BaseController<Xtyrzz> {

	@Autowired
	public XtyrzzService xtyrzzService;

	// @ResponseBody
	@RequestMapping("list")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ParseException {

		Map<String, Object> mapAttr = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// 小写的mm表示的是分钟
		// Date date = sdf.parse(dstr);

		if (request.getParameter("creditUserID") != null) {
			mapAttr.put("creditUserID", request.getParameter("creditUserID"));
			model.addAttribute("creditUserID", request.getParameter("creditUserID"));
		}
		if (request.getParameter("loanTypeName") != null && request.getParameter("loanTypeName") != "") {
			mapAttr.put("loanTypeName", URLDecoder.decode(request.getParameter("loanTypeName"), "utf-8"));
			model.addAttribute("loanTypeName", URLDecoder.decode(request.getParameter("loanTypeName"), "utf-8"));
		}
		if (request.getParameter("transferAcc") != null) {
			mapAttr.put("transferAcc", request.getParameter("transferAcc"));
			model.addAttribute("transferAcc", request.getParameter("transferAcc"));
		}

		if (request.getParameter("beginDate") != null) {
			mapAttr.put("beginDate", request.getParameter("beginDate"));
			model.addAttribute("beginDate", sdf.parse(request.getParameter("beginDate")));
		}
		if (request.getParameter("endDate") != null) {
			mapAttr.put("endDate", request.getParameter("endDate"));
			model.addAttribute("endDate", sdf.parse(request.getParameter("endDate")));
		}

		// List<Map<String, Object>> mm = xtyrzzService.mapQuery(null);
		List<Map<String, Object>> mm = xtyrzzService.queryAllData(mapAttr);

		JSONArray jsonmap = JSONArray.fromObject(mm);
		model.addAttribute("jsonDate", jsonmap.toString());
		return "/ormc/xtyrzz/list";
	}

}
