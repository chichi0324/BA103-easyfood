<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.storecategory.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.fav.model.*"%>
<%@ page import="com.tools.tools"%>
<%
    StrVO strVO=(StrVO) request.getAttribute("strVO");    
    pageContext.setAttribute("strVO",strVO);
    
	StocaService stoSvc=new StocaService();	
	pageContext.setAttribute("stoSvc", stoSvc);
	
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	
	FavService favSvc=new FavService();
	List<FavVO> favList=new ArrayList<FavVO>();
	if(memVO!=null){
		favList=(List<FavVO>)favSvc.getoneFav(memVO.getMem_no());
	}
		
	List<String> favStrList=new ArrayList<String>();
	for(int i=0;i<favList.size();i++){
		favStrList.add(favList.get(i).getStr_no());
	}

%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/search/css/search_store.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>






        <div class="row">
          <div class="col-xs-12 col-sm-6">
             <p style="color:#7700BB"><b>首頁 > 搜尋店家(<%=strVO.getStr_name() %>) > 店家介紹</b>
             <%if(memVO!=null){ %>
             <a href="<%=request.getContextPath()%>/easyfood/front-end/class/member/fav.do?mem_no=<%=memVO.getMem_no() %>&str_no=${strVO.str_no}&action=insert_For_Fav">
             <img src="<%=request.getContextPath()%>/easyfood/front-end/class/search/images/heart/<%=(favStrList.contains(strVO.getStr_no())) ? "red" : "white" %>.png" id="heart" title="<%=(favStrList.contains(strVO.getStr_no())) ? "取消最愛" : "加入最愛" %>" onclick="switchFavorite()"></a>
             <%} %></p>
             <img src="<%=request.getContextPath()%>/tools/Mem_Red_Img?str_no=${strVO.str_no}" width="295px" align="left" class="search_store_img">
          </div>
          <div class="col-xs-12 col-sm-6">
            <h1 style="font-family:Microsoft JhengHei"><b>店家介紹</b></h1>
            <br>
            <h3>${strVO.str_name}</h3>
            <br>
            <b>${stoSvc.getOneStoca(strVO.stoca_no).stoca_name}</b><br>
                                 聯絡人: ${strVO.str_atn}<br>
                                 電話: ${strVO.str_tel}<br>
                                 地址: ${strVO.str_cou}${strVO.str_city}${strVO.str_addr}
          </div>
        </div>     

        <div style="margin-top: 20px">
                      介紹: ${strVO.str_note}
        </div>



<script>
function $id(id){
  return document.getElementById(id);
}
//加入最愛 或 取消最愛
function switchFavorite(){
  var heart = $id("heart");
  if( heart.title == "加入最愛"){
    heart.src = "<%=request.getContextPath()%>/easyfood/front-end/class/search/images/heart/red.png";
    heart.title = "取消最愛";
  }else{ //取消最愛
    heart.src = "<%=request.getContextPath()%>/easyfood/front-end/class/search/images/heart/white.png";
    heart.title = "加入最愛";
  }
}

</script> 



   

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>