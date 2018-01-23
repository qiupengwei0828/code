package com.ormc.service.lxqk;

import java.util.List;
import java.util.Map;

import com.base.service.BaseService;
import com.ormc.entity.Lxqk;

public interface LxqkService extends BaseService<Lxqk> {

	public List<Map<String, Object>> queryAllData(Map<String, Object> map);

}
