<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.storecategory.model.*"%>

<%
	String str_no = (String) session.getAttribute("str_no");	
	
	if(str_no == null){
		session.setAttribute("str_no", "STR_0071");		
		str_no = (String) session.getAttribute("str_no");		
	}
%>
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StrService" />
<jsp:useBean id="StocaSvc" scope="page" class="com.storecategory.model.StocaService" />
<% StrVO strVO = storeSvc.getOneStr(str_no); %>
<% StocaVO StocaVO = StocaSvc.getOneStoca(strVO.getStoca_no()); %>
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
			}
			.css_tr {
				display: table-row;
			}
			.css_td {
				display: table-cell;
				padding-top: 6px;
				padding-left: 20px;
				line-height: 2;
			}
			.input-group-addon {
				margin-bottom: 4px;
				margin-right: 2em;
			}
			input, select {
				font-size: larger;
				width: 250px;
			}
			.strimg {
				width: 150px; 
				height: 150px;
			}
			#update {
				width: 420px;
				text-align: center;
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
				<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile02.jsp" class="list-group-item list-group-item-info">修改資料</a>
				<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_profile03.jsp" class="list-group-item">修改密碼</a>
			</div>
		</div>


		<!-- 右邊開始 =======================================-->			
		<div class="col-xs-12 col-sm-9">
		
			<table>		
					
				<form action="<%=request.getContextPath()%>/str/store.do" method="post" enctype="multipart/form-data" >
					<div class="css_tr">	
						<div class="input-group-addon">店家頭像  </div>
						<div class="css_td">
							<img id="image" class="strimg" src="<%=request.getContextPath()%>/str/readStorePicture.do?STR_NO=<%=str_no%>">
							<button class="btn btn-primary btn-sm" style="width:auto;">更新相片</button>
							<input type="file" id="files" name="str_img" value="" style="border:hidden;">
							<input type="hidden" name="action" value="Up_For_Image">
						</div>
					</div>
				</form>
				
				<form action="<%=request.getContextPath()%>/str/store.do"  method="post">
				 
				<div class="css_tr">
					<div class="input-group-addon">店家簡介</div>
					<div class="css_td"><input class="input" type="text" name="str_note" value=<%= strVO.getStr_note()%>></div>
				</div>


				<div class="css_tr">
					<div class="input-group-addon">店家名稱</div>
					<div class="css_td"><input class="input" type="text" name="str_name" value=<%= strVO.getStr_name()%> readonly></div>
				</div>

				<div class="css_tr">
					<div class="input-group-addon">店家類別</div>
					<div class="css_td"><input class="input" type="text" name="stoca_name" value=<%= StocaVO.getStoca_name() %> readonly></div>
				</div>

				<div class="css_tr">
					<div class="input-group-addon">店家地址</div>
					<div class="css_td">                                     
						<input class="input" type="text" name="str_cou" id="county" value=<%= strVO.getStr_cou()%>>
					
						
						<input class="input" type="text" name="str_city" id="city" value=<%= strVO.getStr_city()%>>
					
					
						<input class="input" type="text" name="str_addr" id="addr" value=<%= strVO.getStr_addr()%>>
					</div>
				</div>

				<div class="css_tr">
					<div class="input-group-addon">聯絡人</div>
					<div class="css_td">
						<input class="input" type="text" name="str_atn" value=<%= strVO.getStr_atn()%>>
					</div>
				</div>

				<div class="css_tr">
					<div class="input-group-addon">聯絡電話</div>
					<div class="css_td">
						<input class="input" type="text" name="str_tel" value=<%= strVO.getStr_tel()%>>
					</div>
				</div>

				<div class="css_tr">
					<div class="input-group-addon">聯絡信箱</div>
					<div class="css_td">
						<input class="input" type="text" name="str_ma" value=<%= strVO.getStr_ma()%>>
					</div>
				</div>

				<div class="css_tr">
					<div class="input-group-addon">平均備餐時間</div>
					<div class="css_td">
						<select name="str_pre" class="select-sm" id="time">
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="60">60</option>
							<option value="90">90</option>
						</select>
						&nbsp;分鐘
					</div>
				</div>

				<div class="css_tr">
					<div class="input-group-addon">提供外送</div>
					<div class="css_td">
						<input style="width:30px;" type="radio" name="str_ship" id="true" value="TRUE" <%= "TRUE".equals(strVO.getStr_ship()) ? "checked" : " "%> >
						<label for="true">Yes</label>&nbsp;
						<input style="width:30px;" type="radio" name="str_ship" id="false" value="FALSE" <%= "FALSE".equals(strVO.getStr_ship()) ? "checked" : " "%> >
						<label for="false">No</label>
					</div>
				</div>
				<div>
					<input type="hidden" name="str_lat"  id="lat" value="<%= strVO.getStr_lat() %>">
					<input type="hidden" name="str_long"  id="lng" value="<%= strVO.getStr_long() %>">
				</div>	
				<div id="update">
					
						<input type="submit" class="btn btn-sm btn-primary" value="修改">
	       				<input type="hidden" name="action" value="str_update">
       				
				</div>
					
       				
				</form>
		
			</table>
		</div>
		
	</div>
		
	
<!-- 右邊結束 =======================================-->
</div>




		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyByu95g9OE5DY3z-4DjCnif5tSTtDzgkX4"></script>
		<script src="<%=request.getContextPath()%>/easyfood/front-end/class/store/js/transLatLng.js"></script>
		<script>
		
		function gettime(){
		
			var time = <%=strVO.getStr_pre()%>;
			console.log("");
			
			switch(time) {
		    case 15:
		    	$('#time').val('15');
		        break;
		    case 30:
		    	$('#time').val('30');
		        break;
		    case 60:
		    	$('#time').val('60');
		        break;
		    case 90:
		    	$('#time').val('90');
		        break;
			}
 		}
		
		
		document.getElementById("files").onchange = function () {
		    var reader = new FileReader();

		    reader.onload = function (e) {
		        // get loaded data and render thumbnail.
		        document.getElementById("image").src = e.target.result;
		    };

		    // read the image file as a data URL.
		    reader.readAsDataURL(this.files[0]);
		};
		
		$(document).ready(function(){
			console.log("123");
			gettime();
		});
		</script>
		
	</body>
</html>