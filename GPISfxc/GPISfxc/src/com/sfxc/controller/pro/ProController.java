package com.sfxc.controller.pro;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.page.Page;
import com.base.utils.security.AccountShiroUtil;
import com.sfxc.entity.ProCheckWsinfo;
import com.sfxc.entity.ProDealInfo;
import com.sfxc.entity.ProQueryInfo;
import com.sfxc.entity.ProResultBxgxx;
import com.sfxc.entity.ProResultDlrzxx;
import com.sfxc.entity.ProResultJjxx;
import com.sfxc.entity.ProResultJyxx;
import com.sfxc.entity.ProResultPosxx;
import com.sfxc.entity.ProResultZhxx;
import com.sfxc.service.ProCheckWsinfoService;
import com.sfxc.service.ProDealInfoService;
import com.sfxc.service.ProQueryInfoService;
import com.sfxc.service.ProResultBxgxxService;
import com.sfxc.service.ProResultDlrzxxService;
import com.sfxc.service.ProResultJjxxService;
import com.sfxc.service.ProResultJyxxService;
import com.sfxc.service.ProResultPosxxService;
import com.sfxc.service.ProResultZhxxService;
import com.sfxc.task.FeedBackProMessage;


@Controller
@RequestMapping("/procuratorate")
public class ProController {
	@Autowired
	public ProQueryInfoService proQueryInfoService;
	
	@Autowired
	public ProCheckWsinfoService proCheckWsinfoService;
	
	@Autowired
	public ProDealInfoService proDealInfoService;
	
	@Autowired
	public ProResultBxgxxService proResultBxgxxService;
	
	@Autowired
	public ProResultDlrzxxService proResultDlrzxxService;
	
	@Autowired
	public ProResultJjxxService proResultJjxxService;
	
	@Autowired
	public ProResultPosxxService proResultPosxxService;	
	
	@Autowired
	public ProResultZhxxService proResultZhxxService;
	
	@Autowired
	public ProResultJyxxService proResultJyxxService;

	/**
	 * 待处理请求列表
	 * @param proQueryInfo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/deal/undealList")
	public String undoneList(ProQueryInfo proQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		proQueryInfo.setStatus("1");
		Page<ProQueryInfo> page = proQueryInfoService.findByPage(proQueryInfo, new Page<ProQueryInfo>(request,response));
		model.addAttribute("page",page);
		model.addAttribute("proQueryInfo",proQueryInfo);
		return "/sfxc/pro/undealList";
	}
	/**
	 * 已反馈的列表
	 * @author Panwf
	 * @date 2016年7月6日
	 * @since:
	 */
	@RequestMapping("/deal/dealedList")
	public String dealedList(ProQueryInfo proQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		proQueryInfo.setStatus("2");
		Page<ProQueryInfo> page = proQueryInfoService.findByPage(proQueryInfo, new Page<ProQueryInfo>(request,response));
		model.addAttribute("page",page);
		model.addAttribute("proQueryInfo",proQueryInfo);
		return "/sfxc/pro/dealedList";
	}
	
