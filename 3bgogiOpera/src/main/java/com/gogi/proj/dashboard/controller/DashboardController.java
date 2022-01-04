package com.gogi.proj.dashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	
	@RequestMapping(value="/dashboard/sale_flow_view.do", method=RequestMethod.GET)
	public String saleFlowView(Model model) {
		
		return "dashboard/sale_flow_view";
	}
}
