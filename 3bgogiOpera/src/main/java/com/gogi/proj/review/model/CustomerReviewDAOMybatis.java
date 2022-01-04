package com.gogi.proj.review.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.review.vo.CustomerReviewVO;

@Repository
public class CustomerReviewDAOMybatis extends SqlSessionDaoSupport implements CustomerReviewDAO{

	private String namespace = "customer.review";

	@Override
	public int insertCustomerReview(CustomerReviewVO crVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertCustomerReview", crVO);
	}

	@Override
	public List<CustomerReviewVO> selectAllCustomerReview() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectAllCustomerReview");
	}

	@Override
	public List<CustomerReviewVO> selectCustomerReviewList(CustomerReviewVO pageVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCustomerReviewList", pageVO);
	}

	@Override
	public int selectCustomerReviewCount(CustomerReviewVO pageVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectCustomerReviewCount", pageVO);
	}

	@Override
	public int updateCustomerReview(CustomerReviewVO crVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateCustomerReview", crVO);
	}

	@Override
	public List<CustomerReviewVO> selectSaleFlowReview() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectSaleFlowReview");
	}

	@Override
	public List<CustomerReviewVO> selectReviewGradeGroup() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectReviewGradeGroup");
	}
	
}
