package com.gogi.proj.aligo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.gogi.proj.commission.SmartstoreCommission;
import com.gogi.proj.excel.ReadOrderExcel;
import com.gogi.proj.excel.SheetListHandler;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.util.FileuploadUtil;

@Component
public class AligoExcelUitl {

	@Autowired
	private ReadOrderExcel roe;
	
	@Autowired
	private FileuploadUtil fu;
	
	public List<AligoVO> aligoExcelFileRead(String fileName) throws POIXMLException{
		
		List<AligoVO> aligoList = new ArrayList<AligoVO>();
		
		String ext = fu.getExtType(fileName);
		
		String upPath = fu.getUploadPath(FileuploadUtil.ORDER_EXCEL, false);
		
		try {
	
			FileInputStream fis= new FileInputStream(upPath+"\\"+fileName);
			
			// ???????????? ???????????? xlsx ??? ??????
			// ???????????? ???????????? ?????????
			if(ext.equals(".xlsx")) {
					SXSSFWorkbook workbook= new SXSSFWorkbook(new XSSFWorkbook(fis));
					
					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
					
					int rowStartIndex = 1;
					
					int columnindex=0;
					
					SXSSFSheet sheet=(SXSSFSheet) workbook.getSheetAt(0);
					
					int rows=sheet.getPhysicalNumberOfRows();
					
					AligoVO aligo;
					
					for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
						
						SXSSFRow row=(SXSSFRow) sheet.getRow(rowindex);
						
						aligo = new AligoVO();
						
						if(row !=null){
							
							for(columnindex=0;columnindex<2;columnindex++){
								
								SXSSFCell cell=(SXSSFCell) row.getCell(columnindex);
								if(cell==null) {
									continue;
									
								}else {
									
									//?????????
									if(columnindex==0) {
										String value = roe.cellTypeReturnSXSS(cell);
										
										aligo.setDestination(value);
									
									//????????????
									}if(columnindex==1) {
										String value = roe.cellTypeReturnSXSS(cell);
										
										aligo.setReceiver(value);
										
									}
								}
								
							}//for
						}
						
						aligoList.add(aligo);
						
					}//for
				
			//???????????? ???????????? xls??? ??????
			}else if(ext.equals(".xls")){
				HSSFWorkbook workbook=new HSSFWorkbook(fis);
				
				FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
				
				int rowStartIndex = 1;
				
				int columnindex=0;
				
				HSSFSheet sheet=workbook.getSheetAt(0);
				
				int rows=sheet.getPhysicalNumberOfRows();
				
				AligoVO aligo = null;
				
				for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
					
					HSSFRow row=sheet.getRow(rowindex);
					
					aligo = new AligoVO();
					
					if(row !=null){
						
						for(columnindex=0;columnindex<2;columnindex++){
							
							HSSFCell cell=row.getCell(columnindex);
							
							if(cell==null) {
								continue;
								
							}else {
								
								//?????????
								if(columnindex==0) {
									String value = roe.cellTypeReturnHSS(cell);
									
									aligo.setDestination(value);
								
								//????????????
								}if(columnindex==1) {
									String value = roe.cellTypeReturnHSS(cell);
									
									aligo.setReceiver(value);
									
								}
							}
							
						}//for
					}
					
					aligoList.add(aligo);
					
				}//for
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return aligoList;
	}
	
	public List<AligoVO> saxParsing(String fileName, String filePath) throws IOException, OpenXML4JException, SAXException, ParserConfigurationException{
		FileInputStream fis= new FileInputStream(filePath+"\\"+fileName);
		
		OPCPackage opc = OPCPackage.open(fis);
		
		XSSFReader xssfReader = new XSSFReader(opc);
		
		XSSFReader.SheetIterator itr = (XSSFReader.SheetIterator)xssfReader.getSheetsData();
		
		StylesTable styles = xssfReader.getStylesTable();
		
		ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(opc);
		
		
		//List<String []> dataList = new ArrayList<String[]>();
		
		List<AligoVO> aligoVO = new ArrayList<AligoVO>();
		
		while(itr.hasNext()) {
			InputStream sheetStream = itr.next();
			InputSource sheetSource = new InputSource(sheetStream);
			
			AligoSheetHandler sheetList = new AligoSheetHandler(aligoVO, 2);
			
			ContentHandler handler = new XSSFSheetXMLHandler(styles, strings, sheetList, false);
			
			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxFactory.newSAXParser();
			
			XMLReader sheetParser = saxParser.getXMLReader();
			
			sheetParser.setContentHandler(handler);
			
			sheetParser.parse(sheetSource);
			
			sheetStream.close();
		}
		
		opc.close();
		
		return aligoVO;
	}

}
