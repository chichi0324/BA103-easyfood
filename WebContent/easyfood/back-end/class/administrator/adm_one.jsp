<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.adm.model.*" %>
<%@ page import="com.admp.model.*" %>
<%@ page import="com.fea.model.*" %>
<%@ page import="com.tools.*" %>
<%
AdmVO admVO = (AdmVO) request.getAttribute("admVO");

//admVO查無資料時,會傳參數為admStr的"noData"
String admStr=(String) request.getAttribute("admStr");

List<FeaVO> feaList=null;
if(admVO!=null){
	feaList=tools.getFeaName(admVO);	
}

%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便管理系統</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/back-end/css/base.css">
<!--[if lt IE 9]>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<%if(admVO==null){ 

	
	if("noData".equals(admStr)){ %>
		<br><br>
		<div style="color: red" class="text-center">查無資料</div>

<%}
	
}else{%>


                 <br><br><br><br><br><br>
               <div class="col-sm-offset-2 col-sm-6 stas">               
                  <form action="<%=request.getContextPath()%>/easyfood/back-end/class/administrator/adm.do" method="get">
                     <table class="table table-bordered table-striped table-hover" style='word-break: keep-all'>
                         <thead >
                           <tr class="success">
                               <th class="col-sm-2">帳號</th>
                               <th>員工資料</th>
                           </tr>
                         </thead>
                         <tbody>
                               <tr><td>帳號</td><td><%=admVO.getAdm_acc() %></td></tr>
                               <tr><td>姓名</td><td><%=admVO.getAdm_name() %></td></tr>
                               <tr><td>電話</td><td><%=admVO.getAdm_pho() %></td></tr>
                               <tr><td>信箱</td><td><%=admVO.getAdm_mail() %></td></tr>
                               <tr><td>地址</td><td><%=admVO.getAdm_adrs() %></td></tr>                         
                               <tr><td>職位</td><td><%=admVO.getAdm_pos() %></td></tr>
                               <tr><td>權限</td>
                                  <td> <% for(int i=0;i<feaList.size();i++){ %> 
                                       <%= feaList.get(i).getFea_name() %><br>
                                       <%} %>
                                  </td>
                               </tr>
                         </tbody>
                     </table>

	<div align="center">
		<input type='hidden' value="<%=admVO.getAdm_no() %>" name="adm_no">
		<input type='hidden' value="display_update_adm" name="action">
		<input type='submit' value='修改' class="btn btn-default">
	</div>
</form>
            </div>

<% }%>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>