package com.cert.controller.jobcertinfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.page.Page;
import com.base.utils.security.AccountShiroUtil;
import com.cert.entity.CertJobCfg;
import com.cert.service.certjobcfg.CertJobCfgService;
import com.sys.entity.Org;
import com.sys.service.org.OrgService;

@Controller
@RequestMapping("/cert/jobcertinfo/")
public class JobCertInfoController extends BaseController<CertJobCfg> {

	@Autowired
	public CertJobCfgService service;

	@Autowired
	public OrgService orgService;

	@RequestMapping("index")
	public String index(CertJobCfg certJobCfg, Model model) {

		Org org = new Org();
		org.setOrgNo(AccountShiroUtil.getCurrentUser().getOrgNo());
		org = orgService.find(org).get(0);

		String orgNo = "";
		if (org.getLevelOrgNo().equals("1")) {
			orgNo = org.getOrgNo1st();
		} else {
			orgNo = org.getOrgNo2nd();
		}

		model.addAttribute("orgNo", orgNo);
		return "/cert/jobcertinfo/index";
	}

	@RequestMapping("list")
	public String list(CertJobCfg certJobCfg, Model model, HttpServletRequest request, HttpServletResponse response) {
		if (certJobCfg.getOrgNo() == null) {
			Org org = new Org();
			org.setOrgNo(AccountShiroUtil.getCurrentUser().getOrgNo());
			org = orgService.find(org).get(0);

			if (org.getLevelOrgNo().equals("1")) {
				certJobCfg.setOrgNo(org.getOrgNo1st());
			} else {
				certJobCfg.setOrgNo(org.getOrgNo2nd());
			}

		}
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		Page<CertJobCfg> page = service.findCertList(certJobCfg, new Page<CertJobCfg>(request, response));
		model.addAttribute("page", page);
		model.addAttribute("certJobCfg", certJobCfg);
		return "/cert/jobcertinfo/list";
	}

	/*
	 * 查询详情
	 */
	@RequestMapping("info")
	public String info(CertJobCfg certJobCfg, Model model) {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<CertJobCfg> list = service.findinfo(certJobCfg);
		model.addAttribute("list", list);
		return "/cert/jobcertinfo/info";
	}
}
