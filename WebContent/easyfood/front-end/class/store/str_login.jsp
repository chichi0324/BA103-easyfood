<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.store.model.*"%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Login Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/style.css">
		<style>
			a, a:focus, a:hover {
				color: #fff;
				text-decoration: none

			}
		</style>
	</head>
	<body>

<!-- header -->
		<div class="page-header center-header">
		  <span>食在方便<small>店家中心</small></span>
		</div>
		
<div class="container-floid login-container">
	<div class="row">
		<div class="col-xs-12 col-sm-4 col-sm-offset-4 login-panel">
<!-- 登入表單 =======================================-->	

<legend class="text-center font-weight-bold text-success"><h2>店家登入</h2></legend>


	<c:if test="${not empty errorMsgs}">
		<div class="error">
			<c:forEach var="message" items="${errorMsgs}">
				<ul>
					<li>${message}</li>
				</ul>
			</c:forEach>
		</div>
	</c:if>

	<c:if test="${inform != null}">
		<div style="color:#e0724f;text-align:center;">${inform}</div>
	</c:if>
		
	<form action="<%=request.getContextPath()%>/str/store.do" method="post">
	
		<div class="form-group">
			<label for="name">帳號</label>
			<input type="text" name="str_acc" id="name" placeholder="請輸入帳號" class="form-control">
		</div>
		
		<div class="form-group">
			<label for="psw">密碼</label>
			<input type="password" name="str_pas" id="psw" placeholder="請輸入密碼" class="form-control">
			<a class="text-success forget-psw" href='#modal-forget' data-toggle="modal">忘記密碼</a>
		</div>
		
		<!-- modal忘記密碼視窗 =======================================-->
		<div class="modal fade" id="modal-forget">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body text-center">密碼已送至您的信箱</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">關閉</button>
					</div>
				</div>
			</div>
		</div>
		<div>
			<button class="btn btn-success btn-block btn-lg login-btn">登入</button>
			<input type="hidden" name="action" value="str_login">
		</div>
	</form>
	<!-- 表單內容結束 =======================================-->
	<button class="btn btn-success btn-block btn-lg login-btn">
		<a href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_register01.jsp">註冊店家</a>
	</button>
	
	<div style="text-align:center;">
		<button id=Yung class="btn-sm">Yung</button>
	</div>
	
		</div>
	</div>
</div>
		
	
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
	<script>
	
	function login() {
		document.getElementById("name").value = "flow0815";
		document.getElementById("psw").value = "flow1111";
	}
	document.getElementById("Yung").onclick = login;
	
	</script>
</html>