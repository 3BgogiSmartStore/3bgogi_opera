<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="kr">
<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title> 환전 신청 </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/js/seller_exchange.js"></script>
</head>
<body>
	<div class="dashboard-main-wrapper">
		<div class="dashboard-header">
			<nav class="navbar navbar-expand-lg bg-white fixed-top">
				<a class="navbar-brand" href="<c:url value='/seller/search.do'/>"
					style="color: #FFA2A2;"> 삼형제고기 셀러 </a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon fas fa-angle-down"></span>
				</button>
			</nav>
		</div>
		<div class="nav-left-sidebar sidebar-dark">
			<div class="menu-list">
				<nav class="navbar navbar-expand-lg navbar-light">
					<a class="d-xl-none d-lg-none" href="#"> 메뉴 열람 </a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarNav">
						<ul class="navbar-nav flex-column">
							<%-- <li class="nav-item"><a class="nav-link" href="<c:url value='/seller/search.do'/>"> 판매량 조회 </a></li> --%>
							<li class="nav-item"><a class="nav-link" href="<c:url value='/seller/exchange.do'/>"> 환전 신청 </a></li>
							<li class="nav-item"><a class="nav-link" href="<c:url value='/seller/exchange_history.do'/>"> 환전 신청 내역 </a></li>
							<li class="nav-item"><a class="nav-link" href="<c:url value='/seller/info.do'/>"> 내 정보 </a></li>
							<li class="nav-item"><a class="nav-link" href="<c:url value='/logout.do'/>"> 로그아웃 </a></li>
							
						</ul>
					</div>
				</nav>
			</div>
		</div>
		<div class="dashboard-wrapper">
			<div class="container-fluid dashboard-content">
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">환전 신청</h2>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="row">
							 <c:set var="totalQty" value="0"/>
							<c:set var="totalPrice" value="0"/>
							<c:set var="totalChangePosivPrice" value="0"/>
	                        <c:if test="${!empty sellerProdList }">
								<c:forEach var="prodList" items="${sellerProdList }">
									<c:forEach var="prodcoms" items="${prodComsList }">
										<c:if test="${prodcoms.key eq  prodList.orProductNumber}">
										
											<c:set var="totalPrice" value="${totalPrice + prodList.orTotalPrice }"/>
											<fmt:parseNumber var="formatPrice" value="${ (  prodList.orTotalPrice * prodcoms.value ) }" integerOnly="true"/>
											<c:set var="totalChangePosivPrice" value="${ totalChangePosivPrice + formatPrice }"/>
										</c:if>
										
									</c:forEach>
								</c:forEach>
							</c:if>
	                        <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
	                        	<div class="card border-3 border-top border-top-primary">
	                            	<div class="card-body">
	                                	<h5 class="text-muted"> 환전 가능 금액 </h5>
	                                    <div class="metric-value d-inline-block">
	                                    	<h2 class="mb-1">
	                                    		<c:if test="${!empty holdingExchange }">
	                                    			환전 진행중인 건이 있습니다
	                                    		</c:if>
	                                    		<c:if test="${empty holdingExchange }">
		                                    		<c:if test="${exchangeMin <= (totalChangePosivPrice - holdingExchange.seExchangePrice  - totalExchangePrice )}">
		                                    			<fmt:formatNumber value="${totalChangePosivPrice - holdingExchange.seExchangePrice  - totalExchangePrice }" pattern="#,###" /> 원
		                                    		</c:if>	
		                                    		<c:if test="${exchangeMin > (totalChangePosivPrice - holdingExchange.seExchangePrice   - totalExchangePrice )}">
		                                    			판매량이 부족해 환전할 수 없습니다.
		                                    		</c:if>
	                                    		</c:if>
	                                    	</h2>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
							<div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
	                        	<div class="card border-3 border-top border-top-primary">
	                            	<div class="card-body">
	                                	<h5 class="text-muted"> 환전 최소 금액 </h5>
	                                    <div class="metric-value d-inline-block">
	                                    	<h2 class="mb-1"><fmt:formatNumber value="${exchangeMin }" pattern="#,###"/> 원</h2>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                       <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12">
	                        	<div class="card border-3 border-top border-top-primary">
	                            	<div class="card-body">
	                                	<h5 class="text-muted"> 환전 완료 금액 </h5>
	                                    <div class="metric-value d-inline-block">
	                                    	<h2 class="mb-1">
	                                    		<fmt:formatNumber value="${totalExchangePrice }" pattern="#,###"/> 원
	                                    	</h2>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
						</div>
					</div>
					
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h4 class="card-header"> 판매 기록 </h4>
							<div class="card-body">
								<table class="table table-hover">
									<thead>
										<tr style="text-align: center;">
											<th>날짜</th>
											<th>상품명</th>
											<th>
												상품 판매 금액
											</th>
											<th>판매 환전 금액 <br> ( % 적용)</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty sellerProdList }">
											<tr>
												<td colspan="4" style="text-align: center;">
													판매된 상품내역이 존재하지 않습니다
												</td>
											</tr>
										</c:if>
										<c:if test="${!empty sellerProdList }">
											<c:set var="totalQty" value="0"/>
											<c:set var="totalPrice" value="0"/>
											<c:set var="totalChangePosivPrice" value="0"/>
											<c:forEach var="prodList" items="${sellerProdList }">
												<tr>
													<td style="text-align: center;">
														${prodList.resDate }
													</td>
													<td>
														${prodList.orProduct }
													</td>
													<td style="text-align: center;">
														<fmt:formatNumber value="${prodList.orTotalPrice }" pattern="#,###"/> 원
														<c:set var="totalPrice" value="${totalPrice + prodList.orTotalPrice }"/>
													</td>
													<td style="text-align: center;">
													
														<c:forEach var="prodcoms" items="${prodComsList }">
															<c:if test="${prodcoms.key eq  prodList.orProductNumber}">
															
																<fmt:parseNumber var="formatPrice" value="${ (  prodList.orTotalPrice * prodcoms.value ) }" integerOnly="true"/>
																<c:set var="totalChangePosivPrice" value="${ totalChangePosivPrice + (  formatPrice ) }"/>
																<fmt:formatNumber value="${formatPrice }" pattern="#,###"/> 원
																
																<br>(${prodcoms.value * 100 } % 적용) 
															</c:if>
															
														</c:forEach>
														
														<%-- <fmt:parseNumber var="formatPrice" value="${ (  prodList.orTotalPrice * 0.15 ) }" integerOnly="true"/>
														<c:set var="totalChangePosivPrice" value="${ totalChangePosivPrice + (  formatPrice ) }"/>
														<fmt:formatNumber value="${formatPrice }" pattern="#,###"/> 원
														
														<br>(15% 적용) --%>
													</td>
												</tr>
											</c:forEach>
											<tr>
												<td colspan="2" style="text-align: center;"> 총 합 </td>
												<td style="text-align: center;">
													<fmt:formatNumber value="${totalPrice }" pattern="#,###"/> 원
												</td>
												<td style="text-align: center;">
													<fmt:formatNumber value="${totalChangePosivPrice }" pattern="#,###"/> 원
												</td>
											</tr>
											<c:if test="${exchangeMin <= (totalChangePosivPrice - holdingExchange.seExchangePrice  - totalExchangePrice )}">
												<td colspan="2" style="text-align: center;">
													환전 가능 금액
												</td>
												<td colspan="2" style="text-align: center;">
													<fmt:formatNumber value="${totalChangePosivPrice }" pattern="#,###"/> 원 (환전 금액) 
													- 
													<fmt:formatNumber value="${totalExchangePrice }" pattern="#,###"/> 원 (환전 완료 금액) 					
													= 
	                                    			<fmt:formatNumber value="${totalChangePosivPrice - holdingExchange.seExchangePrice  - totalExchangePrice }" pattern="#,###" /> 원 ( 환전 가능 금액 )
												</td>
	                                    	</c:if>	
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
					
					<c:if test="${empty seVO }">
						<c:if test="${exchangeMin <= (totalChangePosivPrice - holdingExchange.seExchangePrice   - totalExchangePrice )}">
							<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="card">
									<div class="card-header">
										<h4> 환전 정보 입력하기 ( 예금주가 다를 경우 환전이 불가능합니다 )</h4>
									</div>
									<form id="sellerExchangeForm" class="card-body" name="sellerExchangeForm" action="<c:url value='/seller/exchange.do'/>" method="POST">
										<input type="hidden" id="userAuth" name="userAuth">
										<input type="hidden" id="seExchangePrice" name="seExchangePrice" value="<fmt:formatNumber value='${(totalChangePosivPrice - holdingExchange.seExchangePrice   - totalExchangePrice ) }' pattern='####'/>">
										<input type="hidden" id="seBankNm" name="seBankNm">
										<input type="hidden" id="seBankAccount" name="seBankAccount">
										
		                            	<div id="exchangePriceDiv" class="form-group row" style="display: none;">
		                            		<label class="col-12 col-sm-3 col-form-label text-sm-right">환전 금액</label>
		                            		<div class="col-12 col-sm-8 col-lg-6">
		                            			<input id="seExchangePriceTemp" name="seExchangePriceTemp" class="form-control" type="text" placeholder="" value="<fmt:formatNumber value='${(totalChangePosivPrice - holdingExchange.seExchangePrice   - totalExchangePrice ) }' pattern='#,###'/> 원" readonly="readonly">
		                            		</div>
		                            	</div>
		                            	<div id="bankNmDiv" class="form-group row" style="display: none;">
		                            		<label class="col-12 col-sm-3 col-form-label text-sm-right">은행명</label>
		                            		<div class="col-12 col-sm-8 col-lg-6">
		                            			<input id="seBankNmTemp" name="seBankNmTemp" class="form-control" type="text" placeholder="은행명을 입력해주세요" >
		                            		</div>
		                            	</div>
		                            	<div id="bankAccountDiv" class="form-group row" style="display: none;">
		                            		<label class="col-12 col-sm-3 col-form-label text-sm-right">계좌번호</label>
		                            		<div class="col-12 col-sm-8 col-lg-6">
		                            			<input id="seBankAccountTemp" name="seBankAccountTemp" class="form-control" type="text" placeholder="( - 없이 입력해주세요)" >
		                            		</div>
		                            	</div>
		                            	<div id="authSendingDiv" class="form-group row">
		                            		<label class="col-12 col-sm-3 col-form-label text-sm-right"> 인증번호 받기 </label>
		                            		<div class="col-12 col-sm-8 col-lg-6">
		                            			<button id="authBtn" name="authBtn" class="btn btn-primary btn-block" type="button"> 문자 전송 </button>
		                            		</div>
		                            	</div>
		                            	<div id="authDiv" class="form-group row">
		                            		<label class="col-12 col-sm-3 col-form-label text-sm-right"> 인증 번호 </label>
		                            		<div class="col-12 col-sm-8 col-lg-6">
		                            			<div class="input-group">                            			
			                            			<input id="authNumCheck" name="authNumCheck" class="form-control form-control-sm mb-2" type="text" placeholder="인증 번호를 입력해주세요" />
			                            			<div class="input-group-append">
			                            				<button id="authNumCheckBtn" name="authNumCheckBtn" class="btn btn-primary mb-2" type="button"> 인증 확인 </button>
			                            			</div>
		                            			</div>
		                            		</div>
		                            	</div>
		                            	<div id="countingDiv" class="form-group row" style="display: none;">
			                            	<label class="col-12 col-sm-3 col-form-label text-sm-right"> 인증 시간 </label>
			                            	<div class="col-12 col-sm-8 col-lg-6" style="text-align: center;" >
												<h5 id="authNumCounting"></h5>
			                            	</div>
			                            </div>
			                            <div id="exchangeDiv" class="form-group row" style="display: none;">
			                            	<label class="col-12 col-sm-3 col-form-label text-sm-right"> </label>
		                            		<div class="col-12 col-sm-8 col-lg-6">
		                            			<button class="btn btn-block btn-primary" id="exchangeBtn" type="submit"> 환전 신청</button>
		                            		</div>
						                </div>
						                
									</form> <!--  sellerExchangeForm  -->
								</div>
							</div>
						</c:if>
					</c:if>
					<c:if test="${!empty holdingExchange }">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="card">
								<div class="card-body">
									<table class="table table-hover">
										<thead>
											<tr style="text-align: center;">
												<th>
													회차
												</th>
												<th>
													환전 신청 금액
												</th>
												<th>
													은행명
												</th>
												<th>
													계좌번호
												</th>
												<th>
													신청 시간
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td style="text-align: center;">
													${holdingExchange.seExchangeIncreaseCount }
												</td>
												<td style="text-align: center;">
													<fmt:formatNumber value="${holdingExchange.seExchangePrice }" pattern="#,###"/> 원
												</td>
												<td style="text-align: center;">
													${holdingExchange.seBankNm }
												</td>
												<td style="text-align: center;">
													${holdingExchange.seBankAccount }
												</td>
												<td style="text-align: center;">
													${holdingExchange.seReqDate }
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>


</html>
