<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blre.model.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.mem.model.*"%>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->


</head>
<body>

	<%
        MemVO memVO = (MemVO) session.getAttribute("memVO");
        String mem_no = memVO.getMem_no();

		BlreService blreSvc = new BlreService();
		List<BlreVO> list = blreSvc.findByMember(mem_no);
		pageContext.setAttribute("list", list);
		
		BlogService blogSvc = new BlogService();
	
		
	%>


<% if(list.size()==0){%>
<br>
<div style="color: red" class="text-center">目前沒有文章</div>
<% }else{ %>


	<!-- tab4  回應管理 -->

	<div class="article_res">
		<table class="table table-hover table-bordered  table-condensed">
			<thead>
				<tr style="background-color: #eee">
					<th class="col-sm-3">日期</th>
					<th>回應</th>
					<th>文章</th>
					<th>文章作者</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
			</thead>
			<tbody>
				<% for(int i=0;i<list.size();i++){ %>
					<tr>
						<td><%=tools.timestampToString(list.get(i).getBlre_date()) %></td>
						<td><a href="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do?action=blog_display_all&bl_no=<%=list.get(i).getBl_no() %>"  
						class="mag_title">
						<%=list.get(i).getBlre_con() %></a></td>
						<td><% BlogVO blogVO=blogSvc.findByPrimaryKey(list.get(i).getBl_no()); %>
						    <%=blogVO.getBl_name() %>
						</td>
						<td><%=tools.getMemName(blogVO.getMem_no()) %></td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blre.do">
								<input type="submit" value="修改"> <input type="hidden" name="blre_no"
									value="<%=list.get(i).getBlre_no() %>"> <input type="hidden"
									name="res_style" value="other_res"><input type="hidden"
									name="action" value="display_res_update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blre.do">
								<input type="submit" value="刪除"> <input type="hidden"
									name="blre_no" value="<%=list.get(i).getBlre_no() %>"> <input
									type="hidden" name="action" value="res_delete">
							</FORM>
						</td>
					</tr>
				<%} %>
			</tbody>
		</table>


	</div>


<% } %>




	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>