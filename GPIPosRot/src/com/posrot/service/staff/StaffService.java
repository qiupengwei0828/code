package com.posrot.service.staff;

import com.base.page.Page;
import com.base.service.BaseService;
import com.posrot.entity.Staff;

/*
 * 员工信息
 */
public interface StaffService extends BaseService<Staff> {

	public Page<Staff> findExchangePos(Staff staff, Page<Staff> page);

	// public Page<Staff> findFurlough(Staff staff, Page<Staff> page);

}
