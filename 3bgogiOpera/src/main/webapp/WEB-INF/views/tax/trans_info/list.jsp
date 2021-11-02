<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#dateStart, #dateEnd').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    			
    		});	
    		
    	});
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#transInfoSearchForm").submit();
		
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
                            <h2 class="pageheader-title"> 매입(기타)내역서 목록 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 세금계산서</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 매입(기타)내역서 목록 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 목록 </li>
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
                                <h5 class="card-header">매입(기타)내역서 목록</h5>
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                            	<th scope="col">
		                                               	<label class="custom-control custom-checkbox be-select-all">
								                             <input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="piPkAllSelect"><span class="custom-control-label"></span>
								                        </label>
		                                            </th>
	                                            	<th >사용일자</th>
	                                                <th >사용처</th>
	                                                <th >사용금액</th>
	                                                <th >취소금액</th>
	                                                <th >비고</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${!empty tiList}">
	                                        		<c:set var="totalCost" value="0"></c:set>
		                                        	<c:set var="totalCancledCost" value="0"></c:set>
		                                        	<c:forEach var="tilist" items="${tiList }">
		                                        		<tr>
		                                        			<td scope="row" class="checkTd">
				                                                <label class="custom-control custom-checkbox be-select-all">
										                        	<input class="custom-control-input chk_all" type="checkbox" name="tiPk" value="${tilist.tiPk }">
										                            <span class="custom-control-label"></span>
										                    	</label>
				                                            </td>
		                                        			<td>${tilist.tiDate }</td>
		                                        			<td>
		                                        				<a href="<c:url value='/tax/trans_info/read.do?tiPk=${tilist.tiPk }'/>">${tilist.tiUsedPlace }</a>
		                                        				
		                                        			</td>
		                                        			<td>
		                                        				<fmt:formatNumber value="${tilist.tiUseCost }" pattern="#,###"/>
		                                        				<c:set var="totalCost" value="${totalCost + tilist.tiUseCost}"></c:set>
		                                        			</td>
		                                        			<td>
		                                        				<fmt:formatNumber value="${tilist.tiCancledCost }" pattern="#,###"/>
		                                        				<c:set var="totalCancledCost" value="${totalCancledCost + tilist.tiCancledCost }"></c:set>
		                                        			</td>
		                                        			
		                                        			<td>${tilist.tiRemark }</td>
		                                        		</tr>
		                                        	</c:forEach>
		                                        	
		                                        	
	                                        		<tr>
	                                        			<td colspan="3"> 총 합</td>
		                                                <td >
		                                                	<fmt:formatNumber value="${totalCost }" pattern="#,###" />
		                                                </td>
		                                                <td >
		                                                	<fmt:formatNumber value="${totalCancledCost }" pattern="#,###" />
		                                                </td>
		                                                <td> - </td>
	                                        		</tr>
	                                        	</c:if>
	                                        </tbody>
	                                    </table>
                                	</div>
                                </div>
                            </div>
                        </div>
                        	<!-- 페이징 처리에 필요한 값 -->
						<div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
                        	<form id="transInfoSearchForm" action="" method="get">
                        		<input type="hidden" name="currentPage" value="${osVO.currentPage}">
								<div class="card">
									<div class="card-body border-top">
										<h3 class="font-16"> 사용처</h3>
										<input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="사용처를 입력해주세요" value="${osVO.searchKeyword }">
									</div>
									<div class="card-body">
										<h3 class="font-16"> 페이지 당 목록 개수 </h3>
										<div>
											<select class="form-control mb-3" name=recordCountPerPage>
												<option value="10"
													<c:if test="${osVO.recordCountPerPage == 10 }">
														selected="selected"
													</c:if>
												>10 개씩</option>
												<option value="20"
													<c:if test="${osVO.recordCountPerPage == 20 }">
														selected="selected"
													</c:if>
												>20 개씩</option>
												<option value="30"
													<c:if test="${osVO.recordCountPerPage == 30 }">
														selected="selected"
													</c:if>
												>30 개씩</option>
												<option value="40"
													<c:if test="${osVO.recordCountPerPage == 40 }">
														selected="selected"
													</c:if>
												>40 개씩</option>
											</select>
										</div>
									</div>
									<div class="card-body">
										<h3 class="font-16"> 사용일자 </h3>
										<div class="form-row">							
											<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 mb-2">
												<input type="text" class="form-control" id="dateStart" name="dateStart" value="${osVO.dateStart }"/>
											</div>		
											<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12 mb-2">
												<input type="text" class="form-control" id="dateEnd" name="dateEnd" value="${osVO.dateEnd }"/>
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
        <%@ include file="../../inc/bottom.jsp" %>