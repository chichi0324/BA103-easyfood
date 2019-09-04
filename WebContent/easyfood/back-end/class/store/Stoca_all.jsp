<jsp:include page="header.jsp" />
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.storecategory.model.*" %>
<%
	StocaService stocaSvc = new StocaService();
	List<StocaVO> list = stocaSvc.getAll();
	pageContext.setAttribute("stocaList", list);
%>

<section>

	<div class="container-floid">
		<div class="row">
			<div class="col-xs-12">

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
 					<a href="<%=request.getContextPath()%>/easyfood/back-end/class/store/Stoca_add.jsp">
						<button class="btn btn-lg btn-mybtn" style="width:150px">新增店家類別</button>
 					</a>
				</div>
	
				
				<table class="table">
					<tr align="center">
						<th style="border-top-left-radius:5px;border-bottom-left-radius:5px;text-align:center;">類別圖片</th>
						<th>類別編號</th>
						<th>類別名稱</th>
						<th>類別介紹</th>
						<th style="border-top-right-radius:5px;border-bottom-right-radius:5px;"></th>
					</tr>
					
				<%@ include file="page1.file" %>
					<c:forEach var="stocaVO" items="${stocaList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align="left">
						<td style="width:200px">
							<img src="<%=request.getContextPath()%>/stoca/readStocaPicture.do?STOCA_NO=${stocaVO.stoca_no}" 
								height="150px" width="200px">
						</td>
						<td>${stocaVO.stoca_no}</td>
						<td>${stocaVO.stoca_name}</td>
						<td>${stocaVO.stoca_note}</td>
						<td>
							<form action="<%=request.getContextPath() %>/stoca/stoca.do" method="post" enctype="multipart/form-data">
								<button class="btn btn-lg btn-primary">修改</button>
								<input type="hidden" name="stoca_no" value="${stocaVO.stoca_no}">
								<input type="hidden" name="action" value="UPDATE_ONE">
							</form>
						</td>
					</tr>
					</c:forEach>
					
				</table>
				<%@ include file="page2.file" %>				


			</div>
		</div>
	</div>

</section>
<jsp:include page="footer.jsp" />
