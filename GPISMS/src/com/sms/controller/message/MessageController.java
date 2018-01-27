package com.sms.controller.message;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.sms.entity.Message;
import com.sms.service.message.MessageService;
import com.sys.entity.App;
import com.sys.service.app.AppService;

@Controller
@RequestMapping("/sms/message/")
public class MessageController extends BaseController<Message> {

	@Autowired
	public MessageService messageService;

	@Autowired
	public AppService appService;

	@RequestMapping("list")
	public String list(Message message, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<App> applist = appService.findAllList(null);
		model.addAttribute("applist", applist);
		return "sms/message/list";
	}

	@ResponseBody
	@RequestMapping("msglist")
	public List<Message> msglist(Message message, Model model, HttpServletRequest request, HttpServletResponse response) {

		String beginDate = request.getParameter("beginDate");
		String endsDate = request.getParameter("endsDate");
		if (beginDate != null || endsDate != null) {
			model.addAttribute("beginDate", beginDate);
			model.addAttribute("endsDate", endsDate);
		}
		List<Message> list = messageService.findAllList(message);

		return list;
	}

	@RequestMapping("info")
	public String info(Message message, Model model) {
		message = messageService.find(message).get(0);
		model.addAttribute("message", message);
		return "sms/message/info";
	}

	@RequestMapping("insert")
	public String insert(Message message, Model model) {
		messageService.insert(message);
		return null;
	}

}
