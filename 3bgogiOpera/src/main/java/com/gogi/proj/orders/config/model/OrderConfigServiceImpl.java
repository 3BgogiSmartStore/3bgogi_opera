package com.gogi.proj.orders.config.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.log.model.LogDAO;
import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.config.vo.ExceptAddressKeywordVO;
import com.gogi.proj.orders.config.vo.OrdersDeleteVO;
import com.gogi.proj.orders.config.vo.ReqFilterKeywordVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

@Service
public class OrderConfigServiceImpl implements OrderConfigService {

	@Autowired
	private OrderConfigDAO orderConfigDao;
	
	@Autowired
	private LogDAO logDao;
	
	@Autowired
	private LogService logService;


	@Override
	public int insertExceptAddressKeyword(ExceptAddressKeywordVO eakVO) {
		// TODO Auto-generated method stub
		return orderConfigDao.insertExceptAddressKeyword(eakVO);
	}

	@Override
	public List<ExceptAddressKeywordVO> selectExceptAddressKeyword() {
		// TODO Auto-generated method stub
		return orderConfigDao.selectExceptAddressKeyword();
	}

	@Override
	public int deleteExceptAddressKeyword(ExceptAddressKeywordVO eakVO) {
		// TODO Auto-generated method stub
		return orderConfigDao.deleteExceptAddressKeyword(eakVO);
	}

	@Override
	public List<OrdersVO> exceptAddrTargetOrder(List<ExceptAddressKeywordVO> eakList) {
		// TODO Auto-generated method stub
		return orderConfigDao.exceptAddrTargetOrder(eakList);
	}

	@Override
	public int updateSpecialRegionOrder(List<Integer> orList) {
		// TODO Auto-generated method stub
		return orderConfigDao.updateSpecialRegionOrder(orList);
	}

	@Override
	public int searchEceptAddrAndUpdateCheckFlag(List<ExceptAddressKeywordVO> eakList) {
		// TODO Auto-generated method stub
		return orderConfigDao.searchEceptAddrAndUpdateCheckFlag(eakList);
	}

	@Override
	public int insertReqFilterKeyword(ReqFilterKeywordVO rfkVO) {
		// TODO Auto-generated method stub
		return orderConfigDao.insertReqFilterKeyword(rfkVO);
	}

	@Override
	public List<ReqFilterKeywordVO> selectAllReqFilterKeywordList() {
		// TODO Auto-generated method stub
		return orderConfigDao.selectAllReqFilterKeywordList();
	}

	@Override
	public List<ReqFilterKeywordVO> selectUseReqFilterKeywordList() {
		// TODO Auto-generated method stub
		return orderConfigDao.selectUseReqFilterKeywordList();
	}

	@Override
	public int deleteReqFilterKeywordByPk(ReqFilterKeywordVO rfkVO) {
		// TODO Auto-generated method stub
		return orderConfigDao.deleteReqFilterKeywordByPk(rfkVO);
	}

	@Override
	public int updateReqFilterKeywordUseOrUnuse(ReqFilterKeywordVO rfkVO) {
		// TODO Auto-generated method stub
		return orderConfigDao.updateReqFilterKeywordUseOrUnuse(rfkVO);
	}

	@Override
	public List<OrdersVOList> selectPackingIrreTargetOrderList() {
		// TODO Auto-generated method stub

		List<OrdersVO> targetOrderNumber = orderConfigDao.selectPackingIrreTargetOrder();

		if (targetOrderNumber.size() != 0) {
			List<OrdersVOList> targetOrder = orderConfigDao.selectPackingIrreTargetOrderList(targetOrderNumber);
			return targetOrder;
		} else {
			return null;
		}

	}

	@Override
	public int selectPackingIrreTargetOrderCounting() {
		// TODO Auto-generated method stub
		return orderConfigDao.selectPackingIrreTargetOrderCounting();
	}

	@Override
	public List<OrdersDeleteVO> selectOrdersDeleteList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return orderConfigDao.selectOrdersDeleteList(osVO);
	}

	@Override
	public int selectOrdersDeleteListCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return orderConfigDao.selectOrdersDeleteListCounting(osVO);
	}

	@Override
	public int addTempSendingDeadline(OrderSearchVO osVO, String ip, String adminId) {
		// TODO Auto-generated method stub
		OrdersVO changeOr = null;
		OrderHistoryVO ohVO = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date now = new Date();

		String todayYMD = sdf.format(now);

		for (int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {

			changeOr = new OrdersVO();
			changeOr.setOrSerialSpecialNumber(osVO.getOrSerialSpecialNumberList().get(i));

			List<OrdersVO> changeOrderList = logDao.selectOrdersChangeBeforeValueBySerialSpecialNumber(changeOr);

			for (int j = 0; j < changeOrderList.size(); j++) {

				ohVO = new OrderHistoryVO();

				ohVO.setOrFk(changeOrderList.get(j).getOrPk());
				ohVO.setOhIp(ip);
				ohVO.setOhAdmin(adminId);
				ohVO.setOhEndPoint("cs - ?????? ????????? ??????");
				ohVO.setOhRegdate(todayYMD);
				ohVO.setOhDetail("?????? ????????? [ "+osVO.getDateStart()+" ]");

				logService.insertOrderHistory(ohVO);

			}

		}

		return orderConfigDao.addTempSendingDeadline(osVO);
	}

}
