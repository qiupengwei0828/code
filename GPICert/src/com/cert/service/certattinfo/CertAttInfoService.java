package com.cert.service.certattinfo;

import com.base.service.BaseService;
import com.cert.entity.CertAttInfo;

public interface CertAttInfoService extends BaseService<CertAttInfo> {

	public void updateTabInfo(CertAttInfo att);

	public void deleteCertImg(CertAttInfo att);

	public void updateCertImg(CertAttInfo att);

}
