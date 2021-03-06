package com.gogi.proj.stock.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.model.CostDetailService;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.cost_io.model.CostIoService;
import com.gogi.proj.product.costs.model.CostsService;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.model.StockService;
import com.gogi.proj.stock.vo.CarcassInputListVO;
import com.gogi.proj.stock.vo.ProductInputListVO;
import com.gogi.proj.util.FileuploadUtil;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping(value="/stock")
public class StockController {

	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	private StockService stockService;
	
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private CostDetailService cdService;
	
	@Autowired
	private CostIoService ciService;
	
	@Autowired
	private AllClassificationCodeService accService;
	
	@Autowired
	private FileuploadUtil fileUploadUtil;
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	/**
	 * 
	 * @MethodName : stockCheck
	 * @date : 2020. 5. 6.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ?????? ?????? 
	 */
	@RequestMapping(value="/stk_check.do" , method=RequestMethod.GET )
	public String stockCheck(HttpServletRequest request,OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -30);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVo = (AdminVO)auth.getPrincipal();
		
		stockService.stockChecking(osVO, request.getRemoteAddr(), adminVo.getUsername());
		
		// ?????? ????????? ?????? ????????? ?????? ?????? ????????????
		/*int[] result = stockService.stockSearchList(osVO);*/
		
		List<Map<String, String>> searchResult = stockService.selectStockResult(osVO);
		
		//?????? ?????? ????????????
		//????????? ??????
		List<StoreSectionVO> storeSectionList = configService.selectStoreSectionList();
		
		model.addAttribute("searchResult", searchResult);
		model.addAttribute("osVO", osVO);
		model.addAttribute("storeSectionList", storeSectionList);
		
		model.addAttribute("order_process", 4);
		
