package com.setp.controller.setpuser;

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
import com.setp.entity.SetpUser;
import com.setp.service.setpuser.SetpUserService;

@Controller
@RequestMapping("/setp/setpUser/")
public class SetpUserController extends BaseController<SetpUser> {

	@Autowired
	public SetpUserService setpUserService;

	@RequestMapping("sum_user_index")
	public String sum_user_index(SetpUser setpUser, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		model.addAttribute("orgNo", AccountShiroUtil.getCurrentUser().getOrgNo());
		return "/setp/setpuser/sum_user_index";
	}

	@RequestMapping("sum_user_list")
	public String sum_user_list(SetpUser setpUser, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if (setpUser.getOrgNo() != null) {
			model.addAttribute("orgNo", setpUser.getOrgNo());
		} else {
			model.addAttribute("orgNo", AccountShiroUtil.getCurrentUser().getOrgNo());
		}

		return "/setp/setpuser/userlist";
	}

	@ResponseBody
	@RequestMapping("get_sum_user_list")
	public List<SetpUser> get_sum_user_list(SetpUser setpUser, Model model) throws UnsupportedEncodingException, ParseException {
		if (setpUser.getBeginDate() != null && setpUser.getBeginDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String beginDateStr = setpUser.getBeginDate();
			Date beginDate = new Date();
			try {
				beginDate = sdf.parse(beginDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			model.addAttribute("beginDate", beginDate);

			String endDateStr = setpUser.getEndDate();
			Date endDate = new Date();
			try {
				endDate = sdf.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			model.addAttribute("endDate", endDate);
		}

		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<SetpUser> list = setpUserService.user_sum_setp_num(setpUser);
		model.addAttribute("setpUser", setpUser);
		return list;
	}
}
