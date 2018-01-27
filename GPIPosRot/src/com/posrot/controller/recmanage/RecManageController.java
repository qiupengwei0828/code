package com.posrot.controller.recmanage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.base.utils.DateUtils;
import com.base.utils.comm.Global;
import com.base.utils.security.AccountShiroUtil;
import com.posrot.entity.PosLog;
import com.posrot.entity.Recess;
import com.posrot.entity.ResInfo;
import com.posrot.service.recess.RecessService;
import com.posrot.service.reclog.RecLogService;
import com.posrot.service.res.ResService;
import com.posrot.service.templet.RecCreateWord;
import com.sys.entity.AttachInfo;
import com.sys.entity.User;
import com.sys.service.attachinfo.AttachInfoService;
import com.sys.service.user.UserService;

/*
 * 强修计划制定
 */
@Controller
@RequestMapping("/posrot/recManage/")
public class RecManageController extends BaseController<Recess> {

	@Autowired
	public RecessService recessService;

	@Autowired
	public UserService userService;

	@Autowired
	public RecLogService recLogService;

	@Autowired
	public ResService resService;

	@Autowired
	public AttachInfoService attachInfoService;

	@RequestMapping("index")
	public String index(Recess recess, Model model) {
		return "/posrot/recmanage/form";
	}

