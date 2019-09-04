<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>

	form {
		margin-top: 3em;
		margin-left: 10em;
		margin-right: 10em;
	}

</style>

<c:if test="${not empty errorMsgs}">
	<div style="color:red;">請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<form action="<%=request.getContextPath()%>/dcla/dcla.do" method="post">
	<div>
		<input id="no" type="text" name="dcla_name" placeholder="菜色名稱">
	</div>
	
	<div style="margin-top:1em;">
		<button class="btn btn-lg btn-primary" name="action" value="ADD">確定</button>
	</div>
</form>

<jsp:include page="footer.jsp" />