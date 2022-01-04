<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../inc/top.jsp" %>
    <%@ include file="../../inc/top_nav.jsp" %>
    <script type="text/javascript">
    	$(function(){
			var piCount = 1;
    		
			// ajax /products/all_cost_detail_join_cost_code_list.do
				
    		$('#inputDates').datetimepicker({
    			timepicker:false,
    			lang:'kr',
    			format:'Y-m-d'
    		
    		});
    		
    		$(document).on("focusout", "input[name*=piTax]", function(){
    			countings = $(this).data("tax");
    			
    			taxs = $("input[data-tax='"+countings+"']").val();
    			costs = $("input[data-cost='"+countings+"']").val();
    			
    			$("input[data-totalcost='"+countings+"']").val(( Number(taxs) + Number(costs) ));
    			
    			
    		});
    		
    		$("#insertProductInfoForm").submit(function(){
    			
    			$("input[name*=piQty]").each( function(){
    				if($(this).val() == ''){
    					
    					alert("수량 혹은 중량을 적어주세요");
    					
    					$(this).focus();
    					event.preventDefault();
    					return false
    					
    				}
    				
    			});
    			
    			$("input[name*=piName]").each( function(){
    				if($(this).val() == ''){
    					
    					alert("상품 명을 적어주세요");
    					
    					$(this).focus();
    					event.preventDefault();
    					return false
    					
    				}
    				
    			});
    			
    		});
    		
    		$("#piAddBtn").click(function(){
    			
    			piHTML = "";
    			
    			$.ajax({
    				type       : 'GET',
    				async		: false,
    				url        : '/products/all_cost_detail_join_cost_code_list_in_meat.do',
    				success    : function(data){

    					if(data.length > 0){
    						
    						piHTML+='<tr class="table-3bgogi-hover">'
    			                +'<td>'
    			                	+'<input type="text" name="piList['+piCount+'].piName" class="form-control" placeholder="">'
    			                +'</td>'
    			                
    			                +'<td>'
	                            	+'<select class="form-control" name="piList['+piCount+'].piType" data-live-search="true" data-size="8">'
										+'<option value="">타입없음</option>'
										+'<option value="간편">간편</option>'
										+'<option value="소스">소스</option>'
										+'<option value="광고비">광고비</option>'
										+'<option value="비품">비품</option>';
										
										
										for(let costCount = 0; costCount < data.length; costCount++){
											for(let costCodeCount = 0; costCodeCount < data[costCount].costCodeVOList.length; costCodeCount++){
												
												piHTML+= '<option value="'+data[costCount].costCodeVOList[costCodeCount].ccCodeType+' '+data[costCount].cdName+'">'+data[costCount].costCodeVOList[costCodeCount].ccCodeType+' '+data[costCount].cdName+'</option>';
												
											}
										}
										
									piHTML+='</select>'
									
	                            +'</td>'
    			                +'<td>'
    			                	+'<input type="text" name="piList['+piCount+'].piQty" class="form-control" placeholder="">'
    			                +'</td>'
    			                +'<td>'
    			                	+'<select class="form-control" name="piList['+piCount+'].piMeasure" >'
    									+'<option value=""> 단위 선택</option>'
    									+'<option value="박스"> 박스</option>'
    									+'<option value="g"> g</option>'
    									+'<option value="개"> 개</option>'
    									+'<option value="리터"> 리터</option>'
    								+'</select>'
    			                +'</td>'
    			                +'<td>'
    			                	+'<input type="text" name="piList['+piCount+'].piCost" data-cost="'+piCount+'" class="form-control">'
    			               +'</td>'
    			                +'<td>'
    			                	+'<input type="text" name="piList['+piCount+'].piTax" data-tax="'+piCount+'" class="form-control">'
    			                +'</td>'
    			                +'<td>'
    			                	+'<input type="text" name="piList['+piCount+'].piTotalCost" data-totalcost="'+piCount+'" class="form-control">'
    			                +'</td>'
    			                +'<td>'
    			                	+'<input type="text" name="piList['+piCount+'].piAccountReceivable" class="form-control" value="0">'
    			                +'</td>'
    			                +'<td>'
    			                	+'<input type="text" name="piList['+piCount+'].piRemark1" class="form-control">'
    			                +'</td>'    
    			                +'<td>'
    			                	+'<input type="text" name="piList['+piCount+'].piRemark2" class="form-control">'
    			                +'</td>'  
    			             +'</tr>';
							
    						piCount++;
    						
    						
    					}else{

    					}
    				}
    				
    			});
    			
    			
    			$("#piAddBody").append(piHTML);
    			
    			
    			$("select[name*=piType]").each( function(){
    				$(this).selectpicker();
    				
    			});
    			
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
                            <h2 class="pageheader-title"> 거래내역서 추가 </h2>
                            <div class="page-breadcrumb">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 세금계산서</a></li>
                                        <li class="breadcrumb-item"><a href="#" class="breadcrumb-link"> 거래내역서 </a></li>
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
                                <h5 class="card-header"> 거래내역서 추가하기 </h5>
                                <div class="card-body">
                                    <form id="insertProductInfoForm" method="POST" action="<c:url value='/tax/product_info/insert.do'/>" enctype="multipart/form-data">
                                    	<div class="form-row">
                                    		<div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12 mb-2">
                                                <label for="piInputDate">거래처</label>
                                                
                                                <select class="form-control"  id="resCompany" name="resCompany">
                                                
                                                	<c:forEach var="res" items="${rcList }">
                                                		<option value="${res.rcPk }">${res.rcName }</option>
                                                	</c:forEach>
                                                </select>
                                            </div>
                                            
                                            <div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12 mb-2">
                                                <label for="piInputDate">발급일자</label>
                                                <input type="text" id="inputDates" name="inputDates" class="form-control" autocomplete="off">
                                                
                                                
                                            </div>
                                            <div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12 mb-2">
                                                <label for="piFile">명세서</label>
                                                <input type="file" id="piFile" name="piFile" class="form-control">
                                            </div>
                                        </div>
                                        <hr>                                        
                                        <button class="btn btn-primary btn-xs mb-2" id="piAddBtn" type="button"> 항목 추가 </button>
                                        
                                    	<table id="example2" class="table table-bordered" style="table-layout: fixed; word-break: keep-all;">
                                    		<colgroup>
												<col width="180px" />
												<col width="180px" />
												<col width="80px" />
												<col width="100px" />
												<col width="100px" />
												<col width="100px" />
												<col width="90px" />
												<col width="90px" />
												<col width="150px" />
												<col width="150px" />
												
											</colgroup>
	                                        <thead class="bg-light">
	                                            <tr>
	                                                <th >상품</th>
	                                                <th >상품타입</th>
	                                                <th >수량/중량</th>
	                                                <th >단위</th>
	                                                <th >공급가</th>
	                                                <th >세액</th>
	                                                <th >합계</th>
	                                                <th >미수금</th>
	                                                <th >비고1</th>
	                                                <th >비고2</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id="piAddBody">
		                                        <tr class="table-3bgogi-hover">
		                                            <td>
		                                            	<input type="text" name="piList[0].piName" class="form-control" placeholder="">
		                                            </td>
		                                            <td>
		                                            	<select class="form-control" name="piList[0].piType" data-live-search="true" data-size="8">
															<option value="">타입없음</option>
															<option value="간편">간편</option>
															<option value="소스">소스</option>
															<option value="광고비">광고비</option>
															<option value="비품">비품</option>
															<c:forEach var="costDetaillist" items="${costDetailList }">
																<c:forEach var="costCodeVOlist" items="${costDetaillist.costCodeVOList }">																
																	<option value="${costCodeVOlist.ccCodeType } ${costDetaillist.cdName }">${costCodeVOlist.ccCodeType } ${costDetaillist.cdName }</option>
																</c:forEach>
																
															</c:forEach>
														</select>
		                                            </td>
		                                            <td>
		                                            	<input type="text" name="piList[0].piQty" class="form-control" placeholder="">
		                                            </td>
		                                            
		                                            <td>
		                                            	<select class="form-control" name="piList[0].piMeasure" >
															<option value=""> 단위 선택</option>
															<option value="박스"> 박스</option>
															<option value="g"> g</option>
															<option value="개"> 개</option>
															<option value="리터"> 리터</option>
														</select>
		                                            </td>
		                                            <td>
		                                            	<input type="text" name="piList[0].piCost" data-cost="0" class="form-control">
		                                            </td>
		                                            <td>
		                                            	<input type="text" name="piList[0].piTax" data-tax="0" class="form-control">
		                                            </td>
		                                            <td>
		                                            	<input type="text" name="piList[0].piTotalCost" data-totalcost="0" class="form-control">
		                                            </td>
		                                            <td>
		                                            	<input type="text" name="piList[0].piAccountReceivable" class="form-control" value="0">
		                                            </td>
		                                            <td>
		                                            	<input type="text" name="piList[0].piRemark1" class="form-control">
		                                            </td>    
		                                            <td>
		                                            	<input type="text" name="piList[0].piRemark2" class="form-control">
		                                            </td>  
		                                         </tr>
	                                        </tbody>
	                                    </table>
                                        <div class="form-group row text-right">
                                            <div class="col col-sm-12 col-lg-12">
                                                <button id="productInfoInsertBtn" type="submit" class="btn btn-space btn-primary"> 입력 하기 </button>
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
        <script src="${pageContext.request.contextPath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
		<script type="text/javascript">
			$(function(){
				$("select[name*=piType]").each( function(){
					
    				$(this).selectpicker();
    				
    				
    			});

			});
		</script>
        <!-- /page content -->
        <%@ include file="../../inc/bottom.jsp" %>