<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
    		
    		jQuery.ajaxSettings.traditional = true;

    		var doubleSubmitFlag = false;

    		function doubleSubmitCheck(){
    		    if(doubleSubmitFlag){
    		        return doubleSubmitFlag;
    		        
    		    }else{
    		        doubleSubmitFlag = true;
    		        return false;
    		    }
    		}
    		
    		$('#tiDate').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		
    		});
			
    		$("#insertTransInfoForm").submit(function(){
    			tiDate = $("#tiDate").val();
    			tiUsedPlace = $("input[name=tiUsedPlace]").val();
    			tiUseCost = $("input[name=tiUseCost]").val();
    			tiCancledCost = $("input[name=tiCancledCost]").val();
    			
				if(tiDate == ''){
					alert("사용일자를 입력해주세요");
					$("#tiDate").focus();
    				return false;
    				
				}if(tiUsedPlace == ''){
    				alert("사용처를 입력해주세요");
    				$("input[name=tiUsedPlace]").focus();
    				return false;
    				
    			}if(tiUseCost == '' || tiUseCost == 0){
    				alert("사용금액을 입력해주세요");
    				$("input[name=tiUseCost]").focus();
    				return false;
    				
    			}if(tiCancledCost == '' ){
    				$("input[name=tiCancledCost]").val("0");
    			}
    			
				doubleSubmitCheck();
				
				if(doubleSubmitFlag == false){
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
                            <h2 class="pageheader-title"> 매입(기타) 내역서 추가 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 세금계산서</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 매입(기타) 내역서 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 추가 </li>
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
                                <h5 class="card-header"> 매입(기타) 내역서 추가 </h5>
                                <div class="card-body">
                                    <form id="insertTransInfoForm" method="POST" action="<c:url value='/tax/trans_info/insert.do'/>">
                                    	<div class="form-row">
                                            <div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12 mb-2">
                                                <label for="tiDate"> 사용일자 </label>
                                                <input type="text" id="tiDate" name="tiDate" class="form-control" autocomplete="off">   
                                            </div>
                                        </div>
                                        <hr>                                        
                                    	<table id="example2" class="table table-bordered" style="table-layout: fixed; word-break: keep-all;">
                                    		<colgroup>
												<col width="180px" />
												<col width="80px" />
												<col width="100px" />
												<col width="100px" />
											</colgroup>
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th >사용처</th>
	                                                <th >사용금액</th>
	                                                <th >취소금액</th>
	                                                <th >비고</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id="piAddBody">
		                                        <tr class="table-3bgogi-hover">
		                                            <td>
		                                            	<input type="text" name="tiUsedPlace" class="form-control" placeholder="">
		                                            </td>
		                                            <td>
		                                            	<input type="text" name="tiUseCost" class="form-control" placeholder="">
		                                            </td>
		                                            
		                                            <td>
		                                            	<input type="text" name="tiCancledCost" class="form-control" placeholder="">
		                                            </td>
		                                            <td>
		                                            	<input type="text" name="tiRemark" class="form-control">
		                                            </td>
		                                         </tr>
	                                        </tbody>
	                                    </table>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-12 col-lg-12">
                                                <button id="transInfoInsertBtn" type="submit" class="btn btn-space btn-primary"> 입력 하기 </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- ============================================================== -->
                        <!-- end valifation types -->
                        <!-- ============================================================== -->
                    </div>
            </div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>