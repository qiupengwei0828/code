package com.sys.service.dep;


import java.util.List;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;

import com.sys.entity.Dep;
import com.sys.dao.DepDao;;

@Service("DepService")
public class DepServiceImp  extends  BaseServiceImp<Dep>  implements DepService{

	@Override
	public List<Dep> existsDepCode(Dep dep) {
		return ((DepDao)baseDao).existsDepCode(dep);
	}

}
