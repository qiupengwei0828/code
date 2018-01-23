package com.bd.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.utils.security.AccountShiroUtil;
import com.bd.entity.Store;
import com.bd.service.bdstore.BdStoreService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.Org;
import com.sys.entity.User;
import com.sys.service.org.OrgService;


@Controller
@RequestMapping("/bd/common")
public class CommonController {
	
	@Autowired
	public BdStoreService service;
	@Autowired
	public OrgService orgService;
	
	@RequestMapping("/findUser")
	@ResponseBody
	public Map<String,String> findNum(Store store,Model model) {	
		Map<String,String> map = new HashMap<String,String>();
		User currentAccount = AccountShiroUtil.getCurrentUser();
		String userName = currentAccount.getUserName();
        map.put("userName", userName);        
        String orgNo = currentAccount.getOrgInfo().getOwnDimOrgNo();
        map.put("orgNo", orgNo);
        Org org = new Org();
        org.setOrgNo(orgNo);
        String orgName = orgService.get(org).getOrgName();
        map.put("orgName", orgName);
		return map;
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
		User user = AccountShiroUtil.getCurrentUser();		
		user.getOrgInfo().setBdFlag("1");
		List<Org> list = orgService.findBDAllList(user.getOrgInfo());
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

