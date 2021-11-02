package com.gogi.proj.store.seller.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminRolesVO;
import com.gogi.proj.security.AdminServiceImpl;
import com.gogi.proj.security.AdminVO;

@Service
public class SellerInfoServiceImpl implements SellerInfoService{

	private static final Logger logger = LoggerFactory.getLogger(SellerInfoServiceImpl.class);
	
	@Autowired
	private SellerInfoDAO sellerInfoDao;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private PasswordEncoder passEncoder;

	
	@Override
	public List<AdminVO> selectAllSellerList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return sellerInfoDao.selectAllSellerList(osVO);
	}

	@Override
	public AdminVO selectSellerByAdminPk(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return sellerInfoDao.selectSellerByAdminPk(adminVO);
	}

	@Override
	public int updateSellerAdminInfo(AdminVO adminVO) {
		// TODO Auto-generated method stub
		return sellerInfoDao.updateSellerAdminInfo(adminVO);
	}

	@Transactional
	@Override
	public int createSellerAdminInfo(AdminVO adminVO) {
		// TODO Auto-generated method stub
		
		int duple = adminService.selectAdminDuplicateId(adminVO.getAdminId());
		
		if(duple == 0) {
			adminVO.setAdminPass(passEncoder.encode(adminVO.getAdminPass()));
			   
			int adminInsertResult = sellerInfoDao.createSellerAdminInfo(adminVO);

			if(adminInsertResult > 0) {
				int authInsertResult = sellerInfoDao.insertSellerAuth(adminVO);
				
				if(authInsertResult > 0) return SIGNUP_SECCESS;
				else return SIGNUP_ERROR;
				
			}else {
				return SIGNUP_ERROR;
			}
			 
		}else {
			return DUPLI_id;
			   
		}

	}

	@Override
	public int selectAllSellerListCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return sellerInfoDao.selectAllSellerListCount(osVO);
	}

	@Override
	public List<OrdersVO> selectSellerSalesVolume(String adminId) {
		// TODO Auto-generated method stub
		
		AdminVO adVO = sellerInfoDao.selectSellerInfoByAdminId(adminId);
		
		List<String> prodList = new ArrayList<String>();
		for(String prod : adVO.getSellerProdList().split(",")) {
			prodList.add(prod);
			
		}
		
		adVO.setSellerProdArrayList(prodList);
		
		return sellerInfoDao.selectSellerSalesVolume(adVO);
	}

	@Override
	public int updateSellerInfo(AdminVO adVO) {
		// TODO Auto-generated method stub
		
		adVO.setAdminPass(passEncoder.encode(adVO.getAdminPass()));
		
		return sellerInfoDao.updateSellerInfo(adVO);
	}

	@Override
	public AdminVO selectSellerInfoByAdminId(String adminId) {
		// TODO Auto-generated method stub
		return sellerInfoDao.selectSellerInfoByAdminId(adminId);
	}

	@Override
	public int selectSellerSalesSum() {
		// TODO Auto-generated method stub
		return sellerInfoDao.selectSellerSalesSum();
	}

	@Override
	public String selectAuthNumBySellerId(AdminVO adVO) {
		// TODO Auto-generated method stub
		return sellerInfoDao.selectAuthNumBySellerId(adVO);
	}

	@Override
	public int updateSellerAuthNum(AdminVO adVO) {
		// TODO Auto-generated method stub
		return sellerInfoDao.updateSellerAuthNum(adVO);
	}

	@Override
	public List<AdminVO> sellerPermitExchangeCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return sellerInfoDao.sellerPermitExchangeCount(osVO);
	}

	@Override
	public int sellerPermitExchangePagingCount(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return sellerInfoDao.sellerPermitExchangePagingCount(osVO);
	}
	
}
