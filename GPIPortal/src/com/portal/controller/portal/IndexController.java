package com.portal.controller.portal;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.utils.security.AccountShiroUtil;
import com.sys.entity.App;
import com.sys.entity.User;
import com.sys.service.app.AppService;
import com.sys.service.user.UserService;

@Controller
@RequestMapping("/portal/")
public class IndexController extends BaseController<Object> {

	@Autowired
	public AppService appService;

	@Autowired
	public UserService userService;

	/*
	 * 访问系统首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException {
		// shiro获取用户信息
		User currentAccount = AccountShiroUtil.getCurrentUser();
		App app = new App();
		app.setUserId(currentAccount.getUserId());
		List<App> list = appService.findAppListUser(app);
		User userinfo = userService.findUserInfo(currentAccount).get(0);
		for (int i = 0; i < list.size(); i++) {
			if ("GPICert".equals(list.get(i).getAppCode())) {
				list.get(i).setHasRole("1");
			} else if ("GPIPosRot".equals(list.get(i).getAppCode())) {
				list.get(i).setHasRole("1");
			}
		}

		model.addAttribute("userinfo", userinfo);
		model.addAttribute("currentAccount", currentAccount);
		model.addAttribute("list", list);

		return "/portal/index";
	}

	@RequestMapping("adv")
	public String advUI(Model model) throws UnsupportedEncodingException {
		return "/system/adv/adv";
	}

	@RequestMapping("404")
	public String errorlistUI(Model model) throws UnsupportedEncodingException {
		return "/system/error/404";
	}

	@RequestMapping("home/list")
	public String homeList(Model model) {
		return "/sys/home/list";
	}

}
