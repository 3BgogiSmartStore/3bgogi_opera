package com.gogi.proj.todayPickup.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface TodayPickupDAO {

	/**
	 * 
	 * @MethodName : updateTodayPickupInvoiceNumber
	 * @date : 2021. 8. 3.
	 * @author : Jeon KiChan
	 * @param orderList
	 * @return
	 * @메소드설명 : 오늘의픽업 송장부여 업데이트 or_serial_special_number로 or_delivery_invoice_number, or_delivery_company 수정
	 */
	public int [] updateTodayPickupInvoiceNumber(List<OrdersVO> orderList);
	
	/**
	 * 
	 * @MethodName : grantTodayPickupInvoiceNumber
	 * @date : 2021. 8. 3.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 오늘의 픽업 부여
	 */
	public int grantTodayPickupInvoiceNumber(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : selectTodayPickupTargetChecking
	 * @date : 2021. 8. 4.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 오늘의 픽업 대상자 확인
	 */
	public List<OrdersVO> selectTodayPickupTargetChecking(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantTodayPickupDelivOrderListInMonthCounting
	 * @date : 2021. 8. 9.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 오늘의 픽업 송장 부여 개수 가져오기
	 */
	public int selectDontGrantTodayPickupDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantTodayPickupDelivOrderListInMonth
	 * @date : 2021. 8. 9.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 오늘의 픽업 송장 부여 리스트 가져오기
	 */
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
	public OrdersVO todayPickupDeliveryInvoiceNumberReprinting(OrderSearchVO osVO);
}
