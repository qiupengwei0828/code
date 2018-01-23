package com.sys.controller.user;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.Org;
import com.sys.entity.User;
import com.sys.service.org.OrgService;
import com.sys.service.user.UserService;

@Controller
@RequestMapping("/sys/user/")
public class UserController extends BaseController<User> {

	@Autowired
	private UserService service;

	@Autowired
	private OrgService orgService;

	/**
	 * 用户管理首页
	 */
	@RequestMapping("index")
	public String index(User user, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		return "/sys/user/index";
	}

	@RequestMapping("list")
	public String list(User user, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		if (null == user.getOrgNo()) {
			org.setOrgNo(AccountShiroUtil.getCurrentUser().getOrgNo());
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			org = orgService.get(org);
			user.setOrgNo(org.getLevelOrgNo());
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			Page<User> page = service.findByPage(user, new Page<User>(request, response));
			// 返回机构号
			model.addAttribute("page", page);
		} else {
			// 用于将机构号从树形结构中获去,然后传到list页面,然后再回传过来
			String orgNo = request.getParameter("orgNo");
			user.setOrgNo(orgNo);
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			Page<User> page = service.findByPage(user, new Page<User>(request, response));
			// 返回机构号
			model.addAttribute("page", page);
			model.addAttribute("orgNo", orgNo);
		}
		return "/sys/user/list";
	}

	/*
	 * 用户编辑首页
	 */
	@RequestMapping("form")
	public String form(User user, Org org, Model model) {
		if (null != user.getUserId()) {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			user = service.find(user).get(0);
			model.addAttribute("user", user);
		}
		return "/sys/user/form";
	}

	/*
	 * 
	 * ajax验证添加的用户userId是否存在
	 */
	@RequestMapping("existsUserId")
	@ResponseBody
	public Map<String, Object> existsUserId(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<User> userinfo = service.existsUserId(user);
		if (userinfo.size() > 0) {
			map.put("msg", "用户编码已存在，请重试！");
		} else {
			map.put("msg", "none");
		}
		return map;
	}

	/**
	 * 添加用户
	 */
	@RequestMapping("addUser")
	public String addUser(HttpServletRequest request, HttpServletResponse response, User user) {
		try {
			// 用于第一次给添加的用户设置密码：
			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
			service.insertUser(user);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setCrtTime(df.format(new Date()));
			String CertName = (String) request.getSession().getAttribute("username");
			user.setCrtUser(CertName);
			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
			service.insert(user);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/user/list";
	}

	/*
	 * 删除用户
	 */
	@RequestMapping("delete")
	public String delete(User user) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			service.delete(user);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/user/list";
	}

	/*
	 * 更新用户
	 */
	@RequestMapping("update")
	public String update(User user) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
			service.update(user);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/user/list";
	}

	/*
	 * 为前台ZTREE返回树形数据
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(User user, HttpServletResponse response) {

		List<Map<String, Object>> mapList = Lists.newArrayList();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<User> list = service.findAllList(user);
		for (int i = 0; i < list.size(); i++) {
			// User u = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			// map.put("id", user.getRoleCode());
			// map.put("pId", user.getAppCode());
			// map.put("name",user.getRoleName());
			mapList.add(map);
		}

		return mapList;
	}

	/*
	 * 选择用户标签
	 */
	@RequestMapping("tagShowUserList")
	public String tagShowUserList(User user, Model model) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<User> list = service.findAllList(user);
		model.addAttribute("list", list);
		return "/sys/comm/tagUserSelectList";
	}

	/*
	 * 重置密码
	 */
	@RequestMapping("reset")
	public String reset(User user) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
			service.resetPSD(user);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/sys/user/list";
	}

	/*
	 * 用户信息
	 */
	@RequestMapping("info")
	public String info(Model model) {
		User currentAccount = AccountShiroUtil.getCurrentUser();
		model.addAttribute("currentAccount", currentAccount);
		return "/sys/person/info";
	}

	/*
	 * 
	 */
	@RequestMapping("modifyPwd")
	public String modifyPwd(User user, Model model) {
		model.addAttribute("user", user);
		return "/sys/person/modifyPwd";
	}
}
