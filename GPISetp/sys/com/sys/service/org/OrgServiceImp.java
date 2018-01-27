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

	/*
	 * 跟新机构维度
	 */
	@Override
	public void updateDim(Org org) {
		((OrgDao) baseDao).updateDim(org);
	}

	/*
	 *用于BD的机构树
	 * @see com.sys.service.org.OrgService#findBDAllList(com.sys.entity.Org)
	 */
	@Override
	public List<Org> findBDAllList(Org orgInfo) {
		return ((OrgDao) baseDao).findBDAllList(orgInfo);
	}

}
