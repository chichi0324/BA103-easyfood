<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="com.mem.model.*, com.ord.model.*, com.str.model.*, java.util.*,com.tools.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String Str = (String) session.getAttribute("showImgByBase64");
	String showImgByBase64 = "data:image/jpg;base64," + Str;
	OrdService ordSvc = new OrdService();
	List<OrdVO> list = ordSvc.getAll();
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
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<link rel="short Icon" type="images/x-icon"
	href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
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
					<li><a
						href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=index">首頁</a></li>
					<li><a
						href="<%=request.getContextPath()%>/easyfood/front-end/class/member/member.jsp">會員中心</a></li>
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
				<div class="dropdown">
					<button class="btn dropdown-toggle btn-danger" type="button"
						data-toggle="dropdown">
						全部訂單 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="#">最近一個月訂單</a></li>
						<li><a href="#">最近三個月訂單</a></li>
						<li><a href="#">最近半年訂單</a></li>
						<li><a href="#">全部訂單訂單</a></li>
					</ul>
				</div>
				<!-- <select class="form-control" id="sel1">
			  <option value="最近一個月">最近一個月訂單</option>
			  <option value="最近三個月">最近三個月訂單</option>
			  <option value="最近半年">最近半年訂單</option>
			  <option value="最近一年">最近一年訂單</option>
			  <option value="全部訂單">全部訂單訂單</option>
			</select> -->
				<ul class="nav nav-tabs" style="margin-top: 20px">
					<li class="active"><a data-toggle="tab" href="#home">未確認訂單</a></li>
					<li><a data-toggle="tab" href="#menu1">未出貨訂單</a></li>
					<li><a data-toggle="tab" href="#menu2">已出貨訂單</a></li>
					<li><a data-toggle="tab" href="#menu3">已結案訂單</a></li>
					<li><a data-toggle="tab" href="#menu4">已取消訂單</a></li>
				</ul>
				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">
						<table class="table table-hover order">
							<thead>
								<tr>
									<th>店家</th>
									<th>訂單編號</th>
									<th>訂單成立時間</th>
									<th>總金額</th>
								</tr>
							</thead>
							<tbody>
								<%
									boolean NoOrd = true;
									if (list != null && (list.size() > 0)) {
										for (int i = (list.size() - 1); i >= 0; i--) {
											OrdVO aOrd = list.get(i);
											String str_no = aOrd.getStr_no();
											strService strSvc = new strService();
											StrVO strVO = strSvc.getOneStr(str_no);
											if (aOrd.getOrd_stat().equals("未確認")) {
												if (memVO.getMem_no().equals(aOrd.getMem_no())) {
													NoOrd = false;
								%>

								<tr
									onclick="window.document.location='<%=request.getContextPath()%>/easyfood/front-end/class/member/Ord.do?ord_no=<%=aOrd.getOrd_no()%>&action=getOneOrd_For_Display';">
									<td><%=strVO.getStr_name()%></td>
									<td><%=aOrd.getOrd_no()%></td>
									<td><%=tools.timeformat(aOrd.getOrd_date())%></td>
									<td>$<%=(int) Math.round(aOrd.getOrd_pri())%></td>
								</tr>
								<%
									}
											}
										}
									}
									if (NoOrd) {
								%>
								<tr>
									<td colspan="4" class="text-center">目前尚無訂單</td>
								</tr>

								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<div id="menu1" class="tab-pane fade">
						<table class="table table-hover order">
							<thead>
								<tr>
									<th>店家</th>
									<th>訂單編號</th>
									<th>訂單成立時間</th>
									<th>總金額</th>
								</tr>
							</thead>
							<tbody>
								<%
									boolean NoOrd1 = true;
									if (list != null && (list.size() > 0)) {
										for (int i = (list.size() - 1); i >= 0; i--) {
											OrdVO aOrd = list.get(i);
											String str_no = aOrd.getStr_no();
											strService strSvc = new strService();
											StrVO strVO = strSvc.getOneStr(str_no);
											if (aOrd.getOrd_stat().equals("已確認")) {
												if (memVO.getMem_no().equals(aOrd.getMem_no())) {
													NoOrd1 = false;
								%>

								<tr
									onclick="window.document.location='<%=request.getContextPath()%>/easyfood/front-end/class/member/Ord.do?ord_no=<%=aOrd.getOrd_no()%>&action=getOneOrd_For_Display';">
									<td><%=strVO.getStr_name()%></td>
									<td><%=aOrd.getOrd_no()%></td>
									<td><%=tools.timeformat(aOrd.getOrd_date())%></td>
									<td>$<%=aOrd.getOrd_pri()%></td>
								</tr>
								<%
									}
											}
										}
									}
									if (NoOrd1) {
								%>
								<tr>
									<td colspan="4" class="text-center">目前尚無訂單</td>
								</tr>

								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<div id="menu2" class="tab-pane fade">
						<table class="table table-hover order">
							<thead>
								<tr>
									<th>店家</th>
									<th>訂單編號</th>
									<th>訂單成立時間</th>
									<th>總金額</th>
								</tr>
							</thead>
							<tbody>
								<%
									boolean NoOrd2 = true;
									if (list != null && (list.size() > 0)) {
										for (int i = (list.size() - 1); i >= 0; i--) {
											OrdVO aOrd = list.get(i);
											String str_no = aOrd.getStr_no();
											strService strSvc = new strService();
											StrVO strVO = strSvc.getOneStr(str_no);
											if (aOrd.getOrd_stat().equals("已出貨")) {
												if (memVO.getMem_no().equals(aOrd.getMem_no())) {
													NoOrd2 = false;
								%>

								<tr
									onclick="window.document.location='<%=request.getContextPath()%>/easyfood/front-end/class/member/Ord.do?ord_no=<%=aOrd.getOrd_no()%>&action=getOneOrd_For_Display';">
									<td><%=strVO.getStr_name()%></td>
									<td><%=aOrd.getOrd_no()%></td>
									<td><%=tools.timeformat(aOrd.getOrd_date())%></td>
									<td>$<%=aOrd.getOrd_pri()%></td>
								</tr>
								<%
									}
											}
										}
									}
									if (NoOrd2) {
								%>
								<tr>
									<td colspan="4" class="text-center">目前尚無訂單</td>
								</tr>

								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<div id="menu3" class="tab-pane fade">
						<table class="table table-hover order">
							<thead>
								<tr>
									<th>店家</th>
									<th>訂單編號</th>
									<th>訂單成立時間</th>
									<th>總金額</th>
								</tr>
							</thead>
							<tbody>
								<%
									boolean NoOrd3 = true;
									if (list != null && (list.size() > 0)) {
										for (int i = (list.size() - 1); i >= 0; i--) {
											OrdVO aOrd = list.get(i);
											String str_no = aOrd.getStr_no();
											strService strSvc = new strService();
											StrVO strVO = strSvc.getOneStr(str_no);
											if (aOrd.getOrd_stat().equals("已付款") || aOrd.getOrd_stat().equals("未取餐")) {
												if (memVO.getMem_no().equals(aOrd.getMem_no())) {
													NoOrd3 = false;
								%>

								<tr
									onclick="window.document.location='<%=request.getContextPath()%>/easyfood/front-end/class/member/Ord.do?ord_no=<%=aOrd.getOrd_no()%>&action=getOneOrd_For_Display';">
									<td><%=strVO.getStr_name()%></td>
									<td><%=aOrd.getOrd_no()%></td>
									<td><%=tools.timeformat(aOrd.getOrd_date())%></td>
									<td>$<%=aOrd.getOrd_pri()%></td>
								</tr>
								<%
									}
											}
										}
									}
									if (NoOrd3) {
								%>
								<tr>
									<td colspan="4" class="text-center">目前尚無訂單</td>
								</tr>

								<%
									}
								%>
							</tbody>
						</table>
					</div>

					<div id="menu4" class="tab-pane fade">
						<table class="table table-hover order">
							<thead>
								<tr>
									<th>店家</th>
									<th>訂單編號</th>
									<th>訂單成立時間</th>
									<th>總金額</th>
								</tr>
							</thead>
							<tbody>
								<%
									boolean NoOrd4 = true;
									if (list != null && (list.size() > 0)) {
										for (int i = (list.size() - 1); i >= 0; i--) {
											OrdVO aOrd = list.get(i);
											String str_no = aOrd.getStr_no();
											strService strSvc = new strService();
											StrVO strVO = strSvc.getOneStr(str_no);
											if (aOrd.getOrd_stat().equals("已取消")) {
												if (memVO.getMem_no().equals(aOrd.getMem_no())) {
													NoOrd4 = false;
								%>

								<tr
									onclick="window.document.location='<%=request.getContextPath()%>/easyfood/front-end/class/member/Ord.do?ord_no=<%=aOrd.getOrd_no()%>&action=getOneOrd_For_Display';">
									<td><%=strVO.getStr_name()%></td>
									<td><%=aOrd.getOrd_no()%></td>
									<td><%=tools.timeformat(aOrd.getOrd_date())%></td>
									<td>$<%=aOrd.getOrd_pri()%></td>
								</tr>
								<%
									}
											}
										}
									}
									if (NoOrd4) {
								%>
								<tr>
									<td colspan="4" class="text-center">目前尚無取消訂單</td>
								</tr>

								<%
									}
								%>
							</tbody>
						</table>
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
	<script language="JavaScript"
		src="<%=request.getContextPath()%>/easyfood/front-end/class/member/js/member.js"></script>
</body>
</html>