package com.cert.controller.certstaffmanage;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.page.Page;
import com.base.utils.security.AccountShiroUtil;
import com.cert.entity.Cert;
import com.cert.entity.HoldInfo;
import com.cert.service.cert.CertService;
import com.cert.service.holdinfo.HoldInfoService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.Org;
import com.sys.entity.User;
import com.sys.service.org.OrgService;

@Controller
@RequestMapping("/cert/certStaffManage/")
public class CertStaffManageController extends BaseController<HoldInfo> {

	@Autowired
	public HoldInfoService service;

	@Autowired
	public OrgService orgService;

	@Autowired
	public CertService certService;

	/*
	 * 員工持證情況管理首页
	 */
	@RequestMapping("index")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

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

		return "/cert/certstaffmanage/index";
	}

	/*
	 * 获取机构JSON数据。
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		/* 获取当前登陆用户 */
		User user = AccountShiroUtil.getCurrentUser();
		Org org = user.getOrgInfo();
		// 选取用户所在本级机构所属的本级及下级所有机构
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Org> list = orgService.findAllList(org);
		for (int i = 0; i < list.size(); i++) {
			Org e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getOrgNo());
			map.put("pId", e.getpOrgNo());
			map.put("name", e.getOrgName());
			mapList.add(map);
		}
		return mapList;
	}

	@RequestMapping("list")
	public String list(HoldInfo holdInfo, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// 查询信息_证书
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Cert> certList = certService.findAllList(null);
		model.addAttribute("certList", certList);

		if (holdInfo.getOrgNo() == null) {
			holdInfo.setOrgNo(AccountShiroUtil.getCurrentUser().getOrgNo());
		}

		// holdInfo.setStatus("1");

		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		Page<HoldInfo> page = service.findStaffCert(holdInfo, new Page<HoldInfo>(request, response));
		model.addAttribute("page", page);
		model.addAttribute("holdInfo", holdInfo);
		return "/cert/certstaffmanage/list";
	}

	@RequestMapping("info")
	public String info(HoldInfo holdInfo, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		// 证书扫描件（img）
		List<HoldInfo> imglist = service.findHoldCertInfo(holdInfo);
		// 持证员工信息
		holdInfo = imglist.get(0);
		// HoldInfo info = service.findHoldCertInfo(holdInfo).get(0);
		// List<HoldInfo> list = new ArrayList<HoldInfo>();
		// list.add(info);
		// model.addAttribute("list", list);
		model.addAttribute("holdInfo", holdInfo);
		model.addAttribute("imglist", imglist);
		return "/cert/certstaffmanage/info";
	}
}
