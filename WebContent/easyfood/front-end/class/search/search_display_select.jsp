<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.storecategory.model.*"%>
<%@ page import="com.tools.tools"%>
<%
String address=(String)session.getAttribute("address");
String type=(String)session.getAttribute("type");
List<String> storeType=(List<String>)session.getAttribute("storeType");
String keyWord=(String)session.getAttribute("keyWord");

if(storeType==null){
storeType=new ArrayList<String>();	
}


StocaService stoSvc=new StocaService();
List<StocaVO> stocaList=stoSvc.getALL();
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/class/search/css/search.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<script
	src="<%=request.getContextPath()%>/easyfood/front-end/class/search/js/search.js"></script>


</head>
<body>






	<FORM class="form-horizontal" METHOD="get"
		ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do">
		<div class="col-xs-12 col-sm-3">
			<div>

				<label class="control-label" for="address"> <span
					class="glyphicon glyphicon-globe"></span> 依地址:
				</label>

				<div>
					<input class="form-control " type="text" name="address"
						id="address" value='<%=(address==null) ? "" : address%>' size="50">
				</div>
			</div>
			      <br>
                  <div>
                     <input type="button" value="開啟我的位置" onClick="myMap()" class="btn btn-primary " role="button"> 
                  </div>
                  <div id="distance" style="color: red"><b></b></div>

		</div>


        <div class="col-xs-12 col-sm-3">
			<div>
				<label class="control-label"> <span
					class="glyphicon glyphicon-cutlery"></span> 依取餐方式:
				</label> <br> 
				<input type="radio" name="type" value="FALSE" <%=("FALSE".equals(type)) ? "checked" : "" %>>自取<br>
				<input type="radio" name="type" value="TRUE" <%=("TRUE".equals(type))||(type==null) ? "checked" : "" %>>外送<br>
				<input type="radio" name="type" value="ALL" <%=("ALL".equals(type)) ? "checked" : "" %>>全部
			</div>
        </div>
        <div class="col-xs-12 col-sm-3">
			<div>
				<label class="checkbox"> <span
					class="glyphicon glyphicon-home"></span> 依店家類別:
				</label> 
				<%for (int i=0;i<stocaList.size();i++){ %>
				<input type="checkbox" name="store_style" value="<%=stocaList.get(i).getStoca_no() %>"  <%= (storeType.contains(stocaList.get(i).getStoca_no()))? "checked" :""%> ><%=stocaList.get(i).getStoca_name() %><br> 
				<%} %>
			</div>
		</div>
		<div class="col-xs-12 col-sm-3">
			<div>
				<label class="control-label" for="keyWord"> <span
					class=" glyphicon glyphicon-search"></span> 依關鍵字:
				</label>

				<div>
					<input class="form-control" type="text" name="keyWord" id="keyWord" value='<%=(keyWord==null) ? "" : keyWord%>'
						 placeholder="輸入美食關鍵字">
				</div>
				<br> 

        <input type="submit" class="btn btn-success btn-lg"
					role="button" value="查詢">  <input type="hidden" name="action"
					value="search_display">

			</div>
	</FORM>









	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type='text/javascript'>
function check_all(){ 
var checkItem = document.getElementsByName('store_style');
for(var i=0;i<checkItem.length;i++){
checkItem[i].checked=!checkItem[i].checked; }}
</script>

</body>
</html>