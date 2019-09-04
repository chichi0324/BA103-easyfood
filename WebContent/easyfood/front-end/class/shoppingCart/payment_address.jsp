<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.* ,com.cartitem.model.*,com.cartorder.model.*,com.add.model.*,com.mem.model.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String ord_type = (String) session.getAttribute("ord_type");
	System.out.println("payment_address_ord_type: " + ord_type);
	session.setAttribute("memVO", memVO);
	Integer CheckOutOrderIndex = (Integer) session.getAttribute("CheckOutOrderIndex");
	System.out.println("payment_address_CheckOutOrderIndex: " + CheckOutOrderIndex);
%>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="short Icon" type="images/x-icon"
	href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png
">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/class/shoppingCart/css/payment&address.css">
<style type="text/css">
</style>
</head>
<body>


	<!--導覽列=============================================================================================-->
	<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />
	<!--第一列===================================================================================-->
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row ">

			<div class="col-xs-12 col-md-6 col-centered">


				<!-- CREDIT CARD FORM STARTS HERE -->
				<div class="panel panel-default credit-card-box">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>請修正以下錯誤:
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
						</font>
					</c:if>
					<div class="panel-heading display-table text-left" id="heading1">
<!-- 						<div class="row display-tr"> -->
							<h1 class="panel-title display-td" id="title">外送地址</h1>
<!-- 							<div class="display-td"> -->
<!-- 								<img class="img-responsive pull-right" -->
<!-- 									src="http://i76.imgup.net/accepted_c22e0.png"> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
					<div class="panel-body">
<!-- 						<div class="row"> -->
<!-- 							<div class="col-xs-12"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label for="cardNumber">卡號</label> -->
<!-- 									<div class="input-group"> -->
<!-- 										<input type="password" class="form-control" name="cardNumber" -->
<!-- 											placeholder="Valid Card Number" autocomplete="cc-number" -->
<!-- 											required autofocus /> <span class="input-group-addon"><i -->
<!-- 											class="fa fa-credit-card"></i></span> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-xs-7 col-md-7"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label for="cardExpiry"><span class="hidden-xs">有效期限</span><span -->
<!-- 										class="visible-xs-inline">EXP</span></label> <input type="tel" -->
<!-- 										class="form-control" name="cardExpiry" placeholder="MM / YY" -->
<!-- 										autocomplete="cc-exp" required /> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-xs-5 col-md-5 pull-right"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label for="cardCVC">安全碼</label> <input type="password" -->
<!-- 										class="form-control" name="cardCVC" placeholder="CVC" -->
<!-- 										autocomplete="cc-csc" required /> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/search/cart.do">

							<%
								if (ord_type.equals("外送")) {
							%>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label for="cardNumber">地址</label>
										<div class="input-group">

											<input type="text" class="form-control" name="add_adds"
												placeholder="Address" /> <span class="input-group-addon"><i
												class="fa fa-map-marker"></i></span>
										</div>
									</div>
								</div>
							</div>


							<%
								List<String> oneMemAdd = new ArrayList();
									AddService addSvc = new AddService();
									List<AddVO> addlist = addSvc.getAll();
									for (int i = 0; i < addlist.size(); i++) {
										AddVO addVO = addlist.get(i);
										if (addVO.getMem_no().equals(memVO.getMem_no())) {
											oneMemAdd.add(addVO.getAdd_adds());
										}
									}
									if (oneMemAdd.size() > 0) {
							%>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label for="cardNumber">常用地址</label> <select
											class="form-control" name="add_addsFormFav">
											<%
												for (int j = 0; j < oneMemAdd.size(); j++) {
															String add_adds = oneMemAdd.get(j);
											%>
											<%-- 												<c:forEach var="addVO" items="${oneMemAdd}"> --%>
											<option value="<%=add_adds%>"><%=add_adds%>
												<%-- 												</c:forEach> --%>
												<%
													}
												%>
											
										</select>
									</div>
								</div>
							</div>
							<%
								}
								}
							%>

							<div class="row">
								<div class="col-xs-12">
									<input class="subscribe btn btn-success btn-lg btn-block"
										type="submit" value="確認"> <input type="hidden"
										name="action" value="CHECKOUT"> <input type="hidden"
										name="CheckOutOrderIndex" value="<%=CheckOutOrderIndex%>">
									<input type="hidden" name="ord_type" value="<%=ord_type%>">
						</FORM>
					</div>
				</div>
				<div class="row" style="display: none;">
					<div class="col-xs-12">
						<p class="payment-errors"></p>
					</div>
				</div>
			</div>
		</div>
		<!-- CREDIT CARD FORM ENDS HERE -->


	</div>
	</div>
	</div>
	</div>

	<!--底部平台介紹=============================================================================================-->
	<br>
	<br><br><br><br><br>
	<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>