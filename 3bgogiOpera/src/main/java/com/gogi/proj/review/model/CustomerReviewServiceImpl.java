package com.gogi.proj.review.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.review.vo.CustomerReviewVO;

@Service
public class CustomerReviewServiceImpl implements CustomerReviewService{

	@Autowired
	private CustomerReviewDAO customerReviewDao;

	@Transactional
	@Override
	public int insertCustomerReview(List<CustomerReviewVO> reviewExcelList) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		for(CustomerReviewVO crVO : reviewExcelList) {
			result += customerReviewDao.insertCustomerReview(crVO);	
		}
		 
		return result;
	}

	@Override
	public List<CustomerReviewVO> selectAllCustomerReview() {
		// TODO Auto-generated method stub
		return customerReviewDao.selectAllCustomerReview();
	}

	@Override
	public List<CustomerReviewVO> selectCustomerReviewList(CustomerReviewVO pageVO) {
		// TODO Auto-generated method stub
		return customerReviewDao.selectCustomerReviewList(pageVO);
	}

	@Override
	public int selectCustomerReviewCount(CustomerReviewVO pageVO) {
		// TODO Auto-generated method stub
		return customerReviewDao.selectCustomerReviewCount(pageVO);
	}

	@Override
	public int updateCustomerReview(CustomerReviewVO crVO) {
		// TODO Auto-generated method stub
		return customerReviewDao.updateCustomerReview(crVO);
	}

	@Override
	public List<CustomerReviewVO> selectSaleFlowReview() {
		// TODO Auto-generated method stub
		return customerReviewDao.selectSaleFlowReview();
	}

	@Override
	public List<CustomerReviewVO> selectReviewGradeGroup() {
		// TODO Auto-generated method stub
		return customerReviewDao.selectReviewGradeGroup();
	}
}
