<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.storecategory.model.*"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.adv.model.*"%>
<%@ page import="com.tools.*"%>
<%@ page import="java.util.*"%>
<%
StocaService stoSvc=new StocaService();
List<StocaVO> stocaList=stoSvc.getALL();
pageContext.setAttribute("stocaList",stocaList);

AdvService advSvc=new AdvService();
List<AdvVO> advList=advSvc.getAllStrAuditOk();

strService strSvc=new strService();
pageContext.setAttribute("strSvc", strSvc);

tools tool=new tools();
pageContext.setAttribute("tools",tool);

%>

<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StrService" />
<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService" />
<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="short Icon" type="images/x-icon" href="<%=request.getContextPath()%>/easyfood/front-end/images/logo.png">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">

</style>
</head>
<body>


	<!--導覽列=============================================================================================-->
<jsp:include page="/easyfood/front-end/navbar.jsp" flush="true" />


	<!--標題搜尋列=============================================================================================-->
	<section class="zero">
		<div class="container-fluid ">
			<div class="row">

				<!--      <div class="col-xs-12 col-sm-offset-3 col-sm-2">
          <img src="images/logo.png"  id="imgTop" class="pull-left">  
     </div> -->
				<div class="col-xs-12  col-sm-12">
					<div class="text-center title">
						<h1>
							<span
								style="font-family: Microsoft JhengHei; font-size: 2.3cm; margin-top: 100px; font-weight: bolder;">食在方便</span>
							<i style="font-size: 1.3cm">easyfood</i>
						</h1>
					</div>
				</div>
				<!--      <div class="col-xs-12 col-sm-5">

     </div> -->
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 ">
					<div class="wrap-headline " id="searchContent">
						<br>
						<div class="text-center title_content">
							<i> 無論你想品嚐什麼美食，食在方便都能立刻為您搜尋..... 訂購您渴望的美食~~ </i>
						</div>
							<FORM class="form-horizontal" METHOD="get" ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do">
						<h4 class="text-center"
							style="font-weight: bold; font-family: Microsoft JhengHei; font-size: 2em;">
							<span>美食店家地區搜尋:</span> <select style="width: 200px;" name="address">
								<option value="台北市">台北市</option>
								<option value="新北市">新北市</option>
								<option value="桃園市">桃園市</option>								
								<option value="台中市">台中市</option>
								<option value="台南市">台南市</option>
								<option value="高雄市">高雄市</option>

								<option value="基隆市">基隆市</option>
								<option value="新竹市">新竹市</option>
								<option value="嘉義市">嘉義市</option>

								<option value="新竹縣">新竹縣</option>
								<option value="苗栗縣">苗栗縣</option>
								<option value="彰化縣">彰化縣</option>
								<option value="南投縣">南投縣</option>
								<option value="雲林縣">雲林縣</option>
								<option value="嘉義縣">嘉義縣</option>
								<option value="屏東縣">屏東縣</option>
								<option value="宜蘭縣">宜蘭縣</option>
								<option value="花蓮縣">花蓮縣</option>
								<option value="臺東縣">臺東縣</option>
								<option value="澎湖縣">澎湖縣</option>
							</select> 
							<br> <br> <input type="radio" name="type" value="FALSE"><span>自取</span>　
							          <input type="radio" name="type" value="TRUE" checked><span>外送</span>
                           
                          <div style="display: none;">
				          <%for (int i=0;i<stocaList.size();i++){ %>
				          <input type="checkbox" name="store_style" value="<%=stocaList.get(i).getStoca_no() %>"><%=stocaList.get(i).getStoca_name() %><br> 
				          <%} %>
			              </div>
			              
                           <input type="hidden" name="keyWord">
                           
                           　<input type="submit" class="btn btn-primary btn-lg" role="button" value="查詢">           
                           <input type="hidden" name="action" value="search_display">
						</h4>
                        </FORM>
					</div>
				</div>
			</div>

		</div>
	</section>



	<!--第一列=============================================================================================-->
	<br>
	<br>
	<div class="text-center">
		<h1 class="big_title">
			<b>美食特區</b>
		</h1>
	</div>

	<div class="container">
		<div class="row">

			<div class="col-xs-12 col-sm-4">
				<div class="gallery">
					<a href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=member"> <img
						src="<%=request.getContextPath()%>/easyfood/front-end/images/index/index_member.jpg" class="title_image"
						width="300" height="250">
					</a>
					<div class="desc">
						<h3>會員專區</h3>
						<p>
							各位饕客們,快來加入我們!!! <BR> 可享有店家的優惠折扣唷~~
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-4">
				<div class="gallery">
					<a target="_blank" href="<%=request.getContextPath()%>/easyfood/front-end/class/store/str_login.jsp"> <img
						src="<%=request.getContextPath()%>/easyfood/front-end/images/index/index_store.jpg" class="title_image" width="300"
						height="250">
					</a>
					<div class="desc">
						<h3>店家專區</h3>
						<p>
							提供一個可以建置自己店家的平台, <BR> 客製化的服務~~
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-4">
				<div class="col-xs-12 col-sm-4">
					<div class="gallery">
						<a href="#"> <img
							src="<%=request.getContextPath()%>/easyfood/front-end/images/index/QrCode.png" class="title_image" width="300"
							height="300">
						</a>
						<div class="desc">
							<h3>
								<h3>QRCode掃描和App下載</h3>
							</h3>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>



	<!--第二列=============================================================================================-->
	<br>
	<br>
	<div class="text-center">
		<h1 class="big_title">
			<b>美食記錄</b>
		</h1>
	</div>

	<div class="container">
		<div class="row ">

			<div class="col-xs-12 col-sm-4 ">
				<div class="second">
					<table class="table table-striped table-hover"
						style='word-break: keep-all'>
						<thead>
							<tr class="danger">
								<th colspan="2">
									<h4 class="text-center white-text">
										<b>排行榜</b>
									</h4>
								</th>
								<th class="text-right"><a href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=rank"
									class="btn btn-danger" align="right">更多</a></th>
							</tr>
						</thead>
						<tbody>
							<ul>
							<c:forEach var="rank_week" varStatus="rank" items="${rank_week}" end="2">
								<tr>
									<td>${rank.count}.</td>
									<td><img class="abc img-responsive " src="<%=request.getContextPath()%>/tools/Mem_Red_Img?str_no=${rank_week.getKey()}"
									     style="width:80px;height:55px"  ></td>
									<td><a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=introduce&str_no=${rank_week.getKey()}" >
									     ${storeSvc.getOneNameStoca(rank_week.getKey()).str_name}
									    </a>
									</td>
								</tr>
							</c:forEach>
						    </ul>
						</tbody>
						
					</table>
				</div>
			</div>




			<div class="col-xs-12 col-sm-4">
				<div class="second">
					<table class="table table-striped table-hover"
						style='word-break: keep-all'>
						<thead>
							<tr class="info">
								<th colspan="2">
									<h4 class="text-center white-text">
										<b>四星級以上評價</b>
									</h4>
								</th>
								<th class="text-right"></th>
							</tr>
						</thead>
						<tbody>
							<ul>
							     <c:forEach var="ordVO" items="${ordSvc.getAllByStar()}" end="4" varStatus="s">
								<tr>
									<td>${s.count}.</td>
									<td><a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=comment&str_no=${ordVO.str_no}">${strSvc.getOneStr(ordVO.str_no).str_name}</a></td>
									<td><img src="<%=request.getContextPath()%>/easyfood/front-end/images/index/stars/star${ordVO.ord_ev}.png" width="100px"
										align="left"></td>
								</tr>
								</c:forEach>
						</tbody>
						</ul>
					</table>
				</div>
			</div>


