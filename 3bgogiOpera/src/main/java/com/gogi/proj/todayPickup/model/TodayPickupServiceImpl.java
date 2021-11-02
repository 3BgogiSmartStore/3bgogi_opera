package com.gogi.proj.todayPickup.model;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.epost.model.EpostDAO;
import com.gogi.proj.kakao.KakaoAPI;
import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.todayPickup.util.OrderInfoToTodayPickup;
import com.gogi.proj.todayPickup.util.URLUtil;
import com.gogi.proj.todayPickup.vo.CancleDeliveryVO;
import com.gogi.proj.todayPickup.vo.GoodsListOne;
import com.gogi.proj.todayPickup.vo.GoodsListVO;

@Service
public class TodayPickupServiceImpl implements TodayPickupService{

	private static final Logger logger = LoggerFactory.getLogger(TodayPickupServiceImpl.class);
	

	@Autowired
	private TodayPickupDAO todayPickupDao;
	
	@Autowired
	private EpostDAO epostDao;
	
	@Autowired
	private LogService logService;

	@Override
	public OrdersVO updateTodayPickupInvoiceNumber(OrderSearchVO osVO, String ip, String adminId, String createInvoiceNumDateCounting, String auth, Map<String, String> requestHeaders,  Map<String, String> kakaoRequestHeaders) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		
		String dates = sdf.format(today);
		
		OrderInfoToTodayPickup oitp = new OrderInfoToTodayPickup();
		KakaoAPI kakaoApi = new KakaoAPI();
		
		OrdersVO orderList = epostDao.deliveryPrintTarget(osVO);
		
		URLUtil uUtil = new URLUtil();
		
		String todayPickupJsonString = oitp.todayPickupJsonBody(orderList);
		
		String parsingString = "";
		
		try {
			parsingString = uUtil.getCoordiante("https://mall.todaypickup.com/api/mall/deliveryListRegister", requestHeaders,"POST", todayPickupJsonString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);
			
		}
		
		GoodsListVO gl = oitp.stringToGoodsList(parsingString);
		
		//통신 실패실 경우 
		if(!gl.getSuccess().equals("true")) return null;
		
		OrdersVO orVO = oitp.jsonToOrderInfo(orderList, gl);
		
		if( orVO.getOrDeliveryInvoiceNumber() == null || orVO.getOrDeliveryInvoiceNumber().equals("")) {
			return null;
			
		}
		
		OrderHistoryVO ohVO = null;
		
		orVO.setOrInvoiceNumDate(createInvoiceNumDateCounting);
		orVO.setOrUserColumn5(kakaoApi.searchRegion3depthName(orVO, kakaoRequestHeaders));
		
		int result = todayPickupDao.grantTodayPickupInvoiceNumber(orVO);
		
		if(result > 0) {
			
			int temp = 0;
			
			for(int i = 0; i < orVO.getProductOptionList().size(); i++) {
				
				if( temp == orVO.getProductOptionList().get(i).getAnotherOptionPk()) {
					
				}else {
					ohVO = new OrderHistoryVO();
					ohVO.setOrFk(orVO.getProductOptionList().get(i).getAnotherOptionPk());
					ohVO.setOhIp(ip);
					ohVO.setOhAdmin(adminId);
					ohVO.setOhRegdate(dates);
					ohVO.setOhEndPoint("송장 생성");
					ohVO.setOhDetail("송장 생성 완료 => 오늘의픽업 ( "+orVO.getRegiNo()+" )");
					
					logService.insertOrderHistory(ohVO);
					temp = orVO.getProductOptionList().get(i).getAnotherOptionPk();
					
				}
			}
		}
		
		return orVO;
		
	}

	@Override
	public OrderSearchVO selectTodayPickupTargetChecking(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		
		List<OrdersVO> checkingList = todayPickupDao.selectTodayPickupTargetChecking(osVO);
		
		List<String> targetList = new ArrayList<String>();
		
		OrderInfoToTodayPickup oitp = new OrderInfoToTodayPickup();
		
		URLUtil uUtil = new URLUtil();
		
		Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("Accept", "application/json");
		
        String parsingString = "";

		for(int i = 0; i < checkingList.size(); i++) {
			try {
				parsingString = uUtil.getCoordiante("https://mall.todaypickup.com/api/mall/possibleDelivery?address="+URLEncoder.encode(checkingList.get(i).getOrShippingAddress(), "UTF-8"), requestHeaders, "GET", null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("입출력 에러", e);
				
			}
			
			GoodsListOne gl = oitp.stringToGoodsListOne(parsingString);
			
			if(gl.getSuccess().equals("true")) {
				logger.info("오늘의 픽업 송장 생성 완료 = {}", gl.toString());
				targetList.add(checkingList.get(i).getOrSerialSpecialNumber());
				
			}
			
		}
		
		if(targetList != null && targetList.size() != 0) {
			osVO.setOrSerialSpecialNumberList(targetList);
			
		}
		
		return osVO;
	}

	@Override
	public int selectDontGrantTodayPickupDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return todayPickupDao.selectDontGrantTodayPickupDelivOrderListInMonthCounting(osVO);
	}

	@Override
	public List<OrdersVO> selectDontGrantTodayPickupDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return todayPickupDao.selectDontGrantTodayPickupDelivOrderListInMonth(osVO);
	}

	@Override
	public OrdersVO todayPickupDeliveryInvoiceNumberReprinting(OrderSearchVO osVO, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		
		String dates = sdf.format(today);
		
		
		OrdersVO orVO = todayPickupDao.todayPickupDeliveryInvoiceNumberReprinting(osVO);
		
		List<ProductOptionVO> orList = orVO.getProductOptionList();
		int temp = 0;
		
		OrderHistoryVO ohVO = null;
		
		for(int i = 0; i < orList.size(); i++) {			
			
			if( temp == orList.get(i).getAnotherOptionPk()) {
				
			}else {				
				ohVO = new OrderHistoryVO();
				ohVO.setOrFk(orList.get(i).getAnotherOptionPk());
				ohVO.setOhIp(ip);
				ohVO.setOhAdmin(adminId);
				ohVO.setOhRegdate(dates);				
				ohVO.setOhEndPoint("송장 재출력");
				ohVO.setOhDetail("송장 재출력 완료 => 오늘의픽업 ( "+orVO.getOrDeliveryInvoiceNumber()+" )");

				
				logService.insertOrderHistory(ohVO);
				temp = orList.get(i).getAnotherOptionPk();
			}
		}
		
		
		return todayPickupDao.todayPickupDeliveryInvoiceNumberReprinting(osVO);
	}

	@Override
	public int deleteTodayPickupDelivInvoice(String invoiceNum, Map<String, String> requestHeaders) {
		// TODO Auto-generated method stub
		
		OrderInfoToTodayPickup oitp = new OrderInfoToTodayPickup();
		KakaoAPI kakaoApi = new KakaoAPI();
		
		String todayPickupJsonString = oitp.todayPickupJsonBodyForDelete(invoiceNum);
		
		URLUtil uUtil = new URLUtil();
		
		String parsingString = "";
		
		try {
			parsingString = uUtil.getCoordiante("https://mall.todaypickup.com/api/mall/cancelDelivery", requestHeaders,"POST", todayPickupJsonString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);
			
		}
		
		CancleDeliveryVO gl = oitp.stringToCancleList(parsingString);
		
		//통신 실패실 경우 
		if(!gl.isSuccess()) {
			return 0;
			
		}else {
			return 1;
		}
		
	}
}
