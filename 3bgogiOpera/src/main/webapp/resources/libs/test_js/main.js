
(function ($) {
  // USE STRICT
	class sevenDayClass{
		constructor(date, qty){
			this.date = date;
			this.qty = qty;
		}
	}
	
	var orderCountDataList = new Array();
	var sevenDaysDateAndDataLIst = new Array();
	
  "use strict";
  function devideDateAndData(orderList){
	  var dataSets = new Array();
	  
	  var dateList = new Array();
	  var stringDateList = new Array();
	  var dataList = new Array();
	  var orderCountList = new Array();
	  
	  $.each(orderList, function(idx ){
		 dateList.push(this.orSendingDeadline);
		 stringDateList.push(this.orRequest);
		 dataList.push(this.orTotalPrice);
		 orderCountList.push(this.totalOrderCount);
		 
	  });
	  dataSets.push(dateList);
	  dataSets.push(dataList);
	  dataSets.push(orderCountList);
	  dataSets.push(stringDateList);
	  
	  return dataSets;
  }
  
  
  function sevenDaysDateAndData(orderList){
	  var dateList = new Array();
	  var stringDateList = new Array();
	  
	  var orderCount = 0;
	  
	  $.each(orderList, function(idx ){
		  if(orderCount == 0){
			  dateList.push('발송기한');
			  stringDateList.push('주문 개수');
			  
		  }else{
			  
			  dateList.push(this.orSendingDeadline);
			  stringDateList.push(this.orAmount);
			  
		  }
		  
		  orderCount++;
		  
	  });
	  
	  var sevenDays = new sevenDayClass(dateList, stringDateList);
	  /*dataSets.push(dateList);
	  dataSets.push(stringDateList);*/
	  
	  return sevenDays;
  }
  
  
  function jsonData(orderList){
	  
	  var dateList = [];
	  
	  $.each(orderList, function(idx ){
		  
		  var sendDate = this.orSendingDeadline;
		  var sendQty = this.orAmount;
		  
		  var totalSendData = {
	
			x: sendDate
	
			, y: sendQty
			
		  }
		  
		  dateList[idx] = totalSendData;
		  
	  });
	  
	  
	  console.log( JSON.stringify(dateList)  );
	  
	  return dateList;
	  
	  
  }
  
  window.chartColors = {
			red: 'rgb(255, 99, 132)',
			orange: 'rgb(255, 159, 64)',
			yellow: 'rgb(255, 205, 86)',
			green: 'rgb(75, 192, 192)',
			blue: 'rgb(54, 162, 235)',
			purple: 'rgb(153, 102, 255)',
			grey: 'rgb(201, 203, 207)'
		};
  
  
$.ajax({
	    type       : 'GET',
	    async	   : false,
	    url        : '/main/inserting_orders.do',
	    success    : function(data){
	    	
	    	saleList = jsonData(data);
	    	
	    	Morris.Bar({
	            element: 'sendingList',
	            data: saleList
	            ,
	            
	            xkey: 'x',
	            ykeys: ['y'],
	            labels: ['주문서 개수'],
	            barColors: ['#ff407b']

	        });
	        
	    }
 
  });
  

})(jQuery);