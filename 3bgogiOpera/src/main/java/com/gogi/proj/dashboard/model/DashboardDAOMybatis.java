package com.gogi.proj.dashboard.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;

@Repository
public class DashboardDAOMybatis extends SqlSessionDaoSupport implements DashboardDAO{

	private String dashboardTaxNameSpace = "dashboard.tax";
	private String dashboardMeatNameSpace = "dashboard.meat";
	private String dashboardSeasoningMeatNameSpace = "dashboard.seasoning";
	private String dashboardMealkitSpace = "dashboard.mealkit";

	@Override
	public List<ProductInfoVO> selectProductTaxInfoType(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(dashboardTaxNameSpace+".selectProductTaxInfoType", osVO);
	}

	@Override
	public List<ProductInfoVO> selectProductTaxInfoBySuperPiType(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(dashboardTaxNameSpace+".selectProductTaxInfoBySuperPiType", osVO);
	}

	@Override
	public List<ProductInfoVO> selectAllProductTaxInfoForExcelFile(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(dashboardTaxNameSpace+".selectAllProductTaxInfoForExcelFile", osVO);
	}

	@Override
	public List<CostIoVO> selectMeatTotalWeightInSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(dashboardMeatNameSpace+".selectMeatTotalWeightInSale", osVO);
	}

	@Override
	public int selectMeatTotalPriceInSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(dashboardMeatNameSpace+".selectMeatTotalPriceInSale", osVO);
	}

	@Override
	public List<CostIoVO> selectSeasoningMeatTotalWeightInSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(dashboardSeasoningMeatNameSpace+".selectSeasoningMeatTotalWeightInSale", osVO);
	}

	@Override
	public int selectSeasoningMeatTotalPriceInSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(dashboardSeasoningMeatNameSpace+".selectSeasoningMeatTotalPriceInSale", osVO);
	}

	@Override
	public List<OrdersVO> selectMealkitTotalSale(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(dashboardMealkitSpace+".selectMealkitTotalSale", osVO);
	}

	@Override
	public int selectMealkikTotalSalePrice(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(dashboardMealkitSpace+".selectMealkikTotalSalePrice", osVO);
	}
}
