package com.ormc.service.lxqk;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.ormc.dao.LxqkDao;
import com.ormc.entity.Lxqk;

@Service("LxqkService")
public class LxqkServiceImp extends BaseServiceImp<Lxqk> implements LxqkService {

	@Override
	public List<Map<String, Object>> queryAllData(Map<String, Object> map) {
		return ((LxqkDao) baseDao).queryAllData(map);
	}

}
