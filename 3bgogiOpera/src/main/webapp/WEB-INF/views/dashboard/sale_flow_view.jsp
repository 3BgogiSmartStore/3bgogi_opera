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
							<h5 class="card-header">EBIT (Earnings Before Interest &
								Tax)</h5>
							<div class="card-body">
								<div id="ebit_morris"></div>
								<!-- <div class="text-center">
									<span class="legend-item mr-3"> <span
										class="fa-xs text-secondary mr-1 legend-tile"><i
											class="fa fa-fw fa-square-full"></i></span> <span
										class="legend-text">EBIT (Earnings Before Interest &
											Tax)</span>
									</span>
									<p></p>
								</div> -->
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">4 ~ 5점 리뷰</h5>
							<div class="card-body">
								<div id="morris_gross" style="height: 272px;"></div>
							</div>
							<div class="card-footer bg-white">
								<p>
									Budget <span class="float-right text-dark">12,000.00</span>
								</p>
								<p>
									Balance<span class="float-right text-dark">-2300.00 <span
										class="ml-2 text-secondary"><i
											class="fas fa-caret-up mr-1"></i>25%</span></span>
								</p>
							</div>
						</div>
					</div>
					
					<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">1 ~ 3점 리뷰</h5>
							<div class="card-body">
								<div id="morris_profit" style="height: 272px;"></div>
							</div>
							<div class="card-footer bg-white">
								<p>
									Budget <span class="float-right text-dark">3500.00</span>
								</p>
								<p>
									Balance <span class="float-right text-dark">230.00 <span
										class="ml-2 text-secondary"><i
											class="fas fa-caret-up mr-1"></i>25%</span></span>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">AP and AR Balance</h5>
							<div class="card-body">
								<canvas id="chartjs_balance_bar"></canvas>
							</div>
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">
								Cost of goods / Services <span class="float-right">1 Jan
									2018 to 31 Dec 2018</span>
							</h5>
							<div class="card-body">
								<div id="goodservice"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<!-- ============================================================== -->
					<!-- overdue invoices  -->
					<!-- ============================================================== -->
					<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Disputed vs Overdue Invoices</h5>
							<div class="card-body">
								<div class="ct-chart-invoice ct-golden-section"></div>
								<div class="text-center m-t-40">
									<span class="legend-item mr-3"> <span
										class="fa-xs text-primary mr-1 legend-tile"><i
											class="fa fa-fw fa-square-full "></i></span><span
										class="legend-text">Disputed Invoices</span>
									</span> <span class="legend-item mr-3"> <span
										class="fa-xs text-secondary mr-1 legend-tile"><i
											class="fa fa-fw fa-square-full "></i></span><span
										class="legend-text">Overdue Invoices</span>
									</span>
								</div>
							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end overdue invoices  -->
					<!-- ============================================================== -->
					<!-- ============================================================== -->
					<!-- disputed invoices  -->
					<!-- ============================================================== -->
					<div class="col-xl-5 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Disputed Invoices</h5>
							<div class="card-body">
								<div class="ct-chart-line-invoice ct-golden-section"></div>
								<div class="text-center m-t-10">
									<span class="legend-item mr-3"> <span
										class="fa-xs text-primary mr-1 legend-tile"><i
											class="fa fa-fw fa-square-full"></i></span> <span
										class="legend-text">Disputed Invoices</span>
									</span> <span class="legend-item mr-3"> <span
										class="fa-xs text-secondary mr-1 legend-tile"><i
											class="fa fa-fw fa-square-full"></i></span> <span
										class="legend-text">Avarage</span>
									</span>
								</div>
							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end disputed invoices  -->
					<!-- ============================================================== -->
					<!-- ============================================================== -->
					<!-- account payable age  -->
					<!-- ============================================================== -->
					<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Accounts Payable Age</h5>
							<div class="card-body">
								<div id="account" style="height: 300px;"></div>
							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end account payable age  -->
					<!-- ============================================================== -->
					<!-- ============================================================== -->
					<!-- working capital  -->
					<!-- ============================================================== -->
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">
								Working Capital <span class="float-right">1 Jan 2018 to
									31 Dec 2018</span>
							</h5>
							<div class="card-body">
								<div id="capital"></div>
								<div class="text-center m-t-10">
									<span class="legend-item mr-3"> <span
										class="fa-xs text-secondary mr-1 legend-tile"><i
											class="fa fa-fw fa-square-full"></i></span> <span
										class="legend-text">Working Capital</span>
									</span>
									<p></p>
								</div>
							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end working capital  -->
					<!-- ============================================================== -->
					<!-- ============================================================== -->
					<!-- inventory turnover  -->
					<!-- ============================================================== -->
					<div class="col-xl-6 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Inventory Turnover</h5>
							<div class="card-body">
								<div class="ct-chart-inventory ct-golden-section"></div>
								<div class="text-center m-t-10">
									<span class="legend-item mr-3"> <span
										class="fa-xs text-primary mr-1 legend-tile"><i
											class="fa fa-fw fa-square-full"></i></span> <span
										class="legend-text">Turnover</span>
									</span> <span class="legend-item mr-3"> <span
										class="fa-xs text-secondary mr-1 legend-tile"><i
											class="fa fa-fw fa-square-full"></i></span> <span
										class="legend-text">Target</span>
									</span> <span class="legend-item mr-3"> <span
										class="fa-xs text-info mr-1 legend-tile"><i
											class="fa fa-fw fa-square-full"></i></span> <span
										class="legend-text">Acheived</span>
									</span>
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
