package com.gogi.proj.epost.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;
import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.delivery.config.vo.DoorPassKeywordVO;
import com.gogi.proj.delivery.config.vo.DoorPassVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.epost.api.EpostSendingUtil;
import com.gogi.proj.epost.model.EpostService;
import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.epost.vo.Xsync;
import com.gogi.proj.excel.xlsxWriter;
import com.gogi.proj.freshsolutions.model.FreshSolutionsService;
import com.gogi.proj.orders.cj.model.CjdeliveryService;
import com.gogi.proj.orders.config.model.OrderConfigService;
import com.gogi.proj.orders.lotte.model.LotteService;
import com.gogi.proj.orders.model.OrdersService;
import com.gogi.proj.orders.teamfresh.model.TeamFreshService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost_io.model.CostIoService;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.model.StockService;
import com.gogi.proj.stock.vo.PrintDataSetVO;
import com.gogi.proj.todayPickup.model.TodayPickupService;

@Controller
@RequestMapping(value = "/security")
public class EpostController {

	private static final Logger logger = LoggerFactory.getLogger(EpostController.class);
	
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	//상수
	
	//송장부여
	static final String EPOST_DELIV_SENDING = "http://ship.epost.go.kr/api.InsertOrder.jparcel";
	
	static final String EPOST_DELIV_SENDING_VER_2 = "http://ship.epost.go.kr/api.InsertOrder.jparcel";
	//송장삭제
	static final String EPOST_DELIV_DELETE = "http://ship.epost.go.kr/api.GetResCancelCmd.jparcel";

	@Autowired
	private EpostService epostService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private AllClassificationCodeService accService;
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private CostIoService costIoService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private OrderConfigService orderConfigService;
	
	@Autowired
	private DeliveryConfigService dcService;
	
	@Autowired
	private TodayPickupService todayPickupService;
	
	@Autowired
	private CjdeliveryService cjService;
	
	@Autowired
	private EpostSendingUtil esu;
	
	@Autowired
	private TeamFreshService teamFreshService;
	
	@Autowired
	private LotteService lotteService;
	
	@Autowired
	private FreshSolutionsService freshSolutionsService;

	/*
	 * @RequestMapping(value="/epost.do", method=RequestMethod.GET) public String
	 * epostTestPageGet(Model model) {
	 * 
	 * model.addAttribute("eposts",epostService.selectEpostSendingData().get(1).
	 * toString()); return "security/apiSeedTest"; }
	 */

	
	
