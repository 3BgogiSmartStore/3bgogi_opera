package com.gogi.proj.producttax.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.cost.model.CostDetailService;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.producttax.model.ProductTaxService;
import com.gogi.proj.producttax.vo.ProductInfoListVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;
import com.gogi.proj.producttax.vo.ResCompanyVO;
import com.gogi.proj.producttax.vo.TaxTableVO;
import com.gogi.proj.producttax.vo.TransInfoVO;
import com.gogi.proj.util.FileuploadUtil;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping(value="/tax")
public class ProductTaxController {

	@Autowired
	private ProductTaxService ptService;
	
	@Autowired
	private FileuploadUtil fileUploadUtil;
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	@Autowired
	private CostDetailService costDetailService;
	
	/**
	 * 
	 * @MethodName : resCompanyGet
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @return
	 * @??????????????? : ????????? ?????? ????????? ????????????
	 */
	@RequestMapping(value="/res_company/insert.do", method=RequestMethod.GET)
	public String resCompanyGet() {
		
		return "tax/res_company/insert";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyUpdate
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param rcVOs
	 * @param model
	 * @return
	 * @??????????????? : ????????? ????????? ????????????
	 */
	@RequestMapping(value="/res_company/read.do", method=RequestMethod.GET)
	public String resCompanyUpdateGet(@ModelAttribute ResCompanyVO rcVOs, Model model) {
		
		ResCompanyVO rcVO = ptService.selectRecCompanyByRcPk(rcVOs);
		
		model.addAttribute("rcVO", rcVO);
		
		return "tax/res_company/read";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyUpdatePost
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @param model
	 * @return
	 * @??????????????? : ????????? ????????????
	 */
	@RequestMapping(value="/res_company/read.do", method=RequestMethod.POST)
	public String resCompanyUpdatePost(@ModelAttribute ResCompanyVO rcVO, Model model) {
		
		String msg = "";
		String url = "/tax/res_company/list.do";
		
		int result = ptService.updateResCompany(rcVO);
		
		if(result > 0) {
			msg = "????????? ?????? ??????";
			
		}else {
			msg = "????????? ?????? ??????";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyDelete
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @param model
	 * @return
	 * @??????????????? : ????????? ????????????
	 */
	@RequestMapping(value="/res_company/delete.do", method=RequestMethod.GET)
	public String resCompanyDelete(@ModelAttribute ResCompanyVO rcVO, Model model) {
		String msg = "";
		String url = "/tax/res_company/list.do";
		
		int result = ptService.deleteResCompany(rcVO);
		
		if(result > 0) {
			msg = "????????? ????????????";
			
		}else {
			msg = "????????? ?????? ??????";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyPost
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @param model
	 * @return
	 * @??????????????? : ????????? ????????? ????????????
	 */
	@RequestMapping(value="/res_company/insert.do", method=RequestMethod.POST)
	public String resCompanyPost(@ModelAttribute ResCompanyVO rcVO, Model model) {
		
		String msg = "";
		String url = "/tax/res_company/list.do";
		
		int result = ptService.insertResCompany(rcVO);
		
		if(result > 0) {
			msg = "????????? ????????? ?????? ??????";
			
		}else {
			msg = "????????? ?????? ??????, ????????? ??????????????????";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyList
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @??????????????? : ????????? ?????? ????????? ????????????
	 */
	@RequestMapping(value="/res_company/list.do", method=RequestMethod.GET)
	public String resCompanyList(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		List<ResCompanyVO> rcList = ptService.selectRecCompany(osVO);
		
		model.addAttribute("osVO", osVO);
		model.addAttribute("rcList", rcList);
		
		return "tax/res_company/list";
	}
	
	
	/**
	 * 
	 * @MethodName : insertProductInfoGet
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @??????????????? : ??????????????? ?????? ?????????
	 */
	@RequestMapping(value="/product_info/insert.do", method=RequestMethod.GET)
	public String insertProductInfoGet(Model model) {
		OrderSearchVO osVO = new OrderSearchVO();
		
		List<ResCompanyVO> rcList = ptService.selectRecCompany(osVO);
		
		List<CostDetailVO> costDetailList = costDetailService.selectAllCostDetailJoinCostCodeListInMeat();
		
		model.addAttribute("rcList", rcList);
		model.addAttribute("costDetailList", costDetailList);
		
		return "tax/product_info/insert";
	}
	
	
	/**
	 * 
	 * @MethodName : insertProductInfoPost
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param request
	 * @param piList
	 * @param model
	 * @return
	 * @??????????????? : ??????????????? ??????
	 */
	@RequestMapping(value="/product_info/insert.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	public String insertProductInfoPost(HttpServletRequest request, @ModelAttribute ProductInfoListVO piList, Model model) {
		String fileExe = "";
		String fileUniqName = "";
		String filePath = "";
		String fileOriName = "";
		
		String msg = "";
		String url = "/tax/product_info/insert.do";
		
		try {
			List<Map<String, Object>> fileInfo = fileUploadUtil.fileupload2(request, FileuploadUtil.TAX_FILE);
			
			fileExe=fileInfo.get(0).get("fileExtType")+"";
			fileUniqName=fileInfo.get(0).get("uniFileName")+"";
			filePath=fileInfo.get(0).get("filePath")+"";
			fileOriName=fileInfo.get(0).get("oriFileName")+"";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = "?????? ????????? ??????. ????????? ??????????????????.";
		}
		
		piList.getPiList();
		
		int result = 0;
		
		for(int i = 0; i < piList.getPiList().size(); i++) {
			
			if(piList.getPiList().get(i).getPiName() != null) {				
				piList.getPiList().get(i).setPiFileExe(fileExe);
				piList.getPiList().get(i).setPiFileOriName(fileOriName);
				piList.getPiList().get(i).setPiFilePath(filePath);
				piList.getPiList().get(i).setPiFileUniqName(fileUniqName);
				piList.getPiList().get(i).setPiInputDate(piList.getInputDates());
				piList.getPiList().get(i).setRcFk(piList.getResCompany());
				result = ptService.insertProductInfo(piList.getPiList().get(i));
				
			}
		}
		
		if(result > 0) {
			msg = "??????????????? ?????? ??????";
			
		}else {
			msg = "??????????????? ?????? ??????";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : selectProductInfoGet
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @??????????????? : ??????????????? ?????? ????????????
	 */
	@RequestMapping(value="/product_info/list.do", method=RequestMethod.GET)
	public String selectProductInfoGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
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
		
		int totalRecord = ptService.selectProductInfoListCounting(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<ProductInfoVO> piList = ptService.selectProductInfoList(osVO);
		
		List<ResCompanyVO> rcList = ptService.selectRecCompany(osVO);
		
		List<CostDetailVO> costDetailList = costDetailService.selectAllCostDetailJoinCostCodeListInMeat();
		
		model.addAttribute("costDetailList", costDetailList);
		model.addAttribute("rcList", rcList);
		model.addAttribute("osVO", osVO);
		model.addAttribute("piList", piList);
		
		return "tax/product_info/list";
	}
	
	
	/**
	 * 
	 * @MethodName : productInfoFileDownload
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param request
	 * @param piVO
	 * @return
	 * @??????????????? : ??????????????? ?????? ????????????
	 */
	@RequestMapping(value="/product_info/file_down.do", method=RequestMethod.GET)
	public ModelAndView productInfoFileDownload(HttpServletRequest request, @ModelAttribute ProductInfoVO piVO) {
		
		File file = new File(piVO.getPiFilePath(), piVO.getPiFileUniqName());
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	
	/**
	 * 
	 * @MethodName : taxUpdate
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @??????????????? : ??????????????? ??????
	 */
	@RequestMapping(value="/product_info/tax_update.do", method=RequestMethod.GET)
	@ResponseBody
	public int taxUpdate(@ModelAttribute ProductInfoVO piVO) {
		
		return ptService.updateTaxbilFlag(piVO);
	}
	
	
	/**
	 * 
	 * @MethodName : accUpdate
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @??????????????? : ?????? ?????? ??????
	 */
	@RequestMapping(value="/product_info/acc_update.do", method=RequestMethod.GET)
	@ResponseBody
	public int accUpdate(@ModelAttribute ProductInfoVO piVO) {
	
		return ptService.updateAccFlag(piVO);
	}
	
	
	/**
	 * 
	 * @MethodName : deleteProductInfoByPiPk
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @param model
	 * @return
	 * @??????????????? : ??????????????? ????????????
	 */
	@RequestMapping(value="/product_info/delete.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	@ResponseBody
	public String deleteProductInfoByPiPk(@ModelAttribute ProductInfoVO piVO ){
		String msg = "";

		int result = ptService.deleteProductInfo(piVO);
		
		if(result > 0) {
			msg = "??????????????? ?????? ??????";
		}else {
			msg = "??????????????? ?????? ??????";
		}
		
		return msg;
	}
	
	
	/**
	 * 
	 * @MethodName : readProductInfoByPiPk
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @param model
	 * @return
	 * @??????????????? : ??????????????? ????????????
	 */
	@RequestMapping(value="/product_info/detail.do", method=RequestMethod.GET)
	public String readProductInfoByPiPk(@ModelAttribute ProductInfoVO piVO, Model model) {
		
		ProductInfoVO read = ptService.selectProductInfoByPiPk(piVO);
		
		List<CostDetailVO> costDetailList = costDetailService.selectAllCostDetailJoinCostCodeListInMeat();
		
		model.addAttribute("piVO", read);
		model.addAttribute("costDetailList", costDetailList);
		
		return "tax/product_info/read";
	}
	
	
	/**
	 * 
	 * @MethodName : updateProductInfo
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @param model
	 * @return
	 * @??????????????? : ??????????????? ????????????
	 */
	@RequestMapping(value="/product_info/update.do", method=RequestMethod.POST)
	public String updateProductInfo(HttpServletRequest request ,@ModelAttribute ProductInfoVO piVO, Model model) {
		
		String fileExe = "";
		String fileUniqName = "";
		String filePath = "";
		String fileOriName = "";

		
		try {
			List<Map<String, Object>> fileInfo = fileUploadUtil.fileupload2(request, FileuploadUtil.TAX_FILE);
			
			fileExe=fileInfo.get(0).get("fileExtType")+"";
			fileUniqName=fileInfo.get(0).get("uniFileName")+"";
			filePath=fileInfo.get(0).get("filePath")+"";
			fileOriName=fileInfo.get(0).get("oriFileName")+"";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
		if(fileExe != null && !fileExe.equals("")) {
			piVO.setPiFileExe(fileExe);
			piVO.setPiFileOriName(fileOriName);
			piVO.setPiFilePath(filePath);
			piVO.setPiFileUniqName(fileUniqName);
		}
		
		String msg = "";
		String url = "/tax/product_info/detail.do?piPk="+piVO.getPiPk();
		
		int result = ptService.updateProductInfo(piVO);
		
		if(result > 0) {
			msg = "?????? ??????";
		}else {
			msg = "?????? ??????";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/delete_tax_table.do", method=RequestMethod.GET)
	public String taxTableDelete(Model model) {
		String msg = "";
		String url = "/tax/tax_table.do";
		
		int result = ptService.deleteTaxTable();
		
		if(result > 0) {
			msg = "?????? ??????";
		}else {
			msg = "?????? ??????";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/tax_table.do", method=RequestMethod.GET)
	public String taxTablePost(Model model) {
		
		List<TaxTableVO> taxList = ptService.taxZero();
		List<TaxTableVO> dutyList = ptService.dutyZero();
		List<TaxTableVO> totalCount = ptService.taxTableCount();
		
		model.addAttribute("taxList", taxList);
		model.addAttribute("dutyList", dutyList);
		model.addAttribute("totalCount", totalCount);
		
		return "tax/tax_table";
	}
	
	@RequestMapping(value="/tax_table.do", method=RequestMethod.POST)
	public String taxTablePost(HttpServletRequest request, Model model) {
		
		
		String fileName = "";
		
		try {
			
			fileName = fileUploadUtil.fileupload(request, FileuploadUtil.ORDER_EXCEL);
			
		} catch (Exception e) {
			
		}
		
		List<TaxTableVO> taxList = new ArrayList<TaxTableVO>();
		
		String upPath = fileUploadUtil.getUploadPath(FileuploadUtil.ORDER_EXCEL, false);
		
		try {
	
			FileInputStream fis= new FileInputStream(upPath+"\\"+fileName);
			
			// ???????????? ???????????? xlsx ??? ??????
			// ???????????? ???????????? ?????????
					XSSFWorkbook workbook=new XSSFWorkbook(fis);
					
					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
					
					int rowStartIndex = 1;
					
					int columnindex=0;
					
					XSSFSheet sheet=workbook.getSheetAt(0);
					
					int rows=sheet.getPhysicalNumberOfRows();
					
					for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
						
						XSSFRow row=sheet.getRow(rowindex);
						
						TaxTableVO ttVO = null;
						
						if(row !=null){
							ttVO = new TaxTableVO();
							
							for(columnindex=0;columnindex<14;columnindex++){
								
								XSSFCell cell=row.getCell(columnindex);
								
								if(columnindex==0) {
									if(cell == null) {
										break;
									}else {
										continue;
									}
										
								}if(columnindex==1) {
									ttVO.setTtDate(cell.getStringCellValue());
									
								}if(columnindex==2) {
									
									ttVO.setTtOrderNumber(cell.getStringCellValue());
									
								}if(columnindex==3) {
									
									if(cell == null) {
										ttVO.setTtProductOrderNumber("-");
									}else {										
										ttVO.setTtProductOrderNumber(cell.getStringCellValue());
									}
										
								}if(columnindex==4) {
									
									ttVO.setTtTaxType(cell.getStringCellValue());
										
								}if(columnindex==5) {
									ttVO.setTtProduct(cell.getStringCellValue());
										
								}if(columnindex==6) {
									ttVO.setTtTaxStat(cell.getStringCellValue());
										
								}if(columnindex==7) {
									ttVO.setTtTotalPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==8) {
									ttVO.setTtTaxPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==9) {
									ttVO.setTtDutyFreePrice((int)cell.getNumericCellValue());
									
								}if(columnindex==10) {
									ttVO.setTtCreditPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==11) {
									ttVO.setTtCashDeductionPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==12) {
									ttVO.setTtCashReceiptPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==13) {
									ttVO.setTtAnotherPrice((int)cell.getNumericCellValue());
									
									taxList.add(ttVO);
									//ptService.insertTaxTableData(ttVO);
								}
								
								
							}//for
						}
						
						
						
					}//for
				
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		int [] result = ptService.insertTaxTableDataBatch(taxList);
		
		System.out.println("?????? ??????  = "+result[0]+" , ????????? = "+result[1]+" , ??? ??? = "+result[2]);
		
		String msg = "????????????";
		String url = "/tax/tax_table.do";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
	public String cellTypeReturn(XSSFCell cell) {
		
		String value = "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 switch (cell.getCellType()){
           case HSSFCell.CELL_TYPE_FORMULA:
               value=cell.getCellFormula()+"";
               break;
               
           case HSSFCell.CELL_TYPE_NUMERIC:
	           	if(HSSFDateUtil.isCellDateFormatted(cell)) {
	           		value=dateFormat.format(cell.getDateCellValue());
	           		
	           	}else {		                            		
	           		value=cell.getNumericCellValue()+"";
	           	}
	           	
               break;
           case HSSFCell.CELL_TYPE_STRING:
               value=cell.getStringCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_BLANK:
               value=cell.getBooleanCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_ERROR:
               value=cell.getErrorCellValue()+"";
               break;
               
           default: 
        	   value=cell.getStringCellValue()+"";
           	break;
           }
		 
		return value;
	}
	
	@RequestMapping(value="/trans_info/insert.do", method=RequestMethod.GET)
	public String insertTransInfoGet(Model model) {
		
		return "tax/trans_info/insert";
	}
	
	@RequestMapping(value="/trans_info/insert.do", method=RequestMethod.POST)
	public String insertTransInfoPost(@ModelAttribute TransInfoVO tiVO, Model model) {
		String msg = "";
		String url = "/tax/trans_info/list.do";
		
		int result = ptService.insertTransInfo(tiVO);
		
		if( result > 0) {
			msg = "??????(??????)????????? ?????? ??????";
			
		}else {
			msg = "??????????????? ?????? ??????";
			url = "/tax/trans_info/insert.do";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/trans_info/update.do", method=RequestMethod.POST)
	public String updateTransInfoPost(@ModelAttribute TransInfoVO tiVO, Model model) {
		String msg = "";
		String url = "/tax/trans_info/list.do";
		
		int result = ptService.updateTransInfo(tiVO);
		
		if( result > 0) {
			msg = "??????(??????)????????? ?????? ??????";
			
		}else {
			msg = "??????????????? ?????? ??????";
			url = "/tax/trans_info/read.do?tiPk="+tiVO.getTiPk();
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/trans_info/read.do", method=RequestMethod.GET)
	public String readTransInfo(@ModelAttribute TransInfoVO tiVo, Model model) {
		
		TransInfoVO tiVO = ptService.selectTransInfoByPk(tiVo);
		
		model.addAttribute("tiVO", tiVO);
		
		return "tax/trans_info/read";
	}
	
	@RequestMapping(value="/trans_info/list.do", method=RequestMethod.GET)
	public String transInfoList(@ModelAttribute OrderSearchVO osVO, Model model) {

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
		
		int totalRecord = ptService.selectAllTransInfoCounting(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<TransInfoVO> tiList = ptService.selectAllTransInfoList(osVO);
		
		model.addAttribute("tiList", tiList);
		model.addAttribute("osVO", osVO);
		
		return "tax/trans_info/list";
	}
}
