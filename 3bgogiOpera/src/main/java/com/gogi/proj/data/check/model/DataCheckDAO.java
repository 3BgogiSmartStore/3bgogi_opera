package com.gogi.proj.data.check.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface DataCheckDAO {

	/**
	 * 
	 * @MethodName : checkBuyerByCntNum
	 * @date : 2021. 8. 30.
	 * @author : Jeon KiChan
	 * @param osvO
	 * @return
	 * @메소드설명 : 핸드폰 번호로 주문서 조회
	 */
	public List<OrdersVO> checkBuyerByCntNum(OrderSearchVO osvO);
}
