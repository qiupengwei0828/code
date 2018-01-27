package com.posrot.controller.jjqd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.base.controller.BaseController;
import com.base.utils.DateUtils;
import com.base.utils.comm.Global;
import com.base.utils.security.AccountShiroUtil;
import com.posrot.service.templet.CreateJJQDWord;
import com.sys.entity.AttachInfo;
import com.sys.service.attachinfo.AttachInfoService;

/*
 * 轮岗计划制定
 */
@Controller
@RequestMapping("/posrot/jjqd/")
public class JJQDController extends BaseController<Object> {

	@Autowired
	public AttachInfoService attachInfoService;

	@RequestMapping("down_JJQD")
	public void down_JJQD(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		String path = Global.getConfig("gpi.posrot.jjqd_templet");
		String fileName = Global.getConfig("gpi.posrot.jjqd_templet_name");
		String fileNamePath = path + fileName;
		File file_temp = new File(fileNamePath);
		if (!file_temp.exists()) {
			CreateJJQDWord createJJQDWord = new CreateJJQDWord();
			createJJQDWord.create();
		}

		/*
		 * ----------------------------------------------------------
		 */

		fileName = "";
		fileName = request.getParameter("fileName");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		path = "";
		response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		try {
			path = Global.getConfig("gpi.posrot.jjqd_templet");
			File file = new File(path + File.separator + fileName);
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "up_JJQD", produces = "atext/html;charset=UTF-8")
	public String up_JJQD(HttpServletRequest request, HttpServletResponse response, @RequestParam("jjqdFile") MultipartFile jjqdFile, Model model) {

		String tabKey = request.getParameter("tabKey");
		String actionType = request.getParameter("actionType");

		response.setContentType("text/plain; charset=UTF-8");

		if (null != jjqdFile) {
			// 获取保存的路径,文件原名称
			String originFileName = jjqdFile.getOriginalFilename();

			// 获取文件的扩展名
			String extName = originFileName.substring(originFileName.lastIndexOf(".") + 1);// 扩展名

			String userName = AccountShiroUtil.getCurrentUser().getUserName();

			// 文件重命名，UUID方式
			String fileSaveName = "";
			if ("rot".equals(actionType)) {
				fileSaveName = "rot_" + userName + "_" + get32UUID() + "." + extName;
			} else if ("rec".equals(actionType)) {
				fileSaveName = "rec_" + userName + "_" + get32UUID() + "." + extName;
			}

			Long fileSize = jjqdFile.getSize();
			String fileType = jjqdFile.getContentType();

			String crtDate = DateUtils.getDate();

			AttachInfo attachInfo = new AttachInfo();
			long key = attachInfoService.getKey();

			attachInfo.setAppCode(Global.getConfig("gpi.sys.code"));

			if ("rot".equals(actionType)) {
				attachInfo.setCfgCode(Long.valueOf(5));
			} else if ("rec".equals(actionType)) {
				attachInfo.setCfgCode(Long.valueOf(6));
			}

			attachInfo.setTab("T_POSROT_ROTATE");
			attachInfo.setTabKey(tabKey);
			attachInfo.setRemark(AccountShiroUtil.getCurrentUser().getUserName());

			if (attachInfoService.find(attachInfo).size() > 0) {
				AttachInfo temp_atta = attachInfoService.find(attachInfo).get(0);
				String temp_fileName = temp_atta.getFileSaveName();

				File file = null;
				if ("rot".equals(actionType)) {
					file = new File(Global.getConfig("gpi.posrot.jjqd.rot.up") + temp_fileName);
				} else if ("rec".equals(actionType)) {
					file = new File(Global.getConfig("gpi.posrot.jjqd.rec.up") + temp_fileName);
				}
				if (file.exists()) {
					file.delete();
				}
				attachInfoService.delete(attachInfo);
			}

			attachInfo.setFileId(key);
			attachInfo.setCrtDate(crtDate);
			attachInfo.setFileName(originFileName);
			attachInfo.setFileSize(fileSize);
			attachInfo.setFileType(fileType);
			attachInfo.setFileSaveName(fileSaveName);

			addLog(OP_CLASS_OPER, OP_TYPE_ADD);

			attachInfoService.insert(attachInfo);
			try {
				// 获取保存的路径，
				String savePath = "";
				if ("rot".equals(actionType)) {
					savePath = Global.getConfig("gpi.posrot.jjqd.rot.up");
				} else if ("rec".equals(actionType)) {
					savePath = Global.getConfig("gpi.posrot.jjqd.rec.up");
				}
				File filePath = new File(savePath);

				if (!filePath.exists()) {
					filePath.mkdirs();
				}

				FileUtils.copyInputStreamToFile(jjqdFile.getInputStream(), new File(savePath, fileSaveName));
				// String resStr = JSONObject.fromObject(resMap).toString();

				String outPut = "1";
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(outPut);
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping("down_notify")
	public void down_notify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		String fileName = request.getParameter("fileName");
		String action = request.getParameter("action");
		String type = request.getParameter("type");

		String path = "";
		if ("notify".equals(type)) {

			if ("rot".equals(action)) {
				path = Global.getConfig("gpi.posrot.templet.rot.down");
			} else if ("rec".equals(action)) {
				path = Global.getConfig("gpi.posrot.templet.rec.down");
			}

		} else if ("done".equals(type)) {

			if ("rot".equals(action)) {
				path = Global.getConfig("gpi.posrot.jjqd.rot.up");
			} else if ("rec".equals(action)) {
				path = Global.getConfig("gpi.posrot.jjqd.rec.up");
			}

		}

		response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		try {

			File file = new File(path + File.separator + fileName);
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
