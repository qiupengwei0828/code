package com.sys.service.user;

import java.util.List;
import com.sys.entity.User;
import com.base.page.Page;
import com.base.service.BaseService;

public interface UserService extends BaseService<User> {

	/**
	 * 根据登录帐号查找loginName和accountType，正常只有一条数据 and a.isvalid='1' and
	 * a.account_type='1'需要该条件
	 */
	public User findFormatByLoginName(String loginName);

	public List<User> existsUserId(User role);

	public int insertUser(User user);

	public int resetPSD(User user);

	// 获得指定角色的用户
	public Page<User> findUserRolePage(User user, Page<User> page);

	/**
	 * 验证登录用户是否具有进行本系统的权限
	 * 
	 * @param @param userId
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean checkUserRole(String userId);

	/** 查询用户详细信息 */
	public List<User> findUserInfo(User user);

}
