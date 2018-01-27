package com.posrot.controller.res;

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
import com.base.controller.BaseController;
import com.base.page.Page;
import com.base.utils.DateUtils;
import com.base.utils.security.AccountShiroUtil;
import com.posrot.entity.ResInfo;
import com.posrot.service.res.ResService;
import com.sys.entity.User;
import com.sys.service.user.UserService;

/*
 * 履历管理
 */
@Controller
@RequestMapping("/posrot/res/")
public class ResController extends BaseController<ResInfo> {

	@Autowired
	public ResService resService;

	@Autowired
	public UserService userService;

	@RequestMapping("index")
	public String index() {
		return "/posrot/res/index";
	}

	@RequestMapping("list")
	public String list(User user, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String orgNo = AccountShiroUtil.getCurrentUser().getOrgNo();
		if (user.getOrgNo() != null) {
			Page<User> page = userService.userPage(user, new Page<User>(request, response));
			model.addAttribute("page", page);
		} else {
			user.setOrgNo(orgNo);
			Page<User> page = userService.userPage(user, new Page<User>(request, response));
			model.addAttribute("page", page);
		}
		return "/posrot/res/list";
	}

	/*
	 * 履历列表
	 */
	@RequestMapping("reslist")
	public String reslist(ResInfo resInfo, Model model) {
		List<ResInfo> reslist = resService.find(resInfo);
		model.addAttribute("reslist", reslist);

		User userinfo = new User();
		userinfo.setUserId(resInfo.getUserId());
		userinfo = userService.find(userinfo).get(0);
		model.addAttribute("userinfo", userinfo);

		return "/posrot/res/reslist";
	}

	// form
	@RequestMapping("form")
	public String form(ResInfo resInfo, Model model) {
		if (resInfo.getId() != null) {

			resInfo = resService.find(resInfo).get(0);
			model.addAttribute("resInfo", resInfo);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String end_date = resInfo.getBeginDate();
			Date beginDate = new Date();
			try {
				beginDate = sdf.parse(end_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String out_date = resInfo.getEndDate();
			Date endDate = new Date();
			try {
				endDate = sdf.parse(out_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			model.addAttribute("beginDate", beginDate);
			model.addAttribute("endDate", endDate);

		} else {
			User userinfo = new User();
			userinfo.setUserId(resInfo.getUserId());
			userinfo = userService.find(userinfo).get(0);
			resInfo.setUserName(userinfo.getUserName());
			model.addAttribute("resInfo", resInfo);
		}
		return "/posrot/res/form";
	}

	/*
	 * 添加履历
	 */
	@RequestMapping("insert")
	public String insert(ResInfo resInfo, Model model) {
		try {
			resInfo.setCreUserId(AccountShiroUtil.getCurrentUser().getUserId());
			resInfo.setCreDate(DateUtils.getDate().toString());
			resService.insert(resInfo);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/posrot/res/reslist?userId=" + resInfo.getUserId();
	}

	/*
	 * 更新履历
	 */
	@RequestMapping("update")
	public String update(ResInfo resInfo, Model model) {
		try {
			resInfo.setCreUserId(AccountShiroUtil.getCurrentUser().getUserId());
			resInfo.setCreDate(DateUtils.getDate().toString());
			resService.update(resInfo);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/posrot/res/reslist?userId=" + resInfo.getUserId();
	}

	/*
	 * 删除履历
	 */
	@RequestMapping("delete")
	public String delete(ResInfo resInfo, Model model) {
		try {
			resService.delete(resInfo);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/posrot/res/reslist?userId=" + resInfo.getUserId();
	}
}
