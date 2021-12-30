package com.gogi.proj.orders.detail.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class OrderDetailDAOMybatis extends SqlSessionDaoSupport implements OrderDetailDAO{

	private String namespace = "order.detail_search.search_order_proders";

	@Override
	public int selectCsDetailAllSearchCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectCsDetailAllSearchCounting", osVO);
	}

	@Override
	public List<OrdersVOList> selectCsDetailAllSearch(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCsDetailAllSearch", osVO);
	}

	@Override
	public int deleteOrderEachs(List<Integer> orPks) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".", orPks);
	}

	@Override
	public int cancledOrderEachs(List<Integer> orPks) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".cancledOrderEachs", orPks);
	}

	@Override
	public int cancleBackOrderEachs(List<Integer> orPks) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".cancleBackOrderEachs", orPks);
	}
	
}
