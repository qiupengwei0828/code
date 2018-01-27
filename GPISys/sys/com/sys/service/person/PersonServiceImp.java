package com.sys.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.base.utils.security.CipherUtil;
import com.sys.dao.UserDao;
import com.sys.entity.User;

@Service("PersonService")
public class PersonServiceImp extends BaseServiceImp<User> implements PersonService {

	@Autowired
	private UserDao userDao;

	@Override
	public String modifyPwd(String password,String userId) {
		String pwrsMD5 = CipherUtil.generatePassword(password);// 第一次加密md5，
		String salt = CipherUtil.createSalt();
		String pwd = CipherUtil.createPwdEncrypt(userId, pwrsMD5, salt);
		((UserDao) baseDao).resetPWD(userId,pwd,salt);
		return "1";
	}
	
}
