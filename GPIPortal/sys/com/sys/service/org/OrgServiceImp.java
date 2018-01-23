package com.sys.service.org;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sys.dao.OrgDao;
import com.sys.entity.Org;
import com.base.service.BaseServiceImp;

@Service("OrgService")
public class OrgServiceImp extends BaseServiceImp<Org> implements OrgService {

	@Override
	public List<Org> findAllTree(Org org) {
		return ((OrgDao) baseDao).findAllTree(org);
	}

}
