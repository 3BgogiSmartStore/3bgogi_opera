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
			
    		$("#updateTransInfoForm").submit(function(){
    			
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
                            <h2 class="pageheader-title"> 매입(기타)내역서 상세보기 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 세금계산서</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 매입(기타)내역서 </a></li>
                                        <li class="breadcrumb-item active" aria-current="page"> 상세보기 </li>
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
                                <h5 class="card-header"> 매입(기타)내역서 상세사항 </h5>
                                <div class="card-body">
                                    <form id="updateTransInfoForm" method="POST" action="<c:url value='/tax/trans_info/update.do'/>">
                                    	<input type="hidden" name="tiPk" value="${tiVO.tiPk }">
                                    	
                                    	<div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사용일자 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" id="tiDate" name="tiDate" class="tiDate" value="${tiVO.tiDate }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사용처 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="tiUsedPlace" class="form-control" value="${tiVO.tiUsedPlace }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 사용금액 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="tiUseCost" class="form-control" value="${tiVO.tiUseCost }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 취소금액 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" name="tiCancledCost" class="form-control" value="${tiVO.tiCancledCost }">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"> 비고 </label>
                                            <div class="col-12 col-sm-8 col-lg-6">
                                                <input type="text" id="tiRemark" name="tiRemark" class="form-control" value="${tiVO.tiRemark}">
                                            </div>
                                        </div>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0">
                                                <button id="updateTransInfoBtn" type="submit" class="btn btn-space btn-primary"> 수정하기 </button>
                                                <a class="btn btn-space btn-danger" href="<c:url value='/tax/trans_info/list.do'/>">목록으로</a>
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
            <iframe id="fileDownloadIframe" width="0" height="0">
			</iframe> 
			<div class="modal fade" id="piImgFile" tabindex="-1" role="dialog" aria-labelledby="#piImgFile" aria-hidden="true">
			<div class="modal-dialog" role="document" style="max-width: 900px;">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="stockModal"> 이미지 파일 </h5>
						<a href="#" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</a>
					</div>
					<div class="modal-body" >
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" id="piImgDiv">
							<img id="piImg">
                        </div>
					</div>
				</div>
			</div>
		</div>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>