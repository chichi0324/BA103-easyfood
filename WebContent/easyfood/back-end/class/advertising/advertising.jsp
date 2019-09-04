<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="java.util.*"%>
<%@ page import="com.adv.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.admp.model.*"%>
<%@ page import="com.fea.model.*"%>
<%@ page import="com.tools.*"%>
<%
//避免沒有此權限直接使用超連結進來
AdmVO admVOLogin = (AdmVO) session.getAttribute("admVO_account");
List<String> featureList=(List<String>) session.getAttribute(admVOLogin.getAdm_acc());
if(!featureList.contains("FEA_0001")){               
     response.sendRedirect(request.getContextPath() + "/easyfood/back-end/index.jsp");

}
//============================================================================
%>
<jsp:useBean id="advSvc" scope="page" class="com.adv.model.AdvService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
          <a href="#">廣告管理</a>
        </li>
      </ol>
      </div>
        <div role="tabpanel">
            <!-- 標籤面板：標籤區 -->
            
            
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">待審核廣告</a>                 
                </li>
                <li role="presentation">
                    <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">上架廣告總覽</a>  
                </li>
            </ul>
            
            <!-- 標籤面板：內容區 -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="tab1">
                <c:if test="${advSvc.getAllStrAudit().size()==0}">
                <br>
                <div style="color: red" class="text-center">目前無待審核廣告</div>
                </c:if>
                	<c:forEach var="advVO1" items="${advSvc.getAllStrAudit()}">	
                	<div class="aaatop">
                		<div>
                		    <br>
							<div class="col-xs-12 col-sm-4"><h5>開始時間:${advVO1.adv_str }</h5></div>
			                <div class="col-xs-12 col-sm-3"><h5>結束時間:${advVO1.adv_end }</h5></div>
			                <div class="col-xs-12 col-sm-3">
			                	<form style="display:inline-block" METHOD="post" ACTION="<%=request.getContextPath()%>/adv/adv_in.do">
			                		<input type="hidden" name="adv_no" value="${advVO1.adv_no}">
			                		<input type="hidden" name="adv_sta" value="通過">
                					<input type="hidden" name="action"value="Update_For_adv">
			                		<button type="submit" class="btn btn-primary btn-sm">通過</button>
			                	</form>
			                	<form style="display:inline-block" METHOD="post" ACTION="<%=request.getContextPath()%>/adv/adv_in.do">
			                		<input type="hidden" name="adv_no" value="${advVO1.adv_no}">
			                		<input type="hidden" name="adv_sta" value="不通過">
                					<input type="hidden" name="action"value="Update_For_adv">
			                		<button type="submit" class="btn btn-danger btn-sm">不通過</button>
			                	</form>	              
	                    </div>

                        </div> 
                		<img src="<%=request.getContextPath()%>/tools/Adv_Red_Img?str_no=${advVO1.str_no}&adv_no=${advVO1.adv_no}" style="width:550px">
						     
                    </div>
                    </c:forEach>
                </div>
                
                <div role="tabpanel" class="tab-pane" id="tab2">
             	<c:if test="${advSvc.getAllStrAuditOk().size()==0}">
                <br>
                <div style="color: red" class="text-center">目前無上架廣告</div>
                </c:if>
             	<c:forEach var="advVO2" items="${advSvc.getAllStrAuditOk()}">
                	<div class="aaatop">
                		<div>
                		    <br>
							<div class="col-xs-12 col-sm-4"><h5>開始時間:${advVO2.adv_str }</h5></div>
			                <div class="col-xs-12 col-sm-3"><h5>結束時間:${advVO2.adv_end }</h5></div>
			                <div class="col-xs-12 col-sm-3">
			                	
	                    </div>
                		<img src="<%=request.getContextPath()%>/tools/Adv_Red_Img?str_no=${advVO2.str_no}&adv_no=${advVO2.adv_no}" style="width:550px">
						

                        </div>      
                    </div>	
                	
                	</c:forEach> 
                
                </div>
                
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