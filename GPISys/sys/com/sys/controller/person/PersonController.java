package com.sys.controller.person;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.utils.comm.Const;
import com.sys.entity.User;
import com.sys.service.person.PersonService;

@Controller
@RequestMapping("/sys/person/")
public class PersonController extends BaseController<User> {

	@Autowired
	private PersonService service;

	/**
	 * 修改密码
	 */
	@RequestMapping("modifyPwd")
	@ResponseBody
	public String modifyPwd(User user, Model model) {
		String password = this.getRequest().getParameter("pwd");
		// shiro获取用户信息
		Subject currentUser = SecurityUtils.getSubject();
		// shiro管理的session
		Session session = currentUser.getSession();
		// 获得用户
		User acount = (User) session.getAttribute(Const.SESSION_USER);
		// 获得用户Id
		String userId = acount.getUserId();
		addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
		String flag = service.modifyPwd(password, userId);
		return flag;
	}

}
