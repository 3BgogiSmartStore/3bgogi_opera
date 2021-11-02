<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
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
                                <h2 class="pageheader-title"> 상품명 별 판매 개수 </h2>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                        	<li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 조회 </a></li>
                                            <li class="breadcrumb-item active" aria-current="page"> 상품명 별 판매 개수 </li>
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
                                <form id="analyForms" class="card" action="<c:url value='/analytics/product_sales.do'/>" method="GET">
                                	<div class="card-body">
                                		<h4> 검색 날짜 설정 </h4>
                                		<div class="btn-group">
				                        	<input type="text" class="btn btn-light btn-xs" id="dateStart" name="dateStart" style="width: 8em;" value="${osVO.dateStart }"/> &nbsp;
				                            <input type="text" class="btn btn-light btn-xs" id="dateEnd" name="dateEnd" style="width: 8em;" value="${osVO.dateEnd }"/>                               
				                        </div>
				                        <button class="btn btn-primary btn-xs" type="submit">검색하기</button>
                                	</div>
                                </form>
                            </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="checkboxradio">
                                <div class="card">
                                <h4 class="card-header">해당 기간 총 매출 : 
                                	<fmt:formatNumber value="${totalPrice }" pattern="#,###"/> 원
                                </h4>
                                <div class="card-body">
                                    <table class="table table-hover">
                                    	
                                    	<thead>
											<tr>
												<th>상품명</th>
												<th>개수</th>
											</tr>
                                    	</thead>
                                        <tbody>
                                        	<c:if test="${!empty saleList }">
                                        		<c:forEach var="slist" items="${saleList }">                                        		
		                                            <tr>
		                                            	<td>${slist.orProduct }</td>
		                                            	<td>${slist.orAmount }</td>
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
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>  
        <%@ include file="../../inc/bottom.jsp" %>