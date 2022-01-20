package com.gogi.proj.orders.meshkorea.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class MeshKoreaDAOMybatis extends SqlSessionDaoSupport implements MeshKoreaDAO{

	private String namespace = "delivery.mesh_korea_delivery";

	@Override
	public int selectDontGrantMeshKoreaDelivListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectDontGrantMeshKoreaDelivListInMonthCounting", osVO);
	}

	@Override
	public List<OrdersVOList> selectDontGrantMeshKoreaelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectDontGrantMeshKoreaelivOrderListInMonth", osVO);
	}

	@Override
	public List<OrdersVO> selectMeshKoreaDeliveryTargetChecking() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectMeshKoreaDeliveryTargetChecking");
	}

	@Override
	public List<OrdersVO> selectMeshKoreaDeliveryData(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectMeshKoreaDeliveryData", osVO);
	}

	@Override
	public int grantMeshKoreaInvoiceNumber(OrdersVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".grantMeshKoreaInvoiceNumber", osVO);
	}
	
}
