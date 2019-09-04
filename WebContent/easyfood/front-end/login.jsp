<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="short Icon" type="images/x-icon" href="images/logo.jpg">
   	<link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	<link rel="short Icon" type="images/x-icon" href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
    <link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
    <link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/css/register.css">
    
    <style type="text/css">  

    </style>
	</head>
	<body>


	<!--導覽列=============================================================================================-->
<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />



<!--第一列===================================================================================-->
	<br><br>
	<div class="container">
		<div class="row main">

			<div class="main-login main-center" id="main-center">

				
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>請修正以下錯誤:
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
						</font>
					</c:if>
					<h3 class="text-center">
						<b>會員登入</b>
					</h3>
					<form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/easyfood/front-end/class/member/mem.do">
					<div class="form-group">
						<label for="name" class="cols-sm-2 control-label">帳號</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-user fa-fw" aria-hidden="true"></i></span> <input
									type="text" class="form-control" id="mem_acc" name="mem_acc"
									value="<%=(memVO == null) ? "" : memVO.getMem_acc()%>" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="email" class="cols-sm-2 control-label">密碼</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-fw" aria-hidden="true"></i></span> <input
									type="password" class="form-control" id="mem_pw" name="mem_pw"
									value="<%=(memVO == null) ? "" : memVO.getMem_pw()%>" />
							</div>
						</div>
					</div>

					<div class="form-group ">
						<input type="submit"
							class="btn btn-success btn-lg btn-block login-button" value="登入">
						<input type="hidden" name="action" value="login">
					</div>

					<div class="form-group ">
						<a href="<%=request.getContextPath()%>/easyfood/front-end/registerMem.jsp"
							class="btn btn-default btn-lg btn-block login-button"
							role="button">註冊</a>
					</div>

				</form>
			</div>
		</div>
	</div>

<br>
     <div class="col-xs-12 col-offset-sm-3 col-sm-9">
     <input type="button" name="" onclick="login1()" value="琪"> 
     <input type="button" name="" onclick="login2()" value="陽"> 
     <input type="button" name="" onclick="login3()" value="瑋"> 
     <input type="button" name="" onclick="login4()" value="漾"> 
     <input type="button" name="" onclick="login5()" value="西"> 
     </div>


	<!--底部平台介紹=============================================================================================-->


<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />
		
		
		
		
	<script type="text/javascript">
      function login1(){
        document.getElementById("mem_acc").value="CHICHI";
        document.getElementById("mem_pw").value="123456";
      }
      function login2(){
          document.getElementById("mem_acc").value="WANGYANGMING";
          document.getElementById("mem_pw").value="123456";
        }
      function login3(){
          document.getElementById("mem_acc").value="Taste";
          document.getElementById("mem_pw").value="123456";
        }
      function login4(){
          document.getElementById("mem_acc").value="YANGYANG";
          document.getElementById("mem_pw").value="123456";
        }
      function login5(){
          document.getElementById("mem_acc").value="cheng";
          document.getElementById("mem_pw").value="123456";
        }
    </script>
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
	</body>
</html>