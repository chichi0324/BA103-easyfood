<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>

<%
	String str_no = (String) session.getAttribute("str_no");	
	StrService strSvc = new StrService();
	StrVO strVO = strSvc.getOneStr(str_no);
	pageContext.setAttribute("strVO", strVO);
	
	if(str_no == null){
		session.setAttribute("str_no", "STR_0071");		
		str_no = (String) session.getAttribute("str_no");		
	}
%>

<%-- <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StrService" /> --%>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/style.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/dish.css">
		<style type="text/css">
			#css_table {
				display:table;
				font-size: larger;
				text-align: center;   
			}
			.css_tr {
				display: table-row;
			}
			.css_td {
				display: table-cell;
				padding-top: 6px;
				padding-left: 20px;
				line-height:1.5;
			}
			.input-group-addon {
				margin-bottom: 4px;
				margin-right: 2em;
			}
			.btn {
				margin-top: 20px;
				width: 100px;
				text-align: center;
			}			
		</style>

	</head>
	<body>
<!-- header======================================= -->	
		<div class="page-header center-header">
		  <span>食在方便<small>店家中心</small></span>
		</div>

<!-- navbar======================================= -->
		<nav class="navbar center-navbar navbar-inverse" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile01.jsp"><%=strVO.getStr_name() %></a>
				</div>
				
		<!-- 手機隱藏選單區=======================================-->
				<div class="collapse navbar-collapse navbar-ex1-collapse">
			<!-- 左選單 =======================================-->
					<ul class="nav navbar-nav">
						<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_center.jsp">店家中心</a></li>
						<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishView.jsp">菜單專區</a></li>
						<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderCheck.jsp">訂單專區</a></li>
						<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_inform01.jsp">個人訊息</a></li>
						<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_advertise01.jsp">廣告專區</a></li>
						<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_bonus_01.jsp">優惠專區</a></li>
					</ul>
				
			<!-- 右選單 =======================================-->
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= strVO.getStr_name()%> 您好<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile01.jsp">店家資料</a></li>
								<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderView.jsp">訂單查詢</a></li>
								<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_inform01.jsp">個人訊息</a></li>
							</ul>
						</li>
						<li><a href='#modal-id' data-toggle="modal">登出</a></li>
					</ul>
				</div>
		<!-- 手機隱藏選單區結束 =======================================-->
			</div>
		</nav>

<!-- modal登出視窗 =======================================-->
		<jsp:include page="str_logout.jsp" />
		
<!-- breadcrumb 麵包屑 =======================================-->
		<ol class="breadcrumb">
			<li>
				<a href="<%=request.getContextPath()%>/easyfood/front-end/index.jsp">首頁</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_center.jsp">店家中心</a>
			</li>
			<li class="active">店家帳號</li>
		</ol>


<!-- container 內文開始 =======================================-->
<div class="container">
	<div class="row">
		<!-- 左邊開始 =======================================-->
		<div class="col-xs-12 col-sm-3">
			<div class="list-group">
				<a href="#" class="list-group-item list-group-custom active">店家帳號</a>
				<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile01.jsp" class="list-group-item">基本資料</a>
				<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile02.jsp" class="list-group-item">修改資料</a>
				<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile03.jsp" class="list-group-item list-group-item-info">修改密碼</a>
			</div>
		</div>


		<!-- 右邊開始 =======================================-->			
		<div class="col-xs-12 col-sm-9 between">
			
			
			<div id="css_table">
			
			<form action="<%=request.getContextPath()%>/str/store.do" method="post">
				<div class="css_tr">
					<div class="input-group-addon">原本密碼</div>
					<div class="css_td">
						<input class="input" type="password" name="str_pas" value="">
					</div>
				</div>

				<div class="css_tr">
					<div class="input-group-addon">更改密碼</div>
					<div class="css_td">
						<input class="input" type="password" name="str_pas_new" value="">
					</div>
				</div>

				<div class="css_tr">
					<div class="input-group-addon">再輸入一次</div>
					<div class="css_td">
						<input class="input" type="password" name="str_pas_new2" value="">
					</div>
				</div>
	
				<input type="hidden" name="action" value="str_up_pas">
				<button class="btn btn-sm btn-primary">修改</button>	
			</form>
				
				<c:if test="${not empty errorMsgs}">
					<font color='red'>
						<ul>
						  <c:forEach var="message" items="${errorMsgs}">
						    <li>${message}</li>
						  </c:forEach>
						</ul>
					</font>
				</c:if>
				
			</div>
			
		</div>
		<!-- 右邊結束 =======================================-->
	</div>
</div>

	
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

	</body>
</html>