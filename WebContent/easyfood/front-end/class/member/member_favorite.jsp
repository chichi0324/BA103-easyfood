<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="java.util.*"%>
<%@ page import="com.fav.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.add.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
MemVO memVO = (MemVO) session.getAttribute("memVO");
String mem_no = memVO.getMem_no();
%>	
<%
    FavService favSvc = new FavService();   
    List<FavVO> list = favSvc.getoneFav(mem_no);
    pageContext.setAttribute("list",list); 
    

%>	
	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StrService" />
	
	
	
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 		<link rel="short Icon" type="images/x-icon" href="images/logo.jpg"> -->

	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<!-- 	    <link rel="short Icon" type="images/x-icon" href="../../images/logo.png"> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/favorite.css">	    
	    <style type="text/css">
 
      ol{
    margin-top: 50px;
    margin-bottom: 10px;
	}
    </style>

	</head>
	<body>


	<!--導覽列===============================================================================-->
<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />

<!--第一列===================================================================================-->
	
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<ol class="breadcrumb">
					<li><a
						href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=index">首頁</a></li>
					<li><a
						href="<%=request.getContextPath()%>/easyfood/front-end/class/member/member.jsp">會員中心</a></li>
				    <li class="active"><a href="<%=request.getContextPath()%>/easyfood/front-end/class/member/member_favorite.jsp">最愛店家</a></li>
				</ol>
			</div>
		</div>
	</div>
	
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-3">
            <jsp:include page="leftGroup.jsp" flush="true" />
		</div>

		<div class="col-xs-12 col-sm-8">
		<div role="tabpanel">
          <!-- 標籤面板：標籤區 -->
          <ul class="nav nav-tabs" role="tablist">
              <li role="presentation" class="active">
                  <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" class=toplove>最愛店家</a>
              </li>             
          </ul>
      
          <!-- 標籤面板：內容區 -->
          <div class="tab-content">
			 
			         
          	<div role="tabpanel" class="tab-pane active" id="tab1">
          	
              <c:forEach var="favVO" items="${list}">    
                	<div class="fatable"> 
                	 	
                		<c:forEach var="strVO" items="${storeSvc.all}">	
                		
                			<c:if test="${favVO.str_no==strVO.str_no}"> 
                			   <div>
                      	     <a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=introduce&str_no=${strVO.str_no}">
                      	     <img class="abc" src="<%=request.getContextPath()%>/tools/Mem_Red_Img?str_no=${strVO.str_no} " style="width:300px"></a>
                      			${strVO.str_name} 
                			       <div style="float:right;">
  	                  	
                							  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/member/fav.do">
                						    <input type="submit" class="btn btn-danger btn-tb" value="移除">    		
                						    <input type="hidden" name="mem_no" value="${mem_no}">
                						    <input type="hidden" name="str_no" value="${favVO.str_no}">
                						    <input type="hidden" name="action"value="delete_For_Fav"></FORM>
                                
	                  	        </div>
                			   </div>  		              				
                			</c:if>
                		</c:forEach> 	                  	
                  </div>
                </c:forEach>              
              </div>                 
			     </div>             
        </div>
    </div>	
		


		
      		

		</div>
	</div>
</div>


	<!--底部平台介紹==============================================================================-->
<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    $(function(){

      var navbarTop = $('.shoppingCart').offset().top;
      
      $('.shoppingCart').affix({
        offset:{
          top: navbarTop         
        }
      })
    })
    $('.btn-minuse').on('click', function(){            $(this).parent().siblings('input').val(parseInt($(this).parent().siblings('input').val()) - 1)
})

$('.btn-pluss').on('click', function(){            $(this).parent().siblings('input').val(parseInt($(this).parent().siblings('input').val()) + 1)
})
    </script>
	</body>
</html>