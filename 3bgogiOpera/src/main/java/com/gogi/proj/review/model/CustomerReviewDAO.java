package com.gogi.proj.review.model;

import java.util.List;

import com.gogi.proj.review.vo.CustomerReviewVO;

public interface CustomerReviewDAO {
	
	/**
	 * 
	 * @MethodName : insertCustomerReview
	 * @date : 2021. 12. 31.
	 * @author : Jeon KiChan
	 * @param crVO
	 * @return
	 * @메소드설명 : 리뷰 기록하기
	 */
	public int insertCustomerReview(CustomerReviewVO crVO);
	
	
	/**
	 * 
	 * @MethodName : selectAllCustomerReview
	 * @date : 2021. 12. 31.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 모든 리뷰 기록 가져오기
	 */
	public List<CustomerReviewVO> selectAllCustomerReview();
	
	
	/**
	 * 
	 * @MethodName : selectCustomerReviewList
	 * @date : 2021. 12. 31.
	 * @author : Jeon KiChan
	 * @param pageVO
	 * @return
	 * @메소드설명 : 리뷰 목록 가져오기
	 */
	public List<CustomerReviewVO> selectCustomerReviewList(CustomerReviewVO pageVO);
	
	
	/**
	 * 
	 * @MethodName : selectCustomerReviewCount
	 * @date : 2021. 12. 31.
	 * @author : Jeon KiChan
	 * @param pageVO
	 * @return
	 * @메소드설명 : 리뷰 목록을 위한 total record 
	 */
	public int selectCustomerReviewCount(CustomerReviewVO pageVO);
	
	
	/**
	 * 
	 * @MethodName : updateCustomerReview
	 * @date : 2021. 12. 31.
	 * @author : Jeon KiChan
	 * @param crVO
	 * @return
	 * @메소드설명 : 리뷰 내용 업데이트
	 */
	public int updateCustomerReview(CustomerReviewVO crVO);
	
	
	/**
	 * 
	 * @MethodName : selectSaleFlowReview
	 * @date : 2021. 12. 31.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 상황판에 올려질 리뷰 내용 가져오기
	 */
	public List<CustomerReviewVO> selectSaleFlowReview();
	
	
	/**
	 * 
	 * @MethodName : selectReviewGradeGroup
	 * @date : 2021. 12. 31.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 평점별 리뷰 개수
	 */
	public List<CustomerReviewVO> selectReviewGradeGroup();
}
