package com.pro.client;

import java.util.ArrayList;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.log4j.Logger;

import com.pro.tools.core.util.Base64Util;
import com.pro.tools.core.util.PropertiesUtil;
import com.pro.tools.core.util.StringUtil;
import com.pro.tools.core.util.XmlStringReader;



/**
 * 跟服务端接口交互类
 * @author yangj
 *
 */
public class HttpChannel {
private static final Logger logger = Logger.getLogger(HttpChannel.class);	
	
	private static boolean isRuning=false;
	
	private static JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory
			.newInstance();     // 创建动态客户端
	private static String  wsdlUrl = PropertiesUtil.readValue("sfxc.properties", "pro.ckwWebService.url");
	private static Client client = factory.createClient(wsdlUrl); // 创建客户端连接
	private static String namespace="http://syyh.service.webService.ckw.tdh/";
	
    /**
     * 获取协查请求
     * @param method
     * @return
     */
	public static String getXzcxList(String method){
		  String queryContent="";
		  try{
			  QName qname = new QName(namespace, method);
			  Object[] result = client.invoke(qname, ParmComm.getCertInfo());
			  queryContent = StringUtil.clearRN(result[0].toString());//获取到的未解密并且未解析的原报文文件
			  logger.info("获取到的协查请求原报文内容为："+queryContent);
			  queryContent = Base64Util.getFromBase64(queryContent);
			  logger.info("一次解密后的协查请求为：" + queryContent);
			  queryContent = XmlStringReader.doXmlStringReader(queryContent);
			  logger.info("解析后的协查请求为：" + queryContent);
			  }catch(Exception e){
				  e.printStackTrace();
			  }
		
		return queryContent;
		
	}
	/**
	 * 获得协查文件
	 * @param method
	 */
	public static void getWsInfo(String method){
		//读取待处理文件夹中的待处理请求
		String queryContent = FileDeal.getQueryContent();
		//解析待处理请求，找到相应的查询单号，用于查询协查文件
		ArrayList<String> bdhmList = XmlStringReader.parseQueryContent(queryContent);
		String bdhm="";
		String bdhminfo="";
		String conditionStr=""; 
		 try{
			    QName qname = new QName(namespace, method);
			    Object[] result = null;
				for (int i = 0; i < bdhmList.size(); i++) {
					//输入参数
					bdhm=bdhmList.get(i);
					conditionStr="<condition BDHM=\""+Base64Util.encodeGbk(bdhm)+"\"></condition>";//属性值转成GBK编码并进行base64编码
					bdhminfo="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><bdhminfo>"+conditionStr+"</bdhminfo>";
					bdhminfo=Base64Util.encodeGbk(bdhminfo);//整个xml串base64编码
					result=client.invoke(qname, ParmComm.getCertInfo(),bdhminfo);
					
					//处理获得的协查文书--文书内容
					XmlStringReader.pareQueryFile(bdhm,result[0].toString());
					//将文书内容保存到共享目录协查请求--协查文件
				}
		 }catch(Exception e){
			  e.printStackTrace();
		  }
	}
	
    /**
     * 反馈查询内容
     * @param method
     * @param backContent
     */
	public static void dealReturnInfo(String method,String backContent){
		 //首先加密反馈内容 在XmlStringReader 里面先将xml格式字符串解析出来，然后属性值需转成GBK编码格式后进行一次base64编码，最后再针对整个XML内容进行base64编码。
		  String queryConent = XmlStringReader.parseBackXml(backContent);
		  QName qname = new QName(namespace, method);
		  try {
			Object[] result = client.invoke(qname, ParmComm.getCertInfo(),queryConent);
			//反馈结果后，根据服务端返回值判断是否反馈成功并处理
			String returnStr = XmlStringReader.getBackResult(result[0].toString());
			//将反馈结果保存至 共享目录下--返回消息--待处理报文
			FileDeal.saveMsg(returnStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//测试
	public static void main(String[] args) {
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
    	//反馈报文--只有具体账户信息和交易信息
    	String feedStr="<?xml version='1.0' encoding='UTF-8'?><backinfo><bdhm BDHM='YC2016062101'><zhxxList><zhxx BDHM='YC2016062101' ZHXH='1' KHZH='101091002192000' YE='0'"
    			+ " FKSJ='2016-02-24 11:18:44' ZHLB='一卡通卡片' ZHZT='正常' KHWD='安宁支行' BZ='156' TXDZ='' YZBM='' LXDH='18919905750' BEIZ='' SFTZ=''></zhxx></zhxxList>"
    			+ "<JYXXList><JYXX  BDHM='YC2016062101' KHZH='101091002192000' JYLSH='417591' JYLX='转出' JYFS='手机银行客户端' JYWD='飞天支行' JJBH='' JYSJ='2016-01-16 14:26:28' DFJE='' "
    			+ "JFJE='1000' BZ='156' SFFMC='张凯' SFFZH='6217853600001176774' SFFLX='银行卡号' SFFDW='跨行转账' YE='10.53' ZYXX='' FKSJ='2016-02-24 11:18:44' ></JYXX></JYXXList></bdhm></backinfo>";

    	String method="shfeedXzcxInfo";
    	dealReturnInfo(method,feedStr);
//    	getXzcxList("getXzcxList");//获取协查请求
	}
}
