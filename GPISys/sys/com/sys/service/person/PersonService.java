package com.sys.service.person;

import com.base.service.BaseService;
import com.sys.entity.User;

public interface PersonService extends BaseService<User> {

	
	public String modifyPwd(String pwd,String userId);


}
