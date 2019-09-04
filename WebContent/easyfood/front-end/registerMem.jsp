<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>���b��K</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="short Icon" type="images/x-icon" href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png
">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/css/register.css">
<body>

	<!--�����C=============================================================================================-->
<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />

	<!--�Ĥ@�C===================================================================================-->
	<div class="container">
		<div class="row main">
			<div class="main-login main-center">
				<form class="form-horizontal" METHOD="post" ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/mem.do"
					enctype="multipart/form-data">
					<%-- ���~��C --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>�Эץ��H�U���~:
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
						</font>
					</c:if>
					<h3 class="text-center">
						<b>�|�����U</b>
					</h3>
					<div class="form-group">
						<label for="name" class="cols-sm-2 control-label">�b��</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-user fa-fw" aria-hidden="true"></i></span> <input
									type="text" class="form-control" name="mem_acc" id="mem_acc"
									 placeholder="��J�A���b��" required	autofocus
									value="<%=(memVO == null) ? "" : memVO.getMem_acc()%>" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="email" class="cols-sm-2 control-label">�K�X</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-fw" aria-hidden="true"></i></span> <input
									type="password" class="form-control" name="mem_pw" id="mem_pw"
									placeholder="��J�A���K�X" required	autofocus
									value="<%=(memVO == null) ? "" : memVO.getMem_pw()%>" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="email" class="cols-sm-2 control-label">�T�{�K�X</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-fw" aria-hidden="true"></i></span> <input
									type="password" class="form-control" name="mem_pw_confirm"
									id="con_mem_pw" placeholder="�A����J�A���K�X" required	autofocus
									value="<%=(memVO == null) ? "" : memVO.getMem_pw()%>" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="username" class="cols-sm-2 control-label">�m�W</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-user fa-fw" aria-hidden="true"></i></span> <input
									type="text" class="form-control" name="mem_name" id="mem_name"
									placeholder="��J�A���W�l" required	autofocus
									value="<%=(memVO == null) ? "" : memVO.getMem_name()%>" />
							</div>
						</div>
					</div>

					<!--             <div class="form-group"> -->
					<!--               <label for="password" class="cols-sm-2 control-label">�����Ҧr��</label> -->
					<!--               <div class="cols-sm-10"> -->
					<!--                 <div class="input-group"> -->
					<!--                   <span class="input-group-addon"><i class="fa fa-id-card fa-fw" aria-hidden="true"></i></span> -->
					<!--                   <input type="text" class="form-control" name="mem_icn" id="password"  placeholder="��J�A�������Ҧr��" -->
					<%--                   value="<%= (memVO==null)? "A123456789":memVO.getMem_icn()%>"/> --%>
					<!--                 </div> -->
					<!--               </div> -->
					<!--             </div> -->

					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">������X</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-mobile-phone fa-lg fa-fw" aria-hidden="true"></i></span>
								<input type="tel" class="form-control" name="mem_pho"
									id="mem_pho" placeholder="��J�A��������X" required	autofocus
									value="<%=(memVO == null) ? "" : memVO.getMem_pho()%>" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">E-mail</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-envelope fa-fw" aria-hidden="true"></i></span> <input
									type="email" class="form-control" name="mem_mail" id="mem_mail"
									placeholder="��J�A��E-mail" required	autofocus
									value="<%=(memVO == null) ? "" : memVO.getMem_mail()%>" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">�j�Y�K</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<input type="file" class="form-control" name="mem_img"
									id="uplImg" placeholder="" />
								<div>
									<img class="img img-responsive center-block">
								</div>
							</div>
						</div>
					</div>

					<div class="form-group ">
						<input type="hidden" name="action" value="register"> <input
							type="submit"
							class="btn btn-success btn-lg btn-block login-button" value="���U">
						<!-- 	            <button type="button" class="btn btn-success btn-lg btn-block login-button">���U</button> -->
					</div>

					<div class="form-group ">
						<a class="btn btn-default btn-lg btn-block login-button" href="<%=request.getContextPath()%>/easyfood/front-end/login.jsp">�n�J</a>
					</div>

				</form>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-offset-sm-3 col-sm-9">
	<input type="button" name="" onclick="register()" value="�X"> 
    </div>
	<!--�������x����==============================================================================-->
<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />




	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
    function register(){
        document.getElementById("mem_acc").value="qwer";
        document.getElementById("mem_pw").value="qwerty";
        document.getElementById("con_mem_pw").value="qwerty";
        document.getElementById("mem_name").value="�\�ΤC";
        document.getElementById("mem_pho").value="0987958475";
        document.getElementById("mem_mail").value="h4528@gmail.com";
      }
	
	
		//�Ϥ��w��  
		$(function() {
			function preview1(input) {
				if (input.files && input.files[0]) {
					var reader = new FileReader();
					reader.onload = function(e) {
						$('.img').attr('src', e.target.result);
					}
					reader.readAsDataURL(input.files[0]);
				}
			}

			$("#uplImg").change(function(input) {
				preview1(this);
			})
		})
	</script>
</body>

</html>
