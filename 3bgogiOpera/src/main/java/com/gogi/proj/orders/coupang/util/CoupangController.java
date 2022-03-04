package com.gogi.proj.orders.coupang.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoupangController {

	@Autowired
	private CoupangConnectUtil ccUtil;
	
	@RequestMapping(value="/coupnag/test.do")
	public String coupangTestConnect() {
		
		ccUtil.getCoupangCanceledOrderList();
		
		return "test/coupang_test";
	}
}
