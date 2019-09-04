<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.str.model.*"%>
<% 
StrVO strVO = (StrVO) request.getAttribute("strVO");
%>

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/search/css/search_store_online.css">

		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->


  </head>
	<body>







    <!--第二列右邊第一排==========================================-->
      <div class="row">
        <div class="col-xs-12 col-sm-12">
          <p class="storeNav"><b>首頁 > 搜尋店家(<%=strVO.getStr_name() %>) > 線上諮詢</b></p>
        </div>
      </div>

      <div class="row">
        <div class="col-xs-12 col-sm-12">
        <h2 style="font-family:Microsoft JhengHei"><b><center>線上諮詢</center></b></h2>



        </div>
      </div>



   

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>