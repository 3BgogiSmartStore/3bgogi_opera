jQuery(document).ready(function($) {
	
	jQuery.ajaxSettings.traditional = true;
	
	var dataChangeFlag = false;
	var sendingFlag = false;
	var orSerialSpecialNumber;

	//주문서 나누기 
	$(".devideOrderButton").click(function(){
		
		var orSerialSpecialNumber = $(this).val();
		
		window.open("/orders/config/devide.do?orSerialSpecialNumber="+orSerialSpecialNumber, "주문서 나누기" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");

	});
	
	
	//주문서 정보 변경
	$(".editCustomerInfoBtn").click(function(){
		
		var orSerialSpecialNumberList = $(this).val();
		
		window.open("/orders/config/combine_order.do?orSerialSpecialNumberList="+orSerialSpecialNumberList, "주문서 정보 변경" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
			
	});
	
	//주문 삭제 기능
	$("#deleteOrderButton").click(function(){
		if(confirm("선택된 주문서를 정말 삭제하시겠습니까? ( 복구 불가 )")){			
			/*var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
			var orSerialSpecialNumberList = new Array(orSize);
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.id="csSearchForm";
			
			$("#csSearchIframe").append(csSearchForm);
			
			for(var i=0; i<orSize; i++){
				var orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
				$("#csSearchForm").append(orSerialSpecialNumberInput);
			}
			*/
			
			var csSearchFormData = csSearchFormInsertAndReturn('name', 'orSerialSpecialNumberList');
			
			$.ajax({
				type       : 'POST',
				data       : csSearchFormData,
				url        : '/orders/delete/customer_order.do',
				success    : function(data){
					if(data > 0){
						
						alert("주문서 삭제 완료");
						location.reload();
					}else{
						alert("삭제 실패");
					}
				}
				
			});
		}
	});
	
	function csSearchFormInsertAndReturn(attr_type, inputName){
		
		var orSize = $("input["+attr_type+"="+inputName+"]:checked").length;
		var orSerialSpecialNumberList = new Array(orSize);
		
		var csSearchForm =  document.createElement("form");
		csSearchForm.method="POST";
		csSearchForm.id="csSearchForm";
		
		$("#csSearchIframe").append(csSearchForm);
		
		for(var i=0; i<orSize; i++){
			var orSerialSpecialNumberInput = document.createElement("input");
			orSerialSpecialNumberInput.name=inputName;
			orSerialSpecialNumberInput.type="hidden";
			orSerialSpecialNumberInput.value=$("input["+attr_type+"="+inputName+"]:checked")[i].value;
			$("#csSearchForm").append(orSerialSpecialNumberInput);
		}
		
		console.log(orSize);
		console.log(orSerialSpecialNumberList);
		return jQuery("#csSearchForm").serialize();
	}
	
	$("#orSeiralSpecialNumberAllSelect").click(function(){
		
		if($(this).is(":checked")){
			$("input[name=orSerialSpecialNumberList]").prop("checked","checked");
			
		}else{
			$("input[name=orSerialSpecialNumberList]").prop("checked","");
			
		}
		
	});
	
	$("#orPkAllSelect").click(function(){
		
		if($(this).is(":checked")){
			$("input[name=orPks]").prop("checked","checked");
			
		}else{
			$("input[name=orPks]").prop("checked","");
			
		}
		
	});
	
	$("input[name=orSerialSpecialNumberList]").click(function(){
		
		if($(this).is(":checked")){
			
			
		}else{
			
			if($("#orSeiralSpecialNumberAllSelect").is(":checked")){
				$("#orSeiralSpecialNumberAllSelect").prop("checked","");
				
			}
		}
		
		/*		if($("#orSeiralSpecialNumberAllSelect").is(":checked")){
			$("input[name=orSerialSpecialNumberList]").prop("checked","checked");
		}else{
			$("input[name=orSerialSpecialNumberList]").prop("checked","");
		}
		*/
		
	});
	
	
	$("input[name=orPks]").click(function(){
		
		if($(this).is(":checked")){
			
			
		}else{
			
			if($("#orPkAllSelect").is(":checked")){
				$("#orPkAllSelect").prop("checked","");
				
			}
		}
		
		/*		if($("#orSeiralSpecialNumberAllSelect").is(":checked")){
			$("input[name=orSerialSpecialNumberList]").prop("checked","checked");
		}else{
			$("input[name=orSerialSpecialNumberList]").prop("checked","");
		}
		*/
		
	});

	// 주문서 나누기 orSerialSpecialNumberList
	$("#devideOrderButton").click(function(){

		var orderCheckCounting = $("input[name=orSerialSpecialNumberList]:checked").length;
		
		if(orderCheckCounting > 1){
			alert("한 번에 두 개 이상의 주문서를 나눌 수 없습니다. ");
			
		}else if(orderCheckCounting === 0){
			alert("나눌 주문서를 체크해주세요");
			
		}else{
			/*alert($("input[name=orSerialSpecialNumberList]:checked")[0].value);*/
			
			
			window.open("/orders/config/devide.do?orSerialSpecialNumber="+$("input[name=orSerialSpecialNumberList]:checked")[0].value, "주문서 나누기" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
			
		}
	});
	
	
	//주문서 합치기
	$("#combineOrderButton").click(function(){
		var orSize = $("input[data-deliv-weiting='1']:checked").length;
		
		if(orSize == 0){
			alert("합포 혹은 배송지 변경할 수 있는 주문서가 없습니다");
		}else{
			
			if(confirm(orSize+"개의 주문서를 합포 혹은 배송지 변경을 하시겠습니까?")){				
					var orSerialSpecialNumberList = new Array(orSize);
					
					for(var i=0; i<orSize; i++){
						orSerialSpecialNumberList[i]=$("input[data-deliv-weiting='1']:checked")[i].value;
						
					}
					
					window.open("/orders/config/combine_order.do?orSerialSpecialNumberList="+orSerialSpecialNumberList, "주문서 합치기" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
			}
		}
			
	});
	
	var orderOrPk = 0;
	var orderCancledFlag = false;
	
	$(document).on("click", "input[name=orPk]", function(){
		orderOrPk=$(this).val();
		orderCancledFlag = $(this).data("cancled-flag");
	});
	
	
	$(document).on("click", ".searchExcelTarget", function(){
		$("select[name=excelFlag]").val("2");
		$("input[name=excelOrFk]").val($(this).data("excel-or-fk"));
		
		$("#searchCustomerInfo").submit();
		
	});
	
	//환불 처리 하기 
	$("#refundOrderBtn").click(function(){
		var cancledCount = 0;
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}

		if(orderCancledFlag == true){
			alert("주문취소된 건은 환불처리가 불가능합니다");
			return false;
		}
		window.open("/orders/refund_order.do?orPk="+orderOrPk, "주문서 환불 " , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
		
	});
	
	//특별 요청 사항 처리하기
	$("#specialReqBtn").click(function(){

		if(!dataChangeFlag){
			alert("송장 삭제 및 발송을 취소 후 나눠주세요");
			return false;
		}
		
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}

		if(orderCancledFlag == true){
			alert("주문취소된 건은 특별 요청 처리가 불가능합니다");
			return false;
		}
		
		window.open("/orders/special_request.do?orPk="+orderOrPk, "특별 요청 사항 처리 " , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
		
	});
	
	
	//주문서 개수 나누기
	$("#SelfdevisionOrderBtn").click(function(){
		
		if(!dataChangeFlag){
			alert("송장 삭제 및 발송을 취소 후 나눠주세요");
			return false;
		}
		
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}
		
		window.open("/orders/config/self_devide_order.do?orPk="+orderOrPk, "주문서 나누기" , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
		
	});
	
	//주문서 삭제하기
	$("#deleteOrderTargetingDeleteBtn").click(function(){
		
		if(!dataChangeFlag){
			alert("송장 삭제 및 발송을 취소 후 삭제해주세요");
			return false;
		}
		
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}
		
		if(confirm("해당 건을 정말 삭제하시겠습니까?")){			
			$.ajax({
				type       : 'POST',
				data       : {
					"orPk":orderOrPk
				},
				url        : '/orders/delete/order_one.do',
				success    : function(data){
					if(data == true){		
						alert("해당 주문서 삭제 완료");
						location.reload();
					}else{
						alert("삭제 실패");
					}
				}
				
			});
		}
		
	});
	
	$("#excelGiftSetBtn").click(function(){
		
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다");
			
			return false;
		}if(sendingFlag == true){
			alert("발송된 주문서는 지정할 수 없습니다");
			
			return false;
		}
		
		window.open("/orders/devide/gift.do?orPk="+orderOrPk, "엑셀 주소지 입력" , "width=800px, height=620px, top=50px, left=50px, scrollbars=no");
		
	});
	
	//합포페이지 하나의 정보로 변경할 주문서 선택할 경우 액션
	$("input[name=changeOrderInfo]").change(function(){
		
		var orSerialSpecialNumber = $(this).data("serial-special-number");
		var orBuerId = $(this).data("buyer-id");
		var orSettlementDay = $(this).data("settlement-day");
		var orBuyerName = $(this).data("buyer-name");
		var orBuyerAnotherName = $(this).data("buyer-another-name"); 
		var orBuyerContractNumber1 = $(this).data("buyer-contract-number1");
		var orBuyerContractNumber2 = $(this).data("buyer-contract-number2");
		var orReceiverName = $(this).data("receiver-name");
		var orReceiverContractNumber1 = $(this).data("receiver-contract-number1");
		var orReceiverContractNumber2 = $(this).data("receiver-contract-number2");
		var orDeliveryMessage = $(this).data("delivery-message");
		var orShippingAddress = $(this).data("shipping-address");
		var orShippingAddressDetail = $(this).data("shipping-address-detail");
		var orShippingAddressNumber = $(this).data("shipping-address-number");
		var orSendingDeadline = $(this).data("sending-deadline");
		var orRegdate = $(this).data("regdate");
		
		
		$("#orSendingDeadline").val(orSendingDeadline);
		$("#orBuerId").val(orBuerId);
		$("#orSettlementDay").val(orSettlementDay);
		$("#orBuyerName").val(orBuyerName);
		$("#orBuyerAnotherName").val(orBuyerAnotherName);
		$("#orReceiverName").val(orReceiverName);
		$("#orShippingAddress").val(orShippingAddress);
		$("#orShippingAddressDetail").val(orShippingAddressDetail);
		$("#orShippingAddressNumber").val(orShippingAddressNumber);
		$("#orReceiverContractNumber1").val(orReceiverContractNumber1);
		$("#orReceiverContractNumber2").val(orReceiverContractNumber2);
		$("#orBuyerContractNumber1").val(orBuyerContractNumber1);
		$("#orBuyerContractNumber2").val(orBuyerContractNumber2);
		$("#orDeliveryMessage").val(orDeliveryMessage);
		$("#orSerialSpecialNumber").val(orSerialSpecialNumber);
		$("#orRegdate").val(orRegdate);
		
	});
	
	$("#combineOrderBtn").click(function(){
		
		var params = jQuery("#combineOrderForm").serialize();
		
		$.ajax({
		    type       : 'POST',
		    url        : '/orders/config/combine_order.do',
		    data       : params,
		    success    : function(data){
		    	if(data == true){
		    		alert("주문서 합포 완료");
		    		opener.location.reload();
		    		self.close();
		    	}else{
		    		alert("주문서 합포 실패");
		    	}
		    }
		});
	});
	
	// 상품 변경 클릭
	$("#changeProductBtn").click(function(){
		
		let orSize = $("input[name=orPks]:checked").length;
		
		if(orSize == 0){
			alert("삭제할 주문서가 선택되지 않았습니다");
			return false;
		}
		
		
		let orPks = new Array(orSize);
		
		for(var i=0; i<orSize; i++){
			orPks[i]=$("input[name=orPks]:checked")[i].value;
			
		}
		
		productMatching(orPks);
		
		/*if(orSize > 1){
			alert("한 번에 하나의 주문만 변경할 수 있습니다");
			
			return false;
		}
		
		let orPk = $("input[name=orPks]:checked").val();
		
		productMatching(orPk);
		*/
		
		
	});
	
	//송장번호 수기 입력
	$(".insertDelivNum").click(function(){
		var orSerialSpecialNumber = $(this).val();
		
		window.open("/orders/config/edit_deliv_num.do?orSerialSpecialNumber="+orSerialSpecialNumber, "송장번호 입력" , "width=500px, height=620px, top=50px, left=50px, scrollbars=no");
	});
	
	//상품 변경 페이지 들어가기
	function productMatching(orPk){
    	window.open("/order/config/multi_change_product_option.do?orPks="+orPk+"", "주문서 상품 변경" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
    	
    }
	
	//상품 추가 페이지 들어가기
	$("#addProductButton").click(function(){		
		var orSize = $("input[data-deliv-weiting='1']:checked").length;
		
		if(orSize == 0){
			alert("추가할 수 있는 주문건이 없습니다");
			
		}else{
			var orSerialSpecialNumberList = new Array(orSize);
			
			for(var i=0; i<orSize; i++){
				orSerialSpecialNumberList[i]=$("input[data-deliv-weiting='1']:checked")[i].value;
			}
			
			window.open("/orders/config/add_product_option.do?orSerialSpecialNumberList="+orSerialSpecialNumberList+"", "상품 추가" , "width=1500px, height=620px, top=50px, left=50px, scrollbars=no");
		}
		
	});
	
	$("#deleteDelivButton").click(function(){
		
		var orSize = $("input[data-deliv='1']:checked").length;
		var orSerialSpecialNumberList = new Array(orSize);
		
		if(orSize == 0){
			alert("송장이 부여된 주문건이 없습니다"); 
			return false;
			
		}
		
		if(confirm(orSize+" 개의 송장을 삭제하시겠습니까?")){
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.id="csSearchForm";
			
			$("#csSearchIframe").html("");
			$("#csSearchIframe").append(csSearchForm);
			
			for(var i=0; i<orSize; i++){
				var orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[data-deliv='1']:checked")[i].value;
				$("#csSearchForm").append(orSerialSpecialNumberInput);
			}

			var csSearchFormData = jQuery("#csSearchForm").serialize();
			
			
			$.ajax({
				type       : 'POST',
				data       : csSearchFormData,
				url        : '/security/epost/delete.do',
				success    : function(data){
					
					window.open("/security/deliv_result.do?delivResult="+data, "송장 삭제 결과" , "width=800px, height=700px, top=50px, left=50px");
					
				}
				
			});
		}
		
	});
	
	
	$("#outputBtn").click(function(){
		
		var orSize = $("input[data-deliv='1']:checked").length;
		
		if(orSize == 0){
			alert("발송할 수 있는 주문이 없습니다"); 
			return false;
		}
		
		if(orSize > 200){
			alert("한 번에 200개 이상의 송장을 발송처리 할 수 없습니다");
			return false;
		}
		if(confirm(orSize+" 개의 주문을 발송처리하시겠습니까?")){
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.id="csSearchForm";
			
			$("#csSearchIframe").html("");
			$("#csSearchIframe").append(csSearchForm);
			
			
			$("input[data-deliv='1']:checked").each(function(i, selected) {
				
				var orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orVoList["+i+"].orSerialSpecialNumber";
				
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[data-deliv='1']:checked")[i].value;
				
				var orDeliveryNum = document.createElement("input");
				orDeliveryNum.name="orVoList["+i+"].orDeliveryInvoiceNumber";
				
				orDeliveryNum.type="hidden";
				orDeliveryNum.value=$(selected).data("deliv-num");
			
				
				
				$("#csSearchForm").append(orSerialSpecialNumberInput);
				$("#csSearchForm").append(orDeliveryNum);
				
		     });

			
			/*for(var i=0; i<orSize; i++){
				var orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orVoList["+i+"].orSerialSpecialNumber";
				
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[data-deliv='1']:checked")[i].value;
				
				
				var orDeliveryNum = document.createElement("input");
				orDeliveryNum.name="orVoList["+i+"].orDeliveryInvoiceNumber";
				
				orDeliveryNum.type="hidden";
				orDeliveryNum.value=$("input[data-deliv='1']:checked")[i].data("deliv-num");
				
				$("#csSearchForm").append(orSerialSpecialNumberInput);
				$("#csSearchForm").append(orDeliveryNum);
				
				alert($("input[data-deliv='1']:checked")[i].data("deliv-num"));
				
				
			}*/
			
			var csSearchFormData = jQuery("#csSearchForm").serialize();
			
			
			$.ajax({
				type       : 'POST',
				data       : csSearchFormData,
				url        : '/orders/order_output.do',
				success    : function(data){
					alert("발송완료");
					location.reload();
					
				}
				
			});
		}
		
	});
	
	// 복수 매칭된 상품 나누기
	$("#multiMatchingDivBtn").click(function(){
		
		if(orderOrPk == 0){
			alert("선택된 주문서가 없습니다"); 
			return false;
		}
		
		if(confirm("해당 주문서를 매칭 상품 별로 나누시겠습니까?")){
			$.ajax({
				type       : 'GET',
				data       : {
					"orPk":orderOrPk
				},
				url        : '/orders/multi_matching_devide.do',
				success    : function(data){
					alert(data);
					location.reload();
					
				}
				
			});
		}
		

		
	});
	
	$("#outputCancledBtn").click(function(){
		var orSize = $("input[data-output='1']:checked").length;

		if(orSize == 0){
			alert("발송을 취소할 주문이 없습니다");
			return false;
		}
		
		if(confirm(orSize+" 개의 주문건을 발송 취소하시겠습니까?")){
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.id="csSearchForm";
			
			$("#csSearchIframe").html("");
			$("#csSearchIframe").append(csSearchForm);
			
			for(var i=0; i<orSize; i++){
				var orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orVoList["+i+"].orSerialSpecialNumber";
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[data-output='1']:checked")[i].value;
				$("#csSearchForm").append(orSerialSpecialNumberInput);
			}

			var csSearchFormData = jQuery("#csSearchForm").serialize();
			
			
			$.ajax({
				type       : 'POST',
				data       : csSearchFormData,
				url        : '/orders/order_output_canled.do',
				success    : function(data){		
					alert("발송 취소 완료");
					location.reload();
					
				}
				
			});
		}
		
	});
	
	$("#changeSendingDeadlineBtn").click(function(){
		var orSize = $("input[data-deliv-weiting='1']:checked, input[data-deliv='1']:checked").length;
		var orSerialSpecialNumberList = new Array(orSize);
			
		for(var i=0; i<orSize; i++){
			orSerialSpecialNumberList[i]=$("input[data-deliv-weiting='1']:checked, input[data-deliv='1']:checked")[i].value;
			
		}

		if(orSize == 0) {
			alert("변경할 주문 건이 없습니다");
			return false;
		}
		
		
		window.open('/orders/change/deadline.do?orSerialSpecialNumberList='+orSerialSpecialNumberList, "발송일 변경" , "width=430, height=500, top=200, left=1200, scrollbars=no");
	});
	
	$("#orderHistoryBtn").click(function(){
		
		if(orderOrPk == 0){
			alert("주문서를 선택해주세요");
			return false;
		}
		
		window.open('/log/order_history.do?orPk='+orderOrPk, "주문서 작업 기록" , "width=950, height=500, top=200, left=400, scrollbars=no");
		
	});

	$("#aligoSmsBtn").click(function(){
		
		var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
		var orSerialSpecialNumberList = new Array(orSize);
		
		if(orSize == 0){
			alert("문자 발송 대상자가 선택 되지 않았습니다"); 
			return false;
		}
		
		if(confirm(orSize+" 건에 대해 문자를 발송하시겠습니까? ")){
			
			
			
			var csSearchForm =  document.createElement("form");
			csSearchForm.method="POST";
			csSearchForm.target="aligoSms";
			csSearchForm.id="csSearchForm";

			$("#csSearchIframe").html("");
			$("#csSearchIframe").append(csSearchForm);
			
			
			$('input[name=orSerialSpecialNumberList]:checked').each(function(i, selected) {
				
				var AligoReceiver = document.createElement("input");
				AligoReceiver.name="aligoVOList["+i+"].receiver";
				AligoReceiver.type="hidden";
				AligoReceiver.value=$(selected).data("buyer-contract-number");
				
				var AligoDestination = document.createElement("input");
				AligoDestination.name="aligoVOList["+i+"].destination";
				AligoDestination.type="hidden";
				AligoDestination.value=$(selected).data("buyer-name");
				
				$("#csSearchForm").append(AligoReceiver);
				$("#csSearchForm").append(AligoDestination);
		     });
			
			window.open('', 'aligoSms', 'width=1000, height=700');
			frm = document.getElementById("csSearchForm");
			frm.action = "/aligo_msg/view.do";
			frm.target = "aligoSms";
			frm.method = "post";
			frm.submit();
		}
		
	});
	
	$("#reprintingDelivInvoiceBtn").click(function(){
		
		var orSize = $("input[data-deliv='1'][data-deliv-company='우체국택배']:checked, input[data-deliv='1'][data-deliv-company='방문수령']:checked, input[data-deliv='1'][data-deliv-company='퀵서비스']:checked, input[data-output='1'][data-deliv-company='우체국택배']:checked, input[data-output='1'][data-deliv-company='퀵서비스']:checked, input[data-output='1'][data-deliv-company='방문수령']:checked").length;
		var todayOrSize = $("input[data-deliv='1'][data-deliv-company='오늘의픽업']:checked, input[data-output='1'][data-deliv-company='오늘의픽업']:checked").length;
		
		if(orSize == 0 && todayOrSize == 0){
			alert("송장 재출력을 할 수 있는 주문건이 없습니다"); 
			return false;
			
		}if(orSize >= 1 && todayOrSize >= 1){
			alert("서로 다른 택배사를 동시에 재출력할 수 없습니다"); 
			return false;
			
		}
		
		var count = orSize == 0 ? todayOrSize : orSize;
		
		if(confirm(count+" 건의 송장을 재출력하시겠습니까? ( * 주의 : 송장이 중복으로 나올 수 있으니 송장 삭제 후 재출력 권장 * )")){		
			
			orSerialSpecialNumberList = new Array(count);
				
			window.open('', 'viewer', 'width=1000, height=700');
			
			delivForm =  document.createElement("form");
			delivForm.method="POST";
			
			if(orSize >= 1){
				delivForm.action = '/security/reprinting_deliv_invoice.do';
				
			}else if(todayOrSize >= 1){				
				delivForm.action = '/reprinting_today_pickup_deliv_invoice.do';
				
			}
			delivForm.target = "viewer";
			
			
			for(var i=0; i<count; i++){
				orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
				orSerialSpecialNumberInput.type="hidden";
				
				if(orSize >= 1){
					orSerialSpecialNumberInput.value=$("input[data-deliv='1'][data-deliv-company='우체국택배']:checked, input[data-deliv='1'][data-deliv-company='방문수령']:checked, input[data-deliv='1'][data-deliv-company='퀵서비스']:checked, input[data-output='1'][data-deliv-company='우체국택배']:checked, input[data-output='1'][data-deliv-company='퀵서비스']:checked, input[data-output='1'][data-deliv-company='방문수령']:checked")[i].value;
					
				}else if(todayOrSize >= 1){				
					orSerialSpecialNumberInput.value=$("input[data-deliv='1'][data-deliv-company='오늘의픽업']:checked, input[data-output='1'][data-deliv-company='오늘의픽업']:checked")[i].value;
					
					
				}

				delivForm.append(orSerialSpecialNumberInput);
				
				
			}
			
			$("#excelDownloadIframe").append(delivForm);
			
			delivForm.submit();
			$("#excelDownloadIframe").html("");
		}
		
		
	});
	
	$("#orderDelivInvoiceBtn").click(function(){
		
		var orSize = $("input[name='orSerialSpecialNumberList']:checked").length;
		
		if(orSize == 0){
			alert("주문서를 재출력할 수 있는 주문건이 없습니다"); 
			return false;
		}
	
		if(confirm(orSize+" 건의 주문서를 재출력하시겠습니까?")){			
			orSerialSpecialNumberList = new Array(orSize);
			delivForm =  document.createElement("form");
			
			delivForm.method="POST";
			delivForm.action = '/security/orderIO.do';

			for(var i=0; i<orSize; i++){
				orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[name='orSerialSpecialNumberList']:checked")[i].value;
				delivForm.append(orSerialSpecialNumberInput);
				
			}
			
			$("#excelDownloadIframe").append(delivForm);
			
			delivForm.submit();
			$("#excelDownloadIframe").html("");
		}
		
	});
	
	$("#labelDelivInvoiceBtn").click(function(){
		
		var orSize = $("input[data-deliv='1']:checked, input[data-output='1']:checked").length;
		
		if(orSize == 0){
			alert("라벨을 재출력할 수 있는 주문건이 없습니다"); 
			return false;
		}
	
		if(confirm(orSize+" 건에 대한 라벨지를 재출력하시겠습니까?")){			
			orSerialSpecialNumberList = new Array(orSize);
			delivForm =  document.createElement("form");
			delivForm.method="POST";
			delivForm.action = '/security/product_label.do';

			for(var i=0; i<orSize; i++){
				orSerialSpecialNumberInput = document.createElement("input");
				orSerialSpecialNumberInput.name="orSerialSpecialNumberList";
				orSerialSpecialNumberInput.type="hidden";
				orSerialSpecialNumberInput.value=$("input[data-deliv='1']:checked, input[data-output='1']:checked")[i].value;
				delivForm.append(orSerialSpecialNumberInput);
				
			}
			
			$("#excelDownloadIframe").append(delivForm);
			
			delivForm.submit();
			$("#excelDownloadIframe").html("");
		}
		
	});
	
	$("#cancleOrderPart").click(function(){
		
		let orSize = $("input[data-cancled='false']:checked").length;

		if(orSize == 0){
			alert("발송을 취소할 주문이 없습니다");
			return false;
		}
		
		if(confirm(orSize+" 개의 주문건을 발송 취소하시겠습니까?")){
			
			let orPks = new Array(orSize);
			
			for(var i=0; i<orSize; i++){
				orPks[i]=$("input[data-cancled='false']:checked")[i].value;
				
			}
			
			
			$.ajax({
				type       : 'POST',
				async	   : false,
				data       : {
					"orPks":orPks
				},
				url        : '/order_detail/cancle_each_order.do',
				success    : function(data){	
					if(data > 0 ){
						alert("주문 취소 처리 완료");
					}else if(data == 0){
						alert("주문 취소 처리 실패");
					}else{
						alert("서버 에러 ");
					}

					location.reload();
					
					
				}
				
			});
		}
		
	});
	
	
	$("button[name=orderHistoryShow]").click(function(){
		orderOrPk = $(this).data("orpk");
		window.open('/log/order_history.do?orPk='+orderOrPk, "주문서 작업 기록" , "width=950, height=500, top=200, left=400, scrollbars=no");
		
	});
	
	
	$("#cancleOrderRestore").click(function(){
		
		let orSize = $("input[data-cancled='true']:checked").length;

		
		if(orSize == 0){
			alert("취소 처리를 복구할 주문서가 선택되지 않았습니다");
			return false;
		}
		
		if(confirm(orSize+" 개의 주문건을 취소 처리를 복구하시겠습니까?")){
			
			let orPks = new Array(orSize);
			
			for(var i=0; i<orSize; i++){
				orPks[i]=$("input[data-cancled='true']:checked")[i].value;
				
			}
			
			
			$.ajax({
				type       : 'POST',
				async	   : false,
				data       : {
					"orPks":orPks
				},
				url        : '/order_detail/cancled_order_restore.do',
				success    : function(data){	
					
					if(data > 0 ){
						alert("주문 복구 처리 완료");
					}else if(data == 0){
						alert("주문 복구 처리 실패");
					}else{
						alert("서버 에러 ");
					}

					location.reload();
					
					
				}
				
			});
		}
		
	});
	
	
	$("#deleteOrderTargetingDeleteBtn").click(function(){
		
		let orSize = $("input[name=orPks]:checked").length;

		
		if(orSize == 0){
			alert("삭제할 주문서가 선택되지 않았습니다");
			return false;
		}
		
		if(confirm(orSize+" 개의 주문건을 삭제하시겠습니까?")){
			
			let orPks = new Array(orSize);
			
			for(var i=0; i<orSize; i++){
				orPks[i]=$("input[name=orPks]:checked")[i].value;
				
			}
			
			$.ajax({
				type       : 'POST',
				async	   : false,
				data       : {
					"orPks":orPks
				},
				url        : '/order_detail/delete_each_order.do',
				success    : function(data){	
					
					if(data > 0 ){
						alert("주문 삭제 처리 완료");
					}else if(data == 0){
						alert("주문 삭제 처리 실패");
					}else{
						alert("서버 에러 ");
					}

					location.reload();
					
				}
				
			});
		}
		
	});
	
	$("button[name='depositBtn']").click(function(){
		
		if(confirm("해당 주문건의 입금확인처리를 하겠습니까?")){
			
			orSerialSpecialNumber = $(this).val();
			
			$.ajax({
				type       : 'GET',
				url        : '/orders/check_deposit.do',
				data       : {"orSerialSpecialNumber" : orSerialSpecialNumber},
				success    : function(data){
					if(data == 0){
						alert("입금 확인처리 실패");
						location.reload();
						
					}else{
						alert("입금 확인처리 완료");
						location.reload();
					}
				}
			
			});
		}
		
	});
	
	
	
	$("#createOrderByOrderInfo").click(function(){
		orSize = $("input[name='orSerialSpecialNumberList']:checked").length;
		
		if(orSize == 0){
			alert("선택된 주문서가 없습니다"); 
			return false;
		}else if(orSize > 1){
			alert("한 번에 두 개 이상의 주문서를 생성할 수 없습니다"); 
			return false;
		}
		
		orSerialSpecialNumber = $("input[name='orSerialSpecialNumberList']:checked").val();
		
		window.open('/orders/create/order.do?orSerialSpecialNumber='+orSerialSpecialNumber, "새주문생성" , "width=1500, height=900, top=100, left=100, scrollbars=no");
		
	});
	
	
	$("#pickUpServiceBtn").click(function(){
		orSize = $("input[data-deliv-weiting='1']:checked").length;
		
		if(orSize == 0){
			alert("변경할 수 있는 주문서가 없습니다"); 
			return false;
			
		}else if(orSize > 1){
			alert("한 번에 두 개 이상의 주문서를 변경할 수 없습니다"); 
			return false;
		}
		
		orSerialSpecialNumber = $("input[data-deliv-weiting='1']:checked").val();
		
		window.open("/orders/pick_up_service.do?orSerialSpecialNumber="+orSerialSpecialNumber, "수령방식변경" , "width=500px, height=620px, top=50px, left=50px, scrollbars=no");
		
	});
	
	$("#orderRecordForm").submit(function(){
		
		aorReason = $("#aorReason").val();
		
		if(aorReason == ''){
			
			alert("c/s 내역을 입력해주세요 ");
			
			$("#aorReason").val("");
			$("#aorReason").focus();
			
			return false;
			
		}
		
		$.ajax({
			type       : 'POST',
			data       : {
				"aorSerialSpecialNumber":orSerialSpecialNumber,
				"aorReason":aorReason
			},
			url        : '/orders/order_record.do',
			success    : function(data){		
				alert("cs 내역 등록 완료");
				searchOrderRecordList(orSerialSpecialNumber);
				
			}
			
		});
		
		
		return false;
		
	});
	
	
	function searchOrderRecordList(orSerialSpecialNumber){
		adminOrderRecordListHTML = "";
		$.ajax({
			type       : 'GET',
			data       : {
				"orSerialSpecialNumber":orSerialSpecialNumber
			},
			url        : '/orders/order_record_ajax.do',
			success    : function(data){		
				
				if(data.size != 0){					
					$.each(data, function(){
						
						adminOrderRecordListHTML+='<div class="card-body">'
							+'<div class="review-block" style="font-size: 13px;">'
								+'<p class="review-text font-italic m-0">'+this.aorReason+'</p>'
								+'<span class="text-dark font-weight-bold"> '+this.aorAdminName+' / ( '+this.aorAdminId+' ) </span>'
								+'<small class="text-mute"> ( '+allFormatDate(this.aorRegdate)+' )</small>'
							+'</div>'
						+'</div>';
					});
					
					$("#adminOrderRecordList").html(adminOrderRecordListHTML);
					
				}
			
				
			}
			
		});
	}
	
	$("#createOrderByOldOrder").click(function(){
		
		var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
		
		
		var orSerialSpecialNumberList = new Array(orSize);
		
		if(orSize == 0){
			alert("선택된 주문서가 존재하지 않습니다"); 
			return false;
			
		}
		
		if(orSize != 1){
			alert("하나의 주문만 선택해주세요"); 
			return false;
			
		}
		
		if(confirm("해당 주문서를 재생성하시겠습니까?")){
			
			orSerialSpecialNumber = $("input[name=orSerialSpecialNumberList]:checked").val();
			
			$.ajax({
				type       : 'POST',
				data       : {
					"orSerialSpecialNumber":orSerialSpecialNumber
					
				},
				url        : '/orders/create_new_order_by_old_order.do',
				success    : (data) => {		
					if(data > 0){
						alert("새 주문 생성 완료");
						location.reload();
					}else{
						alert("주문 생성 실패");
						location.reload();
					}
					
				},error	: (log) => {
					alert("주문 생성 오류. 복사로 생성된 주문서거나 서버 에러입니다. " + log);
				}
				
			});
			
		}
		
	});
	
	
	if( $("#priceRange").is(":checked") == true){
		$("#minPrice, #maxPrice").removeAttr("readonly");
		
	}else{
		$("#minPrice, #maxPrice").attr("readonly","readonly");
		
	}
	
	$("#priceRange").click(function(){
		
		if( $("#priceRange").is(":checked") == true){
			$("#minPrice, #maxPrice").removeAttr("readonly");
			console.log("체크됨 = "+$("#priceRange").val());
		}else{
			$("#minPrice, #maxPrice").attr("readonly","readonly");
			console.log("체크풀림 = "+$("#priceRange").val());
			
			
		}
		
	});
	
	$("#minPrice, #maxPrice").click(function(){
		$("#minPrice, #maxPrice").removeAttr("readonly");
		
		$("#priceRange").prop("checked","checked");
		
		
	});
	
	$("#absEpostDeliv").click(function(){
		
		var orSize = $("input[name=orSerialSpecialNumberList]:checked").length;
		
		var orSerialSpecialNumberList = new Array(orSize);
		
		if(orSize == 0){
			alert("선택된 주문서가 존재하지 않습니다"); 
			return false;
			
		}

		if(confirm("우체국택배로 고정하시겠습니까?")){
			for(var i=0; i<orSize; i++){
				orSerialSpecialNumberList[i]=$("input[name=orSerialSpecialNumberList]:checked")[i].value;
				
			}
			
			$.ajax({
				type       : 'GET',
				data       : {
					"orSerialSpecialNumber":orSerialSpecialNumberList
					
				},
				url        : '/orders/abs_epost_deliv.do',
				success    : (data) => {		
					if(data > 0){
						alert("고정 완료");
						location.reload();
					}else{
						alert("고정 실패");
						location.reload();
					}
					
				},error	: (log) => {
					alert("서버 에러 발생. " + log);
				}
				
			});
			
		}
		
	});
	
	$("#doorPassButton").click(function(){
		orSize = $("input[name='orSerialSpecialNumberList']:checked").length;
		
		if(orSize > 1){
			alert("공동현관 비밀번호를 두 개 이상의 주문서에 동시 입력할 수 없습니다"); 
			return false;
		}
		
		orSerialSpecialNumber = $("input[name='orSerialSpecialNumberList']:checked").val();
		
		if( orSerialSpecialNumber == undefined){
			orSerialSpecialNumber = "";
			
		}
		
		window.open('/delivery/config/door_pass.do?orSerialSpecialNumber='+orSerialSpecialNumber, "공동현관 비밀번호 입력" , "width=700, height=900, top=100, left=100, scrollbars=no");
		
		
		
	});

	
}); // AND OF JQUERY