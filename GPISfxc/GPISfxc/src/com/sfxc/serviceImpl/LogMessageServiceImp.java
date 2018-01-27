package com.sfxc.serviceImpl;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.sfxc.entity.LogMessage;
import com.sfxc.service.LogMessageService;
@Service("LogMessageService")
public class LogMessageServiceImp extends BaseServiceImp<LogMessage> implements LogMessageService {
  
}