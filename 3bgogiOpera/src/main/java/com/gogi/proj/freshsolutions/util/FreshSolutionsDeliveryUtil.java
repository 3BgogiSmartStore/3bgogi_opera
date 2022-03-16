package com.gogi.proj.freshsolutions.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gogi.proj.freshsolutions.vo.FreshSolutionsDTO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.todayPickup.util.URLUtil;

@Component
public class FreshSolutionsDeliveryUtil {

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
			
			FreshSolutionsDTO freshSolutionsDTO = freshSolutionDataAccess.stringToFreshSolutionsDTO(result);

			if (freshSolutionsDTO.getOperationTime().equals("DAWN")) {

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
}
