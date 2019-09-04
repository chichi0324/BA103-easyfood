<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="com.tools.*,com.mem.model.*, com.ord.model.*, com.ordit.model.*, com.store.model.*, com.dish.model.* ,com.rep.model.* , java.util.*"%>
<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
	String Str = (String) session.getAttribute("showImgByBase64");
	String showImgByBase64 = "data:image/jpg;base64," + Str;
	OrdVO ordVO = (OrdVO) request.getAttribute("ordVO");
	StrVO strVO = (StrVO) request.getAttribute("strVO");
	List<OrditVO> aOrditVOlist = (List<OrditVO>) request.getAttribute("aOrditlist");
	List<DishVO> aDishVOlist = (List<DishVO>) request.getAttribute("aDishlist");
	RepService repSvc = new RepService();
	List<RepVO> replist = repSvc.getAll();
	OrdService ordSvc = new OrdService();
	List<OrdVO> ordlist = ordSvc.getAll();
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
					<li class="active"><a href="#">訂單明細</a></li>
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

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">訂單資訊</h3>
					</div>
					<ul class="list-group">
						<li class="list-group-item">訂單狀態：<%=ordVO.getOrd_stat()%><br>
							訂單成立時間：<%=tools.timeformat(ordVO.getOrd_date())%><br> 訂單編號：<%=ordVO.getOrd_no()%><br>
							店家名稱：<%=strVO.getStr_name()%><br> 店家編號：<%=strVO.getStr_no()%>
						</li>
						<li class="list-group-item">取餐方式：<%=ordVO.getOrd_type()%><br>
							取餐人：<%=memVO.getMem_name()%><br> 手機號碼：<%=memVO.getMem_pho()%><br>
						</li>
					</ul>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">購買明細</h3>
					</div>

					<table class="table">
						<thead>
							<tr>
								<th>商品</th>
								<th>數量</th>
								<th>小計</th>
							</tr>
						</thead>
						<tbody>
							<%
								int k = 0, total = 0;

								for (int i = 0; i < aOrditVOlist.size(); i++) {
									OrditVO aOrditVO = aOrditVOlist.get(i);
									k++;
									for (int j = 0; j < aDishVOlist.size(); j++) {
										DishVO aDishVO = aDishVOlist.get(j);
										if (aDishVO.getDish_no().equals(aOrditVO.getDish_no())) {
							%>
							<tr>
								<td><%=aDishVO.getDish_name()%></td>
								<td><%=aOrditVO.getOrdit_qua()%></td>
								<td><%=(int) Math.round(aOrditVO.getOrdit_pri() * aOrditVO.getOrdit_qua())%></td>
							</tr>
							<%
								total += aOrditVO.getOrdit_pri() * aOrditVO.getOrdit_qua();
										}
									}
								}
							%>

							<td class="text-right" colspan="3"><%=k%>&nbsp;項商品合計:&nbsp;&nbsp;&nbsp;&nbsp;$<%=total%><br>運費:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$0<br>
								<b>總計:</b>&nbsp;&nbsp;&nbsp;&nbsp;$<%=(int) Math.round(ordVO.getOrd_pri())%></td>
							</tr>
						</tbody>
					</table>
				</div>

				<%
					if (ordVO.getOrd_stat().equals("已付款")) {
				%>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">訂單操作</h3>
					</div>
					<div class="panel-body">
						<%
							boolean NoRep = true;
								for (int i = 0; i < replist.size(); i++) {
									RepVO arepVO = replist.get(i);
									if (arepVO.getOrd_no().equals(ordVO.getOrd_no())) {
										NoRep = false;
						%>
						<a
							href="<%=request.getContextPath()%>/easyfood/front-end/class/member/Rep.do?ord_no=<%=ordVO.getOrd_no()%>&action=getRep_For_display&str_name=<%=strVO.getStr_name()%>">查看檢舉</a><br>
						<%
							}
								}
								if (NoRep) {
						%>
						<a
							href="<%=request.getContextPath()%>/easyfood/front-end/class/member/report.jsp?ord_no=<%=ordVO.getOrd_no()%>&str_name=<%=strVO.getStr_name()%>">我要檢舉</a><br>
						<%
							}
						%>
						<%
							if (ordVO.getOrd_eva() == null) {
						%>
						<a
							href="<%=request.getContextPath()%>/easyfood/front-end/class/member/evaluate.jsp?ord_no=<%=ordVO.getOrd_no()%>&str_name=<%=strVO.getStr_name()%>">我要評價</a>
						<%
							} else {
						%>
						<a
							href="<%=request.getContextPath()%>/easyfood/front-end/class/member/Ord.do?ord_no=<%=ordVO.getOrd_no()%>&action=displayOneEv&str_name=<%=strVO.getStr_name()%>">修改評價</a>

						<%
							}
						%>
					</div>
				</div>
				<%
					}
				%>

			</div>
		</div>
	</div>

	<!--底部平台介紹==============================================================================-->
	<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>