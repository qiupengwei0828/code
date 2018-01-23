package com.ormc.service.one;

import java.util.List;
import java.util.Map;

import com.base.service.BaseService;
import com.ormc.entity.One;

public interface OneService extends BaseService<One> {

	public List<Map<String, Object>> queryAllData(Map<String, Object> map);

}
