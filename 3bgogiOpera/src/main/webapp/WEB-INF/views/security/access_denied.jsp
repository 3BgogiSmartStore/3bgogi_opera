<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>삼형제고기, 해당 페이지 권한이 없습니다</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
</head>
<body>
    <div class="dashboard-main-wrapper p-0">
        <nav class="navbar navbar-expand dashboard-top-header bg-white">
            <div class="container-fluid">
                <div class="dashboard-nav-brand">
                    <a class="navbar-brand" href="#" style="color:#FFA2A2;">삼형제고기</a>
                </div>
            </div>
        </nav>
        <div class="bg-light text-center">
            <div class="container">
                <div class="row">
                    <div class="offset-xl-2 col-xl-8 offset-lg-2 col-lg-8 col-md-12 col-sm-12 col-12">
                        <div class="error-section">
                            <div class="error-section-content">
                                <h1 class="display-3"> 권한이 없습니다. </h1>
                                <p> 접근 권한이 없는 사용자입니다. 관리자에게 문의해주세요. </p>
                                <sec:authorize access="hasRole('ROLE_USER')">
                                	<a href="<c:url value='/main/home.do'/>" class="btn btn-primary btn-lg"> 메인으로 돌아가기 </a>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_SELLER')">
                                	<a href="<c:url value='/seller/exchange.do'/>" class="btn btn-secondary btn-lg"> 셀러 메인으로 돌아가기 </a>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/js/main-js.js"></script>
</body>
</html>