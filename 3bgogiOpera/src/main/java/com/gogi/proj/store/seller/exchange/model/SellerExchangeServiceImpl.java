package com.gogi.proj.store.seller.exchange.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.store.seller.exchange.controller.SellerExchangeController;
import com.gogi.proj.store.seller.exchange.vo.SellerExchangeVO;
import com.gogi.proj.store.seller.model.SellerInfoService;
import com.gogi.proj.store.seller.util.SellerComsCal;

@Service
public class SellerExchangeServiceImpl implements SellerExchangeService {

	private static final Logger logger = LoggerFactory.getLogger(SellerExchangeServiceImpl.class);

	@Autowired
	private SellerExchangeDAO sellerExchangeDao;

	@Autowired
	private SellerInfoService sellerInfoService;

	@Override
	public SellerExchangeVO exchangePosiv(AdminVO adVO) {
		// TODO Auto-generated method stub

		AdminVO adminVO = sellerInfoService.selectSellerInfoByAdminId(adVO.getUsername());

		// 대기중인 환전 내역 개수 확인
		int holdingExchangeCount = sellerExchangeDao.exchangePosiv(adminVO);

		// 하나라도 있다면 내역 보내기
		if (holdingExchangeCount > 0) {

			return sellerExchangeDao.holdingExchangePermission(adminVO);
		} else {

			return null;
		}
	}

	@Override
	public int exchangePermitHistory(AdminVO adVO) {
		// TODO Auto-generated method stub
		
		return sellerExchangeDao.exchangePermitHistory(adVO);
	}

	@Transactional
	@Override
	public String insertExchange(SellerExchangeVO seVO, AdminVO adVO, String authNum) {
		// TODO Auto-generated method stub
		String msg = "";
		// 문자 인증 한 번 더 체크
		String sellerAuthNum = sellerInfoService.selectAuthNumBySellerId(adVO);

		logger.info("환전 단계 문자 인증 체크 - authNum = {}, sellerAuthNum = {}", authNum, sellerAuthNum);
		if (authNum.equals(sellerAuthNum)) {
			logger.info("문자 인증 체크 완료");

		} else {
			msg = "인증번호가 다릅니다.";
			return msg;
		}
		
		adVO.setSellerAuthNum("");
		adVO.setAdminId(adVO.getUsername());
		
		int authUpdateResult = sellerInfoService.updateSellerAuthNum(adVO);
		
		// 환전을 신청한 기록이 있는지 확인
		SellerExchangeVO seHistory = exchangePosiv(adVO);

		if (seHistory != null) {
			msg = "환전 신청 기록이 존재하여 신청이 완료될 때까지 신청할 수 없습니다";
			return msg;
		}

		// 환전 기록 체크
		List<OrdersVO> sellerProdList = sellerInfoService.selectSellerSalesVolume(adVO.getUsername());

		int totalSales = 0;

		for (OrdersVO price : sellerProdList) {
			double commission = SellerComsCal.productCommission(price.getOrProductNumber());
			
			totalSales += (int)( price.getOrTotalPrice() * commission );
		}

		// 셀러 총 정보 조회
		AdminVO adminVO = sellerInfoService.selectSellerInfoByAdminId(adVO.getUsername());

		SellerExchangeVO holdingExchange = sellerExchangeDao.holdingExchangePermission(adminVO);
		
		// 환전이 완료된 과거 금액 총 합
		int totalExchangePrice = exchangePermitHistory(adminVO);

		int holdingExchangePrice = holdingExchange == null ? 0 : holdingExchange.getSeExchangePrice();
		
		// 총 판매 금액과 환전이 완료된 금액 합산
		int exchangePosivPrice = totalSales - totalExchangePrice - holdingExchangePrice;

		
		// 환전 할 수 있는 금액 체크
		if ( exchangePosivPrice < SellerExchangeController.exchangeMin) {
			msg = "환전 가능 금액을 충족하지 못하였습니다";
			return msg;

		}

		seVO.setAdminFk(adminVO.getAdminPk());
		seVO.setSeExchangePrice(exchangePosivPrice);

		int increaseCount = sellerExchangeDao.selectIncreaseCount(adVO);

		seVO.setSeExchangeIncreaseCount(increaseCount + 1);

		int result = sellerExchangeDao.insertExchange(seVO);
		
		if(result > 0) {
			msg = "환전 신청 완료";
			
		}else {
			msg = "환전 신청 실패";
			
		}

		return msg;
	}

	@Override
	public SellerExchangeVO holdingExchangePermission(AdminVO adVO) {
		// TODO Auto-generated method stub
		return sellerExchangeDao.holdingExchangePermission(adVO);
	}

	@Override
	public List<SellerExchangeVO> searchSellerExchangeHistory(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return sellerExchangeDao.searchSellerExchangeHistory(adminVO);
	}

	@Override
	public List<AdminVO> searchDontPermitExchanges(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return sellerExchangeDao.searchDontPermitExchanges(osVO);
	}

	@Override
	public int permitExchangeReq(SellerExchangeVO seVO) {
		// TODO Auto-generated method stub
		return sellerExchangeDao.permitExchangeReq(seVO);
	}

	@Override
	public int searchDontPermitExchangesCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return sellerExchangeDao.searchDontPermitExchangesCount(osVO);
	}

	@Override
	public List<SellerExchangeVO> selectAllSellerExchangeByAdminPk(AdminVO adVO) {
		// TODO Auto-generated method stub
		return sellerExchangeDao.selectAllSellerExchangeByAdminPk(adVO);
	}

}
