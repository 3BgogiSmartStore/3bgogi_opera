<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>


<link rel="stylesheet" href="${resourcePath}/resources/libs/morris-bundle/morris.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/css/animsition.min.css" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/js/animsition.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>

<script src="${resourcePath}/resources/libs/morris-bundle/morris.js"></script>
<script src="${resourcePath}/resources/libs/morris-bundle/Morrisjs.js"></script>
<script src="${resourcePath}/resources/libs/morris-bundle/raphael.min.js"></script>

<script type="text/javascript">
	$(function(){
		
		settingDate = new Date(); 
		settingDate.setMonth(settingDate.getMonth()-1);
		
		dateStart = formatDate(settingDate);
		settingDate.setMonth(settingDate.getMonth()+3);
		
		dateEnd = formatDate(settingDate);
		
		$("#delivWeiting").click(function(){
			location.href='/orders/search/customer_orders.do?dateType=or_sending_deadline&datePeriod=2&dateStart='+dateStart+'&dateEnd='+dateEnd+'&outputPosiv=1&deliveryInvoiceFlag=2&cancledFlag=1&excelFlag=4&reservationType=0&refundFlag=2';
			
		});
		
		$("#sendingWeiting").click(function(){
			location.href='/orders/search/customer_orders.do?dateType=or_sending_deadline&datePeriod=2&dateStart='+dateStart+'&dateEnd='+dateEnd+'&outputPosiv=1&deliveryInvoiceFlag=1&cancledFlag=1&excelFlag=4&reservationType=2&refundFlag=2';
			
		});
		
		$("#sendingFinished").click(function(){
			location.href='/orders/search/customer_orders.do?dateType=or_sending_day&datePeriod=0&dateStart='+dateStart+'&dateEnd='+dateEnd+'&outputPosiv=2&deliveryInvoiceFlag=1&cancledFlag=1&excelFlag=4&reservationType=2&refundFlag=2';
			
		});
		
		
	});

</script>


