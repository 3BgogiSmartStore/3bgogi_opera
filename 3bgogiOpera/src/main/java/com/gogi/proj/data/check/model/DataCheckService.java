package com.gogi.proj.data.check.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface DataCheckService {

	public List<OrdersVO> checkBuyerByCntNum(OrderSearchVO osVO);
}
