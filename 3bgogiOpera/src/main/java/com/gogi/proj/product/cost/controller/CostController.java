package com.gogi.proj.product.cost.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gogi.proj.classification.code.model.AllClassificationCodeService;
import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.orders.autocomplete.Godomall;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.model.CostDetailService;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.cost_io.model.CostIoService;
import com.gogi.proj.product.costs.model.CostsService;
import com.gogi.proj.util.PageUtility;
import com.gogi.proj.util.WebScraper;

@Controller
@RequestMapping("/products")
public class CostController {

	private static final Logger logger = LoggerFactory.getLogger(CostController.class);
	
	@Autowired
	private AllClassificationCodeService accService;
	
	@Autowired
	private CostDetailService costDetailService;
	
	@Autowired
	private CostsService costsService;
	
	@Autowired
	private CostIoService costIoService;
	
	
	/**
	 * @MethodName : insertCostDetailGet
	 * @date : 2019. 1. 23.
	 * @author : Jeon KiChan
	 * @메소드설명 : 데이터 관리/데이터입력/원가 상세사항 입력 페이지 들어가기
	 */
	@RequestMapping(value="/insert/cost_detail.do", method=RequestMethod.GET)
	public String insertCostDetailGet(@ModelAttribute PaginationInfo info, Model model) {
		
		info.setTotalRecord(costDetailService.selectCostDetailCount(info));
		info.setBlockSize(10);
		info.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);

		// 원가 상세사항을 입력할 때 원가의 분류 코드 조회 후 뿌리기
		List<CostCodeVO> ccList = accService.selectCostCodeList();
		List<CostDetailVO> costDetailList = costDetailService.selectAllCostDetail(info);
		
		model.addAttribute("ccList", ccList);
		model.addAttribute("costDetailList", costDetailList);
		model.addAttribute("PaginationInfo", info);
		
