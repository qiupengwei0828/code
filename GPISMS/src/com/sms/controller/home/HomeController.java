package com.sms.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sms.entity.Message;
import com.sms.entity.Sent;
import com.sms.service.message.MessageService;
import com.sms.service.sent.SentService;

@Controller
@RequestMapping("/sms/home/")
public class HomeController {

	@Autowired
	public MessageService messageService;

	@Autowired
	public SentService sentService;

	// @ResponseBody
	@RequestMapping("index")
	public String index(Object object, Model model) {
		return "sms/home/index";
	}

	// @ResponseBody
	@RequestMapping("data1")
	public String data1(Object object, Model model) {
		// 个系统发送数量明细
		List<Sent> applist = sentService.app_msg_count();
		// 总计
		int msgCountNum = sentService.findAllList(null).size();
		// 发送数百数量
		Message msg = new Message();
		msg.setStatus("2");
		int fail = messageService.findAllList(msg).size();
		model.addAttribute("applist", applist);
		model.addAttribute("msgCountNum", msgCountNum);
		model.addAttribute("fail", fail);
		return "sms/home/data/data1";
	}

	// @ResponseBody
	@RequestMapping("data2")
	public String data2(Object object, Model model) {
		String appName = "";
		String counts = "";
		List<Sent> list = sentService.app_msg_count();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				appName += "'" + list.get(i).getAppName() + "'" + ",";
				counts += list.get(i).getMsgCount() + ",";
			}
			model.addAttribute("appName", appName.substring(0, appName.length() - 1));
			model.addAttribute("counts", counts.subSequence(0, counts.length() - 1));
		}
		return "sms/home/data/data2";
	}

	// @ResponseBody
	@RequestMapping("data3")
	public String data3(Object object, Model model) {
		String months = "";
		String sums = "";
		List<Sent> list = sentService.sentCount();
		for (int i = 0; i < list.size(); i++) {
			months += "'" + list.get(i).getMonth() + "'" + ",";
			sums += list.get(i).getMsgCount() + ",";
		}
		model.addAttribute("months", months.substring(0, months.length() - 1));
		model.addAttribute("sums", sums.subSequence(0, sums.length() - 1));
		return "sms/home/data/data3";
	}

}
