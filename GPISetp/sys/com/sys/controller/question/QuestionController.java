package com.sys.controller.question;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.controller.BaseController;
import com.base.page.Page;
import com.base.utils.DateUtils;
import com.base.utils.comm.Global;
import com.sys.entity.AttachInfo;
import com.sys.entity.Que;
import com.sys.service.que.QueService;

@Controller
@RequestMapping("/sys/question/")
public class QuestionController extends BaseController<Object> {

	@Autowired
	public QueService queservice;

	// 问题反馈
	@RequestMapping("form")
	public String form(Model model) throws UnsupportedEncodingException {
		return "/sys/question/form";
	}

	@ResponseBody
	@RequestMapping(value = "upload_question_img")
	public String upload_question_img(Que que, AttachInfo attachInfo, HttpServletRequest request, HttpServletResponse response, @RequestParam("questionImg") MultipartFile questionImg, Model model)
			throws IllegalStateException, IOException, ServletException {
		if (null != questionImg) {
			// 文件ID
			long imgKey = queservice.getKey();
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			attachInfo.setFileId(imgKey);
			// 附件所属系统
			addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
			attachInfo.setAppCode(Global.getConfig("gpi.sys.code"));
			// 配置ID
			attachInfo.setCfgCode(Long.valueOf(2));
			// 所属表
			attachInfo.setTab("T_SYS_QUE_CON");
			// 表ID
			attachInfo.setTabKey("");// 先存储在更新
			// 文件原名称
			attachInfo.setFileName(questionImg.getOriginalFilename());
			// 文件类型
			attachInfo.setFileType(questionImg.getContentType());
			// 文件原名称
			String originFileName = questionImg.getOriginalFilename();
			// 获取文件的扩展名
			String extName = originFileName.substring(originFileName.lastIndexOf(".") + 1);// 扩展名
			// 文件重命名，UUID方式
			String fileSaveName = get32UUID() + "." + extName;
			attachInfo.setFileSaveName(fileSaveName);
			// 文件达小
			attachInfo.setFileSize(questionImg.getSize());
			attachInfo.setCrtDate(DateUtils.getDate().toString());

			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
			// attachInfoService.insert(attachInfo);

			try {
				// 这里使用Apache的FileUtils方法来进行保存
				// 获取保存的路径，
				String separator = File.separator;
				String paths = "help" + separator + "question" + separator + "img";
				String savePath = request.getSession().getServletContext().getRealPath(paths);

				File file = new File(savePath);
				// 保存文件
				if (!file.exists() && !file.isDirectory()) {
					file.mkdir();
				}
				// 保存图片
				FileUtils.copyInputStreamToFile(questionImg.getInputStream(), new File(savePath, fileSaveName));
				response.setContentType("text/html;charset=utf-8");
				// 返回图片的URL地址
				response.getWriter().write(request.getContextPath() + "/help/" + "question/" + "img/" + fileSaveName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("insert")
	public Map<String, Object> insert(HttpServletRequest request, HttpServletResponse response, Que que, Model model) throws UnsupportedEncodingException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		response.setContentType("text/html;charset=utf-8");
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		String key = String.valueOf(queservice.getKey());
		que.setId(key);
		que.setSysId(Global.getConfig("gpi.sys.code"));
		addLog(OP_CLASS_OPER, OP_TYPE_ADD);
		queservice.insert(que);
		resMap.put("success", "success");
		return resMap;
	}

	@RequestMapping("reload")
	public String reload(Model model) throws UnsupportedEncodingException {
		return "redirect:/sys/index";
	}

	@RequestMapping("list")
	public String list(Que que, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		Page<Que> page = queservice.findByPage(que, new Page<Que>(request, response));
		model.addAttribute("page", page);
		return "/sys/question/list";
	}

	@RequestMapping("info")
	public String info(Que que, Model model) throws UnsupportedEncodingException {

		logger.info("============" + que.getId());

		addLog(OP_CLASS_OPER, OP_TYPE_QUERY);
		que = queservice.find(que).get(0);
		model.addAttribute("que", que);
		return "/sys/question/info";
	}

	@RequestMapping("update")
	public String update(Que que, Model model) throws UnsupportedEncodingException {
		addLog(OP_CLASS_OPER, OP_TYPE_UPDATE);
		queservice.updateStatus(que);
		return "redirect:/sys/question/list";
	}
}
