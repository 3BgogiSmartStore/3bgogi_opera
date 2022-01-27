package com.gogi.proj.orders.config.util;

import java.io.FileInputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gogi.proj.commission.SmartstoreCommission;
import com.gogi.proj.kakao.KakaoAPI;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.util.FileuploadUtil;
import com.gogi.proj.util.PhoneNumberUtil;

@Component
public class ExcelDevideUtil {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private KakaoAPI kakaoApi;
	
	@Autowired
	private PhoneNumberUtil pnUtil;

	@Autowired
	private FileuploadUtil fileUtil;

	public List<OrdersVO> readGiftSetExcelFile(String fileName, OrdersVO originalOrVO) throws POIXMLException {

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("Authorization", "KakaoAK bcbba5428da89430f8eb29996ec26f2a");
        
		kakaoApi = new KakaoAPI();
		
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();

		String ext = fileUtil.getExtType(fileName);

		SmartstoreCommission sc = new SmartstoreCommission();

		Calendar cal = Calendar.getInstance();

		Timestamp ts = new Timestamp(cal.getTimeInMillis());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int orderCounting = 0;

		String upPath = fileUtil.getUploadPath(FileuploadUtil.ORDER_EXCEL, false);

		try {

			FileInputStream fis = new FileInputStream(upPath + "\\" + fileName);

			// 엑셀파일 확장자가 xlsx 일 경우
			// 분기별로 처리해서 해야함
			if (ext.equals(".xlsx")) {
				XSSFWorkbook workbook = new XSSFWorkbook(fis);

				FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

				int rowStartIndex = 1;

				int columnindex = 0;

				XSSFSheet sheet = workbook.getSheetAt(0);

				int rows = sheet.getPhysicalNumberOfRows();

				OrdersVO orderVO;

				for (int rowindex = rowStartIndex; rowindex < rows; rowindex++) {

					XSSFRow row = sheet.getRow(rowindex);

					orderVO = new OrdersVO();

					if (row != null && !row.getCell(0).getStringCellValue().equals("false") && !row.getCell(0).getStringCellValue().equals("")) {
						boolean nullCount = false;
						orderVO = originalOrVO.copy();

						for (columnindex = 0; columnindex < 11; columnindex++) {

							XSSFCell cell = row.getCell(columnindex);
							
							// 판매처별로 엑셀 열을 읽어서 씀
							// 구매자명
							if (cell == null && (columnindex == 1 || columnindex == 9)) {
								if (columnindex == 1) {
									nullCount = true;
									break;
								} else if (columnindex == 9) {
									orderVO.setOrDeliveryMessage("");
								}

								continue;

							} else if (cell != null && columnindex == 10) {
								switch (cell.getCellType()) {
								case HSSFCell.CELL_TYPE_NUMERIC:

									if (HSSFDateUtil.isCellDateFormatted(cell)) {

										Date userDate = new Date(cell.getDateCellValue().getTime());
										Calendar minoDate = Calendar.getInstance();
										minoDate.setTime(userDate);

										minoDate.add(Calendar.DATE, -1);

										orderVO.setOrSendingDeadline(new Date(minoDate.getTimeInMillis()));

									}

									break;
								default:

									String value = cell.getStringCellValue() + "";

									if (!value.equals("false") && !value.equals("")) {
										SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
										Date userDate = new Date(df.parse(value).getTime());
										Calendar minoDate = Calendar.getInstance();
										cal.setTime(userDate);

										cal.add(Calendar.DATE, -1);

										orderVO.setOrSendingDeadline(new Date(minoDate.getTimeInMillis()));
									}
								}

							} else {

								if (columnindex == 0) {
									if (cell.getStringCellValue() == null || cell.getStringCellValue().equals("")) {
									} else {
										String value = cellTypeReturn(cell);

										orderVO.setOrBuyerAnotherName(value);
									}

								}if (columnindex == 1) {
									String value = cellTypeReturn(cell);

									orderVO.setOrReceiverName(value);

								}if (columnindex == 2) {

									String value = (cellTypeReturn(cell) + "").replaceAll(" ", "");

									if( value.equals("") || value.equals("false")) {
										
										String addr = (cellTypeReturn(row.getCell(3)) + "");
										
										int subNum = addr.lastIndexOf("(");

										if (subNum > 0) {

											String subStr = addr.substring(subNum, addr.length());

											orderVO.setOrShippingAddress(addr.substring(0, subNum));
											orderVO.setOrShippingAddressDetail(subStr);

										} else {

											orderVO.setOrShippingAddress(addr);
											orderVO.setOrShippingAddressDetail("");

										}
										
										value = kakaoApi.searchRegionZipCode(orderVO.getOrShippingAddress(), requestHeaders);
										
									}
									
									if (value.length() < 5) {
										value = "0" + value;
									}

									orderVO.setOrShippingAddressNumber(value);

								}if (columnindex == 3) {

									String value = cellTypeReturn(cell);

									int subNum = value.lastIndexOf("(");

									if (subNum > 0) {

										String subStr = value.substring(subNum, value.length());

										orderVO.setOrShippingAddress(value.substring(0, subNum));
										orderVO.setOrShippingAddressDetail(subStr);
									} else {

										orderVO.setOrShippingAddress(value);
										orderVO.setOrShippingAddressDetail("");
									}

								}if (columnindex == 4) {
									String value = cellTypeReturn(cell);

									value = pnUtil.phoneNumberHyphenAdd(value, "N");

									if (value.substring(0, 2).equals("10")) {
										value = "0" + value;
									}

									orderVO.setOrReceiverContractNumber1(value);

								}if (columnindex == 5) {
									String value = cellTypeReturn(cell);

									if (value.substring(0, 2).equals("10")) {
										value = "0" + value;
									}

									value = pnUtil.phoneNumberHyphenAdd(value, "N");

									orderVO.setOrReceiverContractNumber2(value);
								}if (columnindex == 6) {

									orderVO.setOrAmount((int) cell.getNumericCellValue());

								}if (columnindex == 7) {
									String value = cellTypeReturn(cell);

									orderVO.setOrProduct(value);

								}if (columnindex == 8) {
									String value = "";

									if (cell == null) {
										orderVO.setOrProductOption("단일상품");

									} else {

										switch (cell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA:
											value = cell.getCellFormula();
											break;
										case HSSFCell.CELL_TYPE_NUMERIC:
											value = (int) cell.getNumericCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_STRING:
											value = cell.getStringCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK:
											value = cell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_ERROR:
											value = cell.getErrorCellValue() + "";
											break;
										}

										int firstIndex = value.lastIndexOf("[");
										int lastIndex = value.lastIndexOf("]");

										if (firstIndex != -1 && lastIndex != -1 && value.length() != 0) {
											lastIndex = lastIndex + 1;

											orderVO.setOrProductOption(
													"배송메세지에" + value.substring(lastIndex, value.length()));

										} else {
											orderVO.setOrProductOption(value);
										}

									}

								}if (columnindex == 9) {
									String value = cellTypeReturn(cell) + "";

									if (!value.equals("false") && !value.equals("")) {
										orderVO.setOrDeliveryMessage(value);

									} else {
										orderVO.setOrDeliveryMessage("");
									}

								}
							}

						} // for
						
						orderCounting++;

						if (nullCount == false)
							orderList.add(orderVO);
					}

				} // for

				// 엑셀파일 확장자가 xls일 경우
			} else if (ext.equals(".xls")) {
				HSSFWorkbook workbook = new HSSFWorkbook(fis);

				FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

				int rowStartIndex = 1;

				int columnindex = 0;

				HSSFSheet sheet = workbook.getSheetAt(0);

				int rows = sheet.getPhysicalNumberOfRows();

				OrdersVO orderVO;

				for (int rowindex = rowStartIndex; rowindex < rows; rowindex++) {

					HSSFRow row = sheet.getRow(rowindex);

					orderVO = new OrdersVO();

					if (row != null && !row.getCell(0).getStringCellValue().equals("false")
							&& !row.getCell(0).getStringCellValue().equals("")) {
						boolean nullCount = false;
						orderVO = originalOrVO.copy();

						for (columnindex = 0; columnindex < 11; columnindex++) {

							HSSFCell cell = row.getCell(columnindex);
							// 판매처별로 엑셀 열을 읽어서 씀

							// 구매자명
							if (cell == null && (columnindex == 1 || columnindex == 9)) {

								if (columnindex == 1) {
									nullCount = true;
									break;
								} else if (columnindex == 9) {
									orderVO.setOrDeliveryMessage("");
								}

								continue;

							} else if (cell != null && columnindex == 10) {
								switch (cell.getCellType()) {
								case HSSFCell.CELL_TYPE_NUMERIC:

									if (HSSFDateUtil.isCellDateFormatted(cell)) {

										Date userDate = new Date(cell.getDateCellValue().getTime());
										Calendar minoDate = Calendar.getInstance();
										minoDate.setTime(userDate);

										minoDate.add(Calendar.DATE, -1);

										orderVO.setOrSendingDeadline(new Date(minoDate.getTimeInMillis()));

									}

									break;
								default:

									String value = cell.getStringCellValue() + "";

									if (!value.equals("false") && !value.equals("")) {
										SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
										Date userDate = new Date(df.parse(value).getTime());
										Calendar minoDate = Calendar.getInstance();
										cal.setTime(userDate);

										cal.add(Calendar.DATE, -1);

										orderVO.setOrSendingDeadline(new Date(minoDate.getTimeInMillis()));
									}
								}

							} else {

								if (columnindex == 0) {
									if (cell.getStringCellValue() == null || cell.getStringCellValue().equals("")) {
									} else {
										String value = cellTypeReturnHSS(cell);

										orderVO.setOrBuyerAnotherName(value);
									}

								}if (columnindex == 1) {
									String value = cellTypeReturnHSS(cell);

									orderVO.setOrReceiverName(value);

								}if (columnindex == 2) {

									String value = (cellTypeReturnHSS(cell) + "").replaceAll(" ", "");
									
									if( value.equals("") || value.equals("false")) {
										
										String addr = (cellTypeReturnHSS(row.getCell(3)) + "");
										
										int subNum = addr.lastIndexOf("(");

										if (subNum > 0) {

											String subStr = addr.substring(subNum, addr.length());

											orderVO.setOrShippingAddress(addr.substring(0, subNum));
											orderVO.setOrShippingAddressDetail(subStr);
										} else {

											orderVO.setOrShippingAddress(addr);
											orderVO.setOrShippingAddressDetail("");
										}
										
										value = kakaoApi.searchRegionZipCode(orderVO.getOrShippingAddress(), requestHeaders);
									}
									
									if (value.length() < 5) {
										value = "0" + value;
									}

									orderVO.setOrShippingAddressNumber(value);

								}if (columnindex == 3) {

									String value = cellTypeReturnHSS(cell);

									int subNum = value.lastIndexOf("(");

									if (subNum > 0) {

										String subStr = value.substring(subNum, value.length());

										orderVO.setOrShippingAddress(value.substring(0, subNum));
										orderVO.setOrShippingAddressDetail(subStr);
									} else {

										orderVO.setOrShippingAddress(value);
										orderVO.setOrShippingAddressDetail("");
									}

								}if (columnindex == 4) {
									String value = cellTypeReturnHSS(cell);

									if (value.substring(0, 2).equals("10")) {
										value = "0" + value;
									}
									value = pnUtil.phoneNumberHyphenAdd(value, "N");

									orderVO.setOrReceiverContractNumber1(value);

								}if (columnindex == 5) {
									String value = cellTypeReturnHSS(cell);

									if (value.substring(0, 2).equals("10")) {
										value = "0" + value;
									}

									value = pnUtil.phoneNumberHyphenAdd(value, "N");

									orderVO.setOrReceiverContractNumber2(value);

								}if (columnindex == 6) {
									orderVO.setOrAmount((int) cell.getNumericCellValue());

								}if (columnindex == 7) {
									String value = cellTypeReturnHSS(cell);

									orderVO.setOrProduct(value);

								}if (columnindex == 8) {
									String value = "";

									if (cell == null) {
										orderVO.setOrProductOption("단일상품");

									} else {

										switch (cell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA:
											value = cell.getCellFormula();
											break;
										case HSSFCell.CELL_TYPE_NUMERIC:
											value = (int) cell.getNumericCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_STRING:
											value = cell.getStringCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK:
											value = cell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_ERROR:
											value = cell.getErrorCellValue() + "";
											break;
										}

										int firstIndex = value.lastIndexOf("[");
										int lastIndex = value.lastIndexOf("]");

										if (firstIndex != -1 && lastIndex != -1 && value.length() != 0) {
											lastIndex = lastIndex + 1;

											orderVO.setOrProductOption(
													"배송메세지에" + value.substring(lastIndex, value.length()));

										} else {
											orderVO.setOrProductOption(value);
										}

									}

								}if (columnindex == 9) {
									String value = "";

									switch (cell.getCellType()) {
									case HSSFCell.CELL_TYPE_FORMULA:
										value = cell.getCellFormula();
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										value = (int) cell.getNumericCellValue() + "";
										break;
									case HSSFCell.CELL_TYPE_STRING:
										value = cell.getStringCellValue() + "";
										break;
									case HSSFCell.CELL_TYPE_BLANK:
										value = "";
										break;
									case HSSFCell.CELL_TYPE_ERROR:
										value = cell.getErrorCellValue() + "";
										break;
									}

									if (!value.equals("false") && !value.equals("")) {
										orderVO.setOrDeliveryMessage(value);

									} else {
										orderVO.setOrDeliveryMessage("");
									}
								}
							}

						} // for

						orderCounting++;

						if (nullCount == false)
							orderList.add(orderVO);
					}

				} // for
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return orderList;
	}

	public String cellTypeReturn(XSSFCell cell) {

		String value = "";
		if (cell == null)
			return "";

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula() + "";
			break;

		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				value = dateFormat.format(cell.getDateCellValue());

			} else {
				value = ((int) cell.getNumericCellValue()) + "";
			}

			break;
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue() + "";
			break;

		case HSSFCell.CELL_TYPE_BLANK:
			value = cell.getBooleanCellValue() + "";
			break;

		case HSSFCell.CELL_TYPE_ERROR:
			value = cell.getErrorCellValue() + "";
			break;

		default:
			value = cell.getStringCellValue() + "";
			break;
		}

		return value;
	}

