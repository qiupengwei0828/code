package com.setp.service.exportorgavg;

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
import com.setp.entity.SetpCount;

public class ExportOrgAvg {

	// /导出
	public void export(List<SetpCount> list, HttpServletResponse response) throws Exception {
		response.reset();
		response.addHeader("Content-Type", "applicationnd.ms-excel; charset=UTF-8");
		String filename = "部门计步平均排名";
		response.addHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(filename + ".xls", "UTF-8"));
		response.addHeader("Pragma", "public");
		response.addHeader("Cache-Control", "max-age=0");
		OutputStream out = response.getOutputStream();
		exportExcel(list, out);
		out.flush();
		out.close();
	}

	private void exportExcel(List<SetpCount> list, OutputStream out) throws Exception {
		// 声明一个EXCEL工作薄
		HSSFWorkbook myWorkbook = new HSSFWorkbook();
		// 生成一个sheet
		HSSFSheet mySheet = myWorkbook.createSheet("部门计步平均排名明细");
		// 设置默认值
		// mySheet.setDefaultColumnWidth(10);
		mySheet.setDefaultRowHeight((short) 320);
		CreateTable(mySheet, myWorkbook, list);
		myWorkbook.write(out);
	}

	private void CreateTable(HSSFSheet sheet, HSSFWorkbook workbook, List<SetpCount> list) {
		HSSFDataFormat format = workbook.createDataFormat();
		HSSFCellStyle cellStyle1 = myStyle(sheet, HSSFColor.WHITE.index, HSSFCellStyle.ALIGN_CENTER, HSSFFont.BOLDWEIGHT_NORMAL, 10);
		HSSFCellStyle cellStyle = myStyle(sheet, HSSFColor.WHITE.index, HSSFCellStyle.ALIGN_RIGHT, HSSFFont.BOLDWEIGHT_NORMAL, 10);

		setValueByRowAndCol(0, 0, "排名", sheet, cellStyle, format);
		setValueByRowAndCol(0, 1, "机构", sheet, cellStyle, format);
		setValueByRowAndCol(0, 2, "平均步数", sheet, cellStyle, format);

		for (int i = 0; i < list.size(); i++) {
			SetpCount setpCount = list.get(i);
			setValueByRowAndCol(i + 1, 0, setpCount.getRownum(), sheet, cellStyle1, format);
			setValueByRowAndCol(i + 1, 1, setpCount.getOrgName(), sheet, cellStyle1, format);
			setValueByRowAndCol(i + 1, 2, setpCount.getNum(), sheet, cellStyle1, format);
		}

		/*
		 * 设置列宽自适用大小
		 */
		for (int l = 0; l < list.size(); l++) {
			sheet.setColumnWidth(l, 6000);
			// sheet.autoSizeColumn(l, true);
		}
	}

	// 根据行和高赋值，值是String类型
	public static void setValueByRowAndCol(int row, int col, String value, HSSFSheet sheet, HSSFCellStyle style, HSSFDataFormat format) {
		HSSFRow hssfrow = sheet.getRow(row);
		HSSFCell cell = null;
		if (hssfrow == null) {
			hssfrow = sheet.createRow(row);
			cell = hssfrow.createCell(col);
			cell.setCellType(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(new HSSFRichTextString(value));
			cell.setCellStyle(style);
		} else {
			cell = hssfrow.createCell(col);
			cell.setCellType(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(new HSSFRichTextString(value));
			cell.setCellStyle(style);
		}
	}

	// 根据行和高赋值，值是double类型
	public static void setValueByRowAndCol(int row, int col, double value, HSSFSheet sheet, HSSFCellStyle style, HSSFDataFormat format) {

		HSSFRow hssfrow = sheet.getRow(row);
		HSSFCell cell = null;
		if (hssfrow == null) {
			hssfrow = sheet.createRow(row);
			cell = hssfrow.createCell(col);
			// String str = DecimalFormat.getNumberInstance().format(value);
			cell.setCellValue(value);
			style.setDataFormat(format.getFormat("#,##0.00"));
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellStyle(style);

		} else {
			cell = hssfrow.createCell(col);
			// NumberFormat nf = new jxl.write.NumberFormat("#,##0.00");
			// WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
			cell.setCellValue(value);
			style.setDataFormat(format.getFormat("#,##0.00"));
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellStyle(style);
		}

	}

	// 合并单元格设置边框
	public static void setRegionStyle(HSSFCellStyle cs, CellRangeAddress region, HSSFSheet sheet) {

		for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
			HSSFRow row = sheet.getRow(i);
			if (row == null)
				row = sheet.createRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				HSSFCell cell = row.getCell(j);
				if (cell == null) {
					cell = row.createCell(j);
					cell.setCellValue("");
				}
				cell.setCellStyle(cs);
			}
		}
		sheet.addMergedRegion(region);
	}

	// 获取样式方法
	// 对应参数分别为 sheet，背景颜色，对齐方式，字体宽度，字体大小
	public static HSSFCellStyle myStyle(HSSFSheet mySheet, short bg, short myAlign, short boldweight, int height) {

		// 生成一个样式
		HSSFCellStyle cellStyle = mySheet.getWorkbook().createCellStyle();

		// 设置样式
		cellStyle.setWrapText(true); // 自动换行
		cellStyle.setAlignment(myAlign);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setFillForegroundColor(bg);// 设置单元格的背景颜色．
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		// 设置边框
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

		// 字体应用到样式中
		cellStyle.setFont(font);
		return cellStyle;
	}

	public static HSSFCellStyle myStyleNoBorder(HSSFSheet mySheet, short bg, short myAlign, short boldweight, int height) {

		// 生成一个样式
		HSSFCellStyle cellStyle = mySheet.getWorkbook().createCellStyle();
		// 设置样式
		cellStyle.setWrapText(true); // 自动换行
		cellStyle.setAlignment(myAlign);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setFillForegroundColor(bg);// 设置单元格的背景颜色．
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// 生成一个字体
		HSSFFont font = mySheet.getWorkbook().createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) height);
		font.setBoldweight(boldweight);
		// 字体应用到样式中
		cellStyle.setFont(font);
		return cellStyle;
	}

}
