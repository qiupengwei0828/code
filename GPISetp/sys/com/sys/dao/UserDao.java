package com.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.sys.entity.User;

/**
 * 系统数据层
 */
@MyBatis
public interface UserDao extends BaseDao<User> {
	/*
	 * 根据登录帐号查找loginName和accountType，正常只有一条数据 and a.isvalid='1' and
	 * a.account_type='1'需要该条件
	 */
	public User findFormatByLoginName(String loginName);

	public List<User> existsUserId(User app);

	public List<User> findExchangePos(@Param("param") User user, Page<User> page);

	// 获得对象列表
	public List<User> findFurlough(@Param("param") User user, Page<User> page);

	public List<User> findUserRolePage(@Param("param") User user, Page<User> page);

	public User resetPWD(@Param("userId") String userId, @Param("pwd") String pwd, @Param("salt") String salt);

	// 取得用户在当前系统有中角色数
	public int countUserRole(@Param("userId") String userId, @Param("appCode") String appCode);

	// 查询用户详细信息
	public List<User> queryUserInfo(User User);

	public List<User> userPage(@Param("param") User user, Page<User> page);

	// 查询所有密码为空的用户
	public List<User> queryPWDIsNull(User User);

	// 更新用户岗位信息
	public void update_pos_info(User User);
}
