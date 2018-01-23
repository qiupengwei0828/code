package com.bd.controller.bd;

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

import com.base.controller.BaseController;
import com.base.page.Page;
import com.bd.entity.Certact;
import com.bd.entity.Store;
import com.bd.service.bd.CertactService;
import com.bd.service.bdstore.BdStoreService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.Dic;
import com.sys.entity.Org;
import com.sys.utils.DicUtils;

@Controller
@RequestMapping("/bd/")
public class BdController extends BaseController<Object> {

	@Autowired
	public CertactService certactService;
	@Autowired
	public BdStoreService service;
	
	/*
	 * 凭证分页查询
	 */
	@RequestMapping("certact/list")
	public String index(Certact certact,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	    Page<Certact> page = certactService.findByPage(certact, new Page<Certact >(request, response));
	    model.addAttribute("certact",certact);
		model.addAttribute("page",page);
		return "/bd/certact/list";
	}

	
	@RequestMapping("certact/form")
	public String form(Certact certact,Model model)  {	
		if(null!=certact.getCertactCode()){
			certact=certactService.find(certact).get(0);
			 model.addAttribute("certact", certact);
			}
		return "/bd/certact/form";
	}
	
	/* 
	 *凭证添加 
	 */
	@RequestMapping("certact/add")
	public String add(Certact certact,Model model) {
		certactService.insert(certact);
		return "redirect:/bd/certact/list";
	}
	
	@RequestMapping("certact/update")
	public String update(Certact certact,Model model) {		
		certactService.update(certact);
		return "redirect:/bd/certact/list";
	}
	@RequestMapping("certact/delete")
	public String delete(Certact certact,Model model) {		
		certactService.delete(certact);
		return "redirect:/bd/certact/list";
	}
	
	@RequestMapping("certact/exists")
	@ResponseBody
	public Map<String,Object> exists(Certact certact,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Certact> certactinfo = certactService.exists(certact);
		if (certactinfo.size() > 0) {
			map.put("msg", "已存在该单证");
		} else {
			map.put("msg", "none");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "certact/treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Certact certact = new Certact();
	    List<Dic> listType = DicUtils.getDicList("DIC_BD_CERTACTTYPE");
		for (int i = 0; i < listType.size(); i++) {
			Dic e = listType.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getpValue());
		    map.put("pId", e.getpValue());
			map.put("name", e.getpName());
			mapList.add(map);
			certact.setCertactType(e.getpValue());
			List<Certact> list = certactService.findCertactType(certact);
			for (int j = 0; j < list.size(); j++) {
				Certact ee = list.get(j);
				Map<String, Object> map1 = Maps.newHashMap();
				map1.put("id", ee.getCertactCode());
			    map1.put("pId", e.getpValue());
				map1.put("name", ee.getCertactName());
				mapList.add(map1);				
			}
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping("certact/getConfirm")
	public List<Map<String, Object>> getConfirm(Certact certact,Model model,HttpServletRequest request, HttpServletResponse response) {
		String orgNo = request.getParameter("orgNo");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Certact> list = certactService.findCertactType(certact);
		for (int j = 0; j < list.size(); j++) {
			Certact e = list.get(j);
			Map<String, Object> map1 = Maps.newHashMap();
			map1.put("id", e.getCertactCode());
			map1.put("name", e.getCertactName());
//			User currentAccount = AccountShiroUtil.getCurrentUser();
			Store store = new Store();
			store.setOrgNo(orgNo);
			store.setCertactCode(e.getCertactCode());
			Org org = new Org();
//			org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
//			org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
			List<Store> listNum = service.findNum(store,org);
			if(listNum.size()>0){
			    map1.put("num", listNum.get(0).getStoreNum());			
			}else{
				map1.put("num", 0);
			}
			mapList.add(map1);				
		}
		return mapList;
	}
}
