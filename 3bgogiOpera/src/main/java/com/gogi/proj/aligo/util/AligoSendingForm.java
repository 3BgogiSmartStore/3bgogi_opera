package com.gogi.proj.aligo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.proj.aligo.kakao.AligoKakaoResultDTO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.util.JsonToMapUtil;

@Component
public class AligoSendingForm {

	@Resource(name="apiKeyProperties")
	private Properties apiKeyProperties;
	
	private String key;
	private String user_id;

	final static String encodingType = "utf-8";
	final static String boundary = "____boundary____";
	final static String templetNum = "TG_7721";
	
	
	/**
	 * 
	 * @MethodName : smsMsg
	 * @date : 2021. 11. 3.
	 * @author : Jeon KiChan
	 * @param aligo
	 * @return
	 * @메소드설명 : receiver는 고객 번호, destination은 %고객명% 치환용 입력
	 */
	public String smsMsg(AligoVO aligo) {
		
		key = apiKeyProperties.getProperty("api_key.aligo_api_key");
		user_id = apiKeyProperties.getProperty("api_key.aligo_user_id");
				
        String apiURL = "https://apis.aligo.in/send/";    // json 결과

        Map<String, String> requestHeaders = new HashMap<>();
        
        requestHeaders.put("key",key);
        requestHeaders.put("user_id",user_id);
        requestHeaders.put("sender","070-4169-3167");
        requestHeaders.put("receiver",aligo.getReceiver());
        requestHeaders.put("destination", aligo.getDestination() == null ? "":aligo.getDestination());
        requestHeaders.put("msg",aligo.getMsg());
        //requestHeaders.put("data-urlencode", "title="+URLEncoder.encode(aligo.getTitle(),"UTF-8"));
        requestHeaders.put("rdate", aligo.getrDate() == null ? "":aligo.getrDate());
        requestHeaders.put("rtime", aligo.getrTime() == null ? "":aligo.getrTime());
        requestHeaders.put("testmode_yn", "N");
        
        String responseBody = get(apiURL,requestHeaders,"POST");
        
		return responseBody;
		
	}
	
	public String aligoCjDelivDoorPassMsg() {
		
		return "[삼형제고기 알림]\n"
				+"%고객명% 고객님의 상품은 새벽배송으로 발송될 예정입니다.\n"
		
				+"그러나 새벽배송에 필요한 공동현관 출입번호가 기재되지 않아\n"
				+"1층 공동현관 앞에 배송될 수 있어 연락드립니다.\n "
		
				+"아래 네이버 톡톡으로 공동현관 출입번호를 알려주시면\n" 
				+"송장에 기재하여 발송하도록 하겠습니다.\n"
		
				+"톡톡문의링크 https://talk.naver.com/ct/wc9uqb?frm=psf";
		
		
		
				
	}

	public AligoVO aligoSendingForm(List<OrdersVO> orList) {
		AligoVO aligoVO = new AligoVO();
		int counting = 1;
		int totalPrice = 0;
		int deliveryPrice = 0;
		
		DecimalFormat df = new DecimalFormat("###,###");
		String address = "";
		StringBuilder productList = new StringBuilder("[삼형제고기주문]\n\n");
		
		for(OrdersVO orVO : orList) {
			if(counting == 1) {
				productList.append(orVO.getOrBuyerName()+" 님 주문내역입니다\n\n●주문내역\n");
				aligoVO.setReceiver(orVO.getOrBuyerContractNumber1());
				address+=(orVO.getOrShippingAddress()+" "+orVO.getOrShippingAddressDetail());
				productList.append(counting+". "+orVO.getOrProduct()+" "+orVO.getOrProductOption()+" "+orVO.getOrAmount()+" 개\n"+df.format(orVO.getOrTotalPrice())+" 원\n");
				deliveryPrice+=orVO.getOrDeliveryPrice();
				
			}else {
				productList.append(counting+". "+orVO.getOrProduct()+" "+orVO.getOrProductOption()+" "+orVO.getOrAmount()+" 개\n"+df.format(orVO.getOrTotalPrice())+" 원\n");
			}
			totalPrice += orVO.getOrTotalPrice();
			counting++;
		}
		
		totalPrice+=deliveryPrice;
		
		if(deliveryPrice > 0) {
			productList.append("\n총 합 : "+df.format(totalPrice)+"원(배송비 "+df.format(deliveryPrice)+"원 포함)\n");
			
		}else {
			productList.append("\n총 합 : "+df.format(totalPrice)+"원\n");
			
		}
		
		productList.append("\n");
		productList.append("기업은행(삼형제고기)\n");
		productList.append("231-160549-04-019\n\n");
		productList.append("주문자명으로 입금 부탁드립니다^^\n");
		productList.append("주문자 정보 -(틀릴 경우 바로 연락주세요)\n");
		productList.append(address);
		
		aligoVO.setMsg(productList.toString());
		return aligoVO;
		
	}
	
