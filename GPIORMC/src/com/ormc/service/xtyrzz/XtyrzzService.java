package com.ormc.service.xtyrzz;

import java.util.List;
import java.util.Map;

import com.base.service.BaseService;
import com.ormc.entity.Xtyrzz;

public interface XtyrzzService extends BaseService<Xtyrzz> {

	public List<Map<String, Object>> queryAllData(Map<String, Object> map);

}
