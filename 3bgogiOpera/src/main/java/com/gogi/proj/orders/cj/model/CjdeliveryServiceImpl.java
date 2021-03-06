package com.gogi.proj.orders.cj.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
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

import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.cj.util.CjDeliveryArea;
import com.gogi.proj.orders.cj.util.CjDetailDeliveryAreaCheck;
import com.gogi.proj.orders.cj.util.CjResultMessage;
import com.gogi.proj.orders.cj.util.OrderInfoToCjdelivery;
import com.gogi.proj.orders.model.OrdersDAO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.todayPickup.util.URLUtil;

@Service
public class CjdeliveryServiceImpl implements CjdeliveryService{

	private static final Logger logger = LoggerFactory.getLogger(CjdeliveryServiceImpl.class);
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	@Autowired
	private CjdeliveryDAO cjDao;

	@Autowired
	private OrdersDAO orderDao;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private DeliveryConfigService dcService;
	
	
	/**
	 * method : ???????????? ?????? ?????? ??????
	 * 
	 */
	public boolean isCjDeliveryArea(String zipCode, String addr, String addrDetail) {
        
		URLUtil uUtil = new URLUtil();
		
		OrderInfoToCjdelivery ocjd = new OrderInfoToCjdelivery();
		
		Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("Accept", "*/*");
        
        String address = addr+" "+addrDetail;
        
        if(dcService.isEarlyDelivArea(address, 5)) {
        	
        	if(addr.split(" ")[0].contains("??????") ) {
    			return true;
    			
    		}else if(addr.contains("?????????")) {
    			return true;
    			
    		}else if(addr.contains("?????????")) {
    			return true;
    			
    		}else if(addr.contains("?????????")) {
    			return true;
    			
    		}else if(addr.contains("?????????")) {
    			return true;
    			
    		}else if(addr.contains("?????????")) {
    			return true;
    			
    		}else if(addr.contains("?????????")) {
    			return true;
    			
    		}else if(addr.contains("?????????")) {
    			return true;
    			
    		}else if(addr.contains("?????????")) {
    			return true;
    			
    		}else if(addr.contains("?????????")) {
    			return true;
    			
    		}else {        		
    			//?????? ????????? ?????? ?????? ?????? ??????
    			if( CjDeliveryArea.findDelivPosivArea(addr) == CjDeliveryArea.POSIV) {
    				
    				String parsingString = "";
    				
    				//?????? ??? ?????? ?????? ?????? ??????
    				if(CjDetailDeliveryAreaCheck.hasAreaName(addr) == true) {
    					
    					try {
    						
    						parsingString = uUtil.getCoordiante("https://www.cjthemarket.com/common/address/chkDawnDeliveryAvailable.json?addr="+URLEncoder.encode(addr, "UTF-8")+"&zipCd="+URLEncoder.encode(zipCode,"UTF-8"), requestHeaders, "POST", null);
    						
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						throw new RuntimeException("????????? ??????", e);
    						
    					}
    					
    					CjResultMessage cjMsg = ocjd.stringToCjResultMsg(parsingString);
    					
    					if(cjMsg.getIsDawnAble() == true) {
    						return true;
    					}
    					
    				}
    				
    			}
    		}
        	
        }
        
		return false;
	}
	
	@Override
	public OrderSearchVO selectCjDeliveryTargetChecking(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		
		List<OrdersVO> checkingList = cjDao.selectCjDeliveryTargetChecking(osVO);
		
		List<String> targetList = new ArrayList<String>();

        int checkingListSize = checkingList.size();
        
        for(int i = 0; i < checkingListSize; i++) {

        	if(isCjDeliveryArea(checkingList.get(i).getOrShippingAddressNumber(), checkingList.get(i).getOrShippingAddress(), checkingList.get(i).getOrShippingAddressDetail())) {
        		targetList.add(checkingList.get(i).getOrSerialSpecialNumber());
        	}
        		
		}
        
        if(targetList != null && targetList.size() != 0) {
			osVO.setOrSerialSpecialNumberList(targetList);
			
		}
		
		return osVO;
		
	}

