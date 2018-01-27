package com.setp.controller.export;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.setp.entity.SetpCount;
import com.setp.entity.SetpUser;
import com.setp.service.exportorgavg.ExportOrgAvg;
import com.setp.service.exportusersum.ExportUserSum;
import com.setp.service.setpcount.SetpCountService;
import com.setp.service.setpuser.SetpUserService;

@Controller
@RequestMapping("/setp/export/")
public class ExportController extends BaseController<Object> {

	@Autowired
	public SetpUserService setpUserService;

	@Autowired
	public SetpCountService setpCountService;

	// setpUserService;
	@RequestMapping("sum_user_setp")
	public String sum_user_setp(SetpUser setpUser, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<SetpUser> list = setpUserService.user_sum_setp_num(setpUser);
		ExportUserSum export = new ExportUserSum();
		try {
			export.export(list, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("avg_org_setp")
	public String avg_org_setp(SetpCount setpCount, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		setpCount.setDim(String.valueOf(setpCountService.getDim(setpCount).size()));

		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<SetpCount> list = setpCountService.org_avg_setp_num(setpCount);
		ExportOrgAvg export = new ExportOrgAvg();
		try {
			export.export(list, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
