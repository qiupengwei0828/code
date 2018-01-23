package com.bd.service.bdstore;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import com.bd.entity.BdUseDetail;
import com.bd.entity.Store;
import com.sys.utils.DicUtils;
public class Export {


	///导出
	public void export(List<Store> list,HttpServletResponse response) throws Exception {
		response.reset();
		response.addHeader("Content-Type","applicationnd.ms-excel; charset=UTF-8");
		String filename = "凭证库数据";
		response.addHeader("Content-Disposition", "attachment; filename="+ java.net.URLEncoder.encode(filename+".xls", "UTF-8"));  
		response.addHeader("Pragma", "public");
		response.addHeader("Cache-Control", "max-age=0");		
		OutputStream out =response.getOutputStream();
		exportExcel(list,out); 
		out.flush();
		out.close();
	}
	
	private void exportExcel(List<Store> list,OutputStream out) throws Exception {	
		//声明一个EXCEL工作薄
		HSSFWorkbook myWorkbook = new HSSFWorkbook();
		//生成一个sheet
		HSSFSheet mySheet = myWorkbook.createSheet("凭证库数据");
		//设置默认值
		//mySheet.setDefaultColumnWidth(10);
		mySheet.setDefaultRowHeight((short) 320);
		CreateTable(mySheet,myWorkbook,list
	        		);
        myWorkbook.write(out);
	}
	
	
	
	
	private void CreateTable(HSSFSheet sheet,HSSFWorkbook workbook,List<Store> list){		
	     	HSSFDataFormat format  =  workbook.createDataFormat();
			HSSFCellStyle cellStyle1 = myStyle(sheet, HSSFColor.WHITE.index, HSSFCellStyle.ALIGN_CENTER, HSSFFont.BOLDWEIGHT_NORMAL,10);
			HSSFCellStyle cellStyle = myStyle(sheet, HSSFColor.WHITE.index, HSSFCellStyle.ALIGN_RIGHT, HSSFFont.BOLDWEIGHT_NORMAL,10);

			setValueByRowAndCol(0,0,"机构代码",sheet,cellStyle,format);
			setValueByRowAndCol(0,1,"机构名称",sheet,cellStyle,format);
			setValueByRowAndCol(0,2,"库存类型",sheet,cellStyle,format);
			setValueByRowAndCol(0,3,"单证类型",sheet,cellStyle,format);
			setValueByRowAndCol(0,4,"单证代码",sheet,cellStyle,format);
			setValueByRowAndCol(0,5,"单证名称",sheet,cellStyle,format);
			setValueByRowAndCol(0,6,"库存数量",sheet,cellStyle,format);
			setValueByRowAndCol(0,7,"凭证库",sheet,cellStyle,format);
			setValueByRowAndCol(0,8,"凭证分户",sheet,cellStyle,format);
			setValueByRowAndCol(0,9,"业务范围",sheet,cellStyle,format);
			setValueByRowAndCol(0,10,"在库状态",sheet,cellStyle,format);
			setValueByRowAndCol(0,11,"备注",sheet,cellStyle,format);
			for(int i=0;i<list.size();i++){
				Store store = list.get(i);					
					setValueByRowAndCol(i+1,0,store.getOrgNo(),sheet, cellStyle1,format);
				    setValueByRowAndCol(i+1,1,store.getOrgName(),sheet, cellStyle1,format);
				    setValueByRowAndCol(i+1,2,DicUtils.getDicLabel(store.getRepType(),"DIC_BD_REP_TYPE", ""),sheet,cellStyle,format);
				    setValueByRowAndCol(i+1,3,DicUtils.getDicLabel(store.getCertactType(),"DIC_BD_CERTACTTYPE", ""),sheet,cellStyle,format);
				    setValueByRowAndCol(i+1,4,store.getCertactCode(),sheet,cellStyle,format);
				    setValueByRowAndCol(i+1,5,store.getCertactName(),sheet,cellStyle,format);
				    setValueByRowAndCol(i+1,6,store.getStoreNum(),sheet,cellStyle,format);
				    setValueByRowAndCol(i+1,7,DicUtils.getDicLabel(store.getCertactStore(),"DIC_BD_CERTACT_STORE", ""),sheet,cellStyle,format);
				    setValueByRowAndCol(i+1,8,store.getCertactSplit(),sheet,cellStyle,format);
				    setValueByRowAndCol(i+1,9,DicUtils.getDicLabel(store.getBusinessScope(),"DIC_BD_SCOPE", ""),sheet,cellStyle,format);
				    setValueByRowAndCol(i+1,10,DicUtils.getDicLabel(store.getStatus(),"DIC_BD_STORE_STATUS", ""),sheet,cellStyle,format);
				    setValueByRowAndCol(i+1,11,store.getRemark(),sheet,cellStyle,format);
			 }
			/**设置列宽自适用大小
			 * */
			for(int l=0;l<11;l++){
				sheet.autoSizeColumn(l, true);
		}
	}
	///导出
		public void exportReport(List<BdUseDetail> list,HttpServletResponse response) throws Exception {
			response.reset();
			response.addHeader("Content-Type",
					"applicationnd.ms-excel; charset=UTF-8");
			String filename = "用量报表数据";
			response.addHeader("Content-Disposition", "attachment; filename="+ java.net.URLEncoder.encode(filename+".xls", "UTF-8"));  
			response.addHeader("Pragma", "public");
			response.addHeader("Cache-Control", "max-age=0");		
			OutputStream out =response.getOutputStream();
			exportExcelReport(list,out); 
			out.flush();
			out.close();
		}
		private void exportExcelReport(List<BdUseDetail> list,OutputStream out) throws Exception {	
			//声明一个EXCEL工作薄
			HSSFWorkbook myWorkbook = new HSSFWorkbook();
			//生成一个sheet
			HSSFSheet mySheet = myWorkbook.createSheet("用量报表数据");
			//设置默认值
			//mySheet.setDefaultColumnWidth(10);
			mySheet.setDefaultRowHeight((short) 320);
			CreateTableReport(mySheet,myWorkbook,list
		        		);
	        myWorkbook.write(out);
		}
		private void CreateTableReport(HSSFSheet sheet,HSSFWorkbook workbook,List<BdUseDetail> list){		
		     	HSSFDataFormat format  =  workbook.createDataFormat();
				HSSFCellStyle cellStyle1 = myStyle(sheet, HSSFColor.WHITE.index, HSSFCellStyle.ALIGN_CENTER, HSSFFont.BOLDWEIGHT_NORMAL,10);
				HSSFCellStyle cellStyle = myStyle(sheet, HSSFColor.WHITE.index, HSSFCellStyle.ALIGN_RIGHT, HSSFFont.BOLDWEIGHT_NORMAL,10);

				setValueByRowAndCol(0,0,"机构代码",sheet,cellStyle,format);
				setValueByRowAndCol(0,1,"机构名称",sheet,cellStyle,format);
				setValueByRowAndCol(0,2,"单证代码",sheet,cellStyle,format);
				setValueByRowAndCol(0,3,"单证名称",sheet,cellStyle,format);
				setValueByRowAndCol(0,4,"使用类型",sheet,cellStyle,format);
				setValueByRowAndCol(0,5,"用量",sheet,cellStyle,format);
				setValueByRowAndCol(0,6,"使用日期",sheet,cellStyle,format);
				setValueByRowAndCol(0,7,"操作人员",sheet,cellStyle,format);
				for(int i=0;i<list.size();i++){
					BdUseDetail store = list.get(i);					
						setValueByRowAndCol(i+1,0,store.getOrgNo(),sheet, cellStyle1,format);
					    setValueByRowAndCol(i+1,1,store.getOrgName(),sheet, cellStyle1,format);
					   setValueByRowAndCol(i+1,2,store.getCertactCode(),sheet,cellStyle,format);
					    setValueByRowAndCol(i+1,3,store.getCertactName(),sheet,cellStyle,format);
					    setValueByRowAndCol(i+1,4,DicUtils.getDicLabel(store.getUseType(),"DIC_USE_TYPE", ""),sheet,cellStyle,format);
					    setValueByRowAndCol(i+1,5,store.getUseNum().doubleValue(),sheet,cellStyle,format);
					    setValueByRowAndCol(i+1,6,store.getUseDate(),sheet,cellStyle,format);
					    setValueByRowAndCol(i+1,7,store.getUserId(),sheet,cellStyle,format);
					    }
				/**设置列宽自适用大小
				 * */
				for(int l=0;l<7;l++){
					sheet.autoSizeColumn(l, true);
			}
		}

