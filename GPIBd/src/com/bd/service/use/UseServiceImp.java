package com.bd.service.use;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.base.page.Page;
import com.base.service.BaseServiceImp;
import com.bd.dao.BdStoreDao;
import com.bd.dao.BdStoreLogDao;
import com.bd.dao.UseDao;
import com.bd.entity.BdUseDetail;
import com.bd.entity.Store;
import com.bd.entity.StoreLog;
import com.bd.service.bdstore.Export;
import com.sys.entity.Org;

@Service("UseService")
public class UseServiceImp extends BaseServiceImp<BdUseDetail> implements UseService {

	
	@Autowired
	private UseDao useDao;
	@Autowired
	private BdStoreDao bdStoreDao;
	@Autowired
	private BdStoreLogDao bdStoreLogDao;

	 @SuppressWarnings("unused")
	public String readExcel(MultipartFile file){  
		 String fileName = file.getOriginalFilename();
	     List<String> valueList=new ArrayList<String>();  
	     String filepathtemp="/mnt/b2b/tmp";//缓存文件目录  
	     String tmpFileName= System.currentTimeMillis()+"."+getExtensionName(fileName);  
	     String ExtensionName=getExtensionName(fileName);  
	     File filelist = new File(filepathtemp);  
	     if  (!filelist .exists()  && !filelist .isDirectory())        
	        {         
	            filelist .mkdir();      
	        }   
	        String filePath = filepathtemp+System.getProperty("file.separator")+tmpFileName;  
	        File tmpfile = new File(filePath);  
	        try {
				copy(file, filepathtemp,tmpFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}	          
	        if(ExtensionName.equalsIgnoreCase("xls")){  
	            try {
					valueList=readExcel2003(filePath);
				} catch (IOException e) {
					e.printStackTrace();
				}  
	        }else if(ExtensionName.equalsIgnoreCase("xlsx")) {  
	            try {
					valueList=readExcel2007(filePath);
				} catch (IOException e) {
					e.printStackTrace();
				}  
	        } 
	        //删除缓存文件  
	        tmpfile.delete();  
	        return "success";  
	                  
	    }  
	      
	    /** 
	     * 读取97-2003格式 
	     * @param filePath 文件路径 
	     * @throws java.io.IOException 
	     */  
	    @SuppressWarnings({ "resource", "unused" })
		public List<String> readExcel2003(String filePath) throws IOException{  
	        //返回结果集  
	        List<String> valueList=new ArrayList<String>();  
	        FileInputStream fis=null;  
	        try {  
	            fis=new FileInputStream(filePath);  
	            HSSFWorkbook wookbook = new HSSFWorkbook(fis);
	            HSSFSheet sheet = wookbook.getSheetAt(0);  
	            int rows = sheet.getPhysicalNumberOfRows();   
	            int cells=0;  
	            HSSFRow firstRow = sheet.getRow(0);  
	            if (firstRow != null) {  
	                cells = firstRow.getPhysicalNumberOfCells(); 
	            } 
	            for (int i = 2; i < rows; i++) {  
	                HSSFRow row = sheet.getRow(i);  
	                if (row != null) {
	                	BdUseDetail bdUseDetail = new BdUseDetail();
	                        try { 
	                        	bdUseDetail.setOrgNo(getCellValue(row.getCell(0)));
	                        	bdUseDetail.setOrgName(getCellValue(row.getCell(1)));
	                        	bdUseDetail.setCertactCode(getCellValue(row.getCell(2))); 
	                        	bdUseDetail.setCertactName(getCellValue(row.getCell(3)));
	                        	bdUseDetail.setUseType(getCellValue(row.getCell(4)));
	                        	bdUseDetail.setUseNum(new BigDecimal(getCellValue(row.getCell(5))));
	                        	bdUseDetail.setUseDate(getCellValue(row.getCell(6)));
	                        	bdUseDetail.setUserId(getCellValue(row.getCell(7)));
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
	                    		List<Store> list = bdStoreDao.find(store);
	                    		store.setCertactCode(list.get(0).getCertactCode());
	                    		store.setCertactName(list.get(0).getCertactName());
	                    		store.setCertactStore(list.get(0).getCertactStore());
	                    		store.setCertactType(list.get(0).getCertactType());
	                    		store.setCertactSplit(list.get(0).getCertactSplit());
	                    		store.setBusinessScope(list.get(0).getBusinessScope());
	                    		store.setOrgName(list.get(0).getOrgName());
	                    		store.setOrgNo(list.get(0).getOrgNo());
	                    		store.setRemark(list.get(0).getRemark());
	                    		store.setRepType(list.get(0).getRepType());
	                    		store.setStatus(list.get(0).getStatus());
	                    		store.setId(list.get(0).getId());
	                    		store.setStoreNum(list.get(0).getStoreNum()-bdUseDetail.getUseNum().longValue());
	                    		bdStoreDao.update(store);
	                    		bdStoreLogDao.insert(storeLog);
	                        } catch (Exception e) {  
	                            e.printStackTrace();          
	                        } 
	                        ((UseDao)baseDao).insert(bdUseDetail);
	                    } 	                
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }finally {  
	            fis.close();  
	        }  
	        return valueList;  
	    }  
	    /** 
	     * 读取2007以上版本
	     * @param filePath 文件路径 
	     * @return 
	     * @throws java.io.IOException 
	     */  
	    @SuppressWarnings("resource")
		public  List<String> readExcel2007(String filePath) throws IOException{  
	        List<String> valueList=new ArrayList<String>();  
	        FileInputStream fis =null;  
	        try {  
	            fis =new FileInputStream(filePath);  
				XSSFWorkbook xwb = new XSSFWorkbook(fis);
	            XSSFSheet sheet = xwb.getSheetAt(0);  
	            XSSFRow row;  
	            row = sheet.getRow(0);   
	            for (int i = sheet.getFirstRowNum() + 2; i <= sheet.getPhysicalNumberOfRows(); i++) {  
	                row = sheet.getRow(i);  
	                if (row != null) { 
	                	BdUseDetail bdUseDetail = new BdUseDetail();
                    	bdUseDetail.setOrgNo(row.getCell(0).toString());
                    	bdUseDetail.setOrgName(row.getCell(1).toString());
                    	bdUseDetail.setCertactCode(row.getCell(2).toString().trim()); 
                    	bdUseDetail.setCertactName(row.getCell(3).toString().trim());
                    	bdUseDetail.setUseType(row.getCell(4).toString().trim().substring(0,1));
                    	bdUseDetail.setUseNum(new BigDecimal(row.getCell(5).toString().trim()));
                    	bdUseDetail.setUseDate(row.getCell(6).toString().trim());
                    	bdUseDetail.setUserId(row.getCell(7).toString().trim());  
                    	((UseDao)baseDao).insert(bdUseDetail); 

                    	
                    	StoreLog storeLog = new StoreLog();		
                		storeLog.setCertactName(bdUseDetail.getCertactName());
                		storeLog.setOutNum(bdUseDetail.getUseNum().longValue());
                		storeLog.setOrgName(bdUseDetail.getOrgNo());
                		storeLog.setUserId(bdUseDetail.getUserId());
                		storeLog.setOrgNo(bdUseDetail.getOrgNo());
                		storeLog.setCertactCode(bdUseDetail.getCertactCode());		
                		Store store = new Store();
                		store.setOrgNo(bdUseDetail.getOrgNo());
                		store.setCertactCode(bdUseDetail.getCertactCode());
                		List<Store> list = bdStoreDao.find(store);
                		store.setCertactCode(list.get(0).getCertactCode());
                		store.setCertactName(list.get(0).getCertactName());
                		store.setCertactStore(list.get(0).getCertactStore());
                		store.setCertactType(list.get(0).getCertactType());
                		store.setCertactSplit(list.get(0).getCertactSplit());
                		store.setBusinessScope(list.get(0).getBusinessScope());
                		store.setOrgName(list.get(0).getOrgName());
                		store.setOrgNo(list.get(0).getOrgNo());
                		store.setRemark(list.get(0).getRemark());
                		store.setRepType(list.get(0).getRepType());
                		store.setStatus(list.get(0).getStatus());
                		store.setId(list.get(0).getId());		
                		store.setStoreNum(list.get(0).getStoreNum()-bdUseDetail.getUseNum().longValue());
                		bdStoreDao.update(store);
                		bdStoreLogDao.insert(storeLog);
	                }  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }finally {  
	            fis.close();  
	        }  
	  
	        return valueList;  
	    }  
	      
	    /** 
	     * 文件操作 获取文件扩展名 
	     *  
	     * @Author: sunny 
	     * @param filename 
	     *            文件名称包含扩展名 
	     * @return 
	     */  
	    public static String getExtensionName(String filename) {  
	        if ((filename != null) && (filename.length() > 0)) {  
	            int dot = filename.lastIndexOf('.');  
	            if ((dot > -1) && (dot < (filename.length() - 1))) {  
	                return filename.substring(dot + 1);  
	            }  
	        }  
	        return filename;  
	    }  
	    private static final int BUFFER_SIZE = 2 * 1024;  
	  /**
	   * 
	   * @param src
	   * @param dst
	   */
	    @SuppressWarnings("unused")
		private static void copy(File src, File dst) {  
	        InputStream in = null;  
	        OutputStream out = null;  
	        try {  
	            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);  
	            out = new BufferedOutputStream(new FileOutputStream(dst),  
	                    BUFFER_SIZE);  
	            byte[] buffer = new byte[BUFFER_SIZE];  
	            int len = 0;  
	            while ((len = in.read(buffer)) > 0) {  
	                out.write(buffer, 0, len);  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (null != in) {  
	                try {  
	                    in.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	            if (null != out) {  
	                try {  
	                    out.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	    }  
	  
	    /** 
	     * 上传copy文件方法(for MultipartFile) 
	     * @param savePath 在linux上要保存完整路径 
	     * @param newname 新的文件名称， 采用系统时间做文件名防止中文报错的问题 
	     * @throws Exception 
	     */  
	    public static void copy(MultipartFile file,String savePath,String newname) throws Exception {  
	        try {  
	            File targetFile = new File(savePath,newname);  
	            if (!targetFile.exists()) {  
	                targetFile.mkdirs();  
	            }  
	  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	  
	    }  
	      
	    private static String getCellValue(HSSFCell cell) {  
	        DecimalFormat df = new DecimalFormat("#");  
	        String cellValue=null;  
	        if (cell == null)  
	            return null;  
	        switch (cell.getCellType()) {  
	            case HSSFCell.CELL_TYPE_NUMERIC:  
	                if(HSSFDateUtil.isCellDateFormatted(cell)){  
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	                    cellValue=sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));  
	                    break;  
	                }  
	                cellValue=df.format(cell.getNumericCellValue());  
	                break;  
	            case HSSFCell.CELL_TYPE_STRING:           
	                cellValue=String.valueOf(cell.getStringCellValue());  
	                break;  
	            case HSSFCell.CELL_TYPE_FORMULA:  
	                cellValue=String.valueOf(cell.getCellFormula());  
	                break;  
	            case HSSFCell.CELL_TYPE_BLANK:  
	                cellValue=null;  
	                break;  
	            case HSSFCell.CELL_TYPE_BOOLEAN:  
	                cellValue=String.valueOf(cell.getBooleanCellValue());  
	                break;  
	            case HSSFCell.CELL_TYPE_ERROR:  
	                cellValue=String.valueOf(cell.getErrorCellValue());  
	                break;  
	        }  
	        if(cellValue!=null&&cellValue.trim().length()<=0){  
	            cellValue=null;  
	        }  
	        return cellValue;  
	    }

		@Override
		public Page<BdUseDetail> findByPage(BdUseDetail bdUseDetail, Org org,
				Page<BdUseDetail> page) {
			page.setList(((UseDao)baseDao).findByPage(bdUseDetail,org, page));
			return page;
		}

		@Override
		public List<BdUseDetail> find(BdUseDetail bdUseDetail, String quarter,
				String year) {
			return ((UseDao)baseDao).find(bdUseDetail,quarter,year);
		}

		@Override
		public Page<BdUseDetail> findByPageReport(BdUseDetail bdUseDetail,
				Org org, Page<BdUseDetail> page) {
			page.setList(((UseDao)baseDao).findByPageReport(bdUseDetail,org, page));
			return page;
		}

		@Override
		public void exportReport(BdUseDetail bdUseDetail, HttpServletResponse response, Org org) {
			List<BdUseDetail> list = ((UseDao)baseDao).export(bdUseDetail,org);
			Export export = new Export();
			try {
				export.exportReport(list,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
}
