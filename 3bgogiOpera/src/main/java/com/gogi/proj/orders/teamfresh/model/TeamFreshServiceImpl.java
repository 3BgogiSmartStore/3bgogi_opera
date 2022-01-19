package com.gogi.proj.orders.teamfresh.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.cj.model.CjdeliveryDAO;
import com.gogi.proj.orders.model.OrdersDAO;
import com.gogi.proj.orders.teamfresh.util.TeamFreshDataAccess;
import com.gogi.proj.orders.teamfresh.vo.TeamFreshResultVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.todayPickup.util.URLUtil;

@Service
public class TeamFreshServiceImpl implements TeamFreshService{

	@Resource(name="apiKeyProperties")
	private Properties apiKeyProperties;
	
	@Autowired
	private OrdersDAO ordersDao;
	
	@Autowired
	private TeamFreshDAO teamFreshDao;
	
	@Autowired
	private CjdeliveryDAO cjDao;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private DeliveryConfigService dcService;
	
	@Autowired
	private TeamFreshDataAccess teamFreshDataAccess;

	@Override
	public OrderSearchVO selectTeamFreshDeliveryTargetChecking(OrderSearchVO osVO) {
		// TODO Auto-generated method stub

		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String formatToday = sdf.format(today);
		
		List<OrdersVO> checkingList = cjDao.selectCjDeliveryTargetChecking(osVO);
		
		List<String> targetList = new ArrayList<String>();
		
		for(OrdersVO orVO : checkingList) {
			
			if(isTeamFreshDeliveryArea(orVO.getOrShippingAddress(), formatToday) == true) {
				targetList.add(orVO.getOrSerialSpecialNumber());
			}
		}
		
		osVO.setOrSerialSpecialNumberList(targetList);
		
		return osVO;
	}

	@Override
	public boolean isTeamFreshDeliveryArea(String jsonAddr, String formatToday) {
		// TODO Auto-generated method stub
        String parsingString = "";
        
        String key = apiKeyProperties.getProperty("api_key.team_fresh_key");
        
        URLUtil uUtil = new URLUtil();
		
		Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("apiaccesskey", key);
        
        requestHeaders.put("Content-type", "application/json;charset=UTF-8");
        
        JSONObject json = new JSONObject();
        
        json.put("addrBasic", jsonAddr);
        json.put("orderDate", formatToday);
        
        
        try {
			
			parsingString = uUtil.getCoordiante("https://tmsapi.teamfresh.co.kr/api/area/searchDeliveryArea", requestHeaders, "POST", json.toJSONString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);
			
		}
        
        TeamFreshResultVO tfVO =  teamFreshDataAccess.stringToTeamFreshDataResultDTO(parsingString);
        
        //System.out.println("tfVO = "+tfVO.toString());
        
        if(tfVO.getResult().getDelyverResult().equals("배송가능") == true) {
        	
        	return true;
        }else {
        	
        	return false;
        }
      
	}

