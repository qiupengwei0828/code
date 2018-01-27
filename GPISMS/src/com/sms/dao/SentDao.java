package com.sms.dao;

import java.util.List;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sms.entity.Sent;

@MyBatis
public interface SentDao extends BaseDao<Sent> {

	public List<Sent> sentCount();

	public List<Sent> app_msg_count();

}
