<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.str.model.*" %>
<%
String str_rep=(String) request.getAttribute("str_rep");
String str_stat=(String) request.getAttribute("str_stat");

List<StrVO> strlist = (List<StrVO>) request.getAttribute("strList");
pageContext.setAttribute("strlist", strlist);
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
<%if(strlist!=null){ 
	if(strlist.size()==0){
%>
		<br>
		<div style="color: red" class="text-center">查無資料</div>
<%}else{%>


 <form action="<%=request.getContextPath()%>/easyfood/back-end/class/report/report.do" method="get">
	<table class="table table-striped table-hover"
		style='word-break: keep-all'>
		<thead>
			<tr class="success">
				<th>店名</th>
				<th>地址</th>
				<th>電話</th>
				<th>聯絡人</th>
				<th>被檢舉次數</th>
				<th>狀態</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="strVO" items="${strlist}" varStatus="s">
			<tr>
				<td><a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=introduce&str_no=${strVO.str_no}" target="_blank">${strVO.str_name}</a></td>
				<td>${strVO.str_cou}${strVO.str_city}${strVO.str_addr}</td>
				<td>${strVO.str_tel}</td>
				<td>${strVO.str_atn}</td>
				<td>${strVO.str_rep}</td>
				<td><select name="str_stat_${s.index}">
				        <option value="營業中" ${(strVO.str_stat=="營業中")? "selected" :""} >營業中</option>
						<option value="建置中" ${(strVO.str_stat=="建置中")? "selected" :""} >建置中</option>
                          <option value="暫停中"  ${(strVO.str_stat=="暫停中")? "selected" :""} >暫停中</option>
                          <option value="下架"  ${(strVO.str_stat=="下架")? "selected" :""} >下架</option>
				</select></td>
			</tr>
			<input type='hidden' value="${strVO.str_no}" name="str_no_${s.index}">
		</c:forEach>
		</tbody>
	</table>

	<div align="center">
		<input type='hidden' value="<%=strlist.size() %>" name="number">
		<input type='hidden' value="<%=str_rep %>" name="str_rep">
		<input type='hidden' value="<%=str_stat %>" name="str_stat">
		<input type='hidden' value="update_str_stas" name="action">
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