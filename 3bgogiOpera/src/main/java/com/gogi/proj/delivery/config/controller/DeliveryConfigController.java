package com.gogi.proj.delivery.config.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.model.OrdersService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping("/delivery/config")
public class DeliveryConfigController {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryConfigController.class);
	
	
	@Autowired
	private DeliveryConfigService dcService;
	
	@Autowired
	private OrdersService ordersService;
	
	
	/**
	 * 
	 * @MethodName : delivInvoiceNumCheck
	 * @date : 2020. 12. 1.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 송장 나올 때 체크
	 */
	@RequestMapping(value="/deliv_num_check.do", method=RequestMethod.GET)
	public String delivInvoiceNumCheck(@ModelAttribute OrderSearchVO osVO,Model model) {
		
		List<OrdersVO> invoiceNum = ordersService.selectCreateInvoiceNum();
		List<OrdersVO> orList = dcService.selectDelivNumCheckTarget(osVO);
		
		model.addAttribute("orList", orList);
		model.addAttribute("invoiceNum", invoiceNum);
		
		return "delivery/deliv_num_check";
	}
	
	
	/**
	 * 
	 * @MethodName : earlyDelivAreaGet
	 * @date : 2021. 11. 24.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 배송 불가 지역 체크하기
	 */
	@RequestMapping(value="/early_deliv.do", method=RequestMethod.GET)
	public String earlyDelivAreaGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		List<EarlyDelivTypeVO> edtList = dcService.earlyDelivType();
		
		int totalRecord = dcService.earlyDelivAreaCount(osVO);
		
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<EarlyDelivTypeVO> edaList = dcService.selectEarlyDelivArea(osVO);
		
		model.addAttribute("edaList", edaList);
		model.addAttribute("osVO", osVO);
		model.addAttribute("edtList", edtList);
		
		return "delivery/config/early_deliv";
	}
	
	
	/**
	 * 
	 * @MethodName : earlyDelivAreaPost
	 * @date : 2021. 11. 24.
	 * @author : Jeon KiChan
	 * @param edaVO
	 * @param model
	 * @return
	 * @메소드설명 : 배송 불가 지역 추가하기
	 */
	@RequestMapping(value="/early_deliv.do", method=RequestMethod.POST)
	public String earlyDelivAreaPost(@ModelAttribute EarlyDelivAreaVO edaVO, Model model) {
		String msg = "";
		String url = "/delivery/config/early_deliv.do";
		
		int result = dcService.insertEarlyDelivArea(edaVO);
		
		if(result > 0) {
			msg = "배송 불가 지역 추가 완료";
		}else {
			msg = "배송 불가 지역 추가 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : earlyDelivAreaDelete
	 * @date : 2021. 11. 25.
	 * @author : Jeon KiChan
	 * @param edaVO
	 * @param model
	 * @return
	 * @메소드설명 : 배송 불가 지역 삭제하기
	 */
	@RequestMapping(value="/early_deliv_del.do", method=RequestMethod.GET)
	public String earlyDelivAreaDelete(@ModelAttribute EarlyDelivAreaVO edaVO, Model model) {
		
		String msg = "";
		String url = "/delivery/config/early_deliv.do";
		
		int result = dcService.deleteEarlyDelivArea(edaVO);
		
		if(result > 0) {
			msg = "배송 불가 지역 삭제 완료";
		}else {
			msg = "배송 불가 지역 삭제 완료 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
}

