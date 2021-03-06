package com.gogi.proj.freshsolutions.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.freshsolutions.util.FreshSolutionsDeliveryUtil;
import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.cj.model.CjdeliveryDAO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

@Service
public class FreshSolutionsServiceImpl implements FreshSolutionsService {

	private static final Logger logger = LoggerFactory.getLogger(FreshSolutionsServiceImpl.class);

	@Resource(name = "fileUploadProperties")
	private Properties fileProperties;

	@Autowired
	private FreshSolutionsDAO freshSolutionsDao;

	@Autowired
	private FreshSolutionsDeliveryUtil freshSolutionsDeliveryUtil;

	@Autowired
	private LogService logService;

	@Autowired
	private DeliveryConfigService dcService;
	
	@Autowired
	private CjdeliveryDAO cjDeliveryDao;

	public boolean isFreshSolutionsDeliveryArea(String addr, String addrDetail, int delivType) {

		String fullAddr = addr + " " + addrDetail;

		boolean result = freshSolutionsDeliveryUtil.isFreshSolutionsDeliveryAreaByRestAPI(addr, addrDetail, delivType);

		if (result) {

			if (dcService.isEarlyDelivArea(fullAddr, 3)) {

				return true;
			} else {

				return false;
			}

		} else {
			return false;
		}

	}

	@Override
	public OrderSearchVO selectFreshSolutionsDeliveryTargetChecking(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		List<OrdersVO> checkingList = freshSolutionsDao.selectFreshSolutionsDeliveryTargetChecking(osVO);

		List<String> targetList = new ArrayList<String>();

		int checkingListSize = checkingList.size();

		for (int i = 0; i < checkingListSize; i++) {

			if (isFreshSolutionsDeliveryArea(checkingList.get(i).getOrShippingAddress(), checkingList.get(i).getOrShippingAddressDetail(), osVO.getSearchAddType())) {
				targetList.add(checkingList.get(i).getOrSerialSpecialNumber());
			}

		}

		if (targetList != null && targetList.size() != 0) {
			osVO.setOrSerialSpecialNumberList(targetList);

		}

		return osVO;
	}

