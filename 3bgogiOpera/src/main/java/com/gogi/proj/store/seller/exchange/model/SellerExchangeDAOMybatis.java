package com.gogi.proj.store.seller.exchange.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.store.seller.exchange.vo.SellerExchangeVO;

@Repository
public class SellerExchangeDAOMybatis extends SqlSessionDaoSupport implements SellerExchangeDAO{

	private String namespace = "seller.exchange";

	@Override
	public int exchangePosiv(AdminVO adVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".exchangePosiv", adVO);
	}

	@Override
	public SellerExchangeVO holdingExchangePermission(AdminVO adVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".holdingExchangePermission", adVO);
	}

	@Override
	public int exchangePermitHistory(AdminVO adVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".exchangePermitHistory", adVO) == null ? 0 : getSqlSession().selectOne(namespace+".exchangePermitHistory", adVO);
	}

	@Override
	public int insertExchange(SellerExchangeVO seVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertExchange", seVO);
	}

	@Override
	public int selectIncreaseCount(AdminVO adVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectIncreaseCount", adVO);
	}

	@Override
	public List<SellerExchangeVO> searchSellerExchangeHistory(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".searchSellerExchangeHistory", adminVO);
	}

	@Override
	public List<AdminVO> searchDontPermitExchanges(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".searchDontPermitExchanges", osVO);
	}

	@Override
	public int permitExchangeReq(SellerExchangeVO seVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".permitExchangeReq", seVO);
	}

	@Override
	public int searchDontPermitExchangesCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".searchDontPermitExchangesCount", osVO);
	}

	@Override
	public List<SellerExchangeVO> selectAllSellerExchangeByAdminPk(AdminVO adVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectAllSellerExchangeByAdminPk", adVO);
	}
}
