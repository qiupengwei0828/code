/**   
* @Title: soapui request xml
* @Package ccb 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2014-8-11 上午11:12:04 
* @version V1.0   
*/
package com.pro.client;

import com.pro.tools.core.util.Base64Util;
import com.pro.tools.core.util.PropertiesUtil;

/**
 * @author ccb-wind
 *
 */
public class ParmComm {

	 
     public static String soapHeader="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:syyh=\"http://syyh.service.webService.ckw.tdh/\">"+
                               "<soapenv:Header/>"+
                               "<soapenv:Body>";
     public static String soapEnd="</soapenv:Body>"+
    		                  "</soapenv:Envelope>";
     
     /**
      * 获取协查请求中的  输入参数
      * Usermarker：用户标识，包括用户名、密码。
	  *	如下xml串：
	  *	<?xml version="1.0" encoding="UTF-8"?>
	  *	<usermarker>
	  *	<condition username="约定用户名" password="约定密码"></condition>
	  *	</usermarker>
      * @return
      */
     public static String getCertInfo()
     {
    	 StringBuffer sbf = new StringBuffer();
    	 String userStr=Base64Util.encodeGbk(PropertiesUtil.readValue("sfxc.properties","pro.data.sys.user"));
	     String pwdStr=Base64Util.encodeGbk(PropertiesUtil.readValue("sfxc.properties","pro.data.sys.pwd"));	    	 
		 String m = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><usermarker><condition username=\""+userStr+"\" password=\""+pwdStr+"\" /></usermarker>";
		 m = Base64Util.encodeGbk(m);  
		 sbf.append(m);
		 return sbf.toString();
   	 
     }
     
     
     /**
      * soapui获取协查请求中的 request xml
      * @return
      */
     public static String getXzcxListStr()
     {
    	 StringBuffer sbf = new StringBuffer();
    	 sbf.append("<syyh:getXzcxList>");
    	 sbf.append("<arg0>");
	     sbf.append(getCertInfo());  	 
         sbf.append("</arg0>");
		 sbf.append("</syyh:getXzcxList>");     
		 return soapHeader+sbf.toString()+soapEnd;     
     }
  
     /**
      * 将账户查询结果信息反馈请求单位的方法 中的request xml 
      * @param base64Data
      * @return
      */
     public static String mkShfeedXzcxInfo(String base64Data)
     {
    	 StringBuffer sbf = new StringBuffer();
    	 sbf.append("<syyh:shfeedXzcxInfo>");
    	 sbf.append("<arg0>");
	     sbf.append(getCertInfo());  	 
         sbf.append("</arg0>");
    	 sbf.append("<arg1>");
	     sbf.append(base64Data);  	 
         sbf.append("</arg1>");         
		 sbf.append("</syyh:shfeedXzcxInfo>");     
		 return soapHeader+sbf.toString()+soapEnd;      	 
     }
     
     /**
      * 获取请求单位各查询申请涉及的相关文书信息  请求xml
      * @param base64Data
      * @return
      */
     public static String mkCheckWsInfo(String base64Data)
     {
    	 StringBuffer sbf = new StringBuffer();
    	 sbf.append("<syyh:wsInfo>");
    	 sbf.append("<arg0>");
	     sbf.append(getCertInfo());  	 
         sbf.append("</arg0>");
    	 sbf.append("<arg1>");
	     sbf.append(base64Data);  	 
         sbf.append("</arg1>");         
		 sbf.append("</syyh:wsInfo>");     
		 return soapHeader+sbf.toString()+soapEnd;  
     }	
	
}
