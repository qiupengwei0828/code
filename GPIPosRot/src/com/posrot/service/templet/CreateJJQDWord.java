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

import com.base.utils.comm.Global;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

// 使用freemark生成word
public class CreateJJQDWord {

	public String create() {

		String fileName = Global.getConfig("gpi.posrot.jjqd_templet_name");

		CreateJJQDWord createWord = new CreateJJQDWord("");
		createWord.setTemplateName("templet_03.ftl");
		createWord.setFileName(fileName);

		String down_path = Global.getConfig("gpi.posrot.jjqd_templet");

		File file = new File(down_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		createWord.setFilePath(down_path);

		createWord.createWord();

		return fileName;

	}

	private void createWord() {

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
	public CreateJJQDWord(String templatePath) {
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

	public CreateJJQDWord() {
		super();
	}

}
