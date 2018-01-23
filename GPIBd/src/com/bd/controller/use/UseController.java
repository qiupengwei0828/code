package com.bd.controller.use;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.controller.BaseController;
import com.base.page.Page;
import com.base.utils.security.AccountShiroUtil;
import com.bd.entity.BdUseDetail;
import com.bd.entity.Store;
import com.bd.entity.StoreLog;
import com.bd.service.bdstore.BdStoreLogService;
import com.bd.service.bdstore.BdStoreService;
import com.bd.service.use.UseService;
import com.sys.entity.Org;

@Controller
@RequestMapping("/bd/use")
public class UseController extends BaseController<Object> {

	@Autowired
	public UseService useService;
	
	@Autowired
	public BdStoreLogService bdStoreLogService;
	
	@Autowired
	public BdStoreService bdStoreService;
	
	
	@RequestMapping("/list")
	public String list(BdUseDetail bdUseDetail,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<BdUseDetail> page = useService.findByPage(bdUseDetail, org,new Page<BdUseDetail >(request, response));
		model.addAttribute("page",page);
		model.addAttribute("bdUseDetail",bdUseDetail);
		return "/bd/use/list";
	}
	@RequestMapping("/listData")
	public String list(Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {		
		return "/bd/orgTree/uselist";
	}
	@RequestMapping("/uselistReport")
	public String listReport(Model model) throws UnsupportedEncodingException {		
		return "/bd/orgTree/uselistReport";
	}
	@RequestMapping("/listReport")
	public String listReport(BdUseDetail bdUseDetail,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
	    Page<BdUseDetail> page = useService.findByPageReport(bdUseDetail, org,new Page<BdUseDetail >(request, response));
		model.addAttribute("page",page);
		model.addAttribute("bdUseDetail",bdUseDetail);
		return "/bd/use/listReport";
	}
	@SuppressWarnings("resource")
	@RequestMapping("/down")
	public String down(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.reset();
		response.setHeader("Content-Type","applicationnd.ms-excel; charset=UTF-8");
		String filename = "单证使用情况导入表.xls";
		response.setHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode(filename, "UTF-8"));
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		ServletContext servletContext=request.getSession().getServletContext();
		String filePath = servletContext.getRealPath(File.separator+ "loadfiles" + File.separator +"单证使用情况导入表.xls");
		HSSFWorkbook wbs = null;
		OutputStream out = response.getOutputStream();
		try {
			// 创建工作簿
			wbs = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(filePath)));	
			wbs.write(out);
		}catch (Exception e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
		return null;
	}
	@RequestMapping("/importUse")
	public String importUse() {		
		return "/bd/use/importUse";
	}
	@RequestMapping("/importData")
	public String importData( MultipartFile myfiles,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException  {
		if (myfiles.getSize() == 0) {
			return "false";
		}
		String msg = useService.readExcel(myfiles);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(msg);
		return "redirect:/bd/use/list";
		
	}
	@RequestMapping("/form")
	public String form(BdUseDetail bdUseDetail,Model model) {	
		model.addAttribute("bdUseDetail",bdUseDetail);
		return "/bd/use/form";
	}
	@RequestMapping("/add")
	public String add(BdUseDetail bdUseDetail,Model model) {
		useService.insert(bdUseDetail);
		StoreLog storeLog = new StoreLog();		
		storeLog.setCertactName(bdUseDetail.getCertactName());
		storeLog.setOutNum(bdUseDetail.getUseNum().longValue());
		storeLog.setOrgName(bdUseDetail.getOrgName());
		storeLog.setUserId(bdUseDetail.getUserId());
		storeLog.setOrgNo(bdUseDetail.getOrgNo());
		storeLog.setCertactCode(bdUseDetail.getCertactCode());		
		Store store = new Store();		
		store.setOrgNo(bdUseDetail.getOrgNo());
		store.setCertactCode(bdUseDetail.getCertactCode());
		List<Store> list = bdStoreService.find(store);
		store.setId(list.get(0).getId());
		store.setStoreNum(list.get(0).getStoreNum()-bdUseDetail.getUseNum().longValue());
		bdStoreService.updateStroreNum(store);
		bdStoreLogService.insert(storeLog);
		return "redirect:/bd/use/list?orgNo="+bdUseDetail.getOrgNo();
	}
	@RequestMapping("/findNum")
	@ResponseBody
	public int findNum(BdUseDetail bdUseDetail,Model model) throws UnsupportedEncodingException {
		String uString = java.net.URLDecoder.decode(bdUseDetail.getUseDate(),"UTF-8");
		String year = uString.substring(4, uString.length()-1);
		String  quarter = "";
        if(uString.contains("一")){
        	quarter = "1";
        }else if(uString.contains("二")){
        	quarter = "2";
        }else if(uString.contains("三")){
        	quarter = "3";
        }else if(uString.contains("四")){
        	quarter = "4";
        }
        if("1".equals(quarter)){
        	quarter="4";
        	year =String.valueOf(Integer.parseInt(year)-1);
        }else{
        	quarter=String.valueOf(Integer.parseInt(quarter)-1);
        }
		List<BdUseDetail> list = useService.find(bdUseDetail,quarter,year);
		int data=0;
		if(list.get(0).getUseNum()!=null){
		   data = list.get(0).getUseNum().intValue();
		}
		return data;
	}
	@RequestMapping("/exportData")
	public String export(BdUseDetail bdUseDetail,Model model,HttpServletResponse response) {	
		Org org = new Org();
		org.setOrgDimField(AccountShiroUtil.getCurrentUser().getOrgInfo().getOrgDimField());
		org.setOwnDimOrgNo(AccountShiroUtil.getCurrentUser().getOrgInfo().getOwnDimOrgNo());
		useService.exportReport(bdUseDetail,response,org);
		return null;
	}

}
