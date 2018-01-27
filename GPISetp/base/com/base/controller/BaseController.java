package com.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.base.page.Page;
import com.base.utils.IPUtil;
import com.base.utils.comm.Global;
import com.base.utils.comm.UuidUtil;
import com.base.utils.security.AccountShiroUtil;
import com.base.utils.webpage.PageData;
import com.sys.dao.OpLogDao;
import com.sys.entity.OpLog;
import com.sys.utils.SpringContextHolder;

public class BaseController<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**日志常量 */
	protected static final String OP_CLASS_LOGIN="1";
	protected static final String OP_CLASS_OPER="2";
	
	protected static final String OP_TYPE_LOGOUT="0";
	protected static final String OP_TYPE_LOGIN="1";
	
	protected static final String OP_TYPE_ADD="2";
	protected static final String OP_TYPE_DEL="3";
	protected static final String OP_TYPE_UPDATE="4";
	protected static final String OP_TYPE_QUERY="5";	
	
	
    /**
     * 保存用户日志到数据库
     * @param @param opClass  1登录日志；2操作日志；
     * @param @param opType   1登录、2增加、3删除、4修改、5查询
     * @return void
     * @throws
     */
	protected void addLog(String opClass,String opType)
	{
		OpLog oLog= new OpLog();
		oLog.setAppCode(Global.getConfig("gpi.sys.code"));
		oLog.setUserId(AccountShiroUtil.getCurrentUser().getUserId());
		oLog.setOpIp(IPUtil.getIpAddr(getRequest()));
//		oLog.setServletPath(getRequest().getServletPath());
//		oLog.setOpObj(this.getClass().getName());
		oLog.setOpClass(opClass);
		oLog.setOpType(opType);
		oLog.setOpCnt(getRequest().getQueryString());
		try
		{
		  OpLogDao logDao = SpringContextHolder.getBean(OpLogDao.class);
		   logDao.insert(oLog);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 添加Model消息
	 * 
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("message", sb.toString());
	}

	/**
	 * 添加Flash消息
	 * 
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}

	/**
	 * 得到PageData
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 得到32位的uuid
	 * 
	 * @return
	 */
	public String get32UUID() {
		return UuidUtil.get32UUID();
	}

	/**
	 * 得到分页列表的信息
	 * 
	 * @param <T>
	 */
	public <T> Page<T> getPage() {
		return new Page<T>();
	}

	public static void logBefore(Logger logger, String interfaceName) {
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger) {
		logger.info("end");
		logger.info("");
	}

	/**
	 * 资源的权限（URl级别）
	 * 
	 * @param type
	 *            资源类别(优化速度)
	 * @return
	 */
	protected boolean doSecurityIntercept(String type) {
		// try {
		// String servletPath = getRequest().getServletPath();
		// servletPath = StringUtils.substringBeforeLast(servletPath,".");//
		// 去掉后面的后缀
		// String userId=AccountShiroUtil.getCurrentUser().getAccountId();
		// List<Resources>
		// authorized=resourcesService.resAuthorized(userId,type);
		// for(Resources r:authorized){
		// if(StringUtils.isNotBlank(r.getResUrl())){
		// if(StringUtils.equals(r.getResUrl(),servletPath)){
		// return true;
		// }
		// }
		//
		// }
		// } catch (Exception e) {
		// logger.error(e.toString(),e);
		// }
		return true;
	}

	/**
	 * 资源的权限（URl级别,拥有第一级资源权限，这资源才能访问）
	 * 
	 * @param type
	 *            资源类别(优化速度)
	 * @param url
	 *            第一级资源
	 * @return
	 */
	protected boolean doSecurityIntercept(String type, String url) {
		// try {
		// String userId=AccountShiroUtil.getCurrentUser().getAccountId();
		// List<Resources>
		// authorized=resourcesService.resAuthorized(userId,type);
		// for(Resources r:authorized){
		// if(StringUtils.isNotBlank(r.getResUrl())){
		// if(StringUtils.equals(r.getResUrl(),url)){
		// return true;
		// }
		// }
		// }
		// } catch (Exception e) {
		// logger.error(e.toString(),e);
		// }
		return true;
	}
}