	public String cellTypeReturnSXSS(SXSSFCell cell) {

		String value = "";

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula() + "";
			break;

		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				value = dateFormat.format(cell.getDateCellValue());

			} else {
				value = ((int) cell.getNumericCellValue()) + "";
			}

			break;
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue() + "";
			break;

		case HSSFCell.CELL_TYPE_BLANK:
			value = cell.getBooleanCellValue() + "";
			break;

		case HSSFCell.CELL_TYPE_ERROR:
			value = cell.getErrorCellValue() + "";
			break;

		default:
			value = cell.getStringCellValue() + "";
			break;
		}

		return value;
	}

	public String cellTypeReturnHSS(HSSFCell cell) {

		String value = "";
		if (cell == null)
			return "";

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula() + "";
			break;

		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				value = dateFormat.format(cell.getDateCellValue());

			} else {
				value = ((int) cell.getNumericCellValue()) + "";
			}

			break;
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue() + "";
			break;

		case HSSFCell.CELL_TYPE_BLANK:
			value = cell.getBooleanCellValue() + "";
			break;

		case HSSFCell.CELL_TYPE_ERROR:
			value = cell.getErrorCellValue() + "";
			break;

		default:
			value = cell.getStringCellValue() + "";
			break;
		}

		return value;
	}
	
	
	public List<OrdersVO> addOriginalOrderInfo(OrdersVOList orList, OrdersVO originalOrder){
		
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();
		
			OrdersVO orderVO;
			
			int orderCounting = 0;
			
		for( OrdersVO excelOrderInfo : orList.getOrVoList()) {
			orderVO = new OrdersVO();
			
			try {
				orderVO = originalOrder.copy();
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			orderVO.setOrDeliveryMessage(excelOrderInfo.getOrDeliveryMessage());
			orderVO.setOrSendingDeadline(excelOrderInfo.getOrSendingDeadline());
			orderVO.setOrBuyerAnotherName(excelOrderInfo.getOrBuyerAnotherName());
			orderVO.setOrReceiverName(excelOrderInfo.getOrReceiverName());
			orderVO.setOrShippingAddressNumber(excelOrderInfo.getOrShippingAddressNumber());
			orderVO.setOrShippingAddress(excelOrderInfo.getOrShippingAddress());
			orderVO.setOrShippingAddressDetail(excelOrderInfo.getOrShippingAddressDetail());
			orderVO.setOrReceiverContractNumber1(excelOrderInfo.getOrReceiverContractNumber1());
			orderVO.setOrReceiverContractNumber2(excelOrderInfo.getOrReceiverContractNumber2());
			orderVO.setOrAmount(excelOrderInfo.getOrAmount());
			orderVO.setOrProduct(excelOrderInfo.getOrProduct());
			orderVO.setOrProductOption(excelOrderInfo.getOrProductOption());
			
			orderVO.setOrOrderNumber(originalOrder.getOrOrderNumber()+"-"+orderCounting);
			orderVO.setOrProductOrderNumber(originalOrder.getOrProductOrderNumber()+"-"+orderCounting);
			orderVO.setOrProductPrice(originalOrder.getOrProductPrice()/originalOrder.getOrAmount());
			orderVO.setOrProductOptionPrice(originalOrder.getOrProductOptionPrice()/originalOrder.getOrAmount());
			orderVO.setOrDiscountPrice(originalOrder.getOrDiscountPrice()/originalOrder.getOrAmount());
			orderVO.setOrTotalPrice(originalOrder.getOrTotalPrice()/originalOrder.getOrAmount());
			orderVO.setOrTotalCost(0);
			
			if(orderCounting > 1) {
				orderVO.setOrDeliveryPrice(0);
				orderVO.setOrDeliveryDiscountPrice(0);
			}
			
			orderVO.setOrPaymentCommision(originalOrder.getOrPaymentCommision()/originalOrder.getOrAmount());
			orderVO.setOrAnotherPaymentCommision(originalOrder.getOrAnotherPaymentCommision()/originalOrder.getOrAmount());
			orderVO.setOrFk(originalOrder.getOrPk());
			
			orderList.add(orderVO);
			
			orderCounting++;
			
		}
		
		return orderList;
	}

}
