package com.gogi.proj.dashboard.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.analytics.model.AnalyticsService;
import com.gogi.proj.dashboard.model.DashboardService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;
import com.gogi.proj.review.model.CustomerReviewService;
import com.gogi.proj.review.vo.CustomerReviewVO;
import com.gogi.proj.stock.model.StockService;

@Controller
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private AnalyticsService analyService;
	
	@Autowired
	private CustomerReviewService customerReviewService;
	
	@RequestMapping(value="/dashboard/sale_flow_view.do", method=RequestMethod.GET)
	public String saleFlowView(Model model) {
		
		List<ProductOptionVO> productStockList = stockService.productOptionStockAlarm();
		List<OrdersVO> threeMonthTotalSales = dashboardService.threeMonthTotalSales();
		List<CustomerReviewVO> reviewList = customerReviewService.selectSaleFlowReview();
		Map<String, Object> deliveryTypeResult =  analyService.mainDeliveryTypeResult();
		
		int matching_fail = analyService.notMatchingOrder();
		int dont_grant_invoice_num  = analyService.dontGrantInvoiceOrder();
		int output_weiting_order  = analyService.sendingWeitOrder();
		int output_order  = analyService.sendingFinishOrder();
		int deposit_order  = analyService.depositOrder();
		
		
		model.addAttribute("productStockList", productStockList);
		model.addAttribute("threeMonthTotalSales", threeMonthTotalSales);
		
		model.addAttribute("deliveryTypeResult", deliveryTypeResult);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("matching_fail", matching_fail);
		model.addAttribute("dont_grant_invoice_num", dont_grant_invoice_num);
		model.addAttribute("output_weiting_order", output_weiting_order);
		model.addAttribute("output_order", output_order);
		model.addAttribute("deposit_order", deposit_order);
		
		return "dashboard/sale_flow_view";
	}
	
	
	/**
	 * 
	 * @MethodName : threeMonthTotalSalesAjax
	 * @date : 2022. 1. 13.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ?????? ??? ??? ?????? ???????????? ( ????????? ????????? ??? ????????? )
	 */
	@RequestMapping(value="/dashboard/three_month_sales.do", method=RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> threeMonthTotalSalesAjax(){
		
		return dashboardService.threeMonthTotalSales();
	}
	
	/**
	 * 
	 * @MethodName : monthTotalSalesAjax
	 * @date : 2022. 1. 13.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ?????? 11?????? ?????? ?????? ???????????? ( ????????? ????????? ??? ?????????)
	 */
	@RequestMapping(value="/dashboard/month_sales.do", method=RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> monthTotalSalesAjax(){
		
		return dashboardService.monthTotalSales();
	}
	
	
	/**
	 * 
	 * @MethodName : totalPurchaseContrastTotalSales
	 * @date : 2022. 1. 5.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @??????????????? : ?????? ??????, ????????? ?????? ?????? ??????
	 */
	@RequestMapping(value="/dashboard/dashboard_purchase_contrast_sales.do", method={RequestMethod.GET, RequestMethod.POST})
	public String totalPurchaseContrastTotalSales(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null || osVO.getDateStart().equals("")) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}
		
		List<ProductInfoVO> piTypeList = dashboardService.selectProductTaxInfoType(osVO);
		
		//????????? ?????? ??????
		List<CostIoVO> meatWeightList = dashboardService.selectMeatTotalWeightInSale(osVO);
		int meatSales = dashboardService.selectMeatTotalPriceInSale(osVO);
		
		//????????? ?????? ??????
		List<CostIoVO> seasoningMeatWeightList = dashboardService.selectSeasoningMeatTotalWeightInSale(osVO);
		int seasoningMeatSales = dashboardService.selectSeasoningMeatTotalPriceInSale(osVO);
		
		//???????????? 
		List<OrdersVO> mealkitList = dashboardService.selectMealkitTotalSale(osVO);
		int mealkitSales = dashboardService.selectMealkikTotalSalePrice(osVO);
		
		model.addAttribute("osVO", osVO);
		model.addAttribute("piTypeList", piTypeList);
		
		model.addAttribute("meatWeightList", meatWeightList);
		model.addAttribute("meatSales", meatSales);
		
		model.addAttribute("seasoningMeatWeightList", seasoningMeatWeightList);
		model.addAttribute("seasoningMeatSales", seasoningMeatSales);
		
		model.addAttribute("mealkitList", mealkitList);
		model.addAttribute("mealkitSales", mealkitSales);
		
		
		return "dashboard/dashboard_purchase_contrast_sales";
	}
	
	
	
	/**
	 * 
	 * @MethodName : selectProductTaxInfoBySuperPiTypeAjax
	 * @date : 2022. 1. 5.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @??????????????? : ?????????????????? ???????????? ?????? ???????????? ?????? ????????? ???????????? ajax ????????????
	 */
	@RequestMapping(value="/dashboard/category_type_list.do", method=RequestMethod.GET)
	@ResponseBody
	public List<ProductInfoVO> selectProductTaxInfoBySuperPiTypeAjax(@ModelAttribute OrderSearchVO osVO){
		
		return dashboardService.selectProductTaxInfoBySuperPiType(osVO);
	}
	
	
	/**
	 * 
	 * @MethodName : productTaxInfoExcelResult
	 * @date : 2022. 1. 13.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @??????????????? : ??????????????? ??????, ??????, ?????? ??? ?????? ????????? ????????????
	 */
	@RequestMapping(value="/dashboard/prod_tax_excel.do", method=RequestMethod.POST)
	public ModelAndView productTaxInfoExcelResult(@ModelAttribute OrderSearchVO osVO) {
		
		File excelFile = dashboardService.productTaxInfoExcelWrite(osVO);
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", excelFile);
		
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
}
