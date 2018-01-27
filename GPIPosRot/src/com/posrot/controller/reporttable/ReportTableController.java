package com.posrot.controller.reporttable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.posrot.entity.Recess;
import com.posrot.entity.ResInfo;
import com.posrot.service.recess.RecessService;
import com.posrot.service.res.ResService;
import com.sys.entity.Pos;
import com.sys.service.pos.PosService;

@Controller
@RequestMapping("/posrot/reporttable/")
public class ReportTableController extends BaseController<ResInfo> {

	@Autowired
	public ResService resService;

	@Autowired
	public RecessService recessService;

	@Autowired
	public PosService posService;

	/*
	 * 岗位轮换花名册
	 */
	@RequestMapping("rotatelist")
	public String rotatelist(ResInfo resInfo, Model model) {
		List<Pos> poslist = posService.findAllList(null);
		model.addAttribute("poslist", poslist);
		return "posrot/reporttable/rotatelist";
	}

	@ResponseBody
	@RequestMapping("rotate_all_list")
	public List<ResInfo> rotate_all_list(ResInfo resInfo, HttpServletRequest request, HttpServletResponse response) {
		String temp = resInfo.getUserName();
		if (temp != null && temp != "") {
			try {
				temp = URLDecoder.decode(temp, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			resInfo.setUserName(temp);
		}

		List<ResInfo> list = resService.findAllList(resInfo);
		return list;
	}

	// 岗位轮换花名册数据导出
	@RequestMapping("rotate_all_export")
	public void export(ResInfo resInfo, Model model, HttpServletResponse response) {
		resService.export(resInfo, response);
	}

	/*
	 * 从业人员强制休假花名册
	 */
	@RequestMapping("recesslist")
	public String recesslist(Recess recess, Model model) {
		// List<Pos> poslist = posService.findAllList(null);
		// model.addAttribute("poslist", poslist);
		return "posrot/reporttable/recesslist";
	}

	@ResponseBody
	@RequestMapping("recesslist_all_list")
	public List<Recess> rotate_all_list(Recess recess, HttpServletRequest request, HttpServletResponse response) {

		String REC_temp = recess.getRecUserName();
		String REP_temp = recess.getRepUserName();
		if (REC_temp != null && REC_temp != "" || REP_temp != null && REP_temp != "") {
			try {
				REC_temp = URLDecoder.decode(REC_temp, "utf-8");
				REP_temp = URLDecoder.decode(REP_temp, "utf-8");

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			recess.setRecUserName(REC_temp);
			recess.setRepUserName(REP_temp);
		}

		List<Recess> list = recessService.findAllList(recess);
		return list;
	}

	// 强制休假花名册数据导出
	@RequestMapping("recess_all_export")
	public void recess_export(Recess recess, Model model, HttpServletResponse response) {
		recessService.export(recess, response);
	}
}
