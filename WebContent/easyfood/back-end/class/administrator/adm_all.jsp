<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.admp.model.*"%>
<%@ page import="com.fea.model.*"%>
<%@ page import="com.tools.*"%>
<%
	AdmService admpSvc = new AdmService();
	List<AdmVO> admList = admpSvc.getAll();
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
	<%
		if (admList.size() == 0) {
	%>


	<br>
	<br>
	<div style="color: red" class="text-center">目前無員工資料</div>

	<%
		} else {
	%>


	<br>

	<div class="stas">


			<table class="table table-striped table-hover"
				style= "word-break:break-all; ">
				<thead>
					<tr class="success">
						<th>帳號</th>
						<th>姓名</th>
						<th>電話</th>
						<th width="85px">信箱</th>
						<th>職位</th>
						<th width="85px">地址</th>
						<th>權限</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
				</thead>
				<tbody>
				   <% for(int i=0;i<admList.size();i++){ %>
					<tr>
						<td><%= admList.get(i).getAdm_acc() %></td>
						<td><%= admList.get(i).getAdm_name() %></td>			
						<td><%= admList.get(i).getAdm_pho() %></td>
						<td><%= admList.get(i).getAdm_mail() %></td>
						<td><%= admList.get(i).getAdm_pos() %></td>
						<td><%= admList.get(i).getAdm_adrs() %></td>
						<td><% List<FeaVO> feaList= tools.getFeaName(admList.get(i));  
							 for(int j=0;j<feaList.size();j++) { %>	
							<%= feaList.get(j).getFea_name() %><br>
							<%} %>
						</td>
						<td>
							<div align="center">
								<form action="<%=request.getContextPath()%>/easyfood/back-end/class/administrator/adm.do" method="get">
								<input type='hidden' value="<%= admList.get(i).getAdm_no() %>" name="adm_no">
								<input type='hidden' value="display_update_adm" name="action"> <input type='submit' value='修改' class="btn btn-default">
							    </form>
							</div>
						</td>
						<td>
							<div align="center">
								<form action="<%=request.getContextPath()%>/easyfood/back-end/class/administrator/adm.do" method="get">
								<input type='hidden' value="<%= admList.get(i).getAdm_no() %>" name="adm_no">
								<input type='hidden' value="delete_adm" name="action"> <input type='submit' value='刪除' class="btn btn-default">
							    </form>
							</div>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>

		
	</div>

	<%
		}
	%>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>