<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Register Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<!-- <link rel="stylesheet" type="text/css" href="../css/base.css"> -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/style.css">
		<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/easyfood/front-end/class/store/js/transLatLng.js"></script> --%>
		

	</head>
	<body>

<!-- header =======================================-->
		<div class="page-header center-header">
		  <span>食在方便<small>店家中心</small></span>
		</div>
	
		<div class="container-floid rig-container">
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-sm-offset-4 rig-panel">	
<!-- 註冊表單 =======================================-->	
					<legend class="text-center font-weight-bold text-success">
						<h2>店家註冊中心</h2>
						<small>基本資料</small>
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
					
					<jsp:useBean id="stocaDAO" scope="page" class="com.storecategory.model.StocaDAO" />
					
					<form action="<%=request.getContextPath()%>/str/store.do" method="post">
						<div class="form-group rig-form-inline">
							<label for="name">店家名稱</label>
							<input type="text" name="str_name" id="name" placeholder="店家名稱" size="40" class="form-control">
						</div>
						
						<div class="form-group rig-form-inline">
							<label for="categ">店家類別</label>
							<select class="form-control" name="stoca_no" id="categ" style="width:10em;">
							<option value="請選擇">請選擇</option>
							<c:forEach var="stocaVO" items="${stocaDAO.all}">
								<option value="${stocaVO.stoca_no}">${stocaVO.stoca_name}
							</c:forEach>	
						  	</select>
						</div>
						
						<div class="form-group rig-form-inline">
							<label for="county">縣市</label>		
								<select class="form-control" name="str_cou" id="county" style="width:180px;">
								<option value="請選擇">請選擇</option>
							    <option value="台北市">台北市</option>
							    <option value="桃園市">桃園市</option>
							    <option value="新竹市">新竹市</option>
						  	</select>
						</div>
						
						<div class="form-group rig-form-inline">
							<label for="city">地區</label>
							<input type="text" name="str_city" id="city" size="34" class="form-control">
						</div>
							
						<div class="form-group">
							<label for="addr">詳細地址</label>
							<input type="text" name="str_addr" id="addr" class="form-control">
						</div>
						
						<div class="form-group rig-form-inline">
							<label for="attn">聯絡人</label>
							<input type="text" name="str_atn" id="attn" size="20" class="form-control">
						</div>
						
						<div class="form-group rig-form-inline">
							<label for="tel">聯絡電話</label>
							<input type="tel" name="str_tel" id="tel" size="35" class="form-control" placeholder="02-12345678">
						</div>
						
						<div class="form-group">
							<label for="mail">聯絡信箱</label>
							<input type="mail" name="str_ma" id="mail" placeholder="請填入連絡信箱" class="form-control">
						</div>
						
						<input type="text" name="str_lat"  id="lat">
						<input type="text" name="str_long"  id="lng">
					
						<div>
							<button class="btn btn-success btn-block btn-lg login-btn" onmousedown="trans();">提交</button>
							<input type="hidden" name="action" value="getRegisteEd">
						</div>
						
					</form>
					
<!-- 表單內容結束 =======================================-->
<div style="text-align:center;">
	<button id=Yung class="btn-sm">MyHotPot</button>
</div>
						
				</div>
			</div>
		</div>
	

	
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    		<script src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyByu95g9OE5DY3z-4DjCnif5tSTtDzgkX4"></script>
    		<script src="<%=request.getContextPath()%>/easyfood/front-end/class/store/js/transLatLng.js"></script>
    		<script>
    		function login() {
    			document.getElementById("name").value = "MyHotPot";
    			document.getElementById("categ").value = "STOCA_0004";
    			document.getElementById("county").value = "桃園市";
    			document.getElementById("city").value = "中壢區";
    			document.getElementById("addr").value = "中央路216巷200號";
    			document.getElementById("attn").value = "李映蓉";
    			document.getElementById("tel").value = "0978-087-183";
    			document.getElementById("mail").value = "kurtie.one215@gmail.com";
    		}
    		document.getElementById("Yung").onclick = login;
    		</script>
	</body>
</html>