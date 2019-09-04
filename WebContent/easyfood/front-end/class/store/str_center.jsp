<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.store.model.*"%>
<%@ page import="com.blog.model.*" %>
<%@ page import="com.ord.model.*" %>
<%	
	String str_no = (String) session.getAttribute("str_no");	
	StrService strSvc = new StrService();
	StrVO strVO = strSvc.getOneStr(str_no);
	pageContext.setAttribute("strVO", strVO);
	
	BlogService blogSvc = new BlogService();
	List<BlogVO> blogList = blogSvc.findByStore(str_no);
	pageContext.setAttribute("blogList", blogList);
	
	int uncheckNum = 0, checkedNum = 0, evaCount = 0;
	OrdService ordSvc = new OrdService();
	List<OrdVO> myList = ordSvc.getAllByStr(str_no);
	for(OrdVO orderVO : myList) {
		if(orderVO.getOrd_stat().equals("未確認")) {
			uncheckNum++;
		}
	}
	
	for(OrdVO orderVO : myList) {
		if(orderVO.getOrd_stat().equals("已確認")) {
			checkedNum++;
		}
	}
	
	for(OrdVO orderVO : myList) {
		if(orderVO.getOrd_eva() != null && orderVO.getOrd_eva().length() != 0) {
			evaCount++;
		}
	}
	
	pageContext.setAttribute("uncheckNum", uncheckNum);
	pageContext.setAttribute("checkedNum", checkedNum);
	pageContext.setAttribute("evaCount", evaCount);
	/* System.out.println("uncheckNum =" + uncheckNum);
	System.out.println("checkedNum =" + checkedNum); */
	
%>
<jsp:useBean id="memSvc" class="com.mem.model.MemService"/>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Center Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<!-- <link rel="stylesheet" type="text/css" href="../css/base.css"> -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/style.css">
		
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
						<li class="active"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_center.jsp">店家中心</a></li>
						<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishView.jsp">菜單專區</a></li>
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
		<!-- breadcrumb 麵包屑 =======================================-->
		<ol class="breadcrumb">
			<li>
				<a href="<%=request.getContextPath()%>/easyfood/front-end/index.jsp">首頁</a>
			</li>
			<li class="active">店家中心</li>
		</ol>


		<!-- container 內文開始 =======================================-->
		<div class="container">
			<div class="row">

				<!-- 左邊開始 =======================================-->	
				<div class="col-xs-12 col-sm-3">

					<div class="list-group">
						<div class="list-group-item list-group-custom active">消費評等</div>
						<jsp:include page="str_starCounting.jsp" />
					</div>
					
					<div class="list-group">
						<div class="list-group-item list-group-custom active">推薦文章</div>
						<c:forEach var="blogVO" items="${blogList}" end="2">
						<div class="card">
						  	<img class="card-img" src="<%=request.getContextPath()%>/tools/Mem_Red_Img?mem_no=${blogVO.mem_no}" alt="Avatar">
						  	<div class="container-card">
						  	<c:forEach var="memVO" items="${memSvc.all}">
						  		<c:if test="${blogVO.mem_no.equals(memVO.mem_no)}">
								<h4><b>${memVO.mem_name}</b></h4>
								</c:if>
							</c:forEach>
								<p><a class="blog" href="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do?action=blog_display_all&bl_no=${blogVO.bl_no}">${blogVO.bl_name}</a></p>
							
							</div>
						</div>
						</c:forEach>
					</div>
					
				</div>
				<!-- 左邊結束 =======================================-->



				<!-- 右邊開始 =======================================-->	
				<div class="col-xs-12 col-sm-9">				
					<div class="col-xs-12 col-sm-12" style="padding-top:8px;">
						<img src="<%=request.getContextPath()%>/easyfood/front-end/class/store/images/spices-header.jpg" class="img-responsive">
					</div>

					<!-- 右下一開始 -->
					<div class="col-xs-12 col-sm-12 center-list">
						<div class="row">
							<div class="col-xs-12 col-sm-4">
								<div class="panel panel-custom">
								  <div class="panel-heading">
								    <h3 class="panel-title">店家帳號</h3>
								  </div>
								  <ul class="list-group">
								  	<li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile01.jsp" class="store-func">基本資料</a></li>
								    <li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile02.jsp" class="store-func">修改資料</a></li>
								    <li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile03.jsp" class="store-func">修改密碼</a></li>
								  </ul>
								</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="panel panel-custom">
								  <div class="panel-heading">
								    <h3 class="panel-title">菜單資訊</h3>
								  </div>
								  <ul class="list-group">
								    <li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishView.jsp" class="store-func">菜單總覽</a></li>
								    <li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishEdd.jsp" class="store-func">菜單上架</a></li>
								    <li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishUpdate.jsp" class="store-func">菜單修改</a></li>
								  </ul>
								</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="panel panel-custom">
								  <div class="panel-heading">
								    <h3 class="panel-title">訂單資訊</h3>
								  </div>
								  <ul class="list-group">
								  	<li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderView.jsp" class="store-func">訂單總覽</a></li>
								    <li class="list-group-item">
								    <span class="badge">${uncheckNum}</span><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderCheck.jsp" class="store-func">未確認訂單</a></li>
								    <li class="list-group-item">
								    <span class="badge">${checkedNum}</span><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_orderConfirm.jsp" class="store-func">已確認訂單</a></li>
								    
								  </ul>
								</div>
							</div>
						</div>
					</div>
					<!-- 右下一結束 -->

					<!-- 右下二開始 -->
					<div class="col-xs-12 col-sm-12  center-list">
						<div class="row">
							<div class="col-xs-12 col-sm-4">
								<div class="panel panel-custom">
								  <div class="panel-heading">
								    <h3 class="panel-title">個人訊息</h3>
								  </div>
								  <ul class="list-group">
								    <li class="list-group-item">
								    <span class="badge">${evaCount}</span><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_inform01.jsp" class="store-func">評價通知</a></li>
								    <li class="list-group-item">
								    <span class="badge">${strVO.str_rep}</span><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_inform02.jsp" class="store-func">檢舉通知</a></li>
								</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="panel panel-custom">
								  <div class="panel-heading">
								    <h3 class="panel-title">刊登廣告</h3>
								  </div>
								  <ul class="list-group">
								    <li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_advertise01.jsp" class="store-func">廣告總覽</a></li>
								    <li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_advertise02.jsp" class="store-func">廣告上架申請</a></li>
								    
								  </ul>
								</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="panel panel-custom">
								  <div class="panel-heading">
								    <h3 class="panel-title">促銷優惠</h3>
								  </div>
								  <ul class="list-group">
								    <li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_bonus_01.jsp" class="store-func">促銷專區總覽</a></li>
								    <li class="list-group-item"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_bonus_02.jsp" class="store-func">促銷活動申請</a></li>
								  </ul>
								</div>
							</div>
						</div>
					</div>
					<!-- 右下二結束 -->


				</div>
				<!-- 右邊結束 =======================================-->

			</div>
		</div>
		<!-- container 內文結束 =======================================-->
		
		
				
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>