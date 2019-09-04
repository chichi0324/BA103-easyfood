<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String mem_no = (String) session.getAttribute("mem_no");
String display = (String) request.getAttribute("display");
%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="short Icon" type="images/x-icon" href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/search/css/search.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script> 
         

	</head>
	<body>


	<!--導覽列==========================================================================-->
<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />






 <!--第一列===================================================================-->
 <div class="container">
  <div class="row firstRow">

   <jsp:include page="search_display_select.jsp" flush="true" /> 

    </div>

  </div>

 <!--第二列===================================================================-->
  
  <div class="row">

<%if ("enter_store".equals(display)) { %>

<jsp:include page="search_enter_store.jsp" flush="true" />

<%}else{ %>

<jsp:include page="search_display_store.jsp" flush="true" />

<%} %>


  </div>
<!--底部平台介紹================================================================-->
</div>

<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />


    

   

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

	</body>
</html>