package com.sfxc.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.base.utils.comm.Global;
import com.sfxc.entity.CourtDealInfo;
import com.sfxc.entity.CourtQueryInfo;
import com.sfxc.entity.ProCheckWsinfo;
import com.sfxc.service.CourtDealInfoService;
import com.sfxc.service.CourtQueryInfoService;
import com.sfxc.service.ProCheckWsinfoService;
import com.sfxc.util.commom.ApplicationContextUtil;
import com.sfxc.util.commom.file.OperationFile;

public class ReadCourtTxt {	
		
	/**
	 * Spring定时器读取法院请求文件
	 * @author Panwf
	 * @date 2016年7月12日
	 * @since:
	 */
	@SuppressWarnings("static-access")
	public void readTxt() { 		
		Global bal = new Global();
		String path = bal.getConfig("court.query.file.path");
		String copy = bal.getConfig("court.query.file.bak.path");		
        try {  
            boolean validate = false;  
            SAXBuilder builder = new SAXBuilder(validate);            
            File file=new File(path);
            File[] tempList = file.listFiles();
            for (int i = 0; i < tempList.length; i++) {
             if (tempList[i].isFile()) {
            	 InputStream in = new FileInputStream(tempList[i]);
                 Document doc = builder.build(in);  
                 // 获取根节点 <university>  
                 Element root = doc.getRootElement();  
                 readNode(root, ""); 
                 OperationFile operationFile = new OperationFile();
                 operationFile.copyFile(""+tempList[i], copy+System.currentTimeMillis()+".txt");
                 operationFile.delFile(""+tempList[i]);
             }            
            }
             
        } catch (JDOMException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
	/**
	 * 读取txt文件存入库
	 * @author Panwf
	 * @date 2016年7月4日
	 * @since:
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public void readNode(Element root, String prefix) {  
		Global bal = new Global();
		String checkFile = bal.getConfig("court.query.wsinfo.path");
		CourtQueryInfoService courtQueryInfoService = (CourtQueryInfoService)ApplicationContextUtil.getBean("CourtQueryInfoService");
		CourtDealInfoService courtDealInfoService = (CourtDealInfoService)ApplicationContextUtil.getBean("CourtDealInfoService");
        if (root == null) return;  
        // 获取属性  
		List<Attribute> attrs = root.getAttributes();
        if (attrs != null && attrs.size() > 0) {
        	CourtQueryInfo courtQueryInfo = new CourtQueryInfo();
        	CourtDealInfo courtDealInfo = new CourtDealInfo();
            for (Attribute attr : attrs) {            	
            	if("BDHM".equals(attr.getName())){
            		courtQueryInfo.setQueryId(attr.getValue());
            		courtDealInfo.setBdhm(attr.getValue());
            	}
            	if("LB".equals(attr.getName())){
            		courtQueryInfo.setBankType(attr.getValue());
            	}
				if("XZ".equals(attr.getName())){
					courtQueryInfo.setQueryQu(attr.getValue());       		
				}
				if("XM".equals(attr.getName())){
					courtQueryInfo.setQueryName(attr.getValue());
				}
				if("GJ".equals(attr.getName())){
					courtQueryInfo.setNationArea(attr.getValue());
				}
				if("ZJLX".equals(attr.getName())){
					courtQueryInfo.setCertType(attr.getValue());
				}							
				if("DSRZJHM".equals(attr.getName())){
					courtQueryInfo.setCertNo(attr.getValue());
				}
				if("FZJG".equals(attr.getName())){	
					courtQueryInfo.setIssuctfAhrLo(attr.getValue());
				}	
				if("FYMC".equals(attr.getName())){	
					courtQueryInfo.setCourtName(attr.getValue());
				}
				
				if("GBR".equals(attr.getName())){
					courtQueryInfo.setJudgeName(attr.getValue());
				}
				if("AH".equals(attr.getName())){
					courtQueryInfo.setCaseId(attr.getValue());
				}				
				if("CKKSSJ".equals(attr.getName())){
					courtQueryInfo.setStartDt(attr.getValue());
				}
				if("CKJSSJ".equals(attr.getName())){
					courtQueryInfo.setEndDt(attr.getValue());
				}								
            }
            courtDealInfo.setDealstaus("0");
            List<CourtQueryInfo> list = courtQueryInfoService.find(courtQueryInfo);
            if(list.size()==0){
	            courtQueryInfoService.insert(courtQueryInfo);
	            courtDealInfoService.insert(courtDealInfo);	            	            
            	boolean validate = false;  
                SAXBuilder builder = new SAXBuilder(validate);            
                File f=new File(checkFile+"/"+courtQueryInfo.getQueryId()+"/"+courtQueryInfo.getQueryId()+".txt");
                WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();             
                ServletContext servletContext = webApplicationContext.getServletContext();
                String path = servletContext.getRealPath("..")+"/tmp";
                OperationFile oFile = new OperationFile();
                try {
					oFile.copyDirectiory(checkFile+"/"+courtQueryInfo.getQueryId(),path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                InputStream in;
				try {
					in = new FileInputStream(f);
					Document doc = builder.build(in);  
	                Element root1 = doc.getRootElement();  
	                readConfigFile(root1, "",courtQueryInfo.getQueryId(),checkFile);
				 } catch (JDOMException e) {  
			            e.printStackTrace();  
			     } catch (IOException e) {  
			            e.printStackTrace();  
			     }	                
             } 
        }  
        // 获取他的子节点  
        List<Element> childNodes = root.getChildren();  
        prefix += "\t";  
        for (Element e : childNodes) {  
            readNode(e, prefix);  
        }         
    } 
	/**
	 * 取文件的型号（共享文件中以txt形式存放），存放文件的路径到库
	 * @author Panwf
	 * @date 2016年7月4日
	 * @since:
	 */
	@SuppressWarnings({ "unchecked"})
	public void readConfigFile(Element root, String prefix,String queryId,String checkFile) {  
		ProCheckWsinfoService proCheckWsinfoService = (ProCheckWsinfoService)ApplicationContextUtil.getBean("ProCheckWsinfoService");
        if (root == null) return;  
        // 获取属性  
		List<Attribute> attrs = root.getAttributes();
        if (attrs != null && attrs.size() > 0) {
        	ProCheckWsinfo proCheckWsinfo = new ProCheckWsinfo();
            for (Attribute attr : attrs) {            	
            	if("XH".equals(attr.getName())){
            		proCheckWsinfo.setXh(attr.getValue());
            	}
            	if("WJMC".equals(attr.getName())){
            		proCheckWsinfo.setWjmc(attr.getValue());
            	}
            	if("WJLX".equals(attr.getName())){
            		proCheckWsinfo.setWjlx(attr.getValue());
            	}
            }
            proCheckWsinfo.setQueryId(queryId); 
            proCheckWsinfo.setFilepath(checkFile+queryId+"/"+proCheckWsinfo.getWjmc());
            proCheckWsinfoService.insert(proCheckWsinfo);
        }
        // 获取他的子节点  
        List<Element> childNodes = root.getChildren();  
        prefix += "\t";  
        for (Element e : childNodes) {  
        	readConfigFile(e, prefix,queryId,checkFile);  
        }          
	}
}