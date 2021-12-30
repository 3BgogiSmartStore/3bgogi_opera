package com.gogi.proj.orders.detail.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.config.model.OrderConfigDAO;
import com.gogi.proj.orders.model.OrdersDAO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.stock.model.StockService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailDAO orderDetailDao;

	@Autowired
	private LogService logService;
	
	@Autowired
	private OrderConfigDAO ocDao;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Override
	public int selectCsDetailAllSearchCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return orderDetailDao.selectCsDetailAllSearchCounting(osVO);
		
	}

	@Override
	public List<OrdersVOList> selectCsDetailAllSearch(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return orderDetailDao.selectCsDetailAllSearch(osVO);
		
	}

	@Transactional
	@Override
	public int deleteOrderEachs(List<Integer> orPks, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		OrdersVO orVO = null; 
		
		for( int i : orPks) {
			
			boolean stockResult = stockService.updateProductChangeValues(orVO);
			
			orVO = ordersDAO.selectOrdersByPk(i);
			
			orVO.setAdminId(adminId);
			orVO.setIp(ip);
			ocDao.insertDeleteOrders(orVO);
			
		}
		
		int result = orderDetailDao.deleteOrderEachs(orPks);
		
		return result;
	}

	@Transactional
	@Override
	public int cancledOrderEachs(List<Integer> orPks, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		int logCount = 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		int result = orderDetailDao.cancledOrderEachs(orPks);
		
		if(result == 0) return 0;
		
		for( int i : orPks) {
			
			OrderHistoryVO ohVO = new OrderHistoryVO();
			ohVO.setOhAdmin(adminId);
			ohVO.setOhIp(ip);
			ohVO.setOrFk(i);
			ohVO.setOhEndPoint("주문");
			ohVO.setOhRegdate(sdf.format(new Date()));
			ohVO.setOhDetail("주문 취소 처리 ");
			
			logCount+=logService.insertOrderHistory(ohVO);
			
		}
		
		return logCount;
	}

	@Transactional
	@Override
	public int cancleBackOrderEachs(List<Integer> orPks, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		int logCount = 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		int result = orderDetailDao.cancleBackOrderEachs(orPks);
		
		if(result == 0) return 0;
		
		for( int i : orPks) {
			
			OrderHistoryVO ohVO = new OrderHistoryVO();
			ohVO.setOhAdmin(adminId);
			ohVO.setOhIp(ip);
			ohVO.setOrFk(i);
			ohVO.setOhEndPoint("주문");
			ohVO.setOhRegdate(sdf.format(new Date()));
			ohVO.setOhDetail("주문 취소 처리 복구 ");
			
			logCount+=logService.insertOrderHistory(ohVO);
			
		}
		
		return logCount;
	}

	@Transactional
	@Override
	public int productMultiChange(List<Integer> orPks,OrdersVO orVO, String ip, String adminId) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		OrderHistoryVO ohVO = null;
		
		for(int i : orPks) {
			orVO.setOrPk(i);
			ohVO = new OrderHistoryVO();
			ohVO.setOhAdmin(adminId);
			ohVO.setOhIp(ip);
			ohVO.setOrFk(i);
			ohVO.setOhEndPoint("상품");
			ohVO.setOhRegdate(sdf.format(new Date()));
			ohVO.setOhDetail("상품 변경, 상품 재고 초기화");
			
			logService.insertOrderHistory(ohVO);
			
			stockService.updateProductChangeValues(orVO);
			
			result += ordersDAO.changeProductAndOptionByOrPk(orVO);
		}
		
		return result;
	}
	
}
