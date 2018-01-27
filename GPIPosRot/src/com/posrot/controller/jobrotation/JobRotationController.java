package com.posrot.controller.jobrotation;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.posrot.entity.Staff;
import com.posrot.entity.StaffPos;
import com.posrot.service.staff.StaffService;
import com.posrot.service.staffpos.StaffPosService;
import com.sys.entity.Pos;
import com.sys.entity.User;
import com.sys.service.org.OrgService;
import com.sys.service.pos.PosService;
import com.sys.service.user.UserService;

/*
 * 员工轮岗信息列表
 */
@Controller
@RequestMapping("/posrot/jobrotation/")
public class JobRotationController extends BaseController<Staff> {

	@Autowired
	private StaffService staffservice;

	@Autowired
	public StaffPosService staffposervice;

	@Autowired
	private OrgService orgService;

	@Autowired
	private UserService userService;

	@Autowired
	private PosService posService;

	@RequestMapping("index")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		return "/posrot/jobrotation/index";
	}

	@RequestMapping("list")
	public String list(Staff staff, StaffPos staffpos, Model model, HttpServletRequest request, HttpServletResponse response) {
		if (staff.getOrgNo() == null) {
			// 默认查询当前用户所在机构的用户列表
			staff.setOrgNo(AccountShiroUtil.getCurrentUser().getOrgNo());
		}
		// 查询条件,岗位
		List<Pos> posList = posService.findAllList(null);
		model.addAttribute("posList", posList);
		Page<Staff> page = staffservice.findExchangePos(staff, new Page<Staff>(request, response));
		model.addAttribute("orgNo", staff.getOrgNo());
		model.addAttribute("page", page);
		model.addAttribute("staff", staff);
		return "/posrot/jobrotation/list";
	}

	// 查询轮岗员工信息
	@RequestMapping("queryUserInfo")
	public String queryUserInfo(Staff staff, Model model) {
		User user = new User();
		user.setPos(staff.getPos());
		user.setUserId(staff.getUserId());
		User info = userService.queryUserInfo(user).get(0);
		Rotation rotation = new Rotation();
		rotation.setRotUserId(info.getUserId());
		rotation.setRotUserName(info.getUserName());
		rotation.setRotOrgNo(info.getOrgNo());
		rotation.setRotOrgName(info.getOrgName());
		rotation.setRotPos(info.getPos());
		rotation.setRotPosName(info.getPosName());
		List<Pos> posList = posService.findAllList(null);
		model.addAttribute("poslist", posList);
		model.addAttribute("rotation", rotation);
		return "/posrot/rotmanage/form";
	}
}
