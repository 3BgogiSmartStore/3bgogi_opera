package com.gogi.proj.todayPickup.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogi.proj.kakao.KakaoAPI;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.todayPickup.model.TodayPickupService;

@Controller
public class TodayPickupController {

	private static final Logger logger = LoggerFactory.getLogger(TodayPickupController.class);
	
	@Autowired
	private TodayPickupService todayPickupService;
	
	@Resource(name="apiKeyProperties")
	private Properties apiKeyProperties;
	
	
	
	public String getTodayPickupAuthorizations()  throws MalformedURLException, IOException {
		
		String userName= apiKeyProperties.getProperty("api_key.todaypickup.user_name");
		String password= apiKeyProperties.getProperty("api_key.todaypickup.password");
		
		String login = "https://mall.todaypickup.com/mall/login?username="+userName+"&password="+password;

		URL obj = new URL(login);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// add reuqest header
		con.setRequestMethod("POST");

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();

		Charset charset = Charset.forName("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		String Authorizations = "";
		Map headers = con.getHeaderFields();
		Set<String> keys = headers.keySet();
		for (String key : keys) {
			if (key != null && key.equals("Authorization")) {
				Authorizations = con.getHeaderField(key);
			}
			String val = con.getHeaderField(key);

		}
		
		return Authorizations;
		
	}
			
	@RequestMapping(value="/security/create_today_pickup_invoice.do", method=RequestMethod.POST)
	public String createDelivInvoice(HttpServletRequest request, @ModelAttribute OrderSearchVO osVO, Model model) throws MalformedURLException, IOException {
		
		String Authorizations = getTodayPickupAuthorizations();
		
		Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("Authorization", Authorizations);
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("Content-Type", "application/json");
        
        
        Map<String, String> kakaoRequestHeaders = new HashMap<>();
        kakaoRequestHeaders.put("Authorization", "KakaoAK bcbba5428da89430f8eb29996ec26f2a");
        
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		List<OrdersVO> orList = new ArrayList<>();
		List<OrdersVO> errorOr = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today= new Date();
		
		String formatDate = sdf.format(today);
		
		OrdersVO orVO = null;
		for(int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {
			osVO.setSearchKeyword(osVO.getOrSerialSpecialNumberList().get(i));
			
			orVO = todayPickupService.updateTodayPickupInvoiceNumber(osVO, request.getRemoteAddr(), adminVo.getUsername(), formatDate, Authorizations, requestHeaders, kakaoRequestHeaders);
			
			if(orVO != null && orVO.getRegiNo() != null) {
				
				orList.add(orVO);
				
			}else {
				errorOr.add(orVO);
			}
		}

		
		
		
		//실패건이 없을 경우
		
		
		/*
		
		String msg = "오늘의 픽업 송장 부여 완료건 = "+orList.size();
		String url = "/security/epost.do";
		
		if(errorOr.size() != 0) {			
			msg = msg+", 실패건 = "+errorOr.size()+",  - 실패건을 확인해주세요 - ";
			
			model.addAttribute("parentHref","/security/epost.do?edtFk=1");
			
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		model.addAttribute("reload",true);
		model.addAttribute("closing",true);
		
		return "common/message";
		
		*/
		
		model.addAttribute("orList",orList);
		model.addAttribute("errorOr",errorOr);
		
		return "delivery/create_today_pickup_deliv_invoice";

	}
	
	/**
	 * 
	 * @MethodName : reprintingDelivInvoice
	 * @date : 2021. 8. 9.
	 * @author : Jeon KiChan
	 * @param request
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 오늘의 픽업 송장 재출력하기
	 */
	@RequestMapping(value="/reprinting_today_pickup_deliv_invoice.do", method=RequestMethod.POST)
	public String reprintingDelivInvoice(HttpServletRequest request, @ModelAttribute OrderSearchVO osVO, Model model) {
		List<OrdersVO> orList = new ArrayList<>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		for(int i = 0; i < osVO.getOrSerialSpecialNumberList().size(); i++) {
			osVO.setSearchKeyword(osVO.getOrSerialSpecialNumberList().get(i));
			orList.add(todayPickupService.todayPickupDeliveryInvoiceNumberReprinting(osVO, request.getRemoteAddr(), adminVo.getUsername() ));
		}
		
		
		/*for( OrdersVO or : orList) {
			int temp = 0;
			if(or.getProductOptionList().get(0).getProdSorting() == 0 && or.getProductOptionList().size() > 1) {				
				for( int i = 1; i < or.getProductOptionList().size(); i++) {
					if( or.getProductOptionList().get(i).getProdSorting() == 0 ) {
						or.getProductOptionList().get(0).setProdSorting(1);
						break;
					}
				}
			}
		}

		
		Collections.sort(orList);*/
		
		model.addAttribute("orList",orList);
		
		return "delivery/create_today_pickup_deliv_invoice";
	}
	
}
