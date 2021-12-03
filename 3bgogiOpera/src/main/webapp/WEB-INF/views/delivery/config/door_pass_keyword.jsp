<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="kr">
<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title> 공동현관 출입방법 키워드 목록  </title>
    <!-- Bootstrap CSS -->
    
    <link rel="stylesheet" href="${resourcePath}/resources/libs/css/theme.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/resources/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/js/main-js.js"></script>
    
    
    <script type="text/javascript">
    
    	$(function(){
    		$("button[name=deleteKeyword]").click(function(){
    			
    			if(confirm("해당 키워드를 삭제하시겠습니까?")){
    				var dpkPk = $(this).val();
    				location.href="<c:url value='/delivery/config/door_pass_keyword_del.do?dpkPk="+dpkPk+"'/>";
    			}
    		});
    		
    		
    	});
    </script>
    <style>
    html,
    body {
        height: 100%;
    }

    body {
        display: -ms-flexbox;
        display: flex;
        -ms-flex-align: center;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
    }
    </style>
</head>
<body>
        <div class="splash-container" style="max-width: 100%;">
        	<div class="card">        	
	            <div class="card-body" id="">
	            	<table class="table table-hover">
                    	<thead>
                        	<tr>
                            	<th scope="col" width="70%" style="text-align: center;"> 제외 키워드 </th>
                                <th scope="col" width="30%" style="text-align: center;"> 삭제 </th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:if test="${empty dpkList }">
                        		<tr>
	                            	<th scope="row" colspan="2"> 제외 키워드가 없습니다 </th>
	                            </tr>
                        	</c:if>
                        	<c:if test="${!empty dpkList }">
	                        	<c:forEach var="keyWord" items="${dpkList }">
	                        		<tr>
		                            	<th scope="row">${keyWord.dpkWord }</th>
		                                <td>
		                                	<button class="btn btn-block btn-danger btn-xs" name="deleteKeyword" value="${keyWord.dpkPk }"> 삭제 </button>
		                                </td>
		                            </tr>	
	                        	</c:forEach>                        		
                        	</c:if>
                        </tbody>
                   </table>
	            </div>
        	</div>
        	<div class="card">        	
	            <div class="card-body" id="">
	            	<form class="form-group" action="<c:url value='/delivery/config/door_pass_keyword.do'/>" method="POST">
                    	<div class="input-group">
	                        <input type="text" class="form-control" name="dpkWord" placeholder="공동현관 추출 키워드">
	                        
	                        <div class="input-group-append">
	                        	<button type="submit" class="btn btn-primary"> 추가 </button>
	                        </div>
                        </div>
	            	</form>
	            </div>
        	</div>
        </div>
</body>
</html>