	/**
	 * 
	 * @MethodName : epostPagePost
	 * @date : 2020. 2. 4.
	 * @author : Jeon KiChan
	 * @param orderSearchVO
	 * @param model
	 * @return
	 * @메소드설명 : 미수여 송장 확인
	 */
	@RequestMapping(value = "/epost.do", method = RequestMethod.GET)
	public String epostPagePost(@ModelAttribute OrderSearchVO orderSearchVO, Model model) {	
		List<StoreSectionVO> storeSectionList = configService.selectStoreSectionList();
		List<OrdersVO> insertStoreOrderList = ordersService.selectOrdersCountingByInputDate();
		
		List<EarlyDelivTypeVO> edtList = dcService.earlyDelivType();
		
		orderSearchVO.setBlockSize(10);
		
		if(orderSearchVO.getRecordCountPerPage() == 0) {			
			orderSearchVO.setRecordCountPerPage(500);
			
		}
		
		/*osVO.setSearchType("1");
		List<RegDataVO> regList = epostService.selectEpostSendingData(osVO);*/
		
		if(orderSearchVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			orderSearchVO.setDateStart(sdf.format(sevenDays));
			orderSearchVO.setDateEnd(sdf.format(today));
			
		}
		
		if(orderSearchVO.getTotalQtyAlarm() == 0) {
			orderSearchVO.setTotalQtyAlarm(12);
		}
		
		/*if(orderSearchVO.getEdtFk() == 6) {
			orderSearchVO.setEdtFk(6);
			
			orderSearchVO.setOrSerialSpecialNumberList(todayPickupService.selectTodayPickupTargetChecking(orderSearchVO).getOrSerialSpecialNumberList());
			
			int checkingCount = todayPickupService.selectDontGrantTodayPickupDelivOrderListInMonthCounting(orderSearchVO);
			
			orderSearchVO.setTotalRecord(checkingCount);
			
			List<OrdersVO> checkingResultList = todayPickupService.selectDontGrantTodayPickupDelivOrderListInMonth(orderSearchVO);
			
			model.addAttribute("storeSectionList",storeSectionList);
			model.addAttribute("insertStoreOrderList", insertStoreOrderList);
			model.addAttribute("OrderSearchVO", orderSearchVO);
			model.addAttribute("orderList",checkingResultList);
			model.addAttribute("edtList", edtList);
			
			return "delivery/not_sending_list";
			
		}else */
		
		//우체국으로 고정된 목록 가져오기
		List<OrdersVOList> epostAbsDelivList = epostService.selectDontGrantDelivOrderAbsDelivEpost(orderSearchVO);
		
		model.addAttribute("epostAbsDelivList", epostAbsDelivList);
		
		if(orderSearchVO.getEdtFk() == 7) {
			orderSearchVO.setOrSerialSpecialNumberList(teamFreshService.selectTeamFreshDeliveryTargetChecking(orderSearchVO).getOrSerialSpecialNumberList());
			int packingIrreOrderCounting = orderConfigService.selectPackingIrreTargetOrderCounting();
			
			int checkingCount = 0;
			
			List<OrdersVOList> checkingResultList = null;
			
			if(orderSearchVO.getOrSerialSpecialNumberList() == null) {
				orderSearchVO.setTotalRecord(checkingCount);
				
			}else {
				checkingCount = cjService.selectDontGrantCjDelivOrderListInMonthCounting(orderSearchVO);
				
				orderSearchVO.setTotalRecord(checkingCount);
				
				checkingResultList = cjService.selectDontGrantCjDelivOrderListInMonth(orderSearchVO);
			}
			
			List<DoorPassKeywordVO> doorList = dcService.selectAllDoorPassKeyword();
			
			if(checkingResultList != null ) {				
				for( OrdersVOList buyerInfo :  checkingResultList) {
					
					if(buyerInfo.getOrDelivEnter() != null && !buyerInfo.getOrDelivEnter().equals("")) {
						buyerInfo.setOrDelivEnterFlag(true);
						
					}else {					
						buyerInfo = dcService.doorPassCheck(buyerInfo);
						
						if(buyerInfo.getOrDelivEnter() != null && !buyerInfo.getOrDelivEnter().equals("")) {
							buyerInfo.setOrDelivEnterFlag(true);
							
						}else {
							
							for( OrdersVO orderInfo : buyerInfo.getOrVoList()) {
								
								if(orderInfo.getOrDeliveryMessage() != null && !orderInfo.getOrDeliveryMessage().equals("")) {
									
									for(DoorPassKeywordVO dpk : doorList) {
										
										if(orderInfo.getOrDeliveryMessage().contains(dpk.getDpkWord())) {
											buyerInfo.setOrDelivEnterFlag(true);
											break;
											
										}
										
									}
									
									
								}
								
							}
						}
						
					}
				}
			}
			
			model.addAttribute("storeSectionList",storeSectionList);
			model.addAttribute("insertStoreOrderList", insertStoreOrderList);
			model.addAttribute("packingIrreOrderCounting", packingIrreOrderCounting);
			model.addAttribute("OrderSearchVO", orderSearchVO);
			model.addAttribute("orderList",checkingResultList);
			model.addAttribute("edtList", edtList);
			
			return "delivery/not_sending_list";
			
		}else if(orderSearchVO.getEdtFk() == 5){	
			orderSearchVO.setEdtFk(5);
			
			orderSearchVO.setOrSerialSpecialNumberList(cjService.selectCjDeliveryTargetChecking(orderSearchVO).getOrSerialSpecialNumberList());
			int packingIrreOrderCounting = orderConfigService.selectPackingIrreTargetOrderCounting();
			
			int checkingCount = 0;
			
			List<OrdersVOList> checkingResultList = null;
			
			if(orderSearchVO.getOrSerialSpecialNumberList() == null) {
				orderSearchVO.setTotalRecord(checkingCount);
				
			}else {
				checkingCount = cjService.selectDontGrantCjDelivOrderListInMonthCounting(orderSearchVO);
				
				orderSearchVO.setTotalRecord(checkingCount);
				
				checkingResultList = cjService.selectDontGrantCjDelivOrderListInMonth(orderSearchVO);
			}
			
			List<DoorPassKeywordVO> doorList = dcService.selectAllDoorPassKeyword();
			
			if(checkingResultList != null ) {				
				for( OrdersVOList buyerInfo :  checkingResultList) {
					
					if(buyerInfo.getOrDelivEnter() != null && !buyerInfo.getOrDelivEnter().equals("")) {
						buyerInfo.setOrDelivEnterFlag(true);
						
					}else {					
						buyerInfo = dcService.doorPassCheck(buyerInfo);
						
						if(buyerInfo.getOrDelivEnter() != null && !buyerInfo.getOrDelivEnter().equals("")) {
							buyerInfo.setOrDelivEnterFlag(true);
							
						}else {
							
							for( OrdersVO orderInfo : buyerInfo.getOrVoList()) {
								
								if(orderInfo.getOrDeliveryMessage() != null && !orderInfo.getOrDeliveryMessage().equals("")) {
									
									for(DoorPassKeywordVO dpk : doorList) {
										
										if(orderInfo.getOrDeliveryMessage().contains(dpk.getDpkWord())) {
											buyerInfo.setOrDelivEnterFlag(true);
											break;
											
										}
										
									}
									
									
								}
								
							}
						}
						
					}
				}
			}
			
			model.addAttribute("storeSectionList",storeSectionList);
			model.addAttribute("insertStoreOrderList", insertStoreOrderList);
			model.addAttribute("packingIrreOrderCounting", packingIrreOrderCounting);
			model.addAttribute("OrderSearchVO", orderSearchVO);
			model.addAttribute("orderList",checkingResultList);
			model.addAttribute("edtList", edtList);
			
			return "delivery/not_sending_list";
			
		}else if(orderSearchVO.getEdtFk() == 4) {
			
			int packingIrreOrderCounting = orderConfigService.selectPackingIrreTargetOrderCounting();

			
			int checkingCount = lotteService.selectDontGrantLotteDelivOrderListInMonthCounting(orderSearchVO);
			
			orderSearchVO.setTotalRecord(checkingCount);
			
			List<OrdersVOList> checkingResultList = lotteService.selectDontGrantLotteDelivOrderListInMonth(orderSearchVO);
			
			model.addAttribute("storeSectionList",storeSectionList);
			model.addAttribute("insertStoreOrderList", insertStoreOrderList);
			model.addAttribute("packingIrreOrderCounting", packingIrreOrderCounting);
			model.addAttribute("OrderSearchVO", orderSearchVO);
			model.addAttribute("orderList",checkingResultList);
			model.addAttribute("edtList", edtList);
			
			return "delivery/not_sending_list";
			
			
		}else if(orderSearchVO.getEdtFk() == 0 || orderSearchVO.getEdtFk() == 3) {
			
			orderSearchVO.setEdtFk(3);
			
			orderSearchVO.setOrSerialSpecialNumberList(freshSolutionsService.selectFreshSolutionsDeliveryTargetChecking(orderSearchVO).getOrSerialSpecialNumberList());
			
			int packingIrreOrderCounting = orderConfigService.selectPackingIrreTargetOrderCounting();
			
			int checkingCount = 0;
			
			List<OrdersVOList> checkingResultList = null;
			
			if(orderSearchVO.getOrSerialSpecialNumberList() == null) {
				orderSearchVO.setTotalRecord(checkingCount);
				
			}else {
				checkingCount = freshSolutionsService.selectDontGrantFreshSolutionsDelivOrderListInMonthCounting(orderSearchVO);
				
				orderSearchVO.setTotalRecord(checkingCount);
				
				checkingResultList = freshSolutionsService.selectDontGrantFreshSolutionsDelivOrderListInMonth(orderSearchVO);
			}
			
			List<DoorPassKeywordVO> doorList = dcService.selectAllDoorPassKeyword();
			
			if(checkingResultList != null ) {				
				for( OrdersVOList buyerInfo :  checkingResultList) {
					
					if(buyerInfo.getOrDelivEnter() != null && !buyerInfo.getOrDelivEnter().equals("")) {
						buyerInfo.setOrDelivEnterFlag(true);
						
					}else {					
						buyerInfo = dcService.doorPassCheck(buyerInfo);
						
						if(buyerInfo.getOrDelivEnter() != null && !buyerInfo.getOrDelivEnter().equals("")) {
							buyerInfo.setOrDelivEnterFlag(true);
							
						}else {
							
							for( OrdersVO orderInfo : buyerInfo.getOrVoList()) {
								
								if(orderInfo.getOrDeliveryMessage() != null && !orderInfo.getOrDeliveryMessage().equals("")) {
									
									for(DoorPassKeywordVO dpk : doorList) {
										
										if(orderInfo.getOrDeliveryMessage().contains(dpk.getDpkWord())) {
											buyerInfo.setOrDelivEnterFlag(true);
											break;
											
										}
										
									}
									
									
								}
								
							}
						}
						
					}
				}
			}
			
			model.addAttribute("storeSectionList",storeSectionList);
			model.addAttribute("insertStoreOrderList", insertStoreOrderList);
			model.addAttribute("packingIrreOrderCounting", packingIrreOrderCounting);
			model.addAttribute("OrderSearchVO", orderSearchVO);
			model.addAttribute("orderList",checkingResultList);
			model.addAttribute("edtList", edtList);
			
			return "delivery/not_sending_list";
			
		}else {			
			int recordCounting = epostService.selectDontGrantDelivOrderListInMonthCounting(orderSearchVO);
			
			orderSearchVO.setTotalRecord(recordCounting);
			
			List<OrdersVO> orderList = epostService.selectDontGrantDelivOrderListInMonth(orderSearchVO);
			
			int packingIrreOrderCounting = orderConfigService.selectPackingIrreTargetOrderCounting();

			model.addAttribute("OrderSearchVO", orderSearchVO);
			model.addAttribute("orderList",orderList);
			model.addAttribute("storeSectionList",storeSectionList);
			model.addAttribute("insertStoreOrderList", insertStoreOrderList);
			model.addAttribute("packingIrreOrderCounting", packingIrreOrderCounting);
			model.addAttribute("edtList", edtList);
			
			return "delivery/not_sending_list";
		}

	}
	
