<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	
    $(function(){
    	$("#deliveryInvoiceNumInsertForm").submit(function(){
    		
    		if($("#excelfile").val() == ''){
    			alert("택배사 송장 파일을 넣어주세요");
    			event.preventDefault();
    			return false;
    			
    		}
    		
    	});
    });
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
                            <h2 class="pageheader-title"> 택배사 송장 일괄 입력 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 송장입력 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 택배사 송장 일괄 입력  </a></li>
                                        
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
                            <div class="card">
                                <h5 class="card-header"> 엑셀 파일 입력 </h5>
                                <div class="card-body">
                                    <form id="deliveryInvoiceNumInsertForm" method="POST" action="<c:url value='/orders/grant_deliv_invoice.do'/>" enctype="multipart/form-data">
                                    	<div class="form-group row">
                                            <div class="col-12 col-lg-12">
                                                <select class="form-control" name="edtPk">
                                                	<option value="5">cj새벽배송</option>
                                                	<option value="3">프레시솔루션</option>
                                                	<option value="4">롯데택배</option>
                                                	
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-12 col-lg-12">
                                                <input id="excelfile" type="file" name="excelfile" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row pt-2 pt-sm-5 mt-1">
                                            <div class="col-sm-6 offset-6">
                                                <p class="text-right">
                                                    <button type="submit" class="btn btn-space btn-secondary"> 엑셀  입력 </button>
                                                </p>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
					</div>
	            </div>
        <!-- /page content -->
        <%@ include file="../inc/bottom.jsp" %>