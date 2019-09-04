<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.blre.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tools.tools"%>
<%

tools tool=new tools();
pageContext.setAttribute("tools",tool);

MemVO memVO = (MemVO) session.getAttribute("memVO");
String mem_no = memVO.getMem_no();
	BlreVO blreVO = (BlreVO) request.getAttribute("blreVO");
	
	String display = (String) request.getAttribute("display");
	
	BlogService blogSvc = new BlogService();
	pageContext.setAttribute("blogSvc",blogSvc);
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

</head>
<body>

	<!--   修改留言頁面 -->



		<c:if test="${not empty errorMsgsRes}">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="messageRes" items="${errorMsgsRes}">
						<li>${messageRes}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>


		<div style="margin: 20px; padding: 20px; background-color: #eee;">
			<form method="post" action="<%=request.getContextPath()%>/easyfood/front-end/class/member/blre.do">
				<div class="form-group">
					<label><%=(mem_no == null) ? "您尚未登入,將以訪客身分留言...." : tools.getMemName(mem_no) %></label>
					<%
						if (mem_no == null) {
					%>
					<input type="text" name="
nickname" class="form-control"
						placeholder="您的暱稱">
					<%
						}
					%>
				</div>
				<div>${blogSvc.findByPrimaryKey(blreVO.bl_no).bl_name}</div>				
				<div>${tools.formattras(blreVO.blre_date)}</div>
				<div class="form-group">
					<textarea name="blre_con" class="form-control" placeholder="想說的話"><%=blreVO.getBlre_con() %></textarea>
				</div>
				<%if("blog_display_update".equals(display)){ %>
				<div class="text-right">
					<input type="hidden" name="action" value="blog_respond_Update">
					<input type="hidden" name="blre_no" value="${blreVO.blre_no}"> 
					<input type="hidden" name="bl_no" value="${blreVO.bl_no}"> <input
						type="submit" value="修改" class="btn btn-primary" role="button">
				</div>
				<%}else{ %>
				<div class="text-right">
					<input type="hidden" name="action" value="respond_Update">
					<input type="hidden" name="blre_no" value="${blreVO.blre_no}"> 
					<input type="hidden" name="bl_no" value="${blreVO.bl_no}"> <input
						type="submit" value="修改" class="btn btn-primary" role="button">
				</div>
				<%} %>
			</form>
		</div>




	<br>
	<br>





	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>