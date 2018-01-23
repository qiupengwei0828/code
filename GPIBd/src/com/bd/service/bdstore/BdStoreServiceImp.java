package com.bd.service.bdstore;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.base.utils.security.AccountShiroUtil;
import com.bd.dao.BdStoreDao;
import com.bd.dao.BdStoreLogDao;
import com.bd.entity.Store;
import com.bd.entity.StoreLog;
import com.sys.entity.Org;

@Service("BdStoreService")
public class BdStoreServiceImp extends BaseServiceImp<Store> implements BdStoreService {

	
	@Autowired
	private BdStoreDao storeDao;
	@Autowired
	private BdStoreLogDao storeLogDao;
	
	
	@Override
	public void insert(Store store) {
		storeDao.insert(store);
		List<Store> list = storeDao.find(store);
		if(list.size()>0){
			StoreLog storeLog = new StoreLog();
			storeLog.setOrgNo(store.getOrgNo());
			storeLog.setStoreId(store.getId());
			storeLog.setCertactCode(store.getCertactCode());
			storeLog.setUserId(AccountShiroUtil.getCurrentUser().getUserName());
			storeLog.setInNum(store.getStoreNum());
			storeLog.setCertactName(store.getCertactName());
			storeLog.setOrgName(store.getOrgName());
			storeLogDao.insert(storeLog);
		}
		 
	}


	@Override
	public void export(Store store,HttpServletResponse response,Org org) {
		List<Store> list = ((BdStoreDao)baseDao).export(store,org);
		Export export = new Export();
		try {
			export.export(list,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Page<Store> findByPage(Store store, Org org, Page<Store> page) {
		page.setList(((BdStoreDao)baseDao).findByPage(store,org, page));
		return page;
	}


	@Override
	public List<Store> findNum(Store store, Org org) {		
		return ((BdStoreDao)baseDao).findNum(store,org);
	}


	@Override
	public void updateStroreNum(Store store) {
		((BdStoreDao)baseDao).updateStroreNum(store);
		
	}
	
}
