<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.admp.model.*"%>
<%@ page import="com.fea.model.*"%>
<%@ page import="com.tools.*"%>
<%
  FeaVO feaVO=(FeaVO)request.getAttribute("feaVO");
  List<String> AdmNOList =(List<String>)request.getAttribute("AdmNOList");
 
  AdmService admSvc=new AdmService();
  List<AdmVO> admAll=admSvc.getAll();
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
  content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>食在方便管理系統</title>
<link rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/easyfood/back-end/css/base.css">
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


                  <div class="col-sm-2"></div>
                  <div class="col-sm-8 auth">
                  <form action="<%=request.getContextPath()%>/easyfood/back-end/class/admprivileges/fea.do" method="get"> 
                     <table class="table table-striped table-hover" style= "word-break:break-all; ">
                         <thead>
                           <tr class="success">
                               <th>權限名稱</th>
                               <th>權限使用者</th>
                           </tr>
                         </thead>
                         <tbody>
                           <tr>
                               <td><%=feaVO.getFea_name() %></td>
                               <td> 
                                   <% for(int i=0;i<admAll.size();i++){ %>
                                   <input type="checkbox"  name="adm_no" value="<%= admAll.get(i).getAdm_no() %>" 
                                     <%= (AdmNOList.contains(admAll.get(i).getAdm_no()))? "checked" :""%> /><%=admAll.get(i).getAdm_name() %><br>
                                     <%} %>                                                                                                       
                               </td>
                           </tr>
                         </tbody>
                     </table>

                                 <div align="center">
                                   
                                   <input type='hidden' value="<%= feaVO.getFea_no() %>" name="fea_no">
                                   <input type='hidden' value="fea_update" name="action"> <input type='submit' value='送出修改' class="btn btn-default">
                                   </form>
                                 </div>


                      </div>



  <script src="https://code.jquery.com/jquery.js"></script>
  <script
    src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>