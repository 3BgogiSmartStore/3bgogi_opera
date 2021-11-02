<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){

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
                            <h2 class="pageheader-title"> 셀러 수정 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 셀러 </a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 셀러 목록 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 수정 </li>
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
                                <h5 class="card-header"> 셀러 ${adVO.adminName } 님의 등록 정보 ( 셀러 활동 시작일 ${adVO.sellerStartDate } )</h5>
                                <div class="card-body">
                                    <form name="updateSellerInfoForm" id="updateSellerInfoForm" action="<c:url value='/seller/manage/update.do'/>" method="post">
                                    	<input type="hidden" name="adminPk" value="${adVO.adminPk }">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 셀러 아이디  </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="" class="form-control" id="adminId" name="adminId" value="${adVO.adminId }" readonly="readonly">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 셀러 이름 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="셀러명 입력" id="adminName" class="form-control" name="adminName" value="${adVO.adminName }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 셀러 연락처 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder="셀러명 입력" id="adminPhone" class="form-control" name="adminPhone" value="${adVO.adminPhone }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 활동 여부 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <label class="custom-control custom-radio custom-control-inline">
		                                            <input type="radio" name="enabled" 
		                                                <c:if test="${ adVO.enabled == true  }">
							                            	checked="checked"
							                            </c:if>
		                                            value="1" class="custom-control-input"><span class="custom-control-label"> 활동중 </span>
		                                        </label>
		                                        
		                                    	<label class="custom-control custom-radio custom-control-inline">
			                                    	<input type="radio" name="enabled"
			                                    		<c:if test="${ adVO.enabled == false  }">
							                                    checked="checked"
							                            </c:if>
		                                            value="0" class="custom-control-input"><span class="custom-control-label"> 비활동 </span>
		                                        </label>
                                            </div>
                                        </div>
                                        <div class="form-group row cdLossRateDiv">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 관리중인 상품 번호 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" placeholder=", 를 기준으로 여러개 입력 가능" class="form-control" id="sellerProdList" name="sellerProdList" value="${adVO.sellerProdList }">
                                            </div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button type="submit" class="btn btn-space btn-primary"> 수정하기 </button>
                                                <button class="btn btn-space btn-secondary">취소하기</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                        <div class="card">
                                <h5 class="card-header"> 해당 셀러의 환전 신청 목록</h5>
                                <div class="card-body">
                                	<div class="table-responsive">
	                                    <table id="example2" class="table table-bordered" style="text-align:center; font-size: 12px; word-break: keep-all; white-space: nowrap;">
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th width="17%">환전 금액</th>
	                                                <th width="15%">회차</th>
	                                                <th width="15%">은행명</th>
	                                                <th width="15%">계좌</th>
	                                                <th width="10%">신청일</th>
	                                                <th width="10%">승인일</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:if test="${empty exchangeList }">
	                                        		<tr class="table-3bgogi-hover" style="text-align: center;">
		                                                <td colspan="6"> 신청 내역이 존재하지 않습니다 </td>
		                                            </tr>
	                                        	</c:if>
	                                        	<c:if test="${!empty exchangeList }">
		                                        	<c:forEach var="exList" items="${exchangeList }">                                        	
			                                            <tr class="table-3bgogi-hover">
			                                                <td>
			                                                	<fmt:formatNumber value="${exList.seExchangePrice }" pattern="#,###" /> 원
			                                                </td>
			                                                <td>${exList.seExchangeIncreaseCount }</td>
			                                                <td>${exList.seBankNm }</td>
			                                                <td>${exList.seBankAccount }</td>
			                                                <td>${exList.seReqDate } </td>
			                                                <td>
			                                                	<c:if test="${empty exList.sePermitDate or exList.sePermitDate == '' }">
			                                                		- 승인하지 않음 - 
			                                                	</c:if>
			                                                	<c:if test="${!empty exList.sePermitDate }">
			                                                		${exList.sePermitDate }
			                                                	</c:if>
			                                                </td>
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
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>