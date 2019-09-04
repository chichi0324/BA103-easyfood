<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*" %>
<%
AdmVO admVO= (AdmVO) request.getAttribute("admVO");
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
    .list-group-item {
   pointer-events: none;
   cursor: default;
}
  </style>
  </head>
	<body>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
		    
                <div id ="small" style="height: 250px">
                  <img src="<%=request.getContextPath()%>/easyfood/back-end/images/logo.png" width=100%  style="margin-top: 15px">
                </div>


		    <div class="list-group"><b>
			    <a href="#" class="list-group-item list-group-item-warning">會員管理</a>
			    <a href="#" class="list-group-item list-group-item-warning">店家管理</a>
			    <a href="#" class="list-group-item list-group-item-warning">管理員管理</a>
          <a href="#" class="list-group-item list-group-item-warning">管理員權限管理</a>
			    <a href="#" class="list-group-item list-group-item-warning">檢舉管理</a>
			    <a href="#" class="list-group-item list-group-item-warning">廣告管理</a>
		    </b></div>



			</div>
			<div class="col-xs-12 col-sm-9">
			     <br>

			     <!--右邊導覽列-->
			 <div class="row">   
			     <div class="container-fluid">                  
                 <nav class="navbar navbar-default" role="navigation">
                  
                  <div class="navbar-header">
                   <a href="#" class="navbar-brand" href="#" id="navbar-title"><b>食在方便管理系統</b></a>
                  </div>
                 <ul class="nav navbar-nav navbar-right">

                 </ul>   
                    
                </nav>
                </div>
             </div> 
     <!--內容-->

	<div class="row second_row">
		<div id="second_content">
    
    <div >
      <ol class="breadcrumb">
        <li>
          <a href="#">首頁</a>
        </li>
        <li>
          <a href="#">登入</a>
        </li>
      </ol>
   </div>
   
   	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

      <div class="col-xs-12 col-sm-3"></div>
      <div class="col-xs-12 col-sm-6">
                  <br><br>
                    <div class="login">                 
                      <form method="post" action="<%=request.getContextPath()%>/easyfood/back-end/login.do">
                        <div class="form-group">
                          <label for="adm_acc" class="cols-sm-2 control-label"><h4>帳號</h4></label>
                          <div class="cols-sm-10">
                            <div class="input-group">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                              <input type="text" class="form-control" name="adm_acc" id="adm_acc" value='<%=(admVO==null) ?"" :admVO.getAdm_acc() %>' placeholder="輸入你的帳號"/>
                            </div>
                          </div>
                        </div>

                        <div class="form-group">
                          <label for="adm_pas" class="cols-sm-2 control-label"><h4>密碼</h4></label>
                          <div class="cols-sm-10">
                            <div class="input-group">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                              <input type="password" class="form-control" name="adm_pas" id="adm_pas" value='<%=(admVO==null) ?"" :admVO.getAdm_pas() %>'  placeholder="輸入你的密碼"/>
                            </div>
                          </div>
                        </div>                    

                        <div class="form-group ">
                          <input type='submit' value='登入' class="btn btn-success btn-lg btn-block login-button">
                        </div>
                        <input type="hidden" name="action"	value="back_login">
                    </form>
                  </div>

      </div>
      <div class="col-xs-12 col-sm-3"></div>
     <br>
     <input type="button" name="" onclick="login1()" value="琪">
     <input type="button" name="" onclick="login2()" value="漾">
     <input type="button" name="" onclick="login3()" value="陽">
     <input type="button" name="" onclick="login4()" value="瑋">
     <input type="button" name="" onclick="login5()" value="辰">
		</div>
	</div>



			</div>

		</div>
	</div>



<jsp:include page="/easyfood/back-end/footer.jsp" flush="true" />


    <script type="text/javascript">
      function login1(){
        document.getElementById("adm_acc").value="CHICHI";
        document.getElementById("adm_pas").value="777777";
      }
      function login2(){
        document.getElementById("adm_acc").value="YUNG";
        document.getElementById("adm_pas").value="123456";
      }
      function login3(){
        document.getElementById("adm_acc").value="YUNGMIN";
        document.getElementById("adm_pas").value="654321";
      }
      function login4(){
        document.getElementById("adm_acc").value="BOBO";
        document.getElementById("adm_pas").value="987654";
      }
      function login5(){
        document.getElementById("adm_acc").value="CHENG";
        document.getElementById("adm_pas").value="456789";
      }
    </script>		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>