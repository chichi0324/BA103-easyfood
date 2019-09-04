<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.dishclass.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tools.tools"%>
<%
	String enter_display = (String) request.getAttribute("enter_display");
    StrVO strVO=(StrVO) request.getAttribute("strVO");
    
    DclaService dclaSvc = new DclaService();
    List<DclaVO> dclaList = dclaSvc.getStr_DishClass(strVO.getStr_no());
    pageContext.setAttribute("dclaList",dclaList);
    
    pageContext.setAttribute("strVO",strVO);
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/search/css/search_store.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>





	<div class="container">
		<!--第二列===================================================================-->

		<div class="row">
			<div class="col-xs-12 col-sm-3 " id="search_store_list">

				<ul class="nav nav-pills nav-stacked">
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=introduce&str_no=${strVO.str_no}"
					 class="search_store_left" 
					 style='<%=(enter_display==null) ? "background-color: orange" :"" %>' ><b>店家介紹</b></a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=blog&str_no=${strVO.str_no}"
					 class="search_store_left" 
					 style='<%=("enter_store_blog".equals(enter_display)) ? "background-color: orange" :"" %>'><b>美食日記分享</b></a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=comment&str_no=${strVO.str_no}"
					 class="search_store_left" 
					 style='<%=("enter_store_comment".equals(enter_display)) ? "background-color: orange" :"" %>'><b>店家評價</b></a></li>
					<li><a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=online&str_no=${strVO.str_no}"
					 class="search_store_left" 
					 style='<%=("enter_store_online".equals(enter_display)) ? "background-color: orange" :"" %>'><b>線上諮詢</b></a></li>
                    <li><a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed search_store_left" aria-expanded="false" aria-controls="ccc" style='<%=("enter_store_menu".equals(enter_display)) ? "background-color: orange" :"" %>'><b>餐點與訂購　<span class="glyphicon glyphicon-chevron-down"></span></b></a></li>
                        <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
                              <div class="panel-body"><b>
                                    <a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=menu&str_no=${strVO.str_no}&dcla_no=all"
                                    >所有餐點</a><br><br>
                                    <c:forEach var="dclaVO" items="${dclaList}">
                                    <a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=menu&str_no=${strVO.str_no}&dcla_no=${dclaVO.dcla_no}">
                                    ${dclaVO.dcla_name}</a><br><br>
                                    </c:forEach>
                                    <!--  <a href="#" style="color:red">促銷方案</a><br><br>-->
                             </div></b>
                        </div>
				</ul>
			</div>

			<div class="col-xs-12 col-sm-9 " id="search_store_content">
				<%if ("enter_store_menu".equals(enter_display)) {%>

				<jsp:include page="enter_storeMenu.jsp" flush="true" />
				
				<%} else if("enter_store_blog".equals(enter_display)){%>
				
				<jsp:include page="enter_storeBlog.jsp" flush="true" />
				
				<%} else if("enter_store_comment".equals(enter_display)){%>

                <jsp:include page="enter_storeComment.jsp" flush="true" />
                
                <%} else if("enter_store_online".equals(enter_display)){%>
                
                <jsp:include page="enter_storeOnline.jsp" flush="true" />
				
				<%} else {%>

				<jsp:include page="enter_storeIntroduce.jsp" flush="true" />

				<%}%>



			</div>
		</div>
	</div>




	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>