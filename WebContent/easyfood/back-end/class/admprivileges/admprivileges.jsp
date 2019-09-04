<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.admp.model.*"%>
<%@ page import="com.fea.model.*"%>
<%@ page import="com.tools.*"%>
<%
//避免沒有此權限直接使用超連結進來
AdmVO admVOLogin = (AdmVO) session.getAttribute("admVO_account");
List<String> featureList=(List<String>) session.getAttribute(admVOLogin.getAdm_acc());
if(!featureList.contains("FEA_0004")){           	   
	   response.sendRedirect(request.getContextPath() + "/easyfood/back-end/index.jsp");

}
//============================================================================

  String display=(String) request.getAttribute("display");
  String fea_man=(String) request.getAttribute("fea_man");

  FeaService feaSvc = new FeaService();
  List<FeaVO> feaList = feaSvc.getAll();
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
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	
  <style type="text/css">
   .auth{
    padding: 30px
   } 
  </style>
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

     <!--==========內容====================-->

	<div class="row second_row">
		<div id="second_content">

    <div >
      <ol class="breadcrumb">
        <li>
          <a href="<%=request.getContextPath()%>/easyfood/back-end/login.do?action=navbar&fea_no=index">首頁</a>
        </li>
        <li>
          <a href="#">管理員權限管理</a>
        </li>
      </ol>
   </div>

        <div role="tabpanel">
            <!-- 標籤面板：標籤區 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class='<%=(display==null) ? "active" : "" %>'>
                    <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">權限管理</a>
                </li>
                <li role="presentation" class='<%=("feature_add").equals(display) ? "active" : "" %>'>
                    <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">新增權限</a>
                </li>
            </ul>
        </div>
                    
        <div class="tab-content">

          <!-- tab1  權限管理 -->
          <div role="tabpanel" class="tab-pane <%=(display==null) ? "active" : "" %>" id="tab1">
              <%if("feature_update".equals(fea_man)){ %>
              <jsp:include page="admp_update.jsp" flush="true" />
              <%}else{ %>
              <jsp:include page="admp_all.jsp" flush="true" />
              <%} %>                  
          </div>

         <!-- tab2  新增權限 -->
         <div role="tabpanel" class="tab-pane <%=("feature_add").equals(display) ? "active" : "" %>" id="tab2">
              <div class="col-sm-2"></div>
              <div class="col-sm-8">
                 <br>
                 <form class="form-horizontal" action="<%=request.getContextPath()%>/easyfood/back-end/class/admprivileges/fea.do" method="get">
                 <h4>權限名稱 :</h4>
                 <input type="text" name="fea_name" value='' style="width: 300px;height: 33px" placeholder="請輸入權限名稱">  
                  <input type='hidden' value="add_fea" name="action">
                  <input type='submit' value='新增權限' class="btn btn-default">     
                  </form>
              </div>
              <br>
              
              <c:if test="${not empty errorMsgsFeaAdd}">
		            <font color='red'>請修正以下錯誤:
			            <ul>
				            <c:forEach var="message" items="${errorMsgsFeaAdd}">
					            <li>${message}</li>
				            </c:forEach>
			            </ul>
		            </font>
	            </c:if>

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