		return "orders/stock/stk_check";
		
	}
	
	

	/** 
	 * 
	 * @MethodName : searchOutputOrder
	 * @date : 2020. 5. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @??????????????? : ?????? ??????????????? 
	 * 
	 * 		???????????? ?????? : searchType = outputPosiv
	 * 		??????????????? : searchType = outputPosivNotRelease
	 * 		???????????? : searchType = outputReserve
	 * 		?????? ????????? ?????? : searchType = outputPosiv
	 * 
	 * 		????????? ?????? ?????? ?????? ????????? ?????? ????????? ?????????
	 */
	@RequestMapping(value="/stock_search_result.do", method=RequestMethod.GET)
	public String searchOutputOrder(OrderSearchVO osVO, Model model) {
		osVO.setDateEnd(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		if(osVO.getDateStart() == null && osVO.getDateStart().equals("")) {
			String msg = "????????? ???????????????";
			String url = "/stock/stk_check.do";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
			
		}
		
		List<OrdersVO> stockList = stockService.searchOutputListByOutputType(osVO);
		
		model.addAttribute("stockList", stockList);
		model.addAttribute("osVO", osVO);
		
		return "orders/stock/stk_search_result";
	}
	
	
	/**
	 * 
	 * @MethodName : stockManageGet
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @??????????????? : ?????? ?????? ????????? ???????????? 
	 */
	@RequestMapping(value="/manage.do", method=RequestMethod.GET)
	public String stockManageGet(Model model) {
		
		return "orders/stock/manage/add_stock";
	}
	
	
	/**
	 * 
	 * @MethodName : selectOptionStockByNameOrBarcodeNumAjax
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @??????????????? : ajax??? ????????? ?????? ?????????????????? ???????????? ?????? ?????? ????????????
	 */
	@RequestMapping(value="/search_option_stocks.do", method=RequestMethod.POST)
	@ResponseBody
	public List<ProductOptionVO> selectOptionStockByNameOrBarcodeNumAjax(@ModelAttribute OrderSearchVO osVO){
		
		return stockService.selectOptionStockByNameOrBarcodeNum(osVO);
	}
	
	
	/**
	 * 
	 * @MethodName : optionStockAddResult
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param pilVO
	 * @return
	 * @??????????????? : ?????? ?????? ?????? ?????????
	 */
	@RequestMapping(value="/add_option_stocks.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	public String optionStockAddResult(HttpServletRequest request, @ModelAttribute ProductInputListVO pilVO) {
		String msg ="";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVO = (AdminVO)auth.getPrincipal();
		
		try {
			
			List<Map<String, Object>> fileInfo = fileUploadUtil.fileupload2(request, FileuploadUtil.STOCK_STATEMENT_IMG);

			pilVO.setPilFileExe(fileInfo.get(0).get("fileExtType")+"");
			pilVO.setPilFileName(fileInfo.get(0).get("uniFileName")+"");
			pilVO.setPilFilePath(fileInfo.get(0).get("filePath")+"");
			pilVO.setPilFileOriName(fileInfo.get(0).get("oriFileName")+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("upload error! checking fileExtension or excel file");
			logger.info(e.getMessage());
			msg = "?????? ????????? ??????. ????????? ??????????????????.";
		}
		
		int result = stockService.insertProductInputList(adminVO, pilVO);
		
		if(result == 2) {
			msg = "?????? ?????? ?????? ??????";
			
		}else if(result == 1) {
			msg = "?????? ?????? ?????? ?????? ??????";
			
		}else {
			msg = "?????? ?????? ?????? ?????? ";
		}
		
		return msg;
	}
	
	
	/**
	 * 
	 * @MethodName : productStockAddReqList
	 * @date : 2020. 7. 20.
	 * @author : Jeon KiChan
	 * @param paging
	 * @param model
	 * @return
	 * @??????????????? : ?????? ?????? ?????? ????????????
	 */
	@RequestMapping(value="/stock_req_list.do", method=RequestMethod.GET)
	public String productStockAddReqList(@ModelAttribute PaginationInfo paging, Model model) {

		if(paging.getSearchMinDate() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			paging.setSearchMinDate(sdf.format(sevenDays));
			paging.setSearchMaxDate(sdf.format(today));
			
		}
		
		int totalRecord = stockService.selectProductInputListsCount(paging);
		
		paging.setTotalRecord(totalRecord);
		
		if(paging.getBlockSize() == 0) {			
			paging.setBlockSize(10);
		}
		
		if(paging.getRecordCountPerPage() == 0) {			
			paging.setRecordCountPerPage(10);
			
		}
		
		if(totalRecord <= paging.getRecordCountPerPage()) {
			paging.setCurrentPage(1);
		}
		
		System.out.println(paging.tests());
		
		List<ProductInputListVO> pilList = stockService.selectProductInputLists(paging);
		
		model.addAttribute("pilList", pilList);
		model.addAttribute("PaginationInfo", paging);
		
		return "orders/stock/manage/stock_add_req_list";
	}
	
	
	/**
	 * 
	 * @MethodName : productStockPerm
	 * @date : 2020. 7. 20.
	 * @author : Jeon KiChan
	 * @param pilVO
	 * @param paging
	 * @param model
	 * @return
	 * @??????????????? : ?????? ?????? ?????? ????????????
	 */
	@RequestMapping(value="/stock_add_res.do", method=RequestMethod.POST)
	public String productStockPerm( @ModelAttribute ProductInputListVO pilVO, @ModelAttribute PaginationInfo paging, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVO = (AdminVO)auth.getPrincipal();
		
		int result = stockService.updateProductInputList(adminVO, pilVO);
		
		if(result > 0) {
			logger.info("?????? ???????????? ??????");
		}else {
			logger.info("?????? ???????????? ??????");
		}
		
		model.addAttribute("PaginationInfo", paging);
		
		return "redirect:/stock/stock_req_list.do";
	}
	
	
	
	/**
	 * 
	 * @MethodName : stockStatementFileDownload
	 * @date : 2020. 7. 28.
	 * @author : Jeon KiChan
	 * @param request
	 * @param pilVO
	 * @return
	 * @??????????????? : ?????? ?????? ???????????? ??????
	 */
	@RequestMapping(value="/option_stocks_file.do", method=RequestMethod.GET)
	public ModelAndView stockStatementFileDownload(HttpServletRequest request, @ModelAttribute ProductInputListVO pilVO) {
		
		String filePath = fileUploadUtil.getUploadPath(request, FileuploadUtil.STOCK_STATEMENT_IMG);
		
		 File file = new File(filePath, pilVO.getPilFileName());
		 
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	
	/**
	 * 
	 * @MethodName : carcassListGet
	 * @date : 2020. 8. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @??????????????? : ?????? ?????? ?????? ????????? 
	 */
	@RequestMapping(value="/carcass/list.do", method=RequestMethod.GET)
	public String carcassListGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}
		
		int totalRecord = cdService.selectCarcassInputListCount(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<CarcassInputListVO> cilList = cdService.selectCarcassInputList(osVO);
		
		
		model.addAttribute("cilList", cilList);
		model.addAttribute("osVO", osVO);
		
		return "orders/stock/carcass/list";
	}
	
	
	/**
	 * 
	 * @MethodName : cilFileDownload
	 * @date : 2020. 8. 27.
	 * @author : Jeon KiChan
	 * @param request
	 * @param cilVO
	 * @return
	 * @??????????????? : 
	 */
	@RequestMapping(value="/cil_file_download.do", method=RequestMethod.GET)
	public ModelAndView cilFileDownload(HttpServletRequest request, @ModelAttribute CarcassInputListVO cilVO) {
		
		File file = new File(cilVO.getCilFilePath(), cilVO.getCilFileUniqName());
		 
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	/**
	 * 
	 * @MethodName : insertCarcassGet
	 * @date : 2020. 8. 27.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @??????????????? : ?????? ?????? ????????? ????????????
	 */
	@RequestMapping(value="/carcass/insert.do", method=RequestMethod.GET)
	public String insertCarcassGet(Model model) {
		
		List<CostCodeVO> ccList = accService.selectCostCodeList();
		
		model.addAttribute("ccList", ccList);
		
		return "orders/stock/carcass/insert";
	}
	
	/**
	 * 
	 * @MethodName : carcassDetail
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param cilVO
	 * @param model
	 * @return
	 * @??????????????? : ?????? ???????????? ????????????
	 */
	@RequestMapping(value="/carcass/read.do", method=RequestMethod.GET)
	public String carcassDetail(@ModelAttribute CarcassInputListVO cilVO, Model model) {
		
		CarcassInputListVO cilList = cdService.selectCarcassInputListDetail(cilVO);
		
		model.addAttribute("cilList", cilList);
		
		return "orders/stock/carcass/read";
	}
	
	/**
	 * 
	 * @MethodName : selectCostDetail
	 * @date : 2020. 8. 27.
	 * @author : Jeon KiChan
	 * @param ccVO
	 * @return
	 * @??????????????? : ?????? ????????????????????? ??????, ????????? ????????? ?????? ?????? ???????????? ajax
	 */
	@RequestMapping(value="/carcass/select_cost_detail.do", method=RequestMethod.GET)
	@ResponseBody
	public List<CostDetailVO> selectCostDetail(@ModelAttribute CostCodeVO ccVO){
	
		return cdService.selectCostdetailWightCostcodeByCcPk(ccVO);
	}
	
	
	/**
	 * 
	 * @MethodName : insertCarcassPost
	 * @date : 2020. 8. 27.
	 * @author : Jeon KiChan
	 * @param request
	 * @param cilVO
	 * @param model
	 * @return
	 * @??????????????? : ?????? ????????????
	 */
	@RequestMapping(value="/carcass/insert.do", method=RequestMethod.POST)
	public String insertCarcassPost(HttpServletRequest request, @ModelAttribute CarcassInputListVO cilVO, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVO = (AdminVO)auth.getPrincipal();

		int result = 0;
		
		String msg = "?????? ?????? ??????";
		String url = "/stock/carcass/list.do";
		
		try {
			
			List<Map<String, Object>> fileList = fileUploadUtil.multiFileupload(request, FileuploadUtil.CARCASS_FILE);
			
			for(int i = 0; i < fileList.size(); i++) {
				
				if(i == 0) {
					cilVO.setCilFileOriName(fileList.get(i).get("oriFileName")+"");
					cilVO.setCilFileUniqName(fileList.get(i).get("uniqFileName")+"");
					cilVO.setCilFileExe(fileList.get(i).get("fileExtType")+"");
					cilVO.setCilFilePath(fileList.get(i).get("filePath")+"");
				}else {
					cilVO.setCilTransDetailFileOriName(fileList.get(i).get("oriFileName")+"");
					cilVO.setCilTransDetailFileUniqName(fileList.get(i).get("uniqFileName")+"");
					cilVO.setCilTransDetailFileExe(fileList.get(i).get("fileExtType")+"");
					cilVO.setCilTransDetailFilePath(fileList.get(i).get("filePath")+"");
				}
			}
			
			result = cdService.insertCarcassAndCostIo(adminVO, cilVO);
			
			if(result > 0 ) msg = "?????? ?????? ?????? ";
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("?????? ????????? ??????", e);
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : updateCarcass
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param cilVO
	 * @param model
	 * @return
	 * @??????????????? : ?????? ????????????
	 */
	@RequestMapping(value="/carcass/update.do", method=RequestMethod.POST)
	public String updateCarcass(@ModelAttribute CarcassInputListVO cilVO, Model model) {
		
		String msg = "";
		String url = "/stock/carcass/read.do?cilPk="+cilVO.getCilPk();
		
		int result = cdService.updateCarcassInputList(cilVO);
		
		if(result > 0) {
			msg = "?????? ?????? ??????";
			
		}else {
			msg = "?????? ?????? ??????";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : deleteCarcass
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param cilVO
	 * @param model
	 * @return
	 * @??????????????? : ?????? ????????????
	 */
	@RequestMapping(value="/carcass/delete.do", method=RequestMethod.POST)
	public String deleteCarcass(@ModelAttribute CarcassInputListVO cilVO, Model model) {
		
		String msg = "";
		String url = "";
		
		int result = cdService.deleteCarcassInputList(cilVO);
		
		if(result > 0) {
			msg = "?????? ?????? ??????";
			url = "/stock/carcass/list.do";
		}else {
			msg = "?????? ??????";
			url = "/stock/carcass/read.do?cilPk="+cilVO.getCilPk();
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : carcassCutListGet
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @??????????????? : ????????? ?????? ?????? ????????????
	 */
	@RequestMapping(value="/carcass/carcass_cut/list.do", method=RequestMethod.GET)
	public String carcassCutListGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}
		int totalRecord = cdService.selectCostIoHistoryCounting(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		
		List<CostIoVO> ciList = cdService.selectCostIoHistory(osVO);
		
		model.addAttribute("ciList", ciList);
		model.addAttribute("osVO", osVO);
		
		return "orders/stock/carcass/carcass_cut/list";
	}
	
	
	/**
	 * 
	 * @MethodName : carcassCutRead
	 * @date : 2020. 10. 15.
	 * @author : Jeon KiChan
	 * @param ciVO
	 * @param model
	 * @return
	 * @??????????????? : ????????? ?????? ?????? ?????? ??????
	 */
	@RequestMapping(value="/carcass/carcass_cut/read.do", method=RequestMethod.GET)
	public String carcassCutRead(@ModelAttribute CostIoVO ciVO, Model model) {
		
		CostIoVO result = ciService.selectCostIoData(ciVO);
		
		model.addAttribute("ciVO", result);
		
		return "orders/stock/carcass/carcass_cut/read";
	}
	
	
	/**
	 * 
	 * @MethodName : carcassCutInsertGet
	 * @date : 2020. 10. 16.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @??????????????? : ????????? ?????? ?????????
	 */
	@RequestMapping(value="/carcass/carcass_cut/insert.do", method=RequestMethod.GET)
	public String carcassCutInsertGet(Model model) {
		List<CostDetailVO> cdList = ciService.selectCostDetailCode();
		
		model.addAttribute("cdList", cdList);
		
		return "orders/stock/carcass/carcass_cut/insert";
	}
	
	
	/**
	 * 
	 * @MethodName : caracssCutInsertPost
	 * @date : 2020. 10. 16.
	 * @author : Jeon KiChan
	 * @param ciVO
	 * @param model
	 * @return
	 * @??????????????? : ????????? ?????? ????????????
	 */
	@RequestMapping(value="/carcass/carcass_cut_insert.do", method=RequestMethod.POST)
	public String caracssCutInsertPost(@ModelAttribute CostIoVO ciVO, Model model) {
		
		String msg = "";
		String url = "/stock/carcass/carcass_cut/list.do";
		
		int result = ciService.insertCostInputData(ciVO);
		
		if(result > 0 ) {
			msg = "????????? ?????? ??????";
		}else {
			msg = "?????? ??????";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : checkOptionBarcodeDupli
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ????????? ?????? ?????? ????????? ????????????
	 */
	@RequestMapping(value="/check_barcode_dupli.do", method=RequestMethod.GET)
	public String checkOptionBarcodeDupli() {
		
		return "orders/stock/manage/check_barcode_num";
	}
	
	
	/**
	 * 
	 * @MethodName : checkOptionBarcodeDupliAjax
	 * @date : 2020. 10. 19.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @??????????????? : ????????? ????????? ????????????
	 */
	@RequestMapping(value="/check_barcode_dupli.do", method=RequestMethod.POST)
	@ResponseBody
	public List<ProductOptionVO> checkOptionBarcodeDupliAjax(@ModelAttribute OrderSearchVO osVO){
		
		return stockService.checkOptionBarcodeDupli(osVO);
	}
	
	
	/**
	 * 
	 * @MethodName : carcarssManageGet
	 * @date : 2020. 10. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @??????????????? : ?????? ????????? ???????????????
	 */
	@RequestMapping(value="/carcass_manage.do", method=RequestMethod.GET)
	public String carcarssManageGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		osVO.setBlockSize(10);
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<CostDetailVO> cdList = cdService.selectCarcassCostManage(osVO);
		List<CostDetailVO> categoryList = cdService.selsectCarcassCostCategoryCounting(osVO);
		
		model.addAttribute("osVO", osVO);
		model.addAttribute("cdList", cdList);
		model.addAttribute("categoryList", categoryList);
		
		return "orders/stock/carcass/manage/list";
	}
	
	
	/**
	 * 
	 * @MethodName : carcarssManageGetAjax
	 * @date : 2020. 10. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @??????????????? : ?????? ????????? ????????????
	 */
	@RequestMapping(value="/carcass_manage_ajax.do", method=RequestMethod.POST)
	@ResponseBody
	public OrderSearchVO carcarssManageGetAjax(@ModelAttribute OrderSearchVO osVO){
		
		List<CostDetailVO> cdList = cdService.selectCarcassCostManage(osVO);
		
		osVO.setCdList(cdList);
		
		return osVO;
	}
	
	/**
	 * 
	 * @MethodName : chooseCostIoAjax
	 * @date : 2020. 10. 27.
	 * @author : Jeon KiChan
	 * @param ciVO
	 * @return
	 * @??????????????? : ??????????????? ?????? ??????
	 */
	@RequestMapping(value="/choose_cost_io_ajax.do", method=RequestMethod.GET)
	@ResponseBody
	public int chooseCostIoAjax(@ModelAttribute CostIoVO ciVO) {
		int result = ciService.chooseCostIo(ciVO);

		return result;
	}
}
