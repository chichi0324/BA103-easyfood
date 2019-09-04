<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.* ,com.cartitem.model.CartItemVO,com.cartorder.model.CartOrderVO"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="short Icon" type="images/x-icon"
	href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/class/shoppingCart/css/shoppingCart.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style type="text/css">
</style>
</head>
<body>


	<!--導覽列=============================================================================================-->
	<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />

	<!--第一列===================================================================================-->
	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<ol class="breadcrumb">
					<li><a
						href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=index">首頁</a></li>
					<li class="active"><a href="#">便當盒</a></li>
				</ol>
			</div>
		</div>
	</div>
	<%
		Vector<CartOrderVO> orderlist = (Vector<CartOrderVO>) session.getAttribute("shoppingcart");
		Float amount = (Float) session.getAttribute("amount");
	%>
	<%
		if (orderlist != null && (orderlist.size() > 0)) {
	%>
	<%
		for (int orderIndex = 0; orderIndex < orderlist.size(); orderIndex++) {
				CartOrderVO CartOrder = (CartOrderVO) orderlist.get(orderIndex);
				Vector<CartItemVO> buylist = (Vector<CartItemVO>) CartOrder.getBuylist();
	%>

	<div class="container">
		<div class="row">

			<div class="col-sm-12 col-md-10 col-md-offset-1">
				<table class="table table-hover">
					<caption class="text-center" style="color: #ED5B31;">
						<h4>
							<b><%=CartOrder.getStr_name()%></b>
						</h4>
					</caption>
					<thead>
						<tr>
							<th class="text-center">商品</th>
							<th class="text-center">數量</th>
							<th class="text-center">單價</th>
							<th class="text-center">總價</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

						<%
							for (int dishIndex = 0; dishIndex < buylist.size(); dishIndex++) {
										CartItemVO CartItem = (CartItemVO) buylist.get(dishIndex);
						%>
						<form class="form-horizontal" METHOD="post"
							ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/search/cart.do">
							<tr>
								<td class="col-sm-8 col-md-6">
									<div class="media">
										<!-- 										<a class="thumbnail pull-left" href="#">  -->
										<!-- 										<img -->
										<!-- 											class="media-object" src="" -->
										<!-- 											style="width: 72px; height: 72px;"> -->
										<!-- 										</a> -->
										<div class="media-body">
											<h4 class="media-heading">
												<p>
													<b><%=CartItem.getDish_name()%></b>
												</p>
											</h4>

										</div>
									</div>
								</td>
								<td class="col-sm-1 col-md-1 text-center"><%=CartItem.getQuantity()%>


									<!-- 									<div class="count-input space-bottom"> --> <!-- 										<a class="incr-btn" data-action="decrease" href="#">–</a> <input -->
									<%-- 											class="quantity" type="text" name="quantity" value="<%=CartItem.getQuantity()%>"/> <a --%>
									<!-- 											class="incr-btn" data-action="increase" href="#">&plus;</a> -->
									<!-- 									</div> --></td>
								<td class="col-sm-1 col-md-1 text-center"><strong>$<%=(int) Math.round(CartItem.getDish_price())%></strong></td>
								<td class="col-sm-  col-md-1 text-center"><strong>$<%=(int) Math.round(CartItem.getDish_price() * CartItem.getQuantity())%></strong></td>
								<td class="col-sm-1 col-md-1">
									<button type="submit" class="continue btn">
										<span class="fa fa-trash-o fa-lg"></span><b>刪除</b>
									</button> <input type="hidden" name="action" value="DELETE"> <input
									type="hidden" name="orderIndex" value="<%=orderIndex%>"><input
									type="hidden" name="dishIndex" value="<%=dishIndex%>">
								</td>
							</tr>
						</form>
						<%
							}
						%>

						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><h5>小記</h5></td>
							<td class="text-right"><h5>
									<%
										Double orderTotalPrice = (double) 0;
												for (int i = 0; i < buylist.size(); i++) {
													CartItemVO CartItem = buylist.get(i);
													Double price = CartItem.getDish_price();
													int quantity = CartItem.getQuantity();
													orderTotalPrice += (price * quantity);

												}
									%>
									<strong>$<%=(int) Math.round(orderTotalPrice)%></strong>
								</h5></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><h5>運費</h5></td>
							<td class="text-right"><h5>
									<strong>$0</strong>
								</h5></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><h4>
									<b>合計</b>
								</h4></td>
							<td class="text-right"><h4>
									<strong>$<%=(int) Math.round(orderTotalPrice)%></strong>
								</h4></td>
						</tr>
						<%
							if (CartOrder.getPro_cat() != null && (CartOrder.isProClassMeet() || CartOrder.isProMoneyMeet())) {
						%>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td id="discountPrice"
								style="color: red; vertical-align: middle;"><h4><b
								class="nowrap">優惠價</b></h4></td>
							<td class="text-right" style="color: red;"><h4>
									<strong>$<%=(int) Math.round(CartOrder.getOrd_pri())%></strong>
									<h5>
										(省$<%=(int) Math.floor(CartOrder.getDisAmount())%>)
									</h5>
								</h4></td>
						</tr>
						<%
							}
						%>
						<form class="form-horizontal" METHOD="post"
							ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/search/cart.do">
							<tr>
								<td></td>

								<td colspan="2">
									<%
										if (CartOrder.getStr_ship().equals("TRUE")) {
									%> <label class="radio-inline"><input type="radio"
										name="ord_type" value="外送" id="delivery" checked>外送</label> <label
									class="radio-inline"><input type="radio"
										name="ord_type" value="自取" id="takeaway">自取</label>
								</td>
								<%
									}
								%>
								<td>
									<button type="button" class="continue btn btn-default" >
										<p id="continue">
											<span class="glyphicon glyphicon-shopping-cart"></span><a id="contShop"
												href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?str_no=<%=CartOrder.getStr_no()%>&dcla_no=all&select=menu&action=enter_store_select">繼續購物</a>
										</p>
									</button>
								</td>
								<td>

									<button type="submit" class="btn btn-success" id="CHECKOUT">
										送出訂單 <span class="glyphicon glyphicon-play"></span> <input
											type="hidden" name="action" value="beforeCHECKOUT"> <input
											type="hidden" name="CheckOutOrderIndex"
											value="<%=orderIndex%>">
										<%
											System.out.println("shoppingCart_orderIndex" + orderIndex);
										%>
									</button>
						</form>
						</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<%
		}
		} else {
	%>
	<div class="row">
	<div class="col-sm-12 col-md-10 col-md-offset-1">
		<div class="panel panel-default">
			<div class="panel-body">

					<h4 class="text-center"><b>您尚未放入任何產品到購物車中。</b><br><b> <br>現在開始訂購您最喜歡的餐廳美食吧！</b></h4>
					

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

				</p>
			</div>
		</div>
		<%
			}
		%>
	</div>
	</div>


	<!--底部平台介紹=============================================================================================-->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />



	<!-- 	<script src="https://code.jquery.com/jquery.js"></script>    這個會抓不到-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/shoppingCart.js"></script>


</body>
</html>