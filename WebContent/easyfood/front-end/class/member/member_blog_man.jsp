<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.tools.tools"%>

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->


	</head>
	<body>

<%

    tools tool=new tools();
    pageContext.setAttribute("tools",tool);

    MemVO memVO = (MemVO) session.getAttribute("memVO");
    String mem_no = memVO.getMem_no();

    BlogService blogSvc = new BlogService();
    List<BlogVO> list = blogSvc.findByMember(mem_no);
    pageContext.setAttribute("list",list);
    
	pageContext.setAttribute("blogSvc",blogSvc);
	
    strService strSvc = new strService();
	pageContext.setAttribute("strSvc",strSvc);
%>

<% if(list.size()==0){%>
<br>
<div style="color: red" class="text-center">目前沒有文章</div>
<% }else{ %>

        <!-- tab3  文章管理 -->
      
      <div class="article_mag">
         <table class="table table-hover table-bordered table-condensed">
          <thead >
            <tr style="background-color: #eee">
              <th class="col-sm-3">日期</th>
              <th>文章</th>
              <th>店家連結</th>
              <th>備註</th>
              <th>修改</th>
              <th>刪除</th>
            </tr>
          </thead>
          <tbody>
   <c:forEach var="blogVO" items="${list}" >       
            <tr>
              <td>${tools.formattras(blogVO.bl_date)}</td>
              <td>
                <a href="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do?action=display_complete&bl_no=${blogVO.bl_no}" 
                class="mag_title">
                ${blogVO.bl_name}</a>
              </td>
              <td><a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?str_no=${blogVO.str_no}&select=introduce&action=enter_store_select">
			${strSvc.getOneStr(blogVO.str_no).str_name}</a></td>
              <td>
                <div>
                  <a href="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do?action=display_complete&bl_no=${blogVO.bl_no}" >
                                                  回應(${blogSvc.findBLRECount(blogVO.bl_no).blre_count==null ? 0:blogSvc.findBLRECount(blogVO.bl_no).blre_count})
                  </a>
                </div>
              </td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="bl_no" value="${blogVO.bl_no}">
			     <input type="hidden" name="action"	value="display_update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="bl_no" value="${blogVO.bl_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
            </tr>
   </c:forEach>         
          </tbody>
        </table>



        </div>

<% } %>

		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

	</body>
</html>