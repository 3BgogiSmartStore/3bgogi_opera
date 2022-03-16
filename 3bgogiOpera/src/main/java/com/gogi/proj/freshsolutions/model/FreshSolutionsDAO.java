package com.gogi.proj.freshsolutions.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface FreshSolutionsDAO {

	
	/**
	 * 
	 * @MethodName : selectDontGrantFreshSolutionsDelivOrderListInMonthCounting
	 * @date : 2022. 3. 11.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 프레시솔루션 페이징처리용 주문서 총 개수
	 */
	public int selectDontGrantFreshSolutionsDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantFreshSolutionsDelivOrderListInMonth
	 * @date : 2022. 3. 11.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 프레시솔루션 송장 부여 가능 주문서
	 */
	public List<OrdersVOList> selectDontGrantFreshSolutionsDelivOrderListInMonth(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectFreshSolutionsDeliveryTargetChecking
	 * @date : 2022. 3. 11.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 프레시솔루션 송장 부여 가능 여부 체크
	 */
	public List<OrdersVO> selectFreshSolutionsDeliveryTargetChecking(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : updateFreshSolutionsDeliveryTargetBeforeGrantInvoiceNum
	 * @date : 2022. 3. 14.
	 * @author : Jeon KiChan
	 * @param orderList
	 * @return
	 * @메소드설명 : 프레시솔루션 임시 송장 부여 (입력전)
	 */
	public int updateFreshSolutionsDeliveryTargetBeforeGrantInvoiceNum(List<OrdersVO> orderList);
	
	
	/**
	 * 
	 * @MethodName : grantFreshSolutionsDeliveryInvoiceNumByBatchUpdate
	 * @date : 2022. 3. 14.
	 * @author : Jeon KiChan
	 * @param orderList
	 * @return
	 * @메소드설명 : 프레시솔루션 송장 부여 대용량 batch 업데이트 
	 */
	public int [] grantFreshSolutionsDeliveryInvoiceNumByBatchUpdate(List<OrdersVO> orderList);
	
	
	/**
	 * 
	 * @MethodName : grantFreshSolutionsDeliveryInvoiceNumBySerialSpecialNumber
	 * @date : 2022. 3. 14.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 프레시솔루션 serialnum으로 송장 부여
	 */
	public int grantFreshSolutionsDeliveryInvoiceNumBySerialSpecialNumber(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : selectFreshSolutionsDeliveryExcelTarget
	 * @date : 2022. 3. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 프레시솔루션에 업로드할 주문서 정보 가져오기
	 */
	public List<OrdersVO> selectFreshSolutionsDeliveryExcelTarget(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectOrdersPkByOrSerialSpecialNumberForGrantFreshSolutionsInvoiceNum
	 * @date : 2022. 3. 14.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumber
	 * @return
	 * @메소드설명 :
	 */
	public List<OrdersVO> selectOrdersPkByOrSerialSpecialNumberForGrantFreshSolutionsInvoiceNum(String orSerialSpecialNumber);
}
