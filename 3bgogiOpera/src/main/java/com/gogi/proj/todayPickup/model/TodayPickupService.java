package com.gogi.proj.todayPickup.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface TodayPickupService {

	
	/**
	 * 
	 * @MethodName : updateTodayPickupInvoiceNumber
	 * @date : 2021. 8. 3.
	 * @author : Jeon KiChan
	 * @param osVO, epost_mapper에서 검색할 serial_special_number 획득
	 * @param ip, 사용자 ip값
	 * @param adminId, 사용자 id값
	 * @param createInvoiceNumDateCounting, 송장 생성일 작성
	 * @param auth, 오늘의픽업에 송장등록에 필요한 auth값 
	 * @param requestHeaders, 오늘의픽업 접속에 필요한 헤더값
	 * @return
	 * @메소드설명 : 오늘의픽업 송장 획득 및 db update
	 */
	public OrdersVO updateTodayPickupInvoiceNumber(OrderSearchVO osVO, String ip, String adminId, String createInvoiceNumDateCounting, String auth, Map<String, String> requestHeaders, Map<String, String> kakaoRequestHeaders);
	
	
	/**
	 * 
	 * @MethodName : selectTodayPickupTargetChecking
	 * @date : 2021. 8. 4.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 오늘의 픽업 대상자 확인
	 */
	public OrderSearchVO selectTodayPickupTargetChecking(OrderSearchVO osVO);
	
	public int selectDontGrantTodayPickupDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	public List<OrdersVO> selectDontGrantTodayPickupDelivOrderListInMonth(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : todayPickupDeliveryInvoiceNumberReprinting
	 * @date : 2021. 8. 9.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 오늘의 픽업 송장 재출력하기
	 */
	public OrdersVO todayPickupDeliveryInvoiceNumberReprinting(OrderSearchVO osVO, String ip, String adminId);
	
	public int deleteTodayPickupDelivInvoice(String invoiceNum, Map<String, String> requestHeaders);
	
}
