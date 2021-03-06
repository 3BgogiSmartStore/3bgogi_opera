package com.gogi.proj.freshsolutions.model;

import java.io.File;
import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface FreshSolutionsService {

	public boolean isFreshSolutionsDeliveryArea(String addr, String addrDetail, int delivType);
	
	public OrderSearchVO selectFreshSolutionsDeliveryTargetChecking(OrderSearchVO osVO);
	
	public int selectDontGrantFreshSolutionsDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	public List<OrdersVOList> selectDontGrantFreshSolutionsDelivOrderListInMonth(OrderSearchVO osVO);
	
	public File fFreshSolutionsDeliveryExcelInfo(OrderSearchVO osVO, String ip, String adminId);
	
	public int grantFreshSolutionsDeliveryInvoiceNumBySerialSpecialNumber(List<OrdersVO> orderList, String ip, String adminId);
	
	public int insertOrderHistory(List<OrdersVO> orderList, String ip, String adminId, int delivType);
	
	public String fFreshSolutionsDeliveryAutoUpload(OrderSearchVO osVO, String ip, String adminId, int delivType);
	
}
