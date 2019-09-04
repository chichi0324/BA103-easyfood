<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.rep.model.*,com.mem.model.*"%>
<%
	request.setCharacterEncoding("UTF-8");
MemVO memVO = (MemVO) session.getAttribute("memVO");
String Str = (String) session.getAttribute("showImgByBase64");
String showImgByBase64 = "data:image/jpg;base64," + Str;
	String ord_no = request.getParameter("ord_no");
	// 	String str_name1 = request.getParameter("str_name");
	// 	String str_name = new String(str_name1.getBytes("ISO-8859-1"), "UTF-8");
	// 	System.out.print(str_name);
	RepVO repVO = (RepVO) request.getAttribute("repVO");
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
<link rel="short Icon" type="images/x-icon" href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
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
						<h3 class="panel-title">
							<b>���|�q��</b>
						</h3>
					</div>
					<ul class="list-group">
						<li class="list-group-item">
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/Rep.do">
								<div class="form-group">
									<div>
										�q��s��:<%=ord_no%>
										<%-- 										<br> ���a�W��:<%=str_name%><br> <br> --%>
									</div>
									<div>
										<%-- ���~��C --%>
										<c:if test="${not empty errorMsgs}">
											<font color='red'>�T��:
												<ul>
													<c:forEach var="message" items="${errorMsgs}">
														<li>${message}</li>
													</c:forEach>
												</ul>
											</font>
										</c:if>
									</div>
									<label for="comment">���|���e�G</label>
									<textarea class="form-control" name="rep_res" rows="5"
										id="comment" <%=repVO != null ? "disabled" : ""%>><%
											if (repVO != null) {
										%><%=repVO.getRep_res()%><%
											}
										%></textarea>
								</div>
								<div class="text-right">
									<button type="submit" class="btn btn-success"
										data-toggle="modal" data-target="#myModal"
										<%=repVO != null ? "disabled" : ""%>>�e�X���|</button>
									<input type="hidden" name="ord_no" value="<%=ord_no%>">
									<input type="hidden" name="action" value="addRep">
									</form>
									<!-- Modal -->
									<div class="modal fade" id="myModal" role="dialog">
										<div class="modal-dialog modal-sm">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">Modal Header</h4>
												</div>
												<div class="modal-body">
													<p>This is a small modal.</p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Close</button>
												</div>
											</div>
										</div>
									</div>
								</div>
				</div>
				
				</li>
				</ul>
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