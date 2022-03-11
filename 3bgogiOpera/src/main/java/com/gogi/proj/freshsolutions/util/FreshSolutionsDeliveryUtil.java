package com.gogi.proj.freshsolutions.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
	public boolean isFreshSolutionsDeliveryArea(String addr, String addrDetatil) {

		String parsingString = "";
		
		URLUtil uUtil = new URLUtil();
		
		Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("Content-type", "application/json;charset=UTF-8");
		
        try {
			
			parsingString = uUtil.getCoordiante("https://toms.open-api.kurly.com/v1/api/address/refine?primaryAddress="+addr+"&secondaryAddress="+addrDetatil+"&isRoadAddress=true", requestHeaders, "GET", null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("입출력 에러", e);
			
		}
        
        FreshSolutionsDTO freshSolutionsDTO = freshSolutionDataAccess.stringToFreshSolutionsDTO(parsingString);
        
        if(freshSolutionsDTO.getOperationTime().equals("DAWN")) {
        	
        	return true;
        }else {
        	
        	return false;
        }
        
	}
}