	//根据行和高赋值，值是String类型
	public static void setValueByRowAndCol(int row,int col,String value,HSSFSheet sheet,HSSFCellStyle style,HSSFDataFormat format ){
		HSSFRow hssfrow = sheet.getRow(row);
		HSSFCell cell = null;
		if(hssfrow == null){
			hssfrow = sheet.createRow(row);
			cell= hssfrow.createCell(col);
			cell.setCellType(HSSFCell.ENCODING_UTF_16);   
			cell.setCellValue(new HSSFRichTextString(value));
			cell.setCellStyle(style);
			
		}else{
			cell= hssfrow.createCell(col);
			cell.setCellType(HSSFCell.ENCODING_UTF_16);   
			cell.setCellValue(new HSSFRichTextString(value));
			cell.setCellStyle(style);
		}
		
	}
	//根据行和高赋值，值是double类型
	public static void setValueByRowAndCol(int row,int col,double value,HSSFSheet sheet,HSSFCellStyle style,HSSFDataFormat format){
		
		HSSFRow hssfrow = sheet.getRow(row);
		HSSFCell cell = null;
		if(hssfrow == null){
			hssfrow = sheet.createRow(row);
			cell= hssfrow.createCell(col);			
//			String str = DecimalFormat.getNumberInstance().format(value);
			cell.setCellValue(value);
			style.setDataFormat( format.getFormat("#,##0.00"));
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellStyle(style);
			
		}else{
			cell= hssfrow.createCell(col); 			
//			NumberFormat nf = new jxl.write.NumberFormat("#,##0.00");
//			WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
			cell.setCellValue(value);
			style.setDataFormat( format.getFormat("#,##0.00"));
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellStyle(style);
		}
		
	}
	
