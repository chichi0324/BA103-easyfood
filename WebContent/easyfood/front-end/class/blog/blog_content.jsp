<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.ra.model.*"%>
<%@ page import="com.blre.model.*"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tools.*" %>
<%
MemVO memVO = (MemVO) session.getAttribute("memVO");
String mem_no = null;
if(memVO!=null){
	mem_no =memVO.getMem_no();
}
pageContext.setAttribute("mem_no",mem_no);
    
    
    tools tool=new tools();
    pageContext.setAttribute("tools",tool);

	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO");

	strService strSvc = new strService();
	pageContext.setAttribute("strSvc", strSvc);
	
	BlogService blogSvc = new BlogService();
	Set<BlreVO> set = blogSvc.getBLREsByBlog(blogVO.getBl_no());
	pageContext.setAttribute("set", set);

	pageContext.setAttribute("blogSvc", blogSvc);

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
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/blog/css/blog_content.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/blog/css/blog.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->


</head>
<body>



									<c:if test="${not empty errorMsgsRebl}">
										<font color='red'>請修正以下錯誤:
											<ul>
												<c:forEach var="messageBlre" items="${errorMsgsRebl}">
													<li>${errorMsgsRebl}</li>
												</c:forEach>
											</ul>
										</font>
									</c:if>

	<!--   新增成功後轉交 -->
	<div class="blog_article">
		<div class="row">
			<h3 class="blog_title">
				<b>${blogVO.bl_name}</b>
			</h3>
			<h4 class="blog_member">${tools.getMemName(blogVO.mem_no)}</h4>
			<h4 class="blog_member">
				店家連結:<a
					href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?str_no=${blogVO.str_no}&select=introduce&action=enter_store_select">
					${strSvc.getOneStr(blogVO.str_no).str_name}</a>
			</h4>
			<h4 class="blog_date_time">${tools.formattras(blogVO.bl_date)}</h4>
		</div>
	<c:if test="${!blogSvc.getRAstasByBlog(blogVO.bl_no).contains(\"通過\")}">
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
				<c:if test="${blogVO.mem_no==mem_no}">
					<div class="text-right more">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do">
							<input type="submit" value="修改" class="btn btn-default"
								role="button"> <input type="hidden" name="bl_no"
								value="${blogVO.bl_no}"> <input type="hidden"
								name="action" value="display_update">
						</FORM>
					</div>
				</c:if>
				<c:if test="${blogVO.mem_no!=mem_no}">
					<div class="text-right more">
						<a href='#modal-id' data-toggle="modal" class="btn btn-warning"
							role="button">檢舉</a>
					</div>
					<!-- modal檢舉視窗 =======================================-->
					<div class="modal fade" id="modal-id">
						<div class="modal-dialog">
							<div class="modal-content">

								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">檢舉文章</h4>

								</div>

								<div class="rep_art" style="padding: 20px">

									<form method="post" action="<%=request.getContextPath()%>/easyfood/front-end/class/blog/repblog.do">
										<div class="form-group">
											<label><%=(mem_no == null) ? "您尚未登入,請登入後再檢舉...." : tools.getMemName(mem_no)%></label>
										</div>
										<%
											if (mem_no != null) {
										%>
										<div class="form-group ">
											<textarea name="ra_res" class="form-control"
												placeholder="檢舉原因..."></textarea>
										</div>
										<div class="text-right">
											<input type="hidden" name="action" value="insert_report">
											<input type="hidden" name="bl_no" value="${blogVO.bl_no}">
											<div class="modal-footer">
												<input type="submit" value="送出檢舉" class="btn btn-danger"
													role="button" >
											</div>
										</div>
										<%
											}
										%>
									</form>
								</div>
							
							</div>
						</div>
					</div>

				</c:if>
				<!-- modal以上檢舉視窗 =======================================-->
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
					<label><%=(mem_no == null) ? "您尚未登入,請登入後再留言...." : tools.getMemName(mem_no)%></label>					
				</div>
				<% if (mem_no != null) { %>
				<div class="form-group">
					<textarea name="blre_con" class="form-control" placeholder="想說的話"></textarea>
				</div>
				<div class="text-right">
					<input type="hidden" name="action" value="blog_leave_message">
					<input type="hidden" name="bl_no" value="${blogVO.bl_no}">
					<input type="submit" value="送出留言" class="btn btn-primary"
						role="button">
				</div>
				<%}%>
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
										name="action" value="blog_display_res_update">
								</FORM>
							</div>
							<div class="col-sm-1">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blre.do">
									<input type="submit" value="刪除" class="btn btn-default"
										role="button"> <input type="hidden" name="blre_no"
										value="${blreVO.blre_no}"> <input type="hidden"
										name="bl_no" value="${blreVO.bl_no}"><input
										type="hidden" name="delete_style" value="delete_res_blog"><input
										type="hidden" name="action" value="res_delete">
								</FORM>
							</div>
						</div>
					</div>
				</c:if>

			</div>
		</c:forEach>

 </c:if>
 <c:if test="${blogSvc.getRAstasByBlog(blogVO.bl_no).contains(\"通過\")}">
 <div class="article">已被管理員移除</div>
 </c:if>
	</div>

	<br>
	<br>


	<div class="text-center">
		<a
			href="<%=request.getContextPath()%>/easyfood/front-end/class/blog/blog.jsp"
			class="btn btn-default  btn login-button">回到美食日記</a>
	</div>





	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>