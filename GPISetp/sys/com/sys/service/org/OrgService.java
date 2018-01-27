package com.sys.service.org;

import java.util.List;
import com.base.service.BaseService;
import com.sys.entity.Org;

public interface OrgService extends BaseService<Org> {

	public List<Org> findAllTree(Org org);

	public void updateDim(Org org);

	public List<Org> findBDAllList(Org orgInfo);

}
