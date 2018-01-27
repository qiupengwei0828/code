package com.sys.controller.pos;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.page.Page;
import com.sys.entity.Pos;
import com.sys.service.pos.PosService;

@Controller
@RequestMapping("/sys/pos/")
public class PosController extends BaseController<Pos> {

	@Autowired
	public PosService service;

	/*
	 * 岗位管理
	 */
	@RequestMapping("index")
	public String index(Pos pos, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		Page<Pos> page = service.findByPage(pos, new Page<Pos>(request, response));
		model.addAttribute("page", page);
		return "/sys/pos/list";
	}

	/*
	 * 修改/添加
	 */
	@RequestMapping("form")
	public String from(Pos pos, Model model, HttpServletRequest request, HttpServletResponse response) {
		if (!"".equals(pos.getId()) && pos.getId() != null) {
			Pos posinfo = service.find(pos).get(0);
			model.addAttribute("pos", posinfo);
		}
		// 轮岗抢修修改岗位信息
		model.addAttribute("appCode", pos.getAppCode());
		return "/sys/pos/form";
	}

	/*
	 * 修改岗位
	 */
	@RequestMapping("update")
	public String update(Pos pos, Model model) {
		service.update(pos);
		if ("posrot".equals(pos.getAppCode())) {
			return "redirect:" + "/posrot/keypos/index";
		}

		return "redirect:" + "/sys/pos/index";
	}

	/*
	 * 添加岗位
	 */
	@RequestMapping("insert")
	public String insert(Pos pos, Model model) {
		service.insert(pos);

		if ("posrot".equals(pos.getAppCode())) {
			return "redirect:" + "/posrot/keypos/index";
		}

		return "redirect:" + "/sys/pos/index";
	}

	/*
	 * 删除岗位
	 */
	@RequestMapping("delete")
	public String delete(Pos pos, Model model) {
		service.delete(pos);
		return "redirect:" + "/sys/pos/index";
	}
}
