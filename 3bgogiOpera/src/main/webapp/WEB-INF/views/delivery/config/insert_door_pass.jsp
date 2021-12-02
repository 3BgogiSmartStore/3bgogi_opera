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
<title> 공동현관 출입방법 입력하기 </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.css" />
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/js/common_util.js"></script>
<script type="text/javascript">
    
    	$(function(){
    		
    		$("#searchAddressBtn").click(function(){
    			window.open("<c:url value='/orders/search/tab.do'/>", "우편 조회" , "width=700, height=800, top=100, left=300, scrollbars=no");
    			
    		});
    		
    	});
    	
    	
    </script>
</head>
<style type="text/css">
	body{
		margin-bottom : 40px;
		padding-top : 40px;
	}
	label{
		font-size: 12px;
	}
	.form-control, .btn-sm{
		font-size: 11px;
	}
</style>
<body>
	<div class="row">
		<div class="offset-xl-1 col-xl-10 col-lg-12 col-md-12 col-sm-12 col-12">
			<form class="row" id="insertDoorPassMsgForm" action="<c:url value='/delivery/config/door_pass.do'/>" method="POST">
				<input type="hidden" id="orSerialSpecialNumber" value="${orSerialSpecialNumber }" name="orSerialSpecialNumber">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h4 class="mb-0"> 
								공동현관비밀번호 입력하기 
							</h4>
							<c:if test="${!empty dpVO }">
								<h5 class="mb-0" style="color : red;"> 
									${dpVO.dpRegdate } 에 입력된 공동현관 비밀번호 [ ${dpVO.dpMsg } ]
								</h5>					
							</c:if>
						</div>
						<div class="card-body">
							<div class="needs-validation" >
								<div class="row mb-2">
									<div class="col-md-12">
										<label for="orUserColumn2"> 구매자 번호 </label> 
										<input type="text" class="form-control form-control-sm" id="orBuyerContractNumber1" name="orBuyerContractNumber1" value="${orVO.orBuyerContractNumber1 }" placeholder="">
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-md-12">
										<label for="orUserColumn3"> 수령자 번호 </label> 
										<input type="text" class="form-control form-control-sm" id="orReceiverContractNumber1" name="orReceiverContractNumber1" value="${orVO.orReceiverContractNumber1 }"  placeholder="">
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-md-12">
										<label for="orShippingAddress"> 주소 </label> 
										<input type="text" class="form-control form-control-sm" id="orShippingAddress" name="orShippingAddress" placeholder="" value="${orVO.orShippingAddress }" >
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-md-12">
										<label for="orShippingAddressDetail"> 상세 주소 </label> 
										<input type="text" class="form-control form-control-sm" id="orShippingAddressDetail" name="orShippingAddressDetail" value="${orVO.orShippingAddressDetail }"  placeholder="">
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-md-12">
										<label for="orShippingAddressDetail"> 공동현관 비밀번호 입력 </label> 
										<input type="text" class="form-control form-control-sm" id="orDelivEnter" name="orDelivEnter"  placeholder="">
									</div>
								</div>
								<hr class="mb-4">
								<button class="btn btn-primary btn-lg btn-block" type="submit"> 추가하기 </button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

<script src="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.full.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/inputmask/js/jquery.inputmask.bundle.js"></script>
<script type="text/javascript">
	$(function() {
			
		//$(".phone-inputmask").inputmask("999-9999-9999");
			
		$.datetimepicker.setLocale('kr');
			
	});	
</script>
</html>
