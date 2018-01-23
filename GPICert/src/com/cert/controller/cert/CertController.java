package com.cert.controller.cert;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.base.controller.BaseController;
import com.base.page.Page;
import com.cert.entity.Cert;
import com.cert.entity.CertJobCfg;
import com.cert.entity.HoldInfo;
import com.cert.service.cert.CertService;
import com.cert.service.certjobcfg.CertJobCfgService;
import com.cert.service.holdinfo.HoldInfoService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/cert/cert/")
public class CertController extends BaseController<Cert> {

	@Autowired
	public CertService certservice;

	@Autowired
	public CertJobCfgService certJobCfgService;

	@Autowired
	public HoldInfoService holdInfoService;

	@RequestMapping("index")
	public String index(Cert cert, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Cert> certList = certservice.findAllList(cert);
		model.addAttribute("certList", certList);
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		Page<Cert> page = certservice.findByPage(cert, new Page<Cert>(request, response));
		model.addAttribute("page", page);
		model.addAttribute("cert", cert);
		return "cert/cert/list";
	}

	@RequestMapping("form")
	public String form(Cert cert, Model model) {
		if (cert.getCertCode() != null) {
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			cert = certservice.find(cert).get(0);
			model.addAttribute("cert", cert);
		}
		return "/cert/cert/form";
	}

	@RequestMapping("addCert")
	public String addCert(Cert cert) {
		try {
			cert.setStatus("1");
			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
			certservice.insert(cert);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/cert/cert/index";
	}

	@RequestMapping("existsCertCode")
	@ResponseBody
	public Map<String, Object> existsCertCode(Cert cert) {
		Map<String, Object> map = new HashMap<String, Object>();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Cert> certinfo = certservice.existsCertCode(cert);
		if (certinfo.size() > 0) {
			map.put("msg", "当前证书编码已存在！");
		} else {
			map.put("msg", "none");
		}
		return map;
	}

	/*
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("deleteCheck")
	public Map<String, Object> deleteCheck(Cert cert) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CertJobCfg certJobCfg = new CertJobCfg();
			certJobCfg.setCertCode(cert.getCertCode());
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			List<CertJobCfg> job = certJobCfgService.find(certJobCfg);
			HoldInfo holdInfo = new HoldInfo();
			holdInfo.setCertCode(cert.getCertCode());
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			List<HoldInfo> hold = holdInfoService.find(holdInfo);
			if (job.size() + hold.size() == 0) {
				map.put("msg", "success");
			} else {
				map.put("msg", "error");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return map;
	}

	/*
	 * 删除
	 */
	@RequestMapping("delete")
	public String delete(Cert cert) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			certservice.delete(cert);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/cert/cert/index";
	}

	/*
	 * 
	 * 更新
	 */
	@RequestMapping("update")
	public String update(Cert cert) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
			certservice.update(cert);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:/cert/cert/index";
	}

	/*
	 * 获取机构JSON数据。
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Cert cert = new Cert();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Cert> list = certservice.findAllList(cert);
		for (int i = 0; i < list.size(); i++) {
			Cert e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getCertCode());
			map.put("pId", e.getIndustry());
			map.put("name", e.getCertName());
			mapList.add(map);
		}
		return mapList;
	}

	/*
	 * 岗位选择证书
	 */
	@RequestMapping("certSelect")
	public String certSelect(Cert cert, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		Page<Cert> page = certservice.findByPage(cert, new Page<Cert>(request, response));
		model.addAttribute("page", page);
		return "/cert/comm/certList";
	}

}
