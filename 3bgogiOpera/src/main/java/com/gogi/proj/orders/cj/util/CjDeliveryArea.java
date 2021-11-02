package com.gogi.proj.orders.cj.util;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public enum CjDeliveryArea {
	UNPOSIV(false , Arrays.asList("전라도", "전라북도", "전라남도", "전북", "전남", "충청도", "충청북도", "충청남도", "충북", "충남", "강원", "경상도", "경상북도", "경상남도", "경북", "경남", "대구", "광주", "대전", "울산", "세종", "제주", "부산")),
	POSIV(true, Collections.EMPTY_LIST);
	
	private boolean isDeliv;
	private List<String> areaList;
	
	CjDeliveryArea(boolean isDeliv, List<String> areaList) {
		// TODO Auto-generated constructor stub
		this.isDeliv = isDeliv;
		this.areaList =  areaList;
	}
	
	public static CjDeliveryArea findDelivPosivArea(String area) {
		
		return Arrays.stream(CjDeliveryArea.values())
				.filter(CjDeliveryArea -> CjDeliveryArea.hasAreaName(area))
				.findAny()
				.orElse(POSIV);
	}
	
	
	public boolean hasAreaName(String area) {
		
		return areaList.stream()
				.anyMatch( areas -> area.split(" ")[0].indexOf(areas)  == -1 ? false : true);
	}
}
