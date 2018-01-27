package com.sms.service.message;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.sms.entity.Message;

@Service("MessageService")
public class MessageServiceImp extends BaseServiceImp<Message> implements MessageService {

}
