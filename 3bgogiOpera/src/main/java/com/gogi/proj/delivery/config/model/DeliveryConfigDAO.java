package com.gogi.proj.delivery.config.model;

import java.util.List;

import com.gogi.proj.delivery.config.vo.DoorPassKeywordVO;
import com.gogi.proj.delivery.config.vo.DoorPassVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface DeliveryConfigDAO {
	
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
	 * @MethodName : allEarlyDelivArea
	 * @date : 2021. 11. 24.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 배송 불가 지역 전체 불러오기
	 */
	public List<EarlyDelivAreaVO> allEarlyDelivArea(int companyNum);
	
	
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
	
	
	/**
	 * 
	 * @MethodName : insertDoorPassKeyword
	 * @date : 2021. 11. 30.
	 * @author : Jeon KiChan
	 * @param dpkVO
	 * @return
	 * @메소드설명 : 출입방법 키워드 추출에 필요한 키워드 추가하기
	 */ 
	public int insertDoorPassKeyword(DoorPassKeywordVO dpkVO);
	
	
	/**
	 * 
	 * @MethodName : selectAllDoorPassKeyword
	 * @date : 2021. 11. 30.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 출입방법 키워드 목록 가져오기
	 */
	public List<DoorPassKeywordVO> selectAllDoorPassKeyword();
	
	
	/**
	 * 
	 * @MethodName : deleteDoorPassKeyword
	 * @date : 2021. 11. 30.
	 * @author : Jeon KiChan
	 * @param dpkVO
	 * @return
	 * @메소드설명 : 출입방법 키워드 삭제하기
	 */
	public int deleteDoorPassKeyword(DoorPassKeywordVO dpkVO);
	
	
	/**
	 * 
	 * @MethodName : selectDoorPassMsgByOrderInfo
	 * @date : 2021. 12. 1.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 공동현관 출입방법 가져오기 (최근에 등록된 1건만)
	 */
	public DoorPassVO selectDoorPassMsgByOrderInfo(OrdersVOList orVO);
	
	
	/**
	 * 
	 * @MethodName : updateOrderDoorPassByOrSerialSpecialNumber
	 * @date : 2021. 12. 1.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 기존 주문서에 공동현관 출입방법 등록하기
	 */
	public int updateOrderDoorPassByOrSerialSpecialNumber(OrdersVOList orVO);
	
	
	/**
	 * 
	 * @MethodName : insertDoorPassMsg
	 * @date : 2021. 12. 1.
	 * @author : Jeon KiChan
	 * @param dpVO
	 * @return
	 * @메소드설명 : 공동현관 출입방법 넣기
	 */
	public int insertDoorPassMsg(DoorPassVO dpVO);
	
	
	/**
	 * 
	 * @MethodName : selectOrderInfoForDoorPass
	 * @date : 2021. 12. 1.
	 * @author : Jeon KiChan
	 * @param orSerialSpecialNumber
	 * @return
	 * @메소드설명 : 공동현관 출입방법에 필요한 구매자 정보 조회용
	 */
	public OrdersVO selectOrderInfoForDoorPass(String orSerialSpecialNumber);
	
	
	/**
	 * 
	 * @MethodName : selectCjDelivDoorPassMsgTarget
	 * @date : 2021. 12. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : cj 공동현관 출입방법 문자 대상자 정보 조회 
	 */
	public List<OrdersVO> selectCjDelivDoorPassMsgTarget(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDoorPassMsgByOrderInfoNotList
	 * @date : 2021. 12. 2.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : OrdersVOList가 아닌 OrdersVO의 정보로 공동현관 출입방법 가져오기
	 */
	public DoorPassVO selectDoorPassMsgByOrderInfoNotList(OrdersVO orVO);
}
