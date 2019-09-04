<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dish.model.*" %>
<%@ page import="com.dishclass.model.*" %>
<%@ page import="java.util.List" %>

<%		
	List<DishVO> dishList = (List<DishVO>) request.getAttribute("dishList");
	pageContext.setAttribute("dishList", dishList);
	
	DclaService dclaSvc = new DclaService();
	List<DclaVO> dclaList = dclaSvc.getAll();
	pageContext.setAttribute("dclaList", dclaList);
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

<section>

	<div class="container-floid">
		<div class="row">
			<div class="col-xs-12">

				<form action="<%=request.getContextPath() %>/dish/dish.do" method="post">
					<div style="text-align:end;">
						<select class="form-control" name="dish_status" style="width:180px;">
							<option value="餐點狀態" selected>餐點狀態</option>
							<option value="準備中">準備中</option>
						    <option value="販售中">販售中</option>
						    <option value="已下架">已下架</option>
					  	</select>
					  	<button class="btn btn-lg btn-mybtn" style="width:150px">查詢狀態</button>
						<input type="hidden" name="action" value="SEARCH_STATUS">			
					</div>
				</form>
	
				<table class="table">
					<tr align="center">
						<th style="border-top-left-radius:5px;border-bottom-left-radius:5px;text-align:center;">菜單編號</th>
						<th>菜單名稱</th>
						<th>菜單價格</th>
						<th>餐點類別</th>
						<th>店家名稱</th>
						<th>餐點狀態</th>
						<th style="border-top-right-radius:5px;border-bottom-right-radius:5px;">修改狀態</th>
					</tr>
					
					<c:forEach var="dishVO" items="${dishList}">
					<tr align="left">
						<td>${dishVO.dish_no}</td>
						<td>${dishVO.dish_name}</td>
						<td>${dishVO.dish_price}</td>
						
						<c:forEach var="dclaVO" items="${dclaList}">
							<c:if test="${dishVO.dcla_no.equals(dclaVO.dcla_no)}">
								<td>${dclaVO.dcla_name}</td>
							</c:if>
						</c:forEach>
		
						<jsp:useBean id="strSvc" scope="page" class="com.store.model.StrService" />
						<c:forEach var="strVO" items="${strSvc.all}">
							<c:if test="${dishVO.str_no.equals(strVO.str_no)}">
								<td>${strVO.str_name}</td>
							</c:if>
						</c:forEach>
										
						<td class="status">${dishVO.dish_status}</td>
		
						<td>
							<form action="<%=request.getContextPath() %>/dish/dish.do" method="post">
								<button class="btn btn-lg btn-primary">狀態審核</button>
								<input type="hidden" name="dish_no" value="${dishVO.dish_no}">
								<input type="hidden" name="action" value="UPDATE_ONE">
							</form>
						</td>
					</tr>
					</c:forEach>
		
				</table>	

			</div>
		</div>
	</div>

</section>


<jsp:include page="footer.jsp" />