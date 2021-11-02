package com.gogi.proj.store.seller.exchange.model;

import java.util.List;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.store.seller.exchange.vo.SellerExchangeVO;

public interface SellerExchangeDAO {

	
	/**
	 * 
	 * @MethodName : exchangePosiv
	 * @date : 2021. 10. 13.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @return
	 * @메소드설명 : 미완료 환전 신청 내역이 있는지 조회
	 */
	public int exchangePosiv(AdminVO adVO);
	
	
	/**
	 * 
	 * @MethodName : holdingExchangePermission
	 * @date : 2021. 10. 13.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @return
	 * @메소드설명 : 미완료 환전 가져오기
	 */
	public SellerExchangeVO holdingExchangePermission(AdminVO adVO);
	
	
	
	/**
	 * 
	 * @MethodName : exchangePermitHistory
	 * @date : 2021. 10. 13.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @return
	 * @메소드설명 : 환전이 완료된 총금액 가져오기
	 */ 
	public int exchangePermitHistory(AdminVO adVO);
	
	
	/**
	 * 
	 * @MethodName : insertExchange
	 * @date : 2021. 10. 13.
	 * @author : Jeon KiChan
	 * @param seVO
	 * @return
	 * @메소드설명 : 환전 기록 넣기
	 */
	public int insertExchange(SellerExchangeVO seVO);
	
	
	/**
	 * 
	 * @MethodName : selectIncreaseCount
	 * @date : 2021. 10. 13.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @return
	 * @메소드설명 : 환전 차수 조회하기 
	 */
	public int selectIncreaseCount(AdminVO adVO);
	
	
	/**
	 * 
	 * @MethodName : searchSellerExchangeHistory
	 * @date : 2021. 10. 14.
	 * @author : Jeon KiChan
	 * @param adminVO
	 * @return
	 * @메소드설명 : 환전 신청 내역 목록
	 */
	public List<SellerExchangeVO> searchSellerExchangeHistory(AdminVO adminVO);
	
	
	/**
	 * 
	 * @MethodName : searchDontPermitExchanges
	 * @date : 2021. 10. 22.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 허가 되지 않은 환전 신청 목록 전부 가져오기
	 */
	public List<AdminVO> searchDontPermitExchanges(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : permitExchangeReq
	 * @date : 2021. 10. 22.
	 * @author : Jeon KiChan
	 * @param seVO
	 * @return
	 * @메소드설명 : 환전 신청 허가하기
	 */
	public int permitExchangeReq(SellerExchangeVO seVO);
	
	
	/**
	 * 
	 * @MethodName : searchDontPermitExchangesCount
	 * @date : 2021. 10. 22.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 :허가 되지 않은 환전 신청 목록 개수
	 */
	public int searchDontPermitExchangesCount(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectAllSellerExchangeByAdminPk
	 * @date : 2021. 10. 26.
	 * @author : Jeon KiChan
	 * @param adVO
	 * @return
	 * @메소드설명 : 셀러 환전 신청 내역 전부 가져오기
	 */
	public List<SellerExchangeVO> selectAllSellerExchangeByAdminPk(AdminVO adVO);
}
