<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dishclass.model.*" %>
<%
	DclaService dclaSvc = new DclaService();
	List<DclaVO> list = dclaSvc.getAll();
	pageContext.setAttribute("dclaList", list);
%>

<c:if test="${not empty errorMsgs}">
	<div style="color:red;">請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>

<div style="margin:5px; text-align:right;">
	<a href="<%=request.getContextPath()%>/easyfood/back-end/class/store/Dcla_add.jsp">
		<button class="btn btn-lg btn-mybtn" style="width:150px" onclick="add();">新增菜單類別</button>
	</a>
</div>

<table class="table">
 <tr align="center">
	<th style="border-top-left-radius:5px;border-bottom-left-radius:5px;">菜單類別編號</th>
	<th>菜單類別名稱</th>
	<th style="border-top-right-radius:5px;border-bottom-right-radius:5px;"></th>
 </tr>
 
 <%@ include file="page1.file" %>
 	<c:forEach var="dclaVO" items="${dclaList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='left'>
			<td>${dclaVO.dcla_no}</td>
			<td>${dclaVO.dcla_name}</td>
			<td>
				<form action="<%=request.getContextPath()%>/dcla/dcla.do" method="post" style="text-align:end;">
					<button class="btn btn-lg btn-primary" style="width:5em;">修改</button>
					<input type="hidden" name="dcla_no" value="${dclaVO.dcla_no}">
					<input type="hidden" name="action" value="UPDATE_ONE">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<jsp:include page="footer.jsp" />
