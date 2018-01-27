package com.sys.controller.dic;

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
import com.base.utils.TimeUtils;
import com.base.utils.security.AccountShiroUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.entity.Dic;
import com.sys.entity.DicType;
import com.sys.entity.User;
import com.sys.service.dic.DicService;
import com.sys.service.dic.DicTypeService;

@Controller
@RequestMapping("/sys/dic/")
public class DictController extends BaseController<Dic> {

	@Autowired
	public DicService service;

	@Autowired
	public DicTypeService typeService;

	/*
	 * 首页管理
	 */
	@RequestMapping(value = { "index" })
	public String index(DicType dicType, Model model) {
		return "/sys/dic/index";
	}

	/**
	 * 获取机构JSON数据。
	 * 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		try {
			// 选取用户所在本级机构所属的本级及下级所有机构
			DicType dicType = new DicType();
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			List<DicType> list = typeService.findAllList(dicType);
			for (int i = 0; i < list.size(); i++) {
				DicType e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getTypeCode());
				map.put("pId", e.getPcode());
				map.put("name", e.getTypeName());
				mapList.add(map);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mapList;
	}

	/**
	 * 查询字典
	 */
	@RequestMapping("diclist")
	public String diclist(Dic dic, HttpServletRequest request, HttpServletResponse response, Model model) {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		Page<Dic> page = service.findByPage(dic, new Page<Dic>(request, response));
		model.addAttribute("page", page);
		return "/sys/dic/list";
	}

	/**
	 * 编辑字典
	 */
	@RequestMapping("form")
	public String form(Dic dicItem, Model model) {
		try {
			if (dicItem.getId() != null) {
				addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
				List<Dic> dicInfo = service.find(dicItem);
				for (Dic dic : dicInfo) {
					model.addAttribute("dic", dic);
				}
			}
			model.addAttribute("dicItem", dicItem);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "/sys/dic/form";
	}

	/*
	 * 添加字典信息
	 */
	@RequestMapping("insert")
	public String insert(Dic dic) {
		try {
			User user = AccountShiroUtil.getCurrentUser();
			String userName = user.getUserName();
			dic.setCrtUser(userName);
			String nowTime = TimeUtils.getTime();
			dic.setCrtTime(nowTime);
			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
			service.insert(dic);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/sys/dic/diclist";
	}

	/*
	 * 删除字典信息
	 */
	@RequestMapping("delete")
	public String delete(Dic dic) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_DEL);
			service.delete(dic);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/sys/dic/diclist";

	}

	/*
	 * 修改字典信息
	 */
	@RequestMapping("update")
	public String update(Dic dic) {
		try {
			addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
			service.update(dic);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/sys/dic/diclist";
	}
}
