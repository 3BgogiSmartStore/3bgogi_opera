package com.gogi.proj.freshsolutions.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class FreshSolutionsDAOMybatis extends SqlSessionDaoSupport implements FreshSolutionsDAO{

	private String namespace = "delivery.fresh_solutions_delivery";

	@Override
	public int selectDontGrantFreshSolutionsDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrdersVOList> selectDontGrantFreshSolutionsDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdersVO> selectFreshSolutionsDeliveryTargetChecking() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
