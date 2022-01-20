package com.gogi.proj.orders.meshkorea.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface MeshKoreaDAO {
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantMeshKoreaDelivListInMonthCounting
	 * @date : 2022. 1. 20.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 부릉 api에서 사용가능한 목록 카운팅하기
	 */
	public int selectDontGrantMeshKoreaDelivListInMonthCounting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantMeshKoreaelivOrderListInMonth
	 * @date : 2022. 1. 20.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 부릉 api에서 사용가능한 주문 목록 가져오기
	 */
	public List<OrdersVOList> selectDontGrantMeshKoreaelivOrderListInMonth(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectMeshKoreaDeliveryTargetChecking
	 * @date : 2022. 1. 20.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 부릉 api에서 체크할 주소 정보 가져오기
	 */
	public List<OrdersVO> selectMeshKoreaDeliveryTargetChecking();
	
	
	/**
	 * 
	 * @MethodName : selectMeshKoreaDeliveryData
	 * @date : 2022. 1. 20.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 부릉 api를 사용하지 못하고 엑셀로 사용해야 할 경우 사용되는 주문데이터 목록
	 */
	public List<OrdersVO> selectMeshKoreaDeliveryData(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : grantMeshKoreaInvoiceNumber
	 * @date : 2022. 1. 20.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 부릉 송장 처리
	 */
	public int grantMeshKoreaInvoiceNumber(OrdersVO osVO);
	
}
