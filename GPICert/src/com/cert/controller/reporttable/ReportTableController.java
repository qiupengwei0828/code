package com.cert.controller.reporttable;

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
import com.cert.entity.HoldInfo;
import com.cert.service.holdinfo.HoldInfoService;
import com.sys.entity.Org;
import com.sys.service.org.OrgService;

@Controller
@RequestMapping("/cert/reporttable/")
public class ReportTableController extends BaseController<HoldInfo> {

	@Autowired
	public HoldInfoService holdInfoService;

	@Autowired
	public OrgService orgService;

	// 员工持证情况明细
	@RequestMapping("userlist")
	public String userlist(HoldInfo holdInfo, Model model) {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Org> orglist = orgService.findAllList(null);
		model.addAttribute("orglist", orglist);
		return "cert/reporttable/userlist";
	}

	@ResponseBody
	@RequestMapping("findUserlist")
	public List<HoldInfo> findUserlist(HoldInfo holdInfo, HttpServletRequest request, HttpServletResponse response) {
		//
		// Org org = new Org();
		// User user = AccountShiroUtil.getCurrentUser();
		// String orgNo = user.getOrgNo();
		// org.setOrgNo(user.getOrgNo());
		// Org orginfo = orgService.get(org);
		// String orgLevel = orginfo.getOrgLevel();

		// -------------------------------------------

		// if ("2".equals(orgLevel)) {
		// holdInfo.setOrgNo2nd(orgNo);
		// } else if ("3".equals(orgLevel)) {
		// holdInfo.setOrgNo3rd(orgNo);
		// } else if ("4".equals(orgLevel)) {
		// holdInfo.setOrgNo4th(orgNo);
		// }

		// --------------------------------------------

		try {
			if (holdInfo.getUserName() != null) {
				String temp = holdInfo.getUserName();
				temp = URLDecoder.decode(temp, "utf-8");
				holdInfo.setUserName(temp);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<HoldInfo> userCertInfo = holdInfoService.userCertInfo(holdInfo);

		return userCertInfo;
	}

	// 导出员工持证情况明细报表
	@RequestMapping("/export")
	public String export(HoldInfo holdInfo, Model model, HttpServletResponse response) {
		holdInfoService.export(holdInfo, response);
		return null;
	}
}
