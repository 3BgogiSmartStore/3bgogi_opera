package com.gogi.proj.orders.cj.model;

import java.io.File;
import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface CjdeliveryService {

	
	/**
	 * 
	 * @MethodName : isCjDeliveryArea
	 * @date : 2021. 12. 28.
	 * @author : Jeon KiChan
	 * @param zipCode
	 * @param addr
	 * @param addrDetail
	 * @return
	 * @메소드설명 : cj 새벽배송 가능 지역 체크
	 */
	public boolean isCjDeliveryArea(String zipCode, String addr, String addrDetail);
	
	
	/**
	 * 
	 * @MethodName : selectCjDeliveryTargetChecking
	 * @date : 2021. 9. 23.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : db에서 1차로 현관비밀번호를 필터링 후 cj 새벽배송 가능지역 ajax로 임시 테스트 
	 */
	public OrderSearchVO selectCjDeliveryTargetChecking(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantCjDelivOrderListInMonthCounting
	 * @date : 2021. 9. 24.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 현관 택배로 필터링, cjcookit으로 주소가 필터링 된 목록의 수량을 가져옴
	 */
	public int selectDontGrantCjDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantCjDelivOrderListInMonth
	 * @date : 2021. 9. 24.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 현관 택배로 필터링, cjcookit으로 주소가 필터링 된 목록의 주문서를 가져옴
	 */
	public List<OrdersVOList> selectDontGrantCjDelivOrderListInMonth(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : cjDeliveryExcelInfo
	 * @date : 2021. 9. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : cj cnplus로 넣을 엑셀파일 가공
	 */
	public File cjDeliveryExcelInfo(OrderSearchVO osVO, String ip, String adminId);
	
	
	/**
	 * 
	 * @MethodName : grantCjDeliveryInvoiceNumBySerialSpecialNumber
	 * @date : 2021. 9. 24.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : primary key 값으로 송장부여가 안될경우 별도의 묶음변호를 기준으로 송장부여
	 */
	public int grantCjDeliveryInvoiceNumBySerialSpecialNumber(List<OrdersVO> orderList, String ip, String adminId);
}
