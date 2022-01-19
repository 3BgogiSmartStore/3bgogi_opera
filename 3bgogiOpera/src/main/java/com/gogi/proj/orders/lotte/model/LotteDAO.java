package com.gogi.proj.orders.lotte.model;

import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface LotteDAO {
	
	public int selectDontGrantLotteDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	public List<OrdersVOList> selectDontGrantLotteDelivOrderListInMonth(OrderSearchVO osVO);
	
	public List<OrdersVO> selectLotteDeliveryTargetChecking();
	
	public int updateLotteDeliveryTargetBeforeGrantInvoiceNum(List<OrdersVO> orderList);
	
	public int grantLotteDeliveryInvoiceNumByOrPk(OrderSearchVO osVO);
	
	public int grantLotteDeliveryInvoiceNumBySerialSpecialNumber(OrdersVO osVO);
	
	public List<OrdersVO> selectLotteDeliveryExcelTarget(OrderSearchVO osVO);
	
	public List<OrdersVO> selectOrdersPkByOrSerialSpecialNumberForGrantLotteInvoiceNum(String orSerialSpecialNumber);
	
	public int [] grantLotteDeliveryInvoiceNumByBatchUpdate(List<OrdersVO> orderList);
	
}
