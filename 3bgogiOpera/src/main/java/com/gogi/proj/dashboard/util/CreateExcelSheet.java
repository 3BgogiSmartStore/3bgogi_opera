package com.gogi.proj.dashboard.util;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.product.cost.vo.CostIoVO;

@Component
public class CreateExcelSheet {

	
	public SXSSFWorkbook excelSheetReturnForMeats(SXSSFWorkbook workbook, List<String> header, String sheetName, List<CostIoVO> costListType, int totalSales) {
		
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(sheetName);
		
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		
		SXSSFCell cell;
		
		int HeaderCounting = 0;
		// 헤더 정보 구성
		for (HeaderCounting = 0; HeaderCounting < header.size(); HeaderCounting++) {
			cell = (SXSSFCell) row.createCell(HeaderCounting);
			cell.setCellValue(header.get(HeaderCounting));
			sheet.autoSizeColumn(HeaderCounting);
			sheet.setColumnWidth(HeaderCounting, ( sheet.getColumnWidth(0) + 1024));

		}
		
		
		int cellCounting = 1;

		CellStyle cs = workbook.createCellStyle();

		DecimalFormat numberFormat = new DecimalFormat("###,###");
		
		for(CostIoVO ciVO : costListType) {
			
			row = (SXSSFRow) sheet.createRow(cellCounting);
			
			cell = (SXSSFCell) row.createCell(0);
			cell.setCellValue(ciVO.getCostDetailName());
			sheet.autoSizeColumn(0);
			sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
			
			
			cell = (SXSSFCell) row.createCell(1);
			cell.setCellValue( numberFormat.format(ciVO.getCiWeight()) + " g");
			cell.setCellStyle(cs);
			sheet.autoSizeColumn(0);
			sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
			
			cellCounting++;
			
		}
		
		row = (SXSSFRow) sheet.createRow(cellCounting);
		
		cell = (SXSSFCell) row.createCell(0);
		cell.setCellValue("총 매출 ");
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
		
		
		cell = (SXSSFCell) row.createCell(1);
		cell.setCellValue( numberFormat.format(totalSales) + " 원");
		cell.setCellStyle(cs);
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
		
		
		return workbook;
	}
	
	
	public SXSSFWorkbook excelSheetReturnForSeasoningMeat(SXSSFWorkbook workbook, List<String> header, String sheetName, List<CostIoVO> costListType, int totalSales) {
		
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(sheetName);
		
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		
		SXSSFCell cell;
		
		int HeaderCounting = 0;
		// 헤더 정보 구성
		for (HeaderCounting = 0; HeaderCounting < header.size(); HeaderCounting++) {
			cell = (SXSSFCell) row.createCell(HeaderCounting);
			cell.setCellValue(header.get(HeaderCounting));
			sheet.autoSizeColumn(HeaderCounting);
			sheet.setColumnWidth(HeaderCounting, ( sheet.getColumnWidth(0) + 1024));

		}
		
		
		int cellCounting = 1;

		CellStyle cs = workbook.createCellStyle();

		DecimalFormat numberFormat = new DecimalFormat("###,###");
		
		for(CostIoVO ciVO : costListType) {
			
			row = (SXSSFRow) sheet.createRow(cellCounting);
			
			cell = (SXSSFCell) row.createCell(0);
			cell.setCellValue(ciVO.getCostDetailName());
			sheet.autoSizeColumn(0);
			sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 512));
			
			
			cell = (SXSSFCell) row.createCell(1);
			cell.setCellValue( ciVO.getCiCountryOfOrigin());
			cell.setCellStyle(cs);
			sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
			
			cell = (SXSSFCell) row.createCell(2);
			cell.setCellValue( numberFormat.format(ciVO.getCiWeight()) + " g");
			cell.setCellStyle(cs);
			sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
			
			cellCounting++;
			
		}

		row = (SXSSFRow) sheet.createRow(cellCounting);
		
		cell = (SXSSFCell) row.createCell(0);
		cell.setCellValue("총 매출");
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 512));
		sheet.addMergedRegion(new CellRangeAddress(cellCounting, cellCounting, 0, 1));
		
		cell = (SXSSFCell) row.createCell(2);
		cell.setCellValue( numberFormat.format(totalSales) + " 원");
		cell.setCellStyle(cs);
		sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
		
		
		return workbook;
	}
	
	
	public SXSSFWorkbook excelSheetReturnForMealkit(SXSSFWorkbook workbook, List<String> header, String sheetName, List<OrdersVO> mealkitListType, int totalSales) {
		
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(sheetName);
		
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		
		SXSSFCell cell;
		
		int HeaderCounting = 0;
		// 헤더 정보 구성
		for (HeaderCounting = 0; HeaderCounting < header.size(); HeaderCounting++) {
			cell = (SXSSFCell) row.createCell(HeaderCounting);
			cell.setCellValue(header.get(HeaderCounting));
			sheet.setColumnWidth(HeaderCounting, ( sheet.getColumnWidth(0) + 1024));

		}
		
		
		int cellCounting = 1;

		CellStyle cs = workbook.createCellStyle();

		DecimalFormat numberFormat = new DecimalFormat("###,###");
		
		for(OrdersVO orVO : mealkitListType) {
			
			row = (SXSSFRow) sheet.createRow(cellCounting);
			
			cell = (SXSSFCell) row.createCell(0);
			cell.setCellValue(orVO.getOrProduct());
			sheet.autoSizeColumn(0);
			sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
			
			
			cell = (SXSSFCell) row.createCell(1);
			cell.setCellValue( numberFormat.format(orVO.getOrAmount()) + "개");
			cell.setCellStyle(cs);
			sheet.autoSizeColumn(0);
			sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
			
			cellCounting++;
			
		}		
		
		row = (SXSSFRow) sheet.createRow(cellCounting);
		
		cell = (SXSSFCell) row.createCell(0);
		cell.setCellValue("총 매출 ");
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
		
		
		cell = (SXSSFCell) row.createCell(1);
		cell.setCellValue( numberFormat.format(totalSales) + " 원");
		cell.setCellStyle(cs);
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));
		
		return workbook;
	}
	
}
