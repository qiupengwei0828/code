package com.posrot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.posrot.entity.Staff;

@MyBatis
public interface StaffDao extends BaseDao<Staff> {

	public List<Staff> findExchangePos(@Param("param") Staff staff, Page<Staff> page);

	// public List<Staff> findFurlough(@Param("param") Staff staff, Page<Staff>
	// page);

}
