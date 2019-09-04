<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ra.model.*" %>
<%@ page import="com.str.model.*" %>
<%@ page import="com.tools.*" %>
<%
String rep_rev=(String) request.getAttribute("rep_rev");

tools tool=new tools();
pageContext.setAttribute("tools",tool);

strService strSvc=new strService();
pageContext.setAttribute("strSvc", strSvc);

List<RaVO> replist = (List<RaVO>) request.getAttribute("replist");
pageContext.setAttribute("replist", replist);
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便管理系統</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/back-end/css/base.css">
<!--[if lt IE 9]>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<%if(replist!=null){ 
	if(replist.size()==0){
%>
		<br>
		<div style="color: red" class="text-center">查無資料</div>
<%}else{%>


 <form action="<%=request.getContextPath()%>/easyfood/back-end/class/report/report.do" method="get">
	<table class="table table-striped table-hover"
		style='word-break: keep-all'>
		<thead>
			<tr class="success">
				<th>店家名稱</th>
				<th>檢舉內容</th>
				<th>檢舉會員</th>
				<th>狀態</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="repVO" items="${replist}" varStatus="s">
			<tr>
				<td><a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=introduce&str_no=${repVO.str_no}" target="_blank">
				${strSvc.getOneStr(repVO.str_no).str_name}</a></td>
				<td>${repVO.rep_res}</td>
				<td>${tools.getMemName(repVO.mem_no)}</td>
				<td><select name="rep_rev_${s.index}">
						<option value="待審核" ${(repVO.rep_rev=="待審核")? "selected" :""} >待審核</option>
                          <option value="通過" ${(repVO.rep_rev=="通過")? "selected" :""} >通過</option>
                          <option value="不通過"  ${(repVO.rep_rev=="不通過")? "selected" :""} >不通過</option>
				</select></td>
			</tr>
			<input type='hidden' value="${repVO.rep_no}" name="rep_no_${s.index}">
		</c:forEach>
		</tbody>
	</table>

	<div align="center">
		<input type='hidden' value="<%=replist.size() %>" name="number">
		<input type='hidden' value="<%=rep_rev %>" name="rep_rev">
		<input type='hidden' value="update_str_rep" name="action">
		<input type='submit' value='修改' class="btn btn-default">
	</div>
</form>


<%
}
	}%>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>