package com.bd.service.bdstore;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.base.page.Page;
import com.base.service.BaseService;
import com.bd.entity.Store;
import com.sys.entity.Org;

public interface BdStoreService extends BaseService<Store> {
		
	public void insert(Store store);
	public void export(Store store,HttpServletResponse response, Org org);
	public Page<Store> findByPage(Store store, Org org, Page<Store> page);
	public List<Store> findNum(Store store, Org org);
	public void updateStroreNum(Store store);
}
