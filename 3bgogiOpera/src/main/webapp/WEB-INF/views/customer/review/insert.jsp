<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){

    		
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
                            <h2 class="pageheader-title"> 고객 리뷰 입력 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 고객 리뷰 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 관리 </a></li>
                                        <li class="breadcrumb-item  active" aria-current="page"><a href="javascript:void(0);" class="breadcrumb-link"> 고객 리뷰 입력 </a></li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                
                	<div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        	<div class="card">
	                        	<div class="card-body">
	                        		<form id="form" method="post" action="<c:url value='/customer_review/insert.do'/>" enctype="multipart/form-data">
                                        <div class="form-group row">
                                            <div class="col-12 col-lg-12 col-form-label">
                                                <input id="smartstoreCustomerReviewExcel" type="file" name="excelfile" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row pt-2 pt-sm-5 mt-1">
                                            <div class="col-sm-6 offset-6">
                                                <p class="text-right">
                                                    <button type="submit" class="btn btn-space btn-secondary"> 고객 리뷰 엑셀파일  입력 </button>
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
        <%@ include file="../../inc/bottom.jsp" %>