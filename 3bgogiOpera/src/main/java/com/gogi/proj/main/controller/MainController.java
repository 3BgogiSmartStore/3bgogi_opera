package com.gogi.proj.main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.proj.analytics.model.AnalyticsService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost_io.model.CostIoService;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.model.StockService;

@Controller
@RequestMapping("/main")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private CostIoService ciService;
	
	@Autowired
	private AnalyticsService analyService;
	
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value="/home.do", method=RequestMethod.GET)
	public String Home( Model model) {
		
		OrderSearchVO osVO = new OrderSearchVO();
		
		osVO.setMinNum(0);
		osVO.setMaxNum(20);
		
		List<CostDetailVO> costInputList = ciService.selectCostInputList(osVO);
		List<Map<String, Object>> deliveryList = analyService.selectTodayDeliveryCount();
		//List<Map<String, Object>> deliveryResult = analyService.selectMainDeliveryResult();
		
		List<ProductOptionVO> productOptionList = stockService.productOptionStockAlarm();
		Map<String, Object> deliveryTypeResult =  analyService.mainDeliveryTypeResult();
		
		
		int matching_fail = analyService.notMatchingOrder();
		int dont_grant_invoice_num  = analyService.dontGrantInvoiceOrder();
		int output_weiting_order  = analyService.sendingWeitOrder();
		int output_order  = analyService.sendingFinishOrder();
		int deposit_order  = analyService.depositOrder();
		int productInputDontPerm = stockService.productInputDontPerm();
		
		model.addAttribute("deliveryTypeResult", deliveryTypeResult);
		model.addAttribute("deliveryList", deliveryList);
		model.addAttribute("costInputList", costInputList);
		//model.addAttribute("deliveryResult", deliveryResult);
		model.addAttribute("productOptionList", productOptionList);
		
		
		model.addAttribute("matching_fail", matching_fail);
		model.addAttribute("dont_grant_invoice_num", dont_grant_invoice_num);
		model.addAttribute("output_weiting_order", output_weiting_order);
		model.addAttribute("output_order", output_order);
		model.addAttribute("deposit_order", deposit_order);
		model.addAttribute("productInputDontPerm", productInputDontPerm);
		
		return "menu/main";
		
	}
	
	
	/**
	 * 
	 * @MethodName : selectSevenDaysOutPutProductQtyMainPage
	 * @date : 2021. 12. 8.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 입력된 주문서 일주일간의 개수 나타내기 (발송기한 기준으로)
	 */
	@RequestMapping(value = "/inserting_orders.do", method = RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> insertOrderCountBySendingDeadlineMainPage() {

		return analyService.insertOrderCountBySendingDeadline();
	}
	
	/**
	 * 
	 * @MethodName : insertReservOrderCountBySendingDeadlineMainPage
	 * @date : 2021. 12. 15.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 예약된 주문서 일주일간의 개수 나타내기 (발송기한 기준으로)
	 */
	@RequestMapping(value = "/inserting_reserv_orders.do", method = RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> insertReservOrderCountBySendingDeadlineMainPage() {

		return analyService.insertReservOrderCountBySendingDeadline();
	}
	
}
