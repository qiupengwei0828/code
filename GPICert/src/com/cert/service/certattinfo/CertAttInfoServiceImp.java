package com.cert.service.certattinfo;

import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImp;
import com.cert.dao.CertAttInfoDao;
import com.cert.entity.CertAttInfo;

@Service("CertAttInfoService")
public class CertAttInfoServiceImp extends BaseServiceImp<CertAttInfo> implements CertAttInfoService {

	public void updateTabInfo(CertAttInfo att) {
		((CertAttInfoDao) baseDao).updateTabInfo(att);
	}

	@Override
	public void deleteCertImg(CertAttInfo att) {
		((CertAttInfoDao) baseDao).deleteCertImg(att);
	}

	@Override
	public void updateCertImg(CertAttInfo att) {
		((CertAttInfoDao) baseDao).updateCertImg(att);

	}
}
