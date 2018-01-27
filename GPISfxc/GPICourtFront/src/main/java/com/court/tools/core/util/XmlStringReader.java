package com.court.tools.core.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.court.client.FileDeal;
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
             	logger.info("查询单号："+ Base64Util.decodeStrGbk(element.getAttributeValue("BDHM"))+"\t类别："+Base64Util.decodeStrGbk(element.getAttributeValue("LB"))+"\t性质："
             	+Base64Util.decodeStrGbk(element.getAttributeValue("XZ"))+"\t被查询人姓名："+Base64Util.decodeStrGbk(element.getAttributeValue("XM"))+"\t国家或地区："+
             	Base64Util.decodeStrGbk(element.getAttributeValue("GJ"))+"\t证件类型："+Base64Util.decodeStrGbk(element.getAttributeValue("ZJLX"))+"\t证件号码："
             	+Base64Util.decodeStrGbk(element.getAttributeValue("DSRZJHM"))+"\t发证机关所在地："+Base64Util.decodeStrGbk(element.getAttributeValue("FZJG"))+"\t执行法院名称："
             	+Base64Util.decodeStrGbk(element.getAttributeValue("FYMC"))+"\t承办法官姓名："+Base64Util.decodeStrGbk(element.getAttributeValue("CBR"))+"\t执行案号："+
             	Base64Util.decodeStrGbk(element.getAttributeValue("AH"))+"\t往来账开始时间："+Base64Util.decodeStrGbk(element.getAttributeValue("CKKSSJ"))+"\t往来账结束时间："+
             	Base64Util.decodeStrGbk(element.getAttributeValue("CKJSSJ")));
             	cxqqStr="<cxqq BDHM=\""+ Base64Util.decodeStrGbk(element.getAttributeValue("BDHM"))+"\" LB=\""+Base64Util.decodeStrGbk(element.getAttributeValue("LB"))+"\" XZ=\""
             	+Base64Util.decodeStrGbk(element.getAttributeValue("XZ"))+"\" XM=\""+Base64Util.decodeStrGbk(element.getAttributeValue("XM"))+
             	"\" GJ=\"" +Base64Util.decodeStrGbk(element.getAttributeValue("GJ"))+"\" ZJLX=\""+Base64Util.decodeStrGbk(element.getAttributeValue("ZJLX"))+"\" DSRZJHM=\""+
             	Base64Util.decodeStrGbk(element.getAttributeValue("DSRZJHM"))+"\" FZJG=\""+Base64Util.decodeStrGbk(element.getAttributeValue("FZJG"))+
             	"\" FYMC=\""+Base64Util.decodeStrGbk(element.getAttributeValue("FYMC"))+"\" CBR=\""+Base64Util.decodeStrGbk(element.getAttributeValue("CBR"))+
             	"\" AH=\""+Base64Util.decodeStrGbk(element.getAttributeValue("AH"))+"\" CKKSSJ=\""+Base64Util.decodeStrGbk(element.getAttributeValue("CKKSSJ"))+
             	"\" CKJSSJ=\""+Base64Util.decodeStrGbk(element.getAttributeValue("CKJSSJ"))+"\"/>";
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
            			 +"\" WJLX=\""+Base64Util.decodeStrGbk(element.getAttributeValue("WJLX"))+"\" WSNR=\""+Base64Util.decodeStrGbk(element.getAttributeValue("WSNR"))+"\"></wsinfo>";
            	 //此处解析文书内容属性，并保存文书内容，在FileDeal 类中编写保存方法，此处调用。
            	 FileDeal.savaQueryFile(bdhm, Base64Util.decodeStrGbk(element.getAttributeValue("WJMC")), Base64Util.decode(Base64Util.decodeStrGbk(element.getAttributeValue("WSNR"))) );
             }
             wsinfoListStr+=wsinfoStr+"</wsinfoList>";
             str="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+wsinfoListStr;
