<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
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
				format:'Y-m-d'
			});
			
			$("#dateStart").click(function(){
				$("#userSelect").prop("checked", true);
			});
			
			$("#dateEnd").click(function(){
				$("#userSelect").prop("checked", true);
			});
		});
	    function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#productOrderMatchingForm").submit();
		}
	    
	    function replaceURL(url) {
	        url= url.replace(/&/g,"%26"); 
	        url= url.replace(/\+/g,"%2B"); 
	        return url;
	    }
	    
	    function productMatching(orPk,storeProductName,storeProductOption , pmPk, editFlag){
	    	
	    	var replaceName = replaceURL(storeProductName);
	    	var replaceOption = replaceURL(storeProductOption);
	    	
	    	window.open("<c:url value='/order/matching/options_matching.do?orPk="+orPk+"&orProduct="+encodeURIComponent(storeProductName)+"&orProductOption="+encodeURIComponent(storeProductOption)+"&pmFk="+pmPk+"&editFlag="+editFlag+"'/>", "주문서 옵션 매칭" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
	    	
	    }
		
    </script>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid  dashboard-content">
                <!-- ============================================================== -->
                <!-- pageheader -->
                <!-- ============================================================== -->
                <div class="row">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="page-header">
                            <h2 class="pageheader-title"> 상품 옵션 매칭 등록 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 입력 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 매칭 </a></li>
                                        
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader -->
                <!-- ============================================================== -->
                	<div class="row">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card" style="background-color: #efeff6;">
								<div class="card-body">
									<h4> 주문서 진행 단계 </h4>
									<a href="<c:url value='/orders/order_page.do'/>" class="btn btn-success"> 주문서 입력</a>
									<a href="<c:url value='/order/config/search_except_addr_order.do'/>" class="btn btn-success"> 특수 지역 체크  </a>
									<a href="<c:url value="/order/matching/products_matching.do"/>" class="btn btn-success"> 상품 매칭 </a> 
									<a href="<c:url value="/order/matching/option_matching.do"/>" class="btn btn-success blinking"> 옵션 매칭 </a>
									<a href="<c:url value='/config/freebie/apply.do'/>" class="btn"> 사은품 부여  </a>    
									<a href="<c:url value='/orders/delivery_msg_check.do'/>" class="btn"> 요구사항 체크 </a>
									<a href="<c:url value='/stock/stk_check.do'/>" class="btn"> 재고 할당 </a> 
									<a href="<c:url value='/orders/cancled_order_check.do'/>" class="btn"> 취소 주문  </a>
									<a href="<c:url value='/security/epost.do'/>" class="btn"> 송장 부여  </a> 
								</div>
							</div>
						</div>
					</div>
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        	<div class="card">
	                        	<div class="card-body">
	                        		<form name="productOrderMatchingForm" id="productOrderMatchingForm" action="<c:url value="/order/matching/option_matching.do"/>" method="get">
	                        			<input type="hidden" name="searchCondition" >
			                        	<input type="hidden" name="currentPage" value="${orderSearchVO.currentPage}">	                        		
			                            <div class="email-filters-left">
				                            <div class="btn-group">
				                                <select class="form-control form-control-sm" name="dateType">
								                    <option value="or_regdate">주문 등록일</option>
								                    <option value="or_settlement_day">주문 결제일</option>
								                    <option value="or_sending_day">주문 발송일</option>
								                    <option value="or_sending_deadline">주문 발송기한</option>
							                    </select>
				                            </div>
				                            &nbsp;
				                            <div class="btn-group">
				                                <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="todays" name="datePeriod" value="0"
		                                        		<c:if test="${orderSearchVO.datePeriod == 0 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 오늘 </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="weeksAgo" name="datePeriod" value="1" 
		                                        		<c:if test="${orderSearchVO.datePeriod == 1 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 일주일 </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="userSelect" name="datePeriod" value="2" 
		                                        		<c:if test="${orderSearchVO.datePeriod == 2 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 임의 </span>
		                                        </label>
				                            </div>
				                            <div class="btn-group">
				                            	<input type="text" class="btn btn-light btn-xs" id="dateStart" name="dateStart" style="width: 8em;" value="${orderSearchVO.dateStart }"/> &nbsp;
				                                <input type="text" class="btn btn-light btn-xs" id="dateEnd" name="dateEnd" style="width: 8em;" value="${orderSearchVO.dateEnd }"/>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name=recordCountPerPage>
													<option value="10"
														<c:if test="${orderSearchVO.recordCountPerPage == 10 }">
															selected="selected"
														</c:if>
													>10 개씩</option>
													<option value="20"
														<c:if test="${orderSearchVO.recordCountPerPage == 20 }">
															selected="selected"
														</c:if>
													>20 개씩</option>
													<option value="30"
														<c:if test="${orderSearchVO.recordCountPerPage == 30 }">
															selected="selected"
														</c:if>
													>30 개씩</option>
													<option value="40"
														<c:if test="${orderSearchVO.recordCountPerPage == 40 }">
															selected="selected"
														</c:if>
													>40 개씩</option>
													<option value="100"
														<c:if test="${orderSearchVO.recordCountPerPage == 100 }">
															selected="selected"
														</c:if>
													>100 개씩</option>
												</select>
				                            </div>
				                            <div class="btn-group">
				                                <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="todays" name="matchingFlag" value="0"
		                                        		<c:if test="${orderSearchVO.matchingFlag == 0 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 매칭안된 </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="weeksAgo" name="matchingFlag" value="1" 
		                                        		<c:if test="${orderSearchVO.matchingFlag == 1 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 매칭된주문 </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="userSelect" name="matchingFlag" value="2" 
		                                        		<c:if test="${orderSearchVO.matchingFlag == 2 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> 전부 </span>
		                                        </label>
				                            </div>
				                            <div class="btn-group">
				                            	<select class="form-control" name="searchType">
								                    <option value="buyerName" 
								                    	<c:if test="${orderSearchVO.searchType == 'buyerName' }">
															 selected="selected"
														</c:if>
								                    >구매자</option>
								                    <option value="receiverName"
								                    	<c:if test="${orderSearchVO.searchType == 'receiverName' }">
															 selected="selected"
														</c:if>
								                    >수령인</option>
								                    <option value="buyerNum"
								                    	<c:if test="${orderSearchVO.searchType == 'buyerNum' }">
															 selected="selected"
														</c:if>
								                    >구매자 번호</option>
								                    <option value="receiverNum"
								                    	<c:if test="${orderSearchVO.searchType == 'receiverNum' }">
															 selected="selected"
														</c:if>
								                    >수령인 번호</option>
								                    <option value="orderNum"
								                    	<c:if test="${orderSearchVO.searchType == 'orderNum' }">
															 selected="selected"
														</c:if>
								                    >주문번호</option>
								                    <option value="orderProductNum"
								                    	<c:if test="${orderSearchVO.searchType == 'orderProductNum' }">
															 selected="selected"
														</c:if>
								                    >상품주문번호</option>
								                    <option value="matchingProduct"
								                    	<c:if test="${orderSearchVO.searchType == 'matchingProduct' }">
															 selected="selected"
														</c:if>
								                    >매칭 상품명</option>
								                    <option value="storeProduct"
								                    	<c:if test="${orderSearchVO.searchType == 'storeProduct' }">
															 selected="selected"
														</c:if>
								                    >판매처 상품명</option>
							                    </select>
				                            </div>
				                            <div class="btn-group">
				                            	<input class="form-control" id="searchKeyword" name="searchKeyword" type="text"  placeholder="검색 내용을 입력해주세요" value="${orderSearchVO.searchKeyword }">
				                                <button class="btn btn-primary" type="submit"> 검색 </button>
				                            </div>
				                        </div>
	                        		</form>
		                        </div>
	                        </div>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 옵션명 미매칭 주문 목록 (총 <fmt:formatNumber value="${notMatchingOptionOrdersResult }" pattern="#,###"/> 개)</h5>
                                <div class="card-body">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                            	<th scope="col"> 판매처 </th>
                                                <th scope="col"> 주문자/수령인 </th>
                                                <th scope="col"> 스토어 상품명 / 매칭 상품명 </th>
                                                <th scope="col"> 주문 개수 </th>
                                                <th scope="col"> 스토어 옵션명 </th>
                                                <th scope="col"> 총 가격 </th>
                                                <th scope="col"> 매칭하기 </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="orderslist" items="${ordersList }">
                                        		<tr>
                                        			<td>${orderslist.ssName }</td>
	                                                <td>${orderslist.orBuyerName }/${orderslist.orReceiverName }</td>
	                                                <td>
	                                                	${orderslist.orProduct } <br>
	                                                	매칭 된 상품 : ${orderslist.productMatchingVOList[0].pmProductName } 
	                                                </td>
	                                                <td>${orderslist.orAmount }</td>
	                                                <td>
	                                                	<c:if test="${empty orderslist.orProductOption }">
	                                                		단일옵션
	                                                	</c:if>
	                                                	<c:if test="${!empty orderslist.orProductOption }">
	                                                		${orderslist.orProductOption }
	                                                	</c:if>
	                                                	<c:if test="${!empty orderslist.productMatchingVOList[0].optionMatchingVOList }">
	                                                		
	                                                		<c:forEach var="optionMatchingList" items="${orderslist.productMatchingVOList[0].optionMatchingVOList }">
	                                                			<p class="text-primary" style="margin: 0px 0px 3px 0px;">${optionMatchingList.productOptionList[0].productName } [ ${optionMatchingList.productOptionList[0].optionName } ] ${orderslist.orAmount * optionMatchingList.omOptionAmount} 개</p>
	                                                		</c:forEach>
	                                                	</c:if>
	                                                </td>
	                                                <td><fmt:formatNumber value="${orderslist.orTotalPrice}" pattern="#,###"/> 원</td>
	                                                <c:if test="${empty orderslist.productMatchingVOList[0].optionMatchingVOList }">
	                                                	<td><button class="btn btn-primary" onclick="productMatching(${orderslist.orPk },'${orderslist.orProduct}','${orderslist.orProductOption }' , ${orderslist.productMatchingVOList[0].pmPk }, 0)"> 매칭 </button></td>
	                                                </c:if>
	                                                <c:if test="${!empty orderslist.productMatchingVOList[0].optionMatchingVOList }">	                                                
		                                                <td><button class="btn btn-success" onclick="productMatching(${orderslist.orPk },'${orderslist.orProduct}','${orderslist.orProductOption }' , ${orderslist.productMatchingVOList[0].pmPk }, 1)"> 재매칭 </button></td>
	                                                </c:if>
	                                            </tr>
                                        	</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<nav aria-label="Page navigation example" style="text-align: center;">
								<ul class="pagination" style="display: -webkit-inline-box;">
									<c:if test='${orderSearchVO.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${orderSearchVO.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${orderSearchVO.lastPage}" begin="${orderSearchVO.firstPage }">
										<li class="page-item 
											<c:if test='${orderSearchVO.currentPage == i }'>
												active
											</c:if>
										"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
									</c:forEach>
									<c:if test='${orderSearchVO.lastPage < orderSearchVO.totalPage}'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${orderSearchVO.lastPage+1})">»</a></li>
									</c:if>
								</ul>
							</nav>
						</div>
                    </div>
            </div>
        <!-- /page content -->
        <%@ include file="../inc/bottom.jsp" %>