package com.gogi.proj.review.util;

import java.io.FileInputStream;
import java.util.ArrayList;
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
import com.gogi.proj.review.vo.CustomerReviewVO;
import com.gogi.proj.util.FileuploadUtil;
import com.gogi.proj.util.StringUtil;

@Component
public class CustomerReviewExcel {

	@Autowired
	private FileuploadUtil fileUtil;
	
	@Autowired
	private StringUtil sUtil;
	
	@Autowired
	private ReadOrderExcel readOrderExcel;
	
	public List<CustomerReviewVO> readCustomerReviewExcelList(String fileName) throws POIXMLException{
		
		List<CustomerReviewVO> reviewList = new ArrayList<CustomerReviewVO>();
		
		String ext = fileUtil.getExtType(fileName);

		String upPath = fileUtil.getUploadPath(FileuploadUtil.ORDER_EXCEL, false);
		
		try {
	
			FileInputStream fis= new FileInputStream(upPath+"\\"+fileName);
			
			// 엑셀파일 확장자가 xlsx 일 경우
			// 분기별로 처리해서 해야함
			if(ext.equals(".xlsx")) {
					XSSFWorkbook workbook=new XSSFWorkbook(fis);
					
					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
					
					int rowStartIndex = 1;

					
					XSSFSheet sheet=workbook.getSheetAt(0);
					
					int rows=sheet.getPhysicalNumberOfRows();
					
					CustomerReviewVO reviewVO;
					
					for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
						
						XSSFRow row=sheet.getRow(rowindex);
						
						reviewVO = new CustomerReviewVO();
						
						if(row !=null && !row.getCell(0).getStringCellValue().equals("false") && !row.getCell(0).getStringCellValue().equals("")){
							
							for(int columnIndex = 0; columnIndex < 25; columnIndex++ ){
								
								XSSFCell cell=row.getCell(columnIndex);
								// 판매처별로 엑셀 열을 읽어서 씀
								//구매자명
								if(cell==null) {
									continue;
									
								}else {
									
									if(columnIndex==1) {
										String value = readOrderExcel.cellTypeReturn(cell);

										reviewVO.setCrProdName(value);
											
									}if(columnIndex==3) {
										
										String value = (readOrderExcel.cellTypeReturn(cell)+"").replaceAll(" ", "");
										
										reviewVO.setCrGrade(Integer.parseInt(value));
										
									}if(columnIndex==5) {
										
										String value = readOrderExcel.cellTypeReturn(cell);
										
										reviewVO.setCrContent(sUtil.mysqlSafe(value));
										
									}if(columnIndex==8) {
										String value = readOrderExcel.cellTypeReturn(cell);
										
										reviewVO.setCrRegdate(value);
											
									}if(columnIndex==10) {
										String value = readOrderExcel.cellTypeReturn(cell);
										
										reviewVO.setCrNum(value);
									}if(columnIndex==23) {
										String value = readOrderExcel.cellTypeReturn(cell);
										
										reviewVO.setCrProdOrderNum(value);
											
									}
								}
									
								
							}//for
							
							reviewList.add(reviewVO);
						}
						
					}//for
				
			//엑셀파일 확장자가 xls일 경우
			}else if(ext.equals(".xls")){
				HSSFWorkbook workbook=new HSSFWorkbook(fis);
				
				FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
				
				int rowStartIndex = 1;
				
				int columnIndex=0;
				
				HSSFSheet sheet=workbook.getSheetAt(0);
				
				int rows=sheet.getPhysicalNumberOfRows();
				
				CustomerReviewVO reviewVO;
				
				for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
					
					HSSFRow row=sheet.getRow(rowindex);
					
					reviewVO = new CustomerReviewVO();
					
					if(row !=null && !row.getCell(0).getStringCellValue().equals("false") && !row.getCell(0).getStringCellValue().equals("")){

						for(columnIndex=0;columnIndex<11;columnIndex++){
							
							HSSFCell cell=row.getCell(columnIndex);
							// 판매처별로 엑셀 열을 읽어서 씀
							
							//구매자명
							if(cell==null) {
								continue;
								
							}else {
								
								if(columnIndex==1) {
									String value = readOrderExcel.cellTypeReturnHSS(cell);

									reviewVO.setCrProdName(value);
										
								}if(columnIndex==3) {
									
									String value = (readOrderExcel.cellTypeReturnHSS(cell)+"").replaceAll(" ", "");
									
									reviewVO.setCrGrade(Integer.parseInt(value));
									
								}if(columnIndex==5) {
									
									String value = readOrderExcel.cellTypeReturnHSS(cell);
									
									reviewVO.setCrContent(sUtil.mysqlSafe(value));
									
								}if(columnIndex==8) {
									String value = readOrderExcel.cellTypeReturnHSS(cell);
									
									reviewVO.setCrRegdate(value);
										
								}if(columnIndex==10) {
									String value = readOrderExcel.cellTypeReturnHSS(cell);
									
									reviewVO.setCrNum(value);
								}if(columnIndex==23) {
									String value = readOrderExcel.cellTypeReturnHSS(cell);
									
									reviewVO.setCrProdOrderNum(value);
										
								}
							}
							
							reviewList.add(reviewVO);
							
						}//for
					}
					
				}//for
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return reviewList;
	}
}
