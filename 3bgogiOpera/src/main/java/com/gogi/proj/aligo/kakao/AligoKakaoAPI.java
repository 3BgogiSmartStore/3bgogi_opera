package com.gogi.proj.aligo.kakao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.proj.aligo.util.AligoSendingForm;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.todayPickup.util.URLUtil;

@Component
public class AligoKakaoAPI {

	private static final Logger logger = LoggerFactory.getLogger(AligoKakaoAPI.class);
	
	@Resource(name="apiKeyProperties")
	private Properties apiKeyProperties;
	
	@Autowired
	private AligoSendingForm asf;
	
	private String key;
	private String user_id;
	private String sender_key;
	
	/**
	 * 
	 * @MethodName : getAligoKakaoToken
	 * @date : 2021. 12. 8.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 알리고 카카오 연동 토큰키값 가져오기
	 */
	public AligoKakaoResultDTO getAligoKakaoToken(String url) {
		
		Map<String, String> requestHeaders = new HashMap<>();
        
		key = apiKeyProperties.getProperty("api_key.aligo_api_key");
		user_id = apiKeyProperties.getProperty("api_key.aligo_user_id");
		
        requestHeaders.put("apikey",key);
        requestHeaders.put("userid",user_id);
        
        String result = "";

			// result = util.getCoordiante(url, requestHeaders, "POST", null);
			
		result = asf.get(url, requestHeaders, "POST");

		AligoKakaoResultDTO dto = stringToAligoKakaoResultDTO(result);

		
		return dto;
	}
	
	public AligoKaKaoTempletList getAlioKakaoTemplt(String url, AligoKakaoResultDTO aligoKakaoDto) {
		
		Map<String, String> requestHeaders = new HashMap<>();
        
		key = apiKeyProperties.getProperty("api_key.aligo_api_key");
		user_id = apiKeyProperties.getProperty("api_key.aligo_user_id");
		sender_key = apiKeyProperties.getProperty("api_key.sender_key");
		
        requestHeaders.put("apikey",key);
        requestHeaders.put("userid",user_id);
        requestHeaders.put("token",aligoKakaoDto.getToken());
        requestHeaders.put("senderkey",sender_key);
        String result = "";

			// result = util.getCoordiante(url, requestHeaders, "POST", null);
			
		result = asf.get(url, requestHeaders, "POST");

		AligoKakaoResultDTO dto = stringToAligoKakaoResultDTO(result);
		
		AligoKaKaoTempletList akDto = null;
		int count = 0;
		
		for(AligoKaKaoTempletList akList : dto.getList()) {		
			count++;
			if(count == 3) akDto = akList;
			
		}
		
		return akDto;
	}
	
	
	public String aligoKakaoSending(String url, AligoKakaoResultDTO aligoKakaoDto, List<OrdersVO> orderList, String subject, String message, String templtCode) {
		
		URLUtil util = new URLUtil();
		
		Map<String, String> requestHeaders = new HashMap<>();
        
		key = apiKeyProperties.getProperty("api_key.aligo_api_key");
		user_id = apiKeyProperties.getProperty("api_key.aligo_user_id");
		sender_key = apiKeyProperties.getProperty("api_key.sender_key");
		
        requestHeaders.put("apikey",key);
        requestHeaders.put("userid",user_id);
        requestHeaders.put("token",aligoKakaoDto.getToken());
        requestHeaders.put("tpl_code",templtCode);
        requestHeaders.put("senderkey",sender_key);
        requestHeaders.put("sender","070-4169-3167");
        
        int count = 1;
        String button = buttonJsonData();
        
        for(OrdersVO orVO : orderList) {
        	String msg = "";
        	String phoneMsg = "";
        	
        	if(orVO.getOrOrderNumber().length() > 37) {
        		String [] orderNum = orVO.getOrOrderNumber().split(",");        		
        		msg = message.replaceAll("#\\{고객명\\}", orVO.getOrBuyerName()).replaceAll("#\\{주문번호\\}", orderNum[0] +" 외 " +(orderNum.length - 1));
        		phoneMsg = phoneMsg().replaceAll("#\\{고객명\\}", orVO.getOrBuyerName()).replaceAll("#\\{주문번호\\}", orderNum[0] +" 외 " +(orderNum.length - 1));

        	}else {
        		msg = message.replaceAll("#\\{고객명\\}", orVO.getOrBuyerName()).replaceAll("#\\{주문번호\\}", orVO.getOrOrderNumber());
        		phoneMsg = phoneMsg().replaceAll("#\\{고객명\\}", orVO.getOrBuyerName()).replaceAll("#\\{주문번호\\}", orVO.getOrOrderNumber());
        	}
        	
        	requestHeaders.put("receiver_"+count, orVO.getOrBuyerContractNumber1());
            requestHeaders.put("recvname_"+count, orVO.getOrBuyerName());
            requestHeaders.put("subject_"+count, subject);
            requestHeaders.put("message_"+count, msg);
            requestHeaders.put("button_"+count, button);
            
            requestHeaders.put("failover", "Y");
            requestHeaders.put("fsubject_"+count, subject);
            requestHeaders.put("fmessage_"+count, phoneMsg);
            
            count++;
        }
        
        
       
        //requestHeaders.put("testMode","Y");
        
        
        String result = "";

			// result = util.getCoordiante(url, requestHeaders, "POST", null);
			
		result = asf.get(url, requestHeaders, "POST");
		
		AligoKakaoResultDTO dto = stringToAligoKakaoResultDTO(result);
		
		return dto.getMessage();
	}
	
	private String buttonJsonData() {
		JSONObject jsonObject = new JSONObject();
		
		JSONArray array = new JSONArray();
		
		JSONObject data = new JSONObject();
		data.put("name", "공동현관 비밀번호 알려주기");
		data.put("linkType", "MD");
		data.put("linkTypeName", "메세지전달");
		
		array.add(data);
		
		jsonObject.put("button", array);
		
		return jsonObject.toJSONString();
	}
	
	private String phoneMsg() {
		return "#{고객명}님!\n" + 
				"□ 주문번호 : #{주문번호}\n" + 
				"\r\n" + 
				"새벽배송으로 발송될 예정입니다.\n" + 
				"그러나 공동현관 출입번호가 기재되지 않아 ★1층 공동현관 앞에 배송될 수 있어 연락드립니다★\n" + 
				"\n" + 
				"하단에 네이버 톡톡 링크@ 클릭 해서\n" + 
				"공동현관 비밀번호 말씀해 주시면 됩니다!\n" + 
				"\n" + 
				"[네이버톡톡] 17:30분 까지만\n" + 
				"talk.naver.com/ct/wc9uqb#nafullscreen";
	}
	
	
	public AligoKakaoResultDTO stringToAligoKakaoResultDTO(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		AligoKakaoResultDTO aligoKakaoDto =  null;
		
		try {
			aligoKakaoDto = obj.readValue(jsonString, AligoKakaoResultDTO.class);
			
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
		
		return aligoKakaoDto;
		
	}

}
