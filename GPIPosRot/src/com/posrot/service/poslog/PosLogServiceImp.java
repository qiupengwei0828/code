package com.posrot.service.poslog;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.base.utils.DateUtils;
import com.base.utils.security.AccountShiroUtil;
import com.posrot.dao.PosLogDao;
import com.posrot.entity.PosLog;

@Service("PosLogService")
public class PosLogServiceImp extends BaseServiceImp<PosLog> implements PosLogService {

	// 1.轮岗计划制定
	@Override
	public void insert1(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		if ("1".equals(posLog.getActionCode())) {
			posLog.setOpeResult("已制定");
			posLog.setOpeContent("保存轮岗计划");// 操作内容
		} else if ("2".equals(posLog.getActionCode())) {
			posLog.setOpeResult("已制定");
			posLog.setOpeContent("轮岗计划制定");// 操作内容
		}
		posLog.setActionCode("1");
		posLog.setActionName("制定");// 1.制定计划;
		posLog.setOpeOpinion("");// 意见
		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

	// 2.轮岗计划提交
	@Override
	public void insert2(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		// posLog.setPlanId(rotation.getId());
		posLog.setActionCode("2");
		// 2.计划提交
		posLog.setActionName("提交");
		// posLog.setOpeOpinion(posLog.getOpeOpinion());// 操作意见
		posLog.setOpeContent("轮岗计划提交");// 操作内容
		posLog.setOpeResult("已提交");// 操作结果
		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

	// 3.轮岗计划审批
	@Override
	public void insert3(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		// posLog.setPlanId(rotation.getId());
		posLog.setActionName("审批");// 3.轮岗计划审批
		// posLog.setOpeOpinion(pLog.getOpeOpinion());// 操作意见
		posLog.setOpeContent("轮岗计划审批");// 操作内容
		if ("1".equals(posLog.getActionCode())) {
			posLog.setOpeResult("已通过");// 1.审批通过
		} else if ("0".equals(posLog.getActionCode())) {
			posLog.setOpeResult("不通过");// 2.审批不通过
		}
		posLog.setActionCode("3");

		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

	// 4.轮岗计划通知
	@Override
	public void insert4(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		// posLog.setPlanId(rotation.getId());
		posLog.setActionCode("4");
		posLog.setActionName("通知");// 4.轮岗计划通知
		posLog.setOpeContent("轮岗计划通知");// 操作内容
		posLog.setOpeResult("已通知");// 操作结果
		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

	// 5.轮岗计划接收
	@Override
	public void insert5(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		posLog.setOpeContent("轮岗计划接收");// 操作结果
		if ("1".equals(posLog.getActionCode())) {
			posLog.setOpeResult("已接收");
		} else if ("0".equals(posLog.getActionCode())) {
			posLog.setOpeResult("未接收");
		}

		posLog.setActionCode("5");
		posLog.setActionName("接收");// 5.轮岗计划接收

		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

	// 6.轮岗计划交接
	@Override
	public void insert6(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		// posLog.setPlanId(rotation.getId());
		posLog.setActionCode("6");
		posLog.setActionName("交接");// 4.轮岗计划通知
		posLog.setOpeContent("轮岗计划交接");// 操作内容
		posLog.setOpeResult("已交接");// 操作结果
		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

	// 7.轮岗计划执行
	@Override
	public void insert7(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		posLog.setActionName("执行");// 7.轮岗计划执行
		posLog.setOpeContent("轮岗计划执行");
		if (posLog.getOpeOpinion() == null || "".equals(posLog.getOpeOpinion())) {
			if ("1".equals(posLog.getActionCode())) {
				posLog.setOpeOpinion("计划已完成");
			} else if ("0".equals(posLog.getActionCode())) {
				posLog.setOpeOpinion("计划未完成");
			}
		}
		if ("1".equals(posLog.getActionCode())) {
			posLog.setOpeResult("已完成");
		} else if ("0".equals(posLog.getActionCode())) {
			posLog.setOpeResult("未完成");
		}
		posLog.setActionCode("7");
		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

	// 8.轮岗计划归档
	@Override
	public void insert8(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		// posLog.setPlanId(rotation.getId());
		posLog.setActionCode("8");
		posLog.setActionName("归档");
		// posLog.setOpeOpinion(pLog.getOpeOpinion());// 操作意见
		posLog.setOpeContent("轮岗计划归档");// 操作内容
		posLog.setOpeResult("已归档");// 操作结果
		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

	/*
	 * 督办 (non-Javadoc)
	 * 
	 * @see
	 * com.posrot.service.poslog.PosLogService#insert9(com.posrot.entity.PosLog)
	 */
	// 9.待接收督办
	@Override
	public void insert9(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		// posLog.setPlanId(rotation.getId());
		posLog.setActionCode("9");
		posLog.setActionName("督办");
		if (posLog.getOpeOpinion() != null) {
			posLog.setOpeOpinion(posLog.getOpeOpinion());// 操作意见
		} else {
			posLog.setOpeOpinion("请尽快接收计划！");
		}
		posLog.setOpeContent("计划接收督办");// 操作内容
		posLog.setOpeResult("接收已督办");// 操作结果
		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

	// 10.待交接督办
	@Override
	public void insert10(PosLog posLog) {
		posLog.setProCode("1");
		posLog.setProName("轮岗计划");
		// posLog.setPlanId(rotation.getId());
		posLog.setActionCode("10");
		posLog.setActionName("督办");
		if (posLog.getOpeOpinion() != null) {
			posLog.setOpeOpinion(posLog.getOpeOpinion());// 操作意见
		} else {
			posLog.setOpeOpinion("请尽快交接计划！");
		}
		posLog.setOpeContent("计划交接督办");// 操作内容
		posLog.setOpeResult("交接已督办");// 操作结果
		posLog.setOpeUserId(AccountShiroUtil.getCurrentUser().getUserId());
		posLog.setOpeUserName(AccountShiroUtil.getCurrentUser().getUserName());
		posLog.setOpeDate(DateUtils.getDate().toString());
		((PosLogDao) baseDao).eInsert(posLog);
	}

}
