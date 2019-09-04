<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.storecategory.model.*" %>
<%
	StocaVO stocaVO = (StocaVO) request.getAttribute("stocaVO");
%>

<style>
	form {
		width: fit-content;
		display: unset;
	}
	.container {
		margin-top: 3em;
		margin-left: 5em;
	}
	input, textarea {
		line-height: 2;
	}
	

</style>

<section>
<div class="container">
	<form action="<%=request.getContextPath() %>/stoca/stoca.do" method="post" enctype="multipart/form-data">
		<div style="display:inline-block;vertical-align:super;">
			<img src="<%=request.getContextPath()%>/stoca/readStocaPicture.do?STOCA_NO=${stocaVO.stoca_no}"
				height="150px" width="200px" id="img">
			<input type="file" name="stoca_img" id="file">
		</div>
		<div style="display:inline-block;">
			<input type="text" name="stoca_no" value="${stocaVO.stoca_no}" readonly>
			<input type="text" name="stoca_name" value="${stocaVO.stoca_name}">
			<textarea name="stoca_note">${stocaVO.stoca_note}</textarea>
			<button class="btn btn-lg btn-primary" name="action" value="UPDATE">確定</button>
		</div>
	</form>
	
</div>

</section>

<script>
	var inputFiles = document.getElementsByName("stoca_img");
	var count = 0;
	function addImage(e){
		var inputFile = e.target;
		for(var j = 0; j<inputFiles.length; j++){
			if(inputFile == inputFiles[j]){
				count = j;
			}
		}
		
		if(inputFile.files && inputFile.files[0]) {
			var img = document.getElementById("img");
			var reader = new FileReader();
			reader.onload = function(e) {
				img.removeAttribute("src");
				img.setAttribute("src", e.target.result);
			}
			
			reader.readAsDataURL(this.files[0]);
		}
	}
	function fileOnchange(){
		for(var i =0; i< inputFiles.length; i++){
			inputFiles[i].onchange = addImage;
		}
	}
	function init(){
		fileOnchange();
	}
	window.onload = init;

</script>
<jsp:include page="footer.jsp" />