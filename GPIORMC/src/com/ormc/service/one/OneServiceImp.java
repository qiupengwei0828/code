package com.ormc.service.one;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.ormc.dao.OneDao;
import com.ormc.entity.One;

@Service("OneService")
public class OneServiceImp extends BaseServiceImp<One> implements OneService {

	@Override
	public List<Map<String, Object>> queryAllData(Map<String, Object> map) {
		return ((OneDao) baseDao).queryAllData(map);
	}

}
