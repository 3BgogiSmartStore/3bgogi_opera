<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title> 송장 선택창 </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${resourcePath}/resources/vendor/multi-select/css/multi-select.css">
<link rel="stylesheet" href="${resourcePath}/resources/vendor/bootstrap-select/css/bootstrap-select.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>


<script type="text/javascript">
    
    	$(function(){
    		
    		$("#cjDeliverySendingResultExcelForm").submit(function(){

    			
				
			});
    		
    	});
    </script>
<style>
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
	<div class="container-fluid  dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<div class="card-header" style="text-align: center;"></div>
					<form class="card-body" id="cjDeliverySendingResultExcelForm" method="POST" action="<c:url value='/delivery/cj_config/select_invoice_num.do'/>">
					
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="cjDeliverySendingResultExcelFormTest">
							<select class="selectpicker form-control-sm mb-2" multiple data-actions-box="true" data-width="100%" id="createInvoiceNumList" name="createInvoiceNumList" title="송장 입력 전체 차수">
								<c:if test="${empty createInvoiceNum }">
									<option disabled>금일 생성된 송장이 없습니다</option>
								</c:if>
								<c:if test="${!empty createInvoiceNum }">
									<option disabled> 송장생성차수 </option>
									
									<c:set  var="invoiceCountNum" value="1"/>
									<c:forEach var="invoiceNumList" items="${createInvoiceNum }">
										<option value="${invoiceNumList.orInvoiceNumDate }"> 
											송장 ${invoiceCountNum} 차 ${invoiceNumList.orDeliveryCompany} ${invoiceNumList.orInvoiceNumDate }
										</option>

										<c:set var="invoiceCountNum" value="${invoiceCountNum + 1 }" />
									</c:forEach>
								</c:if>
							</select>
						</div>
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<button class="btn btn-primary btn-block"> 결과 가져오기 </button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<iframe id="excelDownloadIframe" width="0" height="0">
	</iframe> 
</body>
<script src="${pageContext.request.contextPath}/resources/vendor/slimscroll/jquery.slimscroll.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/js/bootstrap-select.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
</html>
