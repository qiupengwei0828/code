package com.bd.service.voucher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.bd.dao.VoucherDao;
import com.bd.entity.BdVoucherDetail;
import com.sys.entity.Org;

@Service("VoucherService")
public class VoucherServiceImp extends BaseServiceImp<BdVoucherDetail> implements VoucherService {

	
	@Autowired
	private VoucherDao voucherDao;

	@Override
	public Page<BdVoucherDetail> findByPage(BdVoucherDetail bdVoucherDetail,
			Org org, Page<BdVoucherDetail> page) {
		page.setList(((VoucherDao)baseDao).findByPage(bdVoucherDetail,org, page));
		return page;
	}


}
