package com.posrot.service.res;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImp;
import com.posrot.dao.ResDao;
import com.posrot.entity.ResInfo;

@Service("ResService")
public class ResServiceImp extends BaseServiceImp<ResInfo> implements ResService {

	@Override
	public void export(ResInfo resInfo, HttpServletResponse response) {
		List<ResInfo> list = ((ResDao) baseDao).findAllList(null);
		ExportResService service = new ExportResService();
		try {
			service.export(list, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
