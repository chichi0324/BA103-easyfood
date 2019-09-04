<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.model.*"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.tools.tools"%>
<%
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO1");

	strService strSvc = new strService();
	List<StrVO> strList = strSvc.findByStrSta("營業中");
	pageContext.setAttribute("list", strList);

	pageContext.setAttribute("blogVO", blogVO);
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
<link rel="short Icon" type="images/x-icon" href="images/logo.jpg">
<link rel="stylesheet" href="css/member.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/css/base.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/easyfood/front-end/class/member/css/member_blog.css">

<script src="<%=request.getContextPath()%>/easyfood/front-end/class/member/ckeditor/ckeditor.js"></script>


</head>
<body>

	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<!-- tab2  新增美食日記 -->

	<div class="add_article">
		<form method="post" action="<%=request.getContextPath()%>/easyfood/front-end/class/member/blog.do" name="form1">
			<table class="table table-hover table-bordered table-condensed">
				<thead>
					<tr style="background-color: #eee">
						<th class="col-sm-1"></th>
						<th><h5>
								<b>新增美食日記 
							</h5> </b></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>標題</td>
						<td><input type="text" class="form-control input_date"
							name="title" id="title" placeholder="請輸入標題..."
							value='<%=(blogVO == null) ? "" : blogVO.getBl_name()%>' /></td>

					</tr>
					<!--             <tr>
              <td>日期</td>
              <td>
                <input type="date" class="form-control input_date" name="article_date" id="article_date"  
                value='<%// if(blogVO!=null){out.print(blogVO.getBl_date());}%>' />               
              </td>

            </tr> -->
					<tr>
						<td>店家</td>
						<td><select style="width: 200px;" name="article_store">
								<option value="請選擇">請選擇</option>
								<c:forEach var="strVO" items="${list}">
									<option value="${strVO.str_no}"
										${(strVO.str_no==blogVO.str_no)? 'selected':''}>${strVO.str_name}</option>
								</c:forEach>
						</select> (選擇想分享的店家,可設定店家連結)</td>

					</tr>
					<tr>
						<td>日記<br>編輯
						</td>
						<td><textarea name="article_content" id="content" rows="10"
								cols="80"><%=(blogVO == null) ? "" : blogVO.getBl_con()%></textarea>
							<script>
                                CKFinder.setupCKEditor();
                                CKEDITOR.replace( 'content', {});
							</script></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="action" value="insert">
			<div class="text-center">
				<input type='submit' value='送出'
					class="btn btn-success  btn login-button" onclick='processData()'>
			</div>
		</form>
	</div>






	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>