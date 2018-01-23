package com.ormc.service.xtyrzz;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.ormc.dao.XtyrzzDao;
import com.ormc.entity.Xtyrzz;

@Service("XtyrzzService")
public class XtyrzzServiceImp extends BaseServiceImp<Xtyrzz> implements XtyrzzService {

	@Override
	public List<Map<String, Object>> queryAllData(Map<String, Object> map) {
		return ((XtyrzzDao) baseDao).queryAllData(map);
	}
}
