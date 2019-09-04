<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
MemVO memVO = (MemVO) session.getAttribute("memVO");
String mem_no = null;
if(memVO!=null){
	mem_no =memVO.getMem_no();
}
String display = (String) request.getAttribute("display");
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="short Icon" type="images/x-icon" href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/blog/css/blog.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">

<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>
<body>


	<!--導覽列==========================================================================-->
<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />




	<!--第一列===================================================================-->


	<div class="container">
		<div class="row" id="id">
			<!--左邊部份==================================================-->
			<div class="col-xs-3 col-sm-3 " id="blog_left">
				<jsp:include page="blog_select.jsp" flush="true" />
			</div>


			<!--右邊部份==================================================-->
			<div class="col-xs-9 col-sm-9 " style="overflow:hidden" id="blog_right">

				<!--右邊第一排==========================================-->

				<%
					if ("blog_display_complete".equals(display)) {
				%>
				<jsp:include page="blog_content.jsp" flush="true" />
				<%
					} else if("blog_display_update".equals(display)){
				%>
				<jsp:include page="/easyfood/front-end/class/member/member_blog_resUpdate.jsp" flush="true" />
				<%
					}else{
				%>
				<jsp:include page="blog_display.jsp" flush="true" />
				<%
					}
				%>
			</div>
		</div>
	</div>




	<!--底部平台介紹================================================================-->


<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />



	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>