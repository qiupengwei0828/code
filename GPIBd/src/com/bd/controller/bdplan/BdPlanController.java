package com.bd.controller.bdplan;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.bd.entity.BdPlanDetail;
import com.bd.entity.BdPlanEntity;
import com.bd.entity.BdProcess;
import com.bd.entity.BdVoucherDetail;
import com.bd.service.bdplan.BdPlanDetailService;
import com.bd.service.bdplan.BdPlanService;
import com.bd.service.bdplanlog.BdPlanLogService;
import com.bd.service.voucher.VoucherService;
import com.sys.entity.Dic;
import com.sys.entity.Org;
import com.sys.utils.DicUtils;

@Controller
@RequestMapping("/bd/bdplan")
public class BdPlanController extends BaseController<Object> {

	@Autowired
	public BdPlanService bdPlanService;
	@Autowired
	public BdPlanDetailService bdPlanDetailService;
	@Autowired
	public VoucherService voucherService;
	@Autowired
	public BdPlanLogService bdPlanLogService;
	
	/**
	 * 请领单展示
	 * @param bdPlan
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/index")
	public String index(BdPlan bdPlan,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<BdPlan> page = bdPlanService.findByPage(bdPlan,org, new Page<BdPlan >(request, response));
	    model.addAttribute("store",bdPlan);
		model.addAttribute("page",page);
		return "/bd/bdplan/index";
	}
	/**
	 * 待审批单展示
	 * @param bdPlan
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/audit")
	public String audit(BdPlan bdPlan,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<BdPlan> page = bdPlanService.findByPage(bdPlan,org, new Page<BdPlan >(request, response));
		model.addAttribute("store",bdPlan);
		model.addAttribute("page",page);
		return "/bd/bdplan/audit";
	}

	
	@RequestMapping("/saveform")
	public String saveform(BdPlan bdPlan,Model model) throws UnsupportedEncodingException { 
		bdPlan.setStatus("02");		
		bdPlan.setQuarter(bdPlan.getQuarter().substring(0,bdPlan.getQuarter().indexOf("）")+1));
		List<BdPlan> listPlan =bdPlanService.find(bdPlan);
		if(listPlan==null||listPlan.size()==0){
		   bdPlanService.insert(bdPlan);
		}else{
		   bdPlanService.update(bdPlan);
		}
		setProcess(bdPlan);
		return "redirect:/bd/bdplan/index";
	}
	@RequestMapping("/form")
	public String insert(BdPlan bdPlan,Model model) throws UnsupportedEncodingException { 
		bdPlan.setStatus("01");
		bdPlan.setQuarter(bdPlan.getQuarter().substring(0,bdPlan.getQuarter().indexOf("）")+1));
		List<BdPlan> list =bdPlanService.find(bdPlan);		
		if(list==null||list.size()==0){
		   bdPlanService.insert(bdPlan);
		}else{
			bdPlanService.update(bdPlan);
		}
		return "redirect:/bd/bdplan/index";
	}
	
	@RequestMapping("/add")
	public String add(BdPlanEntity bdPlanEntity,Model model,HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("bdPlanEntity", bdPlanEntity);	
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dd = sdf.format(date);
		BdPlan bdPlan = new BdPlan();
		bdPlan.setPlanDate(dd);
		List<BdPlan> list = bdPlanService.findQuarter(dd);
		List<String> listData = new ArrayList<String>(); 
		if("1".equals(list.get(0).getQuarter())){			
			listData.add(0,"四季度（"+(Integer.parseInt(list.get(0).getYear())-1)+"）");
			listData.add(1,"一季度（"+list.get(0).getYear()+"）");
			listData.add(2,"二季度（"+list.get(0).getYear()+"）");
			listData.add(3,"三季度（"+list.get(0).getYear()+"）");
		}
		if("2".equals(list.get(0).getQuarter())){			
			listData.add(0,"一季度（"+list.get(0).getYear()+"）");
			listData.add(1,"二季度（"+list.get(0).getYear()+"）");
			listData.add(2,"三季度（"+list.get(0).getYear()+"）");
			listData.add(3,"四季度（"+list.get(0).getYear()+"）");
		}
		if("3".equals(list.get(0).getQuarter())){			
			listData.add(0,"二季度（"+list.get(0).getYear()+"）");
			listData.add(1,"三季度（"+list.get(0).getYear()+"）");
			listData.add(2,"四季度（"+list.get(0).getYear()+"）");
			listData.add(3, "一季度（"+(Integer.parseInt(list.get(0).getYear())+1)+"）");
		}
		if("4".equals(list.get(0).getQuarter())){			
			listData.add(0,"三季度（"+list.get(0).getYear()+"）");
			listData.add(1,"四季度（"+list.get(0).getYear()+"）");
			listData.add(2, "一季度（"+(Integer.parseInt(list.get(0).getYear())+1)+"）");
			listData.add(3, "二季度（"+(Integer.parseInt(list.get(0).getYear())+1)+"）");
		}
		model.addAttribute("list", listData);
		Org account = AccountShiroUtil.getCurrentUser().getOrgInfo();
		model.addAttribute("account", account);
		return "/bd/bdplan/form";
	}
	@RequestMapping("/detail")
	public String detail(BdPlan bdPlan,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	    BdPlanDetail bdPlanDetail = new BdPlanDetail();
		bdPlan = bdPlanService.selectData(bdPlan).get(0);
		bdPlanDetail.setPlanId(bdPlan.getId());
		Page<BdPlanDetail> page = bdPlanDetailService.findByPage(bdPlanDetail,new Page<BdPlanDetail >(request, response));
		BdProcess bdProcess = new BdProcess();
		bdProcess.setPlanNumber(bdPlan.getPlanNumber());
		Page<BdProcess> page1 = bdPlanLogService.findByPageDetail(bdProcess,new Page<BdProcess >(request, response));
		model.addAttribute("bdPlan",bdPlan);
		model.addAttribute("page",page);
		model.addAttribute("page1",page1);
		return "/bd/bdplan/detail";
	}
	@RequestMapping("/detailAdd")
	public String detailAdd(BdPlanDetail bdPlanDetail,Model model) throws UnsupportedEncodingException {
		model.addAttribute("bdPlanDetail",bdPlanDetail);
		return "/bd/bdplan/detailAdd";
	}
	
	@RequestMapping("/addDetail")
	@ResponseBody
	public String addDetail(BdPlanDetail bdPlanDetail,Model model) throws UnsupportedEncodingException {
		bdPlanDetail.setCertactName(java.net.URLDecoder.decode(bdPlanDetail.getCertactName(), "UTF-8"));
		bdPlanDetail.setIncreases(java.net.URLDecoder.decode(bdPlanDetail.getIncreases(), "UTF-8"));
		bdPlanDetailService.insert(bdPlanDetail);
		return "success";
	}
	@RequestMapping("/addDetailForm")
	public String addDetailForm(BdPlanDetail bdPlanDetail,Model model) throws UnsupportedEncodingException {
		List<BdPlanDetail> list = bdPlanDetailService.find(bdPlanDetail);
		if(list.size()>0){
			bdPlanDetailService.update(bdPlanDetail);
		}else{
			bdPlanDetailService.insert(bdPlanDetail);
		}
		return "redirect:/bd/bdplan/index";
	}
	@RequestMapping("/check")
	public String check(BdPlan bdPlan,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		BdPlanDetail bdPlanDetail = new BdPlanDetail();
		bdPlan = bdPlanService.selectData(bdPlan).get(0);
		bdPlanDetail.setPlanId(bdPlan.getId());
		Page<BdPlanDetail> page = bdPlanDetailService.findByPage(bdPlanDetail,new Page<BdPlanDetail >(request, response));
		BdProcess bdProcess = new BdProcess();
		bdProcess.setPlanNumber(bdPlan.getPlanNumber());
		Page<BdProcess> page1 = bdPlanLogService.findByPageDetail(bdProcess,new Page<BdProcess >(request, response));
		model.addAttribute("bdPlan",bdPlan);
		model.addAttribute("page",page);
		model.addAttribute("page1",page1);
		return "/bd/bdplan/check";
	}
	@RequestMapping("/agree")
	public String agree(BdPlan bdPlan,Model model) throws UnsupportedEncodingException {
		String remark = java.net.URLDecoder.decode(bdPlan.getRemark(), "UTF-8");
		bdPlan = bdPlanService.find(bdPlan).get(0);
		if("".equals(remark)||remark==null){
			bdPlan.setRemark("同意");
		}else{
			bdPlan.setRemark(remark);
		}
		bdPlan.setStatus("03");
		bdPlanService.update(bdPlan);
		List<BdPlan> list = bdPlanService.find(bdPlan);
		BdPlanDetail bdPlanDetail = new BdPlanDetail();
		bdPlanDetail.setPlanId(bdPlan.getId());
		List<BdPlanDetail> listDetail = bdPlanDetailService.find(bdPlanDetail);
		for(int i=0;i<listDetail.size();i++){
			BdVoucherDetail bdVoucherDetail = new BdVoucherDetail();
			bdVoucherDetail.setOrgNo(list.get(0).getPlanOrgno());
			bdVoucherDetail.setOrgName(list.get(0).getPlanOrgname());
			bdVoucherDetail.setPlanDate(list.get(0).getPlanDate());
			bdVoucherDetail.setQuarter(list.get(0).getQuarter());
			bdVoucherDetail.setPlanNumber(list.get(0).getPlanNumber());
			bdVoucherDetail.setPlanId(listDetail.get(i).getPlanId());
			bdVoucherDetail.setPurNum(listDetail.get(i).getPlanNum());
			bdVoucherDetail.setCertactCode(listDetail.get(i).getCertactCode());
			bdVoucherDetail.setCertactName(listDetail.get(i).getCertactName());
			bdVoucherDetail.setPlanNum(listDetail.get(i).getPlanNum());
			bdVoucherDetail.setUserId(list.get(0).getUserId());
			voucherService.insert(bdVoucherDetail);			
		}
		setProcess(list.get(0));		
		return "redirect:/bd/bdplan/audit";
	}
	@RequestMapping("/back")
	public String back(BdPlan bdPlan,Model model) throws UnsupportedEncodingException {		
		String remark = java.net.URLDecoder.decode(bdPlan.getRemark(), "UTF-8");
		bdPlan = bdPlanService.find(bdPlan).get(0); 
		bdPlan.setStatus("04");
		bdPlan.setRemark(remark);
		bdPlanService.update(bdPlan);
		List<BdPlan> list = bdPlanService.find(bdPlan);
		setProcess(list.get(0));
		return "redirect:/bd/bdplan/audit";
	}
	
	@RequestMapping("/getDicList")
	@ResponseBody
	public List<Dic> getDicList() throws UnsupportedEncodingException {
		List<Dic> list =  DicUtils.getDicList("DIC_BD_CERTACTTYPE");
		return list; 
	}
	@RequestMapping("/log")
	public String log(BdPlan bdPlan,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<BdPlan> page = bdPlanService.findByPage(bdPlan,org, new Page<BdPlan >(request, response));
		model.addAttribute("page",page);
		return "/bd/bdplanlog/index";
	}
	@RequestMapping("/delete")
	public String delete(BdPlan bdPlan,Model model) throws UnsupportedEncodingException {
		bdPlanService.delete(bdPlan);
		BdPlanDetail bdPlanDetail = new BdPlanDetail();
		bdPlanDetail.setPlanId(bdPlan.getId());
		bdPlanDetailService.delete(bdPlanDetail);
		return "redirect:/bd/bdplan/index";
	}
	@RequestMapping("/updateform")
	public String updateform(BdPlan bdPlan,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	    bdPlan=bdPlanService.find(bdPlan).get(0);
	    bdPlan.setRemark("");
	    model.addAttribute("bdPlan", bdPlan);
	    BdPlanDetail bdPlanDetail = new BdPlanDetail();
	    bdPlanDetail.setPlanId(bdPlan.getId());
		Page<BdPlanDetail> page = bdPlanDetailService.findByPage(bdPlanDetail,new Page<BdPlanDetail >(request, response));
		model.addAttribute("page",page);
		for(int i=0;i<10;i++){
			if(i<page.getList().size()){
			bdPlanDetail = page.getList().get(i);	
			model.addAttribute("bdPlanDetail"+i,bdPlanDetail);
			}else{
				BdPlanDetail bdPlanDetail1 = new BdPlanDetail();
				model.addAttribute("bdPlanDetail"+i,bdPlanDetail1);
			}									
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dd = sdf.format(date);
		List<BdPlan> list = bdPlanService.findQuarter(dd);
		List<String> listData = new ArrayList<String>(); 
		if("1".equals(list.get(0).getQuarter())){			
			listData.add(0,"四季度（"+(Integer.parseInt(list.get(0).getYear())-1)+"）");
			listData.add(1,"一季度（"+list.get(0).getYear()+"）");
			listData.add(2,"二季度（"+list.get(0).getYear()+"）");
			listData.add(3,"三季度（"+list.get(0).getYear()+"）");
		}
		if("2".equals(list.get(0).getQuarter())){			
			listData.add(0,"一季度（"+list.get(0).getYear()+"）");
			listData.add(1,"二季度（"+list.get(0).getYear()+"）");
			listData.add(2,"三季度（"+list.get(0).getYear()+"）");
			listData.add(3,"四季度（"+list.get(0).getYear()+"）");
		}
		if("3".equals(list.get(0).getQuarter())){			
			listData.add(0,"二季度（"+list.get(0).getYear()+"）");
			listData.add(1,"三季度（"+list.get(0).getYear()+"）");
			listData.add(2,"四季度（"+list.get(0).getYear()+"）");
			listData.add(3, "一季度（"+(Integer.parseInt(list.get(0).getYear())+1)+"）");
		}
		if("4".equals(list.get(0).getQuarter())){			
			listData.add(0,"三季度（"+list.get(0).getYear()+"）");
			listData.add(1,"四季度（"+list.get(0).getYear()+"）");
			listData.add(2, "一季度（"+(Integer.parseInt(list.get(0).getYear())+1)+"）");
			listData.add(3, "二季度（"+(Integer.parseInt(list.get(0).getYear())+1)+"）");
		}
		for(int i=0;i<listData.size();i++){			
			if(bdPlan.getQuarter().equals(listData.get(i))){
				String tmp = listData.get(1);
				listData.remove(1);
				listData.add(1,bdPlan.getQuarter());
				listData.remove(i);
				listData.add(i,tmp);				
				break;
			}
		}
		model.addAttribute("list", listData);
		 return "/bd/bdplan/updateform";
	}
	@RequestMapping("/find")
	@ResponseBody
	public String find(BdPlan bdPlan,Model model) throws UnsupportedEncodingException {
		bdPlan.setQuarter(java.net.URLDecoder.decode(bdPlan.getQuarter(), "UTF-8"));
		List<BdPlan> list = bdPlanService.find(bdPlan);
		String msg="";
        if(list.size()>0){
        	msg = list.get(0).getId().toString();
        }else{
        	msg = "0";
        }
        
		return msg;
	}
	
	/**
	 * 流程日志
	 * @param bdPlan
	 */
	public void setProcess(BdPlan bdPlan){       
		BdProcess bdProcess = new BdProcess();
		bdProcess.setOrgNo(bdPlan.getPlanOrgno());
		bdProcess.setUserId(bdPlan.getUserId());
		bdProcess.setOrgName(bdPlan.getPlanOrgname());
		bdProcess.setPlanNumber(bdPlan.getPlanNumber());
		if("02".equals(bdPlan.getStatus())){
			bdProcess.setNodeName("提交");
			bdProcess.setOperatResu("提交");
		}else if("03".equals(bdPlan.getStatus())){
			bdProcess.setUserId(AccountShiroUtil.getCurrentUser().getUserName());
			bdProcess.setNodeName("审批");
			bdProcess.setOperatResu("同意");
			bdProcess.setOperatRemark(bdPlan.getRemark());
		}else if("04".equals(bdPlan.getStatus())){
			bdProcess.setUserId(AccountShiroUtil.getCurrentUser().getUserName());
			bdProcess.setNodeName("审批");
			bdProcess.setOperatResu("退回");
			bdProcess.setOperatRemark(bdPlan.getRemark());
		}else if("05".equals(bdPlan.getStatus())){
			bdProcess.setUserId(AccountShiroUtil.getCurrentUser().getUserName());
			bdProcess.setNodeName("核对");
			bdProcess.setOperatResu("归档");
		}
		bdPlanLogService.insert(bdProcess);
	}
}

