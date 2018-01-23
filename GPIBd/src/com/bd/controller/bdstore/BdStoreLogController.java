package com.bd.controller.bdstore;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.page.Page;
import com.base.utils.security.AccountShiroUtil;
import com.bd.entity.StoreLog;
import com.bd.service.bdstore.BdStoreLogService;
import com.sys.entity.Org;


@Controller
@RequestMapping("/bd/bdstorelog")
public class BdStoreLogController {
	
	@Autowired
	public BdStoreLogService service;
	
	@RequestMapping("/log")
	public String log(StoreLog storeLog,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<StoreLog> page = service.findByPage(storeLog,org, new Page<StoreLog >(request, response));	   
		model.addAttribute("page",page);
		model.addAttribute("storeLog",storeLog);
		return "/bd/bdstore/log";
	}
}
