package com.bd.controller.voucher;

import java.io.UnsupportedEncodingException;
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
import com.bd.entity.BdPlan;
import com.bd.entity.BdProcess;
import com.bd.entity.BdVoucherDetail;
import com.bd.entity.Store;
import com.bd.entity.StoreLog;
import com.bd.service.bdplan.BdPlanService;
import com.bd.service.bdplanlog.BdPlanLogService;
import com.bd.service.bdstore.BdStoreLogService;
import com.bd.service.bdstore.BdStoreService;
import com.bd.service.voucher.VoucherService;
import com.sys.entity.Org;

@Controller
@RequestMapping("/bd/voucher")
public class VoucherController extends BaseController<Object> {

	@Autowired
	public VoucherService voucherService;
	
	@Autowired
	public BdStoreLogService bdStoreLogService;
	
	@Autowired
	public BdStoreService bdStoreService;
	
	@Autowired
	public BdPlanService bdPlanService;
	
	@Autowired
	public BdPlanLogService bdPlanLogService;
	
	
	@RequestMapping("/list")
	public String list(BdVoucherDetail bdVoucherDetail,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<BdVoucherDetail> page = voucherService.findByPage(bdVoucherDetail,org, new Page<BdVoucherDetail >(request, response));
		model.addAttribute("page",page);
		model.addAttribute("bdVoucherDetail",bdVoucherDetail);
		return "/bd/voucher/list";
	}
	@RequestMapping("/update")
	public String update(BdVoucherDetail bdVoucherDetail,Model model) {
		voucherService.update(bdVoucherDetail);
		List<BdVoucherDetail> listVoucher =  voucherService.find(bdVoucherDetail);
		BdPlan bdPlan = new BdPlan();
		bdPlan.setStatus("05");
		bdPlan.setId(listVoucher.get(0).getPlanId());
		bdPlanService.set(bdPlan);
		BdProcess bdProcess = new BdProcess();
		bdProcess.setOrgNo(listVoucher.get(0).getOrgNo());
		bdProcess.setOrgName(listVoucher.get(0).getOrgName());
		bdProcess.setPlanNumber(listVoucher.get(0).getPlanNumber());
		bdProcess.setUserId(AccountShiroUtil.getCurrentUser().getUserName());
		bdProcess.setNodeName("核对");
		bdProcess.setOperatResu("("+listVoucher.get(0).getCertactCode()+"--"+listVoucher.get(0).getCertactName()+")入库数量："+listVoucher.get(0).getPurNum());
		bdPlanLogService.insert(bdProcess);
		StoreLog storeLog = new StoreLog();		
		storeLog.setCertactName(listVoucher.get(0).getCertactName());
		storeLog.setInNum(listVoucher.get(0).getPurNum());
		storeLog.setOrgName(listVoucher.get(0).getOrgName());
		storeLog.setUserId(listVoucher.get(0).getUserId());
		storeLog.setOrgNo(listVoucher.get(0).getOrgNo());
		storeLog.setCertactCode(listVoucher.get(0).getCertactCode());
		bdStoreLogService.insert(storeLog);
		Store store = new Store();
		store.setOrgNo(listVoucher.get(0).getOrgNo());
		store.setCertactCode(listVoucher.get(0).getCertactCode());
		List<Store> list = bdStoreService.find(store);
		if(list.size()>0){
			store.setCertactCode(list.get(0).getCertactCode());
    		store.setCertactName(list.get(0).getCertactName());
    		store.setCertactStore(list.get(0).getCertactStore());
    		store.setCertactType(list.get(0).getCertactType());
    		store.setCertactSplit(list.get(0).getCertactSplit());
    		store.setBusinessScope(list.get(0).getBusinessScope());
    		store.setOrgName(list.get(0).getOrgName());
    		store.setOrgNo(list.get(0).getOrgNo());
    		store.setRemark(list.get(0).getRemark());
    		store.setRepType(list.get(0).getRepType());
    		store.setStatus(list.get(0).getStatus());
			store.setId(list.get(0).getId());			
			store.setStoreNum(list.get(0).getStoreNum()+listVoucher.get(0).getPurNum());
			bdStoreService.update(store);
		}else{
			Store store1 = new Store();
			store1.setCertactCode(listVoucher.get(0).getCertactCode());
			List<Store> list1 = bdStoreService.find(store1);
			if(list1.size()==0){
				store.setOrgName(listVoucher.get(0).getOrgName());
				store.setCertactName(listVoucher.get(0).getCertactName());
				store.setStoreNum(listVoucher.get(0).getPurNum());
				bdStoreService.insert(store);
			}else{
				Store sto = list1.get(0);
				sto.setStoreNum(listVoucher.get(0).getPurNum());
				sto.setOrgName(listVoucher.get(0).getOrgName());
				sto.setOrgNo(listVoucher.get(0).getOrgNo());
				bdStoreService.insert(sto);
			}
		}
		
		return "redirect:/bd/voucher/list";
	}
}
