package com.gogi.proj.data.check.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogi.proj.data.check.model.DataCheckService;
import com.gogi.proj.data.check.util.DataCheckExcelUtil;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.FileuploadUtil;

@Controller
public class DataCheckController {

	private static final Logger logger = LoggerFactory.getLogger(DataCheckController.class);
	
	@Autowired
	private DataCheckService dataCheckService;
	
	@Autowired
	private FileuploadUtil fileUploadUtil;
	
	@Autowired
	private DataCheckExcelUtil dc;
	
	@RequestMapping(value="/data/excel_search.do", method=RequestMethod.GET)
	public String excelDataMatchingGet(HttpServletRequest request, Model model) {
		
		Calendar calendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		Date sevenDays = calendar.getTime();
		Date today = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		
		OrderSearchVO osVO = new OrderSearchVO();
		
		osVO.setDateStart(sdf.format(sevenDays));
		osVO.setDateEnd(sdf.format(today));
		
		model.addAttribute("osVO", osVO);
		
		return "data/excel_search";
	}
	
	@RequestMapping(value="/data/excel_search.do", method=RequestMethod.POST)
	public String excelDataMatchingPost(HttpServletRequest request, @ModelAttribute OrderSearchVO osVO, Model model) {
		
		String fileName = "";
		String msg = "";
		String url ="";
		
		try {
			
			fileName = fileUploadUtil.fileupload(request, FileuploadUtil.ORDER_EXCEL);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("upload error! checking fileExtension or excel file");
			logger.info(e.getMessage());
			msg = "파일 업로드 실패. 로그를 확인해주세요.";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			
			return "common/message";
		}
		
		osVO = dc.readGiftSetExcelFile(fileName, osVO);
		
		List<OrdersVO> orderList = dataCheckService.checkBuyerByCntNum(osVO);
		
		model.addAttribute("osVO", osVO);
		model.addAttribute("orderList", orderList);
		
		
		return "data/excel_search";
	}
	
}
