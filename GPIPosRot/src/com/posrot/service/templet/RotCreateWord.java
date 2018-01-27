package com.posrot.service.templet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.base.utils.DateUtils;
import com.base.utils.comm.Global;
import com.base.utils.security.AccountShiroUtil;
import com.posrot.entity.Rotation;
import com.sys.entity.Org;
import com.sys.service.org.OrgService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

// 使用freemark生成word
public class RotCreateWord {

	@Autowired
	public OrgService orgService;

	public String create(Rotation rotation) {

		String fileName = "rot_" + "plan_" + rotation.getRotUserName() + "_" + rotation.getRepUserName() + "_" + rotation.getId() + ".doc";

		RotCreateWord createWord = new RotCreateWord("");
		createWord.setTemplateName("templet_01.ftl");
		createWord.setFileName(fileName);

		String down_path = Global.getConfig("gpi.posrot.templet.rot.down");
		File file = new File(down_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		createWord.setFilePath(down_path);

		createWord.createWord(rotation);

		return fileName;

	}

	private void createWord(Rotation rotation) {

		Template t = null;

		try {
			t = configuration.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		File outFile = new File(filePath + fileName);
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rotUserName", rotation.getRotUserName());

		String posrotAdminOrgName = "";

		Org org = AccountShiroUtil.getCurrentUser().getOrgInfo();

		switch (org.getOrgLevel()) {
		case "1":
			posrotAdminOrgName = org.getOrgName1st();
			break;
		case "2":
			posrotAdminOrgName = org.getOrgName2nd();
			break;
		case "3":
			posrotAdminOrgName = org.getOrgName3rd();
			break;
		case "4":
			posrotAdminOrgName = org.getOrgName4th();
			break;
		}

		map.put("rotateNo", rotation.getRotateNo());

		map.put("posrotAdminOrgName", posrotAdminOrgName);

		String temp_date = rotation.getToDate();
		map.put("toYYYY", temp_date.substring(0, 4));
		map.put("toMM", temp_date.substring(5, 7));
		map.put("toDD", temp_date.substring(8, 10));
		map.put("toPosName", rotation.getToPosName());
		map.put("repUserName", rotation.getRepUserName());

		String orgName = AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgName();

		map.put("nowOrgName", orgName);
		temp_date = "";
		temp_date = DateUtils.getDate();
		map.put("crtYYYY", temp_date.substring(0, 4));
		map.put("crtMM", temp_date.substring(5, 7));
		map.put("crtDD", temp_date.substring(8, 10));

		try {
			t.process(map, out);
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 模板文件位置
	public RotCreateWord(String templatePath) {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(this.getClass(), templatePath);
	}

	// 模板配置
	private Configuration configuration;

	// freemark模板的名字
	private String templateName;

	// 生成文件名
	private String fileName;

	// 生成文件路径
	private String filePath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public RotCreateWord() {
		super();
	}

}