	@Override
	public String createTeamFreshDelivInvoice(OrderSearchVO osVO, String ip, String adminId) {
		// TODO Auto-generated method stub
		StringBuffer result = new StringBuffer("");
		
		String key = apiKeyProperties.getProperty("api_key.team_fresh_key");
		
		String parsingString = "";
        
        URLUtil uUtil = new URLUtil();
		
		Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("apiaccesskey", key);
        
        requestHeaders.put("Content-type", "application/json;charset=UTF-8");
        
		List<OrdersVO> delivTarget = teamFreshDao.selectTeamFreshDeliveryExcelTarget(osVO);
		
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String formatToday = sdf.format(today);
		
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		JSONObject json = null; 

        JSONArray orderList = null;
        
        JSONObject orderInfo = null;
        
        int orderSize = delivTarget.size();
        
        for(int i = 0; i < orderSize; i++) {
        	
        	json = new JSONObject();
        	json.put("orderDate", formatToday);
        	
        	orderList = new JSONArray();
        	
        	String delivMsg = "";
			String delivMsgTemp = "";
			String doorPass = "";
			String doorPassTemp = "";
			
			StringBuffer sb = new StringBuffer("");
			
			int prodSize = delivTarget.get(i).getProductOptionList().size();

			for(int prodCount = 0; prodCount < prodSize; prodCount++) {
				
				ProductOptionVO poVO = delivTarget.get(i).getProductOptionList().get(prodCount);

				if(prodCount == prodSize ) {
					sb.append(poVO.getProductName());
				}else if((prodCount + 1) % 2 == 1 ){
					sb.append(poVO.getProductName()+" ● ");
					
				}else if((prodCount + 1) % 2 == 0 ){
					sb.append(poVO.getProductName()+"|");
					
				}
				
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
			
        	orderInfo = new JSONObject();
        	orderInfo.put("idx", (i+1));
        	orderInfo.put("customerName", "삼형제월드");
        	orderInfo.put("receiverName", delivTarget.get(i).getOrReceiverName());
        	orderInfo.put("receiverHp", delivTarget.get(i).getOrReceiverContractNumber1());
        	orderInfo.put("addrBasic", delivTarget.get(i).getOrShippingAddress());
        	orderInfo.put("addrDetail", delivTarget.get(i).getOrShippingAddressDetail());
        	orderInfo.put("zipCode", delivTarget.get(i).getOrShippingAddressNumber());
        	orderInfo.put("note1", doorPass);
        	orderInfo.put("note2", delivMsg);
        	orderInfo.put("boxCount", 1);
        	orderInfo.put("smsYn", "X");
        	orderInfo.put("customerOrderNum", delivTarget.get(i).getOrSerialSpecialNumber());
        	orderInfo.put("senderName", delivTarget.get(i).getOrBuyerName());
        	orderInfo.put("itemInfo", sb.toString());
        	
        	
        	orderInfo.put("cNote1", "");
        	orderInfo.put("cNote2", "");
        	orderInfo.put("cNote3", "");
        	orderInfo.put("cNote4", "");
        	orderInfo.put("cNote5", "");
        	orderInfo.put("premiumYn", "X");
        	
        	orderList.add(orderInfo);
        	
        	json.put("orderInfo", orderList);

        	try {
    			
    			parsingString = uUtil.getCoordiante("https://tmsapi.teamfresh.co.kr/api/order/updateOrder", requestHeaders, "POST", json.toJSONString());
    			
    			TeamFreshResultVO tfVO =  teamFreshDataAccess.stringToTeamFreshDataResultDTO(parsingString);
    			
    			delivTarget.get(i).setOrDeliveryInvoiceNumber(tfVO.getOrderResult()[0].getBarcodeNumList()[0]);
    			delivTarget.get(i).setResNo(tfVO.getOrderResult()[0].getOrderNum());
    			
    			teamFreshDao.grantTeamFreshInvoiceNumber(delivTarget.get(i));
    			
    			
    			List<OrdersVO> orList = ordersDao.selectOrdersPkByOrSerialSpecialNumber(delivTarget.get(i).getOrSerialSpecialNumber());
    			
    			int temp = 0;
    			
    			OrderHistoryVO ohVO = null;
    			
    			for(int orderCount = 0; orderCount < orList.size(); orderCount++) {			
    				
    				if( temp == orList.get(orderCount).getOrPk()) {
    					
    				}else {				
    					ohVO = new OrderHistoryVO();
    					ohVO.setOrFk(orList.get(orderCount).getOrPk());
    					ohVO.setOhIp(ip);
    					ohVO.setOhAdmin(adminId);
    					ohVO.setOhRegdate(formats.format(today));
    					ohVO.setOhEndPoint("송장 부여");
						ohVO.setOhDetail("송장 부여 완료 => 팀프레시 ( "+tfVO.getOrderResult()[0].getBarcodeNumList()[0]+" )");
    					
    					logService.insertOrderHistory(ohVO);
    					temp = orList.get(orderCount).getOrPk();
    				}
    			}
    			
    			if(tfVO.getOrderResult()[0].getBarcodeNumList()[0] != null && !tfVO.getOrderResult()[0].getBarcodeNumList()[0].equals("")) {
    				result.append("구매자 = "+delivTarget.get(i).getOrBuyerName()+", 수령자 = "+delivTarget.get(i).getOrReceiverName()+", 송장번호 = "+tfVO.getOrderResult()[0].getBarcodeNumList()[0]+" 생성완료<br>");
    			}
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			throw new RuntimeException("입출력 에러", e);
    			
    		}
        	
        }
        
        
        return result.toString();
	}

	@Override
	public int deleteTeamFreshDelivInvoice(String teamFreshOrderNum, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		String key = apiKeyProperties.getProperty("api_key.team_fresh_key");
		
		String parsingString = "";
        
        URLUtil uUtil = new URLUtil();
		
		Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("apiaccesskey", key);
        
        requestHeaders.put("Content-type", "application/json;charset=UTF-8");
        
        Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String formatToday = sdf.format(today);
		
		JSONObject json = new JSONObject();
    	json.put("orderDate", formatToday);
    	json.put("orderNum", teamFreshOrderNum);
    	
    	try {
			
			parsingString = uUtil.getCoordiante("https://tmsapi.teamfresh.co.kr/api/order/orderCancel", requestHeaders, "POST", json.toJSONString());
			
			TeamFreshResultVO tfVO =  teamFreshDataAccess.stringToTeamFreshDataResultDTO(parsingString);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);
			
		}
        
		return 0;
	}

	@Override
	public int updateTeamFreshDoorPass(String orderId, String doorPassKeyword) {
		// TODO Auto-generated method stub
		
		String key = apiKeyProperties.getProperty("api_key.team_fresh_key");
		
		String parsingString = "";
        
        URLUtil uUtil = new URLUtil();
		
		Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("apiaccesskey", key);
        
        requestHeaders.put("Content-type", "application/json;charset=UTF-8");
        
        Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String formatToday = sdf.format(today);
		
		JSONObject json = new JSONObject();
		
		JSONArray orderList = new JSONArray();
        
        JSONObject orderInfo = new JSONObject();
        
    	json.put("orderDate", formatToday);

    	orderInfo = new JSONObject();
    	orderInfo.put("idx", 1);
    	orderInfo.put("orderType", "2");
    	orderInfo.put("customerOrderNum", orderId);
    	orderInfo.put("note1", doorPassKeyword);
    	
    	orderList.add(orderInfo);
    	
    	json.put("orderInfo", orderList);
    	
    	try {
			
			parsingString = uUtil.getCoordiante("https://tmsapi.teamfresh.co.kr/api/order/modifyOrder", requestHeaders, "POST", json.toJSONString());
			
			TeamFreshResultVO tfVO =  teamFreshDataAccess.stringToTeamFreshDataResultDTO(parsingString);
			
			if(tfVO.getOrderResult()[0].getOrderResultMsg().equals("저장 성공")) {
				
				return 1;
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);
			
		}
        
		return 0;
	}
}
