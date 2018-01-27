package com.posrot.service.staff;

import org.springframework.stereotype.Service;
import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.posrot.dao.StaffDao;
import com.posrot.entity.Staff;

@Service("StaffService")
public class StaffServiceImp extends BaseServiceImp<Staff> implements StaffService {

	@Override
	public Page<Staff> findExchangePos(Staff staff, Page<Staff> page) {
		page.setList(((StaffDao) baseDao).findExchangePos(staff, page));
		return page;
	}

	// @Override
	// public Page<Staff> findFurlough(Staff staff, Page<Staff> page) {
	// page.setList(((StaffDao) baseDao).findFurlough(staff, page));
	// return page;
	// }

}
