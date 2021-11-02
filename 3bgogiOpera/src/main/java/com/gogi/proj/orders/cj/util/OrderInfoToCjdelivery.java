package com.gogi.proj.orders.cj.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderInfoToCjdelivery {

	public CjResultMessage stringToCjResultMsg(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		
		CjResultMessage cjMsg = null;
		
		try {
			cjMsg = obj.readValue(jsonString, CjResultMessage.class);
			
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
		
		return cjMsg;
		
	}
}
