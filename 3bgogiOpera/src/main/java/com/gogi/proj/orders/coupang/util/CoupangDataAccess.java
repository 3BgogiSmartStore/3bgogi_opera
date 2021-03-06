package com.gogi.proj.orders.coupang.util;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.proj.orders.coupang.vo.CoupangCanceledDTO;
import com.gogi.proj.orders.coupang.vo.CoupangResDTO;
import com.gogi.proj.orders.coupang.vo.CoupangResponseDTO;

@Component
public class CoupangDataAccess {

	public CoupangResDTO stringToCoupangResDTO(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		CoupangResDTO coupangResDTO =  null;
		
		try {
			coupangResDTO = obj.readValue(jsonString, CoupangResDTO.class);
			
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
		
		return coupangResDTO;
		
	}
	
	public CoupangResponseDTO stringToCoupangResponseDTO(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		CoupangResponseDTO coupangResDTO =  null;
		
		try {
			coupangResDTO = obj.readValue(jsonString, CoupangResponseDTO.class);
			
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
		
		return coupangResDTO;
		
	}
	
	public CoupangCanceledDTO StringToCoupangCanceledDTO(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		CoupangCanceledDTO coupangCanceledDTO =  null;
		
		try {
			coupangCanceledDTO = obj.readValue(jsonString, CoupangCanceledDTO.class);
			
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
		
		
		return coupangCanceledDTO;
		
	}
}
