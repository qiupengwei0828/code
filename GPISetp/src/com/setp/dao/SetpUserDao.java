package com.setp.dao;

import java.util.List;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.setp.entity.SetpUser;

@MyBatis
public interface SetpUserDao extends BaseDao<SetpUser> {

	public List<SetpUser> user_sum_setp_num(SetpUser setpUser);

}