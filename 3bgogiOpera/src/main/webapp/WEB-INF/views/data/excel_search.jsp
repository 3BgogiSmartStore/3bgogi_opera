<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		$('#dateStart').datetimepicker({
    			timepicker: true,
    			lang:'kr',
    			format:'Y-m-d H:i'
    			
    		});
    		$('#dateEnd').datetimepicker({
    			timepicker: true,
    			lang:'kr',
    			format:'Y-m-d H:i'
    			
    		});
    		
    		$("#dateStart").click(function(){
    			$("#userSelect").prop("checked", true);
    		});
    		
    		$("#dateEnd").click(function(){
    			$("#userSelect").prop("checked", true);
    		});
    	});
    </script>
    
        <!-- page content -->
        <!-- ============================================================== -->
        <!-- wrapper  -->
        <!-- ============================================================== -->
        <div class="dashboard-wrapper">
            <div class="container-fluid dashboard-content ">
                    <!-- ============================================================== -->
                    <!-- pageheader  -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title"> 통계 선택 조회 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                        	<li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 데이터 관리 </a></li>
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 통계 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 통계 선택 조회 </li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- end pageheader  -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
                                <form id="analyForms" class="card" action="<c:url value='/data/excel_search.do'/>" method="post" enctype="multipart/form-data">
                                	<div class="card-body">
                                		<h4> 날짜 범위 설정 및 엑셀 파일</h4>
				                            <div class="btn-group">
				                            	<input id="excelFile" type="file" name="excelfile" class="form-control">
				                            </div>
				                            <div class="btn-group">
				                            	<input type="text" class="btn btn-light btn-xs" id="dateStart" name="dateStart" value="${osVO.dateStart }"/> &nbsp;
				                                <input type="text" class="btn btn-light btn-xs" id="dateEnd" name="dateEnd" value="${osVO.dateEnd }"/>
				                            </div>
				                            
				                            <div class="btn-group">
				                                <button class="btn btn-primary" type="submit"> 검색 </button>
				                            </div>
                                	</div>
                                </form>
                            </div> 
                    </div>
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
							<div class="card">
								<h1 class="card-head">
									${fn:length(orderList) }
								</h1>
								
                        		<div class="card-body">
                        			<div class="table-responsive">
										<table class="table table-hover">
			                            	<thead>
												<tr>
													<th> 구매자연락처 </th>
													<th> 구매자명 </th>
												</tr>
			                              	</thead>
			                              	
			                                <tbody>
			                                	<c:if test="${!empty orderList}">
				                                	<c:forEach var="orderlist" items="${orderList }">
				                                		<tr>
				                                			<td>
				                                				${orderlist.orBuyerContractNumber1}
				                                			</td>
				                                			<td>
				                                				${orderlist.orBuyerName}
				                                			</td>
				                                		</tr>
				                                	</c:forEach>
			                                	</c:if>
			                                	<c:if test="${empty orderList}">
			                                		<tr>
			                                			<td colspan="2"> 조회된 건이 존재하지 않습니다 </td>
			                                		</tr>
			                                		
			                                	</c:if>
			                                </tbody>
			                           </table>
		                        	</div>
                        		</div>
                        	</div>
                        </div> 
                    </div>
                </div>
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>  
        <%@ include file="../inc/bottom.jsp" %>