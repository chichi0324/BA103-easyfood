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


String leftGroup=(String) session.getAttribute("leftGroup");

%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便管理系統</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

	</head>
	<body>

		    
                <div id ="small" style="height: 250px">
                  <img src="<%=request.getContextPath()%>/easyfood/back-end/images/logo.png" width=100%  style="margin-top: 15px">
                </div>


		    <div class="list-group"><b>
			    <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0002" class="list-group-item list-group-item-warning <%=("member".equals(leftGroup) ? "active" :"") %>" style='<%=(feaList.contains("FEA_0002")) ? "" : "pointer-events: none;cursor: default;"%>'>會員管理</a>
			    <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0003" class="list-group-item list-group-item-warning <%=("store".equals(leftGroup) ? "active" :"") %>" style='<%=(feaList.contains("FEA_0003")) ? "" : "pointer-events: none;cursor: default;"%>'>店家管理</a>
			    <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0005" class="list-group-item list-group-item-warning <%=("administrator".equals(leftGroup) ? "active" :"") %>" style='<%=(feaList.contains("FEA_0005")) ? "" : "pointer-events: none;cursor: default;"%>'>管理員管理</a>
                <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0004" class="list-group-item list-group-item-warning <%=("admprivileges".equals(leftGroup) ? "active" :"") %>" style='<%=(feaList.contains("FEA_0004")) ? "" : "pointer-events: none;cursor: default;"%>'>管理員權限管理</a>
			    <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0006" class="list-group-item list-group-item-warning <%=("report".equals(leftGroup) ? "active" :"") %>" style='<%=(feaList.contains("FEA_0006")) ? "" : "pointer-events: none;cursor: default;"%>'>檢舉管理</a>
			    <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=FEA_0001" class="list-group-item list-group-item-warning <%=("advertising".equals(leftGroup) ? "active" :"") %>" style='<%=(feaList.contains("FEA_0001")) ? "" : "pointer-events: none;cursor: default;"%>'>廣告管理</a>
                <a target="_blank" href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=index" class="list-group-item list-group-item-danger">食在方便首頁</a>
		    </b></div>







		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>