<!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="dashboard-ecommerce">
                <div class="container-fluid dashboard-content ">
                    <!-- ============================================================== -->
                    <!-- pageheader  -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title"> 종합 처리 현황  </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader  -->
                    <!-- ============================================================== -->
                    <div class="ecommerce-widget">
                    	<%-- <c:set var="matching_fail" value="${deliveryResult[0].matching_fail == null ? 0 : deliveryResult[0].matching_fail }" />
	                    <c:set var="dont_grant_invoice_num" value="${deliveryResult[0].dont_grant_invoice_num == null ? 0 : deliveryResult[0].dont_grant_invoice_num }" />
	                    <c:set var="output_weiting_order" value="${deliveryResult[0].output_weiting_order == null ? 0 : deliveryResult[0].output_weiting_order }" />
	                    <c:set var="output_order" value="${deliveryResult[0].output_order == null ? 0 : deliveryResult[0].output_order }" />
	                    <c:set var="reserv_order" value="${deliveryResult[0].reserv_order == null ? 0 : deliveryResult[0].reserv_order }" />
	                    <c:set var="deposit_order" value="${deliveryResult[0].deposit_order == null ? 0 : deliveryResult[0].deposit_order }" /> --%>
	                    
	                    <c:set var="epost_weiting_v" value="${deliveryTypeResult.epost_weiting == null? 0 : deliveryTypeResult.epost_weiting }" />
	                    <c:set var="another_weiting_v" value="${deliveryTypeResult.another_weiting == null? 0 : deliveryTypeResult.another_weiting }" />
	                    <c:set var="epost_finished_v" value="${deliveryTypeResult.epost_finished == null? 0 : deliveryTypeResult.epost_finished }" />
	                    <c:set var="another_finished_v" value="${deliveryTypeResult.another_finished == null? 0 : deliveryTypeResult.another_finished }" />
	                    
                        <div class="row">
                        	 <!--  일반 통계 -->
                            <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body">
                                        <h5 class="text-muted"> 재고 미할당 (상품 미매칭 ) </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1">${matching_fail } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body">
                                        <h5 class="text-muted"> 미입금 주문서 </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1"> ${deposit_order } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body">
                                        <h5 class="text-muted" id="delivWeiting" style="cursor: pointer;"> 송장 대기중 (예약 포함) </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1">${dont_grant_invoice_num }</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body">
                                        <h5 class="text-muted" id="sendingWeiting" style="cursor: pointer;"> 발송 대기중 </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1"> ${output_weiting_order } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body" id="sendingFinished">
                                        <h5 class="text-muted" style="cursor: pointer;"> 오늘 발송 완료 </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1"> ${output_order } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body" id="sendingFinished">
                                        <h5 class="text-muted" > 우체국 대기 </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1"> ${epost_weiting_v } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body" id="sendingFinished">
                                        <h5 class="text-muted" > 그 외 택배사 대기 </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1"> ${another_weiting_v } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body" id="sendingFinished">
                                        <h5 class="text-muted" > <a href="<c:url value='/security/epost/deliv_date.do'/>" target="_blank">우체국 완료</a> </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1"> ${epost_finished_v } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
                                <div class="card border-3 border-top border-top-primary">
                                    <div class="card-body" id="sendingFinished">
                                        <h5 class="text-muted" > 그 외 택배사 완료 </h5>
                                        <div class="metric-value d-inline-block">
                                            <h1 class="mb-1"> ${another_finished_v } 건</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                        	<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                        	
                        		<div class="card">
                                    <h5 class="card-header"> 주문서 발송 개수</h5>
                                    <div class="card-body p-0">
                                    	<div id="sendingList"></div>
	                                    <div class="text-center">
	                                        <span class="legend-item mr-3">
	                                                <span class="fa-xs text-secondary mr-1 legend-tile"><i class="fa fa-fw fa-square-full"></i></span>
	                                        <span class="legend-text"> 발송기한 별 주문서 개수</span>
	                                        </span>
	                                        <p></p>
	                                    </div>
	                                    
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                        		<div class="card">
                                    <h5 class="card-header"> 예약된 주문서 개수</h5>
                                    <div class="card-body p-0">
                                    	<div id="sendingReservList"></div>
	                                    <div class="text-center">
	                                        <span class="legend-item mr-3">
	                                                <span class="fa-xs text-secondary mr-1 legend-tile"><i class="fa fa-fw fa-square-full"></i></span>
	                                        <span class="legend-text"> 발송기한 별 예약 주문서 개수</span>
	                                        </span>
	                                        <p></p>
	                                    </div>
	                                    
                                    </div>
                                </div>
                            </div>
                        	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="card">
                                    <h5 class="card-header"> 입고된 상품 내역 </h5>
                                    <div class="card-body p-0">
                                        <div class="table-responsive">
                                            <table class="table table-hover" style="table-layout: fixed; word-break: keep-all;">
                                            	<colgroup>
													<col width="100px" />
													<col width="100px" />
													<col width="80px" />
													<col width="50px" />
													<col width="180px" />
													<col width="180px" />
													<col width="180px" />
													<col width="180px" />
													<col width="150px" />
												</colgroup>
												
                                                <thead class="bg-light">
                                                    <tr class="border-0">
                                                        <th class="border-0">상품</th>
                                                        <th class="border-0">입고가</th>
                                                        <th class="border-0">입고량</th>
                                                        <th class="border-0">등급</th>
                                                        <th class="border-0">이력번호</th>
                                                        <th class="border-0">도축장</th>
                                                        <th class="border-0">원산지</th>
                                                        <th class="border-0">품목제조번호</th>
                                                        <th class="border-0">등록일</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:if test="${empty costInputList}">
                                                		<tr>
                                                			<td colspan="9"> 내역이 없습니다 </td>
                                                		</tr>
                                                	</c:if>
                                                	<c:if test="${!empty costInputList}">
														<c:forEach var="costInputlist" items="${costInputList }">														
		                                                    <tr>
		                                                        <td>${costInputlist.cdName }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciPrice }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciWeight }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciLevel }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciAnimalProdTraceNum }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciAbattoir }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciCountryOfOrigin }</td>
		                                                        <td>${costInputlist.costIoVOList[0].ciItemsManufNum }</td>
		                                                        <td><fmt:formatDate value="${costInputlist.costIoVOList[0].ciRegdate }"/></td>
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
            </div>
            <script src="${pageContext.request.contextPath}/resources/libs/test_js/popper.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/libs/bootstrap-4.1/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/libs/slick/slick.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/libs/wow/wow.min.js"></script>>
            <script src="${pageContext.request.contextPath}/resources/libs/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
            <script src="${pageContext.request.contextPath}/resources/libs/test_js/main.js"></script>
<script type="text/javascript">

</script>
        <%@ include file="../inc/bottom.jsp" %>