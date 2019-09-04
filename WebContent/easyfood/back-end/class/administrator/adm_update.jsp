<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.adm.model.*" %>
<%@ page import="com.admp.model.*" %>
<%@ page import="com.fea.model.*" %>
<%
AdmVO admVO_update = (AdmVO) request.getAttribute("admVO_update");

List<String> feaNoList=new ArrayList<String>();
List<String> feaList=(List<String>) request.getAttribute("feaNoList");
if(feaList!=null){
	feaNoList=feaList;
}

  FeaService feaSvc=new FeaService();
  List<FeaVO> feaAll=feaSvc.getAll();
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

	<c:if test="${not empty errorMsgsUpdate}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgsUpdate}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>



<div class="col-xs-12 col-sm-1" ></div>
       <div class="col-xs-12  col-sm-8" >


        <div class="main-login main-center">
          <form class="form-horizontal" method="get" action="<%=request.getContextPath()%>/easyfood/back-end/class/administrator/adm.do">           
            <div class="form-group">
              <label for="adm_acc" class="control-label col-sm-2 ">帳號:</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" name="adm_acc" id="adm_acc"
                    placeholder="輸入帳號"  value="<%=(admVO_update==null) ? "" : admVO_update.getAdm_acc() %>"/>
                </div>
            </div>

            <div class="form-group">
              <label for="adm_name" class="control-label col-sm-2">姓名:</label>
              <div class="cols-sm-10">
                <div class="col-sm-10">
                  <input type="text" class="form-control" name="adm_name" id="adm_name"
                    placeholder="輸入姓名" value="<%=(admVO_update==null) ? "" : admVO_update.getAdm_name() %>"/>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="adm_pho" class="control-label col-sm-2">電話:</label>
              <div class="cols-sm-10">
                <div class="col-sm-10">
                  <input type="tel" class="form-control" name="adm_pho" id="adm_pho"
                    placeholder="輸入手機號碼" value="<%=(admVO_update==null) ? "" : admVO_update.getAdm_pho() %>"/>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="adm_mail" class="control-label col-sm-2">信箱:</label>
              <div class="cols-sm-10">
                <div class="col-sm-10">
                  <input type="email" class="form-control" name="adm_mail" id="adm_mail"
                    placeholder="輸入email" value="<%=(admVO_update==null) ? "" : admVO_update.getAdm_mail() %>"/>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="adm_adrs" class="control-label col-sm-2">地址:</label>
              <div class="cols-sm-10">
                <div class="col-sm-10">
                  <input type="text" class="form-control" name="adm_adrs" id="adm_adrs"
                    placeholder="輸入地址" value="<%=(admVO_update==null) ? "" : admVO_update.getAdm_adrs() %>"/>
                </div>
              </div>
            </div>

            <div class="form-group">
              <label for="adm_pos" class="control-label col-sm-2">職位:</label>
              <div class="cols-sm-10">
                <div class="col-sm-10">
                    <select name="adm_pos" id="adm_pos">
                      <option value="請選擇" <%= (admVO_update!=null)&& ("請選擇".equals(admVO_update.getAdm_pos()))? "selected" :""%>>請選擇</option>
                      <option value="會員管理員" <%=(admVO_update!=null)&& ("會員管理員".equals(admVO_update.getAdm_pos()))? "selected" :""%>>會員管理員</option>
                      <option value="店家管理員" <%=(admVO_update!=null)&& ("店家管理員".equals(admVO_update.getAdm_pos()))? "selected" :""%>>店家管理員</option>
                      <option value="員工管理員" <%=(admVO_update!=null)&& ("員工管理員".equals(admVO_update.getAdm_pos()))? "selected" :""%>>員工管理員</option>
                      <option value="檢舉管理員" <%=(admVO_update!=null)&& ("檢舉管理員".equals(admVO_update.getAdm_pos()))? "selected" :""%>>檢舉管理員</option>
                      <option value="廣告管理員" <%=(admVO_update!=null)&& ("廣告管理員".equals(admVO_update.getAdm_pos()))? "selected" :""%>>廣告管理員</option>
                      <option value="總經理" <%=(admVO_update!=null)&& ("總經理".equals(admVO_update.getAdm_pos()))? "selected" :""%>>總經理</option>
                  </select>　
                </div>
              </div>
            </div>  

            <div class="form-group">
              <label for="fea_no" class="control-label col-sm-2">權限:</label>
               <div class="cols-sm-12">
                
                <table>
                  <tr>
                    <td>
                       <% for(int i=0;i<feaAll.size();i++){ %>
                       <input type="checkbox"  name="fea_no" value="<%= feaAll.get(i).getFea_no() %>" <%= (feaNoList.contains(feaAll.get(i).getFea_no()))? "checked" :""%> /><%= feaAll.get(i).getFea_name() %>　<br>
                       <%} %>
                    </td>
                  </tr>
                </table>

          　　　　　&nbsp;&nbsp;(<input type="checkbox"  name="feature_all"  onclick='check_all(this,"fea_no")' >全選)　
      
              </div>

            </div> 

                <div class="login-register" align="center">
                　　　<br>
                    <input type="hidden" name="adm_no" value="<%=(String) request.getAttribute("adm_no")%>">
                    <input type="hidden" name="action" value="adm_update">
                       　<input type='submit' value='送出修改' class="btn btn-success">  
                </div>
          </form>
        </div>


    </div>




    
    <script type="text/javascript"> 
        function check_all(obj,cName) 
        { 
            var checkboxs = document.getElementsByName(cName); 
            for(var i=0;i<checkboxs.length;i++){
              //等於all的checked的狀態
              checkboxs[i].checked = obj.checked;
            } 
        } 

    </script> 		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>