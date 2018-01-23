package com.cert.dao;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.cert.entity.CertAttInfo;

@MyBatis
public interface CertAttInfoDao extends BaseDao<CertAttInfo> {

	public void updateTabInfo(CertAttInfo att);

	public void deleteCertImg(CertAttInfo att);

	public void updateCertImg(CertAttInfo att);
}
