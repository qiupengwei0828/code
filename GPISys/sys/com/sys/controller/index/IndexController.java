package com.sys.controller.index;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.utils.comm.Const;
import com.base.utils.security.AccountShiroUtil;
import com.sys.entity.Menu;
import com.sys.entity.User;
import com.sys.service.menu.MenuService;

@Controller
@RequestMapping("/sys/")
public class IndexController extends BaseController<Object> {

	@Autowired
	public MenuService menuService;

	/**
	 * 访问系统首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException {
		// shiro获取用户信息
		User currentAccount = AccountShiroUtil.getCurrentUser();
		model.addAttribute("currentAccount", currentAccount);
		return "/sys/index";
	}

	@RequestMapping(value = "menu/getLeftMenu")
	public String getLeftMenu(Model model) {
		  //addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		// common = common.substring(common.indexOf("(")+1,
		// common.indexOf(")"));
		// 取得菜单列表
		List<Menu> menuList = new ArrayList<Menu>();
		try {
			// shiro获取用户信息
			Subject currentUser = SecurityUtils.getSubject();
			// shiro管理的session
			Session session = currentUser.getSession();
			// 获得用户
			User acount = (User) session.getAttribute(Const.SESSION_USER);
			// 获得用户Id
			String userId = acount.getUserId();
			menuList = menuService.findMenuTree(userId);
			session.setAttribute(Const.SESSION_MENULIST, menuList);
		} catch (InvalidSessionException e) {
			logger.error(e.toString(), e);
		}
		return "sys/index/leftmenu";
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
