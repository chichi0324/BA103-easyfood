<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.admp.model.*"%>
<%@ page import="com.fea.model.*"%>
<%
	AdmVO admVO = (AdmVO) session.getAttribute("admVO_account");
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便管理系統</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/back-end/css/base.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>
<body>



	<!--右邊導覽列-->

	<div class="container-fluid">
		<nav class="navbar navbar-default" role="navigation">

			<div class="navbar-header">
				<a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=index"
					class="navbar-brand" href="#" id="navbar-title"><b>食在方便管理系統</b></a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><b><%=admVO.getAdm_name()%></b> 您好</a></li>
				<li><a
					href='<%=request.getContextPath()%>/easyfood/back-end/login.do?action=back_logout'
					data-toggle="modal"><b>登出　</b></a></li>
			</ul>

		</nav>
	</div>




	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>