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
		List<OrdersVO> monthTotalSales = dashboardService.monthTotalSales();
		List<CustomerReviewVO> reviewList = customerReviewService.selectSaleFlowReview();
		
		int matching_fail = analyService.notMatchingOrder();
		int dont_grant_invoice_num  = analyService.dontGrantInvoiceOrder();
		int output_weiting_order  = analyService.sendingWeitOrder();
		int output_order  = analyService.sendingFinishOrder();
		int deposit_order  = analyService.depositOrder();
		
		
		model.addAttribute("productStockList", productStockList);
		model.addAttribute("threeMonthTotalSales", threeMonthTotalSales);
		model.addAttribute("monthTotalSales", monthTotalSales);
		
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
	 * @메소드설명 : 3개월 간 총 매출
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
	 * @메소드설명 : 이번 달 일별 매출
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
	 * @메소드설명 : 모든 매입, 매출에 관련 정보 보기
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
		
		//작업된 정육 무게
		List<CostIoVO> meatWeightList = dashboardService.selectMeatTotalWeightInSale(osVO);
		int meatSales = dashboardService.selectMeatTotalPriceInSale(osVO);
		
		//양념육 정육 무게
		List<CostIoVO> seasoningMeatWeightList = dashboardService.selectSeasoningMeatTotalWeightInSale(osVO);
		int seasoningMeatSales = dashboardService.selectSeasoningMeatTotalPriceInSale(osVO);
		
		//간편조리 
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
	 * @메소드설명 : 상위카테고리 키워드로 하위 카테고리 목록 자세히 가져오기 ajax 타입으로
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
	 * @메소드설명 : 거래내역서 총액, 정육, 간편 등 엑셀 파일로 불러오기
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
