<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.tools.tools"%>

<%
   MemVO memVO = (MemVO) session.getAttribute("memVO");
   String mem_no = memVO.getMem_no();
   tools tool=new tools();
   pageContext.setAttribute("tools",tool);

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
	<!-- tab1  我的美食日記 -->
<%@ include file="page1.file" %> 
	<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
    <div class="article">
    ${blogVO.bl_con}
    </div>
    <div class="row">
      <div class="col-xs-12 col-sm-6">
        <div class="text-left more_res">
          <p class="res_color"><b>
                              回應(${blogSvc.findBLRECount(blogVO.bl_no).blre_count==null ? 0:blogSvc.findBLRECount(blogVO.bl_no).blre_count})
          </b></p>
        </div>
      </div>
      <div class="col-xs-12 col-sm-6">
        <div class="text-right more">
          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do">
			     <input type="submit" class="btn btn-default" role="button" value="更多...">
			     <input type="hidden" name="bl_no" value="${blogVO.bl_no}">
			     <input type="hidden" name="action"	value="display_complete"></FORM>
        </div>
      </div>
    </div>

  </div>
	
	</c:forEach>


<%@ include file="page2.file" %>


<% } %>



	