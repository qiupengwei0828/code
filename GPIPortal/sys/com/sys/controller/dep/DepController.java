package com.sys.controller.dep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.sys.entity.Dep;
import com.sys.service.dep.DepService;

@Controller
@RequestMapping("/sys/dep/")
public class DepController extends BaseController<Dep> {
	@Autowired
	private DepService service;

	@RequestMapping("index")
	public String index(Dep dep, Model model) {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Dep> list = service.findAllList(dep);
		model.addAttribute("list", list);
		List<Dep> depCodeList = service.findAllList(dep);
		model.addAttribute("depCodeList", depCodeList);
		return "/sys/dep/list";
	}

	@RequestMapping("form")
	public String form(Dep dep, Model model) {
		if (null != dep.getDepCode()) {
			dep = service.findAllList(dep).get(0);
			model.addAttribute(dep);
		}

		return "/sys/dep/form";
	}

	@RequestMapping("insert")
	public String insert(Dep dep, Model model) {
		try {
			service.insert(dep);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/sys/dep/index";
	}

	@RequestMapping("delete")
	public String delete(Dep dep, Model model) {
		try {
			service.delete(dep);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return "redirect:" + "/sys/dep/index";
	}

	@RequestMapping("update")
	public String update(Dep dep, Model model) {
		try {
			service.update(dep);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "redirect:" + "/sys/dep/index";
	}

	@RequestMapping("existsDepCode")
	@ResponseBody
	public Map<String, Object> existsDepCode(Dep dep) {
		Map<String, Object> map = new HashMap<String, Object>();
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		List<Dep> depinfo = service.existsDepCode(dep);
		if (depinfo.size() > 0) {
			map.put("msg", "业务编码已存在，请重试！");
		} else {
			map.put("msg", "none");
		}
		return map;
	}

}
