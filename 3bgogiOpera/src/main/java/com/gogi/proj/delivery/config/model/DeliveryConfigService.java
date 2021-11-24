package com.gogi.proj.delivery.config.model;

import java.util.List;

import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface DeliveryConfigService {
	
	/**
	 * 
	 * @MethodName : selectDelivNumCheckTarget
	 * @date : 2020. 12. 1.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 송장 나온지 체크하기
	 */
	public List<OrdersVO> selectDelivNumCheckTarget(OrderSearchVO osVO); 
	
	/**
	 * 
	 * @MethodName : earlyDelivType
	 * @date : 2021. 11. 24.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 배송 회사 전부 불러오기
	 */
	public List<EarlyDelivTypeVO> earlyDelivType();
	
	
	/**
	 * 
	 * @MethodName : insertEarlyDelivArea
	 * @date : 2021. 11. 24.
	 * @author : Jeon KiChan
	 * @param edaVO
	 * @return
	 * @메소드설명 : 배송 불가 지역 추가하기
	 */
	public int insertEarlyDelivArea(EarlyDelivAreaVO edaVO);
	
	
	/**
	 * 
	 * @MethodName : selectEarlyDelivArea
	 * @date : 2021. 11. 24.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 배송 불가 지역 불러오기
	 */
	public List<EarlyDelivTypeVO> selectEarlyDelivArea(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : earlyDelivAreaCount
	 * @date : 2021. 11. 24.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 배송 불가 지역 전체 개수
	 */
	public int earlyDelivAreaCount(OrderSearchVO osVO);

	
	/**
	 * 
	 * @MethodName : isEarlyDelivArea
	 * @date : 2021. 11. 24.
	 * @author : Jeon KiChan
	 * @param addr
	 * @return
	 * @메소드설명 : 배송 불가 지역 체크 true == 가능, false == 불가
	 */
	public boolean isEarlyDelivArea(String addr, int delivCompany);
	
	/**
	 * 
	 * @MethodName : deleteEarlyDelivArea
	 * @date : 2021. 11. 24.
	 * @author : Jeon KiChan
	 * @param edaVO
	 * @return
	 * @메소드설명 : 배송 불가 지역 삭제하기
	 */
	public int deleteEarlyDelivArea(EarlyDelivAreaVO edaVO);
	
}