<jsp:useBean id="blogSvc" scope="page" class="com.blog.model.BlogService" />

			<div class="col-xs-12 col-sm-4 ">
				<div class="second">
					<table class="table table-striped table-hover"
						style='word-break: keep-all'>
						<thead>
							<tr class="success">
								<th colspan="2">
									<h4 class="text-center white-text">
										<b>美食日記分享</b>
									</h4>
								</th>
								<th class="text-right"><a href="<%=request.getContextPath()%>/easyfood/front-end/index.do?action=navbar&title=blog"
									class="btn btn-success">更多</a></th>
							</tr>
						</thead>
						<tbody>
							<ul>
							    <c:forEach var="blogVO" items="${blogSvc.all}" end="4" varStatus="s">
								<tr>
									<td>${s.count}.</td>
									<td><a href="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do?action=blog_display_all&bl_no=${blogVO.bl_no}"
				                        class="blog_left_title">${blogVO.bl_name}</a></td>
				                    <td>${tools.getMemName(blogVO.mem_no)}</td>
								</tr>
								</c:forEach>					
						</ul>
					</tbody>
					</table>
				</div>
			</div>

		</div>

	</div>




	<!--第三列=============================================================================================-->
	<br>
	<br>
	<div class="text-center">
		<h1 class="big_title">
			<b>美食廣告</b>
		</h1>
	</div>

   <div class="container">
     <div id="carousel-id" class="carousel slide" data-ride="carousel">
            <!-- 幻燈片小圓點區 -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-id" data-slide-to="0" class="active"></li>
                <% for(int i=0;i<advList.size();i++){ %>
                <li data-target="#carousel-id" data-slide-to="<%=i+1 %>"></li>
                <%} %>
            </ol>
            <!-- 幻燈片主圖區 -->
            <div class="carousel-inner">
              <% for(int i=0;i<advList.size();i++){ %>
                <div class="item <%=(i==0) ? "active" : "" %>">
                    <a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=introduce&str_no=<%=advList.get(i).getStr_no()%>">
                    <img src="<%=request.getContextPath()%>/tools/Mem_Red_Img?adv_no=<%=advList.get(i).getAdv_no() %>" style="width:1200px ;height:500px"></a>
                    <div class="container" aling="right">
                        <div class="carousel-caption"><b>
                            <h1><%=strSvc.getOneStr(advList.get(i).getStr_no()).getStr_name() %></h1>
                            <p><%=advList.get(i).getAdv_str() %>至<br><%=advList.get(i).getAdv_end() %></p>                           
                        </b></div>
                    </div>
                </div>
                <%} %>
            </div>
            <!-- 上下頁控制區 -->
            <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
            <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
        </div>
   </div>




	<!--第四列=============================================================================================-->
	<br>
	<br>
	<div class="text-center">
		<h1 class="big_title">
			<b>全台美食</b>
		</h1>
	</div>



	<div class="container team1">

		<div class="row ">
             <c:forEach var="stocaVO" items="${stocaList}" >
			<div class="col-xs-12 col-sm-4">
				<div class="teamInside text-center">
					<a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?address=&type=ALL&store_style=${stocaVO.stoca_no}&keyWord=&action=search_display">
					<img src="<%=request.getContextPath()%>/tools/Mem_Red_Img?stoca_no=${stocaVO.stoca_no}" class="img-thumbnail"
						 style="width:320px;height:220px"></a>
					<h4 class="text-info">
						<b>${stocaVO.stoca_name}</b>
					</h4>
				</div>
			</div>
			</c:forEach>


		</div>

	</div>



	<!--底部平台介紹=============================================================================================-->


<jsp:include page="/easyfood/front-end/footer.jsp" flush="true" />






	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>