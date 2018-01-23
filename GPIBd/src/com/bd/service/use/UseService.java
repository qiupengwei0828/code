package com.bd.service.use;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.base.page.Page;
import com.base.service.BaseService;
import com.bd.entity.BdUseDetail;
import com.sys.entity.Org;

public interface UseService extends BaseService<BdUseDetail> {


	public String readExcel(MultipartFile myfiles) ;

	public Page<BdUseDetail> findByPage(BdUseDetail bdUseDetail, Org org,
			Page<BdUseDetail> page);

	public List<BdUseDetail> find(BdUseDetail bdUseDetail, String quarter,
			String year);

	public Page<BdUseDetail> findByPageReport(BdUseDetail bdUseDetail, Org org,
			Page<BdUseDetail> page);

	public void exportReport(BdUseDetail bdUseDetail, HttpServletResponse response, Org org);
	
}
