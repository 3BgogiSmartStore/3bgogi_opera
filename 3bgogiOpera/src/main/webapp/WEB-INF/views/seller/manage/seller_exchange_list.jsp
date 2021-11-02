<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		var doubleSubmitFlag = false;

    		function doubleSubmitCheck(){
    		    if(doubleSubmitFlag){
    		        return doubleSubmitFlag;
    		    }else{
    		        doubleSubmitFlag = true;
    		        return false;
    		    }
    		}
    		
			$("button[name=permitExchangeBtn]").click(function(){
				
				if(confirm("해당 환전 신청을 허가하시겠습니까?")){
					
					doubleSubmitCheck();
					
					if(doubleSubmitFlag == false){
						
						return false;
					}
					
				    var exchangeForm = document.createElement("form");
				    exchangeForm.method = "POST";
				    exchangeForm.action = "/seller/manage/exchange.do";
				    
					sePk = $(this).data("se-pk");
					adminName = $(this).data("admin-name");
					adminPhone = $(this).data("admin-phone");
					
					var sePkInput = document.createElement("input");
					sePkInput.type = "hidden";
					sePkInput.name = "sePk";
					sePkInput.value = sePk;
			        
					var adminNameInput = document.createElement("input");
					adminNameInput.type = "hidden";
					adminNameInput.name = "adminName";
					adminNameInput.value = adminName;
					
					var adminPhoneInput = document.createElement("input");
					adminPhoneInput.type = "hidden";
					adminPhoneInput.name = "adminPhone";
					adminPhoneInput.value = adminPhone;
					
			        exchangeForm.append(sePkInput);
			        exchangeForm.append(adminNameInput);
			        exchangeForm.append(adminPhoneInput);
			        
			        document.body.appendChild(exchangeForm);
			        
			        exchangeForm.submit();
			        
			        
				}
			});  				
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#sellerListSearchForm").submit();
			
		}
    	
    </script>
    <style type="text/css">
    	.table-3bgogi-hover:hover{
    		background: #FFC6C6;
    	}
    </style>
    
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
                            <h2 class="pageheader-title"> 환전 신청 목록 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 셀러 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 관리 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 환전 신청 목록 </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end pageheader -->
                <!-- ============================================================== -->
                <div class="ecommerce-widget">
                    <div class="row">
                        <!-- ============================================================== -->
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header"> 환전 신청 목록 </h5>
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="10%">이름 </th>
	                                                <th width="10%">연락처 </th>
	                                                <th width="15%">환전 금액</th>
	                                                <th width="15%">회차</th>
	                                                <th width="15%">은행</th>
	                                                <th width="15%">계좌번호</th>
	                                                <th width="15%">신청일</th>
	                                                <th width="15%">허가</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty sellerExchangeList }">
	                                        		<tr>
	                                        			<td colspan="7"> 환전 신청자가 존재하지않습니다 </td>
	                                        		</tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty sellerExchangeList }">	                                        	
		                                        	<c:forEach var="seList" items="${sellerExchangeList }">                                        	
			                                            <tr class="table-3bgogi-hover">
			                                                <td>${seList.adminName }</td>
			                                                <td>${seList.adminPhone }</td>
			                                                
			                                                <c:forEach var="seInfo" items="${seList.sellerExchangeList }">			                                                
																<td>
																	<fmt:formatNumber value="${seInfo.seExchangePrice }" pattern="#,###"/> 원
																</td>				                                                
																<td> ${seInfo.seExchangeIncreaseCount } </td>
																<td> ${seInfo.seBankNm } </td>
																<td> ${seInfo.seBankAccount } </td>
																<td> ${seInfo.seReqDate } </td>
																<td>
																	<button
																		name="permitExchangeBtn" 
																		class="btn btn-primary"
																		data-se-pk="${seInfo.sePk }"
																		data-admin-name="${seList.adminName }"
																		data-admin-phone="${seList.adminPhone }"
																		
																	>허가</button>
																</td>
			                                                </c:forEach>
			                                            </tr>
		                                        	</c:forEach>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
                        	<!-- 페이징 처리에 필요한 값 -->
						<div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
                        	<form id="sellerListSearchForm" action="<c:url value='/seller/manage/exchange_list.do'/>" method="get">
                        	<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
								<div class="card">
									<div class="card-body border-top">
										<h3 class="font-16"> 이름 </h3>
										<input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="셀러 이름 입력" value="${osVO.searchKeyword }">
									</div>
									<%-- <div class="card-body">
										<h3 class="font-16"> 표기될 셀러 목록 개수 </h3>
										<select class="form-control" name=recordCountPerPage>
											<option value="10"
												<c:if test="${osVO.recordCountPerPage == 10 }">
													selected="selected"
												</c:if>
											>10 건씩</option>
											<option value="20"
												<c:if test="${osVO.recordCountPerPage == 20 }">
													selected="selected"
												</c:if>
											>20 건씩</option>
											<option value="30"
												<c:if test="${osVO.recordCountPerPage == 30 }">
													selected="selected"
												</c:if>
											>30 건씩</option>
										</select>
									</div> --%>
									<div class="card-body border-top">
										<h3 class="font-16"> 환전 신청일 </h3>
										<div class="form-row">
											<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
												<input type="text" class="form-control" name="dateStart" id="dateStart" placeholder="시작일" required value="${osVO.dateStart }">
											</div>
											<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
												<input type="text" class="form-control" name="dateEnd" id="dateEnd" placeholder="종료일" required value="${osVO.dateEnd }">
											</div>
										</div>
									</div>
									<div class="card-body border-top">
										<button class="btn btn-secondary btn-lg btn-block"> 검색하기 </button>
									</div>
								</div>
							</form>
						</div>
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<nav aria-label="Page navigation" style="text-align: center;">
								<ul class="pagination" style="display: inline-flex;  flex-wrap: wrap;">
									<c:if test='${osVO.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${osVO.lastPage}" begin="${osVO.firstPage }">
										<li class="page-item 
											<c:if test='${osVO.currentPage == i }'>
												active
											</c:if>
										"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
									</c:forEach>
									<c:if test='${osVO.lastPage < osVO.totalPage}'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.lastPage+1})">»</a></li>
									</c:if>
								</ul>
							</nav>
						</div>

						<!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
                 </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>