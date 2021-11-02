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
<title> 셀러 정보 확인 </title>
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
							<h2 class="pageheader-title">내 정보</h2>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 셀러 ${adVO.adminName } 님의 등록 정보 ( 셀러 활동 시작일 ${adVO.sellerStartDate } )</h5>
                                <div class="card-body">
                                    <form name="updateSellerInfoForm" id="updateSellerInfoForm" action="<c:url value='/seller/update.do'/>" method="post">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 셀러 아이디  </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="" class="form-control" value="${adVO.adminId }" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 셀러 이름 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="셀러명 입력" id="adminName" class="form-control" name="adminName" value="${adVO.adminName }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비밀번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="password" placeholder="비밀번호 수정" id="adminPass" class="form-control" name="adminPass" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비밀번호 확인 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="password" placeholder="비밀번호 수정 확인" id="adminPassCheck" class="form-control" name="adminPassCheck" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 셀러 연락처 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="셀러명 입력" id="adminPhone" class="form-control" name="adminPhone" value="${adVO.adminPhone }">
                                            </div>
                                        </div>
                                        <div class="form-group row cdLossRateDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 관리중인 상품 번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="" class="form-control" value="${adVO.sellerProdList }" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button type="submit" class="btn btn-space btn-primary"> 수정하기 </button>
                                                <button class="btn btn-space btn-secondary">취소하기</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
				</div>
			</div>
		</div>
	</div>
</body>


</html>
