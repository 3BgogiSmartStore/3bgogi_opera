package com.gogi.proj.dashboard.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.dashboard.util.CreateExcelSheet;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;

@Service
public class DashboardServiceImpl implements DashboardService{

	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	@Autowired
	private DashboardDAO dashboardDao;
	
	@Autowired
	private CreateExcelSheet createExcelSheet;

	@Override
	public List<ProductInfoVO> selectProductTaxInfoType(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dashboardDao.selectProductTaxInfoType(osVO);
	}

	@Override
	public List<ProductInfoVO> selectProductTaxInfoBySuperPiType(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dashboardDao.selectProductTaxInfoBySuperPiType(osVO);
	}

	@Override
	public File productTaxInfoExcelWrite(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		
		List<ProductInfoVO> productTaxInfoList = dashboardDao.selectAllProductTaxInfoForExcelFile(osVO);
		
		List<String> header = new ArrayList<String>();
		
		
		header.add("상품 타입");
		header.add("수량 / 중량");
		header.add("거래 금액");

		SXSSFWorkbook workbook = new SXSSFWorkbook();
		
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("거래내역서");
		
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		
		SXSSFCell cell;
		
		int HeaderCounting = 0;
		// 헤더 정보 구성
		for (HeaderCounting = 0; HeaderCounting < header.size(); HeaderCounting++) {
			cell = (SXSSFCell) row.createCell(HeaderCounting);
			cell.setCellValue(header.get(HeaderCounting));
			sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 1024));

		}
		
		int cellCounting = 1;

		CellStyle cs = workbook.createCellStyle();
		
		DecimalFormat decRadixFormat = new DecimalFormat("#,###.##");
		DecimalFormat numberFormat = new DecimalFormat("###,###");
		double piQtys = 0.0d;

		for(ProductInfoVO piVO : productTaxInfoList) {
			
			row = (SXSSFRow) sheet.createRow(cellCounting);
			
			cell = (SXSSFCell) row.createCell(0);
			cell.setCellValue(piVO.getSuperPiType());
			sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 512));
			
			
			cell = (SXSSFCell) row.createCell(1);
			
			if(piVO.getPiMeasure().equals("g")) {
				
				piQtys = ((double)(piVO.getPiQty()/1000.0));
				
				cell.setCellValue( decRadixFormat.format((piQtys)) + " kg");
				
			}else {
				cell.setCellValue( numberFormat.format(piVO.getPiQty()) + piVO.getPiMeasure());
			}
			
			cell.setCellStyle(cs);
			
			cell = (SXSSFCell) row.createCell(2);
			cell.setCellValue(numberFormat.format(piVO.getPiTotalCost())+" 원");
			sheet.setColumnWidth(2, ( sheet.getColumnWidth(2) + 512));
			
			cellCounting++;
			
		}
		
		List<String> meatHeader = new ArrayList<String>();
		meatHeader.add("부위 명");
		meatHeader.add("판매중량");
		List<CostIoVO> meatLists = dashboardDao.selectMeatTotalWeightInSale(osVO);
		int meatSales = dashboardDao.selectMeatTotalPriceInSale(osVO);
		workbook = createExcelSheet.excelSheetReturnForMeats(workbook, meatHeader, "정육 매출", meatLists, meatSales);
		
		
		List<String> seasoningMeatHeader = new ArrayList<String>();
		seasoningMeatHeader.add("상품명");
		seasoningMeatHeader.add("부위 명");
		seasoningMeatHeader.add("판매중량");
		List<CostIoVO> seasoningMeatLists = dashboardDao.selectSeasoningMeatTotalWeightInSale(osVO);
		int seasoningMeatSales = dashboardDao.selectSeasoningMeatTotalPriceInSale(osVO);
		workbook = createExcelSheet.excelSheetReturnForSeasoningMeat(workbook, seasoningMeatHeader, "양념육 매출", seasoningMeatLists, seasoningMeatSales);
		
		List<String> mealkitHeader = new ArrayList<String>();
		mealkitHeader.add("상품명");
		mealkitHeader.add("판매 개수");
		List<OrdersVO> mealkitLists = dashboardDao.selectMealkitTotalSale(osVO);
		int mealkitSales = dashboardDao.selectMealkikTotalSalePrice(osVO);
		workbook = createExcelSheet.excelSheetReturnForMealkit(workbook, mealkitHeader, "간편조리 매출", mealkitLists, mealkitSales);

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "거래내역서 결과 ["+sdf.format(new Date())+"].xlsx";
		
		File file = new File(fileProperties.getProperty("file.upload.tax_file.path.test"), fileName);
		
		FileOutputStream fos = null;

		try {
			
            fos = new FileOutputStream(file);
            workbook.write(fos);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	
                if(fos!=null) fos.close();
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		
		return file;

	}

	@Override
	public List<CostIoVO> selectMeatTotalWeightInSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dashboardDao.selectMeatTotalWeightInSale(osVO);
	}

	@Override
	public int selectMeatTotalPriceInSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dashboardDao.selectMeatTotalPriceInSale(osVO);
	}

	@Override
	public List<CostIoVO> selectSeasoningMeatTotalWeightInSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dashboardDao.selectSeasoningMeatTotalWeightInSale(osVO);
	}

	@Override
	public int selectSeasoningMeatTotalPriceInSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dashboardDao.selectSeasoningMeatTotalPriceInSale(osVO);
	}

	@Override
	public List<OrdersVO> selectMealkitTotalSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dashboardDao.selectMealkitTotalSale(osVO);
	}

	@Override
	public int selectMealkikTotalSalePrice(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dashboardDao.selectMealkikTotalSalePrice(osVO);
	}
}
