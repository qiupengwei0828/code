package com.pro.tools.core.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.pro.client.FileDeal;
/**
 * 解析检察院协查请求
 * @author yangj
 *
 */
public class XmlStringReader {
	private static final Logger logger = Logger.getLogger(XmlStringReader.class);	

/**
 * 解析并解密协查请求
 * @param xmlString
 * @return
 */
    public static String doXmlStringReader(String xmlString){
    	 String str="";
    	 String cxqqStr = "";
    	 String cxqqlistStr="";
    	 StringReader read = new StringReader(xmlString);  
         //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
         InputSource source = new InputSource(read);  
         //创建一个新的SAXBuilder  
         SAXBuilder saxbBuilder = new SAXBuilder();  
         try {  
             //通过输入源构造一个Document  
             Document doc = saxbBuilder.build(source);  
             //取的根元素  
             Element root = doc.getRootElement();  
             Attribute errMsgElement = root.getAttribute("ErrorMSG");
             if (null!=errMsgElement) {
            	 cxqqlistStr="<cxqqList ErrorMSG=\""+errMsgElement+"\">";
			 }else{
				 cxqqlistStr="<cxqqList>";
			 }
             List<?> node = root.getChildren();  
             for (int i = 0; i < node.size(); i++) {
             	Element element=(Element)node.get(i);
             	logger.info("查询单号："+ Base64Util.decodeStrGbk(element.getAttributeValue("BDHM"))+"\t类别："+Base64Util.decodeStrGbk(element.getAttributeValue("LB"))+"\t性质："+Base64Util.decodeStrGbk(element.getAttributeValue("XZ"))+
         				"\t被查询人："+Base64Util.decodeStrGbk(element.getAttributeValue("XM"))+"\t国家或地区："+Base64Util.decodeStrGbk(element.getAttributeValue("GJ"))+"\t证件类型："
             			+Base64Util.decodeStrGbk(element.getAttributeValue("ZJLX"))+"\t证件号码："+Base64Util.decodeStrGbk(element.getAttributeValue("DSRZJHM"))+"\t证件号码："+Base64Util.decodeStrGbk(element.getAttributeValue("ZJHM"))+
             			"\t发证机关所在地："+Base64Util.decodeStrGbk(element.getAttributeValue("FZJG"))+"\t执行检察院名称："+Base64Util.decodeStrGbk(element.getAttributeValue("JGHM"))+
             			"\t承办检察官："+Base64Util.decodeStrGbk(element.getAttributeValue("CBR"))+"\t执行案号："+Base64Util.decodeStrGbk(element.getAttributeValue("AH"))+
         				"\t交易开始时间："+Base64Util.decodeStrGbk(element.getAttributeValue("CKKSSJ"))+"\t交易结束时间："+Base64Util.decodeStrGbk(element.getAttributeValue("CKJSSJ"))+
         				"\t申请日期："+Base64Util.decodeStrGbk(element.getAttributeValue("SQSJ"))+"\t最晚反馈时间："+Base64Util.decodeStrGbk(element.getAttributeValue("ZWFKSJ")));
             	cxqqStr="<cxqq BDHM=\""+ Base64Util.decodeStrGbk(element.getAttributeValue("BDHM"))+"\" LB=\""+Base64Util.decodeStrGbk(element.getAttributeValue("LB"))+"\" XZ=\""+Base64Util.decodeStrGbk(element.getAttributeValue("XZ"))+
         				"\" XM=\""+Base64Util.decodeStrGbk(element.getAttributeValue("XM"))+"\" GJ=\"" +Base64Util.decodeStrGbk(element.getAttributeValue("GJ"))+"\" ZJLX=\""
             			+Base64Util.decodeStrGbk(element.getAttributeValue("ZJLX"))+"\" DSRZJHM=\""+Base64Util.decodeStrGbk(element.getAttributeValue("DSRZJHM"))+"\" ZJHM=\""+Base64Util.decodeStrGbk(element.getAttributeValue("ZJHM"))+
             			"\" FZJG=\""+Base64Util.decodeStrGbk(element.getAttributeValue("FZJG"))+"\" JGHM=\""+Base64Util.decodeStrGbk(element.getAttributeValue("JGHM"))+
             			"\" CBR=\""+Base64Util.decodeStrGbk(element.getAttributeValue("CBR"))+"\" AH=\""+Base64Util.decodeStrGbk(element.getAttributeValue("AH"))+
         				"\" CKKSSJ=\""+Base64Util.decodeStrGbk(element.getAttributeValue("CKKSSJ"))+"\" CKJSSJ=\""+Base64Util.decodeStrGbk(element.getAttributeValue("CKJSSJ"))+
         				"\" SQSJ=\""+Base64Util.decodeStrGbk(element.getAttributeValue("SQSJ"))+"\" ZWFKSJ=\""+Base64Util.decodeStrGbk(element.getAttributeValue("ZWFKSJ"))+"\"/>";
             }
             cxqqlistStr+=cxqqStr+"</cxqqList>";
             str="<?xml version='1.0' encoding='UTF-8'?>"+cxqqlistStr;
             logger.info("解密后的报文内容为："+str);
         } catch (Exception e) {  
             e.printStackTrace();  
         }
		return str;  
    }
    /**
     * 返回一个协查请求txt中的所有请求单号
     * @param content
     * @return
     */
    public static ArrayList<String>  parseQueryContent(String content){
    	 StringReader read = new StringReader(content);  
         //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
         InputSource source = new InputSource(read);  
         //创建一个新的SAXBuilder  
         SAXBuilder saxbBuilder = new SAXBuilder();  
         String bdhmStr="";
         ArrayList<String> strArray = new ArrayList<String> (); 
         try {  
        	 //通过输入源构造一个Document  
             Document doc = saxbBuilder.build(source);  
             //取的根元素  
             Element root = doc.getRootElement();  
             List<?> node = root.getChildren();  
             for (int i = 0; i < node.size(); i++) {
            	 Element element=(Element)node.get(i);
            	 bdhmStr= element.getAttributeValue("BDHM");
            	 strArray.add(bdhmStr);
             }
         }catch (Exception e) {  
             e.printStackTrace();  
         }
		return strArray;
    }
    /**
     * 解码解析协查文件报文
     * @param xmlStr
     * @return
     */
    public static void pareQueryFile(String bdhm,String xmlStr){
    	 xmlStr=Base64Util.decodeStrGbk(xmlStr);
    	 StringReader read = new StringReader(xmlStr);  
         //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
         InputSource source = new InputSource(read);  
         //创建一个新的SAXBuilder  
         SAXBuilder saxbBuilder = new SAXBuilder();
         String wsinfoListStr = ""; 
         String wsinfoStr=""; 
         String str="";
         try {  
        	 //通过输入源构造一个Document  
             Document doc = saxbBuilder.build(source);  
             //取的根元素  wsinfoList 
             Element root = doc.getRootElement(); 
             Attribute errMsgElement = root.getAttribute("ErrorMSG");
             if (null!=errMsgElement) {
            	 wsinfoListStr="<wsinfoList ErrorMSG=\""+errMsgElement+"\">";
		    	}else{
		    		wsinfoListStr="<wsinfoList>";
		    	}
             List<?> node = root.getChildren();  //wsinfo 节点集合
             for (int i = 0; i < node.size(); i++) {
            	 Element element=(Element)node.get(i);//wsinfo 具体节点
            	 logger.info("协查文件报文为，文书型号："+Base64Util.decodeStrGbk(element.getAttributeValue("XH"))+"  文书名称："+Base64Util.decodeStrGbk(element.getAttributeValue("WJMC"))+
            			 "  文书类型："+Base64Util.decodeStrGbk(element.getAttributeValue("WJLX"))+"  文书内容："+Base64Util.decodeStrGbk(element.getAttributeValue("WSNR")));
            	 wsinfoStr+="<wsinfo XH=\""+Base64Util.decodeStrGbk(element.getAttributeValue("XH"))+"\" WJMC=\""+Base64Util.decodeStrGbk(element.getAttributeValue("WJMC"))
            			 +"\" WJLX=\""+Base64Util.decodeStrGbk(element.getAttributeValue("WJLX"))+"\" WSNR=\""+"\"></wsinfo>";
            	 //此处解析文书内容属性，并保存文书内容，在FileDeal 类中编写保存方法，此处调用。
            	 FileDeal.savaQueryFile(bdhm,Base64Util.decodeStrGbk(element.getAttributeValue("WJMC")),Base64Util.decode(Base64Util.decodeStrGbk(element.getAttributeValue("WSNR"))) );
             }
             wsinfoListStr+=wsinfoStr+"</wsinfoList>";
             str="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+wsinfoListStr;
        	 FileDeal.savaQueryFileXml(bdhm, str);
//             logger.info("获取的协查文件报文为："+str);
         }catch (Exception e) {  
             e.printStackTrace();  
         }
    }
    
