package com.sys.controller.importData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.base.controller.BaseController;
import com.base.utils.comm.Global;
import com.base.utils.imdata.ImportController;
import com.sys.entity.ImportConfWithBLOBs;
import com.sys.service.importData.ImportDataService;

@Controller
@RequestMapping("/sys/importData")
public class ImportDataController extends BaseController<Object> {

	@Autowired
	private ImportDataService importDataService;

	@SuppressWarnings("resource")
	@RequestMapping("/down")
	public String down(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String down = Global.getConfig("download.template");
		String filename = java.net.URLDecoder.decode(request.getParameter("file"), "UTF-8");
		response.reset();
		response.setHeader("Content-Type", "applicationnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		String filePath = down + File.separator + filename;
		HSSFWorkbook wbs = null;
		OutputStream out = response.getOutputStream();
		try {
			// 创建工作簿
			wbs = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(filePath)));
			wbs.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping("/importUse")
	public String importUse() {
		return "/bd/use/importUse";
	}

	@RequestMapping("/importData")
	public String importData(MultipartFile myfiles, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
		if (myfiles.getSize() == 0) {
			return "false";
		}
		Object object = new Object();
		object = request.getParameter("id");
		String str = request.getParameter("modelAttribute");
		String valueString = request.getParameter("value");
		Object type = importDataService.getType(valueString);
		if (type.toString().contains(myfiles.getOriginalFilename().substring(myfiles.getOriginalFilename().indexOf(".")))) {
			Class cls = Class.forName(str);
			Object[] obj = importDataService.find(object).toArray();
			String[] tableCols = new String[obj.length];
			for (int i = 0; i < obj.length; i++) {
				tableCols[i] = obj[i].toString();
			}
			ImportController importController = new ImportController();
			String outPut = importController.readExcel(cls, myfiles, object.toString(), tableCols, valueString, importDataService);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(outPut);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("-1");
		}

		return null;

	}

	@RequestMapping("/imp")
	public String imp(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String str = request.getParameter("id");
		String site = request.getParameter("site");
		String entity = request.getParameter("modelAttribute");
		ImportController importController = new ImportController();
		Class cls = Class.forName(entity);
		Object[] obj = importDataService.find(str).toArray();
		String[] tableCols = new String[obj.length];
		for (int i = 0; i < obj.length; i++) {
			tableCols[i] = obj[i].toString();
		}
		;
		ImportConfWithBLOBs importConf = new ImportConfWithBLOBs();
		importConf.setAimTable(str);
		List<ImportConfWithBLOBs> listData = importDataService.findList(importConf);

		String dropString = "declare cou1 number; begin select count(*) into " + " cou1 from user_tables where table_name='" + listData.get(0).getTmpTable() + "'; if cou1<>0 then "
				+ " execute immediate 'drop table " + listData.get(0).getTmpTable() + " purge'; end if; end; ";
		importDataService.insert(dropString);
		importDataService.delete(listData.get(0).getMidTable());
		Object string = listData.get(0).getTmpCrtSql();
		importDataService.insert(string);
		ArrayList list = importController.excelIn(cls, site);
		List<Object> SqList = importController.constrctCellsSql(list, listData.get(0).getTmpTable(), tableCols);
		for (int i = 0; i < SqList.size(); i++) {
			Object ooObject = new Object();
			ooObject = SqList.get(i);
			importDataService.insert(ooObject);
		}
		importDataService.insert(listData.get(0).getCleanSql());
		ImportConfWithBLOBs importConf1 = new ImportConfWithBLOBs();
		importConf1.setQuerySql(listData.get(0).getQuerySql());
		List<Map> objects = importDataService.findAllListMap(listData.get(0).getQuerySql());
		ArrayList ColNs = new ArrayList();
		String ColNsName = "";
		JSONObject json = new JSONObject();
		for (Object key : objects.get(0).keySet()) {
			ColNs.add(key);
		}
		json.put("ColNs", ColNs);
		ColNsName += "[";
		int kk = 0;
		for (Object key : objects.get(0).keySet()) {
			if (kk != objects.get(0).size() - 1) {
				ColNsName += "{name:\"" + key + "\",width:40},";
			} else {
				ColNsName += "{name:\"" + key + "\",width:40}";
			}
			kk++;
		}
		ColNsName += "]";
		json.put("ColNsName", ColNsName);
		response.setContentType("text/text;charset=UTF-8");
		response.getWriter().print(json);
		return null;
	}

	@RequestMapping("/impData")
	public String impData(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String str = request.getParameter("id");
		ImportConfWithBLOBs importConf = new ImportConfWithBLOBs();
		importConf.setAimTable(str);
		List<ImportConfWithBLOBs> listData = importDataService.findList(importConf);
		List<Map> objects = importDataService.findAllListMap(listData.get(0).getQuerySql());

		ArrayList list1 = new ArrayList();
		JSONObject json = new JSONObject();
		String ColMs = "";
		for (int i = 0; i < objects.size(); i++) {
			Map map = objects.get(i);
			ArrayList list = new ArrayList();
			for (Object key : map.keySet()) {
				list.add(map.get(key));
			}
			list1.add(list);
		}
		json.put("rows", list1);
		response.setContentType("text/text;charset=UTF-8");
		response.getWriter().print(json);
		return null;
	}

	@RequestMapping("/impUse")
	public String impUse(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = request.getParameter("id");
		String flag = request.getParameter("flag");
		ImportConfWithBLOBs importConf = new ImportConfWithBLOBs();
		importConf.setAimTable(str);
		List<ImportConfWithBLOBs> listData = importDataService.findList(importConf);
		if (flag.equals("1")) {
			importDataService.insert(listData.get(0).getBakSql());
		}
		importDataService.insert(listData.get(0).getImpDataSql());
		response.setContentType("text/text;charset=UTF-8");
		response.getWriter().print("success");
		return null;
	}
}
