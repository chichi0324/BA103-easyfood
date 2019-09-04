<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.store.model.*" %>
<%@ page import="com.storecategory.model.*" %>

<%
	StrVO strVO = (StrVO) request.getAttribute("strVO");
	
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
						<select class="form-control" name="status" style="width:180px;">
							<option value="建置中">建置中</option>
						    <option value="營業中">營業中</option>
					  	</select>
					  	<button class="btn btn-lg btn-mybtn" style="width:150px">修改狀態</button>
						<input type="hidden" name="str_no" value="${strVO.str_no}">
						<input type="hidden" name="action" value="UPDATE">
					</div>
				</form>
			
				<div class="col-xs-12 col-sm-2">
					<div style="display:inline-block;vertical-align:super;">
						<img src="<%=request.getContextPath()%>/str/readStorePicture.do?STR_NO=${strVO.str_no}" height="150px" width="200px" id="img">
					</div>
				</div>
	
	
				<div class="col-xs-12 col-sm-10">
				<div class="form-horizontal">

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="no">店家編號</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="no" readonly type="text" name="str_no" value="${strVO.str_no}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="name">店家名稱</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="name" readonly type="text" name="str_name" value="${strVO.str_name}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="status">店家狀態</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="status" readonly type="text" name="str_stat" value="${strVO.str_stat}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="no">店家地址</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="adr" readonly type="text" name="str_adr" value="${strVO.str_cou}${strVO.str_city}${strVO.str_addr}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="stoca">店家類別</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<c:forEach var="stocaVO" items="${stocaList}">
								<c:if test="${stocaVO.stoca_no.equals(strVO.stoca_no)}">
									<input readonly type="text" name="stoca_no" value="${stocaVO.stoca_name}">
								</c:if>
							</c:forEach>
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="atn">聯絡人</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="atn" readonly type="text" name="str_atn" value="${strVO.str_atn}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="tel">聯絡電話</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="tel" readonly type="text" name="str_tel" value="${strVO.str_tel}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="mail">聯絡信箱</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="mail" readonly type="text" name="str_ma" value="${strVO.str_ma}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="acc">店家帳號</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="acc" readonly type="text" name="str_acc" value="${strVO.str_acc}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="pas">店家密碼</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="pas" readonly type="text" name="str_pas" value="${strVO.str_pas}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="ship">外送許可</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="ship" readonly  type="text" name="str_ship" value="${strVO.str_ship}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="pre">備餐時間</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="pre" readonly type="text" name="str_pre" value="${strVO.str_pre}">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12 col-sm-3 control-label">
							<label for="remark">檢舉累計次數</label>
						</div>
						<div class="col-xs-12 col-sm-9">
							<input id="remark" readonly type="text" name="str_rep" value="${strVO.str_rep}">
						</div>
					</div>	

				</div>	
			</div>

			</div>
		</div>
	</div>
	
</section>

<script>
	var status = document.getElementsByName("str_stat")[0].value;
	var selected = document.getElementsByTagName("select")[0];
	var btn = document.getElementsByTagName("button")[0];
	if(status != "建置中") {
		selected.style.visibility = "hidden";
		btn.style.visibility = "hidden";
	}
</script>
<jsp:include page="footer.jsp" />
