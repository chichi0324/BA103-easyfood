<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.adm.model.*" %>
<%@ page import="com.admp.model.*" %>
<%@ page import="com.fea.model.*" %>
<%
//避免沒有此權限直接使用超連結進來
AdmVO admVOLogin = (AdmVO) session.getAttribute("admVO_account");
List<String> featureList=(List<String>) session.getAttribute(admVOLogin.getAdm_acc());
if(!featureList.contains("FEA_0005")){           	   
	   response.sendRedirect(request.getContextPath() + "/easyfood/back-end/index.jsp");

}
//============================================================================


String display=(String) request.getAttribute("display");
String adm_select=(String) request.getAttribute("adm_select");

AdmVO admVO = (AdmVO) request.getAttribute("admVO");


%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>食在方便管理系統</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/back-end/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/back-end/class/administrator/css/administrator.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
		    
              <!--左邊選項-->
               <jsp:include page="/easyfood/back-end/leftGroup.jsp" flush="true" />


			</div>
	<div class="col-xs-12 col-sm-9">
			     <br>

			     <!--右邊導覽列-->
			 <div class="row">   

                 <jsp:include page="/easyfood/back-end/navbar.jsp" flush="true" />

             </div> 
     <!--內容-->

	<div class="row second_row">
		<div id="second_content">

    <div >
      <ol class="breadcrumb">
        <li>
          <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=index">首頁</a>
        </li>
        <li>
          <a href="#">管理員管理</a>
        </li>
      </ol>
   </div>

        <div role="tabpanel">
            <!-- 標籤面板：標籤區 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class='<%=(display==null) ? "active" : "" %>'>
                    <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">查詢管理員</a>
                </li>
                <li role="presentation" class='<%=("adm_add").equals(display) ? "active" : "" %>'>
                    <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">新增管理員</a>
                </li>
                <li role="presentation" class='<%=("adm_all").equals(display) ? "active" : "" %>'>
                    <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">所有管理員</a>
                </li>
            </ul>
        </div>
                    
        <div class="tab-content">

                <!-- tab1  管理管理員 -->
                <div role="tabpanel" class="tab-pane <%=(display==null) ? "active" : "" %>" id="tab1">
                  <div>
                    <br>
                    <div class="col-sm-offset-2 col-sm-10">
                      <form class="form-horizontal" action="<%=request.getContextPath()%>/easyfood/back-end/class/administrator/adm.do" method="get">
                         <h4>查詢員工資料 :</h4>
                         <input type="text" name="adm_acc" value='<%=(admVO==null) ? "" : admVO.getAdm_acc()%>' style="width: 300px;height: 33px" placeholder="請輸入員工帳號">  
                       <input type='hidden' value="display_adm" name="action">
                       　                                    <input type='submit' value='查詢' class="btn btn-default">     
                      </form>
                    </div>
                  </div>
                  <!-- 查詢結果-->
                  <%if("update".equals(adm_select)){ %>
                  <jsp:include page="adm_update.jsp" flush="true" />
                  <%}else{ %>
                  <jsp:include page="adm_one.jsp" flush="true" />
                  <%} %>
                </div>

                <!-- tab2  新增管理員 -->
                <div role="tabpanel" class="tab-pane <%=("adm_add").equals(display) ? "active" : "" %>" id="tab2">

                <div class="col-xs-12 col-sm-2"></div>
                <div class="col-xs-12 col-sm-8">
                 
                 <jsp:include page="adm_add.jsp" flush="true" />

                </div>
                <div class="col-xs-12 col-sm-2"></div>

          
                </div>
         <!-- tab3  所有管理員 -->
                <div role="tabpanel" class="tab-pane <%=("adm_all").equals(display) ? "active" : "" %>" id="tab3">
                  <div>
                       <jsp:include page="adm_all.jsp" flush="true" />
                  </div>
                </div>
       </div>
	</div>



			</div>

		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="/easyfood/back-end/footer.jsp" flush="true" />


		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>