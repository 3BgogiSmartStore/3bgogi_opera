package com.gogi.proj.orders.detail.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface OrderDetailDAO {

	
	/**
	 * 
	 * @MethodName : selectCsDetailAllSearchCounting
	 * @date : 2021. 12. 28.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 고객 주문서 상세 정보 개수 (paging 처리용)
	 */
	public int selectCsDetailAllSearchCounting(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectCsDetailAllSearch
	 * @date : 2021. 12. 28.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 고객 주문서 상세 정보 확인
	 */
	public List<OrdersVOList> selectCsDetailAllSearch(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : deleteOrderEachs
	 * @date : 2021. 12. 29.
	 * @author : Jeon KiChan
	 * @param orPks
	 * @return
	 * @메소드설명 : 체크 주문서 pk 값으로 일괄 삭제처리
	 */
	public int deleteOrderEachs(List<Integer> orPks);
	
	
	/**
	 * 
	 * @MethodName : cancledOrderEachs
	 * @date : 2021. 12. 29.
	 * @author : Jeon KiChan
	 * @param orPks
	 * @return
	 * @메소드설명 : 체크 주문서 pk 값으로 일괄 취소처리
	 */
	public int cancledOrderEachs(List<Integer> orPks);
	
	
	/**
	 * 
	 * @MethodName : cancleBackOrderEachs
	 * @date : 2021. 12. 29.
	 * @author : Jeon KiChan
	 * @param orPks
	 * @return
	 * @메소드설명 : 체크 주문서 pk 값으로 취소건 복구시키기
	 */
	public int cancleBackOrderEachs(List<Integer> orPks);
}
