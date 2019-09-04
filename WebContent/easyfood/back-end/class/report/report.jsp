<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.str.model.*" %>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.admp.model.*"%>
<%@ page import="com.fea.model.*"%>
<%@ page import="com.ra.model.*" %>
<%
//避免沒有此權限直接使用超連結進來
AdmVO admVOLogin = (AdmVO) session.getAttribute("admVO_account");
List<String> featureList=(List<String>) session.getAttribute(admVOLogin.getAdm_acc());
if(!featureList.contains("FEA_0006")){           	   
	   response.sendRedirect(request.getContextPath() + "/easyfood/back-end/index.jsp");

}
//============================================================================

String str_rep=(String) request.getAttribute("str_rep");
String str_stat=(String) request.getAttribute("str_stat");
String ra_rev=(String) request.getAttribute("ra_rev");

String rep_rev=(String) request.getAttribute("rep_rev");

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
    .store_report{
      padding: 20px;
    }
    .blog_report{
      padding: 20px;
    }
    .store_report{
      padding: 20px;
    }
    .stas{
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
          <a href="#">檢舉管理</a>
        </li>
      </ol>
   </div>

        <div role="tabpanel">
            <!-- 標籤面板：標籤區 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class='<%=(display==null) ? "active" : "" %>'>
                    <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">店家狀態</a>
                </li>
                <li role="presentation" class='<%=("store").equals(display) ? "active" : "" %>'>
                    <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">店家檢舉</a>
                </li>
                <li role="presentation" class='<%=("blog").equals(display) ? "active" : "" %>'>
                    <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">美食日記檢舉</a>
                </li>
            </ul>
        </div>
                    
        <div class="tab-content">

                <!-- tab1  店家狀態 -->
                <div role="tabpanel" class="tab-pane <%=(display==null) ? "active" : "" %>" id="tab1">
                  <div class="store_report">
                    <form action="<%=request.getContextPath()%>/easyfood/back-end/class/report/report.do" method="get">
                      <span>店家被檢舉次數:</span>
                        <select name="str_rep">
                          <option value="3" <%=("3".equals(str_rep)) ? "selected":"" %>>3次</option>
                          <option value="2" <%=("2".equals(str_rep)) ? "selected":"" %>>2次</option>
                          <option value="1" <%=("1".equals(str_rep)) ? "selected":"" %>>1次</option>
                          <option value="0" <%=("0".equals(str_rep)) ? "selected":"" %>>0次</option>
                        </select>　 
                        <span>店家狀態:</span>
                        <select name="str_stat">
                          <option value="建置中" <%=("建置中".equals(str_stat)) ? "selected":"" %> >建置中</option>
                          <option value="營業中" <%=("營業中".equals(str_stat)) ? "selected":"" %> >營業中</option>
                          <option value="暫停中" <%=("暫停中".equals(str_stat)) ? "selected":"" %> >暫停中</option>
                          <option value="下架" <%=("下架".equals(str_stat)) ? "selected":"" %> >下架</option>
                        </select> 
                       <input type='hidden' value="display_str_stas" name="action">
                       　<input type='submit' value='查詢' class="btn btn-default">
                    </form>
                  </div>
                  <br>
                    <div class="stas">
                
                         <jsp:include page="report_str.jsp" flush="true" />
                    </div>
                </div>
                
                <!-- tab2  店家檢舉 -->
                <div role="tabpanel" class="tab-pane <%=("store").equals(display) ? "active" : "" %>" id="tab2">
                    <div class="store_report">
                      <form action="<%=request.getContextPath()%>/easyfood/back-end/class/report/report.do" method="get">
                        <span>店家檢舉:</span>
                          <select name="rep_rev">
                            <option value="待審核" <%=("待審核".equals(rep_rev)) ? "selected":"" %> >待審核</option>
                            <option value="通過" <%=("通過".equals(rep_rev)) ? "selected":"" %> >通過</option>
                            <option value="不通過" <%=("不通過".equals(rep_rev)) ? "selected":"" %> >不通過</option>
                          </select>
                        <input type='hidden' value="display_str_rep" name="action">
                       　                                       <input type='submit' value='查詢' class="btn btn-default"> 
                      </form>
                    </div>

                    <br>
                        <div class="stas">
                         
                            <jsp:include page="report_str_rep.jsp" flush="true" />
                        </div>
                </div>
                
                <!-- tab3  美食日記檢舉 -->
                <div role="tabpanel" class="tab-pane <%=("blog").equals(display) ? "active" : "" %>" id="tab3">
                    <div class="blog_report">
                      <form action="<%=request.getContextPath()%>/easyfood/back-end/class/report/report.do" method="get">
                        <span>日記檢舉:</span>
                          <select name="ra_rev">
                            <option value="待審核" <%=("待審核".equals(ra_rev)) ? "selected":"" %> >待審核</option>
                            <option value="通過" <%=("通過".equals(ra_rev)) ? "selected":"" %> >通過</option>
                            <option value="不通過" <%=("不通過".equals(ra_rev)) ? "selected":"" %> >不通過</option>
                          </select>
                        <input type='hidden' value="display_blog_stas" name="action">
                       　<input type='submit' value='查詢' class="btn btn-default"> 
                      </form>
                    </div>

                    <br>
                        <div class="stas">
                         
                            <jsp:include page="report_blog.jsp" flush="true" />
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