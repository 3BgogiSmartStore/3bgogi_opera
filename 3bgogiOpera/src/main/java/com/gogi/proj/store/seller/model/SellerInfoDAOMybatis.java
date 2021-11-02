package com.gogi.proj.store.seller.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;

@Repository
public class SellerInfoDAOMybatis extends SqlSessionDaoSupport implements SellerInfoDAO{

	private String namespace = "seller";

	@Override
	public List<AdminVO> selectAllSellerList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectAllSellerList", osVO);
	}

	@Override
	public AdminVO selectSellerByAdminPk(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectSellerByAdminPk", adminVO);
	}

	@Override
	public int updateSellerAdminInfo(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateSellerAdminInfo", adminVO);
	}

	@Override
	public int createSellerAdminInfo(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".createSellerAdminInfo", adminVO);
	}

	@Override
	public int insertSellerAuth(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertSellerAuth", adminVO);
	}

	@Override
	public int selectAllSellerListCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectAllSellerListCount", osVO);
	}

	@Override
	public List<OrdersVO> selectSellerSalesVolume(AdminVO adVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectSellerSalesVolume", adVO);
	}

	@Override
	public AdminVO selectSellerInfoByAdminId(String adminId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectSellerInfoByAdminId", adminId);	
	}

	@Override
	public int updateSellerInfo(AdminVO adVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateSellerInfo", adVO);
	}

	@Override
	public int selectSellerSalesSum() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectSellerSalesSum");
	}

	@Override
	public String selectAuthNumBySellerId(AdminVO adVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectAuthNumBySellerId", adVO);
	}

	@Override
	public int updateSellerAuthNum(AdminVO adVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateSellerAuthNum", adVO);
	}

	@Override
	public List<AdminVO> sellerPermitExchangeCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sellerPermitExchangeCount", osVO);
	}

	@Override
	public int sellerPermitExchangePagingCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".sellerPermitExchangePagingCount", osVO);
	}
}
