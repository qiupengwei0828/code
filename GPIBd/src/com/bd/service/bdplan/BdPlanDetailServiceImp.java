package com.bd.service.bdplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.bd.dao.BdPlanDetailDao;
import com.bd.entity.BdPlanDetail;

@Service("BdPlanDetailService")
public class BdPlanDetailServiceImp extends BaseServiceImp<BdPlanDetail> implements BdPlanDetailService {

	@Autowired
	private BdPlanDetailDao bdPlanDetailDao;

}
