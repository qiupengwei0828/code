package com.base.utils.imdata;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.base.utils.comm.Global;
import com.sys.dao.ImportDataDao;
import com.sys.entity.AttachInfo;
import com.sys.service.importData.ImportDataService;

/**
 * 
 * @author Administrator
 * 
 */
public class ImportController {

	
	@Autowired
	private ImportDataDao importDataDao;

	/**
	 * 
	 * @param file
	 * @return String
	 */

	public String readExcel(Class cls, MultipartFile file,String tableName,String[] tableCols,String code,ImportDataService importDataService) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filepathtemp = Global.getConfig("upload.file")+"/"+code+"/"+sdf.format(new Date());
		String tmpFileName = getUUID()+".xls";
		File filelist = new File(filepathtemp);
		if (!filelist.exists() && !filelist.isDirectory()) {
			filelist.mkdir();
		}
		String filePath = filepathtemp +filelist.separator+ tmpFileName;
		File tmpfile = new File(filePath);
		try {
			copy(file, filepathtemp, tmpFileName);
			AttachInfo attachInfo = new AttachInfo();
			if(tableName.contains("BD")){
			   attachInfo.setAppCode("GPIBd");
			}
			attachInfo.setCfgCode(Long.parseLong(code));
			attachInfo.setFileName(file.getOriginalFilename());
			attachInfo.setFileSaveName(tmpFileName);
			attachInfo.setFileSize(file.getSize());
			attachInfo.setFileType("excel");
			attachInfo.setTab(tableName);
			importDataService.insertInfo(attachInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 删除缓存文件
//		tmpfile.delete();
		return filepathtemp+filelist.separator+tmpFileName;

	}

	/**
	 * 通用导入
	 * 
	 * @param cls
	 * @param filepath
	 * @return
	 */
	public static ArrayList excelIn(Class<?> cls, String filepath) {
		ArrayList<Map> ary = new ArrayList<Map>();
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(filepath));
			Sheet sheet = workbook.getSheet(0);
			for (int i = 1; i < sheet.getRows(); i++) {
				Object obj = cls.newInstance();
				Field[] fields = cls.getDeclaredFields();
				Map map = new HashMap();
				int f = fields.length;
				for (int j = 3; j < fields.length; j++) {					
					Field field = fields[j];
					Cell cell = sheet.getCell(j-3, i);
					field.setAccessible(true);	
					if (field.getType().getName().equals("int")) {
						field.setInt(obj,
								Integer.parseInt((cell.getContents())));
					} else if (field.getType().getName().equals("double")) {
						field.setDouble(obj,
								Double.parseDouble(cell.getContents()));
					} else if (field.getType().getName().equals("java.math.BigDecimal")) {
						field.set(obj, new BigDecimal(Integer.parseInt(cell.getContents())));
					} else if (field.getType().getName().equals("java.lang.Long")) {
						String s = cell.getContents();
						Long aLong = Long.parseLong(s);
						field.set(obj,aLong);
				    }else {
						field.set(obj, String.valueOf(cell.getContents()));
					}
					map.put(field.getName(), cell.getContents());
					
				}
				ary.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
		return ary;
	}

	public List<Object> constrctCellsSql(ArrayList<Map> list, String tableName,
			String[] tableCols) {
        List<Object> sqlList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			
			StringBuffer bf = new StringBuffer("INSERT INTO " + tableName + "(");
			for(int j=0;j<tableCols.length;j++){
				if (j != tableCols.length - 1)
					bf.append(tableCols[j]).append(",");
				else
					bf.append(tableCols[j]).append("");
			}
		    bf.append(") VALUES ");
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(bf.toString() + "('" + getStrRandomId() + "',");
			int flag = 0;
			for(int k=0;k<tableCols.length;k++){	
			   for (Object key : list.get(i).keySet()) {
				    								
					String tmp = "";
					String keyString = key.toString().toLowerCase();
					String tadString = tableCols[k].replaceAll("_", "").toLowerCase();
					if(key.toString().toLowerCase().equals(tableCols[k].replaceAll("_", "").toLowerCase())){
						flag++;
						tmp = list.get(i).get(key).toString();
						int ss = list.get(i).size();
						if ( flag!= list.get(i).size())
							sqlBuffer.append("'").append(tmp).append("',");
						else
							sqlBuffer.append("'").append(tmp).append("'");
						break;
				   }
			    }
			}
			sqlBuffer.append(")");
			sqlList.add(sqlBuffer.toString());

		}
		return sqlList;
	}

	public static String getStrRandomId() {
		String string = String.valueOf(System.currentTimeMillis());
		String uuid = string.substring(string.length()-11, string.length());
		return uuid;

	}
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;

	}
	/**
	 * 文件操作 获取文件扩展名
	 * 
	 * @Author: sunny
	 * @param filename
	 *            文件名称包含扩展名
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 上传copy文件方法(for MultipartFile)
	 * 
	 * @param savePath
	 *            在linux上要保存完整路径
	 * @param newname
	 *            新的文件名称， 采用uuid做文件名防止中文报错的问题
	 * @throws Exception
	 */
	public static void copy(MultipartFile file, String savePath, String newname)
			throws Exception {
		try {
			File targetFile = new File(savePath, newname);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}

			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
