package com.gogi.proj.analytics.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.analytics.vo.LocalAreaVO;
import com.gogi.proj.another.vo.DatesVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

public interface AnalyticsService {

	//OrderSearchVO를 parameter로 둬서 후에 재사용이 가능하도록 함
		//7일간 매출 수수료 계산 x
		public List<OrdersVO> sevendaysTotalSalesWithoutCommision(OrderSearchVO osVO);
		
		//7일간 매출 수수료 계산, 
		public List<OrdersVO> sevendaysTotalSales(OrderSearchVO osVO);
		
		//7일간 발송 건 수
		public List<OrdersVO> sevendaysSendingOut(OrderSearchVO osVO);
		
		//최근 6개월 간 매출
		public List<OrdersVO> sixMonthTotalSales(OrderSearchVO osVO);
		
		//최근 7일간 물품 보낸 개수
		public List<OrdersVO> selectSevenDaysOutPutProductQty();
		
		public List<Map<String, Object>> selectTodayDeliveryCount();
		
		public List<Map<String, Object>> selectMainDeliveryResult();
		
		//최근 한달간의 가격대별 주문 개수 확인하기 
		public List<Map<String, Object>> selectPriceChartInsert();
		
		//통계 테스트
		public List<Map<String, Object>> selectAnalyDataList(OrderSearchVO osVO);
		
		/**
		 * 
		 * @MethodName : selectReservProductQty
		 * @date : 2020. 9. 9.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 예약된 물품 개수 보기
		 */
		public List<Map<String, Object>> selectReservProductQty(OrderSearchVO osVO);
		
		
		public List<Map<String, Object>> selectReservProductQtyInMonth(DatesVO datesVO);
		
		
		/**
		 * 
		 * @MethodName : selectTotalSalesByDates
		 * @date : 2020. 10. 13.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 매출 조회하기
		 */
		public List<OrdersVO> selectTotalSalesByDates(OrderSearchVO osVO);
		
		
		/**
		 * 
		 * @MethodName : selectCancledSalesByDates
		 * @date : 2020. 10. 13.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 취소 건 조회하기
		 */
		public List<OrdersVO> selectCancledSalesByDates(OrderSearchVO osVO);
		
		
		/**
		 * 
		 * @MethodName : selectLocalAreaAnalytics
		 * @date : 2020. 10. 28.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 지역별 기본 통계
		 */
		public List<OrdersVO> selectLocalAreaAnalytics(OrderSearchVO osVO);
		
		/**
		 * 
		 * @MethodName : sleectLocalAreaTopProducts
		 * @date : 2020. 10. 29.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 특정 지역 카테고리 별 상품 판매 순위 
		 */
		public List<LocalAreaVO> sleectLocalAreaTopProducts(OrderSearchVO osVO);
		
		/**
		 * 
		 * @MethodName : localAreaAnlayDetail
		 * @date : 2020. 10. 29.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 특징 지역 판매량 등등
		 */
		public LocalAreaVO localAreaAnlayDetail(OrderSearchVO osVO);
		
		
		/**
		 * 
		 * @MethodName : selectLocalAreaInflowRoute
		 * @date : 2020. 10. 29.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 특징 지역 유입루트
		 */
		public List<LocalAreaVO> selectLocalAreaInflowRoute(OrderSearchVO osVO);
		
		
		/**
		 * 
		 * @MethodName : selectProductSales
		 * @date : 2021. 10. 22.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 상품명으로 나간 수량 체크
		 */
		public List<OrdersVO> selectProductSales(OrderSearchVO osVO);
		
		/**
		 * 
		 * @MethodName : selectProductSalesTotalPrice
		 * @date : 2021. 10. 22.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 단순 매출 합산 ( 수수료 포함시키지 않음)
		 */
		public int selectProductSalesTotalPrice(OrderSearchVO osVO);
		
		/**
		 * 
		 * @MethodName : mainDeliveryTypeResult
		 * @date : 2021. 10. 26.
		 * @author : Jeon KiChan
		 * @return
		 * @메소드설명 : 메인페이지 우체국, 그외 택배사 피킹 결과
		 */
		public Map<String, Object> mainDeliveryTypeResult();
		
		
		/**
		 * 
		 * @MethodName : notMatchingOrder
		 * @date : 2021. 12. 8.
		 * @author : Jeon KiChan
		 * @return
		 * @메소드설명 : 매칭되지 않은 주문 개수
		 */
		public int notMatchingOrder();
		
		
		/**
		 * 
		 * @MethodName : dontGrantInvoiceOrder
		 * @date : 2021. 12. 8.
		 * @author : Jeon KiChan
		 * @return
		 * @메소드설명 : 송장미부여 주문 개수
		 */
		public int dontGrantInvoiceOrder();
		
		
		/**
		 * 
		 * @MethodName : notInvenOrder
		 * @date : 2021. 12. 8.
		 * @author : Jeon KiChan
		 * @return
		 * @메소드설명 : 재고할당이 되지 않은 주문 개수
		 */
		public int notInvenOrder();
		
		
		/**
		 * 
		 * @MethodName : sendingWeitOrder
		 * @date : 2021. 12. 8.
		 * @author : Jeon KiChan
		 * @return
		 * @메소드설명 : 발송되지 않은 주문 개수
		 */
		public int sendingWeitOrder();
		
		
		/**
		 * 
		 * @MethodName : sendingFinishOrder
		 * @date : 2021. 12. 8.
		 * @author : Jeon KiChan
		 * @return
		 * @메소드설명 : 발송 완료된 주문 개수
		 */
		public int sendingFinishOrder();
		
		
		/**
		 * 
		 * @MethodName : depositOrder
		 * @date : 2021. 12. 8.
		 * @author : Jeon KiChan
		 * @return
		 * @메소드설명 : 입금처리가 되지 않은 주문서
		 */
		public int depositOrder();
		
		
		/**
		 * 
		 * @MethodName : insertOrderCountBySendingDeadline
		 * @date : 2021. 12. 8.
		 * @author : Jeon KiChan
		 * @return
		 * @메소드설명 : 입력된 주문서 일주일간의 개수 나타내기 (발송기한 기준으로)
		 */
		public List<OrdersVO> insertOrderCountBySendingDeadline();
		
		/**
		 * 
		 * @MethodName : insertReservOrderCountBySendingDeadline
		 * @date : 2021. 12. 15.
		 * @author : Jeon KiChan
		 * @return
		 * @메소드설명 : 예약된 주문서 일주일간의 개수 나타내기 (발송기한 기준으로) 
		 */
		public List<OrdersVO> insertReservOrderCountBySendingDeadline();
		
		
		/**
		 * 
		 * @MethodName : selectReservProductQty
		 * @date : 2021. 12. 21.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 예약된 물품 개수 보기 선물세트만
		 */
		public List<Map<String, Object>> selectReservProductQtyInGiftSet(OrderSearchVO osVO);
}
