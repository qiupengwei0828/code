package com.posrot.service.res;

import javax.servlet.http.HttpServletResponse;

import com.base.service.BaseService;
import com.posrot.entity.ResInfo;

/*
 * 人员履历
 */
public interface ResService extends BaseService<ResInfo> {

	public void export(ResInfo resInfo, HttpServletResponse response);

}
