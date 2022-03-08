package com.gogi.proj.orders.coupang.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coupang.openapi.sdk.Hmac;
import com.gogi.proj.orders.coupang.vo.CoupangCancelResponseData;
import com.gogi.proj.orders.coupang.vo.CoupangCanceledDTO;
import com.gogi.proj.orders.coupang.vo.CoupangData;
import com.gogi.proj.orders.coupang.vo.CoupangOrderItems;
import com.gogi.proj.orders.coupang.vo.CoupangResDTO;
import com.gogi.proj.orders.coupang.vo.CoupangResponseDTO;
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
	
	
	/**
	 * 
	 * @MethodName : getCoupangOrderList
	 * @date : 2022. 3. 3.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 쿠팡 결제완료 조회 후 상품준비중으로 변경하여 주문서 긁어오기
	 */
	public List<OrdersVO> getCoupangOrderList(boolean sendingDeadlineFlag) {
		// params
		String method = "GET";
		
		List<OrdersVO> orderList = new ArrayList<OrdersVO>();
		
		Calendar calendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		Date months = calendar.getTime();
		Date todays = cal.getTime();
		// replace with your own vendorId
		
		SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat yMdHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		secretKey = apiKeyProperties.getProperty("api_key.coupang.secret_key");
		accessKey = apiKeyProperties.getProperty("api_key.coupang.access_key");
		userId = apiKeyProperties.getProperty("api_key.coupang.user_id");
		
		String startDate = yMd.format(months);
		String endDate = yMd.format(todays);
		
		String path = "/v2/providers/openapi/apis/api/v4/vendors/"+userId+"/ordersheets";
		
		String authorization = "";
		
		CloseableHttpClient client = null;
		
		try {
			// create client
			client = HttpClients.createDefault();
			// build uri
			URIBuilder uriBuilder = new URIBuilder().setPath(path).addParameter("createdAtFrom", startDate)
					.addParameter("createdAtTo", endDate).addParameter("status", "ACCEPT");

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
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);

                CoupangResDTO coupangResDTO = coupangDataAccess.stringToCoupangResDTO(result);
                
                OrdersVO order = null;
                
                for( CoupangData coupangData : coupangResDTO.getData()  ) {
                	
                	
                	for(CoupangOrderItems coupangItem : coupangData.getOrderItems()) {
                		order = new OrdersVO();
                		
                    	// 쿠팡 고유값
                		order.setSsFk(5);
                		
                    	order.setOrDeliveryNumber(coupangData.getShipmentBoxId());
                    	order.setOrOrderNumber(coupangData.getOrderId());
                    	order.setOrProductOrderNumber(coupangItem.getVendorItemId());
                    	order.setOrBuyerName(coupangData.getOrderer().getName());
                    	order.setOrBuyerContractNumber1(coupangData.getOrderer().getSafeNumber());
                    	order.setOrDeliveryPrice(coupangData.getShippingPrice() + coupangData.getRemotePrice());
                    	order.setOrReceiverName(coupangData.getReceiver().getName());
                    	order.setOrReceiverContractNumber1(coupangData.getReceiver().getSafeNumber());
                    	order.setOrShippingAddress(coupangData.getReceiver().getAddr1());
                    	order.setOrShippingAddressDetail(coupangData.getReceiver().getAddr2());
                    	order.setOrShippingAddressNumber(coupangData.getReceiver().getPostCode());
                    	
                		order.setOrProductNumber(coupangItem.getVendorItemId());
                		order.setOrAmount(coupangItem.getShippingCount());
                		order.setOrTotalPrice(coupangItem.getOrderPrice());
                		order.setOrDiscountPrice(-coupangItem.getDiscountPrice());
                		order.setOrInflowRoute(coupangData.getRefer());
                		order.setOrSettlementDay(new Timestamp(yMdHms.parse(coupangData.getPaidAt().replaceAll("T", " ")).getTime()));
                		order.setOrProduct(coupangItem.getSellerProductName());
                		order.setOrProductOption(coupangItem.getSellerProductItemName());
                		
						Calendar cals = Calendar.getInstance();
        				cals.setTime(new Date(yMd.parse(coupangItem.getEstimatedShippingDate()).getTime()));
        				java.sql.Date d = new java.sql.Date(cals.getTimeInMillis());
        				
        				if(sendingDeadlineFlag) {
        					order.setOrSendingDeadline(d);
        				}else {
        					order.setOrSendingDeadline(new java.sql.Date(todays.getTime()));
        				}
        				
        				
        				order.setOrRegdate(new Timestamp(todays.getTime()));
        				boolean reqResult = changeOrderStatus(order);
        				
                	}

                }
                
                
                
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
		
		orderList = getCoupangOrderOverList(orderList, sendingDeadlineFlag, "");
		
		return orderList;
	}
	
	/**
	 * 
	 * @MethodName : changeOrderStatus
	 * @date : 2022. 3. 3.
	 * @author : Jeon KiChan
	 * @param ordersVO
	 * @return
	 * @메소드설명 : 쿠팡 결제완료 건 상품준비중으로 변경하기
	 */
	public boolean changeOrderStatus(OrdersVO ordersVO) {
		
		String method = "PUT";
		// replace with your own vendorId
		secretKey = apiKeyProperties.getProperty("api_key.coupang.secret_key");
		accessKey = apiKeyProperties.getProperty("api_key.coupang.access_key");
		userId = apiKeyProperties.getProperty("api_key.coupang.user_id");
		
		String path = "/v2/providers/openapi/apis/api/v4/vendors/"+userId+"/ordersheets/acknowledgement";
		
		String authorization = "";
		
		CloseableHttpClient client = null;
		
		JSONObject json = new JSONObject();
		
		json.put("vendorId", userId);
		
		JSONArray orderList = new JSONArray();
		
		orderList.add(ordersVO.getOrDeliveryNumber());
		
		json.put("shipmentBoxIds", orderList);
		
		
		try {
			// create client
			client = HttpClients.createDefault();
			// build uri
			URIBuilder uriBuilder = new URIBuilder().setPath(path).addParameter("vendorId", userId);

			/********************************************************/
			// authorize, demonstrate how to generate hmac signature here
			authorization = Hmac.generate(method, uriBuilder.build().toString(), secretKey, accessKey);

            uriBuilder.setScheme(SCHEMA).setHost(HOST).setPort(PORT);
            HttpPut put = new HttpPut(uriBuilder.build().toString());
            /********************************************************/
            
            StringEntity requestEntity = new StringEntity(json.toJSONString() , "utf-8");
            
            // set header, demonstarte how to use hmac signature here
            put.addHeader("Authorization", authorization);
            
            /********************************************************/
            put.addHeader("content-type", "application/json");
            put.setEntity(requestEntity);
            
            CloseableHttpResponse response = null;
            
            try {
                //execute get request
                response = client.execute(put);
                //print result
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);

                CoupangResponseDTO coupangResDTO = coupangDataAccess.stringToCoupangResponseDTO(result);
                
                if(coupangResDTO.getData().getResponseList()[0].isSucceed() == true) return true;
                
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
		
		
		return false;
	}
	
	
	/**
	 * 
	 * @MethodName : coupangOrderSending
	 * @date : 2022. 3. 3.
	 * @author : Jeon KiChan
	 * @param orderList
	 * @return
	 * @메소드설명 : 쿠팡 주문 건 자동 발송 처리
	 */
	public CoupangResponseDTO coupangOrderSending(List<OrdersVO> orderList) {
		// params
		String method = "POST";
		
		CoupangResponseDTO coupangResDTO = null;
		
		secretKey = apiKeyProperties.getProperty("api_key.coupang.secret_key");
		accessKey = apiKeyProperties.getProperty("api_key.coupang.access_key");
		userId = apiKeyProperties.getProperty("api_key.coupang.user_id");

		String path = "/v2/providers/openapi/apis/api/v4/vendors/"+userId+"/orders/invoices";
		
		String authorization = "";
		
		CloseableHttpClient client = null;
		
		
		/**
		 * json 타입으로 쿠팡 주문서 가공
		 * 
		 */
		
		JSONObject json = new JSONObject();
		
		json.put("vendorId", userId);
		
		JSONArray jsonList = new JSONArray();
		
		JSONObject orders;
		
		for(OrdersVO orVO : orderList) {
			
			orders = new JSONObject();
			
			orders.put("shipmentBoxId", orVO.getOrDeliveryNumber());
			orders.put("orderId", orVO.getOrOrderNumber());
			orders.put("deliveryCompanyCode", getCoupangDelivCode(orVO.getOrDeliveryCompany()));
			orders.put("invoiceNumber", orVO.getOrDeliveryInvoiceNumber());
			orders.put("vendorItemId", orVO.getOrProductOrderNumber());
			orders.put("splitShipping", "false");
			orders.put("preSplitShipped", "false");
			orders.put("estimatedShippingDate", "");
			
			jsonList.add(orders);
			
		}
		
		
		json.put("orderSheetInvoiceApplyDtos", jsonList);
		
		
		try {
			// create client
			client = HttpClients.createDefault();
			// build uri
			URIBuilder uriBuilder = new URIBuilder().setPath(path).addParameter("vendorId", userId);

			/********************************************************/
			// authorize, demonstrate how to generate hmac signature here
			authorization = Hmac.generate(method, uriBuilder.build().toString(), secretKey, accessKey);

            uriBuilder.setScheme(SCHEMA).setHost(HOST).setPort(PORT);
            HttpPost post = new HttpPost(uriBuilder.build().toString());
            /********************************************************/
            
            StringEntity requestEntity = new StringEntity(json.toJSONString() , "utf-8");
            
            // set header, demonstarte how to use hmac signature here
            post.addHeader("Authorization", authorization);
            
            /********************************************************/
            post.addHeader("content-type", "application/json");
            post.setEntity(requestEntity);
            
            CloseableHttpResponse response = null;
            
            try {
                //execute get request
                response = client.execute(post);
                //print result
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);

                coupangResDTO = coupangDataAccess.stringToCoupangResponseDTO(result);
                
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
		
		return coupangResDTO;
	}
	
	
	/**
	 * 
	 * @MethodName : getCoupangCanceledOrderList
	 * @date : 2022. 3. 3.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 쿠팡 취소주문 목록 가져오기
	 */
	public List<OrdersVO> getCoupangCanceledOrderList() {
		// params
		String method = "GET";

		Calendar calendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		Date months = calendar.getTime();
		Date todays = cal.getTime();
		// replace with your own vendorId
		
		SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");

		secretKey = apiKeyProperties.getProperty("api_key.coupang.secret_key");
		accessKey = apiKeyProperties.getProperty("api_key.coupang.access_key");
		userId = apiKeyProperties.getProperty("api_key.coupang.user_id");
		
		String startDate = yMd.format(months);
		String endDate = yMd.format(todays);
		
		String path = "/v2/providers/openapi/apis/api/v4/vendors/"+userId+"/returnRequests";
		
		String authorization = "";
		
		CloseableHttpClient client = null;
		
		List<OrdersVO> canceledOrderList = new ArrayList<OrdersVO>();
        
        OrdersVO canceledOrder = null;
        
		try {
			// create client
			client = HttpClients.createDefault();
			// build uri
			URIBuilder uriBuilder = new URIBuilder().setPath(path).addParameter("createdAtFrom", startDate)
					.addParameter("createdAtTo", endDate).addParameter("cancelType", "CANCEL");

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
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);

                CoupangCanceledDTO coupangCanceledDTO = coupangDataAccess.StringToCoupangCanceledDTO(result);

                
                for( CoupangCancelResponseData coupangCanceledData : coupangCanceledDTO.getData()) {
                	
                	for( CoupangOrderItems coupangOrderDetail : coupangCanceledData.getReturnItems()) {
                		canceledOrder = new OrdersVO();
                		
                		canceledOrder.setOrOrderNumber(coupangCanceledData.getOrderId());
                		canceledOrder.setOrProductOrderNumber(coupangOrderDetail.getVendorItemId());
                		
                		canceledOrderList.add(canceledOrder);
                	}
                }
                
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
		
		return canceledOrderList;
	}
	
	
	public List<OrdersVO> getCoupangOrderOverList(List<OrdersVO> orderList, boolean sendingDeadlineFlag ,String nextToken) {
		// params
		String method = "GET";

		Calendar calendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		Date months = calendar.getTime();
		Date todays = cal.getTime();
		// replace with your own vendorId
		
		SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat yMdHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		secretKey = apiKeyProperties.getProperty("api_key.coupang.secret_key");
		accessKey = apiKeyProperties.getProperty("api_key.coupang.access_key");
		userId = apiKeyProperties.getProperty("api_key.coupang.user_id");
		
		String startDate = yMd.format(months);
		String endDate = yMd.format(todays);
		
		String path = "/v2/providers/openapi/apis/api/v4/vendors/"+userId+"/ordersheets";
		
		String authorization = "";
		
		CloseableHttpClient client = null;
		
		try {
			// create client
			client = HttpClients.createDefault();
			// build uri
			URIBuilder uriBuilder = null;
			
			if(nextToken != null && !nextToken.equals("")) {
				uriBuilder = new URIBuilder().setPath(path).addParameter("createdAtFrom", startDate)
						.addParameter("createdAtTo", endDate).addParameter("status", "INSTRUCT").addParameter("nextToken", nextToken);
			}else {
				uriBuilder = new URIBuilder().setPath(path).addParameter("createdAtFrom", startDate)
						.addParameter("createdAtTo", endDate).addParameter("status", "INSTRUCT");
			}

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
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);

                CoupangResDTO coupangResDTO = coupangDataAccess.stringToCoupangResDTO(result);
                
                OrdersVO order = null;
                
                for( CoupangData coupangData : coupangResDTO.getData()  ) {
                	
                	
                	for(CoupangOrderItems coupangItem : coupangData.getOrderItems()) {
                		order = new OrdersVO();
                		
                    	// 쿠팡 고유값
                		order.setSsFk(5);
                		
                    	order.setOrDeliveryNumber(coupangData.getShipmentBoxId());
                    	order.setOrOrderNumber(coupangData.getOrderId());
                    	order.setOrProductOrderNumber(coupangItem.getVendorItemId());
                    	order.setOrBuyerName(coupangData.getOrderer().getName());
                    	order.setOrBuyerContractNumber1(coupangData.getOrderer().getSafeNumber());
                    	order.setOrDeliveryPrice(coupangData.getShippingPrice() + coupangData.getRemotePrice());
                    	order.setOrReceiverName(coupangData.getReceiver().getName());
                    	order.setOrReceiverContractNumber1(coupangData.getReceiver().getSafeNumber());
                    	order.setOrShippingAddress(coupangData.getReceiver().getAddr1());
                    	order.setOrShippingAddressDetail(coupangData.getReceiver().getAddr2());
                    	order.setOrShippingAddressNumber(coupangData.getReceiver().getPostCode());
                    	
                		order.setOrProductNumber(coupangItem.getVendorItemId());
                		order.setOrAmount(coupangItem.getShippingCount());
                		order.setOrTotalPrice(coupangItem.getOrderPrice());
                		order.setOrDiscountPrice(-coupangItem.getDiscountPrice());
                		order.setOrInflowRoute(coupangData.getRefer());
                		order.setOrSettlementDay(new Timestamp(yMdHms.parse(coupangData.getPaidAt().replaceAll("T", " ")).getTime()));
                		order.setOrProduct(coupangItem.getSellerProductName());
                		order.setOrProductOption(coupangItem.getSellerProductItemName());
                		
						Calendar cals = Calendar.getInstance();
        				cals.setTime(new Date(yMd.parse(coupangItem.getEstimatedShippingDate()).getTime()));
        				java.sql.Date d = new java.sql.Date(cals.getTimeInMillis());
        				
        				if(sendingDeadlineFlag) {
        					order.setOrSendingDeadline(d);
        				}else {
        					order.setOrSendingDeadline(new java.sql.Date(todays.getTime()));
        				}
        				
        				order.setOrRegdate(new Timestamp(todays.getTime()));
        				
        				orderList.add(order);
        				
                	}

                }
                
                if(coupangResDTO.getNextToken() != null && !coupangResDTO.getNextToken().equals(""))
                	orderList = getCoupangOrderOverList(orderList, sendingDeadlineFlag, coupangResDTO.getNextToken());
                
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
		
		return orderList;
	}
	
	
	/**
	 * 
	 * @MethodName : getCoupangDelivCode
	 * @date : 2022. 3. 3.
	 * @author : Jeon KiChan
	 * @param delivComp
	 * @return
	 * @메소드설명 : 쿠팡 택배사 코드 가져오기
	 */
	private String getCoupangDelivCode(String delivComp) {
		
		if(delivComp.equals("우체국택배")) {
			return "EPOST";
		}else if(delivComp.equals("롯데택배")) {
			return "HYUNDAI";
		}else if(delivComp.equals("CJ대한통운")) {
			return "CJGLS";
		}else if(delivComp.equals("대신택배")) {
			return "DAESIN";
		}else if(delivComp.equals("팀프레시")) {
			return "TEAMFRESH";
		}else {
			return "DIRECT";
		}
	}
}
