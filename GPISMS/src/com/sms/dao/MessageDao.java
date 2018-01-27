package com.sms.dao;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sms.entity.Message;

@MyBatis
public interface MessageDao extends BaseDao<Message> {

}
