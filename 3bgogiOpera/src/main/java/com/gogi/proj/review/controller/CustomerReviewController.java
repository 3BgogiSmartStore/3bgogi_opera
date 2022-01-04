package com.gogi.proj.review.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogi.proj.review.model.CustomerReviewService;
import com.gogi.proj.review.util.CustomerReviewExcel;
import com.gogi.proj.review.vo.CustomerReviewVO;
import com.gogi.proj.util.FileuploadUtil;

@Controller
public class CustomerReviewController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerReviewController.class);
	
	@Autowired
	private CustomerReviewService customerReviewService;
	
	@Autowired
	private FileuploadUtil fileUploadUtil;
	
	@Autowired
	private CustomerReviewExcel customerReviewExcel;
	
	/**
	 * 
	 * @MethodName : insertCustomerReviewGet
	 * @date : 2021. 12. 31.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 사용자 리뷰(스마트스토어) 엑셀 파일 넣는 페이지
	 */
	@RequestMapping(value="/customer_review/insert.do", method=RequestMethod.GET)
	public String insertCustomerReviewGet() {
		
		return "customer/review/insert";
	}
	
	
	/**
	 * 
	 * @MethodName : insertCustomerReviewPost
	 * @date : 2022. 1. 3.
	 * @author : Jeon KiChan
	 * @param request
	 * @param model
	 * @return
	 * @메소드설명 : 사용자 리뷰(스마트스토어) 엑셀 파일 넣기
	 */
	@RequestMapping(value="/customer_review/insert.do", method=RequestMethod.POST)
	public String insertCustomerReviewPost(HttpServletRequest request, Model model) {
	
		String msg = "";
		String url = "";
		
		String fileName = "";
	
		try {
			
			fileName = fileUploadUtil.fileupload(request, FileuploadUtil.ORDER_EXCEL);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("upload error! checking fileExtension or excel file");
			logger.info(e.getMessage());
			msg = "파일 업로드 실패. 에러 로그를 확인해주세요.";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url",url);
			
			return "common/message";
		}
		
		List<CustomerReviewVO> reviewList = customerReviewExcel.readCustomerReviewExcelList(fileName);
		
		int insertResult = customerReviewService.insertCustomerReview(reviewList);
		
		msg = "사용자 리뷰 "+insertResult+" 건 입력완료";
		url = "/customer_review/insert.do";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	
}
