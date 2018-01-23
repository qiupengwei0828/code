package com.cert.service.cert;

import java.util.List;

import com.base.service.BaseService;
import com.cert.entity.Cert;

public interface CertService extends BaseService<Cert> {

	public List<Cert> existsCertCode(Cert role);

}
