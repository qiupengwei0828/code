package com.sys.controller.org;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.base.controller.BaseController;
import com.base.utils.TimeUtils;
import com.base.utils.security.AccountShiroUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.Org;
import com.sys.entity.User;
import com.sys.service.org.OrgService;

/**
 * @ClassName:
 * 
 * @Description:
 * 
 * @author: wind
 * @version: V1.0
 * @Createdate: 2016年2月16日
 * 
 */

@Controller
@RequestMapping("/sys/org/")
public class OrgController extends BaseController<Org> {

	@Autowired
	public OrgService service;

	@RequestMapping(value = { "index" })
	public String index(Org org, Model model) {
		return "/sys/org/index";
	}

	/*
	 * 查询机构列表
	 */
	@RequestMapping(value = { "list" })
	public String list(Org org, Model model) {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		model.addAttribute("list", service.findAllList(org));
		return "/sys/org/list";
	}

	/*
	 * 查询机构列表
	 */
	@RequestMapping(value = { "findAllTree" })
	public String findAllTree(Org org, Model model) {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		//
		User user = AccountShiroUtil.getCurrentUser();
		org.setOrgDimField(user.getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(user.getOrgInfo().getOwnDimOrgNo());
		model.addAttribute("list", service.findAllTree(org));
		return "/sys/org/list";
	}

	/*
	 * 机构修改/机构添加
	 */
	@RequestMapping(value = "form")
	public String form(Org org, Model model, RedirectAttributes redirectAttributes) {
		boolean isRoot = false;
		if (org.getOrgNo() != null || "".equals(org.getOrgNo())) {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			List<Org> orgList = service.find(org);
			Org orgInfo = orgList.get(0);
			if ("0".equals(orgInfo.getpOrgNo())) {
				isRoot = true;
			}
			model.addAttribute("org", orgInfo);
		} else {
			model.addAttribute("org", org);
		}
		if (isRoot) {
			addMessage(redirectAttributes, "不允许修改顶级机构！");
			return "redirect:" + "/sys/org/findAllTree";
		} else {
			return "/sys/org/form";
		}

	}

	@RequestMapping(value = "insert")
	public String insert(Org org, Model model, RedirectAttributes redirectAttributes) {
		try {
			User user = AccountShiroUtil.getCurrentUser();
			String userName = user.getUserName();
			org.setCrtUser(userName);
			String nowTime = TimeUtils.getTime();
			org.setCrtTime(nowTime);
			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
			service.insert(org);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/sys/org/findAllTree";
	}

	@RequestMapping(value = "update")
	public String update(Org org, Model model, RedirectAttributes redirectAttributes) {
		try {
			List<Org> orgInfo = service.find(org);
			if ("0".equals(orgInfo.get(0).getpOrgNo())) {
				addMessage(redirectAttributes, "不允许修改顶级机构或编号空");
			} else {
				addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
				service.update(org);
				addMessage(redirectAttributes, "修改机构成功");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/sys/org/findAllTree";
	}

	@RequestMapping(value = "delete")
	public String delete(Org org, RedirectAttributes redirectAttributes) {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Org> orgInfo = service.find(org);
		if ("0".equals(orgInfo.get(0).getpOrgNo())) {
			addMessage(redirectAttributes, "删除机构失败, 不允许删除顶级机构或编号空");
		} else {
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			service.delete(org);
			addMessage(redirectAttributes, "删除机构成功");
		}
		return "redirect:" + "/sys/org/findAllTree";
	}

	/*
	 * 获取机构JSON数据。
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		// 选取用户所在本级机构所属的本级及下级所有机构
		Org org = new Org();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Org> list = service.findAllList(org);
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
}
