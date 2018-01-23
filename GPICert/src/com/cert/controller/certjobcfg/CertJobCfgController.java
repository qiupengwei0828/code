package com.cert.controller.certjobcfg;

import java.io.UnsupportedEncodingException;
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
import com.cert.entity.CertJobCfg;
import com.cert.service.certjobcfg.CertJobCfgService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.Dic;
import com.sys.utils.DicUtils;

@Controller
@RequestMapping("/cert/certjobcfg/")
public class CertJobCfgController extends BaseController<CertJobCfg> {

	@Autowired
	public CertJobCfgService service;

	/*
	 * 岗位要求证书
	 */
	@RequestMapping("index")
	public String index(CertJobCfg certJobCfg, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		return "/cert/certjobcfg/index";
	}

	/*
	 * 持证要求
	 */
	@RequestMapping("holdReq")
	public String holdReq(CertJobCfg certJobCfg, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		model.addAttribute("posCodeValue", certJobCfg.getPosCode());
		return "/cert/certjobcfg/list";
	}

	// 岗位持证列表
	@RequestMapping("hold_list")
	public String hold_list(CertJobCfg certJobCfg, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			Page<CertJobCfg> page = service.findByPage(certJobCfg, new Page<CertJobCfg>(request, response));
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "/cert/certjobcfg/form";
	}

	/*
	 * form
	 */
	@RequestMapping("form")
	public String form(CertJobCfg certJobCfg, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		try {
			if (certJobCfg.getPosCode() != null) {
				addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
				Page<CertJobCfg> page = service.findByPage(certJobCfg, new Page<CertJobCfg>(request, response));
				addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
				service.find(certJobCfg);
				model.addAttribute("page", page);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "/cert/certjobcfg/form";
	}

	/*
	 * 删除岗位要求证书
	 */
	@RequestMapping("delete")
	public String delete(CertJobCfg certJobCfg, Model model) throws UnsupportedEncodingException {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			service.delete(certJobCfg);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/cert/certjobcfg/form?posCode=" + certJobCfg.getPosCode() + "&holdReq=" + certJobCfg.getHoldReq();
	}

	/*
	 * 添加岗位要求证书
	 */
	@RequestMapping("insert")
	public String insert(CertJobCfg certJobCfg, Model model) throws UnsupportedEncodingException {
		try {
			String[] certCodeStr = certJobCfg.getCertCode().split(",");
			for (int i = 0; i < certCodeStr.length; i++) {
				CertJobCfg cfg = new CertJobCfg();
				cfg.setCertCode(certCodeStr[i]);
				cfg.setPosCode(certJobCfg.getPosCode());
				addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
				List<CertJobCfg> list = service.findCertCode(cfg);
				if (list.size() != 0) {
					continue;
				}
				cfg.setPosCode(certJobCfg.getPosCode());
				cfg.setHoldReq(certJobCfg.getHoldReq());
				cfg.setRemark(certJobCfg.getRemark());
				addLog(OP_CLASS_OPER, OP_TYPE_ADD);
				service.insert(cfg);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/cert/certjobcfg/form?posCode=" + certJobCfg.getPosCode() + "&holdReq=" + certJobCfg.getHoldReq();
	}

	/*
	 * 岗位要求证书查询
	 */
	@RequestMapping("find")
	public String find(CertJobCfg certJobCfg, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			Page<CertJobCfg> page = service.findJobCfg(certJobCfg, new Page<CertJobCfg>(request, response));
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		model.addAttribute("certJobCfg", certJobCfg);
		return "/cert/certjobcfg/find";
	}

	/*
	 * 岗位要求证书查询详情
	 */
	@RequestMapping("jobCertHoldInfo")
	public String jobCertHoldInfo(CertJobCfg certJobCfg, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			List<CertJobCfg> list = service.jobCertHoldInfo(certJobCfg);
			model.addAttribute("list", list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "/cert/certjobcfg/info";
	}

	/*
	 * 获取岗位JSON数据
	 */
	@ResponseBody
	@RequestMapping(value = "posTreeData")
	public List<Map<String, Object>> posTreeData() {

		List<Map<String, Object>> mapList = Lists.newArrayList();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);

		List<Dic> poslist = DicUtils.getDicList("DIC_USER_POSITION");

		for (int i = 0; i < poslist.size(); i++) {
			Dic dic = poslist.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", dic.getpValue());
			// map.put("pId", pos.getId());
			map.put("pId", "0");
			map.put("name", dic.getpName());
			mapList.add(map);
		}
		return mapList;
	}

	/*
	 * 获取持证要求JSON数据
	 */
	@ResponseBody
	@RequestMapping(value = "holdReqTreeData")
	public List<Map<String, Object>> holdReqTreeData() {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Dic> list = DicUtils.getDicList("DIC_CERT_HOLD");
		for (int i = 0; i < list.size(); i++) {
			Dic d = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", d.getpValue());
			map.put("pId", d.getId());
			map.put("name", d.getpName());
			mapList.add(map);
		}
		return mapList;
	}
}
