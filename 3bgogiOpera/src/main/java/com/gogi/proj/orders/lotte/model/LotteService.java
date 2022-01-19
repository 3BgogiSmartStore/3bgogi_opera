package com.gogi.proj.orders.lotte.model;

import java.io.File;
import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface LotteService {

	/**
	 * 
	 * @MethodName : selectDontGrantLotteDelivOrderListInMonthCounting
	 * @date : 2022. 1. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 :  롯데택배  수량을 가져옴
	 */
	public int selectDontGrantLotteDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectDontGrantLotteDelivOrderListInMonth
	 * @date : 2022. 1. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 롯데택배 주문서를 가져옴
	 */
	public List<OrdersVOList> selectDontGrantLotteDelivOrderListInMonth(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : lotteDeliveryExcelInfo
	 * @date : 2022. 1. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param ip
	 * @param adminId
	 * @return
	 * @메소드설명 : 롯데택배 시스템에 넣을 엑셀파일 가공
	 */
	public File lotteDeliveryExcelInfo(OrderSearchVO osVO, String ip, String adminId);
	
	
	/**
	 * 
	 * @MethodName : grantLotteDeliveryInvoiceNumBySerialSpecialNumber
	 * @date : 2022. 1. 19.
	 * @author : Jeon KiChan
	 * @param orderList
	 * @param ip
	 * @param adminId
	 * @return
	 * @메소드설명 : primary key 값으로 송장부여가 안될경우 별도의 묶음변호를 기준으로 송장부여
	 */
	public int grantLotteDeliveryInvoiceNumBySerialSpecialNumber(List<OrdersVO> orderList, String ip, String adminId);
	
	
	public int insertOrderHistory(List<OrdersVO> orderList, String ip, String adminId);
	
}
