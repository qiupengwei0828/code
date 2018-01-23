package com.bd.controller.bdstore;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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

import com.base.page.Page;
import com.base.utils.security.AccountShiroUtil;
import com.bd.entity.Store;
import com.bd.service.bdstore.BdStoreService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.Org;
import com.sys.entity.User;
import com.sys.service.org.OrgService;


@Controller
@RequestMapping("/bd/bdstore")
public class BdStoreController {
	
	@Autowired
	public BdStoreService service;
	
	@Autowired
	public OrgService orgService;
	
	@RequestMapping("/info")
	public String infoData(Store store,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<Store> page = service.findByPage(store,org, new Page<Store >(request, response));	   
		model.addAttribute("page",page);
		model.addAttribute("store",store);
		return "/bd/bdstore/info";
	}

	@RequestMapping("/infoData")
	public String index(Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		return "/bd/orgTree/storelist";
	}
	@RequestMapping("/form")
	public String form(Store store,Model model)  {	
		if(null!=store.getId()){
			store=service.find(store).get(0);
			 model.addAttribute("store", store);
			}
		User currentAccount = AccountShiroUtil.getCurrentUser();
		store.setOrgNo(currentAccount.getOrgNo());
		model.addAttribute("store",store);
		return "/bd/bdstore/form";
	}
	
	@RequestMapping("/add")
	public String add(Store store,Model model) {
		service.insert(store);
		return "redirect:/bd/bdstore/info";
	}
	
	@RequestMapping("/update")
	public String update(Store store,Model model) {		
		service.update(store);
		return "redirect:/bd/bdstore/info?orgNo="+store.getOrgNo();
	}
	@RequestMapping("/delete")
	public String delete(Store store,Model model) {		
		service.delete(store);
		return "redirect:/bd/bdstore/info?orgNo="+store.getOrgNo();
	}
	
	@RequestMapping("/export")
	public String export(Store store,Model model,HttpServletResponse response) {	
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
		service.export(store,response,org);
		return null;
	}
	@RequestMapping("/findNum")
	@ResponseBody
	public Map<String,String> findNum(Store store,Model model) {	
		List<Store> list = service.find(store);
		Map<String,String> map = new HashMap<String,String>();
		if(list.size()>0){
			String data = list.get(0).getStoreNum().toString();			
			map.put("num", data);
			String str = list.get(0).getCertactName();
			map.put("name", str);
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = AccountShiroUtil.getCurrentUser();
		List<Org> list = orgService.findAllList(user.getOrgInfo());
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
	@RequestMapping("/importUse")
	public String importUse(Store store,Model model) {	
		model.addAttribute("store",store);
		return "/bd/bdstore/importUse";
	}
	@ResponseBody
	@RequestMapping(value = "treeDataType")
	public List<Map<String, Object>> treeDataType(@RequestParam(required = false) String extId, @RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = AccountShiroUtil.getCurrentUser();
		List<Org> list = orgService.findAllList(user.getOrgInfo());
		for (int i = 0; i < list.size(); i++) {
			if("1".equals(list.get(i).getBdFlag())){
				Org e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getOrgNo());
				map.put("pId", e.getpOrgNo());
				map.put("name", e.getOrgName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	@ResponseBody
	@RequestMapping("/getConfirm")
	public List<Map<String, Object>> getConfirm(Store store,Model model,HttpServletRequest request, HttpServletResponse response) {     
	    String orgNo = AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo();
	    String certactType = request.getParameter("certactType");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		store.setOrgNo(orgNo);
		store.setCertactType(certactType);
		List<Store> list = service.find(store);
		for (int j = 0; j < list.size(); j++) {
			Store e = list.get(j);
			Map<String, Object> map1 = Maps.newHashMap();
			map1.put("id", e.getCertactCode());
			map1.put("name", e.getCertactName());
			map1.put("num", e.getStoreNum());						
			mapList.add(map1);				
		}
		return mapList;
	}
}
