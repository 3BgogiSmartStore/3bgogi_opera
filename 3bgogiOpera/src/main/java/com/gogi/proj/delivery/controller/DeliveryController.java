package com.gogi.proj.delivery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.config.vo.DoorPassKeywordVO;
import com.gogi.proj.delivery.model.DeliveryService;
import com.gogi.proj.delivery.vo.SendingRequestVO;
import com.gogi.proj.excel.CellsStyle;
import com.gogi.proj.excel.xlsxWriter;
import com.gogi.proj.orders.autocomplete.Godomall;
import com.gogi.proj.orders.coupang.util.CoupangConnectUtil;
import com.gogi.proj.orders.coupang.vo.CoupangResponseDTO;
import com.gogi.proj.orders.model.OrdersService;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.security.AdminVO;
import com.healthmarketscience.jackcess.impl.ByteUtil.ByteStream;

@Controller
@RequestMapping(value="/delivery")
public class DeliveryController{

	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	
	@Autowired
	private DeliveryService deliService;
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private OrdersService ordersService;
	
	private CellsStyle cs = new CellsStyle();
	
	@Autowired
	private CoupangConnectUtil coupangConnectUtil;
	
	@Autowired
	private Godomall gm;
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	/**
	 * 
	 * @MethodName : sendingProductPageGet
	 * @date : 2019. 12. 20.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ?????? ??????(???????????????) ????????? 
	 */
	@RequestMapping(value="/sending.do", method=RequestMethod.GET)
	public String sendingProductPageGet() {
		
		return "delivery/sending";
	}
	
