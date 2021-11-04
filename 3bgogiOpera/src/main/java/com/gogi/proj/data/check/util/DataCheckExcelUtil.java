package com.gogi.proj.data.check.util;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gogi.proj.excel.ReadOrderExcel;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.FileuploadUtil;

@Component
public class DataCheckExcelUtil {

	@Autowired
	private FileuploadUtil fu;
	
	public OrderSearchVO readGiftSetExcelFile(String fileName, OrderSearchVO osVO) throws POIXMLException{
		ReadOrderExcel roe = new ReadOrderExcel();

		List<String> buyerCntList = null;
		
		String ext = fu.getExtType(fileName);
		
		try {
	
			FileInputStream fis= new FileInputStream("C:\\Users\\\uBA85\uD6C8\uD30D\\Desktop\\server_file\\"+fileName);
			
			// 엑셀파일 확장자가 xlsx 일 경우
			// 분기별로 처리해서 해야함
			if(ext.equals(".xlsx")) {
					XSSFWorkbook workbook=new XSSFWorkbook(fis);
					
					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
					
					int rowStartIndex = 1;
					
					int columnindex=0;
					
					XSSFSheet sheet=workbook.getSheetAt(0);
					
					int rows=sheet.getPhysicalNumberOfRows();
					
					buyerCntList = new ArrayList<>();
					
					for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
						
						XSSFRow row=sheet.getRow(rowindex);
						
						
						if(row !=null){
							
							for(columnindex=0;columnindex<2;columnindex++){
								
								XSSFCell cell=row.getCell(columnindex);
								// 판매처별로 엑셀 열을 읽어서 씀
								//구매자명
								if(cell==null) {
									
								}else {
									
									if(columnindex==0) {
										if(cell.getStringCellValue() == null || cell.getStringCellValue().equals("")) {
										}else {											
											String value = roe.cellTypeReturn(cell);
											
											buyerCntList.add(value);
										}
											
									}
								}
								
							}//for
						}
						
						
						
					}//for
				
			//엑셀파일 확장자가 xls일 경우
			}else if(ext.equals(".xls")){
				HSSFWorkbook workbook=new HSSFWorkbook(fis);
				
				FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
				
				int rowStartIndex = 1;
				
				int columnindex=0;
				
				HSSFSheet sheet=workbook.getSheetAt(0);
				
				int rows=sheet.getPhysicalNumberOfRows();
				
				buyerCntList = new ArrayList<>();
				
				for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
					
					HSSFRow row=sheet.getRow(rowindex);
					
					
					if(row !=null){
						
						for(columnindex=0;columnindex<2;columnindex++){
							
							HSSFCell cell=row.getCell(columnindex);
							// 판매처별로 엑셀 열을 읽어서 씀
							//구매자명
							if(cell==null) {
						

							}else {

								if(columnindex==0) {
									if(cell.getStringCellValue() == null || cell.getStringCellValue().equals("")) {
									}else {											
										String value = roe.cellTypeReturnHSS(cell);
										
										buyerCntList.add(value);
									}
										
								}
							}
							
						}//for

					}
					
				}//for
			}
			
			System.out.println("buyerCntList = "+buyerCntList.size());
			osVO.setOrSerialSpecialNumberList(buyerCntList);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return osVO;
	}

}
