package com.cert.controller.certattinfo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.controller.BaseController;
import com.base.utils.DateUtils;
import com.base.utils.comm.Global;
import com.cert.entity.CertAttInfo;
import com.cert.service.certattinfo.CertAttInfoService;
import com.cert.service.holdinfo.HoldInfoService;

@Controller
@RequestMapping("/cert/certattinfo/")
public class CertAttInfoController extends BaseController<CertAttInfo> {

	@Autowired
	public CertAttInfoService certAttInfoService;

	@Autowired
	public HoldInfoService holdInfoService;

	@RequestMapping(value = "uploadFile", produces = "atext/html;charset=UTF-8")
	public String uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("certFile") MultipartFile certFile, Model model) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (null != certFile) {
			// 获取保存的路径，
			// 文件原名称
			String originFileName = certFile.getOriginalFilename();
			// todo:文件重命名，UUID方式
			// 获取文件的扩展名
			String extName = originFileName.substring(originFileName.lastIndexOf(".") + 1);// 扩展名
			String fileSaveName = get32UUID() + "." + extName;
			Long fileSize = certFile.getSize();
			String fileType = certFile.getContentType();
			String crtDate = DateUtils.getDate();
			// todo:调用保存入库方法，将文件信息保存到数据库（1.先了主键getKey() 给fileId)
			CertAttInfo certAttInfo = new CertAttInfo();
			certAttInfo.setFileId(certAttInfoService.getKey());
			certAttInfo.setCrtDate(crtDate);
			certAttInfo.setFileName(originFileName);
			certAttInfo.setFileSize(fileSize);
			certAttInfo.setFileType(fileType);
			certAttInfo.setFileSaveName(fileSaveName);
			addLog(OP_CLASS_OPER, OP_TYPE_ADD);
			certAttInfoService.insert(certAttInfo);
			try {
				// 这里使用Apache的FileUtils方法来进行保存
				// 获取保存的路径，
				String savePath = Global.getConfig("cert.savepath");
				FileUtils.copyInputStreamToFile(certFile.getInputStream(), new File(savePath, fileSaveName));
				// todo:返回前台：1 附件保存的主键fileId；2图片URL；3处理结果；
				String filePath = "/certfilepath/" + fileSaveName;
				resMap.put("fileId", certAttInfo.getFileId());
				resMap.put("status", "0");
				resMap.put("filePath", filePath);

				// String resStr = "fileId:" + certAttInfo.getFileId() + "," +
				// "status:" + "0" + "filePath:" + filePath;

				String resStr = JSONObject.fromObject(resMap).toString();

				// response.setCharacterEncoding("utf-8");
				// response.setContentType("text/html");
				response.setContentType("text/html;charset=utf-8");
				// response.getWriter().write(resMap.toString());
				// response.getWriter().write(certAttInfo.getFileId().toString());
				response.getWriter().write(resStr);
				response.getWriter().flush();
			} catch (IOException e) {
				resMap.put("status", "1");
				e.printStackTrace();
			}
		}
		// return resMap.toString();
		return null;
	}

	/*
	 * 通过Ajax上传图片，并对图片进行删除
	 */
	@RequestMapping("fileUpdate")
	@ResponseBody
	public Map<String, Object> fileUpdate(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String tabId = request.getParameter("id");
		CertAttInfo att = new CertAttInfo();
		att.setTabId(Long.parseLong(tabId));
		addLog(OP_CLASS_OPER, OP_TYPE_DEL);
		certAttInfoService.delete(att);
		return map;
	}

	/*
	 * 删除证书扫描件
	 */
	@RequestMapping("deleteCertImg")
	@ResponseBody
	public Map<String, Object> deleteCertImg(CertAttInfo attInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		addLog(OP_CLASS_OPER, OP_TYPE_DEL);
		certAttInfoService.deleteCertImg(attInfo);
		return map;
	}

}
