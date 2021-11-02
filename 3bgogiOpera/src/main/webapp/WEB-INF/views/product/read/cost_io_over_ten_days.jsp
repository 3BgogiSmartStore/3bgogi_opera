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
<title>원재료 상세 기록</title>
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
    	
    	$(".numberCheck").keyup(function(){
			 var checkVal = onlyNumberInsertFunc($(this).val());
			 $(this).val(checkVal);
			 
		 });
    	
    });
    	
    function onlyNumberInsertFunc(values){
		var regex=  /^\d+$/;
		
		if(!regex.test(values)){
			alert("숫자만 입력해야 합니다.");
			return 0;
			
		}else{
			return values;
			
		}
	}
    
</script>
<style>
html, body {
	
}

body {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
}
</style>
</head>
<body>
	<div class="dashboard-wrapper">
		<div class="container-fluid  dashboard-content">
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="card">
						<h5 class="card-header">상품 입고</h5>
						<div class="card-body">
							<div class="table-responsive">
								<table id="example2" class="table table-bordered" style="text-align: center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
									<thead class="bg-light">
										<tr>
											<th width="20%">부위</th>
											<th width="20%">이력번호</th>
											<th width="20%">등록일</th>
											<th width="20%">도축일</th>
											
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty ciList }">
											<tr>
												<td colspan="4">등록된 부분육이 없습니다</td>
											</tr>
										</c:if>
										
										<c:if test="${!empty ciList }">
											<c:forEach var="cilist" items="${ciList }">
												<tr class="table-3bgogi-hover">
													<td>${cilist.costDetailName }</td>
													<td>${cilist.ciAnimalProdTraceNum }</td>
													<td><fmt:formatDate value="${cilist.ciRegdate }" pattern="yyyy-MM-dd" /></td>
													<td>${cilist.ciAbattoir }</td>	
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
</body>


</html>

