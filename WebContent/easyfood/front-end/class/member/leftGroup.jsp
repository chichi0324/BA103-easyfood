<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*, com.ord.model.*, com.str.model.*, java.util.*"%>
<%@ page import="com.adv.model.*"%>
<head> 
<link rel="stylesheet" type="text/css" 
href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member.css"> 
</head>
<%
MemVO memVO = (MemVO) session.getAttribute("memVO");
String Str = (String) session.getAttribute("showImgByBase64");
String showImgByBase64 = "data:image/jpg;base64," + Str;

AdvService advSvc=new AdvService();
List<AdvVO> advList=advSvc.getAllStrAuditOk();

strService strSvc=new strService();
%>

				<table class="table table-hover table-bordered"
					style='word-break: keep-all'>
					<thead></thead>
					<tbody>
						<ul>

							<tr>
								<td class="profile-img"><img class="profile-img-card"
									src='<%=(Str == null || Str.length() == 0) ? request.getContextPath()+"/easyfood/front-end/class/member/images/profile-img-card.png" : showImgByBase64%>' width=80% /></td>
							</tr>

							<tr	onclick="window.document.location='<%=request.getContextPath()%>/easyfood/front-end/class/member/member.jsp';">
								<td class="list member"><span
									class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;會員訂單</td>
							</tr>

							<tr	onclick="window.document.location='<%=request.getContextPath()%>/easyfood/front-end/class/member/member_information.jsp';">
								<td class="list informatiom"><span
									class="glyphicon glyphicon-user list"></span>&nbsp;&nbsp;會員資料</td align="center">
							</tr>

							<tr onclick="window.document.location='<%=request.getContextPath()%>/easyfood/front-end/class/member/member_favorite.jsp';">
								<td class="list favorite"><span
									class="glyphicon glyphicon-heart list"></span>&nbsp;&nbsp;最愛店家</td>
							</tr>
							<tr	onclick="window.document.location='<%=request.getContextPath()%>/easyfood/front-end/class/member/member_blog.jsp';">
								<td class="list blog"><i class="fa fa-file-text"
									style="font-size: 16px"></i>&nbsp;&nbsp;美食日記</td>
							</tr>
					</tbody>
					</ul>
				</table>
				
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>

					<h4 class="text-center">
						<b>店家美食廣告</b>
					</h4>
					<!-- Wrapper for slides -->
      <div style="width:99% ;height:99%;">
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
                <div class="item <%=(i==0) ? "active" : "" %>" >
                    <a href="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?action=enter_store_select&select=introduce&str_no=<%=advList.get(i).getStr_no()%>">
                    <img src="<%=request.getContextPath()%>/tools/Mem_Red_Img?adv_no=<%=advList.get(i).getAdv_no() %>" ></a>
                    <div class="container" aling="right">
                        <div class="carousel-caption"><b>
                            <h3><%=strSvc.getOneStr(advList.get(i).getStr_no()).getStr_name() %></h3>
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
				</div>