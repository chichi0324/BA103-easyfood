<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.store.model.*"%>
<%
StrVO strVO = (StrVO) request.getAttribute("strVO");
%>

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
<!-- 		<link rel="stylesheet" type="text/css" href="../css/base.css"> -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/css/style.css">	
	</head>
	<body>

<!-- header =======================================-->
		<div class="page-header center-header">
		  <span>���b��K<small>���a����</small></span>
		</div>
		
<div class="container-floid rig-container">
  <div class="row">
		<div class="col-xs-12 col-sm-4 col-sm-offset-4 rig-panel">

<!-- ���U��� =======================================-->  
			<legend class="text-center font-weight-bold text-success">
				<h2>���a���U����</h2>
				<small>�b���ӽ�</small>
			</legend>
			
			<c:if test="${not empty errorMsgs}">
				<div class="error">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
				</div>
			</c:if>

	
			<form action="<%=request.getContextPath()%>/str/store.do" method="post">
			
			<div class="form-group">
				<label for="acc">�b��</label>
				<input type="text" name="str_acc" id="acc" placeholder="�ж�J�b��" class="form-control"
					value="<%= (strVO==null) ? "" : strVO.getStr_acc() %>">
				<div class="hint">�п�J�K��Ƥ��^�Ʀr</div>
			</div>
			
			<div class="form-group">
				<label for="pas">�K�X</label>
				<input type="password" name="str_pas" id="pas" placeholder="�ж�J�K�X" class="form-control"
					value="<%= (strVO==null) ? "" : strVO.getStr_pas() %>">
				<div class="hint">�п�J�K��Ƥ��^�Ʀr</div>
			</div>
			
			<div class="form-group">
				<label for="re_pas">�T�{�K�X</label>
				<input type="password" name="str_repas" id="re_pas" placeholder="�нT�{�K�X" class="form-control">
			</div>

			<div><button class="btn btn-success btn-block btn-lg login-btn">�e�X</button></div>
			<input type="hidden" name="action" value="getRegisterSt">
			</form>
<!-- ��椺�e���� =======================================-->

<div style="text-align:center;">
	<button id=Yung class="btn-sm">MyHotPot</button>
</div>

		</div>
	</div>
</div>
	


	
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
	
		function login() {
			document.getElementById("acc").value = "myhouse";
			document.getElementById("pas").value = "myhouse01";
			document.getElementById("re_pas").value = "myhouse01";
		}
		document.getElementById("Yung").onclick = login;
	
		</script>
	</body>
</html>