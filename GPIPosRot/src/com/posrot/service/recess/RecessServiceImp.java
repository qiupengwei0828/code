package com.posrot.service.recess;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.posrot.dao.RecessDao;
import com.posrot.entity.Recess;

@Service("RecessService")
public class RecessServiceImp extends BaseServiceImp<Recess> implements RecessService {

	// 统计各个状态下计划数量
	@Override
	public int countStatusNum(Recess recess) {
		return ((RecessDao) baseDao).countStatusNum(recess);
	}

	// 修改计划状态
	@Override
	public void updateStatus(Recess recess) {
		((RecessDao) baseDao).updateStatus(recess);
	}

	@Override
	public Page<Recess> findNotPass(Recess recess, Page<Recess> page) {
		page.setList(((RecessDao) baseDao).findNotPass(recess, page));
		return page;
	}

	@Override
	public void export(Recess recess, HttpServletResponse response) {
		List<Recess> list = ((RecessDao) baseDao).findAllList(null);
		ExportRecessService service = new ExportRecessService();
		try {
			service.export(list, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
