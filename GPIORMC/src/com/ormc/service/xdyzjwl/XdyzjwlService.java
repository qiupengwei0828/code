package com.ormc.service.xdyzjwl;

import java.util.List;
import java.util.Map;

import com.base.service.BaseService;
import com.ormc.entity.Xdyzjwl;

public interface XdyzjwlService extends BaseService<Xdyzjwl> {

	public List<Map<String, Object>> queryAllData(Map<String, Object> map);

}
