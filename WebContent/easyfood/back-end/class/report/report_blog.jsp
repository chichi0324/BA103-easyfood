<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ra.model.*" %>
<%@ page import="com.blog.model.*" %>
<%@ page import="com.tools.*" %>
<%
String ra_rev=(String) request.getAttribute("ra_rev");

tools tool=new tools();
pageContext.setAttribute("tools",tool);

BlogService blogSvc=new BlogService();
pageContext.setAttribute("blogSvc", blogSvc);

List<RaVO> ralist = (List<RaVO>) request.getAttribute("raList");
pageContext.setAttribute("ralist", ralist);
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
<%if(ralist!=null){ 
	if(ralist.size()==0){
%>
		<br>
		<div style="color: red" class="text-center">查無資料</div>
<%}else{%>


 <form action="<%=request.getContextPath()%>/easyfood/back-end/class/report/report.do" method="get">
	<table class="table table-striped table-hover"
		style='word-break: keep-all'>
		<thead>
			<tr class="success">
				<th>文章名稱</th>
				<th>檢舉內容</th>
				<th>文章作者</th>
				<th>狀態</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="raVO" items="${ralist}" varStatus="s">
			<tr>
				<td><a href="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do?action=blog_display_all&bl_no=${raVO.bl_no}" target="_blank">${blogSvc.findByPrimaryKey(raVO.bl_no).bl_name}</a></td>
				<td>${raVO.ra_res}</td>
				<td>${tools.getMemName(blogSvc.findByPrimaryKey(raVO.bl_no).mem_no)}</td>
				<td><select name="ra_rev_${s.index}">
						<option value="待審核" ${(raVO.ra_rev=="待審核")? "selected" :""} >待審核</option>
                          <option value="通過" ${(raVO.ra_rev=="通過")? "selected" :""} >通過</option>
                          <option value="不通過"  ${(raVO.ra_rev=="不通過")? "selected" :""} >不通過</option>
				</select></td>
			</tr>
			<input type='hidden' value="${raVO.ra_no}" name="ra_no_${s.index}">
		</c:forEach>
		</tbody>
	</table>

	<div align="center">
		<input type='hidden' value="<%=ralist.size() %>" name="number">
		<input type='hidden' value="<%=ra_rev %>" name="ra_rev">
		<input type='hidden' value="update_blog_stas" name="action">
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