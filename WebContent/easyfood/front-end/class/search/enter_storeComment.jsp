<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.dish.model.*"%>
<%@ page import="com.ord.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tools.tools"%>
<%
tools tool=new tools();
pageContext.setAttribute("tools",tool);

	StrVO strVO = (StrVO) request.getAttribute("strVO");

	OrdService ordSvc = new OrdService();
	List<OrdVO> list = ordSvc.findByStore(strVO.getStr_no());

	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/search/css/search_store_comment.css">

		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->


  </head>
	<body>




    <!--第二列右邊第一排==========================================-->
      <div class="row">
        <div class="col-xs-12 col-sm-12">
          <p class="storeNav"><b>首頁 > 搜尋店家(<%=strVO.getStr_name() %>) > 店家評價</b></p>
        </div>
      </div>

      <div class="row">
        <div class="col-xs-12 col-sm-12">
        <h2 style="font-family:Microsoft JhengHei"><b><center>店家評價</center></b></h2>

  <%if (list.size() == 0) {%>
  <br>
  <div style="color: red" class="text-center">目前暫無評論</div>
  <%} else {%>

        <table class="table table-hover" align="center">
        <thead>
          <tr class="danger">
              <th>
                <h4 class="text-center commenter"><b>評論者</b></h4>
              </th>
              <th><h4 class="text-center" id="comment_star"><b>星星數</b></h4></th>
              <th><h4 class="text-center" id="comment_text"><b>內容</b></h4></th>
          </tr>
        </thead>
        <tbody><ul>
          <c:forEach var="ordVO" items="${list}">
          <tr><td class="commenter"><b>${tools.getMemName(ordVO.mem_no)}</b></td><td><img src="../search/images/stars/star${ordVO.ord_ev}.png" align="left" class="star_img"></td><td>${ordVO.ord_eva}</td>
          </tr>
          </c:forEach>
        </tbody></ul>
        </table>
        
  <%}%>

        </div>
      </div>




   

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>