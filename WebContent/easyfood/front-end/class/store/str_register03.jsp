<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Rigister Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<!-- <link rel="stylesheet" type="text/css" href="../css/base.css"> -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/style.css"">
		<style>
			.form-group {
				font-size: 20px;
			}
			a, a:hover, a:focus {
				color: #fff;
				text-decoration: none
			}
		</style>
	</head>
	<body>

<!-- header =======================================-->
		<div class="page-header center-header">
		  <span>食在方便<small>店家中心</small></span>
		</div>
<!-- 註冊表單 =======================================-->		
<div class="container-floid rig-container">
	<div class="row">
		<div class="col-xs-12 col-sm-4 col-sm-offset-4 rig-panel">

			<legend class="text-center font-weight-bold text-success">
			<h2>店家註冊中心</h2>
			<small>詳細資料</small>
			</legend>
			
			<c:if test="${not empty errorMsgs}">
				<div class="error">請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
				</div>
			</c:if>
				
			<form action="<%=request.getContextPath()%>/str/store.do" method="post">
			
			<div class="form-group">
				平均備餐時間<br>
				<label class="radio-inline" for="time1">
					<input type="radio" name="str_pre" id="time1" value="15">15 分鐘
				</label>
				<label class="radio-inline" for="time2">
					<input type="radio" name="str_pre" id="time2" value="30" checked>30 分鐘
				</label>
				<label class="radio-inline" for="time3">
					<input type="radio" name="str_pre" id="time3" value="60" >60 分鐘
				</label>
				<label class="radio-inline" for="time4">
					<input type="radio" name="str_pre" id="time4" value="90" >90 分鐘
				</label>
			</div>
			
			
			<div class="form-group">
				是否提供外送?<br>
				<label class="radio-inline" for="true">
					<input type="radio" name="str_ship" id="true" value="TRUE" checked>Yes
				</label>
				<label class="radio-inline" for="false">
					<input type="radio" name="str_ship" id="false" value="FALSE">No
				</label>
			</div>
			
			<div>
				<button class="btn btn-success btn-block btn-lg login-btn">確認送出</button>
				<input type="hidden" name="action" value="getRegisteTd">
			</div>
			
			</form>
<!-- 表單內容結束 =======================================-->
		</div>
	</div>
</div>

	
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	</body>
</html>