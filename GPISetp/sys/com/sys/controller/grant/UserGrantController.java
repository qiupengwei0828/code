package com.sys.controller.grant;

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
import com.sys.entity.Role;
import com.sys.entity.User;
import com.sys.entity.UserRole;
import com.sys.service.user.UserService;
import com.sys.service.userRole.UserRoleService;

@Controller
@RequestMapping("/sys/usergrant/")
public class UserGrantController extends BaseController<Role> {

	@Autowired
	public UserService service;

	@Autowired
	public UserRoleService userRoleService;

	/**
	 * 用户管理首页
	 */
	@RequestMapping("index")
	public String index(Model model) {
		return "/sys/grant/grantuser";
	}

	/*
	 * 分页
	 */
	@RequestMapping("userlist")
	public String userlist(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			Page<User> page = service.findUserRolePage(user, new Page<User>(request, response));
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "/sys/grant/grantuserlist";
	}

	/**
	 * 删除角色里的用户
	 */
	@RequestMapping("delete")
	public String delete(UserRole userRole, Model model) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			userRoleService.delete(userRole);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/sys/usergrant/userlist?roleCode=" + userRole.getRoleCode();
	}

	/*
	 * 添加用户到角色
	 */
	@RequestMapping("saveuserrole")
	@ResponseBody
	public Map<String, Object> saveuserrole(UserRole userRole, Model model) {
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			String[] userIdStrings = userRole.getUserId().split(",");
			for (int i = 0; i < userIdStrings.length; i++) {
				UserRole urole = new UserRole();
				urole.setUserId(userIdStrings[i]);
				urole.setRoleCode(userRole.getRoleCode());
				addLog(OP_CLASS_OPER, OP_TYPE_ADD);
				List<UserRole> list = userRoleService.find(urole);
				if (list.size() != 0) {
					continue;
				}
				userRoleService.saveUserRole(urole);
				map.put("msg", "success");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return map;
	}
}
