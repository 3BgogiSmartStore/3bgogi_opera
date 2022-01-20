package com.gogi.proj.orders.meshkorea.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.log.model.LogService;
import com.gogi.proj.orders.meshkorea.util.MeshKoreaDataAccess;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.todayPickup.util.URLUtil;


@Service
public class MeshKoreaServiceImpl implements MeshKoreaService{

	@Resource(name="apiKeyProperties")
	private Properties apiKeyProperties;
	
	@Autowired
	private MeshKoreaDAO meshKoreaDao;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private MeshKoreaDataAccess meshKoreaDataAccess;

	@Override
	public boolean isMeshKoreaDeliveryArea(String Addr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int selectDontGrantMeshKoreaDelivListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return meshKoreaDao.selectDontGrantMeshKoreaDelivListInMonthCounting(osVO);
	}

	@Override
	public List<OrdersVOList> selectDontGrantMeshKoreaelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return meshKoreaDao.selectDontGrantMeshKoreaelivOrderListInMonth(osVO);
	}

	@Override
	public void meshKoreaConnect(String url, String methodType, String jsonBody) {
		// TODO Auto-generated method stub
		
		String apikey = apiKeyProperties.getProperty("api_key.mesh_korea_api_key");
		
		String secret = apiKeyProperties.getProperty("api_key.mesh_korea_secret_key");
		
		URLUtil uUtil = new URLUtil();
		
		Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("apikey", apikey);
        
        requestHeaders.put("secret", secret);
        
        requestHeaders.put("Content-type", "application/json;charset=UTF-8");
        
		String parsingString = ""; 
		
		try {
			
			parsingString = uUtil.getCoordiante("https://tmsapi.teamfresh.co.kr/api/order/updateOrder", requestHeaders, methodType, jsonBody);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
