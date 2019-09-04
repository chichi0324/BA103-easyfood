<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.storecategory.model.*" %>
<%	
	StocaVO stocaVO = (StocaVO) request.getAttribute("stocaVO");
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


<table class="table">
	<tr align="center">
		<th style="border-top-left-radius:5px;border-bottom-left-radius:5px;text-align:center;">
			類別圖片
		</th>
		<th>類別編號</th>
		<th>類別名稱</th>
		<th style="border-top-right-radius:5px;border-bottom-right-radius:5px;">類別介紹</th>
	</tr>
	<tr align="left">
		<td style="width:200px;">
			<img src="<%=request.getContextPath()%>/stoca/readStocaPicture.do?STOCA_NO=${stocaVO.stoca_no}" 
				height="150px" width="200px">
		</td>
		<td>${stocaVO.stoca_no}</td>
		<td>${stocaVO.stoca_name}</td>
		<td>${stocaVO.stoca_note}</td>
	</tr>
</table>
<jsp:include page="footer.jsp" />