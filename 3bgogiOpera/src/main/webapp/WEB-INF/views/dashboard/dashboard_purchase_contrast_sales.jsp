<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>매입 매출 통계 조회</title>

	<link href="${resourcePath}/resources/libs/css/theme.css" rel="stylesheet" media="all">
	<link href="${resourcePath}/resources/libs/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet"  media="all">
	<link rel="stylesheet" href="${resourcePath}/resources/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${resourcePath}/resources/vendor/fonts/circular-std/style.css" >
	<link rel="stylesheet" href="${resourcePath}/resources/libs/css/style.css">
	<link rel="stylesheet" href="${resourcePath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">

	
	<link rel="stylesheet" href="${resourcePath}/resources/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css">
	<link rel="stylesheet" href="${resourcePath}/resources/vendor/bootstrap-colorpicker/%40claviska/jquery-minicolors/jquery.minicolors.css">

	<link rel="stylesheet" href="${resourcePath}/resources/vendor/datepicker/jquery.datetimepicker.css" />

	
	<script src="${resourcePath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
	<script src="${resourcePath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
	<script src="${resourcePath}/resources/libs/js/common_util.js"></script>
	<script type="text/javascript">
	    
	function selectProdTaxInfo(prodCategory){
		let dateStart = $('#dateStart').val();
		let dateEnd = $('#dateEnd').val();	
			
		$.ajax({
		    type       : 'GET',
		    async		: false,
		    url        : '/dashboard/category_type_list.do',
		    data       : {
		    	"dateStart":dateStart,
		    	"dateEnd":dateEnd,
		    	"searchKeyword":prodCategory
		    	
		    },
		    success    : function(data){
		    	prodTaxInfoListInnerHTMLForm(data);
		    }
		});
		
	}
	
	function prodTaxInfoListInnerHTMLForm(prodList){
		let prodTaxInfoListHTML = "";
		
		let totalQty = 0;
		let totalCost = 0;
		let superPiType = "";
		let measureType = "";
		
		for(let i = 0; i < prodList.length; i++){
			prodTaxInfoListHTML+=
			"<tr>"
				+"<td>"+prodList[i].superPiType+"</td>";
				
				if(prodList[i].piMeasure == 'g'){
					prodTaxInfoListHTML+= "<td>"+(prodList[i].piQty/1000)+" kg </td>";
					measureType='g';
					
				}else{
					prodTaxInfoListHTML+= "<td>"+comma(prodList[i].piQty)+" "+prodList[i].piMeasure+"</td>";
					measureType=prodList[i].piMeasure;
				}
				
				prodTaxInfoListHTML+="<td>"+comma(prodList[i].piTotalCost)+" 원 </td>"
			+"</tr>";
			
			totalQty+=prodList[i].piQty;
			totalCost+=prodList[i].piTotalCost;
			superPiType = prodList[i].superPiType;
			
		}
		
		
		if(superPiType == '한돈' || superPiType == '한우' || superPiType == '수입'){
			
		prodTaxInfoListHTML+=
			"<tr>"
				+"<td> 총 합 </td>";
				
				if(measureType == 'g'){
					prodTaxInfoListHTML+= "<td>"+(totalQty/1000)+" kg </td>";
					
				}else{
					prodTaxInfoListHTML+= "<td>"+(totalQty)+" "+measureType+"</td>";
					
				}
				
				
				prodTaxInfoListHTML+="<td>"+comma(totalCost)+" 원 </td>"
			+"</tr>";
		}
			
		$("#productTaxInfoListBody").html(prodTaxInfoListHTML);
		
	}
	
	    $(function(){
	    	
	    	$.datetimepicker.setLocale('kr');
	    	
	    	$('#dateStart').datetimepicker({
	    		timepicker:false,
	    		lang:'kr',
	    		format:'Y-m-d'
	    		
	    	});
	
	    	$('#dateEnd').datetimepicker({
	    		timepicker:false,
	    		lang:'kr',
	    		format:'Y-m-d'
	    		
	    	});
	    	
	    	$("#excelResultDown").click(function(){

				if(confirm("검색 결과를 엑셀파일로 다운로드하시겠습니까?")){

					var divs = document.createElement("div");
	    			
	    			var excelDownloadForm =  document.createElement("form");
	    			excelDownloadForm.action="/dashboard/prod_tax_excel.do";
	    			excelDownloadForm.method="POST";
	    			
	    			let dateStart = document.createElement("input");
	    			dateStart.name="dateStart";
	    			dateStart.value=$("#dateStart").val();
	    			
	    			let dateEnd = document.createElement("input");
	    			dateEnd.name="dateEnd";
	    			dateEnd.value=$("#dateEnd").val();
	    			
	    			excelDownloadForm.append(dateStart);
	    			excelDownloadForm.append(dateEnd);
	    			
					$("#excelDownloadIframe").append(excelDownloadForm);
	    			
	    			excelDownloadForm.submit();
	    			
	    			$("#excelDownloadIframe").html("");
	    			
	    		}
				
			});
	    	
	    	
	    });
	    
	</script>
