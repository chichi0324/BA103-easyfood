<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dishclass.model.*" %>
<%
	DclaVO dclaVO = (DclaVO) request.getAttribute("dclaVO");
%>

<table class="table">
	<tr align="center">
		<th style="border-top-left-radius:5px;border-bottom-left-radius:5px;">菜單類別編號</th>
		<th style="border-top-right-radius:5px;border-bottom-right-radius:5px;">菜單類別名稱</th>
	</tr>
	<tr align="left">
		<td>${dclaVO.dcla_no}</td>
		<td>${dclaVO.dcla_name}</td>
	</tr>
</table>
<jsp:include page="footer.jsp" />