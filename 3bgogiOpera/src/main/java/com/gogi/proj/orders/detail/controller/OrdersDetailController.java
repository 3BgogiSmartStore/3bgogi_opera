package com.gogi.proj.orders.detail.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.detail.model.OrderDetailService;
import com.gogi.proj.orders.model.OrdersService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.util.PageUtility;

@Controller
public class OrdersDetailController {

	private static final Logger logger = LoggerFactory.getLogger(OrdersDetailController.class);
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private DeliveryConfigService dcService;
	
	@Autowired
	private AllClassificationCodeService accService;

	/**
	 * 
	 * @MethodName : orderDetailSearch
	 * @date : 2021. 12. 28.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 고객 주문 상세 사항 검색하기
	 */
	@RequestMapping(value="/order_detail/order_search.do", method={RequestMethod.GET, RequestMethod.POST})
	public String orderDetailSearch(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateType() == null) {
			osVO.setDateType("or_regdate");
		}
		
		if(osVO.getSearchKeyword() != null && ( osVO.getSearchType().equals("orderNames") || osVO.getSearchType().equals("orderNum"))) {
			String [] searchList = osVO.getSearchKeyword().split(",");
			List<String> searchLists = new ArrayList<String>();
			for(int i =0; i<searchList.length; i++) {
				searchLists.add(searchList[i]);
			}
			osVO.setSearchKeywordList(searchLists);
		}
		
		int totalRecord = orderDetailService.selectCsDetailAllSearchCounting(osVO);

		if(osVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<OrdersVOList> orderList = orderDetailService.selectCsDetailAllSearch(osVO);
		
		List<StoreSectionVO> ssList =configService.selectStoreSectionList();
		List<OrdersVO> insertStoreOrderList = ordersService.selectOrdersCountingByInputDate();
		List<OrdersVO> invoiceNum = ordersService.selectCreateInvoiceNum();
		List<EarlyDelivTypeVO> edtList = dcService.earlyDelivType();
		
		model.addAttribute("invoiceNum", invoiceNum);
		model.addAttribute("insertStoreOrderList", insertStoreOrderList);
		model.addAttribute("ssList", ssList);
		model.addAttribute("orderList",orderList);
		model.addAttribute("osVO", osVO);
		model.addAttribute("edtList", edtList);
		
		return "orders/detail_search/order_info";
		
	}
	
	
	/**
	 * 
	 * @MethodName : deleteOrderEachs
	 * @date : 2021. 12. 29.
	 * @author : Jeon KiChan
	 * @param request
	 * @param orPks
	 * @return
	 * @메소드설명 : 주문서 개별 삭제하기
	 */
	@RequestMapping(value="/order_detail/delete_each_order.do", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int deleteOrderEachs(HttpServletRequest request, @RequestParam List<Integer> orPks ) {
		// 관리자 로그인 여부 확인
		Authentication auth = null;
		AdminVO adminVO = null;
								
		try {
								
			auth = SecurityContextHolder.getContext().getAuthentication();
			adminVO = (AdminVO) auth.getPrincipal();
									
		}catch(ClassCastException e) {

			return -1;
		}
		
		return orderDetailService.deleteOrderEachs(orPks, request.getRemoteAddr(), adminVO.getUsername());
				
	}
	
	
	
	@RequestMapping(value="/order_detail/cancle_each_order.do", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int cancledOrderEachs(HttpServletRequest request, @RequestParam List<Integer> orPks ) {
		// 관리자 로그인 여부 확인
		Authentication auth = null;
		AdminVO adminVO = null;
								
		try {
								
			auth = SecurityContextHolder.getContext().getAuthentication();
			adminVO = (AdminVO) auth.getPrincipal();
									
		}catch(ClassCastException e) {

			return -1;
		}
		
		return orderDetailService.cancledOrderEachs(orPks, request.getRemoteAddr(), adminVO.getUsername());
				
	}
	
	
	
	@RequestMapping(value="/order_detail/cancled_order_restore.do", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int cancleBackOrderEachs(HttpServletRequest request, @RequestParam List<Integer> orPks ) {
		// 관리자 로그인 여부 확인
		Authentication auth = null;
		AdminVO adminVO = null;
								
		try {
								
			auth = SecurityContextHolder.getContext().getAuthentication();
			adminVO = (AdminVO) auth.getPrincipal();
									
		}catch(ClassCastException e) {

			return -1;
		}
		
		return orderDetailService.cancleBackOrderEachs(orPks, request.getRemoteAddr(), adminVO.getUsername());
				
	}
	
	
	/**
	 * 
	 * @MethodName : changeProductOptionPageGet
	 * @date : 2019. 11. 27.
	 * @author : Jeon KiChan
	 * @param ordersVO
	 * @param model
	 * @return
	 * @메소드설명 : 상품 변경 페이지
	 */
	@RequestMapping(value="/order/config/multi_change_product_option.do", method=RequestMethod.GET)
	public String changeProductOptionPageGet(@RequestParam List<Integer> orPks, Model model) {
		
		List<ClassificationVO> ccList = accService.selectClassificationList();
		
		model.addAttribute("orPks", orPks);
		model.addAttribute("ccList", ccList);
		
		return "orders/config/change_multi_product_option";
	}
	
	
	/**
	 * 
	 * @MethodName : changeProductOptionPagePost
	 * @date : 2019. 11. 29.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 상품 변경 
	 */
	@RequestMapping(value="/order/config/multi_change_product_option.do", method=RequestMethod.POST)
	@ResponseBody
	public int changeProductOptionPagePost(HttpServletRequest request,@RequestParam List<Integer> orPks, 
																		@ModelAttribute OrdersVO orVO) {

		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVO = (AdminVO)auth.getPrincipal();
		
		boolean changeStat = false;
		int result = orderDetailService.productMultiChange(orPks, orVO, request.getRemoteAddr(), adminVO.getUsername());

		return result;
	}
	
	
}
