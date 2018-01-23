package com.bd.service.voucher;

import com.base.page.Page;
import com.base.service.BaseService;
import com.bd.entity.BdVoucherDetail;
import com.sys.entity.Org;

public interface VoucherService extends BaseService<BdVoucherDetail> {

	Page<BdVoucherDetail> findByPage(BdVoucherDetail bdVoucherDetail, Org org,
			Page<BdVoucherDetail> page);

}
