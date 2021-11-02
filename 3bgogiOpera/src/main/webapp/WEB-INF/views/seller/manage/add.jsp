<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){

    		$('#sellerStartDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		});
    		
    		
    	});
		
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
                            <h2 class="pageheader-title"> 셀러 추가 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 셀러 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 셀러 목록 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 셀러 추가 </li>
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
                        <!-- ============================================================== -->
                        <!-- valifation types -->
                        <!-- ============================================================== -->
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 새로운 셀러 추가하기 </h5>
                                <div class="card-body">
                                    <form name="createSellerInfoForm" id="createSellerInfoForm" action="<c:url value='/seller/manage/add.do'/>" method="post">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 셀러 아이디  </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="아이디 입력" class="form-control" id="adminId" name="adminId" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비밀번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="password" placeholder="비밀번호 입력" id="adminPass" class="form-control" name="adminPass" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 셀러 이름 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="이름 입력" id="adminName" class="form-control" name="adminName" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 셀러 연락처 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="연락처 입력" id="adminPhone" class="form-control" name="adminPhone" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 활동 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <label class="custom-control custom-radio custom-control-inline">
		                                            <input type="radio" name="enabled" value="1" class="custom-control-input" checked="checked"><span class="custom-control-label"> 활동중 </span>
		                                        </label>
		                                        
		                                    	<label class="custom-control custom-radio custom-control-inline">
			                                    	<input type="radio" name="enabled" value="0" class="custom-control-input"><span class="custom-control-label"> 비활동 </span>
		                                        </label>
                                            </div>
                                        </div>
                                        <div class="form-group row cdLossRateDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 관리중인 상품 번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder=", 를 기준으로 여러개 입력 가능" class="form-control" id="sellerProdList" name="sellerProdList" value="">
                                            </div>
                                        </div>
                                        <div class="form-group row cdLossRateDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 활동 시작일 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="클릭하면 달력이 나옵니다" class="form-control" id="sellerStartDate" name="sellerStartDate" value="" autocomplete="off">
                                            </div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button type="submit" class="btn btn-primary"> 추가하기 </button>
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