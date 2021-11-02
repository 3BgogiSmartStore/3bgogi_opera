package com.gogi.proj.store.seller.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.gogi.proj.aligo.util.AligoSendingForm;
import com.gogi.proj.aligo.util.AligoVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.store.seller.exchange.model.SellerExchangeService;
import com.gogi.proj.store.seller.exchange.vo.SellerExchangeVO;
import com.gogi.proj.store.seller.model.SellerInfoService;
import com.gogi.proj.util.PageUtility;

@Controller
public class SellerInfoController {

	private static final Logger logger = LoggerFactory.getLogger(SellerInfoController.class);
	
	
	
	@Autowired
	private SellerInfoService sellerInfoService;
	
	@Autowired
	private SellerExchangeService sellerExchangeService;
	
	/**
	 * 
	 * @MethodName : sellerListget
	 * @date : 2021. 10. 5.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 등록된 셀러 목록 확인
	 */
	@RequestMapping(value="/seller/manage/list.do", method=RequestMethod.GET)
	public String sellerListget(@ModelAttribute OrderSearchVO osVO, Model model) {
		
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
		
		if(osVO.getSearchType() == null || osVO.getSearchType().equals("")) {
			osVO.setSearchType("1");
		}
		
		int totalRecord = sellerInfoService.selectAllSellerListCount(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}

		List<AdminVO> sellerList = sellerInfoService.selectAllSellerList(osVO);
		
		model.addAttribute("osVO", osVO);
		model.addAttribute("sellerList", sellerList);
		
		return "seller/manage/list";
		
	}
	
	
	/**
	 * 
	 * @MethodName : searchSellerByAdminPk
	 * @date : 2021. 10. 6.
	 * @author : Jeon KiChan
	 * @param adminVO
	 * @param model
	 * @return
	 * @메소드설명 : 셀러 자세히 조회 및 수정 페이지로 이동
	 */
	@RequestMapping(value="/seller/manage/update.do", method=RequestMethod.GET)
	public String searchSellerByAdminPk(@ModelAttribute AdminVO adminVO, Model model) {
		
		AdminVO adVO = sellerInfoService.selectSellerByAdminPk(adminVO);
		
		List<SellerExchangeVO> exchangeList = sellerExchangeService.selectAllSellerExchangeByAdminPk(adminVO);
		
		
		model.addAttribute("adVO", adVO);
		model.addAttribute("exchangeList", exchangeList);
		
		return "seller/manage/update";
	}
	
	
	/**
	 * 
	 * @MethodName : updateSellerInfo
	 * @date : 2021. 10. 6.
	 * @author : Jeon KiChan
	 * @param adminVO
	 * @param model
	 * @return
	 * @메소드설명 : 셀러 정보 수정
	 */
	@RequestMapping(value="/seller/manage/update.do", method=RequestMethod.POST)
	public String updateSellerInfo(@ModelAttribute AdminVO adminVO, Model model) {
		
		String msg = "";
		String url = "/seller/manage./update.do?adminPk="+adminVO.getAdminPk();
		
		if(adminVO.getAdminName() == null || adminVO.getAdminName().equals("")) {
			msg = "셀러의 이름이 빈 칸입니다";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}if(adminVO.getAdminPhone() == null || adminVO.getAdminPhone().equals("")) {
			msg = "셀러의 연락처가 빈 칸입니다";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}if(adminVO.getSellerProdList()== null || adminVO.getSellerProdList().equals("")) {
			msg = "셀러의 판매 상품이 빈 칸입니다";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}
		
		int result = sellerInfoService.updateSellerAdminInfo(adminVO);
		
		if(result == 0 ) {
			msg = "수정 에러";
		}else {
			msg = "셀러의 정보가 변경되었습니다";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : addSellerGet
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 셀러 추가 페이지
	 */
	@RequestMapping(value="/seller/manage/add.do", method=RequestMethod.GET)
	public String addSellerGet() {
		
		return "seller/manage/add";
	}
	
	
	/**
	 * 
	 * @MethodName : addSellerPost
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @param model
	 * @return
	 * @메소드설명 : 셀러 추가 하기
	 */
	@RequestMapping(value="/seller/manage/add.do", method=RequestMethod.POST)
	public String addSellerPost(@ModelAttribute AdminVO adVO, Model model) {
		
		String msg = "";
		String url = "/seller/manage/add.do";
		
		if(adVO.getAdminName() == null || adVO.getAdminName().equals("")) {
			msg = "셀러의 이름이 빈 칸입니다";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}if(adVO.getAdminPhone() == null || adVO.getAdminPhone().equals("")) {
			msg = "셀러의 연락처가 빈 칸입니다";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}if(adVO.getSellerProdList()== null || adVO.getSellerProdList().equals("")) {
			msg = "셀러의 판매 상품이 빈 칸입니다";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}
		
		int result = sellerInfoService.createSellerAdminInfo(adVO);
		
		if(result == SellerInfoService.SIGNUP_SECCESS ) {
			msg = "셀러 등록이 완료되었습니다";
			url = "/seller/manage/list.do";
			
		}else if(result == SellerInfoService.SIGNUP_ERROR){
			msg = "셀러 등록 중 에러가 발생하였습니다";
			
		}else if(result == SellerInfoService.DUPLI_id) {
			msg = "중복된 id값입니다";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	/**
	 * 
	 * @MethodName : searchSellerInfoBySellerId
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 셀러 정보 확인
	 */
	@RequestMapping(value="/seller/info.do", method=RequestMethod.GET)
	public String searchSellerInfoBySellerId(Model model) {
		
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
		
		AdminVO adminVO = sellerInfoService.selectSellerInfoByAdminId(adVO.getUsername());

		model.addAttribute("adVO", adminVO);
		
		return "seller/update";
	}
	
	/**
	 * 
	 * @MethodName : updateSellerInfoBySellerId
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @param model
	 * @return
	 * @메소드설명 : 셀러 정보 수정하기
	 */
	@RequestMapping(value="/seller/update.do", method=RequestMethod.POST)
	public String updateSellerInfoBySellerId(@ModelAttribute AdminVO adVO, Model model) {
		
		String msg = "";
		String url = "/seller/info.do";
		
		Authentication auth = null;
		AdminVO adminVO = null;
		
		try {
		
			auth = SecurityContextHolder.getContext().getAuthentication();
			adminVO = (AdminVO) auth.getPrincipal();
			
		}catch(ClassCastException e) {
			
			model.addAttribute("msg","로그인 세션이 만료되었습니다.");
			model.addAttribute("url","/login.do");
			
			return "common/message";
		}
		
		AdminVO sellerInfo = sellerInfoService.selectSellerInfoByAdminId(adminVO.getUsername());
		
		adVO.setAdminPk(sellerInfo.getAdminPk());
		
		if(adVO.getAdminPass().length() < 6) {
			msg = "비밀번호를 6자리 이상으로 수정해주세요";
					
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
		}
		int result = sellerInfoService.updateSellerInfo(adVO);
		
		if(result > 0) {
			msg = "정보 수정이 완료되었습니다";

		}else {
			msg = "정보 수정 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : dontPermitExchangeHistoryView
	 * @date : 2021. 10. 22.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 허가 되지 않은 환전 신청 목록 가져오기
	 */
	@RequestMapping(value="/seller/manage/exchange.do", method=RequestMethod.GET)
	public String dontPermitExchangeHistoryView(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		// 관리자 로그인 여부 확인
		Authentication auth = null;
		AdminVO adminVO = null;
		
		try {
		
			auth = SecurityContextHolder.getContext().getAuthentication();
			adminVO = (AdminVO) auth.getPrincipal();
			
		}catch(ClassCastException e) {
			
			model.addAttribute("msg","로그인 세션이 만료되었습니다.");
			model.addAttribute("url","/login.do");
			
			return "common/message";
		}
		
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
		
		int totalRecord = sellerExchangeService.searchDontPermitExchangesCount(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		
		List<AdminVO> sellerExchangeList = sellerExchangeService.searchDontPermitExchanges(osVO);
		
		model.addAttribute("sellerExchangeList", sellerExchangeList);
		model.addAttribute("osVO", osVO);
		
		return "seller/manage/seller_exchange_list";
	}
	
	
	/**
	 * 
	 * @MethodName : permitExchange
	 * @date : 2021. 10. 22.
	 * @author : Jeon KiChan
	 * @param seVO
	 * @return
	 * @메소드설명 : 환전 신청 허가하기
	 */
	@RequestMapping(value="/seller/manage/exchange.do", method=RequestMethod.POST)
	public String permitExchange(
				@ModelAttribute SellerExchangeVO seVO, 
				@RequestParam String adminName, 
				@RequestParam String adminPhone, 
				Model model) {
		
		String msg = "";
		String url = "/seller/manage/exchange.do";
		
		logger.info("seVO = {}", seVO.toString());
		logger.info("adminName = {}, adminPhone = {}", adminName, adminPhone);
		
		// 관리자 로그인 여부 확인
		Authentication auth = null;
		AdminVO adminVO = null;
				
		try {
				
			auth = SecurityContextHolder.getContext().getAuthentication();
			adminVO = (AdminVO) auth.getPrincipal();
					
		}catch(ClassCastException e) {
					
			model.addAttribute("msg","로그인 세션이 만료되었습니다.");
			model.addAttribute("url","/login.do");
					
			return "common/message";
		}
				
		seVO.setSePermitAdmin(adminVO.getUsername());
		
		int result = sellerExchangeService.permitExchangeReq(seVO);
		
		if(result > 0) {			
			AligoSendingForm asf = new AligoSendingForm();
			
			AligoVO aligoVO = new AligoVO();
			
			String sellerMsg = "[삼형제고기 셀러]\n"+adminName+" 님의 환전 신청이 승인되었습니다";
			
			aligoVO.setMsg(sellerMsg);
			aligoVO.setReceiver(adminName+"|"+adminPhone);
			
			msg = adminName+" 님의 환전 신청 허가 완료";
			
		}else {
			msg = adminName+" 님의 환전 신청에 에러가 발생하였습니다.";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	@RequestMapping(value="/seller/manage/seller_exchange_permit_list.do", method=RequestMethod.GET)
	public String exchangePermitList(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		
		// 관리자 로그인 여부 확인
		Authentication auth = null;
		AdminVO adminVO = null;
						
		try {
						
			auth = SecurityContextHolder.getContext().getAuthentication();
			adminVO = (AdminVO) auth.getPrincipal();
							
		}catch(ClassCastException e) {
							
			model.addAttribute("msg","로그인 세션이 만료되었습니다.");
			model.addAttribute("url","/login.do");
							
			return "common/message";
		}
		
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
		
		int totalRecord = sellerInfoService.sellerPermitExchangePagingCount(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<AdminVO> permitList = sellerInfoService.sellerPermitExchangeCount(osVO);
		
		model.addAttribute("osVO", osVO);
		model.addAttribute("permitList", permitList);
		
		return "seller/manage/seller_exchange_permit_list";
	}
	
}
