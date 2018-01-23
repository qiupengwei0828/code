package com.ormc.service.xdyzjwl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.ormc.dao.XdyzjwlDao;
import com.ormc.entity.Xdyzjwl;

@Service("XdyzjwlService")
public class XdyzjwlServiceImp extends BaseServiceImp<Xdyzjwl> implements XdyzjwlService {

	@Override
	public List<Map<String, Object>> queryAllData(Map<String, Object> map) {
		return ((XdyzjwlDao) baseDao).queryAllData(map);
	}

}
