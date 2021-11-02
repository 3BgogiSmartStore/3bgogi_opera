jQuery(document).ready(function($) {
	var regNumber = /(\d)/;

	var timerId = null;

	var SetTime = 300;

	var auth = false;
	
	var doubleSubmitFlag = false;

	function doubleSubmitCheck(){
	    if(doubleSubmitFlag){
	        return doubleSubmitFlag;
	    }else{
	        doubleSubmitFlag = true;
	        return false;
	    }
	}
	

	function msg_time() {

		m = Math.floor(SetTime / 60) + "분 " + (SetTime % 60) + "초";

		var msg = "인증시간에 남은 시간은 <font color='red'>" + m + "</font> 입니다.";

		document.getElementById("authNumCounting").innerHTML = msg;

		SetTime--;

		if (SetTime < 0) {
			clearInterval(timerId);
			alert("인증 시간이 전부 소진되었습니다. 다시 인증해주세요.");
			document.getElementById("authNumCounting").innerHTML = "";
			SetTime = 300;
			$("#countingDiv").hide();
		}

	}

	$("#authBtn").click(function() {

		$("#countingDiv").show();

		timerId = setInterval(msg_time, 1000);

		$.ajax({
			type : 'GET',
			url : '/seller/auth.do',
			success : function(data) {
				alert(data);

			}

		});

	});
	

	$("#authNumCheckBtn").click(function() {
		authNum = $("#authNumCheck").val();

		if (authNum == '' || authNum == null) {

			alert("인증번호를 입력해주세요");
			event.preventDefault();
			return false;
		}

		$.ajax({
			type : 'GET',
			data : {
				"authNum" : authNum
			},
			url : '/seller/auth_check.do',
			success : function(data) {
				if (data == 0) {
					alert("인증번호가 다릅니다");
					$("#authNumCheck").focus();
					auth = false;
					
				} else {
					$("#authSendingDiv").hide();
					$("#countingDiv").hide();
					$("#authDiv").hide();
					$("#exchangeDiv").show();
					$("#exchangePriceDiv").show();
					$("#bankNmDiv").show();
					$("#bankAccountDiv").show();
					auth = true;
					
					alert("인증 완료");

				}

			}

		});
	});

	$("#sellerExchangeForm").submit(function() {

		seBankNmTemp = $("#seBankNmTemp").val();
		seBankAccountTemp = $("#seBankAccountTemp").val();
		authNumCheck = $("#authNumCheck").val();

		if (auth == false) {
			alert("문자 인증이 되지 않았습니다.");
			
			return false;
		}if (seBankNmTemp == '') {
			alert("은행명을 입력해주세요");
			$("input[name=seBankNmTemp]").focus();
			event.preventDefault();
			return false;
			
		}if (seBankAccountTemp == '') {
			alert("계좌번호를 입력해주세요");
			$("input[name=seBankAccountTemp]").focus();
			event.preventDefault();
			return false;
			
		}

		$("#seBankNm").val(seBankNmTemp);
		$("#seBankAccount").val(seBankAccountTemp);
		$("#userAuth").val(authNumCheck);

		doubleSubmitCheck();
		
		if(doubleSubmitFlag == false){
			
			return false;
		}
	});

}); // AND OF JQUERY