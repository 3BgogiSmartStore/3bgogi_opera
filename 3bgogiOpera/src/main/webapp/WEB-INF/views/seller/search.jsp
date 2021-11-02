<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title> 판매량 조회 </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link
	href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
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
					style="color: #FFA2A2;"> 삼형제고기 셀러 </a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon fas fa-angle-down"></span>
				</button>
			</nav>
		</div>
		<div class="nav-left-sidebar sidebar-dark">
			<div class="menu-list">
				<nav class="navbar navbar-expand-lg navbar-light">
					<a class="d-xl-none d-lg-none" href="#"> 메뉴 열람 </a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class="collapse navbar-collapse" id="navbarNav">
					
						<ul class="navbar-nav flex-column">
							<%-- <li class="nav-item"><a class="nav-link" href="<c:url value='/seller/search.do'/>"> 판매량 조회 </a></li> --%>
							<li class="nav-item"><a class="nav-link" href="<c:url value='/seller/exchange.do'/>"> 환전 신청 </a></li>
							<li class="nav-item"><a class="nav-link" href="<c:url value='/seller/exchange_history.do'/>"> 환전 신청 내역 </a></li>
							<li class="nav-item"><a class="nav-link" href="<c:url value='/seller/info.do'/>"> 내 정보 </a></li>
							<li class="nav-item"><a class="nav-link" href="<c:url value='/logout.do'/>"> 로그아웃 </a></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
		<div class="dashboard-wrapper">
			<div class="container-fluid dashboard-content">
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">판매량 조회</h2>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<form class="card" action="" method="GET">
							<div class="card-body">
								<h4>판매량 조회</h4>
							</div>
						</form>
					</div>
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<div class="card-body">
								<table class="table table-hover">
									<thead>
										<tr style="text-align: center;">
											<th>날짜</th>
											<th>상품명</th>
											<th>판매량</th>
											<th>
												판매금액
												<br>(스토어 수수료 차감 금액)
											</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty sellerProdList }">
											<tr>
												<td colspan="4" style="text-align: center;">
													판매된 상품내역이 존재하지 않습니다
												</td>
											</tr>
										</c:if>
										<c:if test="${!empty sellerProdList }">
											<c:set var="totalQty" value="0"/>
											<c:set var="totalPrice" value="0"/>
											<c:forEach var="prodList" items="${sellerProdList }">
												<tr>
													<td style="text-align: center;">
														${prodList.resDate }
													</td>
													<td>
														${prodList.orProduct }
													</td>
													<td style="text-align: center;">
														<fmt:formatNumber value="${prodList.orAmount }" pattern="#,###"/>
														<c:set var="totalQty" value="${totalQty + prodList.orAmount }"/> 개
													</td>
													<td style="text-align: center;">
														<fmt:formatNumber value="${prodList.orTotalPrice }" pattern="#,###"/> 원
														<c:set var="totalPrice" value="${totalPrice + prodList.orTotalPrice }"/>
													</td>
												</tr>
											</c:forEach>
											<tr>
												<td colspan="2" style="text-align: center;"> 총 합 </td>
												<td style="text-align: center;">
													<fmt:formatNumber value="${totalQty }" pattern="#,###"/> 개
												</td>
												<td style="text-align: center;">
													<fmt:formatNumber value="${totalPrice }" pattern="#,###"/> 원
												</td>
											</tr>
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
</body>


</html>