	@RequestMapping(value="/epost/deliv.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	public String epostDelivSending(@ModelAttribute OrderSearchVO osVO) {
		
		String encryptStr;
		EpostSendingUtil esu = new EpostSendingUtil();
		
		osVO.setSearchType("0");
		StringBuilder failResult = new StringBuilder("");
		int result = 0;
		int totalDeliv = osVO.getOrSerialSpecialNumberList().size();
		int delivCount = 1;
		
		for (int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {
			osVO.setSearchKeyword(osVO.getOrSerialSpecialNumberList().get(i));
			List<RegDataVO> regList = epostService.selectEpostSendingData(osVO);
			
		
			int divCount = 1;
			
			String delivMsg = regList.get(0).getDelivMsg() == null ? "" : regList.get(0).getDelivMsg();
			
			for(int j=0; j < regList.size(); j++ ) {
				String [] productCounting = regList.get(j).getGoodsNm().split(";;");
				
				regList.get(j).setDelivMsg("("+delivCount+"/"+totalDeliv+")"+delivMsg);
				regList.get(j).setOrderNo("("+delivCount+"-"+divCount+"/"+totalDeliv+")"+regList.get(j).getOrderNo());
				
				double countingDiv = (double)(productCounting.length / 8);
				int countingDivMod = (productCounting.length % 8);
				
				if(countingDiv > 0 && countingDivMod != 0) {
					StringBuffer sb = new StringBuffer();
					
					//송장 나누기 개수
					int deliCounting = 1;
					String receiverName = regList.get(j).getRecNm();
					
					for(int duplCounting = 0; duplCounting < productCounting.length; duplCounting++) {
						if((duplCounting + 1) % 8 == 0) {
							sb.append(productCounting[duplCounting]);
							
						}else if(duplCounting == productCounting.length - 1){
							sb.append(productCounting[duplCounting]);
							
						}else {
							sb.append(productCounting[duplCounting]+";;");
						}
						
						if(( (duplCounting + 1) % 8 == 0) || duplCounting == productCounting.length - 1) {
							regList.get(j).setGoodsNm(sb.toString());
							regList.get(j).setRecNm(receiverName+"("+deliCounting+")");
							
							if(deliCounting > 1) {
								regList.get(j).setDelivMsg("("+delivCount+"-"+divCount+"/"+totalDeliv+")"+delivMsg);
								regList.get(j).setRecAddr2(" ********* 해당 송장은 보내지 않습니다 파기해 주세요 *********");
								divCount++;
								
							}
							
							encryptStr=esu.epostEncrypting(regList.get(j).epostDelivToString());
							
							if(deliCounting == 1) {
								try {
									RegDataVO sendingData = esu.epostSending(encryptStr, EpostController.EPOST_DELIV_SENDING);
									int updateResult = epostService.grantRegiNoByOrPk(regList.get(j), sendingData, true);
									if(updateResult != 0) {
										result += updateResult;
										
									}else {
										failResult.append("주문자 : "+regList.get(j).getOrdNm()+" , 송장 부여 실패 ( "+sendingData.getMessage()+" )<br>");
										failResult.append("주문자 : "+regList.get(j).getOrdNm()+" , 송장 부여 실패 ( "+sendingData.getMessage()+" ) deliCounting == 1 <br>");
									}
									
									deliCounting++;
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else {
								try {
									RegDataVO sendingData = esu.epostSending(encryptStr, EpostController.EPOST_DELIV_SENDING);
									int updateResult = epostService.grantRegiNoByOrPk(regList.get(j), sendingData, false);
									if(updateResult != 0) {
										result += updateResult;
										
									}else {
										failResult.append("주문자 : "+regList.get(j).getOrdNm()+" , 송장 부여 실패 ( "+sendingData.getMessage()+" )<br>");
										failResult.append("주문자 : "+regList.get(j).getOrdNm()+" , 송장 부여 실패 ( "+sendingData.getMessage()+" ) else deliCounting <br>");
									}
									
									deliCounting++;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							sb = new StringBuffer();
							
							
						}
					}
				}else {
					
					encryptStr=esu.epostEncrypting(regList.get(j).epostDelivToString());
					
					try {
						RegDataVO sendingData = esu.epostSending(encryptStr, EpostController.EPOST_DELIV_SENDING);
						int updateResult = epostService.grantRegiNoByOrPk(regList.get(j), sendingData, true);
						if(updateResult != 0) {
							result += updateResult;
							
						}else {
							failResult.append("주문자 : "+regList.get(j).getOrdNm()+" , 송장 부여 실패 ( "+sendingData.getMessage()+" )<br>");
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			}
			
			delivCount++;
		}
		
		failResult.append("<br>완료된 송장 개수 = "+result+" 장");
		return failResult.toString();
	}
	
	
	/**
	 * 
	 * @MethodName : delivResultGet
	 * @date : 2020. 8. 11.
	 * @author : Jeon KiChan
	 * @param delivResult
	 * @param model
	 * @return
	 * @메소드설명 : 송장 부여 결과값 표시하기
	 */
	@RequestMapping(value="/deliv_result.do", method=RequestMethod.GET)
	public String delivResultGet(@RequestParam String delivResult, Model model) {
		
		model.addAttribute("delivResult",delivResult);
		
		return "delivery/deliv_result";
	}
	
	
	/**
	 * 
	 * @MethodName : createDelivInvoice
	 * @date : 2020. 9. 9.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 송장 자체 출력 페이지
	 */
	@RequestMapping(value="/create_deliv_invoice.do", method=RequestMethod.POST)
	public String createDelivInvoice(HttpServletRequest request, @ModelAttribute OrderSearchVO osVO, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		List<OrdersVO> orList = new ArrayList<>();
		List<OrdersVO> errorOr = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today= new Date();
		
		String formatDate = sdf.format(today);
		int delivCount = 1;
		
		OrdersVO orVO = null;
		for(int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {
			osVO.setSearchKeyword(osVO.getOrSerialSpecialNumberList().get(i));
			
			orVO = epostService.deliveryPrintTarget(osVO, request.getRemoteAddr(), adminVo.getUsername(), formatDate, delivCount);
			
			if(orVO != null && orVO.getRegiNo() != null) {
				orList.add(orVO);
				delivCount++;
				
			}else {
				errorOr.add(orVO);
			}
		}
		
		for( OrdersVO or : orList) {
			int temp = 0;
			if(or.getProductOptionList().get(0).getProdSorting() == 0 && or.getProductOptionList().size() > 1) {				
				for( int i = 1; i < or.getProductOptionList().size(); i++) {
					if( or.getProductOptionList().get(i).getProdSorting() == 0 ) {
						or.getProductOptionList().get(0).setProdSorting(1);
						break;
					}
				}
			}
		}
		
		Collections.sort(orList);
		
		model.addAttribute("orList",orList);
		model.addAttribute("errorOr",errorOr);
		
		return "delivery/create_deliv_invoice";
	}
	
	
	/**
	 * 
	 * @MethodName : reprintingDelivInvoice
	 * @date : 2020. 9. 25.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 등록된 송장 재출력하기
	 */
	@RequestMapping(value="/reprinting_deliv_invoice.do", method=RequestMethod.POST)
	public String reprintingDelivInvoice(HttpServletRequest request, @ModelAttribute OrderSearchVO osVO, Model model) {
		List<OrdersVO> orList = new ArrayList<>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		for(int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {
			osVO.setSearchKeyword(osVO.getOrSerialSpecialNumberList().get(i));
			orList.add(epostService.deliveryInvoiceNumberReprinting(osVO, request.getRemoteAddr(), adminVo.getUsername() ));
		}
		
		
		for( OrdersVO or : orList) {
			int temp = 0;
			if(or.getProductOptionList().get(0).getProdSorting() == 0 && or.getProductOptionList().size() > 1) {				
				for( int i = 1; i < or.getProductOptionList().size(); i++) {
					if( or.getProductOptionList().get(i).getProdSorting() == 0 ) {
						or.getProductOptionList().get(0).setProdSorting(1);
						break;
					}
				}
			}
		}

		
		Collections.sort(orList);
		
		model.addAttribute("orList",orList);
		
		return "delivery/create_deliv_invoice";
	}
	
	@RequestMapping(value="/orderIO.do", method=RequestMethod.POST)
	public ModelAndView selectedOrdersExcelIO(@ModelAttribute OrderSearchVO osVO){
		
		List<OrdersVOList> orVoList = ordersService.selectedOrderExcelByOrderSerachVOForVegit(osVO);
		
		List<Integer> testInt = new ArrayList<>();
		testInt.add(1);
		testInt.add(3);
		osVO.setSearchKeywordNumList(testInt);
		
		xlsxWriter xw = new xlsxWriter();
		
		List<OrdersVO> orList =  ordersService.selectedOrderExcelByOrderSerachVO(osVO);
		
		List<ExcelOrderSeqVO> eoSeq = accService.selectExcelOrderSeqCodeList();
		
		List<String> idxTitle = new ArrayList<String>();
		idxTitle.add("분류코드");
		idxTitle.add("상품명");
		idxTitle.add("개수");
		
		File file = xw.orderXlsWriter(idxTitle, orList, orVoList, eoSeq, fileProperties.getProperty("file.upload.order_IO_excel.path.test"), "2층 주문서");
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	@RequestMapping(value="/product_label.do", method=RequestMethod.POST)
	public ModelAndView printProductLabel(@ModelAttribute OrderSearchVO osVO) {
		
		List<String> idxTitle = new ArrayList<String>();
		idxTitle.add("수량");
		idxTitle.add("제품명");
		idxTitle.add("중량");
		idxTitle.add("원산지");
		idxTitle.add("원재료 및 함량");
		idxTitle.add("유통기한");
		idxTitle.add("보관방법");
		idxTitle.add("등급");
		idxTitle.add("품목제조보고");
		idxTitle.add("도축장");
		idxTitle.add("이력번호");
		idxTitle.add("바코드");
		idxTitle.add("기타번호");
		
		xlsxWriter xw = new xlsxWriter();
		
		List<PrintDataSetVO> labelList = stockService.selectProductLabel(osVO);
		
		File file = xw.labelXlsWriter(idxTitle, labelList, null, fileProperties.getProperty("file.upload.order_IO_excel.path.test"), "라벨지 데이터셋 ");
		
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	/**
	 * 
	 * @MethodName : epostDelivDelete
	 * @date : 2020. 1. 6.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumberList
	 * @return
	 * @throws Exception 
	 * @메소드설명 : 부여된 송장 삭제시 사용
	 */
	@RequestMapping(value="/epost/delete.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	public String epostDelivDelete(@RequestParam List<String> orSerialSpecialNumberList,HttpServletRequest request) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		return epostService.deleteEpostDelivData(orSerialSpecialNumberList, EPOST_DELIV_DELETE, request.getRemoteAddr(), adminVo.getUsername());
		
	}

	@RequestMapping(value = "/test/mdb.do", method = RequestMethod.GET)
	public String mdbDBTest() throws Exception, SQLException {

		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		Connection conn=DriverManager.getConnection("jdbc:ucanaccess://C:/javatest/V_POSTALCODE.mdb" ,"3bgogi_admin", "ww123123");
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("SELECT ZIPCD FROM VPOSTALCODE");
		while (rs.next()) {
		    System.out.println(rs.getString(1));
		}
		return null;
	}
	
	
	/**
	 * 
	 * @MethodName : selectDelivResultDate
	 * @date : 2020. 9. 29.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 송장발송 결과를 위한 날짜 선택창
	 */
	@RequestMapping(value="/epost/deliv_date.do", method=RequestMethod.GET)
	public String selectDelivResultDate(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		OrderSearchVO osVO = new OrderSearchVO();
		
		osVO.setDateStart(today);
		
		model.addAttribute("osVO",osVO);
		
		return "delivery/deliv_date";
	}
	
	/**
	 * 
	 * @MethodName : delivResult
	 * @date : 2020. 9. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 발송기한을 기준으로 우체국에 송장조회하여 잘보내졌는지 확인
	 */
	@RequestMapping(value="/epost/deliv_sending_result.do", method=RequestMethod.GET)
	public String delivResult(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		List<OrdersVO> deliveryInfoList = null;
		
		try {
			deliveryInfoList = epostService.selectDeliveryInvoiceNumberByDate(osVO);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("deliveryInfoList",deliveryInfoList);
		model.addAttribute("osVO", osVO);
		
		return "delivery/deliv_sending_result";
	}
	
	/*
	 * 현재 사용하지 않는 구형 프레시솔루션 엑셀 다운로드 기능
	 * @RequestMapping(value="/fresh_solution.do", method=RequestMethod.POST)
	public ModelAndView freshSolutionExcelDelivFile(@ModelAttribute OrderSearchVO osVO) {
		
		File file = epostService.freshSolutionInfo(osVO);
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}*/
	
	@RequestMapping(value="/cj_delivery.do", method=RequestMethod.POST)
	public ModelAndView cjDeliveryExcelDelivFile(@ModelAttribute OrderSearchVO osVO,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		File file = cjService.cjDeliveryExcelInfo(osVO, request.getRemoteAddr(), adminVo.getUsername());

		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	/**
	 * 
	 * @MethodName : freshSolutionsDeliveryExcelDelivFile
	 * @date : 2022. 3. 16.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param request
	 * @return
	 * @메소드설명 : 프레시솔루션 엑셀업로드용 파일 생성
	 */
	@RequestMapping(value="/fresh_solutions_delivery.do", method=RequestMethod.POST)
	public ModelAndView freshSolutionsDeliveryExcelDelivFile(@ModelAttribute OrderSearchVO osVO,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();

		File file = freshSolutionsService.fFreshSolutionsDeliveryExcelInfo(osVO, request.getRemoteAddr(), adminVo.getUsername());

		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	
	/**
	 * 
	 * @MethodName : freshSolutionsDeliveryAutoUpload
	 * @date : 2022. 3. 21.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param request
	 * @param model
	 * @return
	 * @메소드설명 : 프레시솔루션 자동 업로드
	 */
	@RequestMapping(value="/fresh_solutions_delivery_auto.do", method=RequestMethod.POST)
	public String freshSolutionsDeliveryAutoUpload(@ModelAttribute OrderSearchVO osVO,HttpServletRequest request, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();

		String result = freshSolutionsService.fFreshSolutionsDeliveryAutoUpload(osVO, request.getRemoteAddr(), adminVo.getUsername(), 0);
		
		String url = "/security/epost.do";
		
		model.addAttribute("msg", result);
		model.addAttribute("url",url);
		
		return "common/message";
	}
	
	/**
	 * 
	 * @MethodName : freshSolutionsDeliveryAutoUpload
	 * @date : 2022. 3. 21.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param request
	 * @param model
	 * @return
	 * @메소드설명 : 프레시솔루션 자동 업로드
	 */
	@RequestMapping(value="/fresh_solutions_delivery_auto_day.do", method=RequestMethod.POST)
	public String freshSolutionsDeliveryAutoUploadForDay(@ModelAttribute OrderSearchVO osVO,HttpServletRequest request, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();

		String result = freshSolutionsService.fFreshSolutionsDeliveryAutoUpload(osVO, request.getRemoteAddr(), adminVo.getUsername(), 1);
		
		String url = "/security/epost.do";
		
		model.addAttribute("msg", result);
		model.addAttribute("url",url);
		
		return "common/message";
	}
	
	/**
	 * 
	 * @MethodName : teamFreshDeliveryExcelDelivFile
	 * @date : 2022. 1. 17.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param request
	 * @param model
	 * @return
	 * @메소드설명 : 팀프레시 송장 부여
	 */
	@RequestMapping(value="/teamfresh_delivery.do", method=RequestMethod.POST)
	public String gnratTeamFreshDeliveryInvoiceNum(@ModelAttribute OrderSearchVO osVO,HttpServletRequest request, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		//, request.getRemoteAddr(), adminVo.getUsername()
		
		String result = teamFreshService.createTeamFreshDelivInvoice(osVO, request.getRemoteAddr(), adminVo.getUsername());

		model.addAttribute("delivResult", result);
		
		return "delivery/deliv_result";
	}
	
	
	
	/**
	 * 
	 * @MethodName : lotteDeliveryExcelFile
	 * @date : 2022. 1. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param request
	 * @param model
	 * @return
	 * @메소드설명 : 롯데택배 송장 임시부여
	 */
	@RequestMapping(value="/lotte_delivery.do", method=RequestMethod.POST)
	public ModelAndView lotteDeliveryExcelFile(@ModelAttribute OrderSearchVO osVO,HttpServletRequest request, Model model) {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		File file = lotteService.lotteDeliveryExcelInfo(osVO, request.getRemoteAddr(), adminVo.getUsername());

		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	
	@RequestMapping(value="/epost/epost_stopped_area_check.do", method=RequestMethod.GET)
	public String epostStoppedAreaCheckGet(Model model) {
		
		OrderSearchVO osVO = new OrderSearchVO();
		
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
		
		model.addAttribute("osVO", osVO);

		return "delivery/config/epost_stopped_area_check";
	}
	
	
	/**
	 * 
	 * @MethodName : epostStoppedAreaCheckPost
	 * @date : 2022. 2. 11.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param cjFlag
	 * @param model
	 * @return
	 * @throws Exception
	 * @메소드설명 : 배송 불가 지역 체크
	 */
	@RequestMapping(value="/epost/epost_stopped_area_check.do", method=RequestMethod.POST)
	public String epostStoppedAreaCheckPost(@ModelAttribute OrderSearchVO osVO,@RequestParam int dawnFlag, Model model) throws Exception {
		
		List<OrdersVO> orderList = dcService.selectOrdersBySendingDeadline(osVO);
		
		List<Xsync> epostResultList = new ArrayList<Xsync>();
		String EPOST_DELIV_STOP = "http://ship.epost.go.kr/api.GetStoppedZipCd.jparcel";
				
		Xsync epost = null;
		
		
		for(OrdersVO orVO : orderList) {
			
			if(dawnFlag == 1) {
				
				
				if( orVO.getOrAbsDelivType() == 0 && freshSolutionsService.isFreshSolutionsDeliveryArea(orVO.getOrShippingAddress(), orVO.getOrShippingAddressDetail(), 0)) {
					//새벽배송으로 가능한 지역
					
				}else {				
					epost = esu.stoppedDelivAreaCheck(orVO.epostStoppedAreaToString(), EPOST_DELIV_STOP);
					
					epost.setOrVO(orVO);
					
					epostResultList.add(epost);
				}
				
			}else {
				epost = esu.stoppedDelivAreaCheck(orVO.epostStoppedAreaToString(), EPOST_DELIV_STOP);
				
				epost.setOrVO(orVO);
				
				epostResultList.add(epost);
				
			}
			
			
			
		}
		
		
		model.addAttribute("osVO", osVO);
		model.addAttribute("dawnFlag",dawnFlag);
		model.addAttribute("epostResultList", epostResultList);
		
		return "delivery/config/epost_stopped_area_check";
	}
	
	
}