	public AligoVO sendingAligoSMSLargeFormData(List<OrdersVO> orList) {
		AligoVO aligoVo = new AligoVO();
		
		StringBuffer aligoDes = new StringBuffer("");
		StringBuffer aligorec = new StringBuffer("");
		
		int counting = 0;
		
		for(OrdersVO orVO : orList) {
			if(counting == 0) {
				aligoDes.append(orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
				aligoDes.append(orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
				
			}else {
				aligoDes.append(","+orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
				aligoDes.append(","+orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
			}
			
			counting++;
		}
		
		aligoVo.setDestination(aligoDes.toString());
		aligoVo.setReceiver(aligorec.toString());
		
		return aligoVo;
	}
	
	public String get(String apiUrl, Map<String, String> requestHeaders, String methodType){
        MultipartEntityBuilder  builder = MultipartEntityBuilder.create();
		
		builder.setBoundary(boundary);
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.setCharset(Charset.forName(encodingType));
		
		String result = "";
        try {
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                builder.addTextBody(header.getKey(),  header.getValue()
    					, ContentType.create("Multipart/related", encodingType));
                
            }

            HttpEntity entity = builder.build();
            HttpClient client = HttpClients.createDefault();
    		HttpPost post = new HttpPost(apiUrl);
    		post.setEntity(entity);
    		
    		HttpResponse res = client.execute(post);
    		
    		result = "";
    		if(res != null){
    			BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
    			String buffer = null;
    			while((buffer = in.readLine())!=null){
    				result += buffer;
    			}
    			
    			in.close();
    		}
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {

        }
        
        return result;
    }
	
	
	public Map<String, Object> aligoRemain() {
		
		key = apiKeyProperties.getProperty("api_key.aligo_api_key");
		user_id = apiKeyProperties.getProperty("api_key.aligo_user_id");
		
		Map<String, Object> remainResult = null;
		
		try{

			/**************** 최근 전송 목록 ******************/
			/* "result_code":결과코드,"message":결과문구, */
			/** list : 전송된 목록 배열 ***/
			/******************** 인증정보 ********************/
			String sms_url = "https://apis.aligo.in/remain/"; // 전송요청 URL
			
			String sms = "";
			sms += "user_id=" + user_id; // SMS 아이디 
			sms += "&key=" + key; //인증키
			/******************** 인증정보 ********************/
			
			URL url = new URL(sms_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			OutputStream os = conn.getOutputStream();
			os.write(sms.getBytes());
			os.flush();
			os.close();
			
			String result = "";
			String buffer = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			while((buffer = in.readLine())!=null){
				result += buffer;
			}
			
			in.close();

			JsonToMapUtil jt = new JsonToMapUtil();
			
			JSONParser parser = new JSONParser();
			Object Object = null;
			
			try {
				Object = parser.parse( result );
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			remainResult = jt.getMapFromJsonObject((JSONObject)Object);
			
		}catch(MalformedURLException e1){
			
		}catch(IOException e2){
			
		}
	
		return remainResult;
	}
	
	
	public AligoResultDTO stringToAligoResultDTO(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		AligoResultDTO aligoDto =  null;
		
		try {
			aligoDto = obj.readValue(jsonString, AligoResultDTO.class);
			
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
		
		return aligoDto;
		
	}

}
