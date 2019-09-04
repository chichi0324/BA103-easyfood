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
<title>���b��K</title>
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

	<!--�����C===============================================================================-->
	<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />

	<!--�Ĥ@�C===================================================================================-->
	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<ol class="breadcrumb">
					<li><a
						href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=index">����</a></li>
					<li><a
						href="<%=request.getContextPath()%>/easyfood/front-end/class/member/member.jsp">�|������</a></li>
					<li class="active"><a href="#">�q�����</a></li>
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
						<h3 class="panel-title">�q���T</h3>
					</div>
					<ul class="list-group">
						<li class="list-group-item">�q�檬�A�G<%=ordVO.getOrd_stat()%><br>
							�q�榨�߮ɶ��G<%=tools.timeformat(ordVO.getOrd_date())%><br> �q��s���G<%=ordVO.getOrd_no()%><br>
							���a�W�١G<%=strVO.getStr_name()%><br> ���a�s���G<%=strVO.getStr_no()%>
						</li>
						<li class="list-group-item">���\�覡�G<%=ordVO.getOrd_type()%><br>
							���\�H�G<%=memVO.getMem_name()%><br> ������X�G<%=memVO.getMem_pho()%><br>
						</li>
					</ul>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">�ʶR����</h3>
					</div>

					<table class="table">
						<thead>
							<tr>
								<th>�ӫ~</th>
								<th>�ƶq</th>
								<th>�p�p</th>
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

							<td class="text-right" colspan="3"><%=k%>&nbsp;���ӫ~�X�p:&nbsp;&nbsp;&nbsp;&nbsp;$<%=total%><br>�B�O:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$0<br>
								<b>�`�p:</b>&nbsp;&nbsp;&nbsp;&nbsp;$<%=(int) Math.round(ordVO.getOrd_pri())%></td>
							</tr>
						</tbody>
					</table>
				</div>

				<%
					if (ordVO.getOrd_stat().equals("�w�I��")) {
				%>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">�q��ާ@</h3>
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
							href="<%=request.getContextPath()%>/easyfood/front-end/class/member/Rep.do?ord_no=<%=ordVO.getOrd_no()%>&action=getRep_For_display&str_name=<%=strVO.getStr_name()%>">�d�����|</a><br>
						<%
							}
								}
								if (NoRep) {
						%>
						<a
							href="<%=request.getContextPath()%>/easyfood/front-end/class/member/report.jsp?ord_no=<%=ordVO.getOrd_no()%>&str_name=<%=strVO.getStr_name()%>">�ڭn���|</a><br>
						<%
							}
						%>
						<%
							if (ordVO.getOrd_eva() == null) {
						%>
						<a
							href="<%=request.getContextPath()%>/easyfood/front-end/class/member/evaluate.jsp?ord_no=<%=ordVO.getOrd_no()%>&str_name=<%=strVO.getStr_name()%>">�ڭn����</a>
						<%
							} else {
						%>
						<a
							href="<%=request.getContextPath()%>/easyfood/front-end/class/member/Ord.do?ord_no=<%=ordVO.getOrd_no()%>&action=displayOneEv&str_name=<%=strVO.getStr_name()%>">�ק����</a>

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

	<!--�������x����==============================================================================-->
	<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>