jQuery(document).ready(function($) {
	
	//상품 변경 시 이벤트
	$(document).on('click', 'button[name=changeMultiProductOption]', function(){
		$("#changeMultiProductAndOptionForm").html("");
		
		var orProduct = $(this).data("product-name");
		var orProductOption = $(this).data("option-name");		
		var orAmount = $(this).parent().prev().find("select[name=optionAmount]").val();
		
		let orSize = $("input[name=orPks]").length;
		
		let orPks = new Array(orSize);
		
		for(var i=0; i<orSize; i++){
			
			orPks[i]=$("input[name=orPks]")[i].value;
			
			
			
			
		}
		
		let orPkInput = document.createElement("input");
		orPkInput.name="orPks";
		orPkInput.type="hidden";
		orPkInput.value=orPks;
		$("#changeMultiProductAndOptionForm").append(orPkInput);
		
		let orProductInput = document.createElement("input");
		orProductInput.name="orProduct";
		orProductInput.type="hidden";
		orProductInput.value=orProduct;
		
		let orProductOptionInput = document.createElement("input");
		orProductOptionInput.name="orProductOption";
		orProductOptionInput.type="hidden";
		orProductOptionInput.value=orProductOption;
	
		let orAmountInput = document.createElement("input");
		orAmountInput.name="orAmount";
		orAmountInput.type="hidden";
		orAmountInput.value=orAmount;
		
		
		$("#changeMultiProductAndOptionForm").append(orProductInput);
		$("#changeMultiProductAndOptionForm").append(orProductOptionInput);
		$("#changeMultiProductAndOptionForm").append(orAmountInput);
		$("#changeMultiProductAndOptionForm").append(orPkInput);
		
		
		var changeProductAndOptionFormParams = jQuery("#changeMultiProductAndOptionForm").serialize();
		
		
		$.ajax({
		    type       : 'POST',
		    url        : '/order/config/multi_change_product_option.do',
		    
		    async		: false,
		    data       : changeProductAndOptionFormParams,
		    
		    success    : function(data){
		    	if(data != 0){
		    		alert("상품 변경 완료");
		    		opener.location.reload();
		    		self.close();
		    		
		    	}else{
		    		alert("상품 변경 실패");
		    		
		    	}
		    }
		});
	});
	
	//상품 변경시
	$("form[name=changeProductOptionListSearchForm]").submit(function(){
		
		var cfFk = $("select[name=cfFk]").val();
		var productName = $("input[name=productName]").val();
		
		$.ajax({
		    type       : 'GET',
		    url        : '/order/matching/product_list_result.do',
		    async		: false,
		    data       : {
		    	"cfFk":cfFk,
		    	"productName":productName
		    },
		    success    : function(data){
		    	changeProductOptionListWrite(data);
		    }
		});
		
		event.preventDefault();
		return false;
	});
	
	function changeProductOptionListWrite(data){
		var productOptionListHTML = "";
		
		$.each(data, function(){
			productOptionListHTML+='<div class="product-thumbnail">'
				+'<div class="product-content">'
					+'<div class="row">'
						+'<div class="col-lg-4 col-xl-4 col-md-4">'
							+'<div class="product-content-head">'
							+'<h5>'+this.productName+'</h5>'
							+'<div class="product-rating d-inline-block">';
								if(this.productFlag == true){
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-success">사용중</a>';
								}else{
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-danger">미사용중</a>';
								}
			productOptionListHTML+='</div>'
						+'</div>'
						+'</div>'
						+'<div class="col-lg-8 col-xl-8 col-md-8"><div class="card mb-0">';
			
							if(this.optionVOList.length == 0){
								productOptionListHTML+='<div class="card-header">'
									+'<h5 class="mb-0">등록된 옵션이 없습니다.</h5>'
									+'</div>';
								
							}else{
								var productPks = this.productPk;
								var productNames = this.productName;
								$.each(this.optionVOList, function(){									
									productOptionListHTML+='<div class="card-header" style="cursor: pointer;">'
											+'<div class="row">'
												+'<div class="col-xl-6 col-lg-6 col-sm-12 col-6">'
													+'<h5 class="mb-0">'+this.optionName+'</h5>'
												+'</div>'
												+'<div class="col-xl-4 col-lg-4 col-sm-12 col-4">'
													+'<select class="form-control form-control-sm" name="optionAmount" >';
														for(var i = 1; i < 101; i++){
															productOptionListHTML+='<option value="'+i+'"> 변경할 개수 : '+i+' 개 </option>';
														}
				              productOptionListHTML+='</select>'
			                                    +'</div>'
			                                    +'<div class="col-xl-2 col-lg-2 col-sm-12 col-2">'
			                                    	+'<button name="changeMultiProductOption" value="'+this.optionPk+'" data-product-name="'+productNames+'" data-product-pk="'+productPks+'" data-option-name="'+this.optionName+'" class="btn btn-primary btn-block"> 변경하기 </button>'
			                                    +'</div>'
											+'</div>'
											+'<div>'
											+'</div>'
										+'</div>';
								});
								
							}
							
			productOptionListHTML+='</div></div>'
					+'</div>'
				+'</div>'
			+'</div>';
		});
		
		$("#productOptionSearchList").html(productOptionListHTML);
		
		
	}
	
}); // AND OF JQUERY