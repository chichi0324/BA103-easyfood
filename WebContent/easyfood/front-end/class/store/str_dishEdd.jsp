<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.store.model.*"%>
<%@ page import="com.dish.model.*" %>
<%	
	String str_no = (String) session.getAttribute("str_no");	
	StrService strSvc = new StrService();
	StrVO strVO = strSvc.getOneStr(str_no);	
%>
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
			p {color: #34495E;}
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
		
		<!-- breadcrumb 麵包屑 =======================================-->
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
						<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishView.jsp" class="list-group-item">菜單總覽</a>
						<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishEdd.jsp" class="list-group-item list-group-item-info">菜單上架</a>
						<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_dishUpdate.jsp" class="list-group-item">菜單修改</a>
					</div>
				</div>
		
		
				<!-- 右邊開始 =======================================-->			
				<div class="col-xs-12 col-sm-9">
				
					<c:if test="${not empty errorMsgs}">
						<div class="error">請修正以下錯誤:
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					
					<jsp:useBean id="dclaDAO" scope="page" class="com.dishclass.model.DclaDAO" />
					
					<div class="col-xs-12" style="text-align:end;margin-top:10px;margin-bottom:10px;padding-right:5px;width:750px;">
						<button class="btn btn-sm btn-mydish" id="addMenu">新增</button>
						<button class="btn btn-sm btn-mydish" id="removeMenu">移除</button>
					</div>
					
					<form action="<%=request.getContextPath()%>/dish/dish.do" method="post" enctype="multipart/form-data" style="margin-top:0; margin-bottom:0;padding-top:10px;">
						<div style="display:-webkit-box;margin-bottom:10px;">
							<button class="btn btn-sm btn-mydish">上架</button>
							<input type="hidden" name="action" value="INSERT_DISHES">
						</div>
					
						<div class="col-xs-12" id="menuTable">
							<div class="media" name="media">
								<div class="media-left">
									<img src="<%=request.getContextPath()%>/easyfood/front-end/class/store/images/food.png" 
										class="media-object" height="150px" width="200px">
								</div>
								
								<div class="media-body">
									<div class="dish">
										<h4 class="media-heading">
											<select class="form-control" name="dcla_no" id="categ">
											<option value="菜單分類">菜單分類</option>
											<c:forEach var="dclaVO" items="${dclaDAO.all}">
										    		<option value="${dclaVO.dcla_no}">${dclaVO.dcla_name}</option>
										    </c:forEach>
											</select>
										</h4>
										<p><input type="text" name="dish_name" placeholder="菜單名稱"></p>
										<p><textarea name="dish_note" placeholder="菜單介紹"></textarea></p>
										<p><input type="text" name="dish_price" placeholder="菜單價格 NTD" style="color: #e83131;"></p>
										<p><input class="dish_pic" type="file" name="dish_pic0" style="border:hidden;"></p>
									</div>
								</div>
								<hr>
							</div>
						</div>
					
					</form>
				
				</div>
			</div>
		</div>	
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
			var inputFiles = document.getElementsByClassName("dish_pic");
			var count = 0;
			var k = 0;
			function addMenu(){
				var menuTalbe = document.getElementById("menuTable");
				var media = document.getElementsByName("media")[0].cloneNode(true);
				menuTable.appendChild(media);
				$("input[type=file]:last").val("");
				$("input[type=file]:last").attr("name", "dish_pic"+(++k));
				$("img:last").attr("src", "<%=request.getContextPath()%>/easyfood/front-end/class/store/images/food.png");
				fileOnchange();
			}
			function removeMenu(){
				var mediaList = document.getElementsByName("media");
				var removeMedia = document.getElementById("menuTable").lastElementChild;
				if(mediaList.length != 1) {
					menuTable.removeChild(removeMedia);
				}
			}
			function addImage(e){
				var inputFile = e.target;
				for(var j = 0; j<inputFiles.length; j++){
					if(inputFile == inputFiles[j]){
						count = j;
					}
				
				}
				
				if(inputFile.files && inputFile.files[0]) {
					var reader = new FileReader();
					reader.onload = function(e) {
						$("img")[count].setAttribute("src", e.target.result);
					}
					
					reader.readAsDataURL(this.files[0]);
				}
			}
			
			function fileOnchange(){
				for(var i =0; i< inputFiles.length; i++){
					/* document.getElementsByName("dish_pic"+i).onchange = addImage; */
					inputFiles[i].onchange = addImage;
				}
				
			}
			
			function init(){
				var addBtn = document.getElementById("addMenu");
				var removeBtn = document.getElementById("removeMenu");
				
				addBtn.onclick = addMenu;
				removeBtn.onclick = removeMenu;
				fileOnchange();
			}
			window.onload = init;
		</script>
	</body>
</html>