  //合并单元格设置边框
	public static void setRegionStyle(HSSFCellStyle cs, CellRangeAddress region, HSSFSheet sheet){
	   
	    for(int i=region.getFirstRow();i<=region.getLastRow();i++){
	    	
	    	HSSFRow row=sheet.getRow(i);
	    	if(row==null) row=sheet.createRow(i);
	    	for(int j=region.getFirstColumn();j<=region.getLastColumn();j++){
	    		HSSFCell cell=row.getCell(j);
	    		if( cell==null){
	    			cell=row.createCell(j);
	    			cell.setCellValue("");
	    		}
	    		 cell.setCellStyle(cs);
	    	}
	    }
	    sheet.addMergedRegion(region);
	}
	//获取样式方法
	//对应参数分别为 sheet，背景颜色，对齐方式，字体宽度，字体大小
	public static HSSFCellStyle myStyle(HSSFSheet mySheet,short bg,short myAlign,short boldweight,int height){
		//生成一个样式
		HSSFCellStyle cellStyle = mySheet.getWorkbook().createCellStyle();
		//设置样式
		cellStyle.setWrapText(true);   //自动换行
		cellStyle.setAlignment(myAlign);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
		cellStyle.setFillForegroundColor(bg);// 设置单元格的背景颜色．
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		//设置边框
		
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);  
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);  
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);  
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
		// 生成一个字体
        HSSFFont font = mySheet.getWorkbook().createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) height);
        font.setBoldweight(boldweight);
        //字体应用到样式中
        cellStyle.setFont(font);
        return cellStyle;
	}
	public static HSSFCellStyle myStyleNoBorder(HSSFSheet mySheet,short bg,short myAlign,short boldweight,int height){
		
		//生成一个样式
		HSSFCellStyle cellStyle = mySheet.getWorkbook().createCellStyle();
		//设置样式
		cellStyle.setWrapText(true);   //自动换行
		cellStyle.setAlignment(myAlign);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
		cellStyle.setFillForegroundColor(bg);// 设置单元格的背景颜色．
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// 生成一个字体
        HSSFFont font = mySheet.getWorkbook().createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) height);
        font.setBoldweight(boldweight);
        //字体应用到样式中
        cellStyle.setFont(font);
        return cellStyle;
	}
	   
}