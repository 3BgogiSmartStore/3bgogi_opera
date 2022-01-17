package com.gogi.proj.orders.teamfresh.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface TeamFreshDAO {

	
	/**
	 * 
	 * @MethodName : selectTeamFreshDeliveryExcelTarget
	 * @date : 2022. 1. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 팀프레시 송장용 정보 가져오기
	 */
	public List<OrdersVO> selectTeamFreshDeliveryExcelTarget(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : grantTeamFreshInvoiceNumber
	 * @date : 2022. 1. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 팀프레시 송장 부여하기
	 */
	public int grantTeamFreshInvoiceNumber(OrdersVO osVO);
}