</head>
<body>
	<div class="dashboard-main-wrapper">
		<div class="dashboard-header">
			<nav class="navbar navbar-expand-lg bg-white fixed-top">
				<a class="navbar-brand" href="<c:url value='/main/home.do'/>" style="color: #FFA2A2;"> 매입 및 매출 통계 </a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon fas fa-angle-down"></span>
				</button>
			</nav>
		</div>
		<div class="dashboard-wrapper" style="margin-left: 0px;">
			<div class="container-fluid dashboard-content">
				<div class="row">
                        <!-- ============================================================== -->
                        <!-- profile -->
                        <!-- ============================================================== -->
                        <div class="col-xl-3 col-lg-3 col-md-5 col-sm-12 col-12">
                            <!-- ============================================================== -->
                            <!-- card profile -->
                            <!-- ============================================================== -->
                            <div class="card">
                                <div class="card-body">
                                	<div class="text-center mb-3">
                                        <p>검색 날짜 기준일 설정</p>
                                    </div>
                                    <form class="form-row" method="get" action="<c:url value='/dashboard/dashboard_purchase_contrast_sales.do'/>">			                        				
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-12">
											<input type="text" class="form-control form-control-sm mb-2" id="dateStart" name="dateStart" style="text-align: center;" value="${osVO.dateStart }"/>
										</div>
										<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-12">
											<input type="text" class="form-control form-control-sm mb-2" id="dateEnd" name="dateEnd" style="text-align: center;" value="${osVO.dateEnd }"/>
										</div>
										
										<div class="col-xl-4 col-lg-12 col-md-12 col-sm-12 col-12">
											<button type="submit" class="btn btn-primary btn-xs btn-block" id="searchBtn" name="searchBtn"> 해당 날짜로 검색 </button>
										</div>
										
			                        </form>
                                </div>
                                <div class="card-body border-top">
                                    <div class="form-row">			                        				
										<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
											<button type="button" class="btn btn-primary btn-block" id="excelResultDown" name="excelResultDown"> 엑셀 결과 다운로드 </button>
										</div>
			                        </div>
                                </div>
                            </div>
                            <!-- ============================================================== -->
                            <!-- end card profile -->
                            <!-- ============================================================== -->
                        </div>
                        <!-- ============================================================== -->
                        <!-- end profile -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- campaign data -->
                        <!-- ============================================================== -->
                        <div class="col-xl-9 col-lg-9 col-md-7 col-sm-12 col-12">
                            <!-- ============================================================== -->
                            <!-- campaign tab one -->
                            <!-- ============================================================== -->
                            <div class="influence-profile-content pills-regular">
                                <ul class="nav nav-pills mb-3 nav-justified" id="pills-tab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="pills-campaign-tab" data-toggle="pill" href="#pills-campaign" role="tab" aria-controls="pills-campaign" aria-selected="true">거래내역서 세분화 통계</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="pills-packages-tab" data-toggle="pill" href="#pills-packages" role="tab" aria-controls="pills-packages" aria-selected="false">정육</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="pills-review-tab" data-toggle="pill" href="#pills-review" role="tab" aria-controls="pills-review" aria-selected="false">양념육</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="pills-msg-tab" data-toggle="pill" href="#pills-msg" role="tab" aria-controls="pills-msg" aria-selected="false">간편조리 및 기타</a>
                                    </li>
                                </ul>
                                <div class="tab-content" id="pills-tabContent">
                                    <div class="tab-pane fade show active" id="pills-campaign" role="tabpanel" aria-labelledby="pills-campaign-tab">
                                        <div class="row">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="section-block">
                                                    <h3 class="section-title">검색된 거래내역서 상위 카테고리 목록</h3>
                                                </div>
                                            </div>
                                            
                                            <c:if test="${!empty piTypeList }">
                                            	<c:set var="totalPriceValue" value="0"/>
                                            	
                                            	<c:forEach var="piTypelist" items="${piTypeList }">                                            	
	                                            	<div class="col-xl-2 col-lg-2 col-md-3 col-sm-6 col-6">
		                                                <div class="card">
		                                                    <div class="card-body">
		                                                    
		                                                        <a class="mb-1" onclick="selectProdTaxInfo('${piTypelist.piPk }')">${piTypelist.superPiType } <br> (총 금액 : <fmt:formatNumber value="${piTypelist.piTotalCost }" pattern="#,###"/> 원)</a>
		                                                        
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <c:set var="totalPriceValue" value="${totalPriceValue + piTypelist.piTotalCost }"/>
                                            	</c:forEach>
                                            	<div class="col-xl-2 col-lg-2 col-md-3 col-sm-6 col-6">
		                                        	<div class="card">
		                                            	<div class="card-body">
		                                                	<a class="mb-1"> 총합  : <fmt:formatNumber value="${totalPriceValue }" pattern="#,###"/> 원</a>
		                                                </div>
		                                                
		                                            </div>
		                                       </div>
                                            </c:if>
                                            
                                            <c:if test="${empty piTypeList }">
                                            	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                                                <div class="section-block">
	                                                    <h3 class="section-title"> 거래내역서의 상위 카테고리 목록이 존재하지 않습니다.</h3>
	                                                </div>
	                                            </div>
                                            </c:if>
                                        </div>
                                        <div class="section-block">
                                            <hr>
                                        </div>
                                        <div class="card">
                                            <div class="card-body">
                                                <table id="productLists" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
			                                        <thead class="bg-light">
			                                            <tr>
			                                            	<th >거래상품타입</th>
			                                            	<th >수량 / 중량</th>
			                                                <th > 금액 </th>
			                                            </tr>
			                                        </thead>
			                                        <tbody id="productTaxInfoListBody">

			                                        </tbody>
			                                    </table>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="tab-pane fade" id="pills-packages" role="tabpanel" aria-labelledby="pills-packages-tab">
                                        <div class="row">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="section-block">
                                                    <h3 class="section-title"> 정육 매출액
	                                                    <c:if test="${!empty meatSales }">                                                    
		                                                    <fmt:formatNumber value="${meatSales }" pattern="#,###"/> 원
	                                                    </c:if> 
                                                    </h3>
                                                </div>
                                            </div>
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="card">
		                                            <div class="card-body">
		                                                <table id="productLists" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
					                                        <thead class="bg-light">
					                                            <tr>
					                                            	<th >부위 명</th>
					                                            	<th >판매 중량</th>
					                                            </tr>
					                                        </thead>
					                                        
					                                        <tbody>
																<c:if test="${!empty meatWeightList }">
																	<c:forEach var="meatWeightlist" items="${meatWeightList }">
																		<tr>
							                                            	<th >${meatWeightlist.costDetailName }</th>
							                                            	<th  style="text-align: right; font-size: 12px; word-break: keep-all; white-space: nowrap;"> <fmt:formatNumber value="${meatWeightlist.ciWeight }" pattern="#,###"/> g</th>
							                                            	
							                                            </tr>
																	</c:forEach>
																</c:if>
					                                        </tbody>
					                                    </table>
		                                            </div>
		                                        </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="pills-review" role="tabpanel" aria-labelledby="pills-review-tab">
                                    	<div class="row">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="section-block">
                                                    <h3 class="section-title"> 양념육 매출액 <fmt:formatNumber value="${seasoningMeatSales }" pattern="#,###"/> 원</h3>
                                                </div>
                                            </div>
                                            
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="card">
		                                            <div class="card-body">
		                                                <table id="productLists" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
					                                        <thead class="bg-light">
					                                            <tr>
					                                            	<th >상품 명</th>
					                                            	<th >부위 명</th>
					                                            	<th >판매 중량</th>
					                                            </tr>
					                                        </thead>
					                                        
					                                        <tbody>
																<c:if test="${!empty seasoningMeatWeightList }">
																	<c:forEach var="seasoningMeatWeightlist" items="${seasoningMeatWeightList }">
																		<tr>
							                                            	<th >${seasoningMeatWeightlist.costDetailName }</th>
							                                            	<th >${seasoningMeatWeightlist.ciCountryOfOrigin }</th>
							                                            	<th  style="text-align: right; font-size: 12px; word-break: keep-all; white-space: nowrap;"> <fmt:formatNumber value="${seasoningMeatWeightlist.ciWeight }" pattern="#,###"/> g</th>
							                                            	
							                                            </tr>
																	</c:forEach>
																</c:if>
					                                        </tbody>
					                                    </table>
		                                            </div>
		                                        </div>
                                            </div>
                                        </div> 
                                       
                                    </div>
                                    <div class="tab-pane fade" id="pills-msg" role="tabpanel" aria-labelledby="pills-msg-tab">
                                        <div class="row">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="section-block">
                                                    <h3 class="section-title"> 간편조리 매출액 <fmt:formatNumber value="${mealkitSales }" pattern="#,###"/> 원</h3>
                                                </div>
                                            </div>
                                            
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="card">
		                                            <div class="card-body">
		                                                <table id="productLists" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
					                                        <thead class="bg-light">
					                                            <tr>
					                                            	<th >상품 명</th>
					                                            	<th >판매 개수</th>
					                                            </tr>
					                                        </thead>
					                                        
					                                        <tbody>
																<c:if test="${!empty mealkitList }">
																	<c:forEach var="mealkitlist" items="${mealkitList }">
																		<tr>
							                                            	<th >${mealkitlist.orProduct }</th>
							                                            	<th  style="text-align: right; font-size: 12px; word-break: keep-all; white-space: nowrap;"> <fmt:formatNumber value="${mealkitlist.orAmount }" pattern="#,###"/> 개</th>
							                                            </tr>
																	</c:forEach>
																</c:if>
					                                        </tbody>
					                                    </table>
		                                            </div>
		                                        </div>
                                            </div>
                                        </div> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
			</div>
		</div>
	</div>
	<iframe id="excelDownloadIframe" width="0" height="0">
	</iframe>  
</body>

<script src="${resourcePath}/resources/vendor/datepicker/jquery.datetimepicker.full.min.js"></script>
<script src="${resourcePath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/js/main-js.js"></script>
</html>
