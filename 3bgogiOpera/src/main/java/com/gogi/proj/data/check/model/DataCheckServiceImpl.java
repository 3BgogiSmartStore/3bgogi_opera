package com.gogi.proj.data.check.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Service
public class DataCheckServiceImpl implements DataCheckService{

	@Autowired
	private DataCheckDAO dataCheckDao;

	@Override
	public List<OrdersVO> checkBuyerByCntNum(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return dataCheckDao.checkBuyerByCntNum(osVO);
	}
}
