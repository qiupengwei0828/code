package com.sys.service.importData;

import java.util.List;
import java.util.Map;

import com.base.service.BaseService;
import com.sys.entity.AttachInfo;
import com.sys.entity.ImportConfWithBLOBs;

public interface ImportDataService extends BaseService<Object> {

	Object getType(String valueString);

	void insertInfo(AttachInfo attachInfo);

	List<ImportConfWithBLOBs> findList(ImportConfWithBLOBs importConf);

	List<ImportConfWithBLOBs> findAllListQuert(ImportConfWithBLOBs importConf1);

	List<Map> findAllListMap(String querySql);


}
