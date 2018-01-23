package com.sys.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.dao.UserDao;
import com.sys.entity.User;
import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.base.utils.comm.Global;
import com.base.utils.security.CipherUtil;

@Service("UserService")
public class UserServiceImp extends BaseServiceImp<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findFormatByLoginName(String loginName) {
		User a = null;
		try {
			a = userDao.findFormatByLoginName(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<User> existsUserId(User user) {
		return ((UserDao) baseDao).existsUserId(user);
	}

	@Override
	public int insertUser(User user) {
		int res = 0;
		List<User> userList = userDao.findAllList(user);
		String userid = user.getUserId();
		// 查询数据库是否已经存在用户名
		if (userList.size() > 0) {
			res = 1;
		} else {
			// String pwrs = CipherUtil.createRandomString(6);// 随机密码,以后发邮箱
			String pwrs = "123456";
			// o.setDescription(pwrs);//用户随机密码暂时保存在描述里
			String pwrsMD5 = CipherUtil.generatePassword(pwrs);// 第一次加密md5，
			// o.setSkin("skin-0");//默认皮肤
			String salt = CipherUtil.createSalt();
			user.setSalt(salt);
			// System.out.println(CipherUtil.createPwdEncrypt(userid, pwrsMD5,
			// salt));
			user.setPwd(CipherUtil.createPwdEncrypt(userid, pwrsMD5, salt));
			// o.setSalt(salt);
			// o.setCreateTime(new Date());
			((UserDao) baseDao).insert(user);
		}
		return res;
	}

	/*
	 * 查询指定角色的用户
	 */
	@Override
	public Page<User> findUserRolePage(User user, Page<User> page) {
		page.setList(((UserDao) baseDao).findUserRolePage(user, page));
		return page;
	}

	@Override
	public int resetPSD(User user) {
		int res = 0;
		String userid = user.getUserId();
		String pwrs = "123456";
		String pwrsMD5 = CipherUtil.generatePassword(pwrs);// 第一次加密md5，
		String salt = CipherUtil.createSalt();
		String pwd = CipherUtil.createPwdEncrypt(userid, pwrsMD5, salt);
		((UserDao) baseDao).resetPWD(userid, pwd, salt);
		return res;
	}

	/**
	 * 验证登录用户是否具有进行本系统的权限
	 * 
	 * @param @param userId
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean checkUserRole(String userId) {
		boolean res = false;
		// 如果系统没有公共角色，则查看该用户是否具有系统角色
		if (Global.getConfig("gpi.common.role").toUpperCase().equals("NULL")) {
			if (((UserDao) baseDao).countUserRole(userId, Global.getConfig("gpi.sys.code")) > 0) {
				res = true;
			}
		} else {// 如果系统具有公共角色，则充许用户进入
			res = true;
		}
		return res;
	}

	@Override
	public List<User> findUserInfo(User user) {
		return ((UserDao) baseDao).findUserInfo(user);
	}

}
