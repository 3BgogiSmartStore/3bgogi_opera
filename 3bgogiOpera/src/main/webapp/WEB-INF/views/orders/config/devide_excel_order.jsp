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
<title> 엑셀 파일 주문서 나누기 </title>
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
jQuery.ajaxSettings.traditional = true;

var doubleSubmitFlag = false;

function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}

$(function(){
	$("#orderGiftSubmit, #excelOrderInserst").submit(function(){
		
		
		doubleSubmitCheck();
		
		if(doubleSubmitFlag == false){
			
			
			return false;
		}
	});
	
});
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
	<div class="container-fluid  dashboard-content">
					<div class="row">
						<c:if test="${!empty orderList}">
	                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> 엑셀 파일 주문서 나누기 </h5>
	                                <form class="card-body" id="excelOrderInserst" method="POST" action="<c:url value='/order/config/devide/excel_order_insert.do'/>">
	                                
	                                
	                                	<input type="hidden" name="orPk" value="${orVO.orPk }" id="orPk">
	                                	
	                                    <table class="table table-hover">
	                                        <thead>
	                                            <tr>
	                                                <th scope="col">구매자 표기명</th>
	                                                <th scope="col">수령자</th>
	                                                <th scope="col">연락처</th>
	                                                <th scope="col">우편번호</th>
	                                                <th scope="col">주소</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:set var="orderListCounting" value="0"/>
	                                        	<c:set var="addrFalse" value="0"/>
	                                        	<c:forEach var="orderlist" items="${orderList }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orBuyerAnotherName" value="${orderlist.orBuyerAnotherName }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orReceiverName" value="${orderlist.orReceiverName }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orReceiverContractNumber1" value="${orderlist.orReceiverContractNumber1 }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orReceiverContractNumber2" value="${orderlist.orReceiverContractNumber2 }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orShippingAddressNumber" value="${orderlist.orShippingAddressNumber }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orShippingAddress" value="${orderlist.orShippingAddress }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orShippingAddressDetail" value="${orderlist.orShippingAddressDetail }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orAmount" value="${orderlist.orAmount }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orProduct" value="${orderlist.orProduct }">	    
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orProductOption" value="${orderlist.orProductOption }">	   
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orDeliveryMessage" value="${orderlist.orDeliveryMessage }">
	                                        		<input type="hidden" name="orVoList[${orderListCounting }].orSendingDeadline" value="${orderlist.orSendingDeadline }">	
	                                        		 
		                                        	<tr
		                                        		<c:if test="${orderlist.orShippingAddressNumber == '0' }">
					                                    	style="background-color: #FFA2A2; color:white;"
					                                    	
					                                    </c:if>
		                                        	>
				                                     	<th scope="row">${orderlist.orBuyerAnotherName }</th>
				                                        <th scope="row">${orderlist.orReceiverName }</th>
				                                        <th scope="row">${orderlist.orReceiverContractNumber1 }</th>
				                                        <th scope="row">${orderlist.orShippingAddressNumber }</th>
				                                        <th scope="row">${orderlist.orShippingAddress}</th>
				                                        
				                                    </tr>
				                                    <c:if test="${orderlist.orShippingAddressNumber == '0' }">
				                                    	<c:set var="addrFalse" value="${addrFalse + 1 }"/>
				                                    </c:if>
				                                    
				                                    <c:set var="orderListCounting" value="${orderListCounting + 1 }"/>
	                                        	</c:forEach>
	                                        </tbody>
	                                    </table>
	                                    <c:if test="${addrFalse == 0 }">
				                        	<button type="submit" class="btn btn-success" style="display:block; text-align: right;"> 해당 정보로 입력하기 </button>
				                        	
				                        </c:if>  
				                        
				                        <c:if test="${addrFalse != 0 }">
				                        	<span class="text-danger" style="display:block; text-align: right;"> 잘못된 주소로 인해 우편번호를 찾을 수 없는 건이 있습니다. 직접 기입해주세요.</span>
				                        </c:if>  
	                                </form>
	                            </div>
	                        </div>
						
						</c:if>
						<c:if test="${empty orderList}">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> 엑셀 파일 입력 </h5>
	                                <div class="card-body">
	                                    <form id="orderGiftSubmit" method="POST" action="<c:url value='/order/config/devide/excel_order.do'/>" enctype="multipart/form-data">
	                                    	<input type="hidden" name="orPk" value="${orVO.orPk }" id="orPk">
	                                    	
	                                    	
	                                        <div class="input-group mb-3">
	                                        	<input type="file" name="excelfile" class="form-control">
		                                        <div class="input-group-append be-addon">
		                                        	<input type="submit" class="btn btn-success" value="해당 엑셀 파일로 나누기">
		                                        	<!-- <button type="button" type="submit" class="btn btn-success" > 해당 엑셀 파일로 나누기 </button> -->
		                                        </div>
	                                        </div>
	                                        
	                                        
	                                    </form>
	                                </div>
	                            </div>
	                        </div>
                       </c:if>
                        
					</div>
	</div>
	
</body>
</html>
