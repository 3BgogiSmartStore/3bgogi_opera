package com.gogi.proj.delivery.config.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.delivery.config.vo.DoorPassKeywordVO;
import com.gogi.proj.delivery.config.vo.DoorPassVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivAreaVO;
import com.gogi.proj.delivery.config.vo.EarlyDelivTypeVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class DeliveryConfigDAOMybatis extends SqlSessionDaoSupport implements DeliveryConfigDAO {

	private String namespace = "delivery.config";
	private String deliveryCheck = "delivery.check";
	private String doorPassKeyword = "delivery.config.door_pass_keyword";

	@Override
	public List<OrdersVO> selectDelivNumCheckTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(deliveryCheck+".selectDelivNumCheckTarget", osVO);
	}

	@Override
	public List<EarlyDelivTypeVO> earlyDelivType() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".earlyDelivType");
	}

	@Override
	public int insertEarlyDelivArea(EarlyDelivAreaVO edaVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertEarlyDelivArea", edaVO);
	}

	@Override
	public List<EarlyDelivTypeVO> selectEarlyDelivArea(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectEarlyDelivArea", osVO);
	}

	@Override
	public int earlyDelivAreaCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".earlyDelivAreaCount", osVO);
	}

	@Override
	public List<EarlyDelivAreaVO> allEarlyDelivArea(int companyNum) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".allEarlyDelivArea", companyNum);
	}

	@Override
	public int deleteEarlyDelivArea(EarlyDelivAreaVO edaVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace+".deleteEarlyDelivArea", edaVO);
	}

	@Override
	public int insertDoorPassKeyword(DoorPassKeywordVO dpkVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(doorPassKeyword+".insertDoorPassKeyword", dpkVO);
	}

	@Override
	public List<DoorPassKeywordVO> selectAllDoorPassKeyword() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(doorPassKeyword+".selectAllDoorPassKeyword");
	}

	@Override
	public int deleteDoorPassKeyword(DoorPassKeywordVO dpkVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(doorPassKeyword+".deleteDoorPassKeyword", dpkVO);
	}

	@Override
	public DoorPassVO selectDoorPassMsgByOrderInfo(OrdersVOList orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(deliveryCheck+".selectDoorPassMsgByOrderInfo", orVO);
	}

	@Override
	public int updateOrderDoorPassByOrSerialSpecialNumber(OrdersVOList orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(deliveryCheck+".updateOrderDoorPassByOrSerialSpecialNumber", orVO);
	}

	@Override
	public int insertDoorPassMsg(DoorPassVO dpVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(deliveryCheck+".insertDoorPassMsg", dpVO);
	}

	@Override
	public OrdersVO selectOrderInfoForDoorPass(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(deliveryCheck+".selectOrderInfoForDoorPass", orSerialSpecialNumber);
	}

	@Override
	public List<OrdersVO> selectCjDelivDoorPassMsgTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(deliveryCheck+".selectCjDelivDoorPassMsgTarget", osVO);
	}

	@Override
	public DoorPassVO selectDoorPassMsgByOrderInfoNotList(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(deliveryCheck+".selectDoorPassMsgByOrderInfoNotList", orVO);
	}

	@Override
	public List<OrdersVO> selectOrdersBySendingDeadline(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(deliveryCheck+".selectOrdersBySendingDeadline", osVO);
	}

	@Override
	public int selectDelivCompanyByOrSerialSpecialNumber(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(deliveryCheck+".selectDelivCompanyByOrSerialSpecialNumber", orSerialSpecialNumber);
	}
	
}
