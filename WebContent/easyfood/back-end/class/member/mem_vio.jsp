<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%
String mem_vio=(String) request.getAttribute("mem_vio");
String mem_stas=(String) request.getAttribute("mem_stas");

List<MemVO> memlist = (List<MemVO>) request.getAttribute("memList");
pageContext.setAttribute("memlist", memlist);
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
<%if(memlist!=null){ 
	if(memlist.size()==0){
%>
		<br>
		<div style="color: red" class="text-center">查無資料</div>
<%}else{%>


 <form action="<%=request.getContextPath()%>/easyfood/back-end/class/member/memVio.do" method="get">
	<table class="table table-striped table-hover"
		style='word-break: keep-all'>
		<thead>
			<tr class="success">
				<th>帳號</th>
				<th>姓名</th>
				<th>電話</th>
				<th>信箱</th>
				<th>違規次數</th>
				<th>狀態</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="memVO" items="${memlist}" varStatus="s">
			<tr>
				<td>${memVO.mem_acc}</td>
				<td>${memVO.mem_name}</td>
				<td>${memVO.mem_pho}</td>
				<td>${memVO.mem_mail}</td>
				<td>${memVO.mem_vio}</td>
				<td><select name="mem_stas_${s.index}">
						<option value="正常" ${(memVO.mem_stas=="正常")? "selected" :""}>正常</option>
						<option value="停權" ${(memVO.mem_stas=="停權")? "selected" :""}>停權</option>
				</select></td>
			</tr>
			<input type='hidden' value="${memVO.mem_no}" name="mem_no_${s.index}">
		</c:forEach>
		</tbody>
	</table>

	<div align="center">
		<input type='hidden' value="<%=memlist.size() %>" name="number">
		<input type='hidden' value="<%=mem_vio %>" name="mem_vio">
		<input type='hidden' value="<%=mem_stas %>" name="mem_stas">
		<input type='hidden' value="update_mem_stas" name="action">
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