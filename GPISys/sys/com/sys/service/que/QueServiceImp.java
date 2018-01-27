package com.sys.service.que;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.sys.dao.QueDao;
import com.sys.entity.Que;

@Service("QueService")
public class QueServiceImp extends BaseServiceImp<Que> implements QueService {

	@Override
	public void updateStatus(Que que) {
		((QueDao) baseDao).updateStatus(que);
	}

}