	@Override
	public int selectDontGrantFreshSolutionsDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return freshSolutionsDao.selectDontGrantFreshSolutionsDelivOrderListInMonthCounting(osVO);
	}

	@Override
	public List<OrdersVOList> selectDontGrantFreshSolutionsDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return freshSolutionsDao.selectDontGrantFreshSolutionsDelivOrderListInMonth(osVO);
	}

	@Override
	public File fFreshSolutionsDeliveryExcelInfo(OrderSearchVO osVO, String ip, String adminId) {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");

		Date today = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);

		String dates = dateFormat.format(today);
		Date tomorrow = calendar.getTime();

		// cnplus??????????????? ?????? ????????? ?????? ??????
		List<OrdersVO> delivTarget = freshSolutionsDao.selectFreshSolutionsDeliveryExcelTarget(osVO);

		if (delivTarget == null) {
			logger.info("freshSolutions delivery delivTarget is null, return excel files");
			return null;
		}

		if (delivTarget.size() == 0) {
			logger.info("freshSolutions delivery delivTarget is empty, return excel files");
			return null;
		}

		int result = freshSolutionsDao.updateFreshSolutionsDeliveryTargetBeforeGrantInvoiceNum(delivTarget);

		OrderHistoryVO ohVO = null;

		int temp = 0;

		List<OrdersVO> orList = null;

		for (OrdersVO orVO : delivTarget) {

			orList = freshSolutionsDao.selectOrdersPkByOrSerialSpecialNumberForGrantFreshSolutionsInvoiceNum(
					orVO.getOrSerialSpecialNumber());

			if (orList != null) {
				for (int i = 0; i < orList.size(); i++) {

					if (temp == orList.get(i).getOrPk()) {
						continue;

					} else {
						ohVO = new OrderHistoryVO();
						ohVO.setOrFk(orList.get(i).getOrPk());
						ohVO.setOhIp(ip);
						ohVO.setOhAdmin(adminId);
						ohVO.setOhRegdate(dates);
						ohVO.setOhEndPoint("?????????????????? ???????????? ??????");
						ohVO.setOhDetail("?????????????????? ???????????? ????????? ?????? ??????");

						int results = logService.insertOrderHistory(ohVO);
						temp = orList.get(i).getOrPk();

					}
				}
			}

		}

		List<String> header = new ArrayList<String>();

		header.add("????????? ????????????");
		header.add("???????????????");
		header.add("?????????");
		header.add("?????????");
		header.add("????????????");
		header.add("????????? ??????");
		header.add("????????? ????????????");
		header.add("????????? ????????????");
		header.add("????????? ?????????");
		header.add("??????");
		header.add("??????2(???????????????)");
		header.add("????????????");
		header.add("??????????????????");
		header.add("????????????");
		header.add("????????????");
		header.add("?????????");
		header.add("????????????");
		header.add("??????");

		SXSSFWorkbook workbook = new SXSSFWorkbook();

		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("?????????????????? ??????");

		SXSSFRow row = (SXSSFRow) sheet.createRow(0);

		SXSSFCell cell;

		int HeaderCounting = 0;
		// ?????? ?????? ??????
		for (HeaderCounting = 0; HeaderCounting < header.size(); HeaderCounting++) {
			cell = (SXSSFCell) row.createCell(HeaderCounting);
			cell.setCellValue(header.get(HeaderCounting));
			sheet.autoSizeColumn(HeaderCounting);
			sheet.setColumnWidth(HeaderCounting, (sheet.getColumnWidth(0) + 512));

		}

		int cellCounting = 1;

		CellStyle cs = workbook.createCellStyle();

		for (OrdersVO sortingOrder : delivTarget) {
			if (sortingOrder.getProductOptionList().get(0).getProdSorting() == 0
					&& sortingOrder.getProductOptionList().size() > 1) {
				for (int delivCount = 1; delivCount < sortingOrder.getProductOptionList().size(); delivCount++) {
					if (sortingOrder.getProductOptionList().get(delivCount).getProdSorting() == 0) {
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

			for (ProductOptionVO poVO : delivTarget.get(i).getProductOptionList()) {

				if (poVO.getOptionMemo2() != null && !poVO.getOptionMemo2().equals("")) {
					if (delivMsgTemp.indexOf(poVO.getOptionMemo2()) != -1) {

					} else {
						delivMsg += poVO.getOptionMemo2();
						delivMsgTemp = poVO.getOptionMemo2();
					}
				}

				if (poVO.getOptionMemo1() != null && !poVO.getOptionMemo1().equals("")) {
					if (doorPassTemp.indexOf(poVO.getOptionMemo1()) != -1) {

					} else {

						doorPass += poVO.getOptionMemo1();
						doorPassTemp = poVO.getOptionMemo1();
					}
				} else {

				}

			}

			if (delivMsg.length() > 120) {
				delivMsg = delivMsg.substring(0, 120);
			}
			if (doorPass.length() > 120) {
				doorPass = doorPass.substring(0, 120);
			}

			for (ProductOptionVO poVO : delivTarget.get(i).getProductOptionList()) {

				row = (SXSSFRow) sheet.createRow(cellCounting);

				cell = (SXSSFCell) row.createCell(0);
				cell.setCellValue(delivTarget.get(i).getOrSerialSpecialNumber());

				cell = (SXSSFCell) row.createCell(1);
				cell.setCellValue(yMd.format(tomorrow));
				cell.setCellStyle(cs);

				cell = (SXSSFCell) row.createCell(2);
				if (delivTarget.get(i).getOrBuyerAnotherName() != null
						&& !delivTarget.get(i).getOrBuyerAnotherName().equals("")) {
					cell.setCellValue(delivTarget.get(i).getOrBuyerAnotherName());
				} else {
					cell.setCellValue(delivTarget.get(i).getOrBuyerName());
				}

				cell = (SXSSFCell) row.createCell(3);
				cell.setCellValue(delivTarget.get(i).getOrReceiverName());

				cell = (SXSSFCell) row.createCell(4);
				cell.setCellValue(delivTarget.get(i).getOrShippingAddressNumber());

				/*
				 * cell = (SXSSFCell) row.createCell(5); if(!doorPass.equals("")) {
				 * cell.setCellValue("(???????????? : "+doorPass+")"+delivMsg);
				 * 
				 * }else { cell.setCellValue(delivMsg);
				 * 
				 * } sheet.setColumnWidth(5, ( ( delivMsg.length() * 380 ) + 512));
				 */

				cell = (SXSSFCell) row.createCell(5);
				cell.setCellValue(delivTarget.get(i).getOrShippingAddress());

				cell = (SXSSFCell) row.createCell(6);
				cell.setCellValue(delivTarget.get(i).getOrShippingAddressDetail());
				
				cell = (SXSSFCell) row.createCell(7);
				cell.setCellValue(delivTarget.get(i).getOrReceiverContractNumber2());

				cell = (SXSSFCell) row.createCell(8);
				cell.setCellValue(delivTarget.get(i).getOrReceiverContractNumber1());

				cell = (SXSSFCell) row.createCell(9);
				cell.setCellValue("");

				cell = (SXSSFCell) row.createCell(10);
				if (!doorPass.equals("")) {
					cell.setCellValue("(???????????? : " + doorPass + ")" + delivMsg);

				} else {
					cell.setCellValue(delivMsg);

				}

				cell = (SXSSFCell) row.createCell(11);
				cell.setCellValue("????????????");

				cell = (SXSSFCell) row.createCell(12);
				cell.setCellValue("????????????");

				cell = (SXSSFCell) row.createCell(13);
				cell.setCellValue(poVO.getAnotherOptionPk());

				cell = (SXSSFCell) row.createCell(14);
				cell.setCellValue("??????");

				cell = (SXSSFCell) row.createCell(15);
				cell.setCellValue(poVO.getProductName());

				cell = (SXSSFCell) row.createCell(16);
				cell.setCellValue("");

				cell = (SXSSFCell) row.createCell(17);
				cell.setCellValue(poVO.getAnotherOptionQty());

				cellCounting++;

			}

		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "freshsolutions_delivery_upload_file[" + sdf.format(new Date()) + "].xlsx";

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

				if (fos != null)
					fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return file;
	}

	@Override
	public int grantFreshSolutionsDeliveryInvoiceNumBySerialSpecialNumber(List<OrdersVO> orderList, String ip,
			String adminId) {
		// TODO Auto-generated method stub
		if (orderList == null)
			return 0;

		if (orderList.size() == 0)
			return 0;

		/*
		 * for( OrdersVO orVO : orderList) { count+=
		 * cjDao.grantCjDeliveryInvoiceNumBySerialSpecialNumber(orVO); }
		 */

		//int[] result = freshSolutionsDao.grantFreshSolutionsDeliveryInvoiceNumByBatchUpdate(orderList);

		int result = 0;

		for (int i = 0; i < orderList.size(); i++) {
			
			// ?????????????????? ??????????????? ??????
			if(orderList.get(i).getOrDeliveryInvoiceNumber().length() > 14) {
				result += freshSolutionsDao.grantFreshSolutionsDeliveryInvoiceNumBySerialSpecialNumber(orderList.get(i));
			}else {
				result += cjDeliveryDao.grantCjDeliveryInvoiceNumBySerialSpecialNumber(orderList.get(i));
			}
		}

		int logResult = insertOrderHistory(orderList, ip, adminId, 0);

		if (logResult == 0)
			return 0;

		return result;
	}

	@Transactional
	@Override
	public int insertOrderHistory(List<OrdersVO> orderList, String ip, String adminId, int delivType) {
		// TODO Auto-generated method stub
		int result = 0;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();

		String dates = dateFormat.format(today);

		OrderHistoryVO ohVO = null;

		int temp = 0;

		String temps = "";

		List<OrdersVO> tempList = new ArrayList<OrdersVO>();

		for (OrdersVO orVO : orderList) {
			if (orVO.getOrSerialSpecialNumber().equals(temps)) {
				continue;

			} else {
				tempList.add(orVO);
				temps = orVO.getOrSerialSpecialNumber();
			}
		}

		orderList = tempList;

		for (OrdersVO orVO : orderList) {

			List<OrdersVO> orList = freshSolutionsDao.selectOrdersPkByOrSerialSpecialNumberForGrantFreshSolutionsInvoiceNum(orVO.getOrSerialSpecialNumber());

			for (int i = 0; i < orList.size(); i++) {

				if (temp == orList.get(i).getOrPk()) {
					continue;

				} else {
					ohVO = new OrderHistoryVO();
					ohVO.setOrFk(orList.get(i).getOrPk());
					ohVO.setOhIp(ip);
					ohVO.setOhAdmin(adminId);
					ohVO.setOhRegdate(dates);
					
					if(orVO.getOrDeliveryInvoiceNumber().length() > 14) {
						ohVO.setOhEndPoint("??????????????????  ?????? ??????");
						ohVO.setOhDetail("?????????????????? ?????? ??????  ( " + orVO.getOrDeliveryInvoiceNumber() + " ) ?????? ??????");
						
					}else {
						ohVO.setOhEndPoint("?????????????????? => cj ?????? ??????");
						ohVO.setOhDetail("cj ?????? ?????? ??????  ( " + orVO.getOrDeliveryInvoiceNumber() + " ) ?????? ??????");
						
					}
					

					result += logService.insertOrderHistory(ohVO);
					temp = orList.get(i).getOrPk();

				}
			}
		}

		return result;
	}

	@Override
	@Transactional
	public String fFreshSolutionsDeliveryAutoUpload(OrderSearchVO osVO, String ip, String adminId, int delivType) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");

		String resResult = "";
		
		Date today = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);

		String dates = dateFormat.format(today);
		Date tomorrow = calendar.getTime();

		List<OrdersVO> delivTarget = freshSolutionsDao.selectFreshSolutionsDeliveryExcelTarget(osVO);

		OrderHistoryVO ohVO = null;

		int temp = 0;

		for (OrdersVO sortingOrder : delivTarget) {
			if (sortingOrder.getProductOptionList().get(0).getProdSorting() == 0
					&& sortingOrder.getProductOptionList().size() > 1) {
				for (int delivCount = 1; delivCount < sortingOrder.getProductOptionList().size(); delivCount++) {
					if (sortingOrder.getProductOptionList().get(delivCount).getProdSorting() == 0) {
						sortingOrder.getProductOptionList().get(0).setProdSorting(1);
						break;
					}
				}
			}
		}

		Collections.sort(delivTarget);

		for (int i = 0; i < delivTarget.size(); i++) {
			
			resResult = "";
			
			String delivMsg = "";
			String delivMsgTemp = "";
			String doorPass = "";
			String doorPassTemp = "";

			for (ProductOptionVO poVO : delivTarget.get(i).getProductOptionList()) {

				if (poVO.getOptionMemo2() != null && !poVO.getOptionMemo2().equals("")) {
					if (delivMsgTemp.indexOf(poVO.getOptionMemo2()) != -1) {

					} else {
						delivMsg += poVO.getOptionMemo2();
						delivMsgTemp = poVO.getOptionMemo2();
					}
				}

				if (poVO.getOptionMemo1() != null && !poVO.getOptionMemo1().equals("")) {
					if (doorPassTemp.indexOf(poVO.getOptionMemo1()) != -1) {

					} else {

						doorPass += poVO.getOptionMemo1();
						doorPassTemp = poVO.getOptionMemo1();
					}
				} else {

				}

			}

			int uploadResult = 0;
			
			if(delivType == 0) {
				resResult = freshSolutionsDeliveryUtil.uploadOrderDataForFreshSolutions(delivTarget.get(i), delivMsg, doorPass);
				
				if(resResult.contains("?????????????????????")) uploadResult++;
				
			}else {
				resResult = freshSolutionsDeliveryUtil.uploadOrderDataForFreshSolutionsForDay(delivTarget.get(i), delivMsg, doorPass);
				
				if(resResult.contains("?????????????????????")) uploadResult++;
				
			}

			if(uploadResult == 0) {
				delivTarget.remove(i);
			}
				
			if(uploadResult != 0) {
				
				List<OrdersVO> orList = null;
				int result = freshSolutionsDao.updateFreshSolutionsDeliveryTargetBeforeGrantInvoiceNum(delivTarget);
				
				for (OrdersVO orVO : delivTarget) {

					orList = freshSolutionsDao.selectOrdersPkByOrSerialSpecialNumberForGrantFreshSolutionsInvoiceNum(
							orVO.getOrSerialSpecialNumber());

					if (orList != null) {
						for (int orderCount = 0; orderCount < orList.size(); orderCount++) {

							if (temp == orList.get(orderCount).getOrPk()) {
								continue;

							} else {
								ohVO = new OrderHistoryVO();
								ohVO.setOrFk(orList.get(orderCount).getOrPk());
								ohVO.setOhIp(ip);
								ohVO.setOhAdmin(adminId);
								ohVO.setOhRegdate(dates);
								ohVO.setOhEndPoint("?????????????????? ?????? ??????");
								ohVO.setOhDetail("?????????????????? ????????? ?????? ??????");

								int results = logService.insertOrderHistory(ohVO);
								temp = orList.get(orderCount).getOrPk();

							}
						}
					}

				}
				
			}
			
			//

		}

		return resResult;
	}
}
