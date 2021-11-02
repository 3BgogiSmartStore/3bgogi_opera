package com.gogi.proj.store.seller.exchange.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.proj.aligo.util.AligoSendingForm;
import com.gogi.proj.aligo.util.AligoVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.store.seller.exchange.model.SellerExchangeService;
import com.gogi.proj.store.seller.exchange.vo.SellerExchangeVO;
import com.gogi.proj.store.seller.model.SellerInfoService;
import com.gogi.proj.store.seller.util.SellerComsCal;

@Controller
public class SellerExchangeController {

	private static final Logger logger = LoggerFactory.getLogger(SellerExchangeController.class);
	
	public static final int exchangeMin = 500000;
	
	@Autowired
	private SellerExchangeService sellerExchangeService;
	
	@Autowired
	private SellerInfoService sellerInfoService;
	
	
	/**
	 * 
	 * @MethodName : sellerSellListSearch
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 셀러의 판매된 상품 조회
	 */
	@RequestMapping(value="/seller/search.do", method=RequestMethod.GET)
	public String sellerSellListSearch(OrderSearchVO osVO, Model model) {
		Authentication auth = null;
		AdminVO adVO = null;
		
		try {
		
			auth = SecurityContextHolder.getContext().getAuthentication();
			adVO = (AdminVO) auth.getPrincipal();
			
		}catch(ClassCastException e) {
			
			model.addAttribute("msg","로그인 세션이 만료되었습니다.");
			model.addAttribute("url","/login.do");
			
			return "common/message";
		}

		
		/*List<OrdersVO> sellerProdList = sellerInfoService.selectSellerSalesVolume(adVO.getUsername());

		model.addAttribute("sellerProdList", sellerProdList);*/
		
		return "redirect:/seller/exchange.do";
	}
	
	/**
	 * 
	 * @MethodName : sellerExchageGet
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 셀러가 환전할 수 있는 총 금액 표기
	 */
	@RequestMapping(value="/seller/exchange.do", method=RequestMethod.GET)
	public String sellerExchangeGet(Model model) {
		
		Authentication auth = null;
		AdminVO adVO = null;
		
		try {
		
			auth = SecurityContextHolder.getContext().getAuthentication();
			adVO = (AdminVO) auth.getPrincipal();
			
		}catch(ClassCastException e) {
			
			model.addAttribute("msg","로그인 세션이 만료되었습니다.");
			model.addAttribute("url","/login.do");
			
			return "common/message";
		}
		
		List<OrdersVO> sellerProdList = sellerInfoService.selectSellerSalesVolume(adVO.getUsername());
		AdminVO adminVO = sellerInfoService.selectSellerInfoByAdminId(adVO.getUsername());
		SellerExchangeVO seVO = sellerExchangeService.exchangePosiv(adVO);
		
		SellerExchangeVO holdingExchange = sellerExchangeService.holdingExchangePermission(adminVO);
		
		int totalExchangePrice = sellerExchangeService.exchangePermitHistory(adminVO);
		
		Map<String, Double> prodComsList = SellerComsCal.returnCommission();
		
		
		model.addAttribute("totalExchangePrice", totalExchangePrice);
		model.addAttribute("prodComsList", prodComsList);
		model.addAttribute("holdingExchange", holdingExchange);
		model.addAttribute("seVO", seVO);
		model.addAttribute("sellerProdList", sellerProdList);
		model.addAttribute("exchangeMin", exchangeMin);
		
		return "seller/exchange";
	}
	
	
	/**
	 * 
	 * @MethodName : sellerExchangeHistoryListGet
	 * @date : 2021. 10. 14.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 셀러 환전 내역 조회
	 */
	@RequestMapping(value="/seller/exchange_history.do", method=RequestMethod.GET)
	public String sellerExchangeHistoryListGet(Model model) {
		
		Authentication auth = null;
		AdminVO adVO = null;
		
		try {
		
			auth = SecurityContextHolder.getContext().getAuthentication();
			adVO = (AdminVO) auth.getPrincipal();
			
		}catch(ClassCastException e) {
			
			model.addAttribute("msg","로그인 세션이 만료되었습니다.");
			model.addAttribute("url","/login.do");
			
			return "common/message";
		}
		
		// 셀러 정보 조회
		AdminVO adminVO = sellerInfoService.selectSellerInfoByAdminId(adVO.getUsername());
		
		List<SellerExchangeVO> sellerExchangeList = sellerExchangeService.searchSellerExchangeHistory(adminVO);
		
		model.addAttribute("sellerExchangeList", sellerExchangeList);
		
		return "seller/exchange_history";
	}
	
	
	