	/**
	 * 待审核列表
	 * @param proQueryInfo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	 
	@RequestMapping("/check/uncheckList")
	public String list(ProQueryInfo proQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		proQueryInfo.setStatus("0");
		Page<ProQueryInfo> page = proQueryInfoService.findByPage(proQueryInfo, new Page<ProQueryInfo>(request,response));
		model.addAttribute("page",page);
		model.addAttribute("proQueryInfo",proQueryInfo);
		return "/sfxc/pro/uncheckList";
	}
	/**
	 * 已审核列表
	 * @param proQueryInfo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/check/checkedList")
	public String checkedList(ProQueryInfo proQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		proQueryInfo.setStatus("3");
		Page<ProQueryInfo> page = proQueryInfoService.findByPage(proQueryInfo, new Page<ProQueryInfo>(request,response));
		model.addAttribute("page",page);
		model.addAttribute("proQueryInfo",proQueryInfo);
		return "/sfxc/pro/checkedList";
	}
	/**
	 * 返回到核查页面，进行核查动作
	 * @param proQueryInfo
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/check/check")
	public String check(ProQueryInfo proQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proQueryInfo = proQueryInfoService.find(proQueryInfo).get(0);
		ProCheckWsinfo  proCheckWsinfo = new ProCheckWsinfo();
		proCheckWsinfo.setQueryId(proQueryInfo.getQueryId());
		Page<ProCheckWsinfo> page = proCheckWsinfoService.findByPage(proCheckWsinfo, new Page<ProCheckWsinfo>(request,response));
		model.addAttribute("proQueryInfo",proQueryInfo);
		model.addAttribute("page",page);
		return "/sfxc/pro/check";
	}
	/**
	 * 查看详情
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/check/checked")
	public String checked(ProQueryInfo proQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proQueryInfo = proQueryInfoService.find(proQueryInfo).get(0);
		ProCheckWsinfo  proCheckWsinfo = new ProCheckWsinfo();
		proCheckWsinfo.setQueryId(proQueryInfo.getQueryId());
		Page<ProCheckWsinfo> page = proCheckWsinfoService.findByPage(proCheckWsinfo, new Page<ProCheckWsinfo>(request,response));
		model.addAttribute("proQueryInfo",proQueryInfo);
		model.addAttribute("page",page);
		return "/sfxc/pro/checked";
	}
	/**
	 * 审核通过更新状态
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/check/agree")
	public String agree(ProDealInfo proDealInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proDealInfo.setDealstaus("1");
		proDealInfo.setExaminer(AccountShiroUtil.getCurrentUser().getUserName());
		proDealInfoService.update(proDealInfo);
		return "redirect:/procuratorate/check/uncheckList";
	}
	/**
	 * 审核通过更新状态
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/deal/feed")
	public String feed(ProDealInfo proDealInfo, Model model){	
		proDealInfo.setDealstaus("2");
		proDealInfo.setBacker(AccountShiroUtil.getCurrentUser().getUserName());
		proDealInfoService.update(proDealInfo);
		ProResultBxgxx  proResultBxgxx = new ProResultBxgxx();
		proResultBxgxx.setBdhm(proDealInfo.getBdhm());
		List<ProResultBxgxx> listBxgxxs = proResultBxgxxService.findAllList(proResultBxgxx);
		ProResultDlrzxx proResultDlrzxx = new ProResultDlrzxx();
		proResultDlrzxx.setBdhm(proDealInfo.getBdhm());
		List<ProResultDlrzxx> listDlrzxx = proResultDlrzxxService.findAllList(proResultDlrzxx);
		ProResultJjxx proResultJjxx = new ProResultJjxx();
		proResultJjxx.setBdhm(proDealInfo.getBdhm());
		List<ProResultJjxx> listJjxx = proResultJjxxService.findAllList(proResultJjxx);
		ProResultPosxx proResultPosxx = new ProResultPosxx();
		proResultPosxx.setBdhm(proDealInfo.getBdhm());
		List<ProResultPosxx> listPosxxs = proResultPosxxService.findAllList(proResultPosxx);
		ProResultZhxx proResultZhxx = new ProResultZhxx();
		proResultZhxx.setBdhm(proDealInfo.getBdhm());
		List<ProResultZhxx> listZhxx = proResultZhxxService.findAllList(proResultZhxx);
		ProResultJyxx proResultJyxx = new ProResultJyxx();
		proResultJyxx.setBdhm(proDealInfo.getBdhm());
		List<ProResultJyxx> listJyxx = proResultJyxxService.findAllList(proResultJyxx);
        FeedBackProMessage feedBackMessage = new FeedBackProMessage();
        feedBackMessage.feedBackMessage(listBxgxxs,listDlrzxx,listJjxx,listPosxxs,listZhxx,listJyxx,proDealInfo.getBdhm());
		return "redirect:/procuratorate/deal/undealList";
	}
	/**
	 * 审核未通过，更新状态
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/check/back")
	public String back(ProDealInfo proDealInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proDealInfo.setDealstaus("-1");
		proDealInfo.setExaminer(AccountShiroUtil.getCurrentUser().getUserName());
		proDealInfoService.update(proDealInfo);
		return "redirect:/procuratorate/check/uncheckList";
	}
	/**
	 * 反馈信息,账户信息
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/deal/feedback")
	public String feedback(ProQueryInfo proQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		String flag = request.getParameter("flag");
		proQueryInfo = proQueryInfoService.find(proQueryInfo).get(0);
		ProCheckWsinfo  proCheckWsinfo = new ProCheckWsinfo();
		proCheckWsinfo.setQueryId(proQueryInfo.getQueryId());
		model.addAttribute("proQueryInfo",proQueryInfo);
		ProResultZhxx proResultZhxx = new ProResultZhxx();
		model.addAttribute("proResultZhxx",proResultZhxx);
		proResultZhxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultZhxx> list1 = proResultZhxxService.findAllList(proResultZhxx);
		model.addAttribute("list1",list1);	
		ProResultJyxx proResultJyxx = new ProResultJyxx();
		model.addAttribute("proResultJyxx",proResultJyxx);
		proResultJyxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultJyxx> list2 = proResultJyxxService.findAllList(proResultJyxx);
		model.addAttribute("list2",list2);	
		ProResultBxgxx proResultBxgxx = new ProResultBxgxx();
		model.addAttribute("proResultBxgxx",proResultBxgxx);
		proResultBxgxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultBxgxx> list3 = proResultBxgxxService.findAllList(proResultBxgxx);
		model.addAttribute("list3",list3);
		ProResultDlrzxx proResultDlrzxx = new ProResultDlrzxx();
		model.addAttribute("proResultDlrzxx",proResultDlrzxx);
		proResultDlrzxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultDlrzxx> list4 = proResultDlrzxxService.findAllList(proResultDlrzxx);
		model.addAttribute("list4",list4);
		ProResultJjxx proResultJjxx = new ProResultJjxx();
		model.addAttribute("proResultJjxx",proResultJjxx);
		proResultJjxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultJjxx> list5 = proResultJjxxService.findAllList(proResultJjxx);
		model.addAttribute("list5",list5);	
		ProResultPosxx proResultPosxx = new ProResultPosxx();
		model.addAttribute("proResultPosxx",proResultPosxx);
		proResultPosxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultPosxx> list6 = proResultPosxxService.findAllList(proResultPosxx);
		model.addAttribute("list6",list6);
		if("".equals(flag)||flag==null){
			model.addAttribute("flag",1);
		}else{
			model.addAttribute("flag",flag);
		}
		return "/sfxc/pro/feedback";
	}
	/**
	 * 查看反馈详情信息
	 * @author Panwf
	 * @date 2016年7月6日
	 * @since:
	 */
	@RequestMapping("/deal/queryDetail")
	public String queryDetail(ProQueryInfo proQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		proQueryInfo = proQueryInfoService.find(proQueryInfo).get(0);
		ProCheckWsinfo  proCheckWsinfo = new ProCheckWsinfo();
		proCheckWsinfo.setQueryId(proQueryInfo.getQueryId());
		model.addAttribute("proQueryInfo",proQueryInfo);
		ProResultZhxx proResultZhxx = new ProResultZhxx();
		model.addAttribute("proResultZhxx",proResultZhxx);
		proResultZhxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultZhxx> list1 = proResultZhxxService.findAllList(proResultZhxx);
		model.addAttribute("list1",list1);	
		ProResultJyxx proResultJyxx = new ProResultJyxx();
		model.addAttribute("proResultJyxx",proResultJyxx);
		proResultJyxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultJyxx> list2 = proResultJyxxService.findAllList(proResultJyxx);
		model.addAttribute("list2",list2);	
		ProResultBxgxx proResultBxgxx = new ProResultBxgxx();
		model.addAttribute("proResultBxgxx",proResultBxgxx);
		proResultBxgxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultBxgxx> list3 = proResultBxgxxService.findAllList(proResultBxgxx);
		model.addAttribute("list3",list3);
		ProResultDlrzxx proResultDlrzxx = new ProResultDlrzxx();
		model.addAttribute("proResultDlrzxx",proResultDlrzxx);
		proResultDlrzxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultDlrzxx> list4 = proResultDlrzxxService.findAllList(proResultDlrzxx);
		model.addAttribute("list4",list4);
		ProResultJjxx proResultJjxx = new ProResultJjxx();
		model.addAttribute("proResultJjxx",proResultJjxx);
		proResultJjxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultJjxx> list5 = proResultJjxxService.findAllList(proResultJjxx);
		model.addAttribute("list5",list5);	
		ProResultPosxx proResultPosxx = new ProResultPosxx();
		model.addAttribute("proResultPosxx",proResultPosxx);
		proResultPosxx.setBdhm(proQueryInfo.getQueryId());
		List<ProResultPosxx> list6 = proResultPosxxService.findAllList(proResultPosxx);
		model.addAttribute("list6",list6);
		return "/sfxc/pro/queryDetail";
	}
	/**
	 * 保存反馈信息
	 * @author Panwf
	 * @date 2016年7月6日
	 * @since:
	 */
	@RequestMapping("/deal/addJyxx")
	@ResponseBody
	public List<ProResultJyxx> addJyxx(ProResultJyxx proResultJyxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(proResultJyxx.getKeyid())||proResultJyxx.getKeyid()==null){
			proResultJyxxService.insert(proResultJyxx);
		}else{
			proResultJyxxService.update(proResultJyxx);
		}		
		List<ProResultJyxx> list2 = proResultJyxxService.findAllList(proResultJyxx);
		return list2;
	}
	@RequestMapping("/deal/addBxgxx")
	@ResponseBody
	public List<ProResultBxgxx> addBxgxx(ProResultBxgxx proResultBxgxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(proResultBxgxx.getKeyid())||proResultBxgxx.getKeyid()==null){
			proResultBxgxxService.insert(proResultBxgxx);
		}else{
			proResultBxgxxService.update(proResultBxgxx);
		}		
		List<ProResultBxgxx> list3 = proResultBxgxxService.findAllList(proResultBxgxx);
		return list3;
	}
	@RequestMapping("/deal/addDlrzxx")
	@ResponseBody
	public List<ProResultDlrzxx> addDlrzxx(ProResultDlrzxx proResultDlrzxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(proResultDlrzxx.getKeyid())||proResultDlrzxx.getKeyid()==null){
			proResultDlrzxxService.insert(proResultDlrzxx);
		}else{
			proResultDlrzxxService.update(proResultDlrzxx);
		}		
		List<ProResultDlrzxx> list5 = proResultDlrzxxService.findAllList(proResultDlrzxx);
		return list5;
	}
	@RequestMapping("/deal/addJjxx")
	@ResponseBody
	public List<ProResultJjxx> addJjxx(ProResultJjxx proResultJjxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(proResultJjxx.getKeyid())||proResultJjxx.getKeyid()==null){
			proResultJjxxService.insert(proResultJjxx);
		}else{
			proResultJjxxService.update(proResultJjxx);
		}		
		List<ProResultJjxx> list6 = proResultJjxxService.findAllList(proResultJjxx);
		return list6;
	}
	@RequestMapping("/deal/addPosxx")
	@ResponseBody
	public List<ProResultPosxx> addPosxx(ProResultPosxx proResultPosxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(proResultPosxx.getKeyid())||proResultPosxx.getKeyid()==null){
			proResultPosxxService.insert(proResultPosxx);
		}else{
			proResultPosxxService.update(proResultPosxx);
		}		
		List<ProResultPosxx> list4 = proResultPosxxService.findAllList(proResultPosxx);
		return list4;
	}
	@RequestMapping("/deal/addZhxx")
	@ResponseBody
	public List<ProResultZhxx> addZhxx(ProResultZhxx proResultZhxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(proResultZhxx.getKeyid())||proResultZhxx.getKeyid()==null){
			proResultZhxxService.insert(proResultZhxx);
		}else{
			proResultZhxxService.update(proResultZhxx);
		}		
		List<ProResultZhxx> list1 = proResultZhxxService.findAllList(proResultZhxx);
		return list1;
	}	
	/**
	 * 修改反馈信息
	 * @author Panwf
	 * @date 2016年7月6日
	 * @since:
	 */
	@RequestMapping("/deal/updateJyxx")
	@ResponseBody
	public ProResultJyxx updateJyxx(ProResultJyxx proResultJyxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultJyxx = proResultJyxxService.findAllList(proResultJyxx).get(0);
		return proResultJyxx;
	}
	@RequestMapping("/deal/updateBxgxx")
	@ResponseBody
	public ProResultBxgxx updateBxgxx(ProResultBxgxx proResultBxgxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultBxgxx = proResultBxgxxService.findAllList(proResultBxgxx).get(0);
		return proResultBxgxx;
	}
	@RequestMapping("/deal/updateDlrzxx")
	@ResponseBody
	public ProResultDlrzxx updateDlrzxx(ProResultDlrzxx proResultDlrzxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {			
		proResultDlrzxx = proResultDlrzxxService.findAllList(proResultDlrzxx).get(0);
		return proResultDlrzxx;
	}
	@RequestMapping("/deal/updateJjxx")
	@ResponseBody
	public ProResultJjxx updateJjxx(ProResultJjxx proResultJjxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultJjxx = proResultJjxxService.findAllList(proResultJjxx).get(0);
		return proResultJjxx;
	}
	@RequestMapping("/deal/updatePosxx")
	@ResponseBody
	public ProResultPosxx updatePosxx(ProResultPosxx proResultPosxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultPosxx = proResultPosxxService.findAllList(proResultPosxx).get(0);
		return proResultPosxx;
	}
	@RequestMapping("/deal/updateZhxx")
	@ResponseBody
	public ProResultZhxx updateZhxx(ProResultZhxx proResultZhxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultZhxx = proResultZhxxService.findAllList(proResultZhxx).get(0);
		return proResultZhxx;
	}
	/**
	 * 删除反馈信息
	 * @author Panwf
	 * @date 2016年7月18日
	 * @since:
	 */
	@RequestMapping("/deal/deleteJyxx")
	@ResponseBody
	public String deleteJyxx(ProResultJyxx proResultJyxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultJyxxService.delete(proResultJyxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/deleteBxgxx")
	@ResponseBody
	public String deleteBxgxx(ProResultBxgxx proResultBxgxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultBxgxxService.delete(proResultBxgxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/deleteDlrzxx")
	@ResponseBody
	public String deleteDlrzxx(ProResultDlrzxx proResultDlrzxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {			
		proResultDlrzxxService.delete(proResultDlrzxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/deleteJjxx")
	@ResponseBody
	public String deleteJjxx(ProResultJjxx proResultJjxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultJjxxService.delete(proResultJjxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/deletePosxx")
	@ResponseBody
	public String deletePosxx(ProResultPosxx proResultPosxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultPosxxService.delete(proResultPosxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/deleteZhxx")
	@ResponseBody
	public String  deleteZhxx(ProResultZhxx proResultZhxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		proResultZhxxService.delete(proResultZhxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/importUse")
	public String importUse(ProResultZhxx ProResultZhxx,Model model) {	
		model.addAttribute("ProResultZhxx",ProResultZhxx);
		return "/sfxc/pro/importUse";
	}
}
