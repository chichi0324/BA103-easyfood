<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.admp.model.*"%>
<%@ page import="com.fea.model.*"%>
<%@ page import="com.tools.*"%>
<%
//避免沒有此權限直接使用超連結進來
AdmVO admVOLogin = (AdmVO) session.getAttribute("admVO_account");
List<String> featureList=(List<String>) session.getAttribute(admVOLogin.getAdm_acc());
if(!featureList.contains("FEA_0002")){           	   
	   response.sendRedirect(request.getContextPath() + "/easyfood/back-end/index.jsp");

}
//============================================================================

String mem_vio=(String) request.getAttribute("mem_vio");
String mem_stas=(String) request.getAttribute("mem_stas");

String display=(String) request.getAttribute("display");
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
    .report_times{
      padding: 20px;
    }
    .mem_stas{
      padding: 10px
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
          <a href="#">會員管理</a>
        </li>
      </ol>
   </div>

           <div role="tabpanel">
            <!-- 標籤面板：標籤區 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class='<%=(display==null) ? "active" : "" %>'>
                    <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">違規會員</a>
                </li>
                <li role="presentation" class='<%=("display_all").equals(display) ? "active" : "" %>'>
                    <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">會員列表</a>
                </li>
            </ul>
        </div>
                    
        <div class="tab-content">

                <!-- tab1  違規會員 -->
                <div role="tabpanel" class="tab-pane <%=(display==null) ? "active" : "" %>" id="tab1">
                  <div class="report_times">
                    <form action="<%=request.getContextPath()%>/easyfood/back-end/class/member/memVio.do" method="get">
                      <span>違規未取餐次數:</span>
                        <select name="mem_vio">
                          <option value="3" <%=("3".equals(mem_vio)) ? "selected":"" %> >3次以上</option>
                          <option value="2" <%=("2".equals(mem_vio)) ? "selected":"" %> >2次</option>
                          <option value="1" <%=("1".equals(mem_vio)) ? "selected":"" %> >1次</option>
                          <option value="0" <%=("0".equals(mem_vio)) ? "selected":"" %> >0次</option>
                        </select>　
                        <span>會員狀態:</span>
                        <select name="mem_stas">
                          <option value="正常" <%=("正常".equals(mem_stas)) ? "selected":"" %> >正常</option>
                          <option value="停權" <%=("停權".equals(mem_stas)) ? "selected":"" %>>停權</option>
                        </select> 
                       <input type='hidden' value="display_mem_stas" name="action">
                       　                                    <input type='submit' value='查詢' class="btn btn-default">
                    </form>
                  </div>
                  <br>
                  <div class="mem_stas">
                
                  <jsp:include page="mem_vio.jsp" flush="true" />
                  </div>
                </div>

                <!-- tab2  所有會員列表 -->
                <div role="tabpanel" class="tab-pane <%=("display_all").equals(display) ? "active" : "" %>" id="tab2">

                    <br>
                        <div class="mem_stas">
                           <!-- 所有會員 -->
                           <jsp:include page="mem_all.jsp" flush="true" />
                 
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