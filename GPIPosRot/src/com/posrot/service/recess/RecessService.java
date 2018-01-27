package com.posrot.service.recess;

import javax.servlet.http.HttpServletResponse;

import com.base.page.Page;
import com.base.service.BaseService;
import com.posrot.entity.Recess;

public interface RecessService extends BaseService<Recess> {

	public int countStatusNum(Recess recess);

	public void updateStatus(Recess recess);

	public Page<Recess> findNotPass(Recess recess, Page<Recess> page);

	// 导出强修报表
	public void export(Recess recess, HttpServletResponse response);
}
