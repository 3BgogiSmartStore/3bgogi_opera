package com.gogi.proj.orders.teamfresh.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class TeamFreshDAOMybatis extends SqlSessionDaoSupport implements TeamFreshDAO{

	private String namespace = "delivery.team_fresh";

	@Override
	public List<OrdersVO> selectTeamFreshDeliveryExcelTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectTeamFreshDeliveryExcelTarget", osVO);
	}

	@Override
	public int grantTeamFreshInvoiceNumber(OrdersVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".grantTeamFreshInvoiceNumber", osVO);
	}
	
}
