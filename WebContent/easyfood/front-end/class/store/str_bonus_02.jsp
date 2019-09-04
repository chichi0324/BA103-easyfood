<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="java.util.*"%>
<%@ page import="com.fav.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.add.model.*"%>
<%
	String str_no = (String) session.getAttribute("str_no");	
	
	if(str_no == null){
		session.setAttribute("str_no", "STR_0071");		
		str_no = (String) session.getAttribute("str_no");		
	}
	StrService strSvc = new StrService();
	StrVO strVO = strSvc.getOneStr(str_no);
%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Information Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<!--[if lt IE 9]>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/dish.css">
<style>
		.margintt {
			margin: 10px;
		}
		#bookdate, div>select, input[type=number] {
			width: 200px;
		}
		#promotion {
			margin-top:30px;
			margin-left: 1.8em;
			width: 200px;
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
		<nav class="navbar navbar-inverse center-nav" role="navigation">
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
						<li class="active"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_bonus_01.jsp">優惠專區</a></li>
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
		
		<!-- breadcrumb 麵包屑 =======================================-->
		<ol class="breadcrumb">
			<li>
				<a href="<%=request.getContextPath()%>/easyfood/front-end/index.jsp">首頁</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_center.jsp">店家中心</a>
			</li>
			<li class="active">優惠專區</li>
		</ol>


		<!-- container 內文開始 =======================================-->
		<div class="container">
			<div class="row">
				<!-- 左邊開始 =======================================-->
				<div class="col-xs-12 col-sm-3">
					<div class="list-group">
						<a href="#" class="list-group-item list-group-custom active">促銷優惠</a>
						<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_bonus_01.jsp" class="list-group-item ">促銷專區總覽</a>
						<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_bonus_02.jsp" class="list-group-item list-group-item-info">促銷活動申請</a>	
					</div>
				</div>
		
		
				<!-- 右邊開始 =======================================-->			
				<div class="col-xs-12 col-sm-9" >
					<%//展示時再使用這行java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis()+83600000);%>
					<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
				<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService" />
				<jsp:useBean id="dishclassSvc" scope="page" class="com.dishclass.model.DclaService" />
					<div class="page-header order-page">優惠上架</div>
					<div class="toolbar">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pro/proin.do" >	
							<div class="margintt"><td>開始日期:</td>
								<input type="date" name="datestr" id="bookdate" value=<%=date_SQL%> min=<%= date_SQL %> max="2020-09-18">
							</div>
							<div class="margintt"><td>結束日期:</td>
								<input type="date" name="dateend" id="bookdate" value=<%=date_SQL%> min=<%= date_SQL %> max="2020-09-18"><br>
							</div>
					
				           			
							<div class="margintt">優惠種類:
								<select name="style" class="select-sm" id="select" onclick="choice()">
									<option value="money">金額</option>
									<option value="class">類別</option>
								</select>
							</div>
				<div id="myMoney">
							<div class="margintt">
								條件:<input type="number" name="condition" min=0>要達到優惠總金額
							</div>	
							<div class="margintt">
								折扣:<input type="number" name="discount" min=0 max=100>例:85折 輸入85
							</div>
							
				</div>
				<div id="myClass" style="display:none">

						<div class="margintt">
								種類1:
								<select name="dcla_1" class="select-sm" >
									<c:forEach var="dishVO" items="${dishSvc.getDishClassForStr(str_no)}" > 
										<c:forEach var="dishclassVO" items="${dishclassSvc.all}">	
											<c:if test="${dishclassVO.dcla_no == dishVO.dcla_no}"> 
												<option value="${dishVO.dcla_no}">${dishclassVO.dcla_name}</option>
											</c:if>
										</c:forEach>
									</c:forEach>			
								</select>
							</div>
							
							
							<div class="margintt">
								種類2:
								<select name="dcla_2" class="select-sm" >
									<c:forEach var="dishVO" items="${dishSvc.getDishClassForStr(str_no)}" > 
										<c:forEach var="dishclassVO" items="${dishclassSvc.all}">	
											<c:if test="${dishclassVO.dcla_no == dishVO.dcla_no}"> 
												<option value="${dishVO.dcla_no}">${dishclassVO.dcla_name}</option>
											</c:if>
										</c:forEach>
									</c:forEach>			
								</select>
							</div>
							
							<div class="margintt">
								折扣:<input type="number" name="discount2" min=0 max=100>例:85折 輸入85
							</div>
							
				
					</div>			
							<div>
								<button type="submit" class="btn btn-primary btn-sm" id="promotion">上架</button>
								<input type="hidden" name="str_no" value="${str_no}">
								<input type="hidden" name="action" value="Insert_For_Pro">
							</div>
			
						
						
						</FORM>
					</div>
					
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
		</div>
		
		<script type="text/javascript">

		function choice(){
			if (document.getElementById("select").value=="money") {
		document.getElementById("myMoney").style.display="";
		document.getElementById("myClass").style.display="none";

			}else if(document.getElementById("select").value=="class"){
		document.getElementById("myMoney").style.display="none";
		document.getElementById("myClass").style.display="";
			}

	    }

	
        </script>
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>