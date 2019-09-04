<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.dish.model.*"%>
<%@ page import="com.dishclass.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pro.model.*"%>
<%@ page import="com.tools.tools"%>
<%
StrVO strVO = (StrVO) request.getAttribute("strVO");

  String menu_select = (String) request.getAttribute("menu_select");
  String dcla_no = (String) request.getAttribute("dcla_no");
  

  String dcla_name="所有餐點";
  List<DishVO> list =null;
  
  if("all_menu".equals(menu_select)){
	  dcla_name="所有餐點";
	  String str_no = (String) request.getAttribute("str_no");
	  DishService dishSvc= new DishService();
	  list=dishSvc.getByStrStatus(str_no, "販售中");
  }else{
	  DclaService dclaSvc = new DclaService();
	  DclaVO dclaVO=dclaSvc.getOneDcla(dcla_no);
	  dcla_name=dclaVO.getDcla_name();
	  list = (List<DishVO>) request.getAttribute("dishList");  
  }

  pageContext.setAttribute("list", list);
  
  ProService proSvc=new ProService();
  List<ProVO> proList=proSvc.getStrPro(strVO.getStr_no());
  
  List<String> dclaNoList=new ArrayList<String>();
  if(proList.size()>0){
	  for(int i=0;i<proList.size();i++){
		  if("class".equals(proList.get(i).getPro_cat())  
				  && proList.get(i).getPro_str().before(tools.todayTime()) 
				  && proList.get(i).getPro_end().after(tools.todayTime()) ){
			  dclaNoList.add(proList.get(i).getDcla_no1());
			  dclaNoList.add(proList.get(i).getDcla_no2());
		  }	  
	  }
  }
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
  href="<%=request.getContextPath()%>/easyfood/front-end/class/search/css/search_store_menu.css">

<!--[if lt IE 9]>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>





  <!--第二列右邊第一排==========================================-->
  <div class="row">
    <div class="col-xs-12 col-sm-12">
      <p class="storeNav">
        <b>首頁 > 搜尋店家(<%=strVO.getStr_name() %>) > 餐點與訂購 > <%= dcla_name%></b>
      </p>
      <% if(proList.size()>0){ %>
      <p class="discount_title">
          <b>
        <% for(int i=0;i<proList.size();i++){%>
           <% if("class".equals(proList.get(i).getPro_cat()) && proList.get(i).getPro_end().after(tools.todayTime()) ){ %>
              <%=proList.get(i).getPro_str() %>至<%=proList.get(i).getPro_end() %>　
              <%=tools.getDclaName(proList.get(i).getDcla_no1()) %>+<%=tools.getDclaName(proList.get(i).getDcla_no2()) %>,打<%=(int)(proList.get(i).getPro_dis()*10) %>折<br>
           <%}else if("money".equals(proList.get(i).getPro_cat()) && proList.get(i).getPro_end().after(tools.todayTime()) ){ %>
              <%=proList.get(i).getPro_str() %>至<%=proList.get(i).getPro_end() %>　                        
                                       總金額滿<%=proList.get(i).getPro_mon() %>元,打<%=(int)(proList.get(i).getPro_dis()*10) %>折<br>
           <%} %>
        <%}%>
          <br> 現正優惠中!!!!!!要買要快~~
          </b>
      </p>
      <% }%>
      <h2 style="font-family: Microsoft JhengHei">
        <b><center><%= dcla_name%></center></b>
      </h2>
    </div>
  </div>
  <br>


  <%
    if (list.size() == 0) {
  %>
  <br>
  <div style="color: red" class="text-center">餐點建置中...</div>
  <%
    } else {
  %>




  <!--第二列右邊第二排==========================================-->
  <div class="row">

   <% for(int i=0;i<list.size();i++){ %>
      <div class="col-xs-12 col-sm-4 food">
        <table class="text-center">
          <tr>
            <th class="text-center"><h5>
                <b><%= list.get(i).getDish_name() %></b>
              </h5></th>
          </tr>
          <tr>
  

            <td><img
              src="<%=request.getContextPath()%>/tools/Mem_Red_Img?dish_no=<%= list.get(i).getDish_no() %>"
               class="food_img" style="width:250px;height:180px"></td>
               <!-- 大螢幕 style="width:250px;height:180px" -->
          </tr>
          <tr>
            <td>
                <button class="buttonAdd" id="btadd<%=i%>" onclick="addCount<%=i%>()">+</button> 數量: <input type="text" id="count<%=i%>" size="1" value="1" class="count"><button class="buttonLess" id="btless<%=i%>"  onclick="lessCount<%=i%>()">-</button>
            </td>
          </tr>
          <tr>
            <td>價錢: <%= list.get(i).getDish_price() %>元</td>
          </tr>
          <tr>
            <td>
              <% if(dclaNoList.contains(list.get(i).getDcla_no())){ %>
              <img src="<%=request.getContextPath()%>/easyfood/front-end/class/search/images/discount.png" align="left" style="width:40px;height:31px">
              <%} %>
            <button type="button" class="btn btn-danger btn-xs carButton"
               id="submit<%=i%>" >加入便當盒</button></td>
          </tr>
        </table>
      </div>
      

<!--*************************************AJAX加入購物車部分開始**************************************** -->
				<!--  琪,23行的"#submit1"幫改成動態取得'加入購物車'按鈕的id(217行的id), 感恩 -->
				<!--  琪,236行幫改成動態取得dish_no, 感恩 -->
				<!--  琪,238行幫改成動態取得ord_type(外送or自取), 感恩 -->
				<!--  琪,235行沒改也可跑,改了反而不能跑 -->
				<script type="text/javascript">
				$("#submit<%=i%>").click(function() {
					$.ajax({
						type : "POST",
						url : "cart.do",
						data : {
							dish_no : "<%=list.get(i).getDish_no() %>",
							action : "ADD",
							quantity : $("#count<%=i%>").val()
						}
					})
				});
				</script>		

    

<script>
function $id(id){return document.getElementById(id);}
function addCount<%=i%>(){addCount(<%=i%>);}
function lessCount<%=i%>(){lessCount(<%=i%>);}
function addCount(i){
  var count=parseInt( $id("count"+i).value);
  if(count==10){$id("count"+i).value=10;
     }else{$id("count"+i).value=count+1; }
}
function lessCount(i){
  var count=parseInt( $id("count"+i).value);
   if(count==1){$id("count"+i).value=1;
      }else{$id("count"+i).value=count-1;}               
}
</script>



    <%} %>

  </div>




  <%
    }
  %>




  <script src="https://code.jquery.com/jquery.js"></script>
  <script
    src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>