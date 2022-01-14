<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>판매량 조회</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/charts/chartist-bundle/chartist.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/charts/c3charts/c3.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/charts/morris-bundle/morris.css">

<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${resourcePath}/resources/libs/js/common_util.js"></script>
<script type="text/javascript">
    
    	$(function(){
    		
    	});
    </script>
</head>
<body>
	<div class="dashboard-main-wrapper">
		<div class="dashboard-header">
			<nav class="navbar navbar-expand-lg bg-white fixed-top">
				<a class="navbar-brand" href="<c:url value='/seller/search.do'/>"
					style="color: #FFA2A2;"> 삼형제고기 현황 </a>
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
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">판매량 조회</h2>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header"> 지난 3개월 매출</h5>
							<div class="card-body">
								<div id="threeMonthSales"></div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header"> 리뷰 평점 </h5>
							<div class="card-body">
								<div id="morris_gross" style="height: 272px;"></div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header"> 리뷰 개수 </h5>
							<div id="reviewData" class="card-footer bg-white">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header"> 이번 달 일별 매출</h5>
							<div class="card-body">
								<div id="monthSales"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="card">
                                    <h5 class="card-header"> 최소 보유치 이하로 내려간 상품 재고 목록 </h5>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover" style="table-layout: fixed; word-break: keep-all;">
                                            	<colgroup>
													<col width="150px" />
													<col width="150px" />
													<col width="50px" />
													<col width="50px" />
												</colgroup>
                                                <thead class="bg-light">
                                                    <tr class="border-0">
                                                        <th class="border-0"> 상품명 </th>
                                                        <th class="border-0"> 옵션명 </th>
                                                        <th class="border-0"> 재고 개수 </th>
                                                        <th class="border-0"> 권장 보유량 </th>
                                                    </tr>
                                                </thead>
                                                
                                                <tbody>
                                                	<c:if test="${empty productStockList}">
                                                		<tr>
                                                			<td colspan="4"> 권장 보유량 이하로 내려간 상품이 없습니다 </td>
                                                		</tr>
                                                	</c:if>
                                                	<c:if test="${!empty productStockList}">
														<c:forEach var="productOptionlist" items="${productStockList }">														
		                                                    <tr>
		                                                        <td>${productOptionlist.productName }</td>
		                                                        <td>${productOptionlist.optionName }</td>
		                                                        <td>${productOptionlist.optionStock }</td>
		                                                        <td>${productOptionlist.optionStockMaxAlarm }</td>
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
</body>

<script src="${pageContext.request.contextPath}/resources/vendor/charts/chartist-bundle/chartist.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/charts/chartist-bundle/Chartistjs.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/charts/chartist-bundle/chartist-plugin-threshold.js"></script>

<script src="${pageContext.request.contextPath}/resources/vendor/charts/c3charts/c3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/charts/c3charts/d3-5.4.0.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/vendor/charts/charts-bundle/Chart.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/charts/charts-bundle/chartjs.js"></script>

<script src="${pageContext.request.contextPath}/resources/vendor/charts/sparkline/jquery.sparkline.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/js/customer_review_main.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/dashboard/main-js.js"></script>

<script src="${pageContext.request.contextPath}/resources/vendor/charts/morris-bundle/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/charts/morris-bundle/morris.js"></script>

</html>
