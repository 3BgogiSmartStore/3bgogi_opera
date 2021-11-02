package com.gogi.proj.store.seller.util;

import java.util.HashMap;
import java.util.Map;

public enum SellerComsCal {

	PERSENT_15(0.15, "355276751"),
	PERSENT_20(0.2, "5846245333");
	
	private double commission;
	private String prodNum;
	
	SellerComsCal(double commission, String prodNum ){
		this.commission = commission;
		this.prodNum = prodNum;

	}
	
	/**
	 * 
	 * @MethodName : productCommission
	 * @date : 2021. 10. 27.
	 * @author : Jeon KiChan
	 * @param prod
	 * @return
	 * @메소드설명 : 상품별로 정해진 수수료 가져오기
	 */
	public static double productCommission(String prodNum) {
		
		double result = 1;
		
		for( SellerComsCal scc : SellerComsCal.values() ) {
			
			if( scc.prodNum.equals(prodNum)) {
				return scc.commission;
			}
		}
		
		return result;
	}
	
	public static Map<String, Double> returnCommission(){
		Map<String, Double> lists = new HashMap<>();
		
		for( SellerComsCal scc : SellerComsCal.values() ) {
			
			lists.put(scc.prodNum, scc.commission);
		}
		
		return lists;

	}
	
}
