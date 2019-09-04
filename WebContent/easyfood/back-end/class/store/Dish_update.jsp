<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dish.model.*"%>
<%@ page import="com.dishclass.model.*" %>
<%@ page import="com.store.model.*" %>
<% 
	DishVO dishVO = (DishVO)request.getAttribute("dishVO");
	
	DclaService dclaSvc = new DclaService();
	DclaVO dclaVO = dclaSvc.getOneDcla(dishVO.getDcla_no());
	pageContext.setAttribute("dclaVO", dclaVO);

	StrService strSvc = new StrService();
	StrVO strVO = strSvc.getOneStr(dishVO.getStr_no());
	pageContext.setAttribute("strVO", strVO);
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
		
				<form action="<%=request.getContextPath() %>/dish/dish.do" method="post">
					<div style="text-align:end;">
						<select class="form-control" name="dish_status" style="width:180px;">
							<option value="準備中">準備中</option>
						    <option value="販售中">販售中</option>   
					  	</select>
					  	<button class="btn btn-lg btn-mybtn" style="width:150px">修改狀態</button>
						<input type="hidden" name="dish_no" value="${dishVO.dish_no}">
						<input type="hidden" name="action" value="UPDATE">
					</div>
				</form>
			
				<div class="col-xs-12 col-sm-2">
					<div style="display:inline-block;vertical-align:super;">
						<img src="<%=request.getContextPath()%>/dish/readDishPicture.do?DISH_NO=${dishVO.dish_no}" height="150px" width="200px" id="img">
					</div>
				</div>
	
	
				<div class="col-xs-12 col-sm-10">
				<div class="form-horizontal">

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="no">菜單編號</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="no" readonly type="text" name="dish_no" value="${dishVO.dish_no}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="name">菜單名稱</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="name" readonly type="text" name="dish_name" value="${dishVO.dish_name}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="price">菜單價格</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="price" readonly type="text" name="dish_price" value="${dishVO.dish_price}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="dcla">餐點類別</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<c:if test="${dishVO.dcla_no.equals(dclaVO.dcla_no)}">
								<input id="dcla" readonly type="text" name="dcla_no" value="${dclaVO.dcla_name}">
							</c:if>
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="store">店家名稱</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="store" readonly type="text" name="str_name" value="${strVO.str_name}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="status">餐點狀態</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="status" readonly type="text" name="dish_status" value="${dishVO.dish_status}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="info">餐點介紹</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<textarea id="info" readonly name="dish_note">${dishVO.dish_note}</textarea>
						</div>
					</div>
					
				</div>	
			</div>

			</div>
		</div>
	</div>
	
</section>

<script>
	var status = document.getElementById("status").value;
	var selected = document.getElementsByTagName("select")[0];
	var btn = document.getElementsByTagName("button")[0];
	if(status != "準備中") {
		selected.style.visibility = "hidden";
		btn.style.visibility = "hidden";
	}
</script>

<jsp:include page="footer.jsp" />