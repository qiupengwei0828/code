package com.sys.service.importData;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.sys.dao.ImportDataDao;
import com.sys.entity.AttachInfo;
import com.sys.entity.ImportConfWithBLOBs;

@Service("ImportDataService")
public class ImportDataServiceImp extends BaseServiceImp<Object> implements ImportDataService {

	@Override
	public Object getType(String valueString) {
		return ((ImportDataDao)baseDao).getType(valueString);
	}

	@Override
	public void insertInfo(AttachInfo attachInfo) {
		((ImportDataDao)baseDao).insertInfo(attachInfo);
		
	}

	@Override
	public List<ImportConfWithBLOBs> findList(ImportConfWithBLOBs importConf) {
		// TODO Auto-generated method stub
		return ((ImportDataDao)baseDao).findList(importConf);
	}

	@Override
	public List<ImportConfWithBLOBs> findAllListQuert(
			ImportConfWithBLOBs importConf1) {
		// TODO Auto-generated method stub
		return ((ImportDataDao)baseDao).findAllListQuert(importConf1);
	}

	@Override
	public List<Map> findAllListMap(String querySql) {
		// TODO Auto-generated method stub
		return ((ImportDataDao)baseDao).findAllListMap(querySql);
	}
	 	
}
