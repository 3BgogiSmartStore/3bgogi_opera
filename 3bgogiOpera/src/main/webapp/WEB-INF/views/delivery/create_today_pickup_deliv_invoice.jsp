<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="kr">
<head>
    <!-- Required meta tags -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">
    <title> 오늘의픽업 송장 부여 결과 </title>
	<style>
	/* ibmplexsans*/
    
    @font-face {
        font-family: 'ibmplexsans';
    }
    
    * {
        font-family: 'ibmplexsans';
        box-sizing: border-box;
        /*border: 1px solid;*/
    }

    .invoice-no {
        text-align: right;
        height: 18px;
        font-size: 14px;
    }

    .top-table {
        /*border: 2px solid black;*/
        /*border-top-width: 3px;*/
        /*border-radius: 4px;*/
        width: 100%;
    }

    .row {
        /*border-bottom: 2px solid black;*/
        overflow: hidden;
        width: 100%;
        vertical-align: top;
    }

    .cell {
        display: inline-block;
        padding: 2px 2px;
        float: left;
        height: 20px;
        line-height: 18px;
    }

    .table {
        position: relative;
    }

    .table .label {
        position: absolute;
        left: 0;
        top: -16px;
        height: 14px;
        line-height: 12px;
        /*border-top-left-radius: 5px;
		border-top-right-radius: 5px;*/
        /*background: black;*/
        -webkit-print-color-adjust: exact;
        font-size: 8px;
        color: white;
        padding: 2px 5px;
    }

    .top-table .first, .top-table .second, .top-table .third {
        /*border-right: 2px solid black;*/
        text-align: center;
    }

    .top-table .first, .top-table .third {
        width: 16%;
    }

    .top-table .second {
        width: 20%;
    }

    .top-table .fourth {
        width: 25%;
    }

    .top-table-right {
        position: absolute;
        right: -1px;
        top: -2px;
        text-align: right;
        background: white;
        /*border: 1px solid black;
		border-top-right-radius: 2px;
		border-bottom-left-radius: 20px;*/
        padding-top: 5px;
        padding-left: 5px;
        padding-right: 5px;
        overflow: hidden;
        z-index: 10;
    }

    .top-table-right img {
        width: 90px;
        height: 65px;
    }

    .table .row:last-of-type {
        border-bottom: none;
    }

    .top-table .address {
        font-size: 10px;
        height: 24px;
        width: 83%;
        line-height: 24px;
        padding: 0 5px;
    }

    .top-table .message {
        font-size: 13px;
        width: 66%;
        line-height: 18px;
        text-overflow: ellipsis;
        overflow: hidden;
    }

    .top-table .twoline .cell {
        height: 36px;
    }

    .top-table .twoline .first {
        line-height: 33px;
    }

    .right-table .cell {
        height: 16px;
        line-height: 13px;
    }

    .right-table .threeline .address {
        height: 30px;
    }

    .main .left {
        float: left;
        width: 66%;
    }

    .main .right {
        float: left;
        width: 34%;
        padding-left: 5px;
        vertical-align: top;
    }

    .right-table {
        font-size: 12px;
    }

    .right-table .first {
        width: 60px;
        text-align: center;
    }

    .qr-zone {
        height: 70px;
        position: relative;
        margin-bottom: 5px
    }

    .qrcode {
        width: 80px;
        position: relative;
        left: -10px;
        top: -10px;
        z-index: -1
    }

    .logo-zone {
        position: absolute;
        top: 0;
        right: 0;
        width: 120px;
    }


    .logo-zone img {
        margin-left: 5px;
        margin-top: 0;
        margin-bottom: 5px;
        max-width: 120px;
        max-height: 80px;
        float: right;
    }

    .barcode-zone {
        font-size: 11px;
        text-align: center;
    }

    .barcode {
        margin-top: 5px;
        width: 170px;
        height: 35px;
    }
    
    .left-table {
        font-size: 13px;
        margin-top: 10px;
        max-height: 240px;
        height: 240px;
        
    }

    .left-table .row.main {
        height: 100%;
    }

    .left-table .row.main .box-text {
        margin: 5px;
        line-height: 17px;
    }

    .left-table .header .cell {
        border-bottom: none;
        text-align: center;
    }

    .left-table .cell.name {
        display: flex;
        justify-content: space-between;
        width: 50%;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .left-table .name-size {
        width: 80%;
        white-space: nowrap;
        overflow: hidden;
    }

    .left-table .quantity-size {
        width: 20%
    }

    .left-table .cell.count {
        width: 5%;
        text-align: center;
    }

    .left-table .header .cell.count {
        border-right: none;
    }

    .dong-zone {
        width: 100%;
        text-align: center;
        font-size: 35px;
        font-weight: bold;
    }

    .page-2 .left-table {
        float: left;
        width: 500px;
    }
		
	</style>
	<style media="screen">
		
    .printDiv {
        padding: 5px;
        width: 600px;
        overflow: auto;
    }

    .mt2 {
        margin-top: 2px;
    }
		
	</style>
	<style media="print">
		
   
    .page-divide {
        page-break-after: always;
    } 
    

    .page-gap {
        margin-top: 30mm;
    }

    .printDiv {
        padding: 5px;
        width: 100%;
        overflow: auto;
    }

    .print_Btn {
        display: none;
    }

    .mt10 {
        margin-top: 10mm;
    }

    .mt2 {
        margin-top: 2mm;
    }

    .mb10 {
        margin-bottom: 10mm;
    }

    .logo-zone img {
        max-width: 25mm;
        -webkit-print-color-adjust: exact;
    }

    .invoice-no {
        margin-right: 1mm;
    }

    .top-table {
        margin-top: 2mm;

    }

    .top-table .first, .top-table .third {
        width: 23mm;
    }

    .top-table .second {
        width: 34mm;
    }

    .top-table .fourth {
        width: 42mm;
    }

    .right {
        margin-top: 2mm;
    }

    .top-table .address {
        font-size: 10px;
    }

    .barcode {
        margin-top: 2mm !important;
        width: 47mm !important;
        height: 10mm !important;
    }

    .dong-zone {
        width: 100%;
        text-align: center;
        font-size: 35px;
        font-weight: bold;
    }

    .left-table .row.main .box-text {
        font-size: 13px;
    }


    .page-2 .left-table {
        float: left;
        width: 95mm;
    }


	</style>
</head>
<body>
	<c:if test="${!empty orList }">
		<c:forEach var="orderCount" begin="0" step="1" end="${fn:length(orList) - 1}">
			<div class="printDiv"
				<c:if test="${orderCount != 0}">
					style="padding-top: 3.5mm;"
				</c:if>
				
			>
				<div class="invoice-no">${orList[orderCount].orDeliveryInvoiceNumber }</div>
				<div class="table top-table">
					<div class="label"></div>
					<div class="top-table-right"></div>
					<div class="row">
						<div class="cell first"></div>
						<div class="cell second">
							${orList[orderCount].orReceiverName }
						</div>
						<div class="cell third"></div>
						<div class="cell fourth">
							${orList[orderCount].orReceiverContractNumber1 }
						</div>
					</div>
					<div class="row">
						<div class="cell address">
							${orList[orderCount].orShippingAddress } ${orList[orderCount].orShippingAddressDetail }  
						</div>
					</div>
					
					<div class="row twoline">
						<div class="cell first"></div>
						<div class="cell message">
							${orList[orderCount].orDeliveryMessage }
						</div>
					</div>
				</div>
				<div class="main">
					<div class="left">
						<div class="table left-table">
							<div class="row main">
								<div class="box-text">
									<c:forEach var="i" begin="0" step="1" end="${fn:length(orList[orderCount].productOptionList) - 1}">		
											${orList[orderCount].productOptionList[i].productName } [ ${orList[orderCount].productOptionList[i].optionName} ] ${orList[orderCount].productOptionList[i].anotherOptionQty } 개
											<c:if test="${i != fn:length(orList[orderCount].productOptionList) - 1 }">
												<br>
											</c:if>
									</c:forEach>
								</div>
							</div>
						</div>			
					</div>
					<div class="right">
						<div class="qr-zone">
							<!-- qr코드 들어가는 곳 -->
							<div class="logo-zone">
								<img src="${pageContext.request.contextPath}/resources/images/3b_logo.png">
								
								
							</div>
						</div>
						<div class="table right-table">
							<div class="label"></div>
							<div class="row">
								<div class="cell first"></div>
								<div class="cell">삼형제고기</div>
								
							</div>
						</div>
						<div class="barcode-zone">
							<svg class="deliv-zip-barcode" data-barcodes="${orList[orderCount].orDeliveryInvoiceNumber }">
								
							</svg>
							
 							<!-- <img class="deliv-invoice-img"> -->
							<br>
							<div>${orList[orderCount].orDeliveryInvoiceNumber }</div>
							<div class="order-number">${orList[orderCount].orSerialSpecialNumber }</div>
						</div>
						
						<div class="dong-zone">${orList[orderCount].orUserColumn5 }</div>
					</div>
				</div>
			</div>
			<c:if test="${orderCount != fn:length(orList) - 1 }">
				<div class="page-divide"></div>
				<div class="page-gap"></div>
			</c:if>
		</c:forEach>
	</c:if>
</body>

<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/jsbarcode/3.6.0/JsBarcode.all.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/qrcode/qrcode.min.js"></script>
 <script type="text/javascript">
 $(function(){

		$(".deliv-zip-barcode").each(function(idx, items){
		
			var number = $(this).data("barcodes");
		
			JsBarcode($(".deliv-zip-barcode")[idx], number, {
			  text: number.match(/.{1,4}/g).join("  "),
			  width: 1.5,
			  
			  height: 60,
			  fontSize: 14,		
			  marginBottom : 10,
			  format:"code128",
			  displayValue: false
			  
			});
			
			
			var svg = $(".deliv-zip-barcode")[idx];
			
			var qrcode = new QRCode($(".qr-zone")[idx], {
				width : 50, 
				height : 50
				
			});
			
			qrcode.makeCode(number);
			
			/* $(".deliv-zip-barcode")[idx].css("display","none"); */
			
			/* $(this).css("display","none");
			var xml = new XMLSerializer().serializeToString(svg);
			var base64 = 'data:image/svg+xml;base64,' + btoa(xml);
			var img = $(".deliv-invoice-img")[idx];
			img.src = base64;
			
			img.width=170;  */
	
		});
		
		
		$(".barcode-zone > img").addClass("mt2 barcode");
		
		/* $(".qr-zone > img").addClass("qrcode");  */
		
	});
 </script>
</html>
