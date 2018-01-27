package com.sms.service.sent;

import java.util.List;

import com.base.service.BaseService;
import com.sms.entity.Sent;

public interface SentService extends BaseService<Sent> {

	public List<Sent> sentCount();

	public List<Sent> app_msg_count();

}
