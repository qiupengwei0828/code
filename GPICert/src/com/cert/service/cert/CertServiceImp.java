package com.cert.service.cert;


import java.util.List;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.cert.dao.CertDao;
import com.cert.entity.Cert;

@Service("CertService")
public class CertServiceImp  extends  BaseServiceImp<Cert>   implements CertService{
	

	@Override
	public List<Cert> existsCertCode(Cert cert) {
		return ((CertDao) baseDao).existsCertCode(cert);
	}
	
}
