package com.gogi.proj.analytics.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.analytics.vo.LocalAreaVO;
import com.gogi.proj.another.vo.DatesVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

@Repository
public class AnalyticsDAOMybatis extends SqlSessionDaoSupport implements AnalyticsDAO {

	private String namespace = "analytics.customer";
	
	private String productNameSpace = "analytics.product";
	
	private String mainNameSpace = "analytics.main";

	@Override
	public List<OrdersVO> sevendaysTotalSalesWithoutCommision(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sevendaysTotalSalesWithoutCommision",osVO);
	}

	@Override
	public List<OrdersVO> sevendaysTotalSales(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sevendaysTotalSales",osVO);
	}

	@Override
	public List<OrdersVO> sevendaysSendingOut(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sevendaysSendingOut",osVO);
	}

	@Override
	public List<OrdersVO> sixMonthTotalSales(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sixMonthTotalSales", osVO);
	}

	@Override
	public List<OrdersVO> selectSevenDaysOutPutProductQty() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectSevenDaysOutPutProductQty");
	}

	@Override
	public List<Map<String, Object>> selectTodayDeliveryCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(productNameSpace+".selectTodayDeliveryCount");
	}

	@Override
	public List<Map<String, Object>> selectMainDeliveryResult() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(mainNameSpace+".selectMainDeliveryResult");
	}

	@Override
	public List<Map<String, Object>> selectPriceChartInsert() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(mainNameSpace+".selectPriceChartInsert");
	}

	@Override
	public List<Map<String, Object>> selectAnalyDataList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectAnalyDataList", osVO);
	}

	@Override
	public List<Map<String, Object>> selectReservProductQty(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectReservProductQty", osVO);
	}

	@Override
	public List<Map<String, Object>> selectReservProductQtyInMonth(DatesVO datesVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectReservProductQtyInMonth", datesVO);
	}

	@Override
	public List<OrdersVO> selectTotalSalesByDates(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectTotalSalesByDates", osVO);
	}

	@Override
	public List<OrdersVO> selectCancledSalesByDates(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCancledSalesByDates", osVO);
	}

	@Override
	public List<OrdersVO> selectLocalAreaAnalytics(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectLocalAreaAnalytics", osVO);
	}

	@Override
	public List<LocalAreaVO> sleectLocalAreaTopProducts(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sleectLocalAreaTopProducts", osVO);
	}

	@Override
	public LocalAreaVO localAreaAnlayDetail(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".localAreaAnlayDetail", osVO);
	}

	@Override
	public List<LocalAreaVO> selectLocalAreaInflowRoute(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectLocalAreaInflowRoute", osVO);
	}

	@Override
	public List<OrdersVO> selectProductSales(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(productNameSpace+".selectProductSales", osVO);
	}

	@Override
	public int selectProductSalesTotalPrice(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(productNameSpace+".selectProductSalesTotalPrice", osVO);
	}

	@Override
	public Map<String, Object> mainDeliveryTypeResult() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(mainNameSpace+".mainDeliveryTypeResult");
	}

	@Override
	public int notMatchingOrder() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(mainNameSpace+".notMatchingOrder");
	}

	@Override
	public int dontGrantInvoiceOrder() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(mainNameSpace+".dontGrantInvoiceOrder");
	}

	@Override
	public int notInvenOrder() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(mainNameSpace+".notInvenOrder");
	}

	@Override
	public int sendingWeitOrder() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(mainNameSpace+".sendingWeitOrder");
	}

	@Override
	public int sendingFinishOrder() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(mainNameSpace+".sendingFinishOrder");
	}

	@Override
	public int depositOrder() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(mainNameSpace+".depositOrder");
	}

	@Override
	public List<OrdersVO> insertOrderCountBySendingDeadline() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(mainNameSpace+".insertOrderCountBySendingDeadline");
	}

	@Override
	public List<OrdersVO> insertReservOrderCountBySendingDeadline() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(mainNameSpace+".insertReservOrderCountBySendingDeadline");
	}

	@Override
	public List<Map<String, Object>> selectReservProductQtyInGiftSet(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectReservProductQtyInGiftSet", osVO);
	}
	
}
