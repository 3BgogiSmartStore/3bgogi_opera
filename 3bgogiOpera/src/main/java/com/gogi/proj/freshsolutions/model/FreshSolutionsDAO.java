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
	 * @메소드설명 :
	 */
	public int selectDontGrantFreshSolutionsDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantFreshSolutionsDelivOrderListInMonth
	 * @date : 2022. 3. 11.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 :
	 */
	public List<OrdersVOList> selectDontGrantFreshSolutionsDelivOrderListInMonth(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectFreshSolutionsDeliveryTargetChecking
	 * @date : 2022. 3. 11.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 :
	 */
	public List<OrdersVO> selectFreshSolutionsDeliveryTargetChecking();
}
