package com.bd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.base.dao.MyBatis;
import com.base.page.Page;
import com.bd.entity.BdVoucherDetail;
import com.sys.entity.Org;

/**
 * 系统数据层
 */
@MyBatis
public interface VoucherDao extends BaseDao<BdVoucherDetail> {

	List<BdVoucherDetail> findByPage(@Param("param")BdVoucherDetail bdVoucherDetail,@Param("org")Org org,
			Page<BdVoucherDetail> page);
	

}
