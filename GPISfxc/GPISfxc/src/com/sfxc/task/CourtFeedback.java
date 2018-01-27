package com.sfxc.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.base.utils.comm.Global;
import com.sfxc.entity.CourtWsinfo;
import com.sfxc.service.CourtWsinfoService;
import com.sfxc.util.commom.ApplicationContextUtil;
import com.sfxc.util.commom.file.OperationFile;

public class CourtFeedback {	
		
	/**
	 * Spring定时器读取法院返回文件
	 * @author Panwf
	 * @date 2016年7月12日
	 * @since:
	 */
	@SuppressWarnings("static-access")
	public void readTxt() { 		
		Global bal = new Global();
		String path = bal.getConfig("court.msg.file.path");
		String copy = bal.getConfig("court.msg.file.bak.path");		
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
	@SuppressWarnings({ "unchecked" })
	public void readNode(Element root, String prefix) {  
		CourtWsinfoService courtWsinfoService = (CourtWsinfoService)ApplicationContextUtil.getBean("CourtWsinfoService");
        if (root == null) return;  
        // 获取属性  
		List<Attribute> attrs = root.getAttributes();
        if (attrs != null && attrs.size() > 0) {
        	CourtWsinfo courtWsinfo = new CourtWsinfo();
            for (Attribute attr : attrs) {            	
            	if("bdhm".equals(attr.getName())){
            		courtWsinfo.setQueryId(attr.getValue());
            	}
            	if("result".equals(attr.getName())){
            		if("success".equals(attr.getName())){
            			courtWsinfo.setResultmsg("成功");
            		}else if("fail".equals(attr.getName())){
            			courtWsinfo.setResultmsg("失败");
            		}
            	}
				if("msg".equals(attr.getName())){
					courtWsinfo.setMsg(attr.getValue());       		
				}												
            }
            List<CourtWsinfo> list = courtWsinfoService.find(courtWsinfo);
            if(list.size()==0){
            	courtWsinfoService.insert(courtWsinfo);
            }
        }
	}
}
