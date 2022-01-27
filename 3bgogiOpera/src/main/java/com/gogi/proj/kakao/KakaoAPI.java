package com.gogi.proj.kakao;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.todayPickup.util.URLUtil;

public class KakaoAPI {

	private static final Logger logger = LoggerFactory.getLogger(KakaoAPI.class);
	
	public String searchRegion3depthName(OrdersVO orVO, Map<String, String> requestHeaders) {
		
		URLUtil uUtil = new URLUtil();
		String jsonString = "";
		String result = "";
		
		try {
			jsonString = uUtil.getCoordiante("https://dapi.kakao.com/v2/local/search/address.json?query="+URLEncoder.encode(orVO.getOrShippingAddress(), "UTF-8"), requestHeaders, "GET", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		KakaoDTO kakaoDto = stringToKakaoDTO(jsonString);
		
		
		if(kakaoDto.getDocuments().size() != 0) {
			logger.info("kakao 행정동 결과 = {}", kakaoDto.toString());
			result = kakaoDto.getDocuments().get(0).getAddress().getRegion_3depth_name();
		}
		
		return result;
	}
	
	public String searchRegionZipCode(String Addr, Map<String, String> requestHeaders) {
        
		URLUtil uUtil = new URLUtil();
		String jsonString = "";
		String result = "";
		
		try {
			jsonString = uUtil.getCoordiante("https://dapi.kakao.com/v2/local/search/address.json?query="+URLEncoder.encode(Addr, "UTF-8"), requestHeaders, "GET", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		KakaoDTO kakaoDto = stringToKakaoDTO(jsonString);
		
		
		if(kakaoDto.getDocuments().size() != 0) {
			logger.info("kakao 행정동 결과 = {}", kakaoDto.toString());
			result = kakaoDto.getDocuments().get(0).getRoad_address().getZone_no();
		}
		
		return result;
	}
	
	public KakaoDTO stringToKakaoDTO(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		
		KakaoDTO kakaoDto =  null;
		
		try {
			kakaoDto = obj.readValue(jsonString, KakaoDTO.class);
			
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
		
		return kakaoDto;
		
	}
}
