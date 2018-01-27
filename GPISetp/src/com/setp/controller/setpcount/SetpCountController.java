package com.setp.controller.setpcount;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.base.utils.security.AccountShiroUtil;
import com.setp.entity.SetpCount;
import com.setp.service.setpcount.SetpCountService;
import com.sys.service.org.OrgService;

@Controller
@RequestMapping("/setp/setpCount/")
public class SetpCountController extends BaseController<SetpCount> {

	@Autowired
	public SetpCountService setpCountService;

	@Autowired
	public OrgService orgService;

	// 机构平均排名
	@RequestMapping("avg_org_index")
	public String avg_org_index(SetpCount setpCount, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		model.addAttribute("orgNo", AccountShiroUtil.getCurrentUser().getOrgNo());
		return "/setp/setpcount/avg_org_index";
	}

	// 机构平均排名
	@RequestMapping("avg_org_list")
	public String avg_org_list(SetpCount setpCount, Model model) throws UnsupportedEncodingException {
		if (setpCount.getOrgNo() != null) {
			model.addAttribute("orgNo", setpCount.getOrgNo());
		} else {
			model.addAttribute("orgNo", AccountShiroUtil.getCurrentUser().getOrgNo());
		}
		return "/setp/setpcount/orglist";
	}

	// 机构平均排名
	@ResponseBody
	@RequestMapping("get_avg_org_list")
	public List<SetpCount> get_avg_org_list(SetpCount setpCount, Model model) throws UnsupportedEncodingException {

		setpCount.setDim(String.valueOf(setpCountService.getDim(setpCount).size()));

		if (setpCount.getBeginDate() != null && setpCount.getBeginDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String beginDateStr = setpCount.getBeginDate();
			Date beginDate = new Date();
			try {
				beginDate = sdf.parse(beginDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			model.addAttribute("beginDate", beginDate);

			String endDateStr = setpCount.getEndDate();
			Date endDate = new Date();
			try {
				endDate = sdf.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			model.addAttribute("endDate", endDate);
		}
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<SetpCount> list = setpCountService.org_avg_setp_num(setpCount);
		model.addAttribute("setpCount", setpCount);
		return list;
	}
}