	/**
	 * 
	 * @MethodName : sendingProductPageGet
	 * @date : 2019. 12. 20.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ?????? ??????(???????????????) ????????? 
	 */
	@RequestMapping(value="/sending_div.do", method=RequestMethod.GET)
	public String sendingProductPageGetDiv() {
		
		return "delivery/sending_div";
	}
	
	
	/**
	 * 
	 * @MethodName : selectDelivTargetByOrDeliveryInvoiceNumber
	 * @date : 2019. 12. 20.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @??????????????? : ?????????????????? ????????? ?????? ?????? ????????????
	 */
	@RequestMapping(value="/result.do", method=RequestMethod.POST)
	@ResponseBody
	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(@ModelAttribute OrdersVO orVO){
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("-", ""));

		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "Q"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "W"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "E"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "R"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "T"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "Y"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "U"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "I"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "O"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "P"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "A"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "S"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "D"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "F"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "G"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "H"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "J"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "K"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "L"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "Z"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "X"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "C"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "V"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "B"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "N"));
		orVO.setOrDeliveryInvoiceNumber(orVO.getOrDeliveryInvoiceNumber().replaceAll("???", "M"));
		
		logger.info("orDeliveryInvoiceNumber = {}",orVO.getOrDeliveryInvoiceNumber());		
		return deliService.selectDelivTargetByOrDeliveryInvoiceNumber(orVO);
	}
	
	
	/**
	 * 
	 * @MethodName : updateOrderSendingDay
	 * @date : 2019. 12. 20.
	 * @author : Jeon KiChan
	 * @param orPk
	 * @return
	 * @??????????????? : ??? ?????? ?????? ??????????????????
	 */
	@RequestMapping(value="/sending_result.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean updateOrderSendingDay(HttpServletRequest request, @RequestParam List<String> orPk) {
		boolean result;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		int updates = deliService.updateOrderSendingDay(orPk, request.getRemoteAddr(), adminVo.getUsername());
		
		if(updates == orPk.size()) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @MethodName : storeOrderSendingGet
	 * @date : 2020. 3. 19.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ???????????? ?????? ?????? ??? ?????? ????????? ????????????
	 */
	@RequestMapping(value="/store_order_sending.do", method=RequestMethod.GET)
	public String storeOrderSendingGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null) {
			
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(today));
			
		}
		
		List<OrdersVO> storeSendingResult = deliService.selectStoreSendingResultByDate(osVO);
		
		model.addAttribute("storeSendingResult", storeSendingResult);
		model.addAttribute("osVO",osVO);
		
		return "delivery/store_order_sending";
	}
	
	
	/**
	 * 
	 * @MethodName : storeSending
	 * @date : 2020. 6. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @??????????????? : ????????? ???????????? ?????? 
	 */
	@RequestMapping(value="/store_sending.do", method=RequestMethod.GET)
	public String storeSending(@ModelAttribute OrderSearchVO osVO) {
		
		if(osVO.getDateStart() == null) {
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat detailSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			osVO.setDateStart(sdf.format(today));
			osVO.setDateEnd(detailSdf.format(sevenDays));
			
		}

		deliService.storeSendingFinished(osVO);
		
		return "redirect: /delivery/store_order_sending.do";
	}
	
	/**
	 * 
	 * @MethodName : godomallAutoSending
	 * @date : 2021. 1. 12.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @??????????????? : ????????? ?????? ??????????????????
	 */
	@RequestMapping(value="/godomall_sending.do", method=RequestMethod.GET)
	public String godomallAutoSending(@ModelAttribute StoreSectionVO ssVO) {

		List<OrdersVO> orList = deliService.godomallAutoSendingTarget(ssVO);
		
		String result = gm.godomallAutoSend(orList);
		
		logger.info(result);
		return "redirect: /delivery/store_order_sending.do";
	}
	
	
	/**
	 * 
	 * @MethodName : coupangAutoSending
	 * @date : 2022. 3. 2.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @return
	 * @??????????????? : ?????? ?????? ??????????????????
	 */
	@RequestMapping(value="/coupang_sending.do", method=RequestMethod.GET)
	public String coupangAutoSending(@ModelAttribute StoreSectionVO ssVO) {

		List<OrdersVO> orList = deliService.coupangAutoSendingTarget(ssVO);
		
		CoupangResponseDTO coupangResDTO = coupangConnectUtil.coupangOrderSending(orList);
		
		logger.info(coupangResDTO.toString());
		
		return "redirect: /delivery/store_order_sending.do";
	}
	
	
	
	/**
	 * 
	 * @MethodName : storeSendingCancled
	 * @date : 2020. 6. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @??????????????? : ???????????? ??? ??? ????????????
	 */
	@RequestMapping(value="/store_sending_cancled.do", method=RequestMethod.GET)
	public String storeSendingCancled(HttpServletRequest request ,@ModelAttribute OrderSearchVO osVO, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		String msg = "";
		String url = "/delivery/store_order_sending.do";
		
		int result = deliService.updateDeliveryOutPutCancled(osVO, request.getRemoteAddr(), adminVo.getUsername());
		
		if(result != 0 ) {
			msg = "?????? ?????? ??????";
			
		}else {
			msg = "?????? ?????? ??????";
			
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : sendingResultGet
	 * @date : 2020. 10. 29.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @param model
	 * @return
	 * @??????????????? : ???????????? ??????????????? ????????? ?????? ??? ????????????
	 */
	@RequestMapping(value="/sending/results.do", method=RequestMethod.GET)
	public String sendingResultGet(@ModelAttribute StoreSectionVO ssVO, Model model) {
		
		List<OrdersVO> sendingList = deliService.selectSendingResults(ssVO);
		
		model.addAttribute("sendingList", sendingList);
		
		return "delivery/sending_results";
	}
	
	/**
	 * 
	 * @MethodName : sendingExcelDownload
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVOParam
	 * @param model
	 * @return
	 * @throws IOException
	 * @??????????????? : ????????? ?????? ?????? ?????? ?????? ?????????
	 */
	@RequestMapping(value="/sending/excel_download.do", method=RequestMethod.GET)
	public ModelAndView sendingExcelDownload(@ModelAttribute StoreSectionVO ssVOParam, Model model) throws IOException {
		StoreSectionVO ssVO = configService.selectStoreSectionBySspk(ssVOParam.getSsPk());
		
		ssVOParam.setSsSendingBodyForm(ssVO.getSsSendingBodyForm());
		ssVOParam.setSsSendingGroupForm(ssVO.getSsSendingGroupForm());
		ssVOParam.setSsSendingHeadFormList(ssVO.getSsSendingHeadForm().split(","));
		if(ssVOParam.getSsPk() == 17) {
			
			List<Map<String, Object>> list = deliService.selectSendingExcelEmall(ssVOParam);
			// ????????? ??????
			SXSSFWorkbook workbook = new SXSSFWorkbook();
	        
	        // ???????????? ??????
	        SXSSFSheet sheet =(SXSSFSheet) workbook.createSheet();
	        
	        workbook.setSheetName(0, "????????????");
	        // ??? ??????
	        SXSSFRow row =(SXSSFRow) sheet.createRow(0);
	        row.setHeight((short)500);
	        // ??? ??????
	        SXSSFCell cell;
	        
	        String [] title = ssVOParam.getSsSendingHeadFormList();
	        // ?????? ?????? ??????
	        for(int i = 0; i < ssVOParam.getSsSendingHeadFormList().length; i++) {
	        	cell = (SXSSFCell)row.createCell(i);
	        	cell.setCellValue(title[i]);
	        	
	        }
	        
	        // ???????????? size ?????? row??? ??????
	        
	        int rowCounting = 0;
	        int cellCounting = 1;
	        
	       for(int i=0; i < list.size(); i++) {
	    	   Set<String> set = list.get(i).keySet();
	    	   Iterator<String> iter = set.iterator();
	    	   
	    	   
	    	   row = (SXSSFRow)sheet.createRow(cellCounting);
	    	   
	    	   while(iter.hasNext()) {
	    		   String key = (String)iter.next();
	    		   String value= (String)(list.get(i).get(key)+"");
	    		   
	    		   
		    		cell = (SXSSFCell)row.createCell(rowCounting);
			       	cell.setCellValue(value);
			       	rowCounting++;
	    	   }
	    	   
	    	   
	    	   rowCounting=0;
	    	   cellCounting++;
	       }

	        Date day = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String filedate = sdf.format(day);
	        String fileWrite = ssVO.getSsName()+" ?????? ?????? ??????"+filedate+".xlsx";	        
	        String filePath = fileProperties.getProperty("file.upload.store_sending_excel_file.path.test");
	    
		    
	        // ????????? ?????? ????????? ??????
	        File file = new File(filePath, fileWrite);
	        FileOutputStream fos = null;
	        
	        try {
	            fos = new FileOutputStream(file);
	            workbook.write(fos);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	            	
	                if(fos!=null) fos.close();
	                
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        
			Map<String, Object> fileMap = new HashMap<String, Object>();
			fileMap.put("myfile", file);
			ModelAndView mav = new ModelAndView("downloadView", fileMap);
			
			return mav;
		
		}else {
		List<Map<String, Object>> list = deliService.selectSendingExcel(ssVOParam);
		
		// ????????? ??????
		SXSSFWorkbook workbook = new SXSSFWorkbook();
        
        // ???????????? ??????
        SXSSFSheet sheet =(SXSSFSheet) workbook.createSheet();
        
        workbook.setSheetName(0, "????????????");
        // ??? ??????
        SXSSFRow row =(SXSSFRow) sheet.createRow(0);
        row.setHeight((short)500);
        // ??? ??????
        SXSSFCell cell;
        
        String [] title = ssVOParam.getSsSendingHeadFormList();
        // ?????? ?????? ??????
        for(int i = 0; i < ssVOParam.getSsSendingHeadFormList().length; i++) {
        	cell = (SXSSFCell)row.createCell(i);
        	cell.setCellValue(title[i]);
        	
        }
        
        // ???????????? size ?????? row??? ??????
        
        int rowCounting = 0;
        int cellCounting = 1;
        
       for(int i=0; i < list.size(); i++) {
    	   Set<String> set = list.get(i).keySet();
    	   Iterator<String> iter = set.iterator();
    	   
    	   
    	   row = (SXSSFRow)sheet.createRow(cellCounting);
    	   
    	   while(iter.hasNext()) {
    		   String key = (String)iter.next();
    		   String value= (String)(list.get(i).get(key)+"");
    		   
    		   
	    		cell = (SXSSFCell)row.createCell(rowCounting);
		       	cell.setCellValue(value);
		       	rowCounting++;
    	   }
    	   
    	   
    	   rowCounting=0;
    	   cellCounting++;
       }

        Date day = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String filedate = sdf.format(day);
        String fileWrite = ssVO.getSsName()+" ?????? ?????? ??????"+filedate+".xlsx";	        
        String filePath = fileProperties.getProperty("file.upload.store_sending_excel_file.path.test");
    
	    
        // ????????? ?????? ????????? ??????
        File file = new File(filePath, fileWrite);
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	
                if(fos!=null) fos.close();
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
}
	
	
	/**
	 * 
	 * @MethodName : selectSendingRequestNotChecked
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ???????????? ?????? ??? ????????????
	 */
	@RequestMapping(value="/sending_req.do", method=RequestMethod.GET)
	@ResponseBody
	public List<SendingRequestVO> selectSendingRequestNotChecked(){
		
		return deliService.selectSendingRequestNotChecked();
	}
	
	
	
	/**
	 * 
	 * @MethodName : insertSendingReq
	 * @date : 2020. 10. 21.
	 * @author : Jeon KiChan
	 * @param request
	 * @param srVO
	 * @return
	 * @??????????????? : ???????????? ?????? ( ?????? ?????? ?????????????????? ????????? )
	 */
	@RequestMapping(value="/sending_req.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean insertSendingReq(HttpServletRequest request, @ModelAttribute SendingRequestVO srVO) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		srVO.setSrAdminId(adminVo.getUsername());
		srVO.setSrAdminName(adminVo.getAdminname());
		
		int result = deliService.insertSendingRequest(srVO, request.getRemoteAddr());
		
		if(result > 0) {
			
			return true;
		}else {
			
			return false;
		}
		
	}
	
	
	/**
	 * 
	 * @MethodName : nonPickingCount
	 * @date : 2021. 2. 23.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ???????????? ?????? ?????? ??????
	 */
	@RequestMapping(value="/non_picking_count.do", method=RequestMethod.GET)
	@ResponseBody
	public int nonPickingCount() {
		
		return deliService.nonPickingCount();
		
	}
	
	
	/**
	 * 
	 * @MethodName : selectCjDeliveryResult
	 * @date : 2021. 10. 28.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @??????????????? : cj ????????? ???????????? ?????? ?????? ?????? ????????? ????????????
	 */
	@RequestMapping(value="/cj_config/select_invoice_num.do", method=RequestMethod.GET)
	public String selectCjDeliveryResult(Model model) {
		
		List<OrdersVO> createInvoiceNum = ordersService.selectCreateInvoiceNum();
		
		model.addAttribute("createInvoiceNum",createInvoiceNum);
		
		return "delivery/cj_config/select_invoice_num";
	}
	
	
	/**
	 * 
	 * @MethodName : cjDelivInvoiceNumResult
	 * @date : 2021. 10. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @??????????????? : ?????????????????? ?????? cj ?????? ?????? ?????? 
	 */
	@RequestMapping(value="/cj_config/select_invoice_num.do", method=RequestMethod.POST)
	public ModelAndView cjDelivInvoiceNumResult(@ModelAttribute OrderSearchVO osVO) {
		
		List<OrdersVO> orderList = deliService.selectCjDeliveryInvoiceNum(osVO);
		
		xlsxWriter xw = new xlsxWriter();
		
		File resultFile = xw.cjDelivInvoiceResultExcelFile(orderList);
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", resultFile);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
		
	}
}
