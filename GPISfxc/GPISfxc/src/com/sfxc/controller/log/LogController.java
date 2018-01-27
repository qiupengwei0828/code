package com.sfxc.controller.log;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.page.Page;
import com.sfxc.entity.LogMessage;
import com.sfxc.service.LogMessageService;


@Controller
@RequestMapping("/sfxc")
public class LogController {
	@Autowired
	public LogMessageService logMessageService;
	

	/**
	 *日志信息查询
	 * @author Panwf
	 * @date 2016年7月12日
	 * @since:
	 */
	@RequestMapping("/log/list")
	public String list(LogMessage logMessage, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {		
		Page<LogMessage> page = logMessageService.findByPage(logMessage, new Page<LogMessage>(request,response));
		model.addAttribute("page",page);
		model.addAttribute("logMessage",logMessage);
		return "/sfxc/log/list";
	}
	/**
	 * 日志信息详情
	 * @author Panwf
	 * @date 2016年7月21日
	 * @since:
	 */
	@RequestMapping("/log/queryList")
	public String queryList(LogMessage logMessage, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {		
		logMessage = logMessageService.find(logMessage).get(0);		
		model.addAttribute("logMessage",logMessage);
		return "/sfxc/log/listDetail";
	}
	
}
