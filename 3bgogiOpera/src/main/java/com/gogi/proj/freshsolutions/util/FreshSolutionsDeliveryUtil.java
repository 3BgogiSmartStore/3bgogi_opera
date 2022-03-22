package com.gogi.proj.freshsolutions.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gogi.proj.freshsolutions.vo.FreshSolutionsDTO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.todayPickup.util.URLUtil;

@Component
public class FreshSolutionsDeliveryUtil {

	private static final Logger logger = LoggerFactory.getLogger(FreshSolutionsDeliveryUtil.class);
	
	@Resource(name="apiKeyProperties")
	private Properties apiKeyProperties;
	
	@Autowired
	private FreshSolutionsDataAccess freshSolutionDataAccess;

	/**
	 * 
	 * @MethodName : isFreshSolutionsDeliveryArea
	 * @date : 2022. 3. 11.
	 * @author : Jeon KiChan
	 * @param addr
	 * @param addrDetatil
	 * @return
	 * @메소드설명 : 프레시솔루션 배송 가능 지역 확인
	 */
	public boolean isFreshSolutionsDeliveryArea(String addr, String addrDetail) {

		String parsingString = "";

		URLUtil uUtil = new URLUtil();

		Map<String, String> requestHeaders = new HashMap<>();

		requestHeaders.put("Content-type", "application/json;charset=UTF-8");

		try {

			parsingString = uUtil.getCoordiante("https://toms.open-api.kurly.com/v1/api/address/refine?primaryAddress="
					+ addr + "&secondaryAddress=" + addrDetail + "&isRoadAddress=true", requestHeaders, "GET", null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);

		}

		FreshSolutionsDTO freshSolutionsDTO = freshSolutionDataAccess.stringToFreshSolutionsDTO(parsingString);

		if (freshSolutionsDTO.getOperationTime().equals("DAWN")) {

			return true;
		} else {

			return false;
		}

	}

	
	/**
	 * 
	 * @MethodName : isFreshSolutionsDeliveryAreaByRestAPI
	 * @date : 2022. 3. 16.
	 * @author : Jeon KiChan
	 * @param addr
	 * @param addrDetail
	 * @return
	 * @메소드설명 : 프레시솔루션 rest api로 배송가능 주소 획득하기
	 */
	public boolean isFreshSolutionsDeliveryAreaByRestAPI(String addr, String addrDetail) {

		String host = "toms.open-api.kurly.com";
		String path = "/v1/api/address/refine";
		String schema = "https";

		CloseableHttpClient client = null;

		try {
			client = HttpClients.createDefault();

			URIBuilder uriBuilder = new URIBuilder().setPath(path).addParameter("primaryAddress", addr)
					.addParameter("secondaryAddress", addrDetail).addParameter("isRoadAddress", "TRUE");

			uriBuilder.setScheme(schema).setHost(host);

			HttpGet get = new HttpGet(uriBuilder.build().toString());

			get.addHeader("content-type", "application/json");

			CloseableHttpResponse response = null;

			response = client.execute(get);
			// print result
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			
			logger.info("add = {}, freshsolutions res = {}",addr, result);
			
			FreshSolutionsDTO freshSolutionsDTO = freshSolutionDataAccess.stringToFreshSolutionsDTO(result);
			
			if (freshSolutionsDTO.getOperationTime() != null && freshSolutionsDTO.getOperationTime().equals("DAWN")) {

				return true;
			} else {

				return false;
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		return false;
	}
	
	public String uploadOrderDataForFreshSolutions(OrdersVO orVO, String delivMsg, String doorPass) {
		
		SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = calendar.getTime();
		
		String host = "toms.open-api.kurly.com";
		String path = "/v1/api/tcorders";
		String schema = "https";

		String JWT = apiKeyProperties.getProperty("api_key.fresh_solutions.JWT");
		CloseableHttpClient client = null;

		JSONObject json = new JSONObject();
		
		json.put("vendorCode", "TD112");
		json.put("requestDate", yMd.format(tomorrow));
		
		JSONArray orderList = new JSONArray();
		
		
		JSONObject ordererDetail = new JSONObject();
		
		ordererDetail.put("vendorOrderId", orVO.getOrSerialSpecialNumber());
		ordererDetail.put("orderUserName", orVO.getOrBuyerAnotherName() != null ? orVO.getOrBuyerAnotherName() : orVO.getOrBuyerName());
		ordererDetail.put("receiverName", orVO.getOrReceiverName());
		ordererDetail.put("receiverAddress", orVO.getOrShippingAddress());
		ordererDetail.put("receiverDetailAddress", orVO.getOrShippingAddressDetail() == null ? "" : orVO.getOrShippingAddressDetail()+"");
		ordererDetail.put("receiverTel", orVO.getOrReceiverContractNumber1());
		ordererDetail.put("receiverHp", orVO.getOrReceiverContractNumber1());
		ordererDetail.put("deliveryManagerMessage", !doorPass.equals("") ? "(현관:"+doorPass+") "+delivMsg : delivMsg);
		ordererDetail.put("operationTime", "DAWN");
		ordererDetail.put("smsTransType", "IMMEDIATELY");
		
		
		JSONArray prodList = new JSONArray();
		for( ProductOptionVO products : orVO.getProductOptionList()) {
			JSONObject prodDetail = new JSONObject();
			
			prodDetail.put("productCode", products.getAnotherOptionPk());
			prodDetail.put("productType", "REFRIGERATED");
			prodDetail.put("productName", products.getProductName());
			prodDetail.put("productCount", products.getAnotherOptionQty());
			
			prodList.add(prodDetail);
			
		}
		
		ordererDetail.put("products", prodList);
		orderList.add(ordererDetail);
		json.put("orders", orderList);
		
		String result = "";
		
		try {
			client = HttpClients.createDefault();

			URIBuilder uriBuilder = new URIBuilder().setPath(path);

			uriBuilder.setScheme(schema).setHost(host);

			HttpPost post = new HttpPost(uriBuilder.build().toString());

			StringEntity requestEntity = new StringEntity(json.toJSONString() , "utf-8");

			post.addHeader("content-type", "application/json");
			post.addHeader("authorization", "Bearer "+JWT);

			post.setEntity(requestEntity);
			
			CloseableHttpResponse response = null;

			response = client.execute(post);
			
			// print result
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			
			logger.info("test freshsolutions res = {}", result);
			
		}catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		return result;
		
	}
}
