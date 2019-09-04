<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dishclass.model.*" %>
<%
	DclaVO dclaVO = (DclaVO) request.getAttribute("dclaVO");
%>

<style>

	form {
		margin-top: 3em;
		margin-left: 7em;
		margin-right: 7em;
	}
	

</style>

<form action="<%=request.getContextPath() %>/dcla/dcla.do" method="post">
	
	<div>
		<input type="text" name="dcla_no" value="${dclaVO.dcla_no}" readonly>	
	</div>
	<div>
		<input type="text" name="dcla_name" value="${dclaVO.dcla_name}">	
	</div>
	<div style="margin-top:1em;">
		<button class="btn btn-lg btn-primary" name="action" value="UPDATE">確定</button>
	</div>
	
</form>
<jsp:include page="footer.jsp" />