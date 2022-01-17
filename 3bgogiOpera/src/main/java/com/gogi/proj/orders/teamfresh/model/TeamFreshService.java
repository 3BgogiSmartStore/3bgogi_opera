package com.gogi.proj.orders.teamfresh.model;

import com.gogi.proj.paging.OrderSearchVO;

public interface TeamFreshService {

	/**
	 * 
	 * @MethodName : selectTeamFreshDeliveryTargetChecking
	 * @date : 2022. 1. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 팀프레쉬  주문서 목록 가져오기
	 */
	public OrderSearchVO selectTeamFreshDeliveryTargetChecking(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : isTeamFreshDeliveryArea
	 * @date : 2022. 1. 14.
	 * @author : Jeon KiChan
	 * @param jsonAddr
	 * @param formatToday
	 * @return
	 * @메소드설명 : 팀프레쉬 배송 가능 지역 체크 기능
	 */
	public boolean isTeamFreshDeliveryArea(String jsonAddr, String formatToday);
	
	
	/**
	 * 
	 * @MethodName : createTeamFreshDelivInvoice
	 * @date : 2022. 1. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 팀프레쉬 송장 생성
	 */
	public String createTeamFreshDelivInvoice(OrderSearchVO osVO, String ip, String adminId);
	
	
	public int deleteTeamFreshDelivInvoice(String teamFreshOrderNum, String ip, String adminId);
	
	public int updateTeamFreshDoorPass(String orderId, String doorPassKeyword);
}
