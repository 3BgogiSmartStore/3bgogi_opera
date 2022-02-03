<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../inc/top.jsp" %>
    <%@ include file="../inc/top_nav.jsp" %>
    <style type="text/css">
    	.products_table_hover{
    		background-color: #c3e6cb;
    	}
    	.SelfdevisionOrderBtn{
    		cursor: pointer;
    	}
    </style>
    <script type="text/javascript">
    
    	$(function(){
    		
    		$('#exSearchBar').hide();
    		
    		$("#delivStoppedAreaCheckBtn").click(function(){
    			window.open("<c:url value='/security/epost/epost_stopped_area_check.do'/>", "발송 중단 지역 확인" , "width=700, height=800, top=100, left=300, scrollbars=no");

    		});
    		
    		
    		$(".SelfdevisionOrderBtn").click(function(){
    			
    			var orPk = $(this).data("or-pk");
    			
    			window.open("/orders/config/self_devide_order.do?orPk="+orPk+"&closing="+0, "주문서 나누기" , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
    		});
    		
    		
    		// cs 내역 확인하기
			$(".adminOrderRecord").click(function(){
    			
    			window.open("/orders/order_record.do?orSerialSpecialNumber="+$(this).val(), "cs 내역 확인" , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
    			
    		});
    		
    		

    		$('#showExSearchBar').click(function(){
    			
    				if($(this).is(":checked")){
    					$('#exSearchBar').show();
    					
    				}else{
    					$('#exSearchBar').hide();
    					
    				}
    		});
    		
    		$("#packingOrder").click(function(){
    			dateStart = $("#dateStart").val();
    			dateEnd = $("#dateEnd").val();
    			
    			location.href="<c:url value='/order/config/packing_irre_order_list.do?dateStart="+dateStart+"&dateEnd="+dateEnd+"'/>";
    			
    		});
    		

    		$("#allMergeBtn").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			
    			if(orSize == 0){
    				alert("부여할 수 있는 송장이 존재하지 않습니다");
    				return false;
    			}
    			
    			if(confirm(orSize+" 개의 주문서를 합포하시겠습니까?")){
    					
					doubleSubmitCheck();
        			
        			if(doubleSubmitFlag == false){
        				
        				return false;
        			}
        			
        			$("#sendingDelivButton").removeClass("btn btn-danger");
        			
            		$("#sendingDelivButton").text("");
            		
        			$("#sendingDelivButton").addClass("dashboard-spinner spinner-xs");
        			
	    			var orSerialSpecialNumberList = new Array(orSize);
	    				
					window.open('', 'viewer', 'width=1000, height=700');
	    			
	    			var delivForm =  document.createElement("form");
	    			delivForm.method="POST";
	    			
	    			delivForm.action = '<c:url value="/orders/order_new_all_merge.do"/>';
	    			delivForm.target = "viewer";
	    			
	    			for(var i=0; i<orSize; i++){
	    				var orSerialSpecialNumberInput = document.createElement("input");
	    				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
	    				orSerialSpecialNumberInput.value=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
	    				delivForm.append(orSerialSpecialNumberInput);
	    				console.log(orSerialSpecialNumberInput+" "+$("input[name=orSerialSpecialNumberList]:checked")[i].value);
	    				
	    			}
					
	    			$("#excelDownloadIframe").append(delivForm);
	    			
	    			delivForm.submit();
	    			
	    			$("#excelDownloadIframe").html("");
    			}
    			
    			
    		});
    		
    		
    		$("#todayPickupDelivButton").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			
    			if(orSize == 0){
    				alert("부여할 수 있는 송장이 존재하지 않습니다");
    				return false;
    			}
    			
    			if(confirm(orSize+" 개의 주문서에 송장을 부여하시겠습니까?")){
    					
					doubleSubmitCheck();
        			
        			if(doubleSubmitFlag == false){
        				
        				return false;
        			}
        			
        			$("#sendingDelivButton").removeClass("btn btn-danger");
        			
            		$("#sendingDelivButton").text("");
            		
        			$("#sendingDelivButton").addClass("dashboard-spinner spinner-xs");
        			
	    			var orSerialSpecialNumberList = new Array(orSize);
	    				
					window.open('', 'viewer', 'width=1000, height=700');
	    			
	    			var delivForm =  document.createElement("form");
	    			delivForm.method="POST";
	    			
	    			delivForm.action = '<c:url value="/security/create_today_pickup_invoice.do"/>';
	    			delivForm.target = "viewer";
	    			
	    			for(var i=0; i<orSize; i++){
	    				var orSerialSpecialNumberInput = document.createElement("input");
	    				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
	    				orSerialSpecialNumberInput.value=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
	    				delivForm.append(orSerialSpecialNumberInput);
	    				console.log(orSerialSpecialNumberInput+" "+$("input[name=orSerialSpecialNumberList]:checked")[i].value);
	    				
	    			}
					
	    			$("#excelDownloadIframe").append(delivForm);
	    			
	    			delivForm.submit();
	    			
	    			$("#excelDownloadIframe").html("");
    			}
    			
    			
    		});
    		
    		$("#sendingDelivButton").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			
    			if(orSize == 0){
    				alert("부여할 수 있는 송장이 존재하지 않습니다");
    				return false;
    			}
    			
    			if(confirm(orSize+" 개의 주문서에 송장을 부여하시겠습니까?")){
    					
					doubleSubmitCheck();
        			
        			if(doubleSubmitFlag == false){
        				
        				return false;
        			}
        			
        			$("#sendingDelivButton").removeClass("btn btn-danger");
        			
            		$("#sendingDelivButton").text("");
            		
        			$("#sendingDelivButton").addClass("dashboard-spinner spinner-xs");
        			
	    			var orSerialSpecialNumberList = new Array(orSize);
	    				
					window.open('', 'viewer', 'width=1000, height=700');
	    			
	    			var delivForm =  document.createElement("form");
	    			delivForm.method="POST";
	    			
	    			delivForm.action = '<c:url value="/security/create_deliv_invoice.do"/>';
	    			delivForm.target = "viewer";
	    			
	    			
	    			for(var i=0; i<orSize; i++){
	    				var orSerialSpecialNumberInput = document.createElement("input");
	    				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
	    				orSerialSpecialNumberInput.value=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
	    				delivForm.append(orSerialSpecialNumberInput);
	    				console.log(orSerialSpecialNumberInput+" "+$("input[name=orSerialSpecialNumberList]:checked")[i].value);
	    				
	    			}
					
	    			$("#excelDownloadIframe").append(delivForm);
	    			
	    			delivForm.submit();
	    			
	    			$("#excelDownloadIframe").html("");
    			}
    			
    			
    		});
    		
    		$("#cjDelivButton").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			
    			if(orSize == 0){
    				alert("부여할 수 있는 송장이 존재하지 않습니다");
    				return false;
    			}
    			
    			if(confirm(orSize+" 개의 주문서에 Cj 새벽배송 임시송장을 부여하시겠습니까?")){
        			
        			$("#cjDelivButton").removeClass("btn btn-warning");
        			
            		$("#cjDelivButton").text("");
            		
        			$("#cjDelivButton").addClass("dashboard-spinner spinner-xs");
        			
    				if($("select[name=edtFk]").val() == '3'){
    					alert("임시 송장을 부여합니다");
    					
    				}
    				
					var divs = document.createElement("div");
	    			
	    			var excelDownloadForm =  document.createElement("form");
	    			excelDownloadForm.action="/security/cj_delivery.do";
	    			excelDownloadForm.method="POST";
    					
	    			var orSerialSpecialNumberList = new Array(orSize);
	    			
	    			
	    			for(var i=0; i<orSize; i++){
	    				var orSerialSpecialNumberInput = document.createElement("input");
	    				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
	    				orSerialSpecialNumberInput.value=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
	    				excelDownloadForm.append(orSerialSpecialNumberInput);
	    				
	    			}
	    			
	    			$("#excelDownloadIframe").append(excelDownloadForm);
	    			
	    			excelDownloadForm.submit();
	    			
	    			$("#excelDownloadIframe").html("");
    			}
    			
    			
    		}); 
    		
    		$("#lotteDelivButton").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			
    			if(orSize == 0){
    				alert("부여할 수 있는 송장이 존재하지 않습니다");
    				return false;
    			}
    			
    			if(confirm(orSize+" 개의 주문서에 롯데택배 임시송장을 부여하시겠습니까?")){
        			
        			$("#cjDelivButton").removeClass("btn btn-warning");
        			
            		$("#cjDelivButton").text("");
            		
        			$("#cjDelivButton").addClass("dashboard-spinner spinner-xs");
        			
    				if($("select[name=edtFk]").val() == '3'){
    					alert("임시 송장을 부여합니다");
    					
    				}
    				
					var divs = document.createElement("div");
	    			
	    			var excelDownloadForm =  document.createElement("form");
	    			excelDownloadForm.action="/security/lotte_delivery.do";
	    			excelDownloadForm.method="POST";
    					
	    			var orSerialSpecialNumberList = new Array(orSize);
	    			
	    			
	    			for(var i=0; i<orSize; i++){
	    				var orSerialSpecialNumberInput = document.createElement("input");
	    				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
	    				orSerialSpecialNumberInput.value=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
	    				excelDownloadForm.append(orSerialSpecialNumberInput);
	    				
	    			}
	    			
	    			$("#excelDownloadIframe").append(excelDownloadForm);
	    			
	    			excelDownloadForm.submit();
	    			
	    			$("#excelDownloadIframe").html("");
    			}
    			
    			
    		}); 
    		
    		$("#teamFreshDelivButton").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			
    			if(orSize == 0){
    				alert("부여할 수 있는 송장이 존재하지 않습니다");
    				return false;
    			}
    			
    			if(confirm(orSize+" 개의 주문서에 팀프레시 송장을 부여하시겠습니까?")){
        			
        			$("#teamFreshDelivButton").removeClass("btn btn-warning");
        			
            		$("#teamFreshDelivButton").text("");
            		
        			$("#teamFreshDelivButton").addClass("dashboard-spinner spinner-xs");

					window.open('', 'viewer', 'width=1000, height=700');
	    			
	    			var delivForm =  document.createElement("form");
	    			delivForm.method="POST";
	    			
	    			delivForm.action = '<c:url value="/security/teamfresh_delivery.do"/>';
	    			delivForm.target = "viewer";
	    			
	    			for(var i=0; i<orSize; i++){
	    				var orSerialSpecialNumberInput = document.createElement("input");
	    				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
	    				orSerialSpecialNumberInput.value=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
	    				delivForm.append(orSerialSpecialNumberInput);
	    				console.log(orSerialSpecialNumberInput+" "+$("input[name=orSerialSpecialNumberList]:checked")[i].value);
	    				
	    			}
	    			
	    			$("#excelDownloadIframe").append(delivForm);
	    			
	    			delivForm.submit();
	    			
	    			$("#excelDownloadIframe").html("");
    			}
    			
    			
    		}); 
    		
    		
    		$("#cjDelivDoorMsgButton").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			
    			if(orSize == 0){
    				alert("공동현관 비밀번호 문자를 보내기 위한 주문서가 선택되지 않았습니다");
    				return false;
    			}
    			
    			if(confirm(orSize+" 개의 주문서에 공동현관 비밀번호 요청 문자를 보내시겠습니까?")){
    				
    				doubleSubmitCheck();
        			
        			if(doubleSubmitFlag == false){
        				
        				return false;
        			}
				
	    			var orSerialSpecialNumberList = new Array(orSize);
	    			
	    			
	    			for(var i=0; i<orSize; i++){
	    				orSerialSpecialNumberList[i]=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
	    				
	    			}

	    			$.ajax({
	    				type       : 'POST',
	    				data       : {
	    					"orSerialSpecialNumberList":orSerialSpecialNumberList
	    					
	    				},
	    				url        : '/delivery/config/cj_door_msg.do',
	    				success    : (data) => {		
	    					alert(decodeURI(data));
	    					
	    					
	    					
	    				},error	: (log) => {
	    					alert("서버 에러 발생. " + log);
	    				}
	    				
	    			});	
	    			
    			}
    			
    			
    		});
    		
    		$("#cjDelivDoorMsgKakaoButton").click(function(){

    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			
    			if(orSize == 0){
    				alert("공동현관 비밀번호 알림톡을 보내기 위한 주문서가 선택되지 않았습니다");
    				return false;
    			}
    			
    			if(confirm(orSize+" 개의 주문서에 공동현관 비밀번호 요청 알림톡을 보내시겠습니까?")){
    				
    				doubleSubmitCheck();
        			
        			if(doubleSubmitFlag == false){
        				
        				return false;
        			}
				
	    			var orSerialSpecialNumberList = new Array(orSize);
	    			
	    			
	    			for(var i=0; i<orSize; i++){
	    				orSerialSpecialNumberList[i]=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
	    				
	    			}

	    			$.ajax({
	    				type       : 'POST',
	    				data       : {
	    					"orSerialSpecialNumberList":orSerialSpecialNumberList
	    				},
	    				url        : '/delivery/config/cj_door_msg_kakao.do',
	    				success    : (data) => {		
	    					alert(data);

	    					
	    				},error	: (log) => {
	    					alert("서버 에러 발생. " + log);
	    				}
	    				
	    			});	
	    			
    			}

    			
    		});
    		

    		$("#orSeiralSpecialNumberAllSelect").click(function(){
    			
    			if($(this).is(":checked")){
    				$("input[name=orSerialSpecialNumberList]").prop("checked","checked");
    				
    			}else{
    				$("input[name=orSerialSpecialNumberList]").prop("checked","");
    				
    			}
    			
    		});
    		
    		$("input[name=orSerialSpecialNumberList]").click(function(){
    			
    			if($(this).is(":checked")){
    				
    				
    			}else{
    				
    				if($("#orSeiralSpecialNumberAllSelect").is(":checked")){
    					$("#orSeiralSpecialNumberAllSelect").prop("checked","");
    					
    				}
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
    		
    		$(".devideOrderButton").click(function(){
				
    			var orSerialSpecialNumber = $(this).val();
    			
    			window.open("/orders/config/devide.do?orSerialSpecialNumber="+orSerialSpecialNumber, "주문서 나누기" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");

    		});
    		
    		
			$("#doorPassKeywordListBtn").click(function(){    			
    			window.open("/delivery/config/door_pass_keyword.do", "공동현관 출입방법 키워드 목록" , "width=500px, height=620px, top=50px, left=50px, scrollbars=no");
    			
    		});
    		
    		
    		$("#orderIO").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			var orSerialSpecialNumberList = new Array(orSize);
    			var orderIOList = "";
    			
    			for(var i=0; i<orSize; i++){
    				orSerialSpecialNumberList[i]=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
    			}
    			
    			if(orSize > 0){ 
    				if(confirm(orSize+" 개의 주문서를 다운로드 하시겠습니까?")){
    	    			
    	    			var numListInput = document.createElement("input");
    	    			numListInput.name="orSerialSpecialNumberList";
    	    			numListInput.type="hidden";
    	    			numListInput.value=orSerialSpecialNumberList;
    	    			
    	    			var excelDownloadForm =  document.createElement("form")
    	    			excelDownloadForm.action="/security/orderIO.do";
    	    			excelDownloadForm.method="POST";
    	    			
    	    			excelDownloadForm.append(numListInput);
    	    			
    	    			$("#excelDownloadIframe").append(excelDownloadForm);
    	    			excelDownloadForm.submit();
    	    			$("#excelDownloadIframe").html("");
    	    			
    	    			orderFlag = true;
    	    			
    	    			if(orderFlag == true && labelFlag == true){
    	    				$("#sendingDelivButton").show();
    	    			}
    	    		}
    			}
    			
    		});
    		
    		$("#labelIO").click(function(){
    			var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
    			var orSerialSpecialNumberList = new Array(orSize);
    			var orderIOList = "";
    			
    			for(var i=0; i<orSize; i++){
    				orSerialSpecialNumberList[i]=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
    			}
    			
    			if(orSize > 0){ 
    				if(confirm("라벨지를 다운로드 하시겠습니까?")){
    	    			
    	    			var numListInput = document.createElement("input");
    	    			numListInput.name="orSerialSpecialNumberList";
    	    			numListInput.type="hidden";
    	    			numListInput.value=orSerialSpecialNumberList;
    	    			
    	    			var excelDownloadForm =  document.createElement("form")
    	    			excelDownloadForm.action="/security/product_label.do";
    	    			excelDownloadForm.method="POST";
    	    			
    	    			excelDownloadForm.append(numListInput);
    	    			
    	    			$("#excelDownloadIframe").append(excelDownloadForm);
    	    			excelDownloadForm.submit();
    	    			$("#excelDownloadIframe").html("");
    	    			
    	    			labelFlag = true;
    	    			
    	    			if(orderFlag == true && labelFlag == true){
    	    				$("#sendingDelivButton").show();
    	    			}
    	    			
    	    		}
    			}
    		});
    		
    		$("#cjDelivDoorCheckBtn").click(function(){
    			
    			$("input[data-deliv-enter-flag='false']").each(function(){
    				$(this).prop("checked","checked");
    				
    			});
    			
    			
    			
    		});
    		
    		$(".editCustomerInfoBtn").click(function(){
    			
    			var orSerialSpecialNumberList = $(this).val();
    			
    			window.open("/orders/config/combine_order.do?orSerialSpecialNumberList="+orSerialSpecialNumberList, "주문서 정보 변경" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
    				
    		});
    		
    		//택배사 고정 해제
    		$("#absInitDeliv").click(function(){
    			
    			var orSize = $("input[name=serialNumCheck]:checked").length;
    			
    			var orSerialSpecialNumberList = new Array(orSize);
    			
    			if(orSize == 0){
    				alert("해제할 수 있는 주문서가 존재하지 않습니다"); 
    				return false;
    				
    			}

    			if(confirm("배송회사 고정을 푸시겠습니까?")){
    				for(var i=0; i<orSize; i++){
    					orSerialSpecialNumberList[i]=$("input[name=serialNumCheck]:checked")[i].value;
    					
    				}
    				
    				
    				$.ajax({
    					type       : 'GET',
    					data       : {
    						"orSerialSpecialNumber":orSerialSpecialNumberList
    						
    					},
    					url        : '/orders/abs_init_deliv.do',
    					success    : (data) => {		
    						if(data > 0){
    							alert("배송회사 고정 해제");
    							location.reload();
    						}else{
    							alert("배송회사 고정 실패");
    							location.reload();
    						}
    						
    					},error	: (log) => {
    						alert("서버 에러 발생. " + log);
    					}
    					
    				});
    				
    			}
    			
    		});
    		
    	});
    	
    	
    	
    	function pageFunc(index){
			$("input[name=currentPage]").val(index);
			$("#epostPageForm").submit();
		}
		
    	var doubleSubmitFlag = false;

    	function doubleSubmitCheck(){
    	    if(doubleSubmitFlag){
    	        return doubleSubmitFlag;
    	    }else{
    	        doubleSubmitFlag = true;
    	        return false;
    	    }
    	}
    	
    </script>
    <style type="text/css">
    	.selectClass{
    		background-color: #FFA2A2;
    		color:white;
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
                            <h2 class="pageheader-title"> 송장부여 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문서 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 주문 입력 </a></li>
                                        <li class="breadcrumb-item"><a href="javascript:void(0);" class="breadcrumb-link"> 송장 부여 </a></li>
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
							<div class="card" style="background-color: #efeff6;">
								<div class="card-body">
									<h4> 주문서 진행 단계  </h4>
									
									<a href="<c:url value='/orders/order_page.do'/>" class="btn btn-success"> 주문서 입력</a>
									<a href="<c:url value='/order/config/search_except_addr_order.do'/>" class="btn btn-success"> 특수 지역 체크  </a>
									<a href="<c:url value="/order/matching/products_matching.do"/>" class="btn btn-success"> 상품 매칭 </a> 
									<a href="<c:url value="/order/matching/option_matching.do"/>" class="btn btn-success"> 옵션 매칭 </a>
									<a href="<c:url value='/config/freebie/apply.do'/>" class="btn btn-success"> 사은품 부여  </a>    
									<a href="<c:url value='/orders/delivery_msg_check.do'/>" class="btn btn-success"> 요구사항 체크 </a>
									<a href="<c:url value='/stock/stk_check.do'/>" class="btn btn-success"> 재고 할당 </a> 
									<a href="<c:url value='/orders/cancled_order_check.do'/>" class="btn btn-success"> 취소 주문  </a>
									<a href="<c:url value='/security/epost.do'/>" class="btn btn-success blinking"> 송장 부여  </a> 
								</div>
							</div>
						</div>
					</div>
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        	<div class="card">
	                        	<div class="card-body">
	                        		<form id="epostPageForm" action="<c:url value='/security/epost.do'/>" method="get">
	                        			<input type="hidden" name="searchCondition" >
			                        	<input type="hidden" name="searchKeyword">
			                        	<input type="hidden" name="currentPage" value="${OrderSearchVO.currentPage}">	                        		
			                            <div class="email-filters-right">
				                            <div class="btn-group">
				                            	<select class="form-control" name="ssPk">
				                            		<option value="0">판매처 전체</option>
				                            		<c:forEach var="ssList" items="${storeSectionList }">
				                            			<option value="${ssList.ssPk }">${ssList.ssName }</option>
				                            		</c:forEach>
				                            	</select>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name=recordCountPerPage>
													<option value="50"
														<c:if test="${OrderSearchVO.recordCountPerPage == 50 }">
															selected="selected"
														</c:if>
													>50 개씩</option>
													<option value="100"
														<c:if test="${OrderSearchVO.recordCountPerPage == 100 }">
															selected="selected"
														</c:if>
													>100 개씩</option>
													<option value="200"
														<c:if test="${OrderSearchVO.recordCountPerPage == 200 }">
															selected="selected"
														</c:if>
													>200 개씩</option>
													<option value="500"
														<c:if test="${OrderSearchVO.recordCountPerPage == 500 }">
															selected="selected"
														</c:if>
													>500 개씩</option>
													<option value="2000"
														<c:if test="${OrderSearchVO.recordCountPerPage == 2000 }">
															selected="selected"
														</c:if>
													>2000 개씩</option>
												</select>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name="specialRegionFlag">
													<option value="0"
														<c:if test="${OrderSearchVO.specialRegionFlag == 0 }">
															selected="selected"
														</c:if>
													> 모든 지역 </option>
													<option value="1"
														<c:if test="${OrderSearchVO.specialRegionFlag == 1 }">
															selected="selected"
														</c:if>
													> 확인된 특수지역 </option>
													<option value="2"
														<c:if test="${OrderSearchVO.specialRegionFlag == 2 }">
															selected="selected"
														</c:if>
													> 특수지역 </option>
												</select>
				                            </div>
				                            <div class="btn-group">
				                                <select class="form-control" name=totalQtyAlarm>
													<option value="12"
														<c:if test="${OrderSearchVO.totalQtyAlarm == 12 }">
															selected="selected"
														</c:if>
													> 총 합 12개 이상 표시</option>
													<option value="15"
														<c:if test="${OrderSearchVO.totalQtyAlarm == 15 }">
															selected="selected"
														</c:if>
													> 총 합 15개 이상 표시</option>
													<option value="20"
														<c:if test="${OrderSearchVO.totalQtyAlarm == 20 }">
															selected="selected"
														</c:if>
													> 총 합 20개 이상 표시</option>
													<option value="8"
														<c:if test="${OrderSearchVO.totalQtyAlarm == 8 }">
															selected="selected"
														</c:if>
													> 총 합 8개 이상 표시</option>
													<option value="-1"
														<c:if test="${OrderSearchVO.totalQtyAlarm == -1 }">
															selected="selected"
														</c:if>
													> 표시하지 않음 </option>
												</select>
				                            </div>
				                            
				                            <c:if test="${OrderSearchVO.edtFk == 5 }">
												<div class="btn-group">
					                                <select class="form-control" name="delivMsgFlag">
														<option value="0"
															<c:if test="${OrderSearchVO.delivMsgFlag == 0 }">
																selected="selected"
															</c:if>
														> 배송메세지 및 문앞 전부 표기 </option>
														<option value="1"
															<c:if test="${OrderSearchVO.delivMsgFlag == 1 }">
																selected="selected"
															</c:if>
														> 배송메세지 혹은 현관 비밀번호 있는 건만 </option>
													</select>
					                            </div>		
											</c:if>
				                            <div class="btn-group">
				                            	<select class="form-control" name="edtFk">
				                            		<option value="4"
				                            			<c:if test="${OrderSearchVO.edtFk == 4 }">
															selected="selected"
														</c:if>
				                            		>롯데택배 송장 부여</option>
				                            		
				                            		<option value="5"
				                            			<c:if test="${OrderSearchVO.edtFk == 5 }">
															selected="selected"
														</c:if>
				                            		>cj 새벽배송 송장 부여</option>
				                            		<%-- <option value="6"
				                            			<c:if test="${OrderSearchVO.edtFk == 6 }">
															selected="selected"
														</c:if>
				                            		>오늘의 픽업 송장 부여</option> --%>
				                            		<option value="1"
				                            			<c:if test="${OrderSearchVO.edtFk == 1 }">
															selected="selected"
														</c:if>
				                            		> 우체국 송장 부여</option>
				                            		<%-- <c:forEach var="edtlist" items="${edtList }">
				                            			<option value="${edtlist.edtPk }"> ${edtlist.edtType }</option>
				                            		</c:forEach> --%>
				                            	</select>
				                            </div>
				                            <div class="btn-group">
				                            	<input type="text" class="btn btn-light" id="dateStart" name="dateStart" style="width: 8em;" value="${OrderSearchVO.dateStart }"/> &nbsp; 
				                                <input type="text" class="btn btn-light" id="dateEnd" name="dateEnd" style="width: 8em;" value="${OrderSearchVO.dateEnd }"/>
				                            </div>
				                            <div class="btn-group">
				                            	<c:set var="insertingCountNum" value="1"/>
						                        <select class="form-control" name="insertingCount">
							                        <option value=""> 전체 차수 </option>
													<c:forEach var="isoList" items="${insertStoreOrderList }">
														<fmt:formatDate var='formatDate' value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/>
														<option value="${formatDate }"
															<c:if test="${OrderSearchVO.insertingCount == formatDate }">
																selected="selected"
															</c:if>
														>${insertingCountNum } 차 <fmt:formatDate value='${isoList.orRegdate }' pattern='yyyy-MM-dd HH:mm:ss'/></option>
														<c:set var="insertingCountNum" value="${insertingCountNum + 1 }"/>
													</c:forEach>
												</select>
					                        </div>
				                            <div class="btn-group" style="text-align: right;">
				                                <button class="btn btn-primary" type="submit"> 조건 검색 </button>
				                            </div>
				                        </div>
	                        		</form>
		                        </div>
	                        </div>
                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        	<div class="card">                        	
	                        	<div class="card-body border-top">
	                        		<div class="row">
	                        			<div class="form-group col-sm-6 pl-0">
	                        				<h3 class="navbar-brand" style="padding: 0px 0px 0px 20px; margin: 0px; color: #da0419;">
	                        					<c:if test="${OrderSearchVO.edtFk == 4 }">
													롯데택배 송장 가공중입니다
												</c:if>
												
	                        					<c:if test="${OrderSearchVO.edtFk == 5 }">
													cj 새벽배송 송장 가공중입니다
												</c:if>
                                        		<c:if test="${OrderSearchVO.edtFk == 6 }">
													오늘의픽업 송장 가공중입니다
												</c:if>
												<c:if test="${OrderSearchVO.edtFk == 1 }">
													우체국택배 송장 가공중입니다
												</c:if>
												<c:if test="${OrderSearchVO.edtFk == 7 }">
													팀프레시 송장 가공중입니다
												</c:if>
                                        	</h3>
	                        			</div>
                                        <div class="form-group col-sm-6 pl-0">
                                        	<p class="text-right">
												<button type="button" class="btn btn-primary" id="orderIO">  주문서 출력  </button>
												<!-- <button type="button" class="btn btn-primary" id="labelIO">  라벨지 출력  </button> -->		
												<c:if test="${OrderSearchVO.edtFk == 4 }">
													<button class="btn btn-warning" id="lotteDelivButton"> 롯데택배 임시송장 부여 </button>
												</c:if>		
												<c:if test="${OrderSearchVO.edtFk == 5 }">
													<button class="btn btn-warning" id="cjDelivButton"> cj 새벽배송 임시송장 부여 </button>
												</c:if>		
												<c:if test="${OrderSearchVO.edtFk == 7 }">
													<button class="btn btn-warning" id="teamFreshDelivButton"> 팀프레시 송장 부여 </button>
												</c:if>						
												<c:if test="${OrderSearchVO.edtFk == 6 }">
													<button class="btn btn-warning" id="allMergeBtn"> 선택 주문 전부 합포 </button>
													<button class="btn btn-warning" id="todayPickupDelivButton"> 오늘의 픽업 송장 부여 </button>
												</c:if>
												<c:if test="${OrderSearchVO.edtFk == 1 }">
													<button class="btn btn-danger" id="sendingDelivButton"> 우체국택배 송장 부여 </button>
												</c:if>
                                        	</p>											
                                        </div>
                                    </div>
	                            </div>
                        	</div>
                        	<c:if test="${!empty epostAbsDelivList }">
                        		<div class="card" id="orderController">
                            		<div class="card-header" id="headingSeven">
                                    	<h5 class="mb-0">
                                        	<button id="arcoBtn1" class="btn btn-link" data-toggle="collapse" data-target="#orderControl" aria-expanded="false" aria-controls="orderControl" type="button">
                                            	우체국택배 고정 주문서 확인하기
                                            </button>
                                        </h5>
                                    </div>
                                    <div id="orderControl" class="collapse" aria-labelledby="headingSeven" data-parent="#orderController">
                                    	<div class="card-body">
                                    		<button class="btn btn-primary btn-xs mb-2" type="button" id="absInitDeliv"> 택배사 고정 해제</button>
                                    		
	                                		<table class="table table-bordered">
		                                        <thead>
		                                            <tr style="text-align: center;">
		                                            	<th scope="col" colspan="1">
				                                        </th>
		                                                <th scope="col"> 구매자</th>
		                                                <th scope="col"> 수령자</th>
		                                                <th scope="col"> 주소 </th>
		                                                <th scope="col"> 출입방법 </th>
		                                            </tr>
		                                        </thead>
		                                        <tbody class="dataTable">
		                                        	<c:forEach var="epostAbsList" items="${epostAbsDelivList }">
		                                        		<tr>
		                                        			<td>
		                                        				<label class="custom-control custom-checkbox be-select-all" style="display: inline-block;">
												             	   <input class="custom-control-input serialNumCheck" type="checkbox" name="serialNumCheck" value="${epostAbsList.orSerialSpecialNumber }"><span class="custom-control-label"></span>
												                </label>
		                                        			</td>
		                                        			
		                                        			<td>${epostAbsList.orBuyerName } / ${epostAbsList.orBuyerContractNumber1 }</td>
		                                        			<td>${epostAbsList.orReceiverName } / ${epostAbsList.orReceiverContractNumber1 }</td>
		                                        			<td>${epostAbsList.orShippingAddress } ${epostAbsList.orShippingAddressDetail }</td>
		                                        			<td>${epostAbsList.orDelivEnter }</td>
		                                        		</tr>
		                                        	</c:forEach>
		                                        </tbody>
		                                    </table>
                                        </div>
                                   </div>
                                </div>
                        	
                        	</c:if>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <h5 class="card-header"> 미부여송장 목록 ( <fmt:formatNumber value="${OrderSearchVO.totalRecord }" pattern="#,###" /> 개) </h5>
                                <div class="card-body" style="padding-bottom: 0;">
                                	<button class="btn btn-brand btn-xs mb-2" id="delivStoppedAreaCheckBtn" type="button"> 우체국 발송 중단 지역 확인 </button>
                                	<button class="btn btn-primary btn-xs" id="doorPassKeywordListBtn"> 공동현관 키워드 목록 확인 </button>
                                	
                                	<c:if test="${OrderSearchVO.edtFk == 5 or OrderSearchVO.edtFk == 7 }">
										<button class="btn btn-success btn-xs" id="cjDelivDoorCheckBtn">공동현관 자동체크</button>
										<!-- <button class="btn btn-danger btn-xs" id="cjDelivDoorMsgButton"> 공동현관 비밀번호 요청 문자발송 </button> -->
										<button class="btn btn-danger btn-xs" id="cjDelivDoorMsgKakaoButton"> 공동현관 비밀번호 알림톡 </button>
									</c:if>
                                </div>
                                
                                <div class="card-body">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr style="text-align: center;">
                                            	<th scope="col" colspan="2">
			                                        <label class="custom-control custom-checkbox be-select-all" style="display: inline-block;">
									             	   <input class="custom-control-input chk_all" type="checkbox" name="chk_all" id="orSeiralSpecialNumberAllSelect"><span class="custom-control-label"></span>
									                </label>
		                                        </th>
                                                <th scope="col"> 구매자 / 수령자</th>
                                                <th scope="col"> 주소 </th>
                                                <th scope="col"> 출입방법 </th>
                                                <th scope="col"> 배송메세지 </th>
                                                <th scope="col"> 구매 상품 </th>
                                                <th scope="col"> 매칭 상품 </th>
                                                <th scope="col"> 총 합 </th>
                                            </tr>
                                        </thead>
                                        <tbody class="dataTable">
                                        	<c:if test="${!empty packingIrreOrderCounting and packingIrreOrderCounting != 0 }">
	                                        	<tr>
	                                        		<td id="packingOrder" colspan="9" style="background-color: #FFA2A2; text-align: center; color:white;">묶음 배송이 가능한 주문서가 있습니다</td>
	                                        	</tr>
	                                        	
                                        	</c:if>
                                        	<c:set var="tableCountings" value="1"/>
                                        	<c:set var="numCounting" value="${OrderSearchVO.firstRecordIndex + 1 }"/>
                                        	<c:set var="rowCounting" value="1"/>
	                                        	<c:set var="backgroundBoolean" value="1"/>
	                                        	
	                                        	<c:forEach var="orlist" items="${orderList }">
	                                        		
	                                        		<c:set var="totalProductQty" value="0" />
	                                        		<c:set var="rowSpans" value="${fn:length(orlist.orVoList)}"/>
	                                        		<c:forEach var="stocklist" items="${orlist.orVoList }">
	                                        			<tr
	                                        			<c:if test="${backgroundBoolean % 2 == 0}">
			                                        		style="background-color:#edeef4;"
			                                        	</c:if>
	                                        			>
	                                        			<c:if test="${rowCounting == 1 }">
	                                        				<td rowspan="${rowSpans }" class="customerInfo" style="width:50px; text-align: center;" data-table-info="${tableCountings }">
								                            	${numCounting }
								                            </td>
	                                        				<td rowspan="${rowSpans }" style="width:20px; text-align: center;" data-table-info="${tableCountings }">
	                                        					<label class="custom-control custom-checkbox be-select-all">
												             	   <input class="custom-control-input chk_all table-addr" value="${orlist.orSerialSpecialNumber }" 
												             	   
												             	   
												             	   data-table-addr="${orlist.orShippingAddress } ${orlist.orShippingAddressDetail }"
												             	   
												             	   data-deliv-enter-flag="${orlist.orDelivEnterFlag }"
												             	   type="checkbox" name="orSerialSpecialNumberList"
												             	   
												             	   >
												             	   <span class="custom-control-label"></span>
												                </label>
								                            </td>
			                                        		<td rowspan="${rowSpans }" style="width:260px; text-align: center;" data-table-info="${tableCountings }">
								                            	<p style="margin-bottom: 5px;">${orlist.orBuyerName } / ${orlist.orBuyerContractNumber1 }<br>
								                                ${orlist.orReceiverName } / ${orlist.orReceiverContractNumber1 }</p>
								                                <button class="btn btn-outline-primary btn-xs adminOrderRecord" value="${orlist.orSerialSpecialNumber }">cs내역</button>
								                                <button class="btn btn-outline-success btn-xs devideOrderButton" value="${orlist.orSerialSpecialNumber }">주문서 분리</button>
								                            </td>
								                            <td rowspan="${rowSpans }" style="width:300px; text-align: center;" data-table-info="${tableCountings }">
								                            	<p style="margin-bottom: 5px;">${orlist.orShippingAddress } ${orlist.orShippingAddressDetail }</p>
								                            	<button class="btn btn-outline-success btn-xs editCustomerInfoBtn" value="${orlist.orSerialSpecialNumber }">주소 변경</button>
								                            </td>
								                            <td rowspan="${rowSpans }" style="width:300px; text-align: center;" data-table-info="${tableCountings }">
								                            	${orlist.orDelivEnter }
								                            </td>
			                                        	</c:if>
			                                        	<c:if test="${!empty stocklist.productMatchingVOList }">	                                        			
			                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }"> 
													                 <td style="border: 1px solid lightgray; width:430px;" class="productInfo" data-table-product-info="${tableCountings }">
																		<span style="font-size: 12px;"> ${stocklist.orDeliveryMessage }</span>
					                                        		</td>
					                                        		
					                                        		<td style="border: 1px solid lightgray; width:430px;" class="productInfo" data-table-product-info="${tableCountings }">
																		<span style="font-size: 12px;"> ${stocklist.orProduct }</span><br>
																		<span style="font-size: 12px;"> ${stocklist.orProductOption } </span><a data-or-pk="${stocklist.orPk }" class="SelfdevisionOrderBtn" style="color:red; font-size:10px"> [ 나누기 ]</a>
					                                        		</td>
			                                        		</c:forEach>
	                                        			</c:if>
	                                        			<c:if test="${!empty stocklist.productMatchingVOList }">
	                                        					                                        			
			                                        		<c:forEach var="pmlist" items="${stocklist.productMatchingVOList }"> 
			                                        		
					                                        		<td style="border: 1px solid lightgray; width:430px;" class="productDetail" data-table-product-detail="${tableCountings }">
																		<c:if test="${!empty pmlist.optionMatchingVOList }">																		
							                                        		<c:forEach var="opmlist" items="${pmlist.optionMatchingVOList }">
								                                        		<c:forEach var="proOplist" items="${opmlist.productOptionList }">
								                                        			<c:if test="${!empty proOplist.optionName }">								                                        			
									                                        			<span style="font-size: 12px;
									                                        				<c:if test="${fn:contains(proOplist.productName, '세트') or fn:contains(proOplist.productName, '업소')}">
									                                        				color:red;
									                                        				</c:if>
									                                        			">${proOplist.productName } [ ${proOplist.optionName } ] ${proOplist.cfFk } 개</span><br>
									                                        			<c:set var="totalProductQty" value="${totalProductQty + proOplist.cfFk }"/>
								                                        			</c:if>
								                                        		</c:forEach>

							                                        		</c:forEach>
																		</c:if>
					                                        		</td>
					                                        		<td class="customerInfo totalProductQty" style="width:90px; text-align: center;" data-table-info="${tableCountings }" data-total-qty="${totalProductQty }">
										                            	${totalProductQty } 개 
										                            </td>
			                                        		</c:forEach>
	                                        			</c:if>
	                                        		</tr>
	                                        		<c:set var="rowCounting" value="${rowCounting+1 }"/>
	                                        		</c:forEach>
		                                        	<c:set var="backgroundBoolean" value="${backgroundBoolean + 1 }"/>
		                                        	<c:set var="rowCounting" value="1"/>
		                                        	<c:set var="numCounting" value="${numCounting + 1 }"/>
		                                        	<c:set var="tableCountings" value="${tableCountings + 1 }"/>
		                                        	<c:set var="totalProductQty" value="0"/>	                                        		
	                                        	</c:forEach>
	                                        	<tr>
	                                        		<td colspan="9">  
	                                        			<button class="btn btn-success btn-block" type="button" onclick="goTop()">맨 위로 올라가기</button>
	                                        		</td>
	                                        	</tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<nav aria-label="Page navigation example" style="text-align: center;">
								<ul class="pagination" style="display: -webkit-inline-box;">
									<c:if test='${OrderSearchVO.firstPage>1 }'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${OrderSearchVO.firstPage-1})">«</a></li>
									</c:if>
									<c:forEach var="i" step="1" end="${OrderSearchVO.lastPage}" begin="${OrderSearchVO.firstPage }">
										<li class="page-item 
											<c:if test='${OrderSearchVO.currentPage == i }'>
												active
											</c:if>
										"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${i})">${i }</a></li>
									</c:forEach>
									<c:if test='${OrderSearchVO.lastPage < OrderSearchVO.totalPage}'>
										<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="pageFunc(${OrderSearchVO.lastPage+1})">»</a></li>
									</c:if>
								</ul>
							</nav>
						</div>
                    </div>
            </div>
        <!-- /page content -->
        <iframe id="excelDownloadIframe" width="0" height="0">
		</iframe>       
		
		<script type="text/javascript">
		 $(document).ready(function(e){
		        genRowspan("totalProductQty");
	    		
		    });
		    
		 	function goTop(){		 		
		 		$('html').scrollTop(0);
		 	}
		 	
		    function genRowspan(className){
		    	
		        $("." + className).each(function( i , selector) {
		        	var alarmQty = $("select[name=totalQtyAlarm]").val();
		        	
		        	var values = $(selector).data("table-info");
		            var rows = $("." + className+"[data-table-info='"+values+"']");
		            
		            if (rows.length > 1) {
		                rows.eq(0).attr("rowspan", rows.length);
		                
		                if(rows.eq( (rows.length - 1)).data("total-qty") >= alarmQty && alarmQty != -1){
		                	rows.eq(0).css("color", "red");
		                	rows.eq(0).css("font-weight", "bolder");
		                }
		                
		                rows.eq(0).text( rows.eq( (rows.length - 1) ).text() );
		                rows.not(":eq(0)").prop("background-color","");
		                
		                rows.not(":eq(0)").remove();
		                
		                
		            }else{
		            	
		            	if(rows.eq( (rows.length - 1)).data("total-qty") >= alarmQty && alarmQty != -1){
		                	rows.eq(0).css("color", "red");
		                	rows.eq(0).css("font-weight", "bolder");
		                }
		                
		            	
		            }
		        });
		    }
		    
		    
		    $(function(){
		    	  $(".productInfo").mouseover(function(){
		    			var table_id = $(this).data("table-product-info");
		    			$(this).prev().addClass("products_table_hover");
		    			
		    			$(this).next().addClass("products_table_hover");
		    			$(this).next().next().addClass("products_table_hover");
		    			$(this).addClass("products_table_hover");
		    			$("td[data-table-info='"+table_id+"']").addClass("products_table_hover");
		    			
		    		});
		    		
		    		$(".productInfo").mouseleave(function(){
		    			$(this).prev().removeClass("products_table_hover");
		    			
		    			var table_id = $(this).data("table-product-info");
		    			$(this).next().next().removeClass("products_table_hover");
		    			$(this).next().removeClass("products_table_hover");
		    			$(this).removeClass("products_table_hover");
		    			$("td[data-table-info='"+table_id+"']").removeClass("products_table_hover");
		    			
		    		});
		    		
		    		$(".productDetail").mouseover(function(){
		    			$(this).next().addClass("products_table_hover");
		    			
		    			var table_id = $(this).data("table-product-detail");
		    			$(this).prev().addClass("products_table_hover");
		    			$(this).prev().prev().addClass("products_table_hover");
		    			$(this).addClass("products_table_hover");
		    			$("td[data-table-info='"+table_id+"']").addClass("products_table_hover");
		    			
		    		});
		    		
		    		
		    		$(".productDetail").mouseleave(function(){
		    			$(this).next().removeClass("products_table_hover");
		    			
		    			var table_id = $(this).data("table-product-detail");
		    			$(this).prev().prev().removeClass("products_table_hover");
		    			$(this).prev().removeClass("products_table_hover");
		    			$(this).removeClass("products_table_hover");
		    			$("td[data-table-info='"+table_id+"']").removeClass("products_table_hover");
		    			
		    		});
		    });
		</script>
		
        <%@ include file="../inc/bottom.jsp" %>