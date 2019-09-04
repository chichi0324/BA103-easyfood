<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.store.model.*, com.mem.model.*"%>
<%@ page import="com.ord.model.*, com.ordit.model.*"%>
<%@ page import="com.dish.model.*"%>
<% 
	String str_no = (String) session.getAttribute("str_no");	
	if(str_no == null){
		session.setAttribute("str_no", "STR_0071");		
		str_no = (String) session.getAttribute("str_no");		
	}
	StrService strSvc = new StrService();
	StrVO strVO = strSvc.getOneStr(str_no);
	int count = 0;
%>
<jsp:useBean id="memSvc" class="com.mem.model.MemService" />
<jsp:useBean id="orditSvc" class="com.ordit.model.OrditService" />
<jsp:useBean id="dishSvc" class="com.dish.model.DishService" />

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Order Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<!--[if lt IE 9]>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/dish.css">
	<style>
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

	<div class="page-header center-header">
	  <span>食在方便<small>店家中心</small></span>
	</div>
	
	
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
					<li class="active"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderCheck.jsp">訂單專區</a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_inform01.jsp">個人訊息</a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_advertise01.jsp">廣告專區</a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_bonus_01.jsp">優惠專區</a></li>
				</ul>
			
				<!-- 右選單 =======================================-->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=strVO.getStr_name() %> 您好<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile01.jsp">店家資料</a></li>
							<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderCheck.jsp">訂單查詢</a></li>
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
		<li class="active">訂單專區</li>
	</ol>


	<!-- container 內文開始 =======================================-->
	<div class="container">
		<div class="row">
			<!-- 左邊開始 =======================================-->
			<div class="col-xs-12 col-sm-3">
				<div class="list-group">
					<a href="#" class="list-group-item list-group-custom active">訂單資訊</a>
					<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderView.jsp" class="list-group-item list-group-item-info">訂單總覽</a>
					<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderCheck.jsp" class="list-group-item">未確認訂單</a>
					<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderConfirm.jsp" class="list-group-item">已確認訂單</a>
				</div>
			</div>
	
			<!-- 右邊開始 =======================================-->			
			<div class="col-xs-12 col-sm-9">
				<div class="page-header order-page">訂單總覽</div>
					<div class="toolbar inline-block">
					
					<form action="<%=request.getContextPath() %>/ord/ord.do" method="post">
						<span class="middle fn-sm">訂單編號：</span>
						<input type="text" name="ord_no" placeholder="請輸入訂單編號查詢">
						
						<span class="middle fn-sm">訂單日期：</span>
						<select name="ord_date" class="select-sm">
							<option value="所有訂單">所有訂單</option>
							<option value="本日訂單">本日訂單</option>
							<option value="本週訂單">本週訂單</option>
							<option value="本月訂單">本月訂單</option>
							<option value="年度訂單">年度訂單</option>
						</select>
							
						<button class="btn btn-mydish btn-sm" style="font-size:initial;">搜尋</button>
						<input type="hidden" name="action" value="GET_ORDERS" >
					</form>
					
					</div>
					
			     	<table class="table">
				    		<caption>訂單明細專區</caption>
						<tr>
							<th>訂單編號</th>
							<td>${ordVO.ord_no}</td>
							<th>訂購日期</th>
							<td>${ordVO.ord_date}</td>
							<th>送餐地點</th>
							<td>${ordVO.ord_add}</td>
							
						</tr>
						<tr>
							<th>客戶名稱</th>
							<c:forEach var="memVO" items="${memSvc.all}">
								<c:if test="${ordVO.mem_no.equals(memVO.mem_no)}" >
									<td>${memVO.mem_name}</td>
								</c:if>
							</c:forEach>
							
							<th>聯絡電話</th>
							<c:forEach var="memVO" items="${memSvc.all}">
								<c:if test="${ordVO.mem_no.equals(memVO.mem_no)}" >
									<td>${memVO.mem_pho}</td>
								</c:if>
							</c:forEach>
							
							<th>電子郵件</th>
							<c:forEach var="memVO" items="${memSvc.all}">
								<c:if test="${ordVO.mem_no.equals(memVO.mem_no)}" >
									<td>${memVO.mem_mail}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<th>總金額</th>
							<td>${ordVO.ord_pri}</td>
							<th>送餐方式</th>
							<td>${ordVO.ord_type}</td>
							<th>訂單狀態</th>
							<td id="status">${ordVO.ord_stat}</td>
						</tr>
						<%-- <tr>
							<th>訂單回覆時間</th>
							<td colspan="2">${confirmDate}</td>
							<th>預計出餐時間</th>
							<td colspan="2"></td>
						</tr>
						<tr>
							<th>實際出餐時間</th>
							<td colspan="2"></td>
							<th>訂單結案時間</th>
							<td colspan="2"></td>
						</tr> --%>
				    		<tr style="background-color:#eee;">
							<th>訂單細項</th>
							<th colspan="2">餐點名稱</th>
							<th>訂購數量</th>
							<th>單件價格</th>
							<th>小計金額</th>
						</tr>
							
						<c:forEach var="orditVO" items="${orditList}">
			    			<tr>
							<td><%=++count %></td>
							
							<c:forEach var="dishVO" items="${dishSvc.all}">
								<c:if test="${orditVO.dish_no.equals(dishVO.dish_no)}" >
									<td colspan="2">${dishVO.dish_name}</td>
								</c:if>
							</c:forEach>
							
							<td>${orditVO.ordit_qua}</td>
							<td>${orditVO.ordit_pri}</td>
							<td>${orditVO.ordit_pri*orditVO.ordit_qua}</td>
						</tr>
						</c:forEach>						
				    	</table>	
				    	<div style="text-align:end;" id="confirm">
				    		<form action="<%=request.getContextPath() %>/ord/ord.do" method="post">
				    			<button class="btn btn-mydish btn-sm" style="font-size:initial;" name="status" value="confirm">確認</button>
				    			<button class="btn btn-mydish btn-sm" style="font-size:initial;" name="status" value="cancel">取消</button>
				    			<input type="hidden" name="action" value="MAKE_ORDERS">
				    			<input type="hidden" name="ord_no" value="${ordVO.ord_no}">
				    			<input type="hidden" name="ord_stat" value="${ordVO.ord_stat}">
				    		</form>
				    	</div>
				    	
			</div>
		</div>
	</div>	


	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		var stat = document.getElementById("status").textContent;
		if(stat == "已取消") {
			document.getElementById("confirm").style.visibility = "hidden";
		}
		
	</script>
</body>
</html>