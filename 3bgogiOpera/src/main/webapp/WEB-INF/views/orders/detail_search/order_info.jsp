<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
		$(function(){

			$("input[name=datePeriod]").change(function(){
				datePeriod = $(this).val();
				
				if(datePeriod != 2){
					$("#dateStart").prop("readonly","readonly");
					$("#dateEnd").prop("readonly","readonly");
					
				}else{
					$("#dateStart").prop("readonly","");
					$("#dateEnd").prop("readonly","");
					
				}
				
				
			});
			
			$("#dateStart, #dateEnd").click(function(){
				$("#dateStart").prop("readonly","");
				$("#dateEnd").prop("readonly","");
				
			});
			
			$("#searchKeyword").focus();
			
			$(".table-3bgogi-hover  > tr").click(function(){
    			if($(this).next().hasClass('show')){
    				$(this).css("background-color","#fff");
    				
    			}else{
    				$(this).css("background-color","#FFC6C6");

    			}
    		});
			
			$('#dateStart').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d',
    			"setDate" : new Date(1985,10,01)
    			
    		});
			
    		$('#dateEnd').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		
    		$("#dateStart").click(function(){
    			$("#userSelect").prop("checked", true);
    		});
    		
    		$("#dateEnd").click(function(){
    			$("#userSelect").prop("checked", true);
    		});
    		

			$("#delivResultBtn").click(function(){
    			window.open("<c:url value='/security/epost/deliv_date.do'/>", "송장 결과" , "width=700, height=800, top=100, left=300, scrollbars=no");

    		});
			
			$("#cjDelivResultBtn").click(function(){
    			window.open("<c:url value='/delivery/cj_config/select_invoice_num.do'/>", "cj 송장 결과" , "width=700, height=800, top=100, left=300, scrollbars=no");

    		});

		});
		
		function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#searchCustomerInfo").submit();
		}
		
		function searchDeliveryState(urls, deliveryInvoiceNumber){
			window.open(urls+deliveryInvoiceNumber, "송장 조회" , "width=700, height=800, top=100, left=300, scrollbars=no");
		}
		
		
			
			
	
    </script>
    <style type="text/css">
    	.table-3bgogi-hover > tr :hover{
    		background: #FFC6C6;
    	}
    	
    </style>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid dashboard-content">
                    <!-- ============================================================== -->
                    <!-- pageheader -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h3 class="mb-2"> 주문 고객 상세 검색 </h3>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> C / S</a></li>
	                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 고객 검색 </a></li>
	                                        
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    	<form class="row" id="searchCustomerInfo" action="<c:url value='/order_detail/order_search.do'/>" method="POST">
                    		<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
                        	<input type="hidden" name="excelOrFk" value="0">
                        <!-- ============================================================== -->
                        <!-- search bar  -->
                        <!-- ============================================================== -->
		                        <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
		                        	<div class="card">
		                        		<div class="card-body">
		                        			<label for="" style="font-size: 13px;"> 날짜 관련 </label>
		                        			<div class="form-row">
			                        			<div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
			                        				<select class="form-control form-control-sm  mb-2" name="dateType">
									                    <option value="or_regdate"
									                    	<c:if test="${osVO.dateType == 'or_regdate' }">
									                    		selected="selected"
									                    	</c:if>
									                    >주문 등록일 기준</option>
									                    <option value="or_settlement_day"
									                    	<c:if test="${osVO.dateType == 'or_settlement_day' }">
									                    		selected="selected"
									                    	</c:if>
									                    >주문 결제일 기준</option>
									                    <option value="or_sending_day"
									                    	<c:if test="${osVO.dateType == 'or_sending_day' }">
									                    		selected="selected"
									                    	</c:if>
									                    >주문 발송일 기준</option>
									                    <option value="or_sending_deadline"
									                    	<c:if test="${osVO.dateType == 'or_sending_deadline' }">
									                    		selected="selected"
									                    	</c:if>
									                    >주문 발송기한 기준</option>
								                    </select>
			                        			</div>
			                        			<div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12">
			                        				<label class="custom-control custom-radio custom-control-inline  mb-2">
			                                        	<input type="radio" id="todays" name="datePeriod" value="0"
			                                        		<c:if test="${osVO.datePeriod == 0 }">
																 checked="checked"
															</c:if>
			                                        	class="custom-control-input"><span class="custom-control-label"> 오늘 </span>
			                                        </label>
			                                        <label class="custom-control custom-radio custom-control-inline  mb-2">
			                                        	<input type="radio" id="weeksAgo" name="datePeriod" value="1" 
			                                        		<c:if test="${osVO.datePeriod == 1 }">
																 checked="checked"
															</c:if>
			                                        	class="custom-control-input"><span class="custom-control-label"> 일주일 </span>
			                                        </label>
			                                        <label class="custom-control custom-radio custom-control-inline  mb-2">
			                                        	<input type="radio" id="userSelect" name="datePeriod" value="2" 
			                                        		<c:if test="${osVO.datePeriod == 2 }">
																 checked="checked"
															</c:if>
			                                        	class="custom-control-input"><span class="custom-control-label"> 임의 </span>
			                                        </label>
			                        			</div>
			                        			
			                        			<div class="col-xl-5 col-lg-5 col-md-12 col-sm-12 col-12">
			                        				<div class="form-row">			                        				
														<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
															<input type="text" class="form-control form-control-sm mb-2" id="dateStart" name="dateStart" style="text-align: center;"
																<c:if test="${osVO.datePeriod != 2 }">
																	 readonly="readonly"
																</c:if>  
															value="${osVO.dateStart }"/>
														</div>
														
														<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
															<input type="text" class="form-control form-control-sm mb-2" id="dateEnd" name="dateEnd" style="text-align: center;"
																<c:if test="${osVO.datePeriod != 2 }">
																	 readonly="readonly"
																</c:if>  
															value="${osVO.dateEnd }"/>
														</div>
			                        				</div>
			                        			</div>
		                        			</div>
		                        		</div>
		                        	</div>
		                        </div>
		                        
		                        <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
		                        	<div class="card">
		                        		<div class="card-body">
		                        			<label for="" style="font-size: 13px;"> 
		                        				검색 관련
		                        				<button type="button" class="fas fa-search" data-toggle="tooltip" data-placement="top" title="구매자/수령자통합과 주문번호는 ,를 구분자로 다중검색이 가능하나 주문번호는 정확하게 일치하는 값만 검색이 됩니다"></button> 
		                        			</label>
		                        			<div class="form-row">
			                        			<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
			                        				<div class="form-row">
			                        					<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
			                        						<select class="form-control form-control-sm mb-2" name="ssPk">
											                    <option value="0">전체 판매처</option>
											                    <c:forEach var="sslist" items="${ssList }">						                    	
												                    <option value="${sslist.ssPk }"
												                    	<c:if test="${osVO.ssPk == sslist.ssPk }">
												                    		selected="selected"
												                    	</c:if>
												                    > ${sslist.ssName }</option>
											                    </c:forEach>
										                    </select>
			                        					</div>
			                        					<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
			                        						<select class="form-control form-control-sm mb-2" name="searchType">
							                            		<option value="orderNames" 
											                    	<c:if test="${osVO.searchType == 'orderNames' }">
																		 selected="selected"
																	</c:if>
											                    >구매자 / 수령자 통합</option>
											                    <option value="buyerName" 
											                    	<c:if test="${osVO.searchType == 'buyerName' }">
																		 selected="selected"
																	</c:if>
											                    >구매자</option>
											                    <option value="receiverName"
											                    	<c:if test="${osVO.searchType == 'receiverName' }">
																		 selected="selected"
																	</c:if>
											                    >수령인</option>
											                    <option value="anotherName"
											                    	<c:if test="${osVO.searchType == 'anotherName' }">
																		 selected="selected"
																	</c:if>
											                    >송장표기명</option>
											                    <option value="buyerNum"
											                    	<c:if test="${osVO.searchType == 'buyerNum' }">
																		 selected="selected"
																	</c:if>
											                    >구매자 번호</option>
											                    <option value="receiverNum"
											                    	<c:if test="${osVO.searchType == 'receiverNum' }">
																		 selected="selected"
																	</c:if>
											                    >수령인 번호</option>
											                    <option value="orderNum"
											                    	<c:if test="${osVO.searchType == 'orderNum' }">
																		 selected="selected"
																	</c:if>
											                    >주문번호</option>
											                    <option value="receiverAddress"
											                    	<c:if test="${osVO.searchType == 'receiverAddress' }">
																		 selected="selected"
																	</c:if>
											                    >주소</option>
											                    <option value="orderProductNum"
											                    	<c:if test="${osVO.searchType == 'orderProductNum' }">
																		 selected="selected"
																	</c:if>
											                    >상품주문번호</option>
											                    <option value="matchingProduct"
											                    	<c:if test="${osVO.searchType == 'matchingProduct' }">
																		 selected="selected"
																	</c:if>
											                    >매칭 상품명</option>
											                    <option value="matchingOption"
											                    	<c:if test="${osVO.searchType == 'matchingOption' }">
																		 selected="selected"
																	</c:if>
											                    >매칭 옵션명</option>
											                    <option value="storeProduct"
											                    	<c:if test="${osVO.searchType == 'storeProduct' }">
																		 selected="selected"
																	</c:if>
											                    >판매처 상품명(옵션명)</option>
											                    <option value="invoiceNum"
											                    	<c:if test="${osVO.searchType == 'invoiceNum' }">
																		 selected="selected"
																	</c:if>
											                    >송장번호</option>
											                    <option value="invoiceNum2"
											                    	<c:if test="${osVO.searchType == 'invoiceNum2' }">
																		 selected="selected"
																	</c:if>
											                    >송장번호 아닌 것</option>
											                    <option value="orOutPutDate"
											                    	<c:if test="${osVO.searchType == 'orOutPutDate' }">
																		 selected="selected"
																	</c:if>
											                    >발송기한</option>
											                    <option value="deliveryMessage"
											                    	<c:if test="${osVO.searchType == 'deliveryMessage' }">
																		 selected="selected"
																	</c:if>
											                    >요청사항</option>
										                    </select>
			                        					</div>
			                        				</div>
			                        			</div>
			                        			<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12">
			                        				<div class="form-row">
			                        					<div class="col-xl-8 col-lg-8 col-md-12 col-sm-12 col-12">
			                        						<div class="input-group">			                        						
				                        						<input class="form-control form-control-sm mb-2" id="searchKeyword" name="searchKeyword" type="text"  placeholder="검색 내용을 입력해주세요" value="${osVO.searchKeyword }">
				                        						<div class="input-group-append">				                        						
							                                		<button class="btn btn-primary btn-xs mb-2" type="submit"> 검색 </button>
							                                		
				                        						</div>
			                        						</div>
			                        					</div>
			                        					<div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12">
			                        						<select class="form-control form-control-sm mb-2" name="recordCountPerPage">
																<option value="10"
																	<c:if test="${osVO.recordCountPerPage == 10 }">
																		selected="selected"
																	</c:if>
																>10 개씩 보기</option>
																<option value="20"
																	<c:if test="${osVO.recordCountPerPage == 20 }">
																		selected="selected"
																	</c:if>
																>20 개씩 보기</option>
																<option value="100"
																	<c:if test="${osVO.recordCountPerPage == 100 }">
																		selected="selected"
																	</c:if>
																>100 개씩 보기</option>
																<option value="200"
																	<c:if test="${osVO.recordCountPerPage == 200 }">
																		selected="selected"
																	</c:if>
																>200 개씩 보기</option>
																<option value="500"
																	<c:if test="${osVO.recordCountPerPage == 500 }">
																		selected="selected"
																	</c:if>
																>500 개씩 보기</option>
																<option value="1000"
																	<c:if test="${osVO.recordCountPerPage == 1000 }">
																		selected="selected"
																	</c:if>
																>1000 개씩 보기</option>
																<option value="2000"
																	<c:if test="${osVO.recordCountPerPage == 2000 }">
																		selected="selected"
																	</c:if>
																>2000 개씩 보기</option>
																<option value="3000"
																	<c:if test="${osVO.recordCountPerPage == 3000 }">
																		selected="selected"
																	</c:if>
																>3000 개씩 보기</option>
																		<option value="4000"
																	<c:if test="${osVO.recordCountPerPage == 4000 }">
																		selected="selected"
																	</c:if>
																>4000 개씩 보기</option>
															</select>
			                        					</div>
			                        				</div>
			                        			</div>
		                        			</div>
		                        		</div>
		                        	</div>
		                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                            	<div class="card-body" style="padding-bottom: 5px;">
                            		<div class="row">
                                    	<div class="col-xl-5 col-lg-5 col-md-12 col-sm-12 col-12">
                                        	<label for="" style="font-size: 13px;"> 발송 관련 </label>
                                        	<div class="form-inline">
	                                            <select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.outputPosiv != 0 }">
				                            			selected-values
				                            		</c:if>
				                            	" name="outputPosiv">
				                            		<option value="0"
				                            			<c:if test="${osVO.outputPosiv == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>출고 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.outputPosiv == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>출고 가능</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.outputPosiv == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>출고 불가</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.deliveryInvoiceFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="deliveryInvoiceFlag">
				                            		<option value="0"
				                            			<c:if test="${osVO.deliveryInvoiceFlag == 0 }">
															selected="selected"
														</c:if>
				                            		>송장 출력 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.deliveryInvoiceFlag == 1 }">
															selected="selected"
														</c:if>
				                            		>송장 있는 건</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.deliveryInvoiceFlag == 2 }">
															selected="selected"
														</c:if>
				                            		>송장 없는 건</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.reservationType != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="reservationType" >
				                            		<option value="0"
				                            			<c:if test="${osVO.reservationType == 0 }">
															selected="selected"
														</c:if>
				                            		> 예약 여부 </option>
				                            		<option value="1"
				                            			<c:if test="${osVO.reservationType == 1 }">
															selected="selected"
														</c:if>
				                            		> 예약 건</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.reservationType == 2 }">
															selected="selected"
														</c:if>
				                            		> 예약아닌 건</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
						                        	<c:if test="${osVO.receiveType != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
						                        " name="receiveType"  >
							                        <option value="0"
							                        	<c:if test="${osVO.receiveType == 0}">
															selected="selected"
														</c:if>
							                        > 상품수령타입 </option>
													<option value="1"
														<c:if test="${osVO.receiveType == 1}">
															selected="selected"
														</c:if>
													>택배</option>
													<option value="3"
														<c:if test="${osVO.receiveType == 3}">
															selected="selected"
														</c:if>
													>방문수령</option>
													<option value="2"
														<c:if test="${osVO.receiveType == 2}">
															selected="selected"
														</c:if>
													>퀵서비스</option>
													
													<option value="4"
														<c:if test="${osVO.receiveType == 4}">
															selected="selected"
														</c:if>
													>제주익일특급</option>
												</select>&nbsp;
												<select class="form-control form-control-sm mb-2 
						                        	<c:if test="${osVO.edtFk != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
						                        " name="edtFk"  >
							                        <option value="0"> 택배사 </option>
													<option value="1">우체국택배</option>
													<option value="3">새벽배송(프레시솔루션)</option>
													<option value="5">CJ새벽배송</option>
												</select>&nbsp;
												<select class="form-control form-control-sm mb-2 
						                        	<c:if test="${osVO.sendingReq != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
						                        " name="sendingReq"  >
							                        <option value="0"
							                        	<c:if test="${osVO.sendingReq == 0}">
															selected="selected"
														</c:if>
							                        > 강제출고요청 </option>
													<option value="1"
														<c:if test="${osVO.sendingReq == 1}">
															selected="selected"
														</c:if>
													> 요청 있는 건</option>
													<option value="1"
														<c:if test="${osVO.sendingReq == 1}">
															selected="selected"
														</c:if>
													> 요청 처리된 건</option>
												</select>
                                        	</div>
                                        </div>
                                    	<div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
                                        	<label for="" style="font-size: 13px;"> 주문서 상태 </label>
                                        	<div class="form-inline">
                                        		<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.depositFlag != 0 }">
				                            			selected-values
				                            		</c:if>
				                            	" name="depositFlag" >
				                            		<option value="0"
				                            			<c:if test="${osVO.depositFlag == 0 }">
															selected="selected"
														</c:if>
				                            		> 입금 여부 </option>
				                            		<option value="1"
				                            			<c:if test="${osVO.depositFlag == 1 }">
															selected="selected"
														</c:if>
				                            		> 입금 완료</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.depositFlag == 2 }">
															selected="selected"
														</c:if>
				                            		> 입금 대기</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.invenFlag != 0 }">
				                            			selected-values
				                            		</c:if>
				                            	" name="invenFlag" >
				                            		<option value="0"
				                            			<c:if test="${osVO.invenFlag == 0 }">
															selected="selected"
														</c:if>
				                            		> 재고 할당 여부 </option>
				                            		<option value="1"
				                            			<c:if test="${osVO.invenFlag == 1 }">
															selected="selected"
														</c:if>
				                            		> 재고 미할당</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.invenFlag == 2 }">
															selected="selected"
														</c:if>
				                            		> 재고 할당 완료 </option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2
				                            		<c:if test="${osVO.refundFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="refundFlag" >
				                            		<option value="0"
				                            			<c:if test="${osVO.refundFlag == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>환불 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.refundFlag == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>환불만</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.refundFlag == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>환불 아닌</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.cancledFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="cancledFlag" >
				                            		<option value="0"
				                            			<c:if test="${osVO.cancledFlag == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>주문취소 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.cancledFlag == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>미취소 주문</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.cancledFlag == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>취소 포함</option>
				                            		<option value="3"
				                            			<c:if test="${osVO.cancledFlag == 3 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>취소만</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.excelFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="excelFlag" >
				                            		<option value="0"
				                            			<c:if test="${osVO.excelFlag == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.excelFlag == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 원본파일</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.excelFlag == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 대상파일</option>
				                            		<option value="3"
				                            			<c:if test="${osVO.excelFlag == 3 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 원본,대상파일</option>
				                            		<option value="4"
				                            			<c:if test="${osVO.excelFlag == 4 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>대량주소 원본파일 제외</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2 
				                            		<c:if test="${osVO.specialRegionFlag != 0 }">
				                            			selected-values
				                            				
				                            		</c:if>
				                            	" name="specialRegionFlag">
				                            		<option value="0"
				                            			<c:if test="${osVO.specialRegionFlag == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역 여부</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.specialRegionFlag == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역</option>
				                            		<option value="2"
				                            			<c:if test="${osVO.specialRegionFlag == 2 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역 체크 안된</option>
				                            		<option value="3"
				                            			<c:if test="${osVO.specialRegionFlag == 3 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역 체크 된</option>
				                            		<option value="4"
				                            			<c:if test="${osVO.specialRegionFlag == 4 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>특수지역이 아닌</option>
				                            	</select>&nbsp;
				                            	<select class="form-control form-control-sm mb-2
				                            		<c:if test="${osVO.delivMsg != 0 }">
				                            			selected-values
				                            		</c:if>
				                            	" name="delivMsg" >
				                            		<option value="0"
				                            			<c:if test="${osVO.delivMsg == 0 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>배송메세지</option>
				                            		<option value="1"
				                            			<c:if test="${osVO.delivMsg == 1 }">
				                            				selected="selected"
				                            			</c:if>
				                            		>배송메세지 있는 건</option>
				                            	</select>
                                        	</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                    	<div class="col-xl-5 col-lg-5 col-md-12 col-sm-12 col-12">
                                        	<label for="" style="font-size: 13px;"> 주문입력차수, 송장생성차수 </label>
                                        	<div class="form-inline">
                                        		<c:set var="insertingCountNum" value="1"/>
                                        		
                                        		<select class="selectpicker form-control form-control-sm mb-2" id="createRegdateList" name="createRegdateList" multiple data-actions-box="true" 
													data-width="300px" title="주문 입력 전체 차수">
													<c:if test="${empty insertStoreOrderList }">
														<option disabled> 금일 입력된 주문서가 없습니다 </option>
													</c:if>
							                        <c:if test="${!empty insertStoreOrderList }">
														<option disabled> 주문입력차수 </option>
														<c:forEach var="isoList" items="${insertStoreOrderList }">
															
															<fmt:formatDate var='isoRegdate' value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/>
															
															<option value="<fmt:formatDate value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/>"
																
																<c:if test="${!empty osVO.createRegdateList }">
															
																	<c:forEach var="selectedRegdate" items="${osVO.createRegdateList }">												
																		<c:if test="${selectedRegdate == isoRegdate }">
																			selected="selected"
																			
																		</c:if>
																		
																	</c:forEach>
																</c:if>

															> ${insertingCountNum } 차 ${isoList.ssName }  <fmt:formatDate value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/></option>
															
															<c:set var="insertingCountNum" value="${insertingCountNum + 1 }"/>
														</c:forEach>
														
							                        </c:if>
													
												</select>&nbsp;
												
												
												<c:set  var="invoiceCountNum" value="1"/>
												<select class="selectpicker form-control-sm mb-2" multiple data-actions-box="true" 
													data-width="300px" id="createInvoiceNumList" name="createInvoiceNumList"  title="송장 입력 전체 차수">
									            	<c:if test="${empty invoiceNum }">
									                	<option disabled> 금일 생성된 송장이 없습니다 </option>
									                </c:if>
									                <c:if test="${!empty invoiceNum }">
									                	<option disabled>송장생성차수 </option>
									                	<c:forEach var="invoiceNumList" items="${invoiceNum }">
															<option value="${invoiceNumList.orInvoiceNumDate }"
															
																<c:if test="${!empty osVO.createInvoiceNumList }">
																
																	<c:forEach var="invoiceNums" items="${osVO.createInvoiceNumList }">				
																													
																		<c:if test="${invoiceNumList.orInvoiceNumDate  == invoiceNums }">
																			selected="selected"
																		</c:if>
																		
																	</c:forEach>
																</c:if>
															>송장 ${invoiceCountNum} 차 ( ${invoiceNumList.totalOrderCount } 장 ) ${invoiceNumList.orDeliveryCompany} ${invoiceNumList.orInvoiceNumDate } </option>
															<c:set  var="invoiceCountNum" value="${invoiceCountNum + 1 }"/>
														</c:forEach>
									                </c:if>
								                </select>
                                        	</div>
                                        </div>
                                    </div>
                            	</div>
                            </div>
                        </div>
                        
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            	<div class="card" id="orderController">
                            		<div class="form-inline" style="display: table;">
                            			<div class="form-group" style="display: table-cell; text-align: left">  
	                                		<h5 class="card-header"> 주문서 검색 목록 ( 총 : ${osVO.totalRecord } 개 )</h5>
	                                	</div>
                            		</div>
                            		<div class="card-header" id="headingSeven">
                                    	<h5 class="mb-0">
                                        	<button id="arcoBtn1" class="btn btn-link" data-toggle="collapse" data-target="#orderControl" aria-expanded="false" aria-controls="orderControl" type="button">
                                            	주문서 제어
                                            </button>
                                        </h5>
                                    </div>
                                    <div id="orderControl" class="collapse" aria-labelledby="headingSeven" data-parent="#orderController">
                                    	<div class="card-body">
	                                		<button class="btn btn-success btn-xs mb-2 createNewOrder" type="button" id="createNewOrder"> 새주문생성 </button>
	                                		<button class="btn btn btn-brand btn-xs mb-2" type="button" id="doorPassButton">공동현관 비밀번호 입력</button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="addProductButton"> 상품추가 </button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="aligoSmsBtn"> 문자 보내기 </button>
	                                		<button class="btn btn-primary btn-xs mb-2" type="button" id="combineOrderButton"> 주문서 합치기 및 정보변경</button>
	                                		<button class="btn btn-danger btn-xs mb-2" type="button" id="deleteDelivButton"> 송장 삭제 </button>
	                                		<button class="btn btn-danger btn-xs mb-2" type="button" id="deleteOrderButton" style="text-align: right;"> 주문서 일괄 삭제 </button>
                                        </div>
                                   </div>
                                	<div class="card-header" id="headingSeven">
                                    	<h5 class="mb-0">
                                        	<button id="arcoBtn" class="btn btn-link" data-toggle="collapse" data-target="#orderDetailControl" aria-expanded="false" aria-controls="orderDetailControl" type="button">
                                            	주문서 개별 제어
                                            </button>
                                        </h5>
                                    </div>
                                    <div id="orderDetailControl" class="collapse" aria-labelledby="headingSeven" data-parent="#orderController">
                                    	<div class="card-body">
											<button class="btn btn-danger btn-xs" id="deleteOrderTargetingDeleteBtn" type="button"> 주문 삭제 </button>
											<button class="btn btn-primary btn-xs" id="changeProductBtn" type="button"> 상품 변경 </button>
											<button class="btn btn-primary btn-xs" id="cancleOrderPart" type="button"> 주문 취소 </button>
											<button class="btn btn-primary btn-xs" id="cancleOrderRestore" type="button"> 취소 주문 복구 </button>
                                        </div>
                                   </div>
                                </div>
                            	<div class="card">
	                                <div class="card-body">
	                                	<div class="table-responsive">
		                                    <table class="table table-bordered">
		                                        <thead>
		                                            <tr style="text-align: center;">
		                                            	<th scope="col" class="serialNumTable">
					                                        <label class="custom-control custom-checkbox be-select-all" style="display: inline-block;">
											             	    <input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="orSeiralSpecialNumberAllSelect"><span class="custom-control-label"></span>
											                </label>
				                                        </th>
				                                        <th scope="col">
		                                            		판매처/송장번호
		                                            	</th>
		                                                <th scope="col"> 구매자 / 수령자</th>
		                                                <th scope="col"> 주소 </th>
		                                                <th scope="col" class="detailOrPkList">
					                                        <label class="custom-control custom-checkbox be-select-all" style="display: inline-block;">
											             	   <input class="custom-control-input chk_or_pk_all" type="checkbox" name="chk_or_pk_all" id="orPkAllSelect"><span class="custom-control-label"></span>
											                </label>
				                                        </th>
				                                        
				                                        <th scope="col"> 작업기록</th>
		                                                <th scope="col"> 배송메세지 / 공동현관</th>
		                                                <th scope="col"> 구매 상품 </th>
		                                                <th scope="col"> 매칭 상품 </th>
		                                                <th scope="col"> 총 합 </th>
		                                            </tr>
		                                        </thead>
		                                        <tbody class="dataTable">
		                                        	<c:set var="tableCountings" value="1"/>
		                                        	<c:set var="numCounting" value="${OrderSearchVO.firstRecordIndex + 1 }"/>
		                                        	<c:set var="rowCounting" value="1"/>
			                                        	<c:set var="backgroundBoolean" value="1"/>
			                                        	
			                                        	<c:forEach var="orlist" items="${orderList }">
			                                        		
			                                        		<c:set var="totalProductQty" value="0" />
			                                        		<c:set var="rowSpans" value="${fn:length(orlist.orVoList)}"/>
			                                        		<c:forEach var="stocklist" items="${orlist.orVoList }">
			                                        			<tr
			                                        			<c:if test="${backgroundBoolean % 2 == 0}">
					                                        		style="background-color:#edeef4;"
					                                        	</c:if>
			                                        			>
			                                        			<c:if test="${rowCounting == 1 }">
			                                        				<td rowspan="${rowSpans }" style="width:20px; text-align: center;" data-table-info="${tableCountings }" class="serialNumTable">
			                                        					<label class="custom-control custom-checkbox be-select-all">
														             	   <input class="custom-control-input chk_all" value="${orlist.orSerialSpecialNumber }" 
														             	   <c:if test="${empty orlist.orDeliveryInvoiceNumber and empty orlist.orSendingDay }">
										                                		data-deliv-weiting="1" 
										                                	</c:if>
										                                	<c:if test="${!empty orlist.orDeliveryInvoiceNumber and empty orlist.orSendingDay }">
										                                		data-deliv="1" data-deliv-num="${orlist.orDeliveryInvoiceNumber }" data-deliv-company="${orlist.orDeliveryCompany }"
										                                	</c:if>
										                                	<c:if test="${!empty orlist.orDeliveryInvoiceNumber and !empty orlist.orSendingDay }">
										                                		data-output="1" data-deliv-company="${orlist.orDeliveryCompany }"
										                                	</c:if>										                              		      	
										                                	      	
										                                	data-buyer-name="${orlist.orBuyerName }"
										                                	data-buyer-contract-number="${orlist.orBuyerContractNumber1 }"
														             	   type="checkbox" name="orSerialSpecialNumberList"
														             	   >
														             	   <span class="custom-control-label"></span>
														                </label>
										                            </td>
										                            
										                            
										                            <td rowspan="${rowSpans }" class="customerInfo" style="width:170px; text-align: center;" data-table-info="${tableCountings }">
										                            	${orlist.ssName }
										                            	<c:if test="${empty orlist.orDeliveryInvoiceNumber }">
										                            		<br> <span style="font-size: 12px; color:red;">송장 기입 전</span>
										                            	</c:if>
										                            	<c:if test="${!empty orlist.orDeliveryInvoiceNumber }">
										                            		<br> ${orlist.orDeliveryCompany }
										                            		<br> ${orlist.orDeliveryInvoiceNumber }
										                            	</c:if>
										                            </td>
					                                        		<td rowspan="${rowSpans }" style="width:260px; text-align: center;" data-table-info="${tableCountings }">
										                            	<p style="margin-bottom: 5px;">${orlist.orBuyerName } / ${orlist.orBuyerContractNumber1 }<br>
										                                ${orlist.orReceiverName } / ${orlist.orReceiverContractNumber1 }</p>
										                                <c:if test="${empty orlist.orDeliveryInvoiceNumber }">
										                            		<button class="btn btn-outline-success btn-xs devideOrderButton" type="button" value="${orlist.orSerialSpecialNumber }">주문서 분리</button>
										                            	</c:if>
										                                
										                            </td>
										                            <td rowspan="${rowSpans }" style="width:300px; text-align: center;" data-table-info="${tableCountings }">
										                            	<p style="margin-bottom: 5px;">${orlist.orShippingAddress } ${orlist.orShippingAddressDetail }</p>
										                            	<c:if test="${empty orlist.orDeliveryInvoiceNumber }">
											                            	<button class="btn btn-outline-success btn-xs editCustomerInfoBtn" type="button" value="${orlist.orSerialSpecialNumber }">주소 변경</button>
										                            	</c:if>
										                            	
										                            </td>
					                                        	</c:if>
					                                        	<td style="border: 1px solid lightgray; width:20px;" class="productDetail detailOrPkList" data-table-product-detail="${tableCountings }">
												                	<label class="custom-control custom-checkbox be-select-all">
																    <input class="custom-control-input chk_all" value="${stocklist.orPk }" 
																    	type="checkbox" name="orPks"
																    	
																    	<c:if test="${stocklist.orCancledFlag == false }">
																    		data-cancled='false'
																    	</c:if>
																    	<c:if test="${stocklist.orCancledFlag == true }">
																    		data-cancled='true'
																    	</c:if>
																    	
																    	data-sending-day="${stocklist.orSendingDay }"
																    	data-invoice-num="${sotcklist.orDeliveryInvoiceNumber }"
																    	
																    >
																    <span class="custom-control-label"></span>
																    </label>
																    
												                </td>
												                <td style="border: 1px solid lightgray; width:20px;" class="productDetail detailOrPkList" data-table-product-detail="${tableCountings }">
																    <button class="btn btn-outline-success btn-xs" type="button" name="orderHistoryShow" data-orpk="${stocklist.orPk }" value="${orlist.orSerialSpecialNumber }">작업기록</button>
																    
												                </td>
					                                        	<c:if test="${!empty stocklist.productMatchingVOList }">	                                        			
					                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }"> 
															                 <td style="border: 1px solid lightgray; width:430px;" class="productInfo" data-table-product-info="${tableCountings }">
																				<span style="font-size: 12px;"> ${stocklist.orDeliveryMessage } </span>
																				 <hr> ${stocklist.orDelivEnter }
																				 
							                                        		</td>
							                                        		
							                                        		<td style="border: 1px solid lightgray; width:430px;" class="productInfo" data-table-product-info="${tableCountings }">
							                                        			<c:if test="${stocklist.orCancledFlag == false }">							                                        			
																					<span style="font-size: 12px;"> ${stocklist.orProduct }</span><br>
																					<span style="font-size: 12px;"> ${stocklist.orProductOption } </span><a data-or-pk="${stocklist.orPk }" class="SelfdevisionOrderBtn" style="color:red; font-size:10px"> [ 나누기 ]</a>
							                                        			</c:if>
							                                        			
							                                        			
							                                        			<c:if test="${stocklist.orCancledFlag == true }">							                                        			
																					<del style="font-size: 12px;"> ${stocklist.orProduct }</del><br>
																					<del style="font-size: 12px;"> ${stocklist.orProductOption } </del>
							                                        			</c:if>
							                                        		</td>
					                                        		</c:forEach>
			                                        			</c:if>
			                                        			<c:if test="${empty stocklist.productMatchingVOList }">	                                        			
					                                        		<td style="border: 1px solid lightgray; width:430px;" class="productInfo" data-table-product-info="${tableCountings }">
																		<span style="font-size: 12px;"> ${stocklist.orDeliveryMessage } </span>
																		 <hr> ${stocklist.orDelivEnter }
																		 
							                                        </td>
							                                        <td style="border: 1px solid lightgray; width:430px;" class="productInfo" data-table-product-info="${tableCountings }">
																		<c:if test="${stocklist.orCancledFlag == false }">							                                        			
																			<span style="font-size: 12px;"> ${stocklist.orProduct }</span><br>
																			<span style="font-size: 12px;"> ${stocklist.orProductOption } </span><a data-or-pk="${stocklist.orPk }" class="SelfdevisionOrderBtn" style="color:red; font-size:10px"> [ 나누기 ]</a>
							                                        	</c:if>
							                                        	<c:if test="${stocklist.orCancledFlag == true }">							                                        			
																			<del style="font-size: 12px;"> ${stocklist.orProduct }</del><br>
																			<del style="font-size: 12px;"> ${stocklist.orProductOption } </del>
							                                        	</c:if>							
							                                        </td>
							                                         <td style="border: 1px solid lightgray; width:430px;" class="productInfo" data-table-product-info="${tableCountings }">
							                                         	
							                                         	<c:if test="${stocklist.orCancledFlag == false }">							                                        			
																			매칭된 상품이 존재하지 않습니다.
							                                        	</c:if>
							                                        	<c:if test="${stocklist.orCancledFlag == true }">							                                        			
																			<span style="font-size: 12px; color: red;">해당 상품은 취소처리되었습니다</span>
							                                        	</c:if>	
							                                        </td>
							                                        
							                                        <td style="width:70px;">
							                                        	${stocklist.orAmount } 개
							                                        </td>
			                                        			</c:if>
			                                        			<c:if test="${!empty stocklist.productMatchingVOList }">
					                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }"> 
							                                        		<td style="border: 1px solid lightgray; width:430px;" class="productDetail" data-table-product-detail="${tableCountings }">
																				<c:if test="${!empty pmlist.optionMatchingVOList }">																		
									                                        		<c:forEach var="opmlist" items="${pmlist.optionMatchingVOList }">
										                                        		<c:forEach var="proOplist" items="${opmlist.productOptionList }">
										                                        			<c:if test="${!empty proOplist.optionName }">	
										                                        				<c:if test="${stocklist.orCancledFlag == false }">							                                        			
																									<span style="font-size: 12px;">${proOplist.productName } [ ${proOplist.optionName } ] ${proOplist.cfFk } 개</span><br>
											                                        				<c:set var="totalProductQty" value="${totalProductQty + proOplist.cfFk }"/>
													                                        	</c:if>
													                                        	<c:if test="${stocklist.orCancledFlag == true }">							                                        			
																									<span style="font-size: 12px; color: red;">해당 상품은 취소처리되었습니다</span>
													                                        	</c:if>					
													                                        				                                        			
										                                        			</c:if>
										                                        		</c:forEach>
									                                        		</c:forEach>
																				</c:if>
																				<c:if test="${empty pmlist.optionMatchingVOList }">
																					<c:if test="${stocklist.orCancledFlag == false }">							                                        			
																						옵션 매칭이 되지 않았습니다.
										                                        	</c:if>
										                                        	<c:if test="${stocklist.orCancledFlag == true }">							                                        			
																						<span style="font-size: 12px; color: red;">해당 상품은 취소처리되었습니다</span>
										                                        	</c:if>	
																				</c:if>
							                                        		</td>
							                                        		<c:if test="${!empty pmlist.optionMatchingVOList }">
																				<td class="customerInfo totalProductQty" style="width:70px;"  data-table-info="${tableCountings }" data-total-qty="${totalProductQty }">
													                            	${totalProductQty } 개 
													                            </td>
																			</c:if>
																			<c:if test="${empty pmlist.optionMatchingVOList }">
																				<td class="customerInfo totalProductQty" style="width:70px;" data-table-info="${tableCountings }" data-total-qty="${totalProductQty }">
													                            	${stocklist.orAmount } 개
													                            </td>
																			</c:if>
							                                        		
					                                        		</c:forEach>
			                                        			</c:if>
			                                        		</tr>
			                                        		<c:set var="rowCounting" value="${rowCounting+1 }"/>
			                                        		</c:forEach>
				                                        	<c:set var="backgroundBoolean" value="${backgroundBoolean + 1 }"/>
				                                        	<c:set var="rowCounting" value="1"/>
				                                        	<c:set var="numCounting" value="${numCounting + 1 }"/>
				                                        	<c:set var="tableCountings" value="${tableCountings + 1 }"/>
				                                        	<c:set var="totalProductQty" value="0"/>	                                        		
			                                        	</c:forEach>
		                                        </tbody>
		                                    </table>
	                                	</div>
	                                </div>
	                            </div>
                            </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<nav aria-label="Page navigation" style="text-align: center;" >
									<ul class="pagination" style="display: table; margin-left: auto; margin-right: auto;">
									
										<c:if test="${osVO.firstPage>1 }">
											<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.firstPage-1})">«</a></li>
										</c:if>
										<c:forEach var="i" step="1" end="${osVO.lastPage}" begin="${osVO.firstPage }">
											<li class="page-item
												<c:if test="${osVO.currentPage == i }">
													active
												</c:if>
											"  style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
										</c:forEach>
										<c:if test="${osVO.lastPage < osVO.totalPage}">
											<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.lastPage+1})">»</a></li>
										</c:if>
									</ul>
								</nav>
							</div>
                    	</form>
                    </div>
    <iframe id="csSearchIframe" width="0" height="0" style="display: none;">            	
	</iframe> 
	
	<iframe id="excelDownloadIframe" width="0" height="0">
	</iframe>  
	<script src="${pageContext.request.contextPath}/resources/libs/js/renewal_order_detail_search_manage.js"></script>

	<script type="text/javascript">
	
	$(document).ready(function(e){
        genRowspan("totalProductQty");
		
        
    });
    
 	function goTop(){		 		
 		$('html').scrollTop(0);
 	}
 	
    function genRowspan(className){
    	
        $("." + className).each(function( i , selector) {
        	var alarmQty = $("select[name=totalQtyAlarm]").val();
        	
        	var values = $(selector).data("table-info");
            var rows = $("." + className+"[data-table-info='"+values+"']");
            
            if (rows.length > 1) {
                rows.eq(0).attr("rowspan", rows.length);
                
                if(rows.eq( (rows.length - 1)).data("total-qty") >= alarmQty && alarmQty != -1){
                	rows.eq(0).css("color", "red");
                	rows.eq(0).css("font-weight", "bolder");
                }
                
                rows.eq(0).text( rows.eq( (rows.length - 1) ).text() );
                rows.not(":eq(0)").prop("background-color","");
                
                rows.not(":eq(0)").remove();
                
                
            }else{
            	
            	if(rows.eq( (rows.length - 1)).data("total-qty") >= alarmQty && alarmQty != -1){
                	rows.eq(0).css("color", "red");
                	rows.eq(0).css("font-weight", "bolder");
                }
                
            	
            }
        });
    }
	</script>
	<%@ include file="../../inc/bottom.jsp" %>
        