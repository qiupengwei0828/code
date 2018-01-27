package com.pro.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.log4j.Logger;

import com.pro.tools.core.util.FileOperate;
import com.pro.tools.core.util.PropertiesUtil;
import com.pro.tools.core.util.uuid.IdUtil;

/**
 * 处理报文
 * @author yangj
 *
 */
public class FileDeal {
	private static final Logger logger = Logger.getLogger(FileDeal.class);	
//    private static String  bakQueryContentPath=PropertiesUtil.readValue("sfxc.properties", "pro.query.file.bak.path");//协查请求报文已处理目录
//    private static String bakMsgFilePath=PropertiesUtil.readValue("sfxc.properties", "pro.msg.file.path");//返回消息--已处理报文
    
    /**
     * 获取协查请求并保存至共享目录
     * @param queryContent
     */
	public static void saveQueryContent(String queryContent){
		String  queryContentPath = PropertiesUtil.readValue("sfxc.properties", "pro.query.file.path");//协查请求报文存储目录
		String fileName=IdUtil.getId();
		queryContentPath+=fileName+".txt";
//		bakQueryContentPath=bakQueryContentPath+fileName+".txt";
		FileSystem fs = FileSystems.getDefault();
		//保存请求报文
		Path txtOut = fs.getPath(queryContentPath);
//		Path bakTxtOut = fs.getPath(bakQueryContentPath);
	    Path txttmp = txtOut.getParent();
//	    Path bakTxtTmp = bakTxtOut.getParent();
	    try{
		if (null!=txttmp) // null will be returned if the path has no parent
		    Files.createDirectories(txttmp);
		    Files.createFile(txtOut);
		    Files.write(txtOut, queryContent.getBytes());	
//		//备份
//		if (null!=bakTxtTmp) {
//			 Files.createDirectories(bakTxtTmp);
//			 Files.createFile(bakTxtOut);
//			 Files.write(bakTxtOut, queryContent.getBytes());	
//		}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * 读取协查请求待处理报文
	 * @return
	 */
	public static String getQueryContent(){
		 String  queryContentPath = PropertiesUtil.readValue("sfxc.properties", "pro.query.file.path");//协查请求报文存储目录
		 File path = new File(queryContentPath);
		  BufferedReader br= null;
	      StringBuffer sb = null;
	      if(path.exists()){
	    	  File[] t = path.listFiles();
	    	  for(int i=0;i<t.length;i++)
		    	{
	    			logger.info("读取协查请求待处理文件："+t[i].getName());
	    			try {
	    				 br=new BufferedReader(new FileReader(t[i]));
				         String temp=null;
				         sb=new StringBuffer();
				         temp=br.readLine();
				         while(temp!=null)
				         {
				             sb.append(temp);
				             temp=br.readLine();
				         }					         
				         br.close();	
				         
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    		  
		    	}
	      }
		return sb.toString();
	}
	
/**
 * 反馈
 * @param method
 * @return
 */
	public static void feedQueryInfo(String method){
		String rtnFilePath= PropertiesUtil.readValue("sfxc.properties", "pro.return.file.path");//反馈报文路径
		String bakRtnFilePath=PropertiesUtil.readValue("sfxc.properties", "pro.return.file.bak.path");//反馈报文已处理路径
    	File path = new File(rtnFilePath);
        BufferedReader br= null;
        StringBuffer sb = null;
    	if(path.exists())
    	{
    	    File[] t = path.listFiles();
	    	for(int i=0;i<t.length;i++)
	    	{
	    		logger.info("开始处理返回文件："+t[i].getName());
	    		try {
					 br=new BufferedReader(new FileReader(t[i]));
			         String temp=null;
			         sb=new StringBuffer();
			         temp=br.readLine();
			         while(temp!=null)
			         {
			             sb.append(temp);
			             temp=br.readLine();
			         }					         
			         br.close();				         
			         //处理文件  加密并反馈给服务端
			         HttpChannel.dealReturnInfo(method,sb.toString());
			         //将已处理的反馈文件 移动到 已处理目录
			         logger.info("反馈报文反馈后移动至已反馈目录处理：，移动："+rtnFilePath+t[i].getName()+" 到"+bakRtnFilePath);
			         FileOperate.moveFile(rtnFilePath+t[i].getName(), bakRtnFilePath+t[i].getName());				         
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	}else
    	{
    		logger.info("返回文件路径配置错误，请检查！"+path.getName());
    	}
	}
	/**
	 * 保存 反馈内容后，服务端返回的返回消息
	 * @param xmlMsg
	 */
	public static void  saveMsg(String xmlMsg){
		String msgFilePath=PropertiesUtil.readValue("sfxc.properties", "pro.msg.file.path");//返回消息--待处理报文
		String fileName=IdUtil.getId();
		msgFilePath+=fileName+".txt";
		FileSystem fs = FileSystems.getDefault();
		Path txtOut = fs.getPath(msgFilePath);
	    Path txttmp = txtOut.getParent();
	    try{
			if (null!=txttmp){// null will be returned if the path has no parent
			    Files.createDirectories(txttmp);
			    Files.createFile(txtOut);
			    Files.write(txtOut, xmlMsg.getBytes());	
			}else{
				logger.info("返回文件路径配置错误，请检查！"+msgFilePath);
			}
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
	}
	/**
	 * 保存协查文件
	 * @param bdhm
	 * @param wjmc
	 * @param wjlx
	 * @param wjnr
	 */
	public static void savaQueryFile(String bdhm,String wjmc,byte[] wjnr){
		String wsinfoPath=PropertiesUtil.readValue("sfxc.properties", "pro.query.wsinfo.path");//协查请求--协查文书
		wsinfoPath+=bdhm+"/"+wjmc;
		FileSystem fs = FileSystems.getDefault();
		Path txtOut = fs.getPath(wsinfoPath);
		Path txttmp = txtOut.getParent();
		try{
			if (null!=txttmp){// null will be returned if the path has no parent
				  Files.createDirectories(txttmp);
				  Files.createFile(txtOut);
				  Files.write(txtOut, wjnr);	
			}else{
				logger.info("返回文件路径配置错误，请检查！"+wsinfoPath);
			}
		}catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	/**
	 * 保存协查文件xml字符串
	 * @param bdhm
	 * @param wjmc
	 * @param wjlx
	 * @param wjnr
	 */
	public static void savaQueryFileXml(String bdhm,String str){
		String wsinfoPath=PropertiesUtil.readValue("sfxc.properties", "pro.query.wsinfo.path");//协查请求--协查文书
		wsinfoPath+=bdhm+"/"+bdhm+".txt";
		FileSystem fs = FileSystems.getDefault();
		Path txtOut = fs.getPath(wsinfoPath);
		Path txttmp = txtOut.getParent();
		try{
			if (null!=txttmp){// null will be returned if the path has no parent
				  Files.createDirectories(txttmp);
				  Files.createFile(txtOut);
				  Files.write(txtOut, str.getBytes());	
			}else{
				logger.info("返回文件路径配置错误，请检查！"+wsinfoPath);
			}
		}catch(Exception e){
	    	e.printStackTrace();
	    }
	}
}
