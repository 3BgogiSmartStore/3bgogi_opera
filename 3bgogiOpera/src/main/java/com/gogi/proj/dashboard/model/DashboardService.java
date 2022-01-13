package com.gogi.proj.dashboard.model;

import java.io.File;
import java.util.List;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;

public interface DashboardService {

	
	/**
	 * 
	 * @MethodName : selectProductTaxInfoType
	 * @date : 2022. 1. 5.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 거래내역서 상위 카테고리 목록 가져오기 (일자안에 있는 건들만)
	 */
	public List<ProductInfoVO> selectProductTaxInfoType(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectProductTaxInfoBySuperPiType
	 * @date : 2022. 1. 5.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 거래내역서 상위 카테고리로 하위 목록 자세히 가져오기
	 */
	public List<ProductInfoVO> selectProductTaxInfoBySuperPiType(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : productTaxInfoExcelWrite
	 * @date : 2022. 1. 6.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 거래내역서 엑셀 파일 작성
	 */
	public File productTaxInfoExcelWrite(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectMeatTotalWeightInSale
	 * @date : 2022. 1. 6.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 한돈, 한우 나간 무게 계산
	 */
	public List<CostIoVO> selectMeatTotalWeightInSale(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectMeatTotalPriceInSale
	 * @date : 2022. 1. 6.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 한돈, 한우 매출 계산
	 */
	public int selectMeatTotalPriceInSale(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectSeasoningMeatTotalWeightInSale
	 * @date : 2022. 1. 6.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 양념육 정육 무게 계산
	 */
	public List<CostIoVO> selectSeasoningMeatTotalWeightInSale(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectSeasoningMeatTotalPriceInSale
	 * @date : 2022. 1. 6.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 양념육 매출 계산
	 */
	public int selectSeasoningMeatTotalPriceInSale(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectMealkitTotalSale
	 * @date : 2022. 1. 6.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 간편조리 판매 개수
	 */
	public List<OrdersVO> selectMealkitTotalSale(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectMealkikTotalSalePrice
	 * @date : 2022. 1. 6.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 간편조리 매출
	 */
	public int selectMealkikTotalSalePrice(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : threeMonthTotalSales
	 * @date : 2022. 1. 13.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 지난 3개월 총 매출
	 */
	public List<OrdersVO> threeMonthTotalSales();
	
	
	/**
	 * 
	 * @MethodName : monthTotalSales
	 * @date : 2022. 1. 13.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 이번달 일별 매출
	 */
	public List<OrdersVO> monthTotalSales();
}
