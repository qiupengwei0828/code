package com.posrot.controller.cruxpos;

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
import com.sys.entity.Pos;
import com.sys.service.pos.PosService;

/*
 * 关键岗位详情
 */
@Controller
@RequestMapping("/posrot/keypos/")
public class CruxPosInfoController extends BaseController<Pos> {

	@Autowired
	public PosService service;

	/*
	 * 显示关键岗位列表
	 */
	@RequestMapping("index")
	public String index(Model model, Pos pos, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		// 查询出关键岗位
		pos.setPrimary("1");
		Page<Pos> page = service.findByPage(pos, new Page<Pos>(request,
				response));
		model.addAttribute("page", page);
		return "/posrot/keypos/list";
	}

	/*
	 * 显示关键岗位信息
	 */
	@RequestMapping("info")
	public String info(Model model, Pos pos, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		// 查询出关键岗位
		pos.setPrimary("1");
		List<Pos> list = service.find(pos);
		model.addAttribute("list", list);
		return "/posrot/keypos/info";
	}
}
