package com.cert.dao;

import java.util.List;
import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.cert.entity.Cert;

@MyBatis
public interface CertDao extends BaseDao<Cert> {
	public List<Cert> existsCertCode(Cert cert);

}