//             logger.info("获取的协查文件报文为："+str);
             FileDeal.savaQueryFileXml(bdhm,str);
         }catch (Exception e) {  
             e.printStackTrace();  
         }
    }
    
    /**
     * 解析反馈报文并加密	
     * @param xmlStr
     * @return
     */
    public static String  parseFeedXml(String xmlStr){
    	 String str = "";
    	 StringReader read = new StringReader(xmlStr);  
         //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
         InputSource source = new InputSource(read);  
         //创建一个新的SAXBuilder  
         SAXBuilder saxbBuilder = new SAXBuilder();
         String feedinfoStr="<?xml version='1.0' encoding='UTF-8'?>";//返回信息头开始
         String zhxxStr="";
         String zhxxListStr="";
         String djxxListStr="";
         String djxxStr="";
         String wlxxListStr="";
         String wlxxStr="";
         String glxxListStr="";
         String glxxStr="";
         try {  
        	  //通过输入源构造一个Document  
             Document doc = saxbBuilder.build(source);  
             //取的根元素  
             Element root = doc.getRootElement();  //zhxxList根节点
             List<?> zhxxNodes = root.getChildren();  //zhxx 节点
             for (int i = 0; i < zhxxNodes.size(); i++) {
            	 //具体账户信息
				Element zhxxElement = (Element) zhxxNodes.get(i);
				logger.info("具体账户信息如下，查询单号："+zhxxElement.getAttributeValue("BDHM")+"  序号："+zhxxElement.getAttributeValue("CCXH")+"  开户账号："+zhxxElement.getAttributeValue("KHZH")
							+"  账户余额："+zhxxElement.getAttributeValue("YE")+"  反馈结果时间："+zhxxElement.getAttributeValue("FKSJ")+"  账户类别："+zhxxElement.getAttributeValue("CCLB")+"  账户状态："+
						    zhxxElement.getAttributeValue("ZHZT")+"  开户网点："+zhxxElement.getAttributeValue("KHWD")+"  币种："+zhxxElement.getAttributeValue("BZ")+"  通讯地址："+
							zhxxElement.getAttributeValue("TXDZ")+"  邮政编码："+zhxxElement.getAttributeValue("YZBM")+"  联系电话："+zhxxElement.getAttributeValue("LXDH")+"  备注："+
						    zhxxElement.getAttributeValue("BEIZ")+"  SFTZ："+zhxxElement.getAttributeValue("SFTZ"));
							
				zhxxStr+="<zhxx BDHM=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("BDHM"))+"\" CCXH=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("CCXH"))+"\" KHZH=\""+
							Base64Util.encodeGbk(zhxxElement.getAttributeValue("KHZH"))+"\" YE=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("YE"))+"\" FKSJ=\""+
							Base64Util.encodeGbk(zhxxElement.getAttributeValue("FKSJ"))+"\" CCLB=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("CCLB"))+"\" ZHZT=\""+
							Base64Util.encodeGbk(zhxxElement.getAttributeValue("ZHZT"))+"\" KHWD=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("KHWD"))+"\" BZ=\""+
							Base64Util.encodeGbk(zhxxElement.getAttributeValue("BZ"))+"\" TXDZ=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("TXDZ"))+"\" YZBM=\""+
							Base64Util.encodeGbk(zhxxElement.getAttributeValue("YZBM"))+"\" LXDH=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("LXDH"))+"\" BEIZ=\""+
							Base64Util.encodeGbk(zhxxElement.getAttributeValue("BEIZ"))+"\" SFTZ=\""+Base64Util.encodeGbk(zhxxElement.getAttributeValue("SFTZ"))+"\">";
				//法院冻结信息
				Element djxxListElement = zhxxElement.getChild("djxxList");
				if (null!=djxxListElement) {
					List<?>  djxxElements = djxxListElement.getChildren();
					for (int j = 0; j < djxxElements.size(); j++) {
						Element djxxElement = (Element) djxxElements.get(j);
						logger.info("冻结信息如下，控制措施序号："+djxxElement.getAttributeValue("CSXH")+"  SFBDJ："+djxxElement.getAttributeValue("SFBDJ")+"  冻结截止日期："+
								djxxElement.getAttributeValue("DJJZRQ")+"  冻结机关："+djxxElement.getAttributeValue("DJJG")+"  冻结文号："+djxxElement.getAttributeValue("DJWH")+
								"  冻结金额："+djxxElement.getAttributeValue("DJJE"));
						djxxStr+="<djxx CSXH=\""+Base64Util.encodeGbk(djxxElement.getAttributeValue("CSXH"))+"\" SFBDJ=\""+Base64Util.encodeGbk(djxxElement.getAttributeValue("SFBDJ"))+
								"\" DJJZRQ=\""+Base64Util.encodeGbk(djxxElement.getAttributeValue("DJJZRQ"))+"\" DJJG=\""+Base64Util.encodeGbk(djxxElement.getAttributeValue("DJJG"))+
								"\" DJWH=\""+Base64Util.encodeGbk(djxxElement.getAttributeValue("DJWH"))+"\" DJJE=\""+Base64Util.encodeGbk(djxxElement.getAttributeValue("DJJE"))+"\"></djxx>";
					}
					djxxListStr="<djxxList>"+djxxStr+"</djxxList>";
				}
				//资金往来信息
				Element wlxxListElement = zhxxElement.getChild("");
				if (null!=wlxxListElement) {
					List<?> wlxxElements = wlxxListElement.getChildren();
					for (int j = 0; j < wlxxElements.size(); j++) {
						Element wlxxElement = (Element) wlxxElements.get(j);
						logger.info("资金往来信息如下，资金往来序号："+wlxxElement.getAttributeValue("WLXH")+"  资金流向："+wlxxElement.getAttributeValue("ZJLX")+"  转出卡折号："+
						           wlxxElement.getAttributeValue("ZCKZH")+"  对方卡折姓名："+wlxxElement.getAttributeValue("ZCKZXM")+"  币种："+wlxxElement.getAttributeValue("BZ")+"  金额"+
						           wlxxElement.getAttributeValue("JE")+"  交易时间："+wlxxElement.getAttributeValue("JYSJ"));
						
						wlxxStr+="<wlxx WLXH=\""+Base64Util.encodeGbk(wlxxElement.getAttributeValue("WLXH"))+"\" ZJLX=\""+Base64Util.encodeGbk(wlxxElement.getAttributeValue("ZJLX"))+
								 "\" ZCKZH=\""+Base64Util.encodeGbk(wlxxElement.getAttributeValue("ZCKZH"))+"\" ZCKZXM=\""+Base64Util.encodeGbk(wlxxElement.getAttributeValue("ZCKZXM"))+
								 "\" BZ=\""+Base64Util.encodeGbk(wlxxElement.getAttributeValue("BZ"))+"\" JE=\""+Base64Util.encodeGbk(wlxxElement.getAttributeValue("JE"))+
								 "\" JYSJ=\""+Base64Util.encodeGbk(wlxxElement.getAttributeValue("JYSJ"))+"\"></wlxx>";
					}
					wlxxListStr="<wlxxList>"+wlxxStr+"</wlxxList>";
				}
				//关联账户信息
				Element glxxListElement = zhxxElement.getChild("glxxList");
				if (null!=glxxListElement) {
					List<?> glxxElements = glxxListElement.getChildren();
					for (int j = 0; j < glxxElements.size(); j++) {
						Element glxxElement = (Element) glxxElements.get(j);
						logger.info("关联账户信息如下，关联帐号序号："+glxxElement.getAttributeValue("GLXH")+"  关联账户类别："+glxxElement.getAttributeValue("GLZHLB")+"  关联帐号号码："+
						glxxElement.getAttributeValue("GLZHHM")+"  关联账户管理单位名称："+glxxElement.getAttributeValue("GLZHMC"));	
						
						glxxStr+="<glxx GLXH=\""+Base64Util.encodeGbk(glxxElement.getAttributeValue("GLXH"))+"\" GLZHLB=\""+Base64Util.encodeGbk(glxxElement.getAttributeValue("GLZHLB"))+
								Base64Util.encodeGbk(glxxElement.getAttributeValue("GLZHHM"))+"\" GLZHMC=\""+Base64Util.encodeGbk(glxxElement.getAttributeValue("GLZHMC"))+"\"></glxx>";
					}
					glxxListStr="<glxxList>"+glxxStr+"</glxxList>";
				}
				
				zhxxStr+=djxxListStr+wlxxListStr+glxxListStr+"</zhxx>";
			 }
             zhxxListStr="<zhxxList>"+zhxxStr+"</zhxxList>";
             feedinfoStr+=zhxxListStr;
             str=Base64Util.encodeGbk(feedinfoStr);
             logger.info("属性加密后的反馈报文为："+feedinfoStr+"  最终反馈报文为："+str);
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
         String feedStr="<?xml version='1.0' encoding='UTF-8' ?><zhxxList><zhxx BDHM='20111021320000100001' CCXH='1' KHZH='611234567891234567' YE='100.00' "
         		+ "FKSJ='2011/12/12 09:11:24' CCLB='借记卡' ZHZT='正常' KHWD='中国建设银行江苏省分行广州路支行营业部' BZ='156' TXDZ='南京市鼓楼区***' YZBM='210029' "
         		+ "LXDH='025-***' BEIZ='备注信息' SFTZ=''><djxxList><djxx CSXH='1' SFBDJ='1' DJJZRQ='2011/09/03' DJJG='南京市中级人民法院' DJWH='[2011]宁XXX号' DJJE='10000.00'>"
         		+ "</djxx><djxx CSXH='2' SFBDJ='1' DJJZRQ='2011/09/03' DJJG='南京市鼓楼区人民法院' DJWH='' DJJE='5000.00'></djxx></djxxList><wlxxList>"
         		+ "<wlxx WLXH='1' ZJLX='转出' ZCKZH='622121222222222222' ZCKZXM='李四' BZ='156' JE='100000.00' JYSJ='2011/09/03 09:11:24'></wlxx><wlxx "
         		+ "WLXH='2' ZJLX='取出' ZCKZH='' ZCKZXM='' BZ='156' JE='50000.00' JYSJ='2011/09/03 09:11:24'></wlxx></wlxxList><glxxList><glxx GLXH='1' GLZHLB='股票账户' "
         		+ "GLZHHM='622222222' GLZHMC='某某证券公司'></glxx><glxx GLXH='2' GLZHLB='基金账户' GLZHHM='3434334' GLZHMC='某某基金管理公司'></glxx></glxxList>"
         		+ "</zhxx><zhxx BDHM='20111021320000100001' CCXH='2' KHZH='611234567891234543' YE='100000.00' FKSJ='2011/12/12 09:11:24' CCLB='借记卡' ZHZT='正常' "
         		+ "KHWD='中国建设银行江苏省分行广州路支行营业部' BZ='156' TXDZ='南京市鼓楼区***' YZBM='210029' LXDH='025-***' BEIZ='备注信息' SFTZ=''>"
         		+ "</zhxx><zhxx BDHM='20111021320000100002' CCXH='1' KHZH='查无开户信息' YE='' FKSJ='2011/12/12 09:11:24' CCLB='' ZHZT='' KHWD='' BZ='' TXDZ='' YZBM='' LXDH=' ' "
         		+ "BEIZ='' SFTZ=''></zhxx></zhxxList>";
         parseFeedXml(feedStr);
    
    }
}