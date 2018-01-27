package com.sfxc.controller.court;

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
import com.sfxc.entity.CourtCheckWsinfo;
import com.sfxc.entity.CourtDealInfo;
import com.sfxc.entity.CourtQueryInfo;
import com.sfxc.entity.CourtResultDjxx;
import com.sfxc.entity.CourtResultGlzhxx;
import com.sfxc.entity.CourtResultZhxx;
import com.sfxc.entity.CourtResultZjwlxx;
import com.sfxc.service.CourtCheckWsinfoService;
import com.sfxc.service.CourtDealInfoService;
import com.sfxc.service.CourtQueryInfoService;
import com.sfxc.service.CourtResultDjxxService;
import com.sfxc.service.CourtResultGlzhxxService;
import com.sfxc.service.CourtResultZhxxService;
import com.sfxc.service.CourtResultZjwlxxService;
import com.sfxc.task.FeedBackCourtMessage;


@Controller
@RequestMapping("/court")
public class CourtController {
	@Autowired
	public CourtQueryInfoService courtQueryInfoService;
	
	@Autowired
	public CourtCheckWsinfoService courtCheckWsinfoService;
	
	@Autowired
	public CourtDealInfoService courtDealInfoService;
		
	@Autowired
	public CourtResultZjwlxxService courtResultZjwlxxService;
	
	@Autowired
	public CourtResultGlzhxxService courtResultGlzhxxService;	
	
	@Autowired
	public CourtResultZhxxService courtResultZhxxService;
	
	@Autowired
	public CourtResultDjxxService courtResultDjxxService;

	/**
	 * 待处理请求列表
	 * @param CourtQueryInfo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/deal/undealList")
	public String undoneList(CourtQueryInfo courtQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		courtQueryInfo.setStatus("1");
		Page<CourtQueryInfo> page = courtQueryInfoService.findByPage(courtQueryInfo, new Page<CourtQueryInfo>(request,response));
		model.addAttribute("page",page);
		model.addAttribute("courtQueryInfo",courtQueryInfo);
		return "/sfxc/court/undealList";
	}
	/**
	 * 已反馈的列表
	 * @author Panwf
	 * @date 2016年7月6日
	 * @since:
	 */
	@RequestMapping("/deal/dealedList")
	public String dealedList(CourtQueryInfo courtQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		courtQueryInfo.setStatus("2");
		Page<CourtQueryInfo> page = courtQueryInfoService.findByPage(courtQueryInfo, new Page<CourtQueryInfo>(request,response));
		model.addAttribute("page",page);
		model.addAttribute("courtQueryInfo",courtQueryInfo);
		return "/sfxc/court/dealedList";
	}
	
