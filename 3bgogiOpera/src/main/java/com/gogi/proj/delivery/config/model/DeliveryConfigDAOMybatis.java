package com.gogi.proj.delivery.config.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class DeliveryConfigDAOMybatis extends SqlSessionDaoSupport implements DeliveryConfigDAO {

	private String namespace = "delivery.config";
	private String deliveryCheck = "delivery.check";

	@Override
	public List<OrdersVO> selectDelivNumCheckTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(deliveryCheck+".selectDelivNumCheckTarget", osVO);
	}

	@Override
	public List<EarlyDelivTypeVO> earlyDelivType() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".earlyDelivType");
	}

	@Override
	public int insertEarlyDelivArea(EarlyDelivAreaVO edaVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertEarlyDelivArea", edaVO);
	}

	@Override
	public List<EarlyDelivTypeVO> selectEarlyDelivArea(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectEarlyDelivArea", osVO);
	}

	@Override
	public int earlyDelivAreaCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".earlyDelivAreaCount", osVO);
	}

	@Override
	public List<EarlyDelivAreaVO> allEarlyDelivArea(int companyNum) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".allEarlyDelivArea", companyNum);
	}

	@Override
	public int deleteEarlyDelivArea(EarlyDelivAreaVO edaVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteEarlyDelivArea", edaVO);
	}
	
}
