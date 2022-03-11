package com.gogi.proj.freshsolutions.util;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.proj.freshsolutions.vo.FreshSolutionsDTO;

@Component
public class FreshSolutionsDataAccess {

	public FreshSolutionsDTO stringToFreshSolutionsDTO(String jsonString) {
		
		ObjectMapper obj = new ObjectMapper();
		obj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		FreshSolutionsDTO freshSolutionsDTO =  null;
		
		try {
			freshSolutionsDTO = obj.readValue(jsonString, FreshSolutionsDTO.class);
			
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
		
		return freshSolutionsDTO;
		
	}
}