		return "product/insert/cost_detail";
	}
	
	
	/**
	 * @MethodName : costDetailList
	 * @date : 2019. 5. 27.
	 * @author : Jeon KiChan
	 * @메소드설명 : 원가 상세사항 목록 페이지 들어가기
	 */
	@RequestMapping(value="/list/cost_detail.do", method=RequestMethod.GET)
	public String costDetailList(@ModelAttribute PaginationInfo info, Model model) {

		info.setTotalRecord(costDetailService.selectCostDetailCount(info));
		info.setBlockSize(10);
		
		//한번에 보여지는 결과값 개수
		if(info.getRecordCountPerPage() == 0) {			
			info.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}

		List<CostCodeVO> ccList = accService.selectCostCodeList();
		List<CostDetailVO> costDetailList = costDetailService.selectAllCostDetail(info);
		
		model.addAttribute("ccList", ccList);
		model.addAttribute("costDetailList", costDetailList);
		model.addAttribute("PaginationInfo", info);
		
		return "product/list/cost_detail_list";
	}
	
	
	/**
	 * @MethodName : insertCostDetailPost
	 * @date : 2019. 1. 23.
	 * @author : Jeon KiChan
	 * @param costDetailVo
	 * @메소드설명 : 원가 사항을 입력 받은 뒤 데이터 입력
	 */
	@RequestMapping(value="/insert/cost_detail.do",method=RequestMethod.POST)
	public String insertCostDetailPost(@ModelAttribute CostDetailVO costDetailVO, Model model) {
		
		String msg = "";
		String url = "/products/insert/cost_detail.do";
		
		int result = costDetailService.insertCostDetail(costDetailVO);
		
		if(result > 0) {
			msg = "원가 사항 입력 완료";
			
		}else {
			msg = "원가 사항 입력 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	/**
	 * @MethodName : insertCostsGet
	 * @date : 2019. 2. 18.
	 * @author : Jeon KiChan
	 * @메소드설명 : 다중 원가 입력 페이지 불러오기
	 */
	@RequestMapping(value="/insert/costs.do",method=RequestMethod.GET)
	public String insertCostsGet() {
		
		logger.info("Insert CostOption Page");
		
		return "product/insert/costs";
	}
	
	
	
	/**
	 * @MethodName : insertCostsPost
	 * @date : 2019. 2. 18.
	 * @author : Jeon KiChan
	 * @메소드설명 : 다중원가 데이터 입력
	 */
	@RequestMapping(value="/insert/costs.do", method=RequestMethod.POST)
	public String insertCostsPost(@ModelAttribute CostsVO costsVO, Model model) {
		
		logger.info("costsVO = {}", costsVO.toString());
		
		String msg = "";
		String url = "/products/insert/costs.do";
		
		int result = costDetailService.insertCostsData(costsVO);
		
		if(result > 0) {
			msg = "다중 원가 데이터 입력 완료";
			
		}else {
			msg = "다중 원가 데이터 입력 실패";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	/**
	 * @MethodName : selectCostCodeList
	 * @date : 2019. 5. 20.
	 * @author : Jeon KiChan
	 * @메소드설명 : 원가 코드 분류 가져오기
	 */
	@RequestMapping(value="/cost_code_list.do", method=RequestMethod.GET)
	@ResponseBody
	public List<CostCodeVO> selectCostCodeList(){
		
		return accService.selectCostCodeList();
	}
	
	/**
	 * @MethodName : selectCostDetailListByCcpk
	 * @date : 2019. 5. 22.
	 * @author : Jeon KiChan
	 * @메소드설명 : 원가 분류 코드로 원가 세부사항을 검색해서 목록가져오기 : ajax 형태
	 */
	@RequestMapping(value="/cost_detail_list_by_ccpk.do", method=RequestMethod.GET)
	@ResponseBody
	public List<CostDetailVO> selectCostDetailListByCcpk(@RequestParam int ccPk){
		
		return costDetailService.selectCostDetailByCcpk(ccPk); 
	}
	
	
	/**
	 * @MethodName : selectAllCostDetailJoinCostCodeList
	 * @date : 2019. 5. 23.
	 * @author : Jeon KiChan
	 * @메소드설명 : 원가상세사항과 원가분류코드 조인 목록 가져오기 : ajax 형태
	 */
	@RequestMapping(value="/all_cost_detail_join_cost_code_list.do", method=RequestMethod.GET)
	@ResponseBody
	public List<CostDetailVO> selectAllCostDetailJoinCostCodeList(){
		
		return costDetailService.selectAllCostDetailJoinCostCodeList();
	}
	
	
	/**
	 * @MethodName : selectCostsList
	 * @date : 2019. 5. 30.
	 * @author : Jeon KiChan
	 * @메소드설명 : 다중 원가 상세페이지 목록 들어가기
	 */
	@RequestMapping(value="/list/costs.do", method=RequestMethod.GET)
	public String selectCostsList(Model model) {
		
		model.addAttribute("costsList", costsService.selectCostsGroupBYTotalPriceResult());
		return "product/list/costs_list";
	}
	
	
	/**
	 * @MethodName : selectCostDetailByCostsData
	 * @date : 2019. 5. 30.
	 * @author : Jeon KiChan
	 * @메소드설명 : 다중 원가 상세페이지에서 해당 상품을 누를 경우 상품 이름과 고유 번호로 cost_detail 사항을 찾아서 자세하게 리스트로 뿌려주기
	 */
	@RequestMapping(value="/list/cost_detail_by_costs.do", method=RequestMethod.GET)
	@ResponseBody
	public List<CostsVO> selectCostDetailByCostsData(@ModelAttribute CostsVO costsVO){
	
		return costsService.selectCostDetailByCostsData(costsVO);
	}
	
	
	
	/**
	 * 
	 * @MethodName : updateCostDetailGet
	 * @date : 2020. 4. 6.
	 * @author : Jeon KiChan
	 * @param paramCdVO
	 * @param model
	 * @return
	 * @메소드설명 : 원가상세사항 pk 가져온 후 원가상세사항과 로그기록 가져오기
	 */
	@RequestMapping(value="/update/cost_detail.do", method=RequestMethod.GET)
	public String updateCostDetailGet(@ModelAttribute CostDetailVO paramCdVO, Model model) {
		
		CostDetailVO cdVO = costDetailService.selectCostDetailByCcfk(paramCdVO);
		List<CostCodeVO> ccList = accService.selectCostCodeList();
		
		model.addAttribute("ccList", ccList);
		model.addAttribute("cdVO", cdVO);
		
		return "product/update/cost_detail";
	}
	
	
	/**
	 * 
	 * @MethodName : insertCostIoData
	 * @date : 2020. 4. 8.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 원육, 원가상품 입고 처리
	 */
	@RequestMapping(value="/insert/cost_io.do", method=RequestMethod.POST)
	public String insertCostIoData(@ModelAttribute CostIoVO costIoVO, Model model) {
		
		String url = "/products/update/cost_detail.do?cdPk="+costIoVO.getCdFk();;
		String msg = "";
		
		int result =costIoService.insertCostInputData(costIoVO);
		
		if(result > 0) {
			msg = "상품 입고 완료";
		}else {
			msg = "처리 오류";
		}
		
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : updateCostIoData
	 * @date : 2020. 4. 9.
	 * @author : Jeon KiChan
	 * @param paramCioVO
	 * @param model
	 * @return
	 * @메소드설명 : 상품 입고 데이터 상세사항페이지 들어가기
	 */
	@RequestMapping(value="/update/cost_io.do", method=RequestMethod.GET)
	public String updateCostIoData(@ModelAttribute CostIoVO paramCioVO, Model model) {
		
		CostIoVO ciVO = costIoService.selectCostIoData(paramCioVO); 
		
		model.addAttribute("ciVO", ciVO);
		
		return "product/read/cost_io";
	}
	
	
	/**
	 * 
	 * @MethodName : updateCostIoDataPost
	 * @date : 2020. 4. 9.
	 * @author : Jeon KiChan
	 * @param ciVO
	 * @return
	 * @메소드설명 : 상품 입출고 데이터 수정
	 */
	@RequestMapping(value="/update/cost_io.do", method=RequestMethod.POST)
	@ResponseBody
	public boolean updateCostIoDataPost(@ModelAttribute CostIoVO ciVO) {
		
		int result = costIoService.updateCostIoData(ciVO);
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	/**
	 * 
	 * @MethodName : chooseCostIo
	 * @date : 2020. 10. 22.
	 * @author : Jeon KiChan
	 * @param ciVO
	 * @param model
	 * @return
	 * @메소드설명 : 라벨지에 표기될 부분육 선택
	 */
	@RequestMapping(value="/choose_cost_io.do", method=RequestMethod.GET)
	public String chooseCostIo(@ModelAttribute CostIoVO ciVO, Model model) {
		
		String msg = "";
		String url = "/products/update/cost_detail.do?cdPk="+ciVO.getCdFk();
		
		
		int result = costIoService.chooseCostIo(ciVO);
		
		if(result > 0) {
			msg = "부분육 출고 선택 완료";
		}else {
			msg = "출고값 선택 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : selectOverTenDaysMeet
	 * @date : 2021. 8. 23.
	 * @author : Jeon KiChan
	 * @return
	 * @throws IOException 
	 * @메소드설명 : 등록일을 기준으로 도축일이 10일 이상 넘어간 원육 조회
	 */
	@RequestMapping(value="/cost_io_over_ten_days.do", method=RequestMethod.GET)
	public String selectOverTenDaysMeet(Model model) throws IOException {
		
		List<CostIoVO> ciList = costIoService.selectOverTenDaysMeet();
		
		if(ciList.size() == 0) {
			String msg = "조회 목록이 존재하지 않습니다";
			
			model.addAttribute("msg", msg);
			model.addAttribute("close", true);
			
			return "common/message";
		}
		
		String serviceKey = "JqxCQ8riO2cb9cf8pqWDutNOU066Crgza3EVNhjbKp%2B8TwqIT%2BMx5rAkD9Tv8Y6%2FK9QWkZWiE%2Fgto4QJtiDZQw%3D%3D";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("객체 생성 오류");
		}
		
		Document doc = null;
		Godomall gm = new Godomall();
		WebScraper ws = new WebScraper();
		String temp = "";
		String abattTemp = "";
		
		for(int i = 0; i < ciList.size(); i++) {
			
			String abatt = ciList.get(i).getCiAnimalProdTraceNum();
			
			if(ciList.get(i).getCiAnimalProdTraceNum().contains("L")){
				
				
				if(abattTemp.equals(abatt)) {
					abatt = temp;
					
				}else {
					abatt = ws.selectAbattDate(abatt);
					abattTemp = ciList.get(i).getCiAnimalProdTraceNum();
					temp = abatt;
				}
				
				
			}
			
			StringBuilder urlBuilder = new StringBuilder("http://data.ekape.or.kr/openapi-data/service/user/grade/confirm/issueNo"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("animalNo","UTF-8") + "=" + URLEncoder.encode( abatt , "UTF-8")); /*소돼지이력번호*/

	        try {
	        	
				doc = dBuilder.parse(urlBuilder.toString());		 
				 
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        try {
	        	Element root = doc.getDocumentElement();

	 			NodeList nList = doc.getElementsByTagName("item");
	 			
	 			
	 			for (int j = 0; j < nList.getLength(); j++) {
	 				Node node = nList.item(j);

	 				if (node.getNodeType() == Node.ELEMENT_NODE) {

	 					Element element = (Element) node;
	 					String abattDate = gm.getTagValue("abattDate", element);
	 					
	 					ciList.get(i).setCiAbattoir(abattDate);
	 					
	 				}
	 			}
	 			
	 			if(ciList.get(i).getCiAbattoir() == null || ciList.get(i).getCiAbattoir().equals("")) {
	 				ciList.get(i).setCiAbattoir("이력번호 조회 오류");
	 			}
	 			
	        }catch(NullPointerException e) {
	        	ciList.get(i).setCiAbattoir("이력번호 조회 오류");
	        	e.printStackTrace();
	        }
	       
	        
		}
		
		model.addAttribute("ciList", ciList);
		
		return "product/read/cost_io_over_ten_days";
	}
	
}
