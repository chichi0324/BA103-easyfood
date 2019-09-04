<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*, com.add.model.*,java.util.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String Str = (String) session.getAttribute("showImgByBase64");
	String showImgByBase64 = "data:image/jpg;base64," + Str;
	AddService addSvc = new AddService();
	List<AddVO> allAddList = addSvc.getAll();
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="short Icon" type="images/x-icon"
	href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<link rel="short Icon" type="images/x-icon"
	href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member_information.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member.css">
</head>

<body>

	<!--導覽列===============================================================================-->
<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />

	<!--第一列===================================================================================-->
	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<ol class="breadcrumb">
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=index">首頁</a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/member/member.jsp">會員中心</a></li>
					<li class="active"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/member/member_information.jsp">會員資料</a></li>
				</ol>
			</div>
		</div>
	</div>
	
	
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
			
                    <jsp:include page="leftGroup.jsp" flush="true" />
                    

			</div>

			<div class="col-xs-12 col-sm-6">
				<div>


					<div role="tabpanel">
						<!-- 標籤面板：標籤區 -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#tab1"
								aria-controls="tab1" role="tab" data-toggle="tab">個人資料</a></li>
							<li role="presentation"><a href="#tab2" aria-controls="tab2"
								role="tab" data-toggle="tab">常用地址</a></li>
						</ul>

						<!-- 標籤面板：內容區 -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab1">
                                
								<div class="main-login main-center" >
									<form class="form-horizontal" method="post"
										action="<%=request.getContextPath()%>/easyfood/front-end/class/member/mem.do"
										enctype="multipart/form-data">
										<c:if test="${not empty errorMsgs}">
											<font color='red'>訊息:
												<ul>
													<c:forEach var="message" items="${errorMsgs}">
														<li>${message}</li>
													</c:forEach>
												</ul>
											</font>
										</c:if>
										<div class="form-group">
											<label for="name" class="cols-sm-2 control-label">帳號</label>
											<div class="cols-sm-10">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-user fa-fw" aria-hidden="true"></i></span> <input
														type="text" class="form-control" id="name"
														value="${memVO.mem_acc}" disabled /> <input type="hidden"
														class="form-control" name="mem_acc" id="name"
														value="${memVO.mem_acc}" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<label for="email" class="cols-sm-2 control-label">密碼</label>
											<div class="cols-sm-10">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-lock fa-fw" aria-hidden="true"></i></span> <input
														type="password" class="form-control" name="mem_pw"
														id="password" value="${memVO.mem_pw}" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<label for="username" class="cols-sm-2 control-label">姓名</label>
											<div class="cols-sm-10">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-user fa-fw" aria-hidden="true"></i></span> <input
														type="text" class="form-control" name="mem_name"
														id="username" value="${memVO.mem_name}" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<label for="confirm" class="cols-sm-2 control-label">手機號碼</label>
											<div class="cols-sm-10">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-mobile-phone fa-lg fa-fw" aria-hidden="true"></i></span>
													<input type="tel" class="form-control" name="mem_pho"
														id="confirm" value="${memVO.mem_pho}" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<label for="confirm" class="cols-sm-2 control-label">E-mail</label>
											<div class="cols-sm-10">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-envelope fa-fw" aria-hidden="true"></i></span> <input
														type="email" class="form-control" name="mem_mail"
														id="confirm" value="${memVO.mem_mail}" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<label for="name" class="cols-sm-2 control-label">大頭貼</label>
											<div class="cols-sm-10">
												<div class="input-group">
													<img src="data:image/jpg;base64,${showImgByBase64}"
														class="img img-responsive center-block img-thumbnail"
														style="padding: 20px"> <input type="file"
														class="form-control" name="mem_img" id="uplImg"
														value="更換大頭貼" />
												</div>
											</div>
										</div>

										<div class="form-group ">
											<input type="submit"
												class="btn btn-success btn-lg btn-block login-button"
												value="修改"> <input type="hidden" name="action"
												value="update">
										</div>
									</form>
								</div>

							</div>
							<div role="tabpanel" class="tab-pane" id="tab2">

								<div class="main-login main-center">
									<c:if test="${not empty errorMsgs1}">
										<font color='red'>訊息:
											<ul>
												<c:forEach var="message" items="${errorMsgs1}">
													<li>${message}</li>
												</c:forEach>
											</ul>
										</font>
									</c:if>
									<form class="form-horizontal" method="post"
										action="<%=request.getContextPath()%>/easyfood/front-end/class/member/Add.do">
                                        <br><br>
										<div class="form-group">
											<label for="address" class="cols-sm-2 control-label">地址</label>
											<div class="cols-sm-10">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-map-marker" aria-hidden="true"></i></span> <input
														type="text" class="form-control" name="add_adds"
														id="address" value="" />

												</div>
											</div>
										</div>

										<div class="form-group ">
											<button type="submit"
												class="btn btn-success btn-block login-button">
												<b>新增</b>
											</button>
											<input type="hidden" name="action" value="addAdd"> <input
												type="hidden" name="mem_no" value="${memVO.mem_no}">
										</div>

									</form>
									<%
										for (int i = 0; i < allAddList.size(); i++) {
											AddVO aAddVO = allAddList.get(i);
											if (aAddVO.getMem_no().equals(memVO.getMem_no())) {
									%>

									<form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/easyfood/front-end/class/member/Add.do">

										<div class="form-group">
											<label for="address" class="cols-sm-2 control-label">地址</label>
											<div class="cols-sm-10">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-map-marker" aria-hidden="true"></i></span> <input
														type="text" class="form-control" name="add_adds"
														id="address" value="<%=aAddVO.getAdd_adds()%>" />

												</div>
											</div>
										</div>

										<div class="form-group ">
											<button type="submit"
												class="btn btn-success btn-block login-button">
												<b>修改</b>
											</button>
											<input type="hidden" name="action" value="updateAdd">
											<input type="hidden" name="mem_no" value="${memVO.mem_no}">
											<input type="hidden" name="index" value="<%=i%>">
										</div>

									</form>
									<%
										}
										}
									%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	</div>

	<!--底部平台介紹==============================================================================-->
<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//圖片預覽  
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