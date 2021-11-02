package com.gogi.proj.todayPickup.util;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.todayPickup.vo.CancleDeliveryVO;
import com.gogi.proj.todayPickup.vo.GoodsListOne;
import com.gogi.proj.todayPickup.vo.GoodsListVO;

public class OrderInfoToTodayPickup {

	/**
	 * 
	 * @MethodName : todayPickupJsonBody
	 * @date : 2021. 8. 2.
	 * @author : Jeon KiChan
	 * @param order
	 * @return
	 * @메소드설명 : 오늘의픽업 배송지 다건 API에 필요한 정보 JSON 형식으로 만들기 
	 */
	public String todayPickupJsonBody(OrdersVO order) {

		JSONObject obj = new JSONObject();
		
		try {
			JSONArray jArray = new JSONArray();
			
			for (int i = 0; i < order.getProductOptionList().size(); i++){
				JSONObject sObject = new JSONObject();
				sObject.put("childrenMallId", "");
				sObject.put("deliveryAddress", order.getOrShippingAddress()+", "+order.getOrShippingAddressDetail());
				sObject.put("deliveryMessage", order.getOrDeliveryMessage());
				sObject.put("deliveryName", order.getOrReceiverName());
				sObject.put("deliveryPhone", order.getOrReceiverContractNumber1());
				sObject.put("deliveryPostal", order.getOrShippingAddressNumber());
				sObject.put("deliveryTel", order.getOrReceiverContractNumber2());
				sObject.put("goodsName", order.getProductOptionList().get(i).getProductName());
				sObject.put("invoiceNumber", "");
				sObject.put("invoicePrintYn", "N");
				sObject.put("mallName", "삼형제고기");
				sObject.put("optionName", order.getProductOptionList().get(i).getOptionName());
				sObject.put("orderNumber", order.getOrSerialSpecialNumber());
				sObject.put("quantity", order.getProductOptionList().get(i).getAnotherOptionQty());
				jArray.put(sObject);
				
			}
			
			obj.put("goodsList", jArray);


		} catch (JSONException e) {
			throw new RuntimeException("Json 에러 ", e);
			
		}
		
		return obj.toString();

	}
	
	public String todayPickupJsonBodyForDelete(String invoiceNum) {

		JSONObject obj = new JSONObject();
		
		try {
			
			obj.put("invoiceNumber", invoiceNum);

		} catch (JSONException e) {
			throw new RuntimeException("Json 에러 ", e);
			
		}
		
		return obj.toString();

	}
	
	
	/**
	 * 
	 * @MethodName : jsonToOrderInfo
	 * @date : 2021. 8. 2.
	 * @author : Jeon KiChan
	 * @param order 주문 정보
	 * @param goodsList 오늘의픽업 송장 정보
	 * @return
	 * @메소드설명 :
	 */
	public OrdersVO jsonToOrderInfo(OrdersVO order, GoodsListVO goodsList) {
		
		order.setRegiNo(goodsList.getData().get(0).getInvoiceNumber());
		order.setOrDeliveryInvoiceNumber(goodsList.getData().get(0).getInvoiceNumber());
		order.setOrDeliveryCompany("오늘의픽업");
		
		return order;
		
	}
	
	
	/**
	 * 
	 * @MethodName : stringToGoodsList
	 * @date : 2021. 8. 3.
	 * @author : Jeon KiChan
	 * @param jsonString
	 * @return
	 * @메소드설명 : Json 형태로 된 String 값을 GoodsListVO에 넣어줌
	 */
	public GoodsListVO stringToGoodsList(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		
		GoodsListVO gl =  null;
		
		try {
			gl = obj.readValue(jsonString, GoodsListVO.class);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Json 데이터 파싱 실패", e);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Json 데이터 매핑 실패", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);
		}
		
		return gl;
		
	}
	
	public GoodsListOne stringToGoodsListOne(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		
		GoodsListOne gl =  null;
		
		try {
			gl = obj.readValue(jsonString, GoodsListOne.class);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Json 데이터 파싱 실패", e);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Json 데이터 매핑 실패", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);
		}
		
		return gl;
		
	}
	
	public CancleDeliveryVO stringToCancleList(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		
		CancleDeliveryVO gl =  null;
		
		try {
			gl = obj.readValue(jsonString, CancleDeliveryVO.class);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Json 데이터 파싱 실패", e);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Json 데이터 매핑 실패", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);
		}
		
		return gl;
		
	}
}