	@Override
	public int selectDontGrantCjDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return cjDao.selectDontGrantCjDelivOrderListInMonthCounting(osVO);
	}

	@Override
	public List<OrdersVOList> selectDontGrantCjDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return cjDao.selectDontGrantCjDelivOrderListInMonth(osVO);
	}

	@Transactional
	@Override
	public File cjDeliveryExcelInfo(OrderSearchVO osVO, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		
		String dates = dateFormat.format(today);
		
		// cnplus??????????????? ?????? ????????? ?????? ??????
		List<OrdersVO> delivTarget = cjDao.selectCjDeliveryExcelTarget(osVO);
		
		if(delivTarget == null) {
			logger.info("cj delivery delivTarget is null, return excel files");
			return null;
		}
		
		
		if(delivTarget.size() == 0) {
			logger.info("cj delivery delivTarget is empty, return excel files");
			return null;
		}
		
		int result = cjDao.updateCjDeliveryTargetBeforeGrantInvoiceNum(delivTarget);

		OrderHistoryVO ohVO = null;
		
		int temp = 0;
		
		List<OrdersVO> orList = null; 
		
		
		for( OrdersVO orVO : delivTarget) {
			
			orList = cjDao.selectOrdersPkByOrSerialSpecialNumberForGrantCjInvoiceNum(orVO.getOrSerialSpecialNumber());
			
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
						ohVO.setOhEndPoint("cj ???????????? ??????");
						ohVO.setOhDetail("cj ???????????? ????????? ?????? ??????");
						
						int results = logService.insertOrderHistory(ohVO);
						temp = orList.get(i).getOrPk();
						
					}
				}
			}
			
		}
		
		List<String> header = new ArrayList<String>();
		
		
		header.add("??????10");
		header.add("???????????????");
		header.add("?????????????????????");
		header.add("???????????????(??????,??????)");
		header.add("?????????");
		header.add("???????????????1");
		header.add("??????9");
		header.add("??????????????????");

		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("????????????");
		
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		
		SXSSFCell cell;
		
		int HeaderCounting = 0;
		// ?????? ?????? ??????
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
			String delivMsgTemp = "";
			String doorPass = "";
			String doorPassTemp = "";
			
			for(ProductOptionVO poVO : delivTarget.get(i).getProductOptionList()) {

				if(poVO.getOptionMemo2() != null && !poVO.getOptionMemo2().equals("")) {					
					if(delivMsgTemp.indexOf(poVO.getOptionMemo2()) != -1 ) {
						
					}else {
						delivMsg+= poVO.getOptionMemo2();
						delivMsgTemp = poVO.getOptionMemo2();
					}
				}
				
				if(poVO.getOptionMemo1() != null && !poVO.getOptionMemo1().equals("")) {						
					if(doorPassTemp.indexOf(poVO.getOptionMemo1()) != -1) {

						
					}else {

						doorPass+= poVO.getOptionMemo1();
						doorPassTemp = poVO.getOptionMemo1();
					}
				}else {

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
				cell.setCellValue(doorPass);
				sheet.autoSizeColumn(0);
				sheet.setColumnWidth(0, ( sheet.getColumnWidth(0) + 512));
				
				cell = (SXSSFCell) row.createCell(1);
				cell.setCellValue(delivTarget.get(i).getOrReceiverName());
				cell.setCellStyle(cs);
				
				if( delivTarget.get(i).getOrReceiverName().length() < 6) {
					sheet.setColumnWidth(1, ( ( 5 * 380 ) + 512));
				}else {
					sheet.autoSizeColumn(1);
					sheet.setColumnWidth(1, ( ( delivTarget.get(i).getOrReceiverName().length() * 380 ) + 512));
				}
				
				
				cell = (SXSSFCell) row.createCell(2);
				cell.setCellValue(delivTarget.get(i).getOrReceiverContractNumber1());
				sheet.autoSizeColumn(2);
				sheet.setColumnWidth(2, ( sheet.getColumnWidth(2) + 512));
				
				cell = (SXSSFCell) row.createCell(3);
				cell.setCellValue(delivTarget.get(i).getOrShippingAddress());	

				cell = (SXSSFCell) row.createCell(4);
				cell.setCellValue(poVO.getProductName());
				sheet.autoSizeColumn(4);
				sheet.setColumnWidth(4, ( sheet.getColumnWidth(4) + 512));
				
				cell = (SXSSFCell) row.createCell(5);
				if(!doorPass.equals("")) {
					cell.setCellValue("(???????????? : "+doorPass+")"+delivMsg);
					
				}else {
					cell.setCellValue(delivMsg);
					
				}
				sheet.setColumnWidth(5, ( ( delivMsg.length() * 380 ) + 512));

				cell = (SXSSFCell) row.createCell(6);
				cell.setCellValue(1);
				sheet.autoSizeColumn(6);
				sheet.setColumnWidth(6, ( sheet.getColumnWidth(6) + 512));
				
				cell = (SXSSFCell) row.createCell(7);
				cell.setCellValue(delivTarget.get(i).getOrSerialSpecialNumber());
				sheet.autoSizeColumn(7);
				sheet.setColumnWidth(7, ( sheet.getColumnWidth(7) + 512));
				
				cellCounting++;
				
				
			}
			
			
			
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "cj_delivery_upload_file["+sdf.format(new Date())+"].xlsx";
		
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
	public int grantCjDeliveryInvoiceNumBySerialSpecialNumber(List<OrdersVO> orderList, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		if(orderList == null) return 0;
		
		if(orderList.size() == 0) return 0;
		
		/*for( OrdersVO orVO : orderList) {
			count+= cjDao.grantCjDeliveryInvoiceNumBySerialSpecialNumber(orVO);
		}*/
		
		int [] result = cjDao.grantCjDeliveryInvoiceNumByBatchUpdate(orderList);
		
		if(result == null) return 0;
		
		for( int i = 0; i < result.length; i++) {
			logger.info("cjDao grant invoiceNum result[{}], result = {}",i,result[i]);
		}
		
		int logResult = insertOrderHistory(orderList, ip, adminId);
		
		if(logResult == 0) return 0;
		
		return result[0];
	}
	
	@Transactional
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
			
			List<OrdersVO> orList = cjDao.selectOrdersPkByOrSerialSpecialNumberForGrantCjInvoiceNum(orVO.getOrSerialSpecialNumber());
			
			for(int i = 0; i < orList.size(); i++) {
				
				if( temp == orList.get(i).getOrPk()) {
					continue;
					
				}else {
					ohVO = new OrderHistoryVO();
					ohVO.setOrFk(orList.get(i).getOrPk());
					ohVO.setOhIp(ip);
					ohVO.setOhAdmin(adminId);
					ohVO.setOhRegdate(dates);
					ohVO.setOhEndPoint("cj ???????????? ?????? ??????");
					ohVO.setOhDetail("cj ???????????? ?????? ??????  ( "+orVO.getOrDeliveryInvoiceNumber()+" ) ?????? ??????");
					
					result+= logService.insertOrderHistory(ohVO);
					temp = orList.get(i).getOrPk();
					
				}
			}
		}
		
		return result;
	}
	
	
}
