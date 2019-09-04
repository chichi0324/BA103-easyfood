<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*" %>
<%
String mem_no = (String) session.getAttribute("mem_no");
MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<link rel="short Icon" type="images/x-icon" href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member_blog.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
<script src="<%=request.getContextPath()%>/easyfood/front-end/class/member/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath()%>/easyfood/front-end/class/member/ckfinder/ckfinder.js"></script>
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
					<li class="active"><a href="#">美食日記管理</a></li>
				</ol>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
			
                 <jsp:include page="leftGroup.jsp" flush="true" />
           
			</div>

			<div class="col-xs-12 col-sm-9">

				<%
					String default_tab = (String) request.getAttribute("default_tab");
				%>

				<div role="tabpanel">
					<!-- 標籤面板：標籤區 -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation"
							class="<%if (default_tab == null) {
				out.print("active");
			}%>"><a
							href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">我的美食日記</a></li>
						<li role="presentation"
							class="<%if ("add".equals(default_tab)) {
				out.print("active");
			}%>"><a
							href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">新增美食日記</a></li>
						<li role="presentation"
							class="<%if ("man".equals(default_tab)) {
				out.print("active");
			}%>"><a
							href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">文章管理</a></li>
						<li role="presentation"
							class="<%if ("res".equals(default_tab)) {
				out.print("active");
			}%>"><a
							href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">回應管理</a></li>
					</ul>
				</div>

				<%
					String display = (String) request.getAttribute("display");
				%>

				<!-- tab1  我的美食日記 -->
				<div class="tab-content">
					<div role="tabpanel"
						class="tab-pane <%if (default_tab == null) {out.print("active");}%>"
						id="tab1">
						<%if ("display".equals(display)) {%>
						<jsp:include page="member_blog_content.jsp" flush="true" />
						
						<%} else if ("update_display".equals(display)) {%>
						
						<jsp:include page="member_blog_update.jsp" flush="true" />
						
						<%} else if ("res_update_display".equals(display)) {%>
						<jsp:include page="member_blog_resUpdate.jsp" flush="true" />

						<%} else {%>
						<jsp:include page="member_blog_display.jsp" flush="true" />
						<%
							}
						%>
					</div>

					<!-- tab2  新增美食日記 -->
					<div role="tabpanel"
						class="tab-pane <%if ("add".equals(default_tab)) {
				out.print("active");
			}%>"
						id="tab2">

						<jsp:include page="member_blog_add.jsp" flush="true" />

					</div>

					<!-- tab3  文章管理 -->
					<div role="tabpanel"
						class="tab-pane <%if ("man".equals(default_tab)) {
				out.print("active");
			}%>"
						id="tab3">

						<jsp:include page="member_blog_man.jsp" flush="true" />

					</div>


					<!-- tab4  回應管理 -->
					<div role="tabpanel"
						class="tab-pane <%if ("res".equals(default_tab)) {
				out.print("active");
			}%>"
						id="tab4">
						<jsp:include page="member_blog_res.jsp" flush="true" />

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
</body>
</html>