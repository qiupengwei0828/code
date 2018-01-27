package com.court.client;

import org.apache.log4j.Logger;

/**
 * 法院客户端
 * @author yangj
 *
 */
public class ClientSyy {
	private static final Logger logger = Logger.getLogger(ClientSyy.class);	
	
	private static boolean isRuning=false;
	
	private String getQueryInfo="getXzcxList";//调用该接口返回法院请求的司法查询信息。
    private String feedQueryInfo="shfeedXzcxInfo";//调用该接口将司法查询结果信息反馈请求单位。
    private String wsInfo = "wsInfo";//调用该接口获取请求单位各查询申请涉及的相关文书信息。
	
	 /**
     * 连接服务端接口，发起请求获取协查请求并将其保存至共享目录
     */
	  public void getQueryInfo()	{
		logger.info("获取协查请求,并保存至共享目录开始工作!");
		if (!isRuning) {
			isRuning=true;
			String queryContent = HttpChannel.getXzcxList(getQueryInfo);
			FileDeal.saveQueryContent(queryContent);
			isRuning=false;
		}
		
	  }
	  
	  /**
	   * 获取协查请求文件
	   */
	  public void getWsInfo(){
		  logger.info("获取协查文件，并保存至共享目录开始工作!");
		  if (!isRuning) {
			  isRuning=true;
			  HttpChannel.getWsInfo(wsInfo);
			  isRuning=false;
		 }
	  }
	
	  /**
	   * 扫描 共享目录中--反馈报文目录中待处理报文，处理完后，将其移动至 反馈报文--已处理 文件下
	   */
	  public void feedQueryInfo()
	  {      
		   logger.info("反馈报文开始工作,反馈完成后，将共享目录下待处理报文转移至已处理目录下!");
		   if (!isRuning) {
		   isRuning=true;
		   FileDeal.getBackcontent(feedQueryInfo);//获取到反馈报文，格式为xml字符串
		   isRuning=false;
		   }
	    	
	  }
	  /**
	   * 测试
	   * @param args
	   */
	  public static void main(String[] args) {
//		  new ClientSyy().getQueryInfo();
		  new ClientSyy().getWsInfo();
	}
}
