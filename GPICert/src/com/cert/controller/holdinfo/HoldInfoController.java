package com.cert.controller.holdinfo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.cert.entity.Cert;
import com.cert.entity.CertAttInfo;
import com.cert.entity.CertJobCfg;
import com.cert.entity.HoldInfo;
import com.cert.service.cert.CertService;
import com.cert.service.certattinfo.CertAttInfoService;
import com.cert.service.certjobcfg.CertJobCfgService;
import com.cert.service.holdinfo.HoldInfoService;
import com.sys.entity.Org;
import com.sys.entity.User;
import com.sys.service.org.OrgService;
import com.sys.service.user.UserService;

@Controller
@RequestMapping("/cert/holdinfo/")
public class HoldInfoController extends BaseController<HoldInfo> {

	@Autowired
	private HoldInfoService holdInfoservice;

	@Autowired
	private UserService userService;

	@Autowired
	public CertService certservice;

	@Autowired
	public CertJobCfgService certJobCfgService;

	@Autowired
	public CertAttInfoService certAttInfoService;

	@Autowired
	public OrgService orgService;

	/*
	 * 显示我的证书
	 */
	@RequestMapping("index")
	public String index(HoldInfo holdInfo, Model model) {
		User user = AccountShiroUtil.getCurrentUser();
		String userid = user.getUserId();
		holdInfo.setUserId(userid);
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<HoldInfo> list = holdInfoservice.findAllList(holdInfo);
		model.addAttribute("list", list);
		return "cert/holdinfo/list";
	}

	/*
	 * 编辑我的证书
	 */
	@RequestMapping("form")
	public String form(HoldInfo holdInfo, Model model) {
		List<HoldInfo> editlist = new ArrayList<>();
		if (holdInfo.getId() != null) {
			HoldInfo holdIn = new HoldInfo();
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			// 持证员工信息
			holdIn = holdInfoservice.find(holdInfo).get(0);
			editlist = holdInfoservice.find(holdInfo);
			if (editlist.size() <= 9) {
				for (int i = 0; i < (9 - editlist.size()); i++) {
					HoldInfo in = new HoldInfo();
					editlist.add(in);
				}
			}
			model.addAttribute("editlist", editlist);
			model.addAttribute("holdInfo", holdIn);
		} else {
			for (int i = 0; i < 9; i++) {
				HoldInfo info = new HoldInfo();
				editlist.add(info);
			}
			model.addAttribute("editlist", editlist);
		}

		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Cert> certList = certservice.findAllList(null);
		model.addAttribute("certList", certList);
		return "cert/holdinfo/form";
	}

	/*
	 * 添加证书
	 */

	@RequestMapping("insert")
	public String insert(HttpServletRequest request, HttpServletResponse response, HoldInfo holdInfo, Model model) {
		try {
			String userId = AccountShiroUtil.getCurrentUser().getUserId();
			User user = userService.findFormatByLoginName(userId);
			// 加getKey SQL
			holdInfo.setId(holdInfoservice.getKey());
			holdInfo.setUserId(user.getUserId());
			holdInfo.setUserCert(user.getCertNo());
			// 证书状态1.已认证，2.待审核
			holdInfo.setStatus("2");
			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
			holdInfoservice.insert(holdInfo);
			if ((null != holdInfo.getFileId()) && (holdInfo.getFileId().length() > 0)) {
				String file_id = holdInfo.getFileId();
				String[] fileidArr = file_id.split(",");
				for (int i = 0; i < fileidArr.length; i++) {
					CertAttInfo att = new CertAttInfo();
					att.setFileId(Long.parseLong(fileidArr[i]));
					att.setTab("T_CERT_USER_HOLD_INFO");
					att.setTabId(holdInfo.getId());
					// 加 update SQL
					addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
					certAttInfoService.updateTabInfo(att);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/cert/holdinfo/index";
	}

	@RequestMapping("update")
	public String update(HoldInfo holdInfo, Model model) {
		try {
			holdInfo.setStatus("2");
			holdInfoservice.update(holdInfo);
			if ((null != holdInfo.getFileId()) && (holdInfo.getFileId().length() > 0)) {

				String file_id = holdInfo.getFileId();
				String[] fileidArr = file_id.split(",");
				for (int i = 0; i < fileidArr.length; i++) {
					CertAttInfo att = new CertAttInfo();
					att.setFileId(Long.parseLong(fileidArr[i]));
					att.setTab("T_CERT_USER_HOLD_INFO");
					att.setTabId(holdInfo.getId());
					// 加 update SQL
					addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
					certAttInfoService.updateTabInfo(att);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/cert/holdinfo/index";
	}

	/*
	 * 删除我的证书
	 */
	@RequestMapping("delete")
	public String delete(HoldInfo holdInfo, Model model) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			holdInfoservice.delete(holdInfo);
			CertAttInfo certAttInfo = new CertAttInfo();
			certAttInfo.setTabId(holdInfo.getId());
			// 删除证书扫描件
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			certAttInfoService.delete(certAttInfo);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/cert/holdinfo/index";
	}

	/*
	 * 查询详情
	 */
	@RequestMapping("info")
	public String info(CertJobCfg certJobCfg, Model model) {

		User user = AccountShiroUtil.getCurrentUser();
		String userid = user.getUserId();
		certJobCfg.setUserId(userid);

		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<CertJobCfg> list = certJobCfgService.findinfo(certJobCfg);
		model.addAttribute("list", list);
		return "/cert/holdinfo/info";
	}

	/*
	 * 持证审核
	 */
	@RequestMapping("check")
	public String check(HoldInfo holdInfo, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		HoldInfo info = new HoldInfo();
		User user = AccountShiroUtil.getCurrentUser();
		Org org = new Org();
		org.setOrgNo(user.getOrgNo());
		Org orginfo = orgService.get(org);
		if ("1".equals(orginfo.getOrgLevel())) {
			info.setOrgLevel("1");
		}
		info.setOrgDimField(user.getOrgInfo().getOrgDimField());
		info.setOwnDimOrgNo(user.getOrgInfo().getOwnDimOrgNo());

		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		Page<HoldInfo> page = holdInfoservice.check(info, new Page<HoldInfo>(request, response));
		model.addAttribute("page", page);
		return "/cert/holdinfo/check";
	}

	/*
	 * 持证审核详情
	 */
	@RequestMapping("checkinfo")
	public String checkinfo(HoldInfo holdInfo, Model model) {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		// 证书扫描件
		List<HoldInfo> imglist = holdInfoservice.checkinfo(holdInfo);
		// 持证员工信息
		HoldInfo info = holdInfoservice.checkinfo(holdInfo).get(0);
		model.addAttribute("imglist", imglist);
		model.addAttribute("holdInfo", info);
		return "/cert/holdinfo/checkform";
	}

	/*
	 * 审核通过
	 */
	@RequestMapping("pass")
	public String pass(HoldInfo holdInfo, Model model) {
		HoldInfo info = new HoldInfo();
		info.setId(holdInfo.getId());
		info.setStatus(holdInfo.getStatus());
		if ("".equals(holdInfo.getRemark()) && holdInfo.getRemark() == "") {
			info.setRemark("认证通过");
		} else {
			info.setRemark(holdInfo.getRemark().trim());
		}
		addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
		holdInfoservice.pass(info);
		return "redirect:" + "/cert/holdinfo/check";
	}
}
