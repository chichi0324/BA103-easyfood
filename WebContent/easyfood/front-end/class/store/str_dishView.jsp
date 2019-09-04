<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dish.model.*" %>
<%@ page import="com.dishclass.model.*" %>
<%@ page import="com.store.model.*" %>
<%	
	String str_no = (String) session.getAttribute("str_no");
	StrService strSvc = new StrService();
	StrVO strVO = strSvc.getOneStr(str_no);
	
	DishService dishSvc = new DishService();
	List<DishVO> dcList = dishSvc.getDishClassForStr(str_no);
	pageContext.setAttribute("dcList", dcList);
	
%>
<jsp:useBean id="dclaSvc" class="com.dishclass.model.DclaService"/>


<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Dish Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<!--[if lt IE 9]>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<!-- <link rel="stylesheet" type="text/css" href="../css/base.css"> -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/dish.css">
	<style>
		select.select-sm {
			height:35;
			vertical-align: middle;
		}
		input {
			vertical-align: middle;
			width: initial;
			line-height: 2.26;
			border-radius: 5px;
			border-color: #aaa;
		}
	</style>
</head>
<body>
	<!-- header======================================= -->	
	<div class="page-header center-header">
	  <span>食在方便<small>店家中心</small></span>
	</div>

	<!-- navigation bar======================================= -->
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
					<li class="active"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishView.jsp">菜單專區</a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderCheck.jsp">訂單專區</a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_inform01.jsp">個人訊息</a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_advertise01.jsp">廣告專區</a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_bonus_01.jsp">優惠專區</a></li>
				</ul>
			
				<!-- 右選單 =======================================-->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=strVO.getStr_name() %> 您好
						<b class="caret"></b>
						</a>
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
	
	<!-- 麵包屑 =======================================-->
	<ol class="breadcrumb">
		<li>
			<a href="<%=request.getContextPath()%>/easyfood/front-end/index.jsp">首頁</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_center.jsp">店家中心</a>
		</li>
		<li class="active">菜單專區</li>
	</ol>


	<!-- container 內文開始 =======================================-->
	<div class="container">
		<div class="row">
			<!-- 左邊開始 =======================================-->
			<div class="col-xs-12 col-sm-3">
				<div class="list-group">
					<a href="#" class="list-group-item list-group-custom active">菜單資訊</a>
					<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishView.jsp" class="list-group-item list-group-item-info">菜單總覽</a>
					<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishEdd.jsp" class="list-group-item">菜單上架</a>
					<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishUpdate.jsp" class="list-group-item">菜單修改</a>
				</div>
			</div>
	
	
			<!-- 右邊開始 =======================================-->			
			<div class="col-xs-12 col-sm-9">
			
				<div class="page-header order-page">菜單總覽</div>
					<div class="toolbar inline-block">
					
						<c:if test="${not empty errorMsgs}">
							<div class="error">請修正以下錯誤:
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
		
						<form action="<%=request.getContextPath() %>/dish/dish.do" method="post">
							<span class="middle fn-sm">菜單編號：</span>
							<input type="text" name="dish_no" placeholder="請輸入菜單編號查詢">
							
							<span class="middle fn-sm">菜色種類：</span>
							<select name="dishClass" class="select-sm">
							
								<option value="所有種類">所有種類</option>
								<c:forEach var="dishVO" items="${dcList}">
									<c:forEach var="dclaVO" items="${dclaSvc.all}">
										<c:if test="${dishVO.dcla_no.equals(dclaVO.dcla_no)}">
											<option value="${dishVO.dcla_no}">${dclaVO.dcla_name}</option>
										</c:if>
									</c:forEach>
								</c:forEach>
								
							</select>
							<span class="middle fn-sm">餐點狀態：</span>
							<select name="dishStatus" class="select-sm">
								<option value="所有狀態">所有狀態</option>
								<option value="準備中">準備中</option>
								<option value="販售中">販售中</option>
								<option value="已下架">已下架</option>
							</select>
								
							<button class="btn btn-mydish btn-sm" style="font-size:initial;">搜尋</button>
							<input type="hidden" name="action" value="GET_DISHES" >
						</form>
					
					</div>
	
					<div class="col-xs-12" style="text-align:end;margin-top:10px">
						<button class="btn btn-sm btn-mydish" style="visibility:hidden">新增菜單</button>
						<button class="btn btn-sm btn-mydish" style="visibility:hidden">修改菜單</button>
					</div>
				
					<div class="col-xs-12">
						<c:forEach var="dishVO" items="${dishList}">
						
							<div class="media">
							
								<div class="media-left">
									<img src="<%=request.getContextPath()%>/dish/readDishPicture.do?DISH_NO=${dishVO.dish_no}" class="media-object"  height="150px" width="200px">
								</div>
								
								<div class="media-body">
									<div class="dish">
										<c:forEach var="dclaVO" items="${dclaSvc.all}">
											<c:if test="${dishVO.dcla_no == dclaVO.dcla_no}">
												<h4 class="media-heading">${dclaVO.dcla_name}</h4>
											</c:if>
										</c:forEach>
										<p>${dishVO.dish_no}&emsp;<span style="color: #34495E;">${dishVO.dish_name}</span></p>
										<p style="color: #34495E;">${dishVO.dish_note}</p>
									</div>
									<div class="price">NTD ${dishVO.dish_price}</div>
									<div class="status" style="color:#A88A68;">${dishVO.dish_status}</div>
								</div>
								<hr>
							</div>
								
						</c:forEach>
					</div>
				
			</div>
		
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>