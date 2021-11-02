package com.gogi.proj.data.check.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class DataCheckDAOMybatis extends SqlSessionDaoSupport implements DataCheckDAO{

	private String namespace = "data.data_check";

	@Override
	public List<OrdersVO> checkBuyerByCntNum(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".checkBuyerByCntNum", osVO);
	}
	
}
