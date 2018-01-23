package com.bd.controller.bdplan;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.page.Page;
import com.base.utils.security.AccountShiroUtil;
import com.bd.entity.BdPlan;
import com.bd.entity.BdProcess;
import com.bd.service.bdplan.BdPlanDetailService;
import com.bd.service.bdplan.BdPlanService;
import com.bd.service.bdplanlog.BdPlanLogService;
import com.bd.service.bdplanlog.PlanService;
import com.sys.entity.Org;

@Controller
@RequestMapping("/bd/bdplanlog")
public class BdPlanLogController extends BaseController<Object> {

	@Autowired
	public BdPlanLogService bdPlanLogService;
	@Autowired
	public BdPlanDetailService bdPlanDetailService;
	@Autowired
	public PlanService planService;
	@Autowired
	public BdPlanService bdPlanService;
	
	
	@RequestMapping("/log")
	public String log(BdPlan bdPlan,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<BdPlan> page = bdPlanService.findByPageQuery(bdPlan,org, new Page<BdPlan >(request, response));
	    model.addAttribute("store",bdPlan);
		model.addAttribute("page",page);
		return "/bd/bdplanlog/index";  
	}
	@RequestMapping("/queryLog")
	public String queryLog(BdProcess bdProcess,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<BdProcess> page = bdPlanLogService.findByPage(bdProcess,org, new Page<BdProcess >(request, response));	    
		model.addAttribute("page",page);
		return "/bd/bdplanlog/queryLog";  
	}
    @ResponseBody
	@RequestMapping("/queryLogPag")
	public Page<BdProcess>  queryLogPag(BdProcess bdProcess,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<BdProcess> bdPlanlog = bdPlanLogService.findByPage(bdProcess,org, new Page<BdProcess >(request, response));	   
        return bdPlanlog; 
	}
//    @RequestMapping("/insert")
//	public String  insert(BdProcess bdProcess,Model model) throws UnsupportedEncodingException {
//    	Org org = AccountShiroUtil.getCurrentUser().getOrgInfo();
//    	bdProcess.setOrgNo(org.getOrgNo());
//    	bdProcess.setOrgName(org.getOrgName());
//    	bdProcess.setUserId(AccountShiroUtil.getCurrentUser().getUserName());
//    	bdProcess.setNodeName("超限提醒");
//    	bdProcess.setStartTime("1");
//    	bdProcess.setCertactName(java.net.URLDecoder.decode(bdProcess.getCertactName(), "UTF-8"));
//    	bdPlanLogService.insert(bdProcess);
//        return null; 
//	}
}

