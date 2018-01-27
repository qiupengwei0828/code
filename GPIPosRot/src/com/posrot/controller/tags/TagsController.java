package com.posrot.controller.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.base.controller.BaseController;
import com.base.page.Page;
import com.base.utils.security.AccountShiroUtil;
import com.posrot.entity.Rotation;
import com.sys.entity.User;
import com.sys.service.user.UserService;

/*
 * 轮岗计划制定
 */
@Controller
@RequestMapping("/posrot/tags/")
public class TagsController extends BaseController<Rotation> {

	@Autowired
	private UserService userService;

	// tag选择用户
	@RequestMapping("tagUserSelectIndex")
	public String tagUserSelectIndex() {
		return "/posrot/comm/tagUserIndex";
	}

	// tag选择用户
	@RequestMapping("tagShowUserList")
	public String tagShowUserList(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		if (user.getOrgNo() == null) {
			user.setOrgNo(AccountShiroUtil.getCurrentUser().getOrgNo());
		}
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		Page<User> page = userService.userPage(user, new Page<User>(request, response));
		model.addAttribute("page", page);
		return "/posrot/comm/tagUserList";
	}
}
