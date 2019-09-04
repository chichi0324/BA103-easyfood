<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.blre.model.*"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tools.tools"%>
<%
    MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mem_no = memVO.getMem_no();
	pageContext.setAttribute("mem_no",mem_no);
	
    tools tool=new tools();
    pageContext.setAttribute("tools",tool);

	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO");

	BlogService blogSvc = new BlogService();
	Set<BlreVO> set = blogSvc.getBLREsByBlog(blogVO.getBl_no());
	pageContext.setAttribute("set", set);
	
	pageContext.setAttribute("blogSvc",blogSvc);
	
    strService strSvc = new strService();
	pageContext.setAttribute("strSvc",strSvc);
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member_blog.css">


</head>
<body>

	<!--   新增成功後轉交 -->
	<div class="blog_article">
		<div class="row">
			<h3 class="blog_title">
				<b>${blogVO.bl_name}</b>
			</h3>
			<h4 class="blog_member">${tools.getMemName(blogVO.mem_no)}</h4>   
            <h4 class="blog_member">店家連結:<a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?str_no=${blogVO.str_no}&select=introduce&action=enter_store_select">
			${strSvc.getOneStr(blogVO.str_no).str_name}</a></h4>
			<h4 class="blog_time">${tools.formattras(blogVO.bl_date)}</h4>
		</div>
		<div><br>${blogVO.bl_con}</div>
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<div class="text-left more_res">
					<a href="#">
					  回應(${blogSvc.findBLRECount(blogVO.bl_no).blre_count==null ? 0:blogSvc.findBLRECount(blogVO.bl_no).blre_count})
					</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<div class="text-right more">
					<c:if test="${blogVO.mem_no==mem_no}">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do">
							<input type="submit" value="修改" class="btn btn-default"
								role="button"> <input type="hidden" name="bl_no"
								value="${blogVO.bl_no}"> <input type="hidden"
								name="action" value="display_update">
						</FORM>
					</c:if>
				</div>
			</div>
		</div>


		<c:if test="${not empty errorMsgsBlre}">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="messageBlre" items="${errorMsgsBlre}">
						<li>${messageBlre}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>


		<div style="margin: 20px; padding: 20px; background-color: #eee;">
			<form method="post" action="<%=request.getContextPath()%>/easyfood/front-end/class/member/blre.do">
				<div class="form-group">
					<label><%=(mem_no == null) ? "您尚未登入,將以訪客身分留言...." : tools.getMemName(mem_no)%></label>
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
				<div class="form-group">
					<textarea name="blre_con" class="form-control" placeholder="想說的話"></textarea>
				</div>
				<div class="text-right">
					<input type="hidden" name="action" value="leave_message"> <input
						type="hidden" name="bl_no" value="${blogVO.bl_no}"> <input
						type="submit" value="送出留言" class="btn btn-primary" role="button">
				</div>
			</form>
		</div>

		<c:forEach var="blreVO" items="${set}">
			<div style="border-style: ridge; margin: 20px; padding: 15px;">

				<div class="response">
					<div class="row">
						<div class="col-xs-12 col-sm-3"><img src="<%=request.getContextPath()%>/tools/Mem_Red_Img?mem_no=${blreVO.mem_no}" width=90% align="left">
						</div>
						<div class="col-xs-12 col-sm-9">
								<b  style="color: #005599">${tools.getMemName(blreVO.mem_no)}</b><br>
								${tools.formattras(blreVO.blre_date)}<br><br>
								 ${blreVO.blre_con}

						</div>
					</div>
				</div>
				<c:if test="${blreVO.mem_no==mem_no}">
					<div class="text-right">
						<div class="row">
							<div class="col-sm-offset-9 col-sm-1">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blre.do">
									<input type="submit" value="修改" class="btn btn-default"
										role="button"> <input type="hidden" name="blre_no"
										value="${blreVO.blre_no}"> <input type="hidden"
										name="action" value="display_res_update">
								</FORM>
							</div>
							<div class="col-sm-1">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blre.do">
									<input type="submit" value="刪除" class="btn btn-default"
										role="button"> <input type="hidden" name="blre_no"
										value="${blreVO.blre_no}"> <input type="hidden" name="bl_no"
										value="${blreVO.bl_no}"><input type="hidden"
									name="delete_style" value="delete_res_member"><input type="hidden"
										name="action" value="res_delete">
								</FORM>
							</div>
						</div>
					</div>
				</c:if>

			</div>
		</c:forEach>


	</div>

	<br>
	<br>


	<div class="text-center">
		<form method="post" action="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do">
			<input type="hidden" name="action" value="member_blog"> <input
				type='submit' value='回到我的美食日記'
				class="btn btn-success  btn login-button">
		</form>
	</div>





	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>