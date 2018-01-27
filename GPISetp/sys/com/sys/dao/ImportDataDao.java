package com.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.sys.entity.AttachInfo;
import com.sys.entity.ImportConfWithBLOBs;
@MyBatis
public interface ImportDataDao  extends BaseDao<Object> {

	Object getType(String valueString);

	void insertInfo(@Param("attachInfo")AttachInfo attachInfo);

	List<ImportConfWithBLOBs> findList(@Param("importConf")ImportConfWithBLOBs importConf);

	List<ImportConfWithBLOBs> findAllListQuert(@Param("importConf")ImportConfWithBLOBs importConf1);

	List<Map> findAllListMap(String querySql);
	
	

}