    /**
     * 解析反馈报文并加密	
     * @param xmlStr
     * @return
     */
    public static String  parseBackXml(String xmlStr){
    	 String str = "";
    	 StringReader read = new StringReader(xmlStr);  
         //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
         InputSource source = new InputSource(read);  
         //创建一个新的SAXBuilder  
         SAXBuilder saxbBuilder = new SAXBuilder();
         String backInfoStr="<?xml version='1.0' encoding='UTF-8'?><backinfo>";//返回信息头开始
         String bdhmStr="";//bdhm开始节点
         String zhxxListStr="";//账户信息开始
         String zhxxStr="";//账户信息
         String yyxxListStr="";//交易信息开始
         String jjyxStr="";//交易信息
         String bxgxxListStr="";//保险柜信息开始
         String bxgxxStr="";//保险柜信息
         String posxxListStr="";//pos机商户信息开始
         String posxxStr="";//pos机商户信息
         String wyrzxxListStr="";//网银登录日志
         String wyrzxxStr="";//网银日志
         String zdjjxxListStr="";//自动机具信息
         String zdjjxxStr="";//自动机具信息
         try {  
        	  //通过输入源构造一个Document  
             Document doc = saxbBuilder.build(source);  
             //取的根元素  
             Element root = doc.getRootElement();  //backinfo根节点
             List<?> bdhmNodes = root.getChildren();  //bdhm 节点
             for (int i = 0; i < bdhmNodes.size(); i++) {
              	Element bdhmNode=(Element)bdhmNodes.get(i);//bdhm节点
                logger.info("查询单号："+bdhmNode.getAttributeValue("BDHM"));
                bdhmStr+="<bdhm BDHM=\""+Base64Util.encodeGbk(bdhmNode.getAttributeValue("BDHM"))+"\">";
                //账户信息
                Element zhxxListElement = bdhmNode.getChild("zhxxList");//账户信息根节点
                if (null!=zhxxListElement) {
                	 List<?> zhxxElements = zhxxListElement.getChildren();//账户信息节点
                     for (int j = 0; j < zhxxElements.size(); j++) {
     					Element zhxxElement = (Element) zhxxElements.get(j);
     					logger.info("具体账户信息如下，查询单号："+zhxxElement.getAttributeValue("BDHM")+"  账户序号："+zhxxElement.getAttributeValue("ZHXH")+
     							"  开户账号："+zhxxElement.getAttributeValue("KHZH")+"  余额："+zhxxElement.getAttributeValue("YE")+"  反馈时间："+zhxxElement.getAttributeValue("FKSJ")
     							+"  账户类别："+zhxxElement.getAttributeValue("ZHLB")+"  账户状态："+zhxxElement.getAttributeValue("ZHZT")+"  网点："+zhxxElement.getAttributeValue("KHWD")+
     							"  币种："+zhxxElement.getAttributeValue("BZ")+"  通讯地址："+zhxxElement.getAttributeValue("TXDZ")+"  邮编："+zhxxElement.getAttributeValue("YZBM")+
     							"  联系电话："+zhxxElement.getAttributeValue("LXDH")+"  备注："+zhxxElement.getAttributeValue("BEIZ")+"  是否透支："+zhxxElement.getAttributeValue("SFTZ")+
     							"  开户日期："+zhxxElement.getAttributeValue("KHRQ")+"  销户日期："+zhxxElement.getAttributeValue("XHRQ"));
     					zhxxStr +="<zhxx BDHM=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("BDHM"))+"\" ZHXH=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("ZHXH"))+
     						   "\" KHZH=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("KHZH"))+"\" YE=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("YE"))+
     						   "\" FKSJ=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("FKSJ"))+"\" ZHLB=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("ZHLB"))+
     						   "\" ZHZT=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("ZHZT"))+"\" KHWD=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("KHWD"))+
     						   "\" BZ=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("BZ"))+"\" TXDZ=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("TXDZ"))+
     						   "\" YZBM=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("YZBM"))+"\" LXDH=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("LXDH"))+
     						   "\" BEIZ=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("BEIZ"))+"\" SFTZ=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("SFTZ"))+
     						   "\" KHRQ=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("KHRQ"))+"\" XHRQ=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("XHRQ"))+"\"></zhxx>";
     				}
                     zhxxListStr+="<zhxxList>"+zhxxStr+"</zhxxList>";
				}
               
                //交易信息
                Element jyxxListElement = bdhmNode.getChild("JYXXList");
                if (null!=jyxxListElement) {
					List<?> jyxxElements = jyxxListElement.getChildren();//交易信息节点
					for (int j = 0; j < jyxxElements.size(); j++) {
						Element jyxxElement = (Element) jyxxElements.get(j);
						logger.info("交易信息如下， 查询单号："+jyxxElement.getAttributeValue("BDHM")+"  开户账号："+jyxxElement.getAttributeValue("KHZH")+"  交易流水号："+jyxxElement.getAttributeValue("JYLSH")
								+"  交易类型："+jyxxElement.getAttributeValue("JYLX")+"  交易方式："+jyxxElement.getAttributeValue("JYFS")+"  交易网点："+jyxxElement.getAttributeValue("JYWD")+
								"  机具编号："+jyxxElement.getAttributeValue("JJBH")+"  交易时间："+jyxxElement.getAttributeValue("JYSJ")+"  贷方金额："+jyxxElement.getAttributeValue("DFJE")+
								"  借方金额："+jyxxElement.getAttributeValue("JFJE")+"  币种："+jyxxElement.getAttributeValue("BZ")+"  收付方名称："+jyxxElement.getAttributeValue("SFFMC")+
								"  收付方账号："+jyxxElement.getAttributeValue("SFFZH")+"  收付方类型："+jyxxElement.getAttributeValue("SFFLX")+"  收付方单位："+jyxxElement.getAttributeValue("SFFDW")+
								"  余额："+jyxxElement.getAttributeValue("YE")+"  摘要信息："+jyxxElement.getAttributeValue("ZYXX")+"  反馈时间："+jyxxElement.getAttributeValue("FKSJ"));
						jjyxStr+="<JYXX BDHM=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("BDHM"))+"\" KHZH=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("KHZH"))+"\" JYLSH=\""
								+Base64Util.encodeGbk(jyxxElement.getAttributeValue("JYLSH"))+"\" JYLX=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("JYLX"))+"\" JYFS=\""
								+Base64Util.encodeGbk(jyxxElement.getAttributeValue("JYFS"))+"\" JYWD=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("JYWD"))+"\" JJBH=\""
								+Base64Util.encodeGbk(jyxxElement.getAttributeValue("JJBH"))+"\" JYSJ=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("JYSJ"))+"\" DFJE=\""
								+Base64Util.encodeGbk(jyxxElement.getAttributeValue("DFJE"))+"\" JFJE=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("JFJE"))+"\" BZ=\""
								+Base64Util.encodeGbk(jyxxElement.getAttributeValue("BZ"))+"\" SFFMC=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("SFFMC"))+"\" SFFZH=\""
								+Base64Util.encodeGbk(jyxxElement.getAttributeValue("SFFZH"))+"\" SFFLX=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("SFFLX"))+"\" SFFDW=\""
								+Base64Util.encodeGbk(jyxxElement.getAttributeValue("SFFDW"))+"\" YE=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("YE"))+"\" ZYXX=\""
								+Base64Util.encodeGbk(jyxxElement.getAttributeValue("ZYXX"))+"\" FKSJ=\""+Base64Util.encodeGbk(jyxxElement.getAttributeValue("FKSJ"))+"\"></JYXX>";
					}
					yyxxListStr+="<JYXXList>"+jjyxStr+"</JYXXList>";
				}
                
                //保险柜信息
                Element bxgxxListElement = bdhmNode.getChild("BXGXXList");
                if (null!=bxgxxListElement) {
					List<?> bxgxxElements = bxgxxListElement.getChildren();
					for (int j = 0; j < bxgxxElements.size(); j++) {
						Element bxgxxElement = (Element) bxgxxElements.get(j);
						logger.info("保险柜信息如下，查询单号："+bxgxxElement.getAttributeValue("BDHM")+"  保险柜序号："+bxgxxElement.getAttributeValue("BXGXH")+"  保险柜号："+bxgxxElement.getAttributeValue("BXGH")
								+"  反馈时间："+bxgxxElement.getAttributeValue("FKSJ")+"  保险柜状态："+bxgxxElement.getAttributeValue("BXGZT")+"  所在网点："+bxgxxElement.getAttributeValue("SZWD")+
								"   租用开始时间："+bxgxxElement.getAttributeValue("ZYKSSJ")+"  租用结束时间："+bxgxxElement.getAttributeValue("ZYJSSJ")+"  备注信息："+bxgxxElement.getAttributeValue("BEIZ"));
						bxgxxStr+="<BXGXX BDHM=\""+Base64Util.encodeGbk(bxgxxElement.getAttributeValue("BDHM"))+"\" BXGXH=\""+Base64Util.encodeGbk(bxgxxElement.getAttributeValue("BXGXH"))
								+"\" BXGH=\""+Base64Util.encodeGbk(bxgxxElement.getAttributeValue("BXGH"))+"\" FKSJ=\""+Base64Util.encodeGbk(bxgxxElement.getAttributeValue("FKSJ"))+"\" BXGZT=\""+
								Base64Util.encodeGbk(bxgxxElement.getAttributeValue("BXGZT"))+"\" SZWD=\""+Base64Util.encodeGbk(bxgxxElement.getAttributeValue("SZWD"))+"\" ZYKSSJ=\""+
								Base64Util.encodeGbk(bxgxxElement.getAttributeValue("ZYKSSJ"))+"\" ZYJSSJ=\""+Base64Util.encodeGbk(bxgxxElement.getAttributeValue("ZYJSSJ"))+"\" BEIZ=\""+
								Base64Util.encodeGbk(bxgxxElement.getAttributeValue("BEIZ"))+"\"></BXGXX>";
					}
					bxgxxListStr+="<BXGXXList>"+bxgxxStr+"</BXGXXList>";
				}
                
                //POS机商户信息
                Element posxxListElement = bdhmNode.getChild("POSXXList");
                if (null!=posxxListElement) {
					List<?>  posxxElements = posxxListElement.getChildren();
					for (int j = 0; j < posxxElements.size(); j++) {
						Element posxxElement = (Element) posxxElements.get(j);
						logger.info("pos机商户信息如下，查询单号："+posxxElement.getAttributeValue("BDHM")+"  POS机序列号："+posxxElement.getAttributeValue("POSXH")+"  商户："+posxxElement.getAttributeValue("SH")
								+"  开户账号："+posxxElement.getAttributeValue("KHZH")+"  办理地址："+posxxElement.getAttributeValue("DLDZ")+"  通讯方式："+posxxElement.getAttributeValue("TXFS")+
								"   通讯商家："+posxxElement.getAttributeValue("TXSJ")+"  绑定号码："+posxxElement.getAttributeValue("HM")+"  反馈结果时间："+posxxElement.getAttributeValue("FKSJ"));
					
						posxxStr+="<POSXX BDHM=\""+Base64Util.encodeGbk(posxxElement.getAttributeValue("BDHM"))+"\" POSXH=\""+Base64Util.encodeGbk(posxxElement.getAttributeValue("POSXH"))
								+"\" SH=\""+Base64Util.encodeGbk(posxxElement.getAttributeValue("SH"))+"\" KHZH=\""+Base64Util.encodeGbk(posxxElement.getAttributeValue("KHZH"))+"\" DLDZ=\""+Base64Util.encodeGbk(posxxElement.getAttributeValue("DLDZ"))
								+"\" TXFS=\""+Base64Util.encodeGbk(posxxElement.getAttributeValue("TXFS"))+"\" TXSJ=\""+Base64Util.encodeGbk(posxxElement.getAttributeValue("TXSJ"))
								+"\" HM=\""+Base64Util.encodeGbk(posxxElement.getAttributeValue("HM"))+"\" FKSJ=\""+Base64Util.encodeGbk(posxxElement.getAttributeValue("FKSJ"))+"\"></POSXX>";
					}
					posxxListStr+="<POSXXList>"+posxxStr+"</POSXXList>";
				}
                
                //网银登录日志信息
                Element wyrzxxListElement = bdhmNode.getChild("WYRZXXList");
                if (null!=wyrzxxListElement) {
					List<?> wyrzxxElements = wyrzxxListElement.getChildren();
					for (int j = 0; j < wyrzxxElements.size(); j++) {
						Element wyrzxxElement = (Element) wyrzxxElements.get(j);
						logger.info("网银登录日志信息如下，查询单号："+wyrzxxElement.getAttributeValue("BDHM")+"  登录日志序号："+wyrzxxElement.getAttributeValue("RZXH")+"  登录时间："+
					              wyrzxxElement.getAttributeValue("DLSJ")+"  开户账号："+wyrzxxElement.getAttributeValue("KHZH")+"  登录IP："+wyrzxxElement.getAttributeValue("DLDZ")
					              +"  登录操作："+wyrzxxElement.getAttributeValue("DLCZ")+"  登录描述："+wyrzxxElement.getAttributeValue("DLMS")+"  反馈结果时间："+wyrzxxElement.getAttributeValue("FKSJ"));
					
						wyrzxxStr+="<WYRZXX BDHM=\""+Base64Util.encodeGbk(wyrzxxElement.getAttributeValue("BDHM"))+"\" RZXH=\""+Base64Util.encodeGbk(wyrzxxElement.getAttributeValue("RZXH"))+
								"\" DLSJ=\""+Base64Util.encodeGbk(wyrzxxElement.getAttributeValue("DLSJ"))+"\" KHZH=\""+Base64Util.encodeGbk(wyrzxxElement.getAttributeValue("KHZH"))+
								"\" DLDZ=\""+Base64Util.encodeGbk(wyrzxxElement.getAttributeValue("DLCZ"))+"\" KHZH=\""+Base64Util.encodeGbk(wyrzxxElement.getAttributeValue("DLCZ"))+
								"\" DLMS=\""+Base64Util.encodeGbk(wyrzxxElement.getAttributeValue("DLMS"))+"\" FKSJ=\""+Base64Util.encodeGbk(wyrzxxElement.getAttributeValue("FKSJ"))+"\"></WYRZXX>";
					}
					wyrzxxListStr+="<WYRZXXList>"+wyrzxxStr+"</WYRZXXList>";
				}
                
                //自动机具信息
                Element zdjjxxListElement = bdhmNode.getChild("ZDJJXXList");
                if (null!=zdjjxxListElement) {
					List<?> zdjjxxElements = zdjjxxListElement.getChildren();
					for (int j = 0; j < zdjjxxElements.size(); j++) {
						Element zdjjxxElement = (Element) zdjjxxElements.get(j);
						logger.info("自动机具信息如下，查询单号："+zdjjxxElement.getAttributeValue("BDHM")+"  开户账号："+zdjjxxElement.getAttributeValue("KHZH")+"  自动机具序号："+zdjjxxElement.getAttributeValue("JJXH")
								+"  机具地址："+zdjjxxElement.getAttributeValue("JJDZ")+"  经度："+zdjjxxElement.getAttributeValue("JD")+"  维度："+zdjjxxElement.getAttributeValue("WD")
								+"  网点号："+zdjjxxElement.getAttributeValue("WDH")+"  机构号："+zdjjxxElement.getAttributeValue("JGH")+"  柜机编号："+zdjjxxElement.getAttributeValue("JJBH")
								+"  网点名称："+zdjjxxElement.getAttributeValue("WDMC")+"  联系电话："+zdjjxxElement.getAttributeValue("LXDH")+"  机具类型："+zdjjxxElement.getAttributeValue("JJLX")
								+"  反馈结果时间："+zdjjxxElement.getAttributeValue("FKSJ"));
						zdjjxxStr+="<ZDJJXX BDHM=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("BDHM"))+"\" KHZH=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("KHZH"))+
								"\" JJXH=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("JJXH"))+"\" JJDZ=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("JJDZ"))+
								"\" JD=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("JD"))+"\" WD=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("WD"))+
								"\" WDH=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("WDH"))+"\" JGH=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("JGH"))+
								"\" JJBH=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("JJBH"))+"\" WDMC=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("WDMC"))+
								"\" LXDH=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("LXDH"))+"\" JJLX=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("JJLX"))+
								"\" FKSJ=\""+Base64Util.encodeGbk(zdjjxxElement.getAttributeValue("FKSJ"))+"\"></ZDJJXX>";
					}
					zdjjxxListStr+="<ZDJJXXList>"+zdjjxxStr+"</ZDJJXXList>";
				}
                
             }
             bdhmStr+=zhxxListStr+yyxxListStr+bxgxxListStr+posxxListStr+wyrzxxListStr+zdjjxxListStr+"</bdhm>";
             backInfoStr+=bdhmStr+"</backinfo>";
             str=Base64Util.encodeGbk(backInfoStr);
             logger.info("属性加密后的反馈报文为："+backInfoStr+"  最终反馈报文为："+str);
         }catch(Exception e){
        	 e.printStackTrace();
         }
		return str;
    	
    }
    /**
     * 反馈查询结果后，解析并解密服务端返回值
     * @param backStr
     * @return
     */
    public static String  getBackResult(String backStr){
    	 backStr=Base64Util.decodeStrGbk(backStr);
    	 String str="";
    	 String jbStr="";
    	 String cxjgListStr="";
    	 String resultStr="";
     	 StringReader read = new StringReader(backStr);  
         //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
         InputSource source = new InputSource(read);  
         //创建一个新的SAXBuilder  
         SAXBuilder saxbBuilder = new SAXBuilder();
         try {  
        	//通过输入源构造一个Document  
             Document doc = saxbBuilder.build(source);  
             //取的根元素  
             Element root = doc.getRootElement();  //result根节点
             Attribute errMsgElement = root.getAttribute("errMsg");
             if (null!=errMsgElement) {
            	 String errMsg = Base64Util.decodeStrGbk(root.getAttributeValue("errMsg"));
            	 resultStr="<result errMsg=\""+errMsg+"\">";
			}else{
				resultStr="<result>";
			}
             
             Element cxjglistElement = root.getChild("cxjglist");	
             List<?> jgElements = cxjglistElement.getChildren();  //jg 节点集合
             for (int i = 0; i < jgElements.size(); i++) {
				Element jgElement = (Element) jgElements.get(i);
				logger.info("查询单号："+Base64Util.decodeStrGbk(jgElement.getAttributeValue("bdhm"))+"  返回结果："+Base64Util.decodeStrGbk(jgElement.getAttributeValue("result"))+"  信息："+Base64Util.decodeStrGbk(jgElement.getAttributeValue("msg")));
				jbStr+="<jg bdhm=\""+Base64Util.decodeStrGbk(jgElement.getAttributeValue("bdhm"))+"\" result=\""+ Base64Util.decodeStrGbk(jgElement.getAttributeValue("result"))+"\" msg=\""+ Base64Util.decodeStrGbk(jgElement.getAttributeValue("msg"))+"\"></jg>";
             }
             cxjgListStr="<cxjglist>"+jbStr+"</cxjglist>";
             resultStr+=cxjgListStr+"</result>";
             str="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+resultStr;
             logger.info("反馈到服务端后，服务端的返回值为："+str);
         }catch(Exception e){
        	 e.printStackTrace();
         }
		return str;
    }
    /**
     * 加密反馈结果后的返回值
     * @param backStr
     * @return
     */
    public static String encodeBackResult(String backStr){
    	String start="";
    	String cxjglist="";
    	  String str="";
    	  String result="";
    	 StringReader read = new StringReader(backStr);  
         //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
         InputSource source = new InputSource(read);  
         //创建一个新的SAXBuilder  
         SAXBuilder saxbBuilder = new SAXBuilder();
         try {  
        	//通过输入源构造一个Document  
             Document doc = saxbBuilder.build(source);  
             //取的根元素  
             Element root = doc.getRootElement();  //result根节点
             Attribute errMsgElement = root.getAttribute("errMsg");
             if (null!=errMsgElement) {
            	 result="<result errMsg=\""+Base64Util.encodeGbk(root.getAttributeValue("errMsg"))+"\">";
    			}else{
    				result="<result>";
    			}
	             Element cxjglistElement = root.getChild("cxjglist");	
	             List<?> jgElements = cxjglistElement.getChildren();  //jg 节点集合
	             for (int i = 0; i < jgElements.size(); i++) {
	            	 Element jgElement = (Element) jgElements.get(i);
	            	 logger.info("查询单号："+Base64Util.encodeGbk(jgElement.getAttributeValue("bdhm"))+"  返回结果："+Base64Util.encodeGbk(jgElement.getAttributeValue("result"))+"  信息："+Base64Util.encodeGbk(jgElement.getAttributeValue("msg")));
	            	 str+="<jg bdhm=\""+Base64Util.encodeGbk(jgElement.getAttributeValue("bdhm"))+"\" result=\""+ Base64Util.encodeGbk(jgElement.getAttributeValue("result"))+"\" msg=\""+ Base64Util.encodeGbk(jgElement.getAttributeValue("msg"))+"\"></jg>";
			}
	             cxjglist="<cxjglist>"+str+"</cxjglist>";
                 result+=cxjglist+"</result>";
                 start="<?xml version='1.0' encoding='UTF-8' ?>"+result;
             start=Base64Util.encodeGbk(start);
             logger.info("返回值为："+start);
         }catch(Exception e){
        	 e.printStackTrace();
         }
		return start;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String xmlString="<?xml version='1.0' encoding='UTF-8'?><cxqqList><cxqq BDHM='Q1MyMDE2MDEwOTAwMQ==' LB='WUg=' XZ='MTsy' XM='zfXP/r78Q1M=' GJ='' ZJLX='MDE=' DSRZJHM='NjIwMTAxMjAxNjAxMDE1Njc4' "
        		+ "ZJHM='NjIwMTAxMjAxNjAxMDE1Njc4' FZJG='' JGHM='' CBR='1cW+r7nZ' AH='' CKKSSJ='MjAxNi0wMS0wMSAwMDowMDowMA==' CKJSSJ='MjAxNi0wMS0xOSAwMDowMDowMA==' "
        		+ "SQSJ='MjAxNi0wMS0xOSAwMDowMDowMA==' ZWFKSJ='' /></cxqqList>" ;
        //创建一个新的字符串  
         doXmlStringReader(xmlString);
    	//反馈报文一次解码后
    	String xmlStr = "<?xml version='1.0' encoding='UTF-8'?><backinfo><bdhm BDHM='Q1MyMDE2MDEwOTAwMQ=='><zhxxList><zhxx BDHM='Q1MyMDE2MDEwOTAwMQ==' "
    			+ "ZHXH='MQ==' KHZH='MTMxNDAxMDMwMTIyMjMxNDQ4Mg==' YE='NjAuMDM=' FKSJ='MjAxNi0wMi0xNCAwODo0Mzo1Mg==' ZHLB='uPbIy7vuxto=' ZHZT='1f2zow==' KHWD='wLzW3dL40NCzx7nYx/jR48TPwrfWp9DQ'"
    			+ " BZ='MTU2' TXDZ='wLzW3crQs8e52Mf4' YZBM='NzMwMDAw' LXDH='' BEIZ='sbjXog==' SFTZ=''></zhxx><zhxx BDHM='Q1MyMDE2MDEwOTAwMQ==' ZHXH='Mg==' KHZH='NjIyMjAyMTMxNDAwMjkwMzYxOQ==' "
    			+ "YE='OTkuMDI=' FKSJ='MjAxNi0wMi0xNCAwODo0Mzo1Mg==' ZHLB='uPbIy8Hpzai/qA==' ZHZT='1f2zow==' KHWD='wLzW3dL40NCzx7nYx/jR48TPwrfWp9DQ' BZ='MTU2' TXDZ='wLzW3crQs8e52Mf4' "
    			+ "YZBM='NzMwMDAw' LXDH='' BEIZ='sbjXog==' SFTZ=''></zhxx></zhxxList><JYXXList><JYXX BDHM='Q1MyMDE2MDEwOTAwMQ==' KHZH='MTMxNDAxMDMwMTIyMjMxNDQ4Mg==' JYLSH='MDA='"
    			+ " JYLX='16qz9g==' JYFS='ufHD5g==' JYWD='' JJBH='' JYSJ='MjAxNS0wOS0yMSAwMTo1MDoxNg==' DFJE='My4wMA==' JFJE='My4wMA==' BZ='MTU2' SFFMC='MA==' SFFZH='MA==' "
    			+ "SFFLX='MA==' SFFDW='MA==' YE='NDQuMDM=' ZYXX='0KG27rfRICAgICAgICAgICA=' FKSJ='MjAxNi0wMi0xNCAwODo0Mzo1Mg=='></JYXX><JYXX BDHM='Q1MyMDE2MDEwOTAwMQ==' "
    			+ "KHZH='MTMxNDAxMDMwMTIyMjMxNDQ4Mg==' JYLSH='MDE=' JYLX='16rI6w==' JYFS='ufHD5g==' JYWD='' JJBH='' JYSJ='MjAxNS0wOS0yMSAwMTo1MDoxNg==' DFJE='MC4wNA==' "
    			+ "JFJE='MC4wNA==' BZ='MTU2' SFFMC='MA==' SFFZH='MA==' SFFLX='MA==' SFFDW='MA==' YE='NDcuMDM=' ZYXX='wPvPoiAgICAgICAgICAgICAg' FKSJ='MjAxNi0wMi0xNCAwODo0Mzo1Mg=='>"
    			+ "</JYXX><JYXX BDHM='Q1MyMDE2MDEwOTAwMQ==' KHZH='NjIyMjAyMTMxNDAwMjkwMzYxOQ==' JYLSH='MDI=' JYLX='16qz9g==' JYFS='ufHD5g==' JYWD='' JJBH='' JYSJ='MjAxNS0wNi0yMSAwMjozMjozMw==' "
    			+ "DFJE='My4wMA==' JFJE='My4wMA==' BZ='MTU2' SFFMC='MA==' SFFZH='MA==' SFFLX='MA==' SFFDW='MA==' YE='NDYuOTk=' ZYXX='0KG27rfRICAgICAgICAgICA=' "
    			+ "FKSJ='MjAxNi0wMi0xNCAwODo0Mzo1Mg=='></JYXX><JYXX BDHM='Q1MyMDE2MDEwOTAwMQ==' KHZH='NjIyMjAyMTMxNDAwMjkwMzYxOQ==' JYLSH='MDM=' JYLX='16rI6w==' JYFS='ufHD5g==' JYWD='' "
    			+ "JJBH='' JYSJ='MjAxNS0wNi0yMSAwMjozMjozMw==' DFJE='MC4wNA==' JFJE='MC4wNA==' BZ='MTU2' SFFMC='MA==' SFFZH='MA==' SFFLX='MA==' SFFDW='MA==' YE='NDkuOTk=' "
    			+ "ZYXX='wPvPoiAgICAgICAgICAgICAg' FKSJ='MjAxNi0wMi0xNCAwODo0Mzo1Mg=='></JYXX></JYXXList></bdhm></backinfo>";
    	//反馈报文源码 所有信息都有
    	String xmlSoruce = "<?xml version='1.0' encoding='UTF-8'?><backinfo><bdhm BDHM='YC2016062101'><zhxxList><zhxx BDHM='YC2016062101' ZHXH='1' KHZH='1314010301222314482' YE='60.03' FKSJ='2016-02-14 08:43:52' ZHLB='个人活期' ZHZT='正常' "
    			+ "KHWD='兰州银行城关区雁南路支行' BZ='156' TXDZ='兰州市城关区' YZBM='730000' LXDH='' BEIZ='备注' SFTZ=''></zhxx><zhxx BDHM='YC2016062101' ZHXH='2' KHZH='6222021314002903619' "
    			+ "YE='99.02' FKSJ='2016-02-14 08:43:52' ZHLB='个人灵通卡' ZHZT='正常' KHWD='兰州银行城关区雁南路支行' BZ='156' TXDZ='兰州市城关区' YZBM='730000' LXDH='' BEIZ='备注' SFTZ=''>"
    			+ "</zhxx></zhxxList><JYXXList>  <JYXX BDHM='YC2016062101' KHZH='1314010301222314482' JYLSH='00' JYLX='转出' JYFS='柜面' JYWD='' JJBH='' JYSJ='2015-09-21 01:50:16' DFJE='3.00' "
    			+ "JFJE='3.00' BZ='156' SFFMC='0' SFFZH='0' SFFLX='0' SFFDW='0' YE='44.03' ZYXX='小额费' FKSJ='2016-02-14 08:43:52'></JYXX>  <JYXX BDHM='YC2016062101' KHZH='1314010301222314482' JYLSH='01' JYLX='转入' JYFS='柜面' JYWD='' "
    			+ "JJBH='' JYSJ='2015-09-21 01:50:16' DFJE='0.04' JFJE='0.04' BZ='156' SFFMC='0' SFFZH='0' SFFLX='0' SFFDW='0' YE='44.03' ZYXX='利息' FKSJ='2016-02-14 08:43:52'></JYXX>  <JYXX BDHM='YC2016062101' "
    			+ "KHZH='6222021314002903619' JYLSH='02' JYLX='转出' JYFS='柜面' JYWD='' JJBH='' JYSJ='2015-06-21 02:32:33' DFJE='3.00' JFJE='3.00' BZ='156' SFFMC='0' SFFZH='0' SFFLX='0'"
    			+ " SFFDW='0' YE='46.99' ZYXX='小额费' FKSJ='2016-02-14 08:43:52'></JYXX><JYXX BDHM='YC2016062101' KHZH='6222021314002903619' JYLSH='03' JYLX='转入' JYFS='柜面' JYWD='' "
    			+ "JJBH='' JYSJ='2015-06-21 02:32:33' DFJE='0.04' JFJE='0.04' BZ='156' SFFMC='0' SFFZH='0' SFFLX='0' SFFDW='0' YE='46.99' ZYXX='利息' FKSJ='2016-02-14 08:43:52'></JYXX></JYXXList>"
    			+ "<BXGXXList><BXGXX BDHM='YC2016062101' BXGXH='1' BXGH='360872' FKSJ='2011-09-03 21:30:00' BXGZT='正常' SZWD='兰州银行天水中路支行营业部' ZYKSSJ ='2010-10-03 8:30:00' ZYJSSJ ='2014-10-03 8:30:00' BEIZ='备注信息'></BXGXX></BXGXXList>"
    			+ "<POSXXList><POSXX BDHM='YC2016062101' POSXH='1' SH='张三' KHZH='611234567891234567' DLDZ='192.168.1.12' TXFS='手持' TXSJ ='移动' HM ='13578987568' FKSJ='2011-09-03 09:11:24'></POSXX></POSXXList>"
    			+ "<WYRZXXList><WYRZXX BDHM='YC2016062101' RZXH='1' DLSJ='2011-09-03 21:30:00' KHZH='611234567891234567' IP='192.168.1.23 ' DLCZ='转账汇款' DLMS ='' FKSJ='2011-09-03 09:11:24'></WYRZXX></WYRZXXList>"
    			+ "<ZDJJXXList><ZDJJXX BDHM='YC2016062101' KHZH='611234567891234567'  JJXH='1' JJDZ='兰州银行' JD='' WD='' WDH='XH0001' JGH='JG001' JJBH='BH001' WDMC ='兰州银行' LXDH='0551-65782567' JJLX='CRS'  FKSJ='2011-09-03 09:11:24'></ZDJJXX></ZDJJXXList>"
    			+ "</bdhm></backinfo>";
//    	parseBackXml(xmlSoruce);
    	
    	//有错误信息
    	String cxjgStr = "<?xml version='1.0' encoding='UTF-8' ?><result  errMsg='错误'>"
    			+ "<cxjglist><jg bdhm='YC2016062101' result='success' msg=''></jg><jg bdhm='YC2016062101' result='fail' msg='若成功则空，失败显示具体中文描述'></jg></cxjglist></result>";
   
    	 //无错误信息
    	String cxjgStrErr = "<?xml version='1.0' encoding='UTF-8' ?><result>"
    			+ "<cxjglist><jg bdhm='YC2016062101' result='success' msg=''></jg><jg bdhm='YC2016062101' result='fail' msg='若成功则空，失败显示具体中文描述'></jg></cxjglist></result>";
    	String cxjgRt = encodeBackResult(cxjgStrErr);
//    	getBackResult(cxjgRt);
    	
    	//解析解码后，保存在共享目录的协查请求
    	String jxhQuery="<?xml version='1.0' encoding='UTF-8'?><cxqqList><cxqq BDHM='YC2016062101' LB='YH' XZ='1;2;3' XM='杨志武' GJ='null' ZJLX='01' DSRZJHM='620421198804062035' ZJHM='620421198804062035' FZJG='null' JGHM='null' CBR='施德麟' AH='null' CKKSSJ='2016-06-01 00:00:00' CKJSSJ='2016-06-21 00:00:00' SQSJ='2016-06-21 00:00:00' ZWFKSJ='2016-06-25 00:00:00'/></cxqqList>";
    	parseQueryContent(jxhQuery);
    
    }
}