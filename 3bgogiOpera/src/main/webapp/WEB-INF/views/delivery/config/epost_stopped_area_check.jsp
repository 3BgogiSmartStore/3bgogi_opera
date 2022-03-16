<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="kr">
<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title> 우체국 택배 발송 중지 지역 확인 </title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.css" />
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.full.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script type="text/javascript">
    
    	$(function(){
    		
    		
    		$('#dateStart').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date(1985,10,01)
    			
    		});
    		
    		
    		$('#dateEnd').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date(1985,10,01)
    			
    		});
    		
    		$.datetimepicker.setLocale('kr');
    		
    		
    		$("button[name=addrCheckBtn]").click(function(){
    			
    			$(".table-addr", opener.document).each(function(){
    				
    				let addrCheck = false;
    				let openerAddr = $(this).data("table-addr");

    				
    				let tableCountings = $(this).data("data-table-info");
    				
    				console.log(openerAddr);
    				
    				
    				$(".Addr").each(function(){
    					
    					if($(this).text().includes(openerAddr)) addrCheck = true;
    					
    					console.log( $(this).text().includes(openerAddr) )
    					
    				});
    				
					
    				if(addrCheck == true) $(this).prop("checked",false);
    				
    				
    				
    			});
    			
    			/* $.each($(opener.document).data("data-table-addr"),function(){
    				
    				
    				alert(this.text());
    				
    				
    			}); */
    			
    			
    		});
    	});
    </script>
    <style>
    html,
    body {
        height: 100%;
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
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
		        <div class="card">
		            <div class="card-header" style="text-align: center;">
		                <h4 class="mb-1" style="color:red;"> 확인일 선택 </h4>
		            </div>
		            <form class="card-body" method="POST" action="<c:url value='/security/epost/epost_stopped_area_check.do'/>">
		            
		            	<div class="btn-group">
				        	<input type="text" class="form-control" id="dateStart" name="dateStart" style="text-align: center;" value="${osVO.dateStart }"/>
				        	<input type="text" class="form-control" id="dateEnd" name="dateEnd" style="text-align: center;" value="${osVO.dateEnd }"/>
				        	<button class="btn btn-primary" type="submit"> 검색 </button>
				        </div>
				        <div class="btn-group">
			                <label class="custom-control custom-radio custom-control-inline  mb-2">
			                    <input type="radio" id="" name="cjFlag" value="0" 
			                    	<c:if test="${cjFlag == 0 }">
										checked="checked"
									</c:if>
			                    class="custom-control-input"><span class="custom-control-label"> cj 무시 </span>
			                </label>
			                <label class="custom-control custom-radio custom-control-inline  mb-2">
			                	<input type="radio" id="" name="cjFlag" value="1"
			                    	<c:if test="${cjFlag == 1 }">
										checked="checked"
									</c:if>
			                    class="custom-control-input"><span class="custom-control-label"> cj 적용 </span>
			                </label>
				        </div>
				        
				        
		            </form>
		        </div>
		        <c:if test="${!empty epostResultList }">
		        	<div class="card">
						<div class="card-body">   	
				            		<table class="table table-hover">
		                            	<thead>
											<tr>
												<th>구매자/수령자</th>
												<th>연락처</th>
												<th>주소</th>
												<th>결과</th>
											</tr>
		                                </thead>
		                                <tbody>
		                                	<c:set var="errorResult" value="0" />
		                                	<c:forEach var="epostList" items="${epostResultList }">	
		                                	<c:if test="${empty epostList.error_code }">
		                                    	<tr>
			                                    	<td>
			                                        	${epostList.orVO.orBuyerName } / ${epostList.orVO.orReceiverName }
			                                        </td>
			                                        <td>
			                                        	${epostList.orVO.orBuyerContractNumber1 }
			                                        </td>
			                                        <td class="Addr">${epostList.orVO.orShippingAddress } ${epostList.orVO.orShippingAddressDetail }</td>
			                                        
			                                        <td>${epostList.stopReason }</td>
												</tr>  
												<c:set var="errorResult" value="${errorResult + 1 }" />
		                                    </c:if>
											</c:forEach>       	
		                               </tbody>
		                           </table>
		                           <button class="btn btn-danger btn-block"> 발송 불가능  (${fn:length(errorResult)} 개 ) </button>
		                           
		                           <button class="btn btn-primary btn-block" name="addrCheckBtn"> 발송 체크 </button>
			            	</div>
			            	
					</div>
		        </c:if>
			</div>
		</div>
	</div>
</body>
</html>
