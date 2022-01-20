package com.gogi.proj.orders.meshkorea.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface MeshKoreaService {

	
	/**
	 * 
	 * @MethodName : isMeshKoreaDeliveryArea
	 * @date : 2022. 1. 20.
	 * @author : Jeon KiChan
	 * @param Addr
	 * @return
	 * @메소드설명 : 부릉 새벽배송 가능 지역 체크 기능
	 */
	public boolean isMeshKoreaDeliveryArea(String Addr);
	
	
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
	 * @MethodName : meshKoreaConnect
	 * @date : 2022. 1. 20.
	 * @author : Jeon KiChan
	 * @메소드설명 : 만들어야함
	 */
	public void meshKoreaConnect(String url, String methodType, String jsonBody);
}
