package com.sms.service.sent;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.sms.dao.SentDao;
import com.sms.entity.Sent;

@Service("SentService")
public class SentServiceImp extends BaseServiceImp<Sent> implements SentService {

	@Override
	public List<Sent> sentCount() {
		return ((SentDao) baseDao).sentCount();
	}

	@Override
	public List<Sent> app_msg_count() {
		return ((SentDao) baseDao).app_msg_count();
	}

}
