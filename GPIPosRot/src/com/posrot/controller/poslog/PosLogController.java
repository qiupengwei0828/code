package com.posrot.controller.poslog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.utils.DateUtils;
import com.base.utils.security.AccountShiroUtil;
import com.posrot.entity.PosLog;
import com.posrot.entity.Rotation;
import com.posrot.service.poslog.PosLogService;
import com.posrot.service.rotation.RotationService;

@Controller
@RequestMapping("/posrot/poslog/")
public class PosLogController extends BaseController<PosLog> {

	@Autowired
	private PosLogService posLogService;

	@Autowired
	private RotationService rotationService;

	// 添加轮岗计划log
	@RequestMapping("insertRotation")
	public String insertRotation(PosLog posLog, Model model) {
		PosLog log = new PosLog();
		log.setProCode("1");// 轮岗计划代码：1
		log.setProName("轮岗计划");
		log.setPlanId(posLog.getPlanId());
		log.setActionCode("3");// 1，2，3，4，5，6，7
		log.setActionName("通知");// 1.制定，2.审批，3.通知，4.接收，5.交接，6.执行，7.归档
		log.setOpeOpinion(posLog.getOpeOpinion());// 操作意见
		log.setOpeContent(posLog.getOpeContent());// 操作内容
		log.setOpeResult("已审批");// 操作结果
		log.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());// 操作人ID
		log.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());// 操作人姓名
		log.setOpeDate(DateUtils.getDate().toString());// 操作时间
		posLogService.insert(log);
		// 修改计划状态
		Rotation rot = new Rotation();
		rot.setId(posLog.getPlanId());
		rot.setStatus("3");
		rotationService.updateStatus(rot);
		return "redirect:/posrot/rotation/list";
	}
}
