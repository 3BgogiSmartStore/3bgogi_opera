package com.gogi.proj.orders.coupang.util;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coupang.openapi.sdk.Hmac;
import com.gogi.proj.orders.coupang.vo.CoupangResDTO;
import com.gogi.proj.orders.vo.OrdersVO;

@Component
public class CoupangConnectUtil {

	@Resource(name="apiKeyProperties")
	private Properties apiKeyProperties;
	
	@Autowired
	private CoupangDataAccess coupangDataAccess;
	
	
	private static final String HOST = "api-gateway.coupang.com";
	private static final int PORT = 443;
	private static final String SCHEMA = "https";

	
	private String secretKey;
	private String accessKey;
	private String userId;
	
	public String getCoupangAuthKey(String dateStart, String dateEnd) {
		// params
		String method = "GET";
		// replace with your own vendorId
		

		secretKey = apiKeyProperties.getProperty("api_key.coupang.secret_key");
		accessKey = apiKeyProperties.getProperty("api_key.coupang.access_key");
		userId = apiKeyProperties.getProperty("api_key.coupang.user_id");
		
		String path = "/v2/providers/openapi/apis/api/v4/vendors/"+userId+"/ordersheets";
		
		String authorization = "";
		
		CloseableHttpClient client = null;
		
		try {
			// create client
			client = HttpClients.createDefault();
			// build uri
			URIBuilder uriBuilder = new URIBuilder().setPath(path).addParameter("createdAtFrom", dateStart)
					.addParameter("createdAtTo", dateEnd).addParameter("status", "ACCEPT");

			/********************************************************/
			// authorize, demonstrate how to generate hmac signature here
			authorization = Hmac.generate(method, uriBuilder.build().toString(), secretKey, accessKey);

            uriBuilder.setScheme(SCHEMA).setHost(HOST).setPort(PORT);
            HttpGet get = new HttpGet(uriBuilder.build().toString());
            /********************************************************/
            // set header, demonstarte how to use hmac signature here
            get.addHeader("Authorization", authorization);
            
            /********************************************************/
            get.addHeader("content-type", "application/json");
            CloseableHttpResponse response = null;
            
            try {
                //execute get request
                response = client.execute(get);
                //print result
                System.out.println("****************************************************************************************");
                
                System.out.println("status code:" + response.getStatusLine().getStatusCode());
                System.out.println("status message:" + response.getStatusLine().getReasonPhrase());
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                
                System.out.println("result:" + result);
                
                System.out.println("****************************************************************************************");
                
                CoupangResDTO coupangResDTO = coupangDataAccess.stringToCoupangResDTO(result);
                
                System.out.println("coupangResDTO = "+coupangResDTO.toString());
                System.out.println("****************************************************************************************");
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			if (client != null) {
				
				try {
					client.close();
					
				} catch (IOException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return authorization;
	}
	
	
	public boolean changeOrderStatus(OrdersVO ordersVO) {
		
		String method = "PUT";
		// replace with your own vendorId
		

		secretKey = apiKeyProperties.getProperty("api_key.coupang.secret_key");
		accessKey = apiKeyProperties.getProperty("api_key.coupang.access_key");
		userId = apiKeyProperties.getProperty("api_key.coupang.user_id");
		
		String path = "/v2/providers/openapi/apis/api/v4/vendors/"+userId+"/ordersheets/acknowledgement";
		
		String authorization = "";
		
		CloseableHttpClient client = null;
		
		try {
			// create client
			client = HttpClients.createDefault();
			// build uri
			URIBuilder uriBuilder = new URIBuilder().setPath(path).addParameter("vendorId", userId)
					.addParameter("shipmentBoxIds", ordersVO.getOrDeliveryNumber());

			/********************************************************/
			// authorize, demonstrate how to generate hmac signature here
			authorization = Hmac.generate(method, uriBuilder.build().toString(), secretKey, accessKey);

            uriBuilder.setScheme(SCHEMA).setHost(HOST).setPort(PORT);
            HttpGet get = new HttpGet(uriBuilder.build().toString());
            /********************************************************/
            // set header, demonstarte how to use hmac signature here
            get.addHeader("Authorization", authorization);
            
            /********************************************************/
            get.addHeader("content-type", "application/json");
            CloseableHttpResponse response = null;
            
            try {
                //execute get request
                response = client.execute(get);
                //print result
                System.out.println("****************************************************************************************");
                
                System.out.println("status code:" + response.getStatusLine().getStatusCode());
                System.out.println("status message:" + response.getStatusLine().getReasonPhrase());
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                
                System.out.println("result:" + result);
                
                System.out.println("****************************************************************************************");
                
                CoupangResDTO coupangResDTO = coupangDataAccess.stringToCoupangResDTO(result);
                
                System.out.println("coupangResDTO = "+coupangResDTO.toString());
                System.out.println("****************************************************************************************");
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			if (client != null) {
				
				try {
					client.close();
					
				} catch (IOException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		
		return true;
	}
}
