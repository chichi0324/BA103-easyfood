<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.store.model.*" %>
<%@ page import="com.storecategory.model.*" %>
<%@ page import="java.util.List" %>

<%
	StocaService stocaSvc = new StocaService();
	List<StocaVO> stocaList = stocaSvc.getAll();
	pageContext.setAttribute("stocaList", stocaList);
%>

<c:if test="${not empty errorMsgs}">
	<div class="error">請修正以下錯誤:
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

				<form action="<%=request.getContextPath() %>/str/store.do" method="post">
					<div style="text-align:end;">
						<select class="form-control" name="str_stat" style="width:180px;">
							<option value="店家狀態" selected>店家狀態</option>
							<option value="建置中">建置中</option>
						    <option value="營業中">營業中</option>
						    <option value="已下架">已下架</option>
					  	</select>
					  	<button class="btn btn-lg btn-mybtn" style="width:150px" id="myBtn">查詢</button>
						<input type="hidden" name="action" value="SEARCH_STATUS">			
					</div>
				</form>

				<table class="table">
					<tr align="center">
						<th style="border-top-left-radius:5px;border-bottom-left-radius:5px;text-align:center;">店家編號</th>
						<th>店家類型</th>
						<th>詳細地址</th>
						<th>聯絡人</th>
						<th>聯絡電話</th>
						<th>店家狀態</th>
						<th style="border-top-right-radius:5px;border-bottom-right-radius:5px;">店家審核</th>
					</tr>
					
					<c:forEach var="strVO" items="${strList}">
					<tr align="left">
						<td>
							<a href="<%=request.getContextPath()%>/easyfood/back-end/class/store/Dish_one.jsp?STR_NO=${strVO.str_no}">
								${strVO.str_no}
							</a>
						</td>
						
						<c:forEach var="stocaVO" items="${stocaList}">
							<c:if test="${strVO.stoca_no == stocaVO.stoca_no}">
								<td>${stocaVO.stoca_name}</td>
							</c:if>
						</c:forEach>
						
						<td>${strVO.str_cou}${strVO.str_city}${strVO.str_addr}</td>
						<td>${strVO.str_atn}</td>
						<td>${strVO.str_tel}</td>
						<td id="status">${strVO.str_stat}</td>
		
						<td>
							<form action="<%=request.getContextPath() %>/str/store.do" method="post">
								<button class="btn btn-lg btn-primary">修改狀態</button>
								<input type="hidden" name="str_no" value="${strVO.str_no}">
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
<script>
	var state = document.getElementById("status").textContent;
	console.log(state);
	document.getElementsByTagName("select")[0].value = state;
	
	var btn = document.getElementsByClassName("btn btn-lg btn-primary");
	if(state != "建置中") {
		for(var i = 0 ; i < btn.length ; i++) {
			btn[i].textContent = "查看資訊";
		}
	}
</script>
<jsp:include page="footer.jsp" />