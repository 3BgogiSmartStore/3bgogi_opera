package com.gogi.proj.delivery.config.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.proj.aligo.kakao.AligoKaKaoTempletList;
import com.gogi.proj.aligo.kakao.AligoKakaoAPI;
import com.gogi.proj.aligo.kakao.AligoKakaoResultDTO;
import com.gogi.proj.aligo.util.AligoResultDTO;
import com.gogi.proj.aligo.util.AligoSendingForm;
import com.gogi.proj.aligo.util.AligoVO;
import com.gogi.proj.delivery.config.model.DeliveryConfigService;
import com.gogi.proj.delivery.config.vo.DoorPassKeywordVO;
import com.gogi.proj.delivery.config.vo.DoorPassVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.model.OrdersService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
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
	
	@Autowired
	private AligoSendingForm adf;
	
	@Autowired
	private AligoKakaoAPI aligoKakaoApi;
	
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
	
	
	/**
	 * 
	 * @MethodName : insertDoorPassGet
	 * @date : 2021. 12. 1.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumber
	 * @param model
	 * @return
	 * @메소드설명 : 공동현관 출입방법 가져오기
	 */
	@RequestMapping(value="/door_pass.do", method=RequestMethod.GET)
	public String insertDoorPassGet(@RequestParam String orSerialSpecialNumber, Model model) {
		
		if(orSerialSpecialNumber != null && !orSerialSpecialNumber.equals("")) {
			
			OrdersVO orVO = dcService.selectOrderInfoForDoorPass(orSerialSpecialNumber);
			OrdersVOList orList = new OrdersVOList();
			orList.setOrBuyerContractNumber1(orVO.getOrBuyerContractNumber1());
			orList.setOrReceiverContractNumber1(orVO.getOrReceiverContractNumber1());
			orList.setOrShippingAddress(orVO.getOrShippingAddress());
			orList.setOrShippingAddressDetail(orVO.getOrShippingAddressDetail());
			
			DoorPassVO dpVO = dcService.selectDoorPassMsgByOrderInfo(orList);
			
			model.addAttribute("dpVO", dpVO);
			model.addAttribute("orVO", orVO);
			model.addAttribute("orSerialSpecialNumber",orSerialSpecialNumber);
		}
		
		return "delivery/config/insert_door_pass";
	}
	
	
	/**
	 * 
	 * @MethodName : insertDoorPassPost
	 * @date : 2021. 12. 1.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @param model
	 * @return
	 * @메소드설명 : 공동현관 출입방법 입력하기
	 */
	@RequestMapping(value="/door_pass.do", method=RequestMethod.POST)
	public String insertDoorPassPost(@ModelAttribute OrdersVOList orVO, @RequestParam String orSerialSpecialNumber, Model model) {
		
		String msg = "";
		String url = "/delivery/config/door_pass.do";
		
		DoorPassVO dpVO = new DoorPassVO();
		
		dpVO.setDpBuyerCnt(orVO.getOrBuyerContractNumber1());
		dpVO.setDpReceiverCnt(orVO.getOrReceiverContractNumber1());
		dpVO.setDpAddr(orVO.getOrShippingAddress()+" "+orVO.getOrShippingAddressDetail());
		dpVO.setDpMsg(orVO.getOrDelivEnter());
		
		int result = dcService.insertDoorPassMsg(dpVO);
		
		if(result > 0) {

			if(orSerialSpecialNumber != null && !orSerialSpecialNumber.equals("")) {
				orVO.setOrDelivEnter("");
								
				orVO = dcService.doorPassCheck(orVO);

			}
			
			msg = "입력 완료";
			
			model.addAttribute("closing", true);
		}else {
			msg = "입력 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	
	/**
	 * 
	 * @MethodName : cjDelivDoorMsgSend
	 * @date : 2021. 12. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : cj새벽배송 공동현관 비밀번호 요청 문자 보내기
	 */
	@RequestMapping(value="/cj_door_msg_not_used.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	public String cjDelivDoorMsgSend(@ModelAttribute OrderSearchVO osVO ) {
		
		List<OrdersVO> targetList = dcService.selectCjDelivDoorPassMsgTarget(osVO);
		
		AligoVO aligoVO = new AligoVO();
		
		StringBuffer receiver = new StringBuffer();
		StringBuffer destination = new StringBuffer();
		
		int count = 0;
		
		for( OrdersVO orVO : targetList) {
			if(count == 0) {
				receiver.append(orVO.getOrBuyerContractNumber1());
				destination.append(orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
			}else {
				receiver.append(","+orVO.getOrBuyerContractNumber1());
				destination.append(","+orVO.getOrBuyerContractNumber1()+"|"+orVO.getOrBuyerName());
			}
			
			count++;
			
		}
		
		aligoVO.setReceiver(receiver.toString());
		aligoVO.setDestination(destination.toString());
		aligoVO.setMsg(adf.aligoCjDelivDoorPassMsg());
		
		String result = adf.smsMsg(aligoVO);
		
		AligoResultDTO aligoDto = adf.stringToAligoResultDTO(result);
		
		
		return aligoDto.toString();
	}
	
	
	/**
	 * 
	 * @MethodName : doorPassKeywordGet
	 * @date : 2021. 12. 3.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 배송메세지에서 공동현관 출입방법 키워드 추출 단어 확인 및 추가 페이지 
	 */
	@RequestMapping(value="/door_pass_keyword.do", method=RequestMethod.GET)
	public String doorPassKeywordGet(Model model) {
		
		List<DoorPassKeywordVO> dpkList = dcService.selectAllDoorPassKeyword();
		
		model.addAttribute("dpkList", dpkList);
		
		return "delivery/config/door_pass_keyword";
	}
	
	
	
	/**
	 * 
	 * @MethodName : doorPassKeywordPost
	 * @date : 2021. 12. 3.
	 * @author : Jeon KiChan
	 * @param dpkVO
	 * @param model
	 * @return
	 * @메소드설명 : 배송메세지에서 공동현관 출입방법 키워드 추출 단어 추가
	 */
	@RequestMapping(value="/door_pass_keyword.do", method=RequestMethod.POST)
	public String doorPassKeywordPost(@ModelAttribute DoorPassKeywordVO dpkVO, Model model) {
		
		String msg = "";
		String url = "/delivery/config/door_pass_keyword.do";
		
		int result = dcService.insertDoorPassKeyword(dpkVO);
		
		if(result > 0) {
			msg = "공동현관 출입방법 키워드 추가 완료";
		}else {
			msg = "공동현관 출입방법 키워드 추가 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : doorPassKeywordDelete
	 * @date : 2021. 12. 3.
	 * @author : Jeon KiChan
	 * @param dpkVO
	 * @param model
	 * @return
	 * @메소드설명 : 배송메세지에서 공동현관 출입방법 키워드 추출 단어 삭제하기
	 */
	@RequestMapping(value="/door_pass_keyword_del.do", method=RequestMethod.GET)
	public String doorPassKeywordDelete(@ModelAttribute DoorPassKeywordVO dpkVO, Model model) {
		
		String msg = "";
		String url = "/delivery/config/door_pass_keyword.do";
		
		int result = dcService.deleteDoorPassKeyword(dpkVO);
		
		if(result > 0) {
			msg = "키워드 삭제 완료";
		}else {
			msg = "키워드 삭제 실패";
		}
		
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
	
	@RequestMapping(value="/cj_door_msg.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	@ResponseBody
	public String cjDoorMsgKakao(@ModelAttribute OrderSearchVO osVO) {
		List<OrdersVO> targetList = dcService.selectCjDelivDoorPassMsgTarget(osVO);
		
		AligoKakaoResultDTO aligoKakaoDto = aligoKakaoApi.getAligoKakaoToken("https://kakaoapi.aligo.in/akv10/token/create/30/s/");

		AligoKaKaoTempletList ak = aligoKakaoApi.getAlioKakaoTemplt("https://kakaoapi.aligo.in/akv10/template/list/", aligoKakaoDto);
		
		String result = aligoKakaoApi.aligoKakaoSending("https://kakaoapi.aligo.in/akv10/alimtalk/send/", aligoKakaoDto, targetList, ak.getTempltName(), ak.getTempltContent(), ak.getTempltCode());
		
		return result;
	}
}

