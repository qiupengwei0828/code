package com.posrot.controller.staff;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.base.controller.BaseController;
import com.base.page.Page;
import com.base.utils.security.AccountShiroUtil;
import com.posrot.entity.Staff;
import com.posrot.service.staff.StaffService;
import com.sys.entity.Org;
import com.sys.service.org.OrgService;

@Controller
@RequestMapping("/posrot/staff/")
public class StaffController extends BaseController<Staff> {

	@Autowired
	private OrgService orgService;

	@Autowired
	private StaffService staffService;

	@RequestMapping("index")
	public String index() {
		return "/posrot/staff/index";
	}

	@RequestMapping("list")
	public String list(Staff staff, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		if (null == staff.getOrgNo()) {
			org.setOrgNo(AccountShiroUtil.getCurrentUser().getOrgNo());
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			org = orgService.get(org);
			staff.setOrgNo(org.getLevelOrgNo());
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			Page<Staff> page = staffService.findByPage(staff, new Page<Staff>(request, response));
			// 返回机构号
			model.addAttribute("page", page);
		} else {
			// 用于将机构号从树形结构中获去,然后传到list页面,然后再回传过来
			String orgNo = request.getParameter("orgNo");
			staff.setOrgNo(orgNo);
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			Page<Staff> page = staffService.findByPage(staff, new Page<Staff>(request, response));
			// 返回机构号
			model.addAttribute("page", page);
			model.addAttribute("orgNo", orgNo);
		}
		return "/posrot/staff/list";
	}

}
