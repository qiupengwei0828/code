package com.sys.dao;

import java.util.List;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.Dic;

/**
 * 系统数据层
 * 
 * @param <T>
 */
@MyBatis
public interface DicDao extends BaseDao<Dic> {

	public List<Dic> findUnionCodeAndNameList(String dicType);

}