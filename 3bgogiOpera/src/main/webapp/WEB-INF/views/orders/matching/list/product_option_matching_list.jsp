<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../../inc/top.jsp" %>
    <%@ include file="../../../inc/top_nav.jsp" %>
    <script type="text/javascript">
		$(function(){
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
    		
		});
		
		function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#searchCustomerInfo").submit();
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
                                <h3 class="mb-2"> ?????? ??????  </h3>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item active" aria-current="page"> ????????? </li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- content -->
                    <!-- ============================================================== -->
                    	<form class="row" id="searchCustomerInfo" action="<c:url value='/orders/search/customer_orders.do'/>" method="get">
                    		<input type="hidden" name="searchCondition" >
                        	<input type="hidden" name="currentPage" value="${osVO.currentPage}">
                        <!-- ============================================================== -->
                        <!-- search bar  -->
                        <!-- ============================================================== -->
		                        <div class="col-xl-5 col-lg-5 col-md-5 col-sm-12 col-12">
		                        	<div class="card">
		                        		<div class="card-body">
				                            <div class="btn-group">
				                                <select class="form-control form-control-sm" name="dateType">
				                                	<option value="orRegdate">????????? ?????????</option>
								                    <option value="pmRegdate">?????? ?????? ?????????</option>
								                    <option value="omregdate">?????? ?????? ?????????</option>
							                    </select>
				                            </div>
				                            &nbsp;
				                            <div class="btn-group">
				                                <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="todays" name="datePeriod" value="0"
		                                        		<c:if test="${osVO.datePeriod == 0 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> ?????? </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="weeksAgo" name="datePeriod" value="1" 
		                                        		<c:if test="${osVO.datePeriod == 1 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> ????????? </span>
		                                        </label>
		                                        <label class="custom-control custom-radio custom-control-inline">
		                                        	<input type="radio" id="userSelect" name="datePeriod" value="2" 
		                                        		<c:if test="${osVO.datePeriod == 2 }">
															 checked="checked"
														</c:if>
		                                        	class="custom-control-input"><span class="custom-control-label"> ?????? </span>
		                                        </label>
				                            </div>
				                            <div class="btn-group">
				                            	<input type="text" class="btn btn-light btn-xs" id="dateStart" name="dateStart" style="width: 8em;" value="${osVO.dateStart }"/> &nbsp;
				                                <input type="text" class="btn btn-light btn-xs" id="dateEnd" name="dateEnd" style="width: 8em;" value="${osVO.dateEnd }"/>
				                            </div>
		                        		</div>
		                        	</div>
		                        </div>
		                        <div class="col-xl-7 col-lg-7 col-md-7 col-sm-12 col-12">
		                        	<div class="card">
		                        		<div class="card-body">
				                            <div class="btn-group">
				                            	<select class="form-control" name="searchType">
				                            		<option value="storeProduct"
								                    	<c:if test="${osVO.searchType == 'storeProduct' }">
															 selected="selected"
														</c:if>
								                    >????????? ?????????)</option>
								                    <option value="storeOption"
								                    	<c:if test="${osVO.searchType == 'storeOption' }">
															 selected="selected"
														</c:if>
								                    >????????? ?????????</option>
								                    <option value="matchingProduct"
								                    	<c:if test="${osVO.searchType == 'matchingProduct' }">
															 selected="selected"
														</c:if>
								                    >?????? ?????????</option>
								                    <option value="matchingOption"
								                    	<c:if test="${osVO.searchType == 'matchingOption' }">
															 selected="selected"
														</c:if>
								                    >?????? ?????????</option>
							                    </select>
				                            </div>
				                            <div class="btn-group">
				                            	<input class="form-control" id="searchKeyword" name="searchKeyword" type="text"  placeholder="?????? ????????? ??????????????????" value="${osVO.searchKeyword }">
				                                <button class="btn btn-primary" type="submit"> ?????? </button>
				                            </div>
				                             <div class="btn-group">
				                                <select class="form-control" name="recordCountPerPage">
														<option value="50"
															<c:if test="${osVO.recordCountPerPage == 50 }">
																selected="selected"
															</c:if>
														>??? ???????????? 50 ??????</option>
														<option value="100"
															<c:if test="${osVO.recordCountPerPage == 100 }">
																selected="selected"
															</c:if>
														>??? ???????????? 100 ??????</option>
														<option value="200"
															<c:if test="${osVO.recordCountPerPage == 200 }">
																selected="selected"
															</c:if>
														>??? ???????????? 200 ??????</option>
													</select>
				                            </div>
		                        		</div>
		                        	
		                        	</div>
		                        </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            	<div class="card">
	                                <div class="card-body">
	                                	<div class="table-responsive">
		                                    <table class="table table-hover table-3bgogi-hover " style="font-size: 12px; word-break: keep-all; white-space: nowrap;">
		                                        <thead class="bg-light">
		                                            <tr>
		                                                <th scope="col">
		                                                	<label class="custom-control custom-checkbox be-select-all">
								                                <input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="orSeiralSpecialNumberAllSelect"><span class="custom-control-label"></span>
								                            </label>
		                                                </th>
		                                                <th scope="col"> ?????? ??? </th>
		                                                <th scope="col"> ?????? ?????? </th>
		                                                <th scope="col"> ?????? ??? </th>
		                                                <th scope="col"> ?????? ?????? ??? </th>
		                                                <th scope="col"> ?????? </th>
		                                                <th scope="col"> ?????? ?????? ?????? </th>
		                                            </tr>
		                                        </thead>
		                                        <tbody>
		                                        	<c:if test="${empty orderList }">
			                                        	<tr style="text-align: center;">
			                                        		<td colspan="7"> ????????? ????????? ???????????? </td>
			                                        	</tr>
		                                        	</c:if>
		                                        </tbody>
		                                    </table>
	                                	</div>
	                                </div>
	                            </div>
                            </div>
                            <%-- <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<nav aria-label="Page navigation" style="text-align: center;">
									<ul class="pagination" style="display: table; margin-left: auto; margin-right: auto;">
										<c:if test="${osVO.firstPage>1 }">
											<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.firstPage-1})">??</a></li>
										</c:if>
										<c:forEach var="i" step="1" end="${osVO.lastPage}" begin="${osVO.firstPage }">
											<li class="page-item
												<c:if test="${osVO.currentPage == i }">
													active
												</c:if>
											"  style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
										</c:forEach>
										<c:if test="${osVO.lastPage < osVO.totalPage}">
											<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${osVO.lastPage+1})">??</a></li>
										</c:if>
									</ul>
								</nav>
							</div> --%>
                    	</form>
                    </div>
	<%@ include file="../../../inc/bottom.jsp" %>
        