	/**
	 * 待审核列表
	 * @param CourtQueryInfo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	 
	@RequestMapping("/check/uncheckList")
	public String list(CourtQueryInfo courtQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		courtQueryInfo.setStatus("0");
		Page<CourtQueryInfo> page = courtQueryInfoService.findByPage(courtQueryInfo, new Page<CourtQueryInfo>(request,response));
		model.addAttribute("page",page);
		model.addAttribute("courtQueryInfo",courtQueryInfo);
		return "/sfxc/court/uncheckList";
	}
	/**
	 * 已审核列表
	 * @param CourtQueryInfo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/check/checkedList")
	public String checkedList(CourtQueryInfo courtQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		courtQueryInfo.setStatus("3");
		Page<CourtQueryInfo> page = courtQueryInfoService.findByPage(courtQueryInfo, new Page<CourtQueryInfo>(request,response));
		model.addAttribute("page",page);
		model.addAttribute("courtQueryInfo",courtQueryInfo);
		return "/sfxc/court/checkedList";
	}
	/**
	 * 返回到核查页面，进行核查动作
	 * @param CourtQueryInfo
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/check/check")
	public String check(CourtQueryInfo courtQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		courtQueryInfo = courtQueryInfoService.find(courtQueryInfo).get(0);
		CourtCheckWsinfo  courtCheckWsinfo = new CourtCheckWsinfo();
		courtCheckWsinfo.setQueryId(courtQueryInfo.getQueryId());
		Page<CourtCheckWsinfo> page = courtCheckWsinfoService.findByPage(courtCheckWsinfo, new Page<CourtCheckWsinfo>(request,response));
		model.addAttribute("courtQueryInfo",courtQueryInfo);
		model.addAttribute("page",page);
		return "/sfxc/court/check";
	}
	/**
	 * 查看详情
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/check/checked")
	public String checked(CourtQueryInfo courtQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		courtQueryInfo = courtQueryInfoService.find(courtQueryInfo).get(0);
		CourtCheckWsinfo  courtCheckWsinfo = new CourtCheckWsinfo();
		courtCheckWsinfo.setQueryId(courtQueryInfo.getQueryId());
		Page<CourtCheckWsinfo> page = courtCheckWsinfoService.findByPage(courtCheckWsinfo, new Page<CourtCheckWsinfo>(request,response));
		model.addAttribute("courtQueryInfo",courtQueryInfo);
		model.addAttribute("page",page);
		return "/sfxc/court/checked";
	}
	/**
	 * 审核通过更新状态
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/check/agree")
	public String agree(CourtDealInfo courtDealInfo, Model model) throws UnsupportedEncodingException {	
		courtDealInfo.setDealstaus("1");
		courtDealInfo.setExaminer(AccountShiroUtil.getCurrentUser().getUserName());
		courtDealInfoService.update(courtDealInfo);
		return "redirect:/court/check/uncheckList";
	}
	/**
	 * 审核通过更新状态
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/deal/feed")
	public String feed(CourtDealInfo courtDealInfo, Model model){	
		courtDealInfo.setDealstaus("2");
		courtDealInfo.setBacker(AccountShiroUtil.getCurrentUser().getUserName());
		courtDealInfoService.update(courtDealInfo);
		CourtResultDjxx courtResultDjxx = new CourtResultDjxx();
		courtResultDjxx.setBdhm(courtDealInfo.getBdhm());
		List<CourtResultDjxx> listDjxx = courtResultDjxxService.findAllList(courtResultDjxx);
		CourtResultGlzhxx courtResultGlzhxx = new CourtResultGlzhxx();
		courtResultGlzhxx.setBdhm(courtDealInfo.getBdhm());
		List<CourtResultGlzhxx> listGlzhxx = courtResultGlzhxxService.findAllList(courtResultGlzhxx);
		CourtResultZjwlxx courtResultZjwlxx = new CourtResultZjwlxx();
		courtResultZjwlxx.setBdhm(courtDealInfo.getBdhm());
		List<CourtResultZjwlxx> listZjwlxx = courtResultZjwlxxService.findAllList(courtResultZjwlxx);
		CourtResultZhxx courtResultZhxx = new CourtResultZhxx();
		courtResultZhxx.setBdhm(courtDealInfo.getBdhm());
		List<CourtResultZhxx> listZhxx = courtResultZhxxService.findAllList(courtResultZhxx);
        FeedBackCourtMessage feedBackMessage = new FeedBackCourtMessage();
        feedBackMessage.feedBackMessage(listDjxx,listZjwlxx,listGlzhxx,listZhxx,courtDealInfo.getBdhm());
		return "redirect:/court/deal/undealList";
	}
	/**
	 * 审核未通过，更新状态
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/check/back")
	public String back(CourtDealInfo courtDealInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		courtDealInfo.setDealstaus("-1");
		courtDealInfo.setExaminer(AccountShiroUtil.getCurrentUser().getUserName());
		courtDealInfoService.update(courtDealInfo);
		return "redirect:/court/check/uncheckList";
	}
	/**
	 * 反馈信息,账户信息
	 * @author Panwf
	 * @date 2016年7月5日
	 * @since:
	 */
	@RequestMapping("/deal/feedback")
	public String feedback(CourtQueryInfo courtQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		String flag = request.getParameter("flag");
		courtQueryInfo = courtQueryInfoService.find(courtQueryInfo).get(0);
		CourtCheckWsinfo  courtCheckWsinfo = new CourtCheckWsinfo();
		courtCheckWsinfo.setQueryId(courtQueryInfo.getQueryId());
		model.addAttribute("courtQueryInfo",courtQueryInfo);
		CourtResultZhxx courtResultZhxx = new CourtResultZhxx();
		model.addAttribute("courtResultZhxx",courtResultZhxx);
		courtResultZhxx.setBdhm(courtQueryInfo.getQueryId());
		List<CourtResultZhxx> list1 = courtResultZhxxService.findAllList(courtResultZhxx);
		model.addAttribute("list1",list1);	
		CourtResultDjxx courtResultDjxx = new CourtResultDjxx();
		model.addAttribute("courtResultDjxx", courtResultDjxx);
		courtResultDjxx.setBdhm(courtQueryInfo.getQueryId());
		List<CourtResultDjxx> list2 = courtResultDjxxService.findAllList(courtResultDjxx);
		model.addAttribute("list2",list2);	
		CourtResultGlzhxx courtResultGlzhxx = new CourtResultGlzhxx();
		model.addAttribute("courtResultGlzhxx", courtResultGlzhxx);
		courtResultGlzhxx.setBdhm(courtQueryInfo.getQueryId());
		List<CourtResultGlzhxx> list4 = courtResultGlzhxxService.findAllList(courtResultGlzhxx);
		model.addAttribute("list4",list4);	
		CourtResultZjwlxx courtResultZjwlxx = new CourtResultZjwlxx();
		model.addAttribute("courtResultZjwlxx", courtResultZjwlxx);
		courtResultZjwlxx.setBdhm(courtQueryInfo.getQueryId());
		List<CourtResultZjwlxx> list3 = courtResultZjwlxxService.findAllList(courtResultZjwlxx);
		model.addAttribute("list3",list3);
		if("".equals(flag)||flag==null){
			model.addAttribute("flag",1);
		}else{
			model.addAttribute("flag",flag);
		}
		return "/sfxc/court/feedback";
	}
	/**
	 * 查看反馈详情信息
	 * @author Panwf
	 * @date 2016年7月6日
	 * @since:
	 */
	@RequestMapping("/deal/queryDetail")
	public String queryDetail(CourtQueryInfo courtQueryInfo, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		courtQueryInfo = courtQueryInfoService.find(courtQueryInfo).get(0);
		CourtCheckWsinfo  courtCheckWsinfo = new CourtCheckWsinfo();
		courtCheckWsinfo.setQueryId(courtQueryInfo.getQueryId());
		model.addAttribute("courtQueryInfo",courtQueryInfo);
		CourtResultZhxx courtResultZhxx = new CourtResultZhxx();
		model.addAttribute("courtResultZhxx",courtResultZhxx);
		courtResultZhxx.setBdhm(courtQueryInfo.getQueryId());
		List<CourtResultZhxx> list1 = courtResultZhxxService.findAllList(courtResultZhxx);
		model.addAttribute("list1",list1);	
		CourtResultDjxx courtResultDjxx = new CourtResultDjxx();
		model.addAttribute("courtResultDjxx", courtResultDjxx);
		courtResultDjxx.setBdhm(courtQueryInfo.getQueryId());
		List<CourtResultDjxx> list2 = courtResultDjxxService.findAllList(courtResultDjxx);
		model.addAttribute("list2",list2);	
		CourtResultGlzhxx courtResultGlzhxx = new CourtResultGlzhxx();
		model.addAttribute("courtResultGlzhxx", courtResultGlzhxx);
		courtResultGlzhxx.setBdhm(courtQueryInfo.getQueryId());
		List<CourtResultGlzhxx> list4 = courtResultGlzhxxService.findAllList(courtResultGlzhxx);
		model.addAttribute("list4",list4);	
		CourtResultZjwlxx courtResultZjwlxx = new CourtResultZjwlxx();
		model.addAttribute("courtResultZjwlxx", courtResultZjwlxx);
		courtResultZjwlxx.setBdhm(courtQueryInfo.getQueryId());
		List<CourtResultZjwlxx> list3 = courtResultZjwlxxService.findAllList(courtResultZjwlxx);
		model.addAttribute("list3",list3);
		return "/sfxc/court/queryDetail";
	}
	/**
	 * 保存反馈信息
	 * @author Panwf
	 * @date 2016年7月6日
	 * @since:
	 */
	@RequestMapping("/deal/addDjxx")
	@ResponseBody
	public List<CourtResultDjxx> addJyxx(CourtResultDjxx courtResultDjxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(courtResultDjxx.getId())||courtResultDjxx.getId()==null){
			courtResultDjxxService.insert(courtResultDjxx);
		}else{
			courtResultDjxxService.update(courtResultDjxx);
		}
		List<CourtResultDjxx> list2 = courtResultDjxxService.findAllList(courtResultDjxx);
		return list2;
	}
	@RequestMapping("/deal/addGlzhxx")
	@ResponseBody
	public List<CourtResultGlzhxx> addDlrzxx(CourtResultGlzhxx courtResultGlzhxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(courtResultGlzhxx.getId())||courtResultGlzhxx.getId()==null){
			courtResultGlzhxxService.insert(courtResultGlzhxx);
		}else{
			courtResultGlzhxxService.update(courtResultGlzhxx);
		}
		List<CourtResultGlzhxx> list4 = courtResultGlzhxxService.findAllList(courtResultGlzhxx);
		return list4;
	}
	@RequestMapping("/deal/addZjwlxx")
	@ResponseBody
	public List<CourtResultZjwlxx> addJjxx(CourtResultZjwlxx courtResultZjwlxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(courtResultZjwlxx.getId())||courtResultZjwlxx.getId()==null){
			courtResultZjwlxxService.insert(courtResultZjwlxx);
		}else{
			courtResultZjwlxxService.update(courtResultZjwlxx);
		}		
		List<CourtResultZjwlxx> list3 = courtResultZjwlxxService.findAllList(courtResultZjwlxx);
		return list3;
	}
	@RequestMapping("/deal/addZhxx")
	@ResponseBody
	public List<CourtResultZhxx> addZhxx(CourtResultZhxx courtResultZhxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		if("".equals(courtResultZhxx.getId())||courtResultZhxx.getId()==null){
			courtResultZhxxService.insert(courtResultZhxx);
		}else{
			courtResultZhxxService.update(courtResultZhxx);
		}		
		List<CourtResultZhxx> list1 = courtResultZhxxService.findAllList(courtResultZhxx);
		return list1;
	}	
	/**
	 * 修改反馈信息
	 * @author Panwf
	 * @date 2016年7月6日
	 * @since:
	 */
	@RequestMapping("/deal/updateDjxx")
	@ResponseBody
	public CourtResultDjxx updateJyxx(CourtResultDjxx courtResultDjxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		courtResultDjxx = courtResultDjxxService.findAllList(courtResultDjxx).get(0);
		return courtResultDjxx;
	}
	@RequestMapping("/deal/updateGlzhxx")
	@ResponseBody
	public CourtResultGlzhxx updateBxgxx(CourtResultGlzhxx courtResultGlzhxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		courtResultGlzhxx = courtResultGlzhxxService.findAllList(courtResultGlzhxx).get(0);
		return courtResultGlzhxx;
	}
	@RequestMapping("/deal/updateZjwlxx")
	@ResponseBody
	public CourtResultZjwlxx updateDlrzxx(CourtResultZjwlxx courtResultZjwlxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {			
		courtResultZjwlxx = courtResultZjwlxxService.findAllList(courtResultZjwlxx).get(0);
		return courtResultZjwlxx;
	}
	@RequestMapping("/deal/updateZhxx")
	@ResponseBody
	public CourtResultZhxx updateZhxx(CourtResultZhxx courtResultZhxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		courtResultZhxx = courtResultZhxxService.findAllList(courtResultZhxx).get(0);
		return courtResultZhxx;
	}
	/**
	 * 删除反馈信息
	 * @author Panwf
	 * @date 2016年7月18日
	 * @since:
	 */
	@RequestMapping("/deal/deleteDjxx")
	@ResponseBody
	public String deleteJyxx(CourtResultDjxx courtResultDjxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		courtResultDjxxService.delete(courtResultDjxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/deleteGlzhxx")
	@ResponseBody
	public String deleteBxgxx(CourtResultGlzhxx courtResultGlzhxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		courtResultGlzhxxService.delete(courtResultGlzhxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/deleteZjwlxx")
	@ResponseBody
	public String deleteDlrzxx(CourtResultZjwlxx courtResultZjwlxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {			
		courtResultZjwlxxService.delete(courtResultZjwlxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/deleteZhxx")
	@ResponseBody
	public String deleteZhxx(CourtResultZhxx courtResultZhxx, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {	
		courtResultZhxxService.delete(courtResultZhxx);
		return "SUCCESS";
	}
	@RequestMapping("/deal/importUse")
	@ResponseBody
	public String importUse(CourtResultZhxx courtResultZhxx,Model model) {	
		model.addAttribute("courtResultZhxx",courtResultZhxx);
		return "/sfxc/court/importUse";
	}
}
