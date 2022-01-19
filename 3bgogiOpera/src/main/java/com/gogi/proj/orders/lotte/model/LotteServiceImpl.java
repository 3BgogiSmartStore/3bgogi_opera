package com.gogi.proj.orders.lotte.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

@Service
public class LotteServiceImpl implements LotteService{

	private static final Logger logger = LoggerFactory.getLogger(LotteServiceImpl.class);
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	@Autowired
	private LotteDAO lotteDao;
	
	@Autowired
	private LogService logService;

	@Override
	public int selectDontGrantLotteDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return lotteDao.selectDontGrantLotteDelivOrderListInMonthCounting(osVO);
	}

	@Override
	public List<OrdersVOList> selectDontGrantLotteDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return lotteDao.selectDontGrantLotteDelivOrderListInMonth(osVO);
	}

	@Override
	public File lotteDeliveryExcelInfo(OrderSearchVO osVO, String ip, String adminId) {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		
		String dates = dateFormat.format(today);
		
		// cnplus프로그램에 넣을 주문서 목록 조회
		List<OrdersVO> delivTarget = lotteDao.selectLotteDeliveryExcelTarget(osVO);
		
		if(delivTarget == null) {
			logger.info("Lotte delivery delivTarget is null, return excel files");
			return null;
		}
		
		
		if(delivTarget.size() == 0) {
			logger.info("Lotte delivery delivTarget is empty, return excel files");
			return null;
		}
		
		int result = lotteDao.updateLotteDeliveryTargetBeforeGrantInvoiceNum(delivTarget);

		OrderHistoryVO ohVO = null;
		
		int temp = 0;
		
		List<OrdersVO> orList = null; 
		
		
		for( OrdersVO orVO : delivTarget) {
			
			orList = lotteDao.selectOrdersPkByOrSerialSpecialNumberForGrantLotteInvoiceNum(orVO.getOrSerialSpecialNumber());
			
			if(orList != null) {				
				for(int i = 0; i < orList.size(); i++) {
					
					if( temp == orList.get(i).getOrPk()) {
						continue;
						
					}else {
						ohVO = new OrderHistoryVO();
						ohVO.setOrFk(orList.get(i).getOrPk());
						ohVO.setOhIp(ip);
						ohVO.setOhAdmin(adminId);
						ohVO.setOhRegdate(dates);
						ohVO.setOhEndPoint("롯데택배  생성");
						ohVO.setOhDetail("롯데택배 가송장 생성 완료");
						
						int results = logService.insertOrderHistory(ohVO);
						temp = orList.get(i).getOrPk();
						
					}
				}
			}
			
		}
		
		List<String> header = new ArrayList<String>();
		
		
		header.add("받는사람");
		header.add("전화번호1");
		header.add("우편번호");
		header.add("주소");
		header.add("수량");
		header.add("상품명1");
		header.add("주문번호");
		header.add("합포장키");
		header.add("고객메시지");
		header.add("보내는사람(지정)");
		header.add("주소(지정)");
		header.add("전화번호1(지정)");
		header.add("우편번호(지정)");

		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("발송명단");
		
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		
		SXSSFCell cell;
		
		int HeaderCounting = 0;
		// 헤더 정보 구성
		for (HeaderCounting = 0; HeaderCounting < header.size(); HeaderCounting++) {
			cell = (SXSSFCell) row.createCell(HeaderCounting);
			cell.setCellValue(header.get(HeaderCounting));
			sheet.autoSizeColumn(HeaderCounting);
			sheet.setColumnWidth(HeaderCounting, ( sheet.getColumnWidth(0) + 512));

		}
		
		int cellCounting = 1;

		CellStyle cs = workbook.createCellStyle();

		for( OrdersVO sortingOrder : delivTarget) {
			if(sortingOrder.getProductOptionList().get(0).getProdSorting() == 0 && sortingOrder.getProductOptionList().size() > 1) {				
				for( int delivCount = 1; delivCount < sortingOrder.getProductOptionList().size(); delivCount++) {
					if( sortingOrder.getProductOptionList().get(delivCount).getProdSorting() == 0 ) {
						sortingOrder.getProductOptionList().get(0).setProdSorting(1);
						break;
					}
				}
			}
		}
		
		Collections.sort(delivTarget);
		
		for (int i = 0; i < delivTarget.size(); i++) {
			
			String delivMsg = "";
			String doorPass = "";

			int prodSize = delivTarget.get(i).getProductOptionList().size();

			for(int prodCount = 0; prodCount < prodSize; prodCount++) {
				
				ProductOptionVO poVO = delivTarget.get(i).getProductOptionList().get(prodCount);

				if(poVO.getOptionMemo2() != null && !poVO.getOptionMemo2().equals("")) {					
					if(delivMsg.contains(poVO.getOptionMemo2())) {
						continue;
					}else {
						delivMsg+= poVO.getOptionMemo2();
					}
				}
				
				if(poVO.getOptionMemo1() != null && !poVO.getOptionMemo1().equals("")) {						
					if(doorPass.contains(poVO.getOptionMemo1())) {
						continue;
					}else {
						doorPass+= poVO.getOptionMemo1();
					}
				}
				
			}
			
			if(delivMsg.length() > 120) {				
				delivMsg = delivMsg.substring(0, 120);
			}if(doorPass.length() > 120) {
				doorPass = doorPass.substring(0, 120);
			}
			
			for(ProductOptionVO poVO : delivTarget.get(i).getProductOptionList()) {
				
				row = (SXSSFRow) sheet.createRow(cellCounting);
				
				cell = (SXSSFCell) row.createCell(0);
				cell.setCellValue(delivTarget.get(i).getOrReceiverName());
				sheet.autoSizeColumn(0);
				sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 512));
				
				cell = (SXSSFCell) row.createCell(1);
				cell.setCellValue(delivTarget.get(i).getOrReceiverContractNumber1());
				cell.setCellStyle(cs);
				sheet.setColumnWidth(1, ( ( 5 * 380 ) + 512));
				
				
				cell = (SXSSFCell) row.createCell(2);
				cell.setCellValue(delivTarget.get(i).getOrShippingAddressNumber());
				sheet.autoSizeColumn(2);
				sheet.setColumnWidth(2, ( sheet.getColumnWidth(2) + 512));
				
				cell = (SXSSFCell) row.createCell(3);
				cell.setCellValue(delivTarget.get(i).getOrShippingAddress());	
				sheet.autoSizeColumn(3);
				sheet.setColumnWidth(3, ( sheet.getColumnWidth(3) + 512));
				
				cell = (SXSSFCell) row.createCell(4);
				cell.setCellValue(poVO.getAnotherOptionQty());	
				sheet.autoSizeColumn(4);
				sheet.setColumnWidth(4, ( sheet.getColumnWidth(4) + 512));
				

				cell = (SXSSFCell) row.createCell(5);
				cell.setCellValue(poVO.getProductName());
				sheet.autoSizeColumn(5);
				sheet.setColumnWidth(5, ( sheet.getColumnWidth(5) + 512));
				

				cell = (SXSSFCell) row.createCell(6);
				cell.setCellValue(delivTarget.get(i).getOrSerialSpecialNumber());
				sheet.autoSizeColumn(6);
				sheet.setColumnWidth(6, ( sheet.getColumnWidth(6) + 512));
				
				cell = (SXSSFCell) row.createCell(7);
				cell.setCellValue(delivTarget.get(i).getOrSerialSpecialNumber());
				sheet.autoSizeColumn(7);
				sheet.setColumnWidth(7, ( sheet.getColumnWidth(7) + 512));
				
				
				cell = (SXSSFCell) row.createCell(8);
				cell.setCellValue(delivMsg);
				sheet.autoSizeColumn(8);
				sheet.setColumnWidth(8, ( sheet.getColumnWidth(8) + 512));
				
				cell = (SXSSFCell) row.createCell(9);
				cell.setCellValue(delivTarget.get(i).getOrBuyerName());
				sheet.autoSizeColumn(9);
				sheet.setColumnWidth(9, ( sheet.getColumnWidth(9) + 512));
				
				
				cell = (SXSSFCell) row.createCell(10);
				cell.setCellValue("인천광역시 계양구 효서로 388 2층 삼형제고기");
				sheet.autoSizeColumn(10);
				sheet.setColumnWidth(10, ( sheet.getColumnWidth(10) + 512));
				
				cell = (SXSSFCell) row.createCell(11);
				cell.setCellValue("0507-1312-1620");
				sheet.autoSizeColumn(11);
				sheet.setColumnWidth(11, ( sheet.getColumnWidth(11) + 512));
				
				cell = (SXSSFCell) row.createCell(12);
				cell.setCellValue("21126");
				sheet.autoSizeColumn(12);
				sheet.setColumnWidth(12, ( sheet.getColumnWidth(12) + 512));
				
				cellCounting++;
				
				
			}
			
			
			
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "lotte_delivery_upload_file["+sdf.format(new Date())+"].xlsx";
		
		File file = new File(fileProperties.getProperty("file.upload.order_excel.path.test"), fileName);
		
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
	public int grantLotteDeliveryInvoiceNumBySerialSpecialNumber(List<OrdersVO> orderList, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		if(orderList == null) return 0;
		
		if(orderList.size() == 0) return 0;
		
		/*for( OrdersVO orVO : orderList) {
			count+= cjDao.grantCjDeliveryInvoiceNumBySerialSpecialNumber(orVO);
		}*/
		
		int [] result = lotteDao.grantLotteDeliveryInvoiceNumByBatchUpdate(orderList);
		
		for( int i = 0; i < result.length; i++) {
			logger.info("lotte grant invoiceNum result[{}], result = {}",i,result[i]);
		}
		
		return result[0];
		
	}
	
	
	@Override
	public int insertOrderHistory(List<OrdersVO> orderList, String ip, String adminId) {
		
		int result = 0;
				
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		
		String dates = dateFormat.format(today);
		
		OrderHistoryVO ohVO = null;
		
		int temp = 0;
		String temps = "";
		
		List<OrdersVO> tempList = new ArrayList<OrdersVO>();
		
		for(OrdersVO orVO : orderList) {
			if(orVO.getOrSerialSpecialNumber().equals(temps)) {
				continue;
				
			}else {
				tempList.add(orVO);
				temps = orVO.getOrSerialSpecialNumber();
			}
		}
		
		
		orderList = tempList;
		
		for(OrdersVO orVO : orderList) {
			
			List<OrdersVO> orList = lotteDao.selectOrdersPkByOrSerialSpecialNumberForGrantLotteInvoiceNum(orVO.getOrSerialSpecialNumber());
			
			for(int i = 0; i < orList.size(); i++) {
				
				if( temp == orList.get(i).getOrPk()) {
					continue;
					
				}else {

					ohVO = new OrderHistoryVO();
					ohVO.setOrFk(orList.get(i).getOrPk());
					ohVO.setOhIp(ip);
					ohVO.setOhAdmin(adminId);
					ohVO.setOhRegdate(dates);
					ohVO.setOhEndPoint("롯데택배 생성");
					ohVO.setOhDetail("롯데택배 택배 송장 ( "+orVO.getOrDeliveryInvoiceNumber()+" ) 입력 완료");
					
					result+= logService.insertOrderHistory(ohVO);
					temp = orList.get(i).getOrPk();
					
				}
			}
		}
		
		return result;
	}
	
	
}
