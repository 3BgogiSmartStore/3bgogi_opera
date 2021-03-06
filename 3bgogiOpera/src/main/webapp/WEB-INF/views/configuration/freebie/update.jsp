<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/vendor/inputmask/js/jquery.inputmask.bundle.js"></script>
    <style type="text/css">
    	.renewal-store-list{
    		cursor: pointer;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#fbMinDate').datetimepicker({
    			timepicker: true,
    			lang:'kr',
    			format:'Y-m-d H:i'
    			
    		});
    		
    		$('#fbMaxDate').datetimepicker({
    			timepicker: true,
    			lang:'kr',
    			format:'Y-m-d H:i'
    		});
    		
    		$("input[name=fbTotalQtyFlag]").change(function(){
    			var fbTotalQtyFlag = $(this).val();
    			var fbAddType = $("input[name=fbAddType]:checked").val();
    			if(fbTotalQtyFlag == 0){
    				$("#fbMinTotalQty").prop("readonly","readonly");
    				$("#fbMaxTotalQty").prop("readonly","readonly");
    			}else{
    				$("#fbMinTotalQty").prop("readonly","");
    				$("#fbMaxTotalQty").prop("readonly","");
    			}
    			
    			if(fbAddType == 3 && fbTotalQtyFlag == 1){
    				$("#fbMinPrice").prop("readonly","readonly");
    				
    			}else{
    				$("#fbMinPrice").prop("readonly","");
    				
    			}
    			
    		});
    		
    		$("input[name=fbAnotherCheckFlag]").change(function(){
    			var fbAnotherCheckFlag = $(this).val();
    			
    			if(fbAnotherCheckFlag == 0){
    				$("#fbAnotherCheckList").prop("disabled","disabled");
    				$("#fbAnotherCheckWord").prop("readonly","readonly");
    				$("#fbAnotherCheckType").prop("disabled","disabled");
    				
    			}else{
    				$("#fbAnotherCheckList").prop("disabled","");
    				$("#fbAnotherCheckWord").prop("readonly","");
    				$("#fbAnotherCheckType").prop("disabled","");
    			}
    		});
    		
    		$("input[name=fbAnotherCheckFlag2]").change(function(){
    			var fbAnotherCheckFlag2 = $(this).val();
    			
    			if(fbAnotherCheckFlag2 == 0){
    				$("#fbAnotherCheckList2").prop("disabled","disabled");
    				$("#fbAnotherCheckWord2").prop("readonly","readonly");
    				$("#fbAnotherCheckType2").prop("disabled","disabled");
    				
    			}else{
    				$("#fbAnotherCheckList2").prop("disabled","");
    				$("#fbAnotherCheckWord2").prop("readonly","");
    				$("#fbAnotherCheckType2").prop("disabled","");
    			}
    		});
    		
    		$("#freebieInsertForm").submit(function(){
    			var fbMinPrice = $("#fbMinPrice").val();
    			var fbMaxPrice = $("#fbMaxPrice").val();
    			var fbMinTotalQty = $("#fbMinTotalQty").val();
    			var fbMaxTotalQty = $("#fbMaxTotalQty").val();
    			
    			
    			if(fbMinPrice > fbMaxPrice){
    				alert("?????? ????????? ?????????????????? ?????? ??? ????????????");
    				$("#fbMaxPrice").focus();
    				event.preventDefault();
    				return false;
    				
    			}else if(fbMinTotalQty == '' && fbMinTotalQty == ''){
    				$("#fbMinTotalQty").val("0");
    				$("#fbMaxTotalQty").val("0");
    				event.preventDefault();
    				return false;
    			}
    			
    		});
    		
    		$("input[name=fbAddType]").change(function(){
    			var fbTotalQtyFlag = $("input[name=fbTotalQtyFlag]:checked").val();
    			var fbAddType = $(this).val();
    			
    			if($(this).val() == 3){
    				$("#totalPrice").text("?????? ??????");
    				$("#totalQty").text("?????? ??????");
    				$("#maxTotalPrice").hide();
    				$("#maxTotalQty").hide();
    				$("#fbMinPrice").prop("placeholder","?????? ??????");
    				$("#fbMinTotalQty").prop("placeholder","?????? ??????");
    				
    			}else{
    				$("#totalPrice").text("?????? ??????");
    				$("#totalQty").text("?????? ??????");
    				$("#maxTotalPrice").show();
    				$("#maxTotalQty").show();
    				$("#fbMinPrice").prop("placeholder","?????? ??????");
    				$("#fbMinTotalQty").prop("placeholder","?????? ??????");
    				
    			}
    			
    			if(fbAddType == 3 && fbTotalQtyFlag == 1){
    				$("#fbMinPrice").prop("readonly","readonly");
    				$("#fbMinTotalQty").focus();
    				
    			}else{
    				$("#fbMinPrice").prop("readonly","");
    				$("#fbMinPrice").focus();
    				
    			}
    			
    		});
    		
    		
    		
    	});
    	
    	function openProductOption(){
    		
    		window.open("<c:url value='/common/products.do'/>", "?????? ??????" , "width=800, height=800, top=50, left=400, scrollbars=no");
    		
    	}
    </script>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid dashboard-content ">
                  <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title"> ????????? ?????? ?????? </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> ?????? </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> ????????? ?????? </li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader  -->
                    <!-- ============================================================== -->
                    <form id="freebieInsertForm" action="<c:url value='/config/freebie/update.do'/>" method="POST">
                    	<input type="hidden" name="fbPk" value="${fbVO.fbPk }">
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> ????????? ?????? ?????? </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ????????? </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <input type="text" required="" id="fbName" name="fbName" class="form-control" value="${fbVO.fbName }">
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ?????? ?????? ????????? </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                            
	                                            	<c:set var="stores" value="${fn:split(fbVO.ssList,',') }"/>
							                        <select class="selectpicker" multiple data-actions-box="true" data-width="100%" id="ssList" name="ssList">
								                        <c:if test="${empty ssList }">
								                        	<option value="0">????????? ???????????? ????????????</option>
								                        </c:if>
								                        <c:if test="${!empty ssList }">
								                        	<c:forEach var="sslist" items="${ssList }">
								                        		<option value="${sslist.ssPk }"
								                        			
								                        			<c:forEach var="storeslist" items="${stores }">								                        			
									                        			<c:if test="${sslist.ssPk == storeslist }">
									                        				selected="selected"
									                        			</c:if>
								                        			</c:forEach>
								                        		>${sslist.ssName }</option>
								                        	</c:forEach>
								                        </c:if>
							                        </select>
							                        
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right">????????? ?????? ??????</label>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                            	<fmt:parseDate var="minDate" value="${fbVO.fbMinDate }" pattern="yyyy-MM-dd HH:mm"/>
			                                         
	                                                <input type="text" required id="fbMinDate" class="form-control" name="fbMinDate" value='<fmt:formatDate value="${minDate }" pattern="yyyy-MM-dd HH:mm"/>'>
	                                            </div>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                            	<fmt:parseDate var="maxDate" value="${fbVO.fbMaxDate }" pattern="yyyy-MM-dd HH:mm"/>
	                                            	
	                                                <input type="text" required id="fbMaxDate" class="form-control" name="fbMaxDate" value='<fmt:formatDate value="${maxDate }" pattern="yyyy-MM-dd HH:mm"/>'>
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ?????? ??????(?????? ??????) </label>
	                                            <div class="col-9 col-sm-7 col-lg-9">
	                                            	<label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="0" name="fbType" class="custom-control-input"
		                                                
		                                                	<c:if test="${fbVO.fbType == 0 }">
		                                                		checked="checked"
		                                                	</c:if>
		                                                	<c:if test="${fbVO.fbType != 0 }">
		                                                		disabled="disabled"
		                                                	</c:if>
		                                                	
		                                                ><span class="custom-control-label"> ?????????????????? </span>
		                                            </label>
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="1" name="fbType" class="custom-control-input"
	                                                		<c:if test="${fbVO.fbType == 1 }">
		                                                		checked="checked"
		                                                	</c:if>
		                                                	<c:if test="${fbVO.fbType != 1 }">
		                                                		disabled="disabled"
		                                                	</c:if>
	                                                	><span class="custom-control-label"> ???????????? ?????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="2" name="fbType" class="custom-control-input"
	                                                		<c:if test="${fbVO.fbType == 2 }">
		                                                		checked="checked"
		                                                	</c:if>
		                                                	<c:if test="${fbVO.fbType != 2 }">
		                                                		disabled="disabled"
		                                                	</c:if>
	                                                	><span class="custom-control-label"> ????????? ?????? ?????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="3" name="fbType" class="custom-control-input"
	                                                		<c:if test="${fbVO.fbType == 3 }">
		                                                		checked="checked"
		                                                	</c:if>
		                                                	<c:if test="${fbVO.fbType != 3 }">
		                                                		disabled="disabled"
		                                                	</c:if>
	                                                	><span class="custom-control-label"> ????????? ?????? ?????? </span>
		                                            </label>
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                        	<label class="col-12 col-sm-3 col-form-label text-sm-right"> ?????? ????????? </label>
	                                        	<label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="od.or_regdate" name="fbRegType" class="custom-control-input"
		                                                	<c:if test="${fbVO.fbRegType == 'od.or_regdate' }">
		                                                		checked="checked"
		                                                	</c:if>
		                                                ><span class="custom-control-label"> ??????????????? ????????? </span>
		                                            </label>
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="od.or_settlement_day" name="fbRegType" class="custom-control-input"
	                                                		<c:if test="${fbVO.fbRegType == 'od.or_settlement_day' }">
		                                                		checked="checked"
		                                                	</c:if>
	                                                	><span class="custom-control-label"> ????????? </span>
		                                            </label>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> ????????? ?????? </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <div class="input-group mb-3">
		                                                <input type="hidden" class="form-control" id="productPk" value="${fbVO.productPk }">
		                                                <input type="text" class="form-control" id="productName" value="${fbVO.productName }">
		                                                <div class="input-group-append">
		                                                    <button type="button" class="btn btn-primary" id="searchProduct" onclick="openProductOption()"> ?????? </button>
		                                                </div>
		                                            </div>
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? </label>
	                                            <div class="col-12 col-sm-8 col-lg-6">
	                                                <input type="text" required="" class="form-control" id="optionName" value="${fbVO.optionName }">
	                                                <input type="hidden" required="" class="form-control" id="optionPk" name="optionFk" value="${fbVO.optionFk }">
	                                                
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ?????? </label>
	                                            <div class="col-9 col-sm-7 col-lg-4">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" value="0" 
	                                                		<c:if test="${fbVO.fbAddType == 0 }">
	                                                			checked="checked"
	                                                		</c:if>
	                                                	name="fbAddType" class="custom-control-input"><span class="custom-control-label"> ?????????????????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="1" 
		                                                	<c:if test="${fbVO.fbAddType == 1 }">
	                                                			checked="checked"
	                                                		</c:if>
		                                                name="fbAddType" class="custom-control-input"><span class="custom-control-label"> ?????? ?????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="2" 
		                                                	<c:if test="${fbVO.fbAddType == 2 }">
	                                                			checked="checked"
	                                                		</c:if>
		                                                name="fbAddType" class="custom-control-input"><span class="custom-control-label"> ???????????? x ?????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" value="3" 
		                                                	<c:if test="${fbVO.fbAddType == 3 }">
	                                                			checked="checked"
	                                                		</c:if>
		                                                name="fbAddType" class="custom-control-input" id="multiType"><span class="custom-control-label"> ?????? </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="number" required="" name="fbAddQty" placeholder="??????" class="form-control" value="${fbVO.fbAddQty }">
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> ????????? ?????? ?????? </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" id="totalPrice"> 
	                                            	<c:if test="${fbVO.fbAddType == 3 }">
	                                                	?????? ??????
	                                                </c:if>
	                                                <c:if test="${fbVO.fbAddType != 3 }">
	                                                	?????? ??????
	                                                </c:if>
	                                            </label>
	                                            <div class="col-6 col-sm-4 col-lg-3">
	                                                <input type="number" 
	                                                	<c:if test="${fbVO.fbAddType == 3 }">
		                                                	placeholder="?????? ??????" 
		                                                </c:if>
		                                                <c:if test="${fbVO.fbAddType != 3 }">
		                                                	placeholder="?????? ??????" 
		                                                </c:if>
	                                                id="fbMinPrice" name="fbMinPrice" class="form-control" 
	                                                	<c:if test="${fbVO.fbTotalQtyFlag == true and fbVO.fbAddType == 3}">
	                                                		readonly="readonly"
	                                                	</c:if>
	                                                	
	                                                	<c:if test="${fbVO.fbTotalQtyFlag == true and fbVO.fbAddType != 3}">
	                                                		
	                                                	</c:if>
	                                                value="${fbVO.fbMinPrice }">
	                                                
	                                            </div>
	                                            
	                                            <div class="col-6 col-sm-4 col-lg-3" id="maxTotalPrice"
	                                            	<c:if test="${fbVO.fbAddType == 3 }">
	                                                	style="display:none;"
	                                                </c:if>
	                                            >
	                                                <input type="number" placeholder="?????? ??????" id="fbMaxPrice" 
	                                                	<c:if test="${fbVO.fbTotalQtyFlag == false and fbVO.fbAddType == 3}">
	                                                		readonly="readonly"
	                                                	</c:if>
	                                                	<c:if test="${fbVO.fbTotalQtyFlag == true and fbVO.fbAddType != 3}">
	                                                		
	                                                	</c:if>
	                                                name="fbMaxPrice" class="form-control" value="${fbVO.fbMaxPrice }">
	                                            </div>
	                                        </div>
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right" id="totalQty">
	                                            	<c:if test="${fbVO.fbAddType == 3 }">
	                                                	?????? ??????
	                                                </c:if>
	                                                <c:if test="${fbVO.fbAddType != 3 }">
	                                                	?????? ??????
	                                                </c:if>
	                                            </label>
	                                            <div class="col-6 col-sm-4 col-lg-2">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                <input type="radio" name="fbTotalQtyFlag" value="0" 
	                                                	<c:if test="${fbVO.fbTotalQtyFlag == false }">
	                                                		checked="checked"
	                                                	</c:if>
	                                                class="custom-control-input"><span class="custom-control-label"> ????????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" 
		                                                	<c:if test="${fbVO.fbTotalQtyFlag == true }">
	                                                		checked="checked"
	                                                	</c:if>
		                                                name="fbTotalQtyFlag" value="1" class="custom-control-input"><span class="custom-control-label"> ?????? </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="number" placeholder="?????? ??????" id="fbMinTotalQty" name="fbMinTotalQty" class="form-control" 
	                                                	<c:if test="${fbVO.fbTotalQtyFlag == false }">
	                                                		readonly="readonly"
	                                                	</c:if>
	                                                	<c:if test="${fbVO.fbTotalQtyFlag == true }">
	                                                		
	                                                	</c:if>
	                                                 value="${fbVO.fbMinTotalQty }">
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2" id="maxTotalQty"
	                                            	<c:if test="${fbVO.fbAddType == 3 }">
		                                                style="display:none;"
		                                            </c:if>
	                                            >
	                                            
	                                                <input type="number" placeholder="?????? ??????" id="fbMaxTotalQty" name="fbMaxTotalQty" class="form-control"
	                                                	<c:if test="${fbVO.fbTotalQtyFlag == false }">
	                                                		readonly="readonly"
	                                                	</c:if>
	                                                	<c:if test="${fbVO.fbTotalQtyFlag == true }">
	                                                		
	                                                	</c:if>
	                                                 value="${fbVO.fbMaxTotalQty }">
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                            <div class="card">
	                                <h5 class="card-header"> 
	                                	?????? ?????? 
	                                	<button type="button" class="fas fa-search" data-toggle="tooltip" data-placement="top" title="????????? ?????? ?????? 1??? ????????? ,??? ???????????? ???????????? ????????? ?????? ??? ????????????"></button>	
	                                </h5>
	                                <div class="card-body">
	                                    <div id="validationform">
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ?????? ?????? 1</label>
	                                            <div class="col-6 col-sm-4 col-lg-2">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" name=fbAnotherCheckFlag 
	                                                		<c:if test="${fbVO.fbAnotherCheckFlag == false }">
		                                                		checked="checked"
		                                                	</c:if>
	                                                	class="custom-control-input" value="0"><span class="custom-control-label"> ????????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" 
		                                                	<c:if test="${fbVO.fbAnotherCheckFlag == true }">
		                                                		checked="checked"
		                                                	</c:if>
		                                                name="fbAnotherCheckFlag" class="custom-control-input" value="1"><span class="custom-control-label"> ?????? </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckList" name="fbAnotherCheckList"
	                                                	<c:if test="${fbVO.fbAnotherCheckFlag == false }">
		                                                	disabled="disabled"
		                                                </c:if>
	                                                >
	                                                	<option value="0"
	                                                		<c:if test="${fbVO.fbAnotherCheckList == 0 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>????????? ?????????</option>
	                                                	<option value="1"
	                                                		<c:if test="${fbVO.fbAnotherCheckList == 1 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>????????? ?????????</option>
	                                                	<option value="2"
	                                                		<c:if test="${fbVO.fbAnotherCheckList == 2 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>????????? ????????????</option>
	                                                	<option value="3"
	                                                		<c:if test="${fbVO.fbAnotherCheckList == 3 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ?????????</option>
	                                                	<option value="4"
	                                                		<c:if test="${fbVO.fbAnotherCheckList == 4 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ?????????</option>
	                                                	<option value="5"
	                                                		<c:if test="${fbVO.fbAnotherCheckList == 5 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>??????</option>
	                                                	<option value="6"
	                                                		<c:if test="${fbVO.fbAnotherCheckList == 6 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>???????????????1</option>
	                                                	<option value="7"
	                                                		<c:if test="${fbVO.fbAnotherCheckList == 7 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>???????????????2</option>
	                                                	<option value="8"
	                                                		<c:if test="${fbVO.fbAnotherCheckList == 8 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>???????????????3</option>
	                                                </select>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="text" placeholder="?????????" id="fbAnotherCheckWord" name="fbAnotherCheckWord" class="form-control" 
	                                                	<c:if test="${fbVO.fbAnotherCheckFlag == false }">
		                                                	readonly="readonly"
		                                                </c:if>
	                                                value="${fbVO.fbAnotherCheckWord }">
	                                            </div>
	                                             <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckType" name="fbAnotherCheckType"
	                                                	<c:if test="${fbVO.fbAnotherCheckFlag == false }">
		                                                	disabled="disabled"
		                                                </c:if>
	                                                >
	                                                	<option value="0"
	                                                		<c:if test="${fbVO.fbAnotherCheckType == 0 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ?????? ??????</option>
	                                                	<option value="1"
	                                                		<c:if test="${fbVO.fbAnotherCheckType == 1 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ???</option>
	                                                	<option value="2"
	                                                		<c:if test="${fbVO.fbAnotherCheckType == 2 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>??????</option>
	                                                	<option value="3"
	                                                		<c:if test="${fbVO.fbAnotherCheckType == 3 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ??????</option>
	                                                </select>
	                                            </div>
	                                        </div>
	                                        
	                                        <div class="form-group row">
	                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> ????????? ?????? ?????? 2 </label>
	                                            <div class="col-6 col-sm-4 col-lg-2">
	                                                <label class="custom-control custom-radio custom-control-inline">
	                                                	<input type="radio" name=fbAnotherCheckFlag2 
	                                                		<c:if test="${fbVO.fbAnotherCheckFlag2 == false }">
		                                                		checked="checked"
		                                                	</c:if>
	                                                	class="custom-control-input" value="0"><span class="custom-control-label"> ????????? </span>
		                                            </label>
		                                            <label class="custom-control custom-radio custom-control-inline">
		                                                <input type="radio" 
		                                                	<c:if test="${fbVO.fbAnotherCheckFlag2 == true }">
		                                                		checked="checked"
		                                                	</c:if>
		                                                name="fbAnotherCheckFlag2" class="custom-control-input" value="1"><span class="custom-control-label"> ?????? </span>
		                                            </label>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckList2" name="fbAnotherCheckList2"
	                                                	<c:if test="${fbVO.fbAnotherCheckFlag2 == false }">
		                                                	disabled="disabled"
		                                                </c:if>
	                                                >
	                                                	<option value="0"
	                                                		<c:if test="${fbVO.fbAnotherCheckList2 == 0 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>????????? ?????????</option>
	                                                	<option value="1"
	                                                		<c:if test="${fbVO.fbAnotherCheckList2 == 1 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>????????? ?????????</option>
	                                                	<option value="2"
	                                                		<c:if test="${fbVO.fbAnotherCheckList2 == 2 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>????????? ????????????</option>
	                                                	<option value="3"
	                                                		<c:if test="${fbVO.fbAnotherCheckList2 == 3 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ?????????</option>
	                                                	<option value="4"
	                                                		<c:if test="${fbVO.fbAnotherCheckList2 == 4 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ?????????</option>
	                                                	<option value="5"
	                                                		<c:if test="${fbVO.fbAnotherCheckList2 == 5 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>??????</option>
	                                                	<option value="6"
	                                                		<c:if test="${fbVO.fbAnotherCheckList2 == 6 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>???????????????1</option>
	                                                	<option value="7"
	                                                		<c:if test="${fbVO.fbAnotherCheckList2 == 7 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>???????????????2</option>
	                                                	<option value="8"
	                                                		<c:if test="${fbVO.fbAnotherCheckList2 == 8 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>???????????????3</option>
	                                                </select>
	                                            </div>
	                                            <div class="col-3 col-sm-2 col-lg-2">
	                                                <input type="text" placeholder="?????????" id="fbAnotherCheckWord2" name="fbAnotherCheckWord2" class="form-control" 
	                                                	<c:if test="${fbVO.fbAnotherCheckFlag2 == false }">
		                                                	readonly="readonly"
		                                                </c:if>
	                                                value="${fbVO.fbAnotherCheckWord2 }">
	                                            </div>
	                                             <div class="col-3 col-sm-2 col-lg-2">
	                                                <select class="form-control" id="fbAnotherCheckType2" name="fbAnotherCheckType2"
	                                                	<c:if test="${fbVO.fbAnotherCheckFlag2 == false }">
		                                                	disabled="disabled"
		                                                </c:if>
	                                                >
	                                                	<option value="0"
	                                                		<c:if test="${fbVO.fbAnotherCheckType2 == 0 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ?????? ??????</option>
	                                                	<option value="1"
	                                                		<c:if test="${fbVO.fbAnotherCheckType2 == 1 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ???</option>
	                                                	<option value="2"
	                                                		<c:if test="${fbVO.fbAnotherCheckType2 == 2 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>??????</option>
	                                                	<option value="3"
	                                                		<c:if test="${fbVO.fbAnotherCheckType2 == 3 }">
	                                                			selected="selected"
	                                                		</c:if>
	                                                	>?????? ??????</option>
	                                                </select>
	                                            </div>
	                                        </div>
	                                        
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                    	<div class="offset-4 col-2 col-sm-3">	                    		
			                 	<button class="btn btn-primary btn-block"> ????????? ???????????? </button>
	                    	</div>
	                    </div>
                   </form>
           		</div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>