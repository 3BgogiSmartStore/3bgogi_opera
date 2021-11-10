package com.gogi.proj.orders.cj.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface CjdeliveryDAO {

	/**
	 * 
	 * @MethodName : selectCjDeliveryTargetChecking
	 * @date : 2021. 9. 23.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : cj 택배 배송 현관비밀번호 적지 않은 상태 1차 필터링
	 */
	public List<OrdersVO> selectCjDeliveryTargetChecking(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantCjDelivOrderListInMonthCounting
	 * @date : 2021. 9. 24.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 현관 택배로 필터링, cjcookit으로 주소가 필터링 된 목록의 수량을 가져옴
	 */
	public int selectDontGrantCjDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantCjDelivOrderListInMonth
	 * @date : 2021. 9. 24.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 현관 택배로 필터링, cjcookit으로 주소가 필터링 된 목록의 주문서를 가져옴
	 */
	public List<OrdersVO> selectDontGrantCjDelivOrderListInMonth(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : updateCjDeliveryTargetBeforeGrantInvoiceNum
	 * @date : 2021. 9. 24.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : cj택배에서 송장을 뽑아 업무페이지에 송장번호를 입력하기 전 가입력 상태로 둠 
	 */
	public int updateCjDeliveryTargetBeforeGrantInvoiceNum(List<OrdersVO> orderList);
	
	
	/**
	 * 
	 * @MethodName : grantCjDeliveryInvoiceNumByOrPk
	 * @date : 2021. 9. 24.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : primary key 값으로 송장부여하기
	 */
	public int grantCjDeliveryInvoiceNumByOrPk(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : grantCjDeliveryInvoiceNumBySerialSpecialNumber
	 * @date : 2021. 9. 24.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : primary key 값으로 송장부여가 안될경우 별도의 묶음변호를 기준으로 송장부여
	 */
	public int grantCjDeliveryInvoiceNumBySerialSpecialNumber(OrdersVO orVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectCjDeliveryExcelTarget
	 * @date : 2021. 9. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : cj cnplus로 전송될 주문서 목록 가져오기
	 */
	public List<OrdersVO> selectCjDeliveryExcelTarget(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : grantCjDeliveryInvoiceNumByBatchUpdate
	 * @date : 2021. 9. 30.
	 * @author : Jeon KiChan
	 * @param orderList
	 * @return
	 * @메소드설명 : 송장정보과 묶음번호값을 batch update
	 */
	public int [] grantCjDeliveryInvoiceNumByBatchUpdate(List<OrdersVO> orderList);
	
	
	/**
	 * 
	 * @MethodName : selectOrdersPkByOrSerialSpecialNumberForGrantCjInvoiceNum
	 * @date : 2021. 11. 9.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumber
	 * @return
	 * @메소드설명 : 업무페이지 묶음고유값으로 cj 송장에 필요한 정보 나열
	 */
	public List<OrdersVO> selectOrdersPkByOrSerialSpecialNumberForGrantCjInvoiceNum(String orSerialSpecialNumber);
}
