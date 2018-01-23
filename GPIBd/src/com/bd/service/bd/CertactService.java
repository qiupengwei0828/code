package com.bd.service.bd;

import java.util.List;

import com.base.service.BaseService;
import com.bd.entity.Certact;

public interface CertactService extends BaseService<Certact> {


	List<Certact> exists(Certact certact);

	List<Certact> findCertactType(Certact certactType);
}