	/**
	 * 
	 * @MethodName : sellerExchangePost
	 * @date : 2021. 10. 13.
	 * @author : Jeon KiChan
	 * @param seVO
	 * @param model
	 * @return
	 * @메소드설명 : 셀러 환전하기 
	 */
	@RequestMapping(value="/seller/exchange.do", method=RequestMethod.POST)
	public String sellerExchangePost(@ModelAttribute SellerExchangeVO seVO, @RequestParam String userAuth, Model model, BindingResult bindingResult) {
		
		String msg = "";
		String url = "/seller/exchange.do";
		
		//사용자 로그인 확인
		Authentication auth = null;
		AdminVO adVO = null;
		
		try {
		
			auth = SecurityContextHolder.getContext().getAuthentication();
			adVO = (AdminVO) auth.getPrincipal();
			
		}catch(ClassCastException e) {
		
			msg = "사용자 인증이 만료되었습니다";
			url = "/login.do";
			return "common/message";
			
		}
		
		if(bindingResult.hasErrors()){ // binding 에러가 된 경우, 에러 종류 찍기 
			for (ObjectError allError : bindingResult.getAllErrors()) { 
				logger.info("seller exchange.POST.req binding error = {}",allError.toString());
				
			} 
			
			return "redirect:/seller/exchange.do"; 
		}
		
		msg = sellerExchangeService.insertExchange(seVO, adVO, userAuth);
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : sellerAuthNumUpdate
	 * @date : 2021. 10. 11.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 인증 문자 보내기 기능
	 */
	@RequestMapping(value="/seller/auth.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	@ResponseBody
	public String sellerAuthNumUpdate() {

		Authentication auth = null;
		AdminVO adVO = null;
		
		try {
		
			auth = SecurityContextHolder.getContext().getAuthentication();
			adVO = (AdminVO) auth.getPrincipal();
			
		}catch(ClassCastException e) {
		
			return "사용자 인증이 만료되었습니다";
		}
		
		String authRandomNum = ""+ ((int)(Math.random() * 1000000));

		AdminVO adInfo = sellerInfoService.selectSellerInfoByAdminId(adVO.getUsername());
		
		adInfo.setSellerAuthNum(authRandomNum);
		
		int authUpdateResult = sellerInfoService.updateSellerAuthNum(adInfo);
		
		if(authUpdateResult == 0 ) {
			return "인증번호 생성 실패";
		}
		
		
		AligoSendingForm asf = new AligoSendingForm();
		
		AligoVO aligoVO = new AligoVO();
		
		String msg = "[삼형제고기]\n"+adInfo.getAdminName()+" 님의 인증번호는 "+authRandomNum+" 입니다";
		
		aligoVO.setMsg(msg);
		aligoVO.setReceiver(adInfo.getAdminPhone()+"|"+adInfo.getAdminName());
		
		String smsMs = asf.smsMsg(aligoVO);

		
		msg = "휴대폰으로 발송된 문자의 인증번호를 입력해주세요";

		
		return msg;
	}
	
	
	/**
	 * 
	 * @MethodName : sellerAuthNumCheck
	 * @date : 2021. 10. 12.
	 * @author : Jeon KiChan
	 * @param authNum
	 * @return
	 * @메소드설명 : 인증 문자 비교하기
	 */
	@RequestMapping(value="/seller/auth_check.do", method=RequestMethod.GET)
	@ResponseBody
	public int sellerAuthNumCheck(@RequestParam String authNum) {
		
		int result = 0;
		
		Authentication auth = null;
		AdminVO adVO = null;
		
		try {
		
			auth = SecurityContextHolder.getContext().getAuthentication();
			adVO = (AdminVO) auth.getPrincipal();
			
		}catch(ClassCastException e) {
		
			return 0;
		}
		
		String sellerAuthNum = sellerInfoService.selectAuthNumBySellerId(adVO);
		
		//인증번호가 없을 경우
		if(sellerAuthNum.equals("")) {
			return 0;
			
		}
		
		logger.info("authNum = {}, sellerAuthNum = {}", authNum, sellerAuthNum);
		if(authNum.equals(sellerAuthNum)) {
			result = 1;
			
		}else {
			result = 0;
			
		}
		
		return result;
	}
	
}
