package com.gogi.proj.store.seller.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;

public interface SellerInfoService {

	final int SIGNUP_SECCESS = 1;
	final int DUPLI_id = 0;
	final int SIGNUP_ERROR = -1;
	
	/**
	 * 
	 * @MethodName : selectAllSellerList
	 * @date : 2021. 10. 5.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 셀러 목록 불러오기
	 */
	public List<AdminVO> selectAllSellerList(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectSellerByAdminPk
	 * @date : 2021. 10. 5.
	 * @author : Jeon KiChan
	 * @param adminVO
	 * @return
	 * @메소드설명 : 셀러 정보 불러오기
	 */
	public AdminVO selectSellerByAdminPk(AdminVO adminVO);
	
	
	/**
	 * 
	 * @MethodName : updateSellerAdminInfo
	 * @date : 2021. 10. 5.
	 * @author : Jeon KiChan
	 * @param adminVO
	 * @return
	 * @메소드설명 : 셀러 정보 변경하기
	 */
	public int updateSellerAdminInfo(AdminVO adminVO);
	
	
	/**
	 * 
	 * @MethodName : createSellerAdminInfo
	 * @date : 2021. 10. 5.
	 * @author : Jeon KiChan
	 * @param adminVO
	 * @return
	 * @메소드설명 : 셀러 추가하기
	 */
	public int createSellerAdminInfo(AdminVO adminVO);
	
	
	/**
	 * 
	 * @MethodName : selectAllSellerListCount
	 * @date : 2021. 10. 6.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 셀러 목록 총 개수
	 */
	public int selectAllSellerListCount(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectSellerSalesVolume
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @return
	 * @메소드설명 : 셀러 상품 판매 조회하기
	 */
	public List<OrdersVO> selectSellerSalesVolume(String adminId);
	
	/**
	 * 
	 * @MethodName : selectSellerInfoByAdminId
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @param adminId
	 * @return
	 * @메소드설명 : 셀러 아이디로 조회 정보 찾기
	 */
	public AdminVO selectSellerInfoByAdminId(String adminId);
	
	
	/**
	 * 
	 * @MethodName : updateSellerInfo
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @return
	 * @메소드설명 : 셀러 정보 변경
	 */
	public int updateSellerInfo(AdminVO adVO);
	
	/**
	 * 
	 * @MethodName : selectSellerSalesSum
	 * @date : 2021. 10. 7.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 셀러 판매금액 합산
	 */
	public int selectSellerSalesSum();
	
	
	/**
	 * 
	 * @MethodName : selectAuthNumBySellerId
	 * @date : 2021. 10. 11.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @return
	 * @메소드설명 : 셀러 문자 인증값 불러오기
	 */
	public String selectAuthNumBySellerId(AdminVO adVO);
	
	
	
	/**
	 * 
	 * @MethodName : updateSellerAuthNum
	 * @date : 2021. 10. 11.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @return
	 * @메소드설명 : 셀러 문자 인증 값 수정하기
	 */
	public int updateSellerAuthNum(AdminVO adVO);
	
	
	/**
	 * 
	 * @MethodName : sellerPermitExchangeCount
	 * @date : 2021. 10. 25.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 환전 허가된 목록 확인하기
	 */
	public List<AdminVO> sellerPermitExchangeCount(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : sellerPermitExchangePagingCount
	 * @date : 2021. 10. 25.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 환전 허가된 목록 페이지뷰 카운트용
	 */
	public int sellerPermitExchangePagingCount(OrderSearchVO osVO);
	
}
