<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*" %>
<%@ page import="com.admp.model.*" %>
<%@ page import="com.fea.model.*" %>
<%
AdmVO admVO = (AdmVO) session.getAttribute("admVO_account");
List<String> feaList=null;
if(admVO!=null){
	feaList=(List<String>) session.getAttribute(admVO.getAdm_acc());
}


%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便管理系統</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/back-end/css/base.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

	</head>
	<body>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
		    
      
      <jsp:include page="/easyfood/back-end/leftGroup.jsp" flush="true" />



			</div>
			<div class="col-xs-12 col-sm-9">
			     <br>

			     <!--右邊導覽列-->
			 <div class="row">   

          <jsp:include page="/easyfood/back-end/navbar.jsp" flush="true" />

        </div> 
     <!--內容-->

	<div class="row third_row">
		<div id="third_content">
          <br>
            <div class="col-xs-12 col-sm-4">
            	<div class="gallery">
                   <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0005" style='<%=(feaList.contains("FEA_0005")) ? "" : "pointer-events: none;cursor: default;"%>'>
                    <img src="images/adm.png" class="title_image"  width="200" height="170">
                   </a>
                   <div class="desc" align="center">
                       <h5><b>管理員管理</b></h5>
                   </div>
                </div>
            </div> 
            <div class="col-xs-12 col-sm-4">
              <div class="gallery">
                   <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0004" style='<%=(feaList.contains("FEA_0004")) ? "" : "pointer-events: none;cursor: default;"%>'>
                    <img src="images/private.png" class="title_image"  width="200" height="170">
                   </a>
                   <div class="desc" align="center">
                       <h5><b>管理員權限管理</b></h5>
                   </div>
                </div>
            </div> 
            <div class="col-xs-12 col-sm-4">
            	<div class="gallery">
                   <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0003" style='<%=(feaList.contains("FEA_0003")) ? "" : "pointer-events: none;cursor: default;"%>'>
                    <img src="images/store.png" class="title_image"  width="200" height="170">
                    </a>
                   <div class="desc" align="center">
                      <h5><b>店家管理</b></h5>
                   </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-4">
            	<div class="gallery">
                   <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0002" style='<%=(feaList.contains("FEA_0002")) ? "" : "pointer-events: none;cursor: default;"%>'>
                    <img src="images/member.jpg" class="title_image"  width="200" height="170">
                   </a>
                   <div class="desc" align="center">
                       <h5><b>會員管理</b></h5>
                   </div>
                </div>
            </div> 
            <div class="col-xs-12 col-sm-4">
            	<div class="gallery">
                   <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0006" style='<%=(feaList.contains("FEA_0006")) ? "" : "pointer-events: none;cursor: default;"%>'>
                    <img src="images/report.png" class="title_image"  width="200" height="170">
                    </a>
                   <div class="desc" align="center">
                      <h5><b>檢舉管理</b></h5>
                   </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-4">
            	<div class="gallery">
                   <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0001" style='<%=(feaList.contains("FEA_0001")) ? "" : "pointer-events: none;cursor: default;"%>'>
                    <img src="images/pro.jpg" class="title_image"  width="200" height="170">
                    </a>
                   <div class="desc" align="center">
                      <h5><b>廣告管理</b></h5>
                   </div>
                </div>
            </div>


		</div>
	</div>



			</div>

		</div>
	</div>


<!-- footer -->
<jsp:include page="/easyfood/back-end/footer.jsp" flush="true" />
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>