package com.posrot.controller.furlough;

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
import com.posrot.entity.Staff;
import com.posrot.entity.StaffPos;
import com.posrot.service.staff.StaffService;
import com.posrot.service.staffpos.StaffPosService;
import com.sys.entity.Org;
import com.sys.entity.User;
import com.sys.service.org.OrgService;

/*
 * 强休员工列表
 */
@Controller
@RequestMapping("/posrot/furlough/")
public class FlurloughController extends BaseController<User> {

	@Autowired
	private StaffService staffservice;

	@Autowired
	public StaffPosService staffposervice;

	@Autowired
	private OrgService orgService;

	@RequestMapping("index")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		return "/posrot/jobrotation/index";
	}

	@RequestMapping("list")
	public String list(Staff staff, StaffPos staffpos, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 判断用户是否应该轮岗
		Org org = new Org();
		if (null == staff.getOrgNo()) {
			org.setOrgNo(AccountShiroUtil.getCurrentUser().getOrgNo());
			// addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			org = orgService.get(org);
			staff.setOrgNo(org.getLevelOrgNo());
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			Page<Staff> page = staffservice.findExchangePos(staff, new Page<Staff>(request, response));

			model.addAttribute("page", page);
		} else {
			// 用于将机构号从树形结构中获去,然后传到list页面,然后再回传过来
			String orgNo = request.getParameter("orgNo");
			staff.setOrgNo(orgNo);
			Page<Staff> page = staffservice.findExchangePos(staff, new Page<Staff>(request, response));
			model.addAttribute("page", page);
			model.addAttribute("orgNo", orgNo);
		}
		// Page<User> page =service.findByPage(user,new Page<User>(request,
		// response));
		/*
		 * List<User> list=page.getList(); for(int i=0;i<list.size();i++){
		 * user=list.get(i); //到岗时间转化成"yyyy-MM-dd" //select * from gx where
		 * ROUND(TO_NUMBER(sysdate - time) * 24)<2 try{ SimpleDateFormat sdf =
		 * new SimpleDateFormat("yyyy-MM-dd"); Date posDate
		 * =sdf.parse(user.getPosDate()); Date nowDate=new Date();
		 * if((nowDate.getTime()-posDate.getTime())/(24*60*60*1000)<=15){
		 * user.setExchangePosSatus("该员工应该轮岗"); } else{
		 * user.setExchangePosSatus(""); } }catch(Exception e){
		 * logger.error(e.toString(),e) ; } }
		 */
		// model.addAttribute("page", page);
		// 获取关键岗位信息菜单
		List<StaffPos> posList = staffposervice.findAllList(null);
		model.addAttribute("posList", posList);
		return "/posrot/jobrotation/list";
	}

}
