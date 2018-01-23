package com.bd.service.bd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.bd.dao.CertactDao;
import com.bd.entity.Certact;

@Service("CertactService")
public class CertactServiceImp extends BaseServiceImp<Certact> implements CertactService {

	
	@Autowired
	private CertactDao certactDao;
	
	@Override
	public List<Certact> exists(Certact certact) {
		return ((CertactDao)baseDao).exists(certact);
	}

	@Override
	public List<Certact> findCertactType(Certact certactType) {
		return ((CertactDao)baseDao).findCertactType(certactType);
	}


}
