package com.gogi.proj.delivery.config.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.delivery.config.vo.DoorPassKeywordVO;
import com.gogi.proj.delivery.config.vo.DoorPassVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

@Service
public class DeliveryConfigServiceImple implements DeliveryConfigService{

	private static final Logger logger = LoggerFactory.getLogger(DeliveryConfigServiceImple.class);
	
	@Autowired
	private DeliveryConfigDAO dcDao;

	@Override
	public List<OrdersVO> selectDelivNumCheckTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dcDao.selectDelivNumCheckTarget(osVO);
	}

	@Override
	public List<EarlyDelivTypeVO> earlyDelivType() {
		// TODO Auto-generated method stub
		return dcDao.earlyDelivType();
	}

	@Override
	public int insertEarlyDelivArea(EarlyDelivAreaVO edaVO) {
		// TODO Auto-generated method stub
		return dcDao.insertEarlyDelivArea(edaVO);
	}

	@Override
	public List<EarlyDelivTypeVO> selectEarlyDelivArea(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dcDao.selectEarlyDelivArea(osVO);
	}

	@Override
	public int earlyDelivAreaCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dcDao.earlyDelivAreaCount(osVO);
	}

	@Override
	public boolean isEarlyDelivArea(String addr, int delivCompany) {
		// TODO Auto-generated method stub
		boolean posib = true;
		
		List<EarlyDelivAreaVO> edaList = dcDao.allEarlyDelivArea(delivCompany);
		
		for( EarlyDelivAreaVO edaVO : edaList) {
			
			if(edaVO.isEdaSearchTypeFlag() == true) {
				if(addr.contains(edaVO.getEdaAddr())) return false;
				
			}else {
				if(addr.equals(edaVO.getEdaAddr())) return false;
				
			}
			
		}
		
		
		return posib;
	}

	@Override
	public int deleteEarlyDelivArea(EarlyDelivAreaVO edaVO) {
		// TODO Auto-generated method stub
		return dcDao.deleteEarlyDelivArea(edaVO);
	}

	@Override
	public int insertDoorPassKeyword(DoorPassKeywordVO dpkVO) {
		// TODO Auto-generated method stub
		return dcDao.insertDoorPassKeyword(dpkVO);
	}

	@Override
	public List<DoorPassKeywordVO> selectAllDoorPassKeyword() {
		// TODO Auto-generated method stub
		return dcDao.selectAllDoorPassKeyword();
	}

	@Override
	public int deleteDoorPassKeyword(DoorPassKeywordVO dpkVO) {
		// TODO Auto-generated method stub
		return dcDao.deleteDoorPassKeyword(dpkVO);
	}

	@Override
	public OrdersVOList doorPassCheck(OrdersVOList orVO) {
		// TODO Auto-generated method stub
		
		DoorPassVO dpVO = dcDao.selectDoorPassMsgByOrderInfo(orVO);
		
		if(dpVO != null ){
			orVO.setOrDelivEnter(dpVO.getDpMsg());
			
			dcDao.updateOrderDoorPassByOrSerialSpecialNumber(orVO);
		}
		
		return orVO;
	}

	@Override
	public int insertDoorPassMsg(DoorPassVO dpVO) {
		// TODO Auto-generated method stub
		return dcDao.insertDoorPassMsg(dpVO);
	}

	@Override
	public OrdersVO selectOrderInfoForDoorPass(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return dcDao.selectOrderInfoForDoorPass(orSerialSpecialNumber);
	}

	@Override
	public DoorPassVO selectDoorPassMsgByOrderInfo(OrdersVOList orVO) {
		// TODO Auto-generated method stub
		return dcDao.selectDoorPassMsgByOrderInfo(orVO);
	}

	@Override
	public List<OrdersVO> selectCjDelivDoorPassMsgTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dcDao.selectCjDelivDoorPassMsgTarget(osVO);
	}

	@Override
	public DoorPassVO selectDoorPassMsgByOrderInfoNotList(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return dcDao.selectDoorPassMsgByOrderInfoNotList(orVO);
	}

	@Override
	public List<OrdersVO> selectOrdersBySendingDeadline(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dcDao.selectOrdersBySendingDeadline(osVO);
	}
	
}
