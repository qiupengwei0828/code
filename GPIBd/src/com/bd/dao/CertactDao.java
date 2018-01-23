package com.bd.dao;

import java.util.List;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.bd.entity.Certact;

/**
 * 系统数据层
 */
@MyBatis
public interface CertactDao extends BaseDao<Certact> {
	
	public  List<Certact> exists(Certact certact);

	public List<Certact> findCertactType(Certact certactType);
}
