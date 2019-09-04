<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.tools.tools"%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">


		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->


  </head>
	<body>
	
	<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mem_no = null;
	if(memVO!=null){
		mem_no =memVO.getMem_no();
	}
	
	tools tool=new tools();
	pageContext.setAttribute("tools",tool);
	
	String keyWord = (String) request.getAttribute("keyWord");
	
	String year = (String) request.getAttribute("year");
	String month = (String) request.getAttribute("month");
	
	String select = (String) request.getAttribute("select");
	List<BlogVO> list=null;
	
    BlogService blogSvc = new BlogService();
    
    if("selectTime".equals(select)||"selectKeyWord".equals(select)){
    	list =(List<BlogVO>) request.getAttribute("blogList");
    }else{
        list = blogSvc.getAll();
    }
    
    pageContext.setAttribute("list",list);
    
    pageContext.setAttribute("blogSvc",blogSvc);
    
    strService strSvc = new strService();
	pageContext.setAttribute("strSvc",strSvc);
%>
	

<!--所有文章列表-->
				<c:if test="${not empty errorMsgs}">
					<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</font>
				</c:if>

      <div class="row">
        <div class="col-xs-12 col-sm-12">     
          <div class="newBlog_title">
            <img src="<%=request.getContextPath()%>/easyfood/front-end/class/blog/images/pencil.jpg" width=10% align="left">
              
              <%if("selectTime".equals(select)){ %> 
                          
           <h2 style="font-family:Microsoft JhengHei"><b> <%=year %>年<%=month %>月文章</b></h2>
           <%}else if("selectKeyWord".equals(select)){ %>
           
           <h2 style="font-family:Microsoft JhengHei"><b> 搜尋:　<%=keyWord %>　的結果為...</b></h2>           
           <%}else{ %>
           
           <h2 style="font-family:Microsoft JhengHei"><b> 最新文章</b></h2>
           
           <%} %>
          </div>
        <br>

<% if(list.size()==0){%>
<br>
<div style="color: red" class="text-center">目前沒有文章</div>
<% }else{ %>
<%@ include file="page1.file" %>
 <c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">       
 
        <div class="blog_article" >
            <div class="row">
              <h3 class="blog_title"><b>${blogVO.bl_name}</b></h3>
              <h4 class="blog_member">${tools.getMemName(blogVO.mem_no)}</h4>
              <h4 class="blog_member">店家連結:<a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?str_no=${blogVO.str_no}&select=introduce&action=enter_store_select">
			${strSvc.getOneStr(blogVO.str_no).str_name}</a></h4>
              <h4 class="blog_date_time">${tools.formattras(blogVO.bl_date)}</h4>
            </div>
            
<c:if test="${!blogSvc.getRAstasByBlog(blogVO.bl_no).contains(\"通過\")}">
            <div class="article">${blogVO.bl_con}</div>
            <div class="row">
              <div class="col-xs-12 col-sm-6">
                <div class="text-left more_res">
                  <p class="res_color" ><b>
                                                   回應(${blogSvc.findBLRECount(blogVO.bl_no).blre_count==null ? 0:blogSvc.findBLRECount(blogVO.bl_no).blre_count})
                  </b></p>   
                </div>
              </div>
              <div class="col-xs-12 col-sm-6">
                 <div class="text-right more">
                    <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do">
			        <input type="submit" class="btn btn-default" role="button" value="更多...">
			        <input type="hidden" name="bl_no" value="${blogVO.bl_no}">
			        <input type="hidden" name="action"	value="blog_display_all"></FORM>
                 </div>
              </div>                        
            </div>    
</c:if>
<c:if test="${blogSvc.getRAstasByBlog(blogVO.bl_no).contains(\"通過\")}">
           <div class="article">已被管理員移除</div>
</c:if> 
                          
        </div>

</c:forEach>


        </div>


<%@ include file="page2.file" %>

<% }%>

      </div>



   

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>