	// form
	@RequestMapping("form")
	public String form(Recess recess, User user, Model model) {
		recess = recessService.find(recess).get(0);
		model.addAttribute("recess", recess);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strRecBeginDate = recess.getRecBeginDate();
		Date recBeginDate = new Date();
		try {
			recBeginDate = sdf.parse(strRecBeginDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("recBeginDate", recBeginDate);

		String strRecEndDate = recess.getRecEndDate();
		Date recEndDate = new Date();
		try {
			recEndDate = sdf.parse(strRecEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("recEndDate", recEndDate);

		String strHanDate = recess.getRecEndDate();
		Date hanDate = new Date();
		try {
			hanDate = sdf.parse(strHanDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("hanDate", hanDate);

		return "/posrot/recmanage/form";
	}

	// 添加强修计划
	@RequestMapping("insert")
	public String insert(Recess recess, Model model) {
		Long id = recessService.getKey();
		recess.setId(id);
		recess.setRecessNo(DateUtils.getYear().toString() + id);
		recess.setCreUserId(AccountShiroUtil.getCurrentUser().getUserId());
		recess.setCreDate(DateUtils.getDate().toString());
		recessService.insert(recess);

		PosLog posLog = new PosLog();
		posLog.setPlanId(id);
		posLog.setOpeOpinion(recess.getOpeOpinion());
		if (recess.getStatus().equals("1")) {// 待提交
			posLog.setActionCode("1");
		} else if (recess.getStatus().equals("2")) {// 待审批
			posLog.setActionCode("2");

		}
		recLogService.insert1(posLog);
		return "redirect:" + "/posrot/recManage/index";
	}

	// 修改强修计划
	@RequestMapping("update")
	public String update(Recess recess, Model model) {
		recess.setCreUserId(AccountShiroUtil.getCurrentUser().getUserId());
		recess.setCreDate(DateUtils.getDate().toString());
		recessService.update(recess);

		PosLog posLog = new PosLog();
		posLog.setPlanId(recess.getId());
		recLogService.delete(posLog);

		posLog.setPlanId(recess.getId());
		posLog.setOpeOpinion(recess.getOpeOpinion());
		posLog.setActionCode("1");
		recLogService.insert1(posLog);

		return "redirect:" + "/posrot/recManage/list_items?status=1";
	}

	// 删除强修计划
	@RequestMapping("delete")
	public String delete(Recess recess, Model model) {
		recessService.delete(recess);

		PosLog posLog = new PosLog();
		posLog.setPlanId(recess.getId());
		recLogService.delete(posLog);

		return "redirect:" + "/posrot/recManage/list_items?status=1";
	}

	// 强修计划详情（展示）
	@RequestMapping("all_page_info")
	public String all_page_info(Recess recess, Model model) {

		recess = recessService.find(recess).get(0);
		model.addAttribute("recess", recess);

		if ("8".equals(recess.getStatus())) {
			AttachInfo notify_atta = new AttachInfo();
			notify_atta.setTab("T_POSROT_ROTATE");
			notify_atta.setTabKey(String.valueOf(recess.getId()));
			notify_atta.setCfgCode(Long.valueOf(4));

			if (attachInfoService.find(notify_atta).size() > 0) {
				notify_atta = attachInfoService.find(notify_atta).get(0);
				model.addAttribute("notify", notify_atta.getFileName());
			}

			AttachInfo done_atta = new AttachInfo();
			done_atta.setTab("T_POSROT_ROTATE");
			done_atta.setTabKey(String.valueOf(recess.getId()));
			done_atta.setCfgCode(Long.valueOf(6));
			done_atta.setAppCode("GPIPosRot");
			if (attachInfoService.find(done_atta).size() > 1) {
				AttachInfo temp_atta = new AttachInfo();
				temp_atta = attachInfoService.find(done_atta).get(0);
				model.addAttribute("done_atta1", temp_atta.getFileSaveName());

				temp_atta = attachInfoService.find(done_atta).get(1);
				model.addAttribute("done_atta2", temp_atta.getFileSaveName());
			}
		}

		// 流程日志
		PosLog plog = new PosLog();
		plog.setPlanId(recess.getId());
		List<PosLog> list = recLogService.findAllList(plog);
		model.addAttribute("list", list);
		return "/posrot/recmanage/info_all";
	}

	// 强修计划详情（操作）
	@RequestMapping("info")
	public String info(Recess recess, Model model) {

		// 流程日志
		PosLog plog = new PosLog();
		plog.setPlanId(recess.getId());
		List<PosLog> list = recLogService.findAllList(plog);
		model.addAttribute("list", list);

		if (recess.getStatus() != null) {
			Recess rec = new Recess();
			rec.setId(recess.getId());
			rec = recessService.find(rec).get(0);
			model.addAttribute("recess", rec);
			model.addAttribute("status", recess.getStatus());
		} else {
			recess = recessService.find(recess).get(0);
			model.addAttribute("recess", recess);
			model.addAttribute("status", recess.getStatus());
		}

		if (recess.getStatus().equals("6") || recess.getStatus().equals("7") || recess.getStatus().equals("8")) {
			AttachInfo done_atta = new AttachInfo();
			done_atta.setTab("T_POSROT_ROTATE");
			done_atta.setTabKey(String.valueOf(recess.getId()));
			done_atta.setCfgCode(Long.valueOf(6));
			done_atta.setAppCode("GPIPosRot");
			if (attachInfoService.find(done_atta).size() > 1) {
				AttachInfo temp_atta = new AttachInfo();
				temp_atta = attachInfoService.find(done_atta).get(0);
				model.addAttribute("done_atta1", temp_atta.getFileSaveName());

				temp_atta = attachInfoService.find(done_atta).get(1);
				model.addAttribute("done_atta2", temp_atta.getFileSaveName());
			}
		}
		return "/posrot/recmanage/info";
	}

	// 强修计划审批
	@RequestMapping("check")
	public String check(Recess recess, Model model, HttpServletRequest request, HttpServletResponse response) {
		recess.setStatus("2");// 待审批
		Page<Recess> page = recessService.findByPage(recess, new Page<Recess>(request, response));
		model.addAttribute("page", page);
		model.addAttribute("status", recess.getStatus());
		return "/posrot/recmanage/check";
	}

	// 计划管理
	@RequestMapping("list_items")
	public String list_items(Recess recess, Model model, HttpServletRequest request, HttpServletResponse response) {

		// 全部指定轮岗计划数量
		model.addAttribute("statusAll", recessService.countStatusNum(null));
		Recess rec = new Recess();
		rec.setStatus("1");// 待提交
		model.addAttribute("status1", recessService.countStatusNum(rec));
		rec.setStatus("2");// 待审批
		model.addAttribute("status2", recessService.countStatusNum(rec));
		rec.setStatus("3");// 待通知
		model.addAttribute("status3", recessService.countStatusNum(rec));
		rec.setStatus("4");// 待接收
		model.addAttribute("status4", recessService.countStatusNum(rec));
		rec.setStatus("5");// 待交接
		model.addAttribute("status5", recessService.countStatusNum(rec));
		rec.setStatus("6");// 待执行
		model.addAttribute("status6", recessService.countStatusNum(rec));
		rec.setStatus("7");// 待归档
		model.addAttribute("status7", recessService.countStatusNum(rec));
		// 查询当前登录用户所在的机构的轮岗计划列表
		recess.setRecOrgNo(AccountShiroUtil.getCurrentUser().getOrgNo());
		if (recess.getStatus() == null) {
			recess.setStatus("");// 全部未完成计划
			Page<Recess> page = recessService.findNotPass(recess, new Page<Recess>(request, response));
			model.addAttribute("page", page);
			recess.setStatus("all");
		} else if (recess.getStatus().equals("1")) {
			recess.setStatus("1");// 待提交
			Page<Recess> page = recessService.findByPage(recess, new Page<Recess>(request, response));

			// 判断是否有审批不通过的计划
			PosLog poslog = new PosLog();
			for (int i = 0; i < page.getList().size(); i++) {
				poslog.setActionCode("3");
				poslog.setPlanId(page.getList().get(i).getId());
				if (!recLogService.find(poslog).isEmpty()) {
					page.getList().get(i).setStatus("9");
				}
			}

			model.addAttribute("page", page);
			model.addAttribute("status", recess.getStatus());
		} else if (recess.getStatus().equals("2")) {
			recess.setStatus("2");// 待审核
			Page<Recess> page = recessService.findByPage(recess, new Page<Recess>(request, response));
			model.addAttribute("page", page);
			model.addAttribute("status", recess.getStatus());
		} else if (recess.getStatus().equals("3")) {
			recess.setStatus("3");// 待通知
			Page<Recess> page = recessService.findByPage(recess, new Page<Recess>(request, response));
			model.addAttribute("page", page);
			model.addAttribute("status", recess.getStatus());
		} else if (recess.getStatus().equals("4")) {
			recess.setStatus("4");// 待接收
			Page<Recess> page = recessService.findByPage(recess, new Page<Recess>(request, response));
			model.addAttribute("page", page);
			model.addAttribute("status", recess.getStatus());
		} else if (recess.getStatus().equals("5")) {
			recess.setStatus("5");// 待交接
			Page<Recess> page = recessService.findByPage(recess, new Page<Recess>(request, response));
			model.addAttribute("page", page);
			model.addAttribute("status", recess.getStatus());
		} else if (recess.getStatus().equals("6")) {
			recess.setStatus("6");// 待执行
			Page<Recess> page = recessService.findByPage(recess, new Page<Recess>(request, response));
			model.addAttribute("page", page);
			model.addAttribute("status", recess.getStatus());
		} else if (recess.getStatus().equals("7")) {
			recess.setStatus("7");// 待归档
			Page<Recess> page = recessService.findByPage(recess, new Page<Recess>(request, response));
			model.addAttribute("page", page);
			model.addAttribute("status", recess.getStatus());
		}
		return "/posrot/recmanage/list_" + recess.getStatus();
	}

	// 审批轮岗计划（通过）
	@ResponseBody
	@RequestMapping("pass")
	public Map<String, Object> pass(Recess recess, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println(recess.getStatus() + "==============================");

		if (recess.getStatus().equals("1")) {
			// 修改计划状态为待审批
			recess.setStatus("2");
			recessService.updateStatus(recess);

			PosLog posLog = new PosLog();
			posLog.setPlanId(recess.getId());
			posLog.setActionCode(recess.getActionCode());
			posLog.setOpeOpinion(recess.getOpeOpinion());
			recLogService.insert2(posLog);// 提交轮岗计划
		} else if (recess.getStatus().equals("2")) {
			// 修改计划状态为待通知
			if (recess.getActionCode().equals("1")) {
				recess.setStatus("3");
			} else if (recess.getActionCode().equals("0")) {
				recess.setStatus("1");
			}
			recessService.updateStatus(recess);

			PosLog posLog = new PosLog();
			posLog.setPlanId(recess.getId());
			posLog.setActionCode(recess.getActionCode());
			posLog.setOpeOpinion(recess.getOpeOpinion());
			recLogService.insert3(posLog);// 提交轮岗计划
		} else if (recess.getStatus().equals("3")) {
			// 修改计划状态为待接收
			if (recess.getActionCode().equals("1")) {
				recess.setStatus("4");
			} else if (recess.getActionCode().equals("0")) {
				// recess.setStatus("2");
			}
			recessService.updateStatus(recess);

			PosLog posLog = new PosLog();
			posLog.setPlanId(recess.getId());
			posLog.setActionCode(recess.getActionCode());
			posLog.setOpeOpinion(recess.getOpeOpinion());
			recLogService.insert4(posLog);// 通知轮岗计划
		} else if (recess.getStatus().equals("4")) {
			// 计划接收
			PosLog pLog_temp = new PosLog();
			pLog_temp.setPlanId(recess.getId());
			pLog_temp.setActionCode("5");
			pLog_temp.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
			List<PosLog> list = recLogService.find(pLog_temp);

			if (list.size() > 0) {
				String title = "您已经接收计划，请等待顶岗人/轮岗人接收！";
				map.put("title", title);
			} else {
				PosLog posLog = new PosLog();
				posLog.setPlanId(recess.getId());
				posLog.setActionCode(recess.getActionCode());
				posLog.setOpeOpinion(recess.getOpeOpinion());
				recLogService.insert5(posLog);// 接收轮岗计划
			}
			list = null;
			pLog_temp = new PosLog();
			pLog_temp.setPlanId(recess.getId());
			pLog_temp.setActionCode("5");
			list = recLogService.find(pLog_temp);
			if (list.size() > 1) {
				recess.setStatus("5");
				recessService.updateStatus(recess);
			}

		} else if (recess.getStatus().equals("5")) {
			// 交接计划
			PosLog pLog_temp = new PosLog();
			pLog_temp.setPlanId(recess.getId());
			pLog_temp.setActionCode("6");
			pLog_temp.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
			List<PosLog> list = recLogService.find(pLog_temp);
			if (list.size() > 0) {
				String title = "您已经交接了计划，请等待顶岗人/轮岗人交接！";
				map.put("title", title);
			} else {
				PosLog posLog = new PosLog();
				posLog.setPlanId(recess.getId());
				posLog.setActionCode(recess.getActionCode());
				posLog.setOpeOpinion(recess.getOpeOpinion());
				recLogService.insert6(posLog);
			}
			list = null;
			pLog_temp = new PosLog();
			pLog_temp.setPlanId(recess.getId());
			pLog_temp.setActionCode("6");
			list = recLogService.find(pLog_temp);
			if (list.size() > 1) {
				recess.setStatus("6");
				recessService.updateStatus(recess);
			}

		} else if (recess.getStatus().equals("6")) {
			// 计划执行
			if (recess.getActionCode().equals("1")) {
				recess.setStatus("7");
			} else if (recess.getActionCode().equals("0")) {
				recess.setStatus("6");
			}
			recessService.updateStatus(recess);

			PosLog posLog = new PosLog();
			posLog.setPlanId(recess.getId());
			posLog.setActionCode(recess.getActionCode());
			posLog.setOpeOpinion(recess.getOpeOpinion());
			recLogService.insert7(posLog);// 提交轮岗计划
		} else if (recess.getStatus().equals("7")) {
			// 计划归档
			if (recess.getActionCode().equals("1")) {
				Recess rec = new Recess();
				rec.setId(recess.getId());
				recess = recessService.find(rec).get(0);
				recess.setStatus("8");

				User info = new User();
				info.setUserId(recess.getRepUserId());
				info = userService.find(info).get(0);

				/*
				 * 添加员工履历
				 */

				ResInfo resInfo = new ResInfo();

				resInfo.setUserId(recess.getRepUserId());
				resInfo.setPosCode(recess.getRepPos());
				resInfo.setBeginDate(info.getPosDate());
				resInfo.setEndDate(DateUtils.getDate().toString());
				resInfo.setStatus("1");
				resInfo.setRemark("");// 备注
				resInfo.setCreUserId(AccountShiroUtil.getCurrentUser().getUserId());
				resInfo.setCreDate(DateUtils.getDate().toString());
				addLog(OP_CLASS_OPER, OP_TYPE_ADD);
				resService.insert(resInfo);

				/*
				 * 更新顶岗员工信息
				 */

				User user = new User();
				user.setOrgNo(recess.getRecOrgNo());
				user.setPos(recess.getRecPos());
				user.setPosDate(DateUtils.getDate().toString());
				addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
				userService.update_pos_info(user);

			} else if (recess.getActionCode().equals("0")) {
				recess.setStatus("7");
			}
			recessService.updateStatus(recess);

			PosLog posLog = new PosLog();
			posLog.setPlanId(recess.getId());
			posLog.setActionCode(recess.getActionCode());
			posLog.setOpeOpinion(recess.getOpeOpinion());
			recLogService.insert8(posLog);// 提交轮岗计划
		}
		map.put("msg", "1");
		return map;
	}

	//
	@RequestMapping("info_urg")
	public String infoUrg(Recess recess, Model model) {
		String status = recess.getStatus();
		recess = recessService.find(recess).get(0);
		model.addAttribute("recess", recess);
		model.addAttribute("status", status);

		// 流程日志
		PosLog plog = new PosLog();
		plog.setPlanId(recess.getId());
		List<PosLog> list = recLogService.findAllList(plog);
		model.addAttribute("list", list);

		return "/posrot/recmanage/info_urg";
	}

	// 审批轮岗计划（通过）
	@ResponseBody
	@RequestMapping("urg")
	public Map<String, Object> urg(Recess recess, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		String status = recess.getStatus();

		if (status.equals("4")) {
			PosLog posLog = new PosLog();
			posLog.setPlanId(recess.getId());
			posLog.setActionCode(recess.getActionCode());
			posLog.setOpeOpinion(recess.getOpeOpinion());
			recLogService.insert9(posLog);// 接收轮岗计划
		} else if (status.equals("5")) {
			PosLog posLog = new PosLog();
			posLog.setPlanId(recess.getId());
			posLog.setActionCode(recess.getActionCode());
			posLog.setOpeOpinion(recess.getOpeOpinion());
			recLogService.insert10(posLog);// 提交轮岗计划
		}

		map.put("title", "success");
		return map;
	}

	// 已归档计划
	@RequestMapping("archives")
	public String archives(Recess recess, Model model, HttpServletRequest request, HttpServletResponse response) {
		recess.setStatus("8");// 已归档计划
		Page<Recess> page = recessService.findByPage(recess, new Page<Recess>(request, response));
		model.addAttribute("page", page);
		model.addAttribute("status", recess.getStatus());
		return "/posrot/recmanage/archives";
	}

	// 我的轮岗/强修
	@RequestMapping("mypos_list")
	public String mypos_list(Recess recess, Model model) {
		List<Recess> list = new ArrayList<Recess>();

		String userId = AccountShiroUtil.getCurrentUser().getUserId();

		recess.setStatus("4");// 待交接
		recess.setTempUserId(userId);
		List<Recess> list4 = recessService.find(recess);

		recess.setStatus("5");// 待接收
		recess.setTempUserId(userId);
		List<Recess> list5 = recessService.find(recess);

		for (int i = 0; i < list4.size(); i++) {
			if (list4.get(i).getId() != null) {
				list.add(list4.get(i));
			}
		}

		for (int i = 0; i < list5.size(); i++) {
			if (list5.get(i).getId() != null) {
				list.add(list5.get(i));
			}
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStatus().equals("4")) {
				PosLog plog = new PosLog();
				plog.setPlanId(list.get(i).getId());
				plog.setOpeUserId(userId);
				plog.setActionCode("5");
				recLogService.find(plog);
				if (!recLogService.find(plog).isEmpty()) {
					list.get(i).setStatus("5");
				}
			} else if (list.get(i).getStatus().equals("5")) {
				PosLog plog = new PosLog();
				plog.setPlanId(list.get(i).getId());
				plog.setOpeUserId(list.get(i).getRecUserId());
				plog.setActionCode("6");
				recLogService.find(plog);
				if (!recLogService.find(plog).isEmpty()) {
					list.get(i).setStatus("5");
				}
			}
		}
		model.addAttribute("list", list);
		return "/posrot/recmanage/mypos_list";
	}

	@ResponseBody
	@RequestMapping("downPlan")
	public String downPlan(Recess recess, Model model) {

		recess = recessService.find(recess).get(0);
		RecCreateWord recCreateWord = new RecCreateWord();
		String fileName = recCreateWord.create(recess);

		// 存储到附件表
		String fileSaveName = get32UUID() + "." + ".doc";
		AttachInfo attachInfo = new AttachInfo();
		// 删除旧计划附件
		attachInfo.setTab("T_POSROT_ROTATE");
		attachInfo.setTabKey(String.valueOf(recess.getId()));
		attachInfo.setCfgCode(Long.valueOf(4));
		attachInfo.setAppCode(Global.getConfig("gpi.sys.code"));
		attachInfoService.find(attachInfo);
		if (attachInfoService.find(attachInfo).size() > 0) {
			attachInfoService.delete(attachInfo);
		}

		// insert新计划附件
		attachInfo.setFileId(attachInfoService.getKey());
		attachInfo.setAppCode(Global.getConfig("gpi.sys.code"));
		attachInfo.setCfgCode(Long.valueOf(4));
		attachInfo.setTab("T_POSROT_ROTATE");
		attachInfo.setTabKey(String.valueOf(recess.getId()));
		attachInfo.setFileName(fileName);
		attachInfo.setFileType("word");
		attachInfo.setFileSaveName(fileSaveName);
		attachInfo.setCrtDate(DateUtils.getDate());
		attachInfo.setFileSize(null);
		attachInfoService.insert(attachInfo);

		return fileName;

	}

	@RequestMapping("download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		String fileName = request.getParameter("fileName");

		response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		try {
			String path = Global.getConfig("gpi.posrot.templet.rec.down");
			File file = new File(path + File.separator + fileName);
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
