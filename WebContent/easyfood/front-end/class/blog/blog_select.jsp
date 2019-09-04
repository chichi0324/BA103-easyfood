<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.tools.tools"%>
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



<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
@import
	"<%=request.getContextPath()%>/easyfood/front-end/class/blog/css/blog.css"
	;
</style>

</head>
<body>


	<%
		BlogService blogSvc = new BlogService();
		List<BlogVO> list = blogSvc.getAll();
		pageContext.setAttribute("list", list);

		java.util.Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String thisMonth = cal.get(Calendar.MONTH + 1) + "";
		String thisYear = cal.get(Calendar.YEAR) + "";

		pageContext.setAttribute("thisMonth", thisMonth);
		pageContext.setAttribute("thisYear", thisYear);
	%>



	<div class="blog_time">
		<b>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do">
				<h4>
					<select name="year">
						<c:forEach var="year" begin="2014" end="2020">
							<option value="${year}" ${year==pageScope.thisYear ? 'selected' : ''}>${year}</option>
						</c:forEach>
					</select>年 <select name="month">
						<c:forEach var="month" begin="1" end="12">
							<option value="${month}" ${month==pageScope.thisMonth ? 'selected' : ''}>${month}</option>
						</c:forEach>
					</select>月
				</h4>


				<input type="submit" value="查詢"
					class="btn btn-danger  btn login-button"> <input
					type="hidden" name="action" value="select_diary">
			</FORM>
		</b>
	</div>

	<div class="text-left">
		<h2 class="text-center">
			<b>最新文章</b>
		</h2>
		<br>
		<c:forEach var="blogVO" items="${list}" end="9">
			<a
				href="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do?action=blog_display_all&bl_no=${blogVO.bl_no}"
				class="blog_left_title"><b>${blogVO.bl_name}</b></a><br>
		</c:forEach>
		<br>
		<a href="<%=request.getContextPath()%>/easyfood/front-end/class/blog/blog.jsp"
		class="blog_left_title">更多...</a>
	</div>

	<div class="many_button" align="center">
		<br> <a href="#" class="btn btn-default " role="button"><b>北部</b></a>
		<a href="#" class="btn btn-default " role="button"><b>中部</b></a><br>
		<br> <a href="#" class="btn btn-default " role="button"><b>南部</b></a>
		<a href="#" class="btn btn-default " role="button"><b>東部</b></a><br>
		<br> <a href="#" class="btn btn-default " role="button"><b>外島</b></a>
		<a href="#" class="btn btn-default " role="button"><b>其他</b></a><br>
		<br> <br> <a href="#" class="btn btn-default" role="button"><b>中式</b></a>
		<a href="#" class="btn btn-default" role="button"><b>西式</b></a><br>
		<br> <a href="#" class="btn btn-default" role="button"><b>法式</b></a>
		<a href="#" class="btn btn-default" role="button"><b>泰式</b></a><br>
		<br> <a href="#" class="btn btn-default" role="button"><b>義式</b></a>
		<a href="#" class="btn btn-default" role="button"><b>越式</b></a><br>
		<br>
	</div>

	<div class="text-center">
		<br> <a href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=member_blog"
			class="btn btn-success btn-lg" role="button">我的日記專區</a> <br>
		<br>
	</div>

	<div class="search">
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do">
			<div>
				<input class="form-control" type="text" name="keyWord" 
					value="" placeholder="輸入關鍵字">
			
			<br> <input type="submit" value="查詢"
				class="btn btn-danger  btn login-button"> <input
				type="hidden" name="action" value="select_diary_keyword"> </b>
				</div>
		</FORM>
	</div>










	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>