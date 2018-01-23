package com.sys.service.dep;

import java.util.List;

import com.base.service.BaseService;
import com.sys.entity.Dep;


public interface DepService   extends  BaseService<Dep>{
	public List<Dep> existsDepCode(Dep dep);
	
	
}
