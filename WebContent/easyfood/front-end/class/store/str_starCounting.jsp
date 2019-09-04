<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List, java.util.LinkedList" %>
<%@ page import="com.store.model.*"%>
<%@ page import="com.ord.model.*" %>
<%
	String str_no = (String) session.getAttribute("str_no");	

	int starCount = 0;
	int starCount5 = 0, starCount4 = 0, starCount3 = 0, starCount2 = 0, starCount1 = 0 ;
	List<OrdVO> ordList = new LinkedList<OrdVO>();
	
	OrdService ordSvc = new OrdService();
	List<OrdVO> list = ordSvc.getAllByStr(str_no);
	for(OrdVO ordVO : list) {
		if(ordVO.getOrd_stat().equals("已付款")) {
			ordList.add(ordVO);
			starCount++;
		}
	}
	for(OrdVO ordVO : ordList) {
		if(ordVO.getOrd_ev() == 5) {
			starCount5++;
		} else if (ordVO.getOrd_ev() == 4) {
			starCount4++;
		} else if (ordVO.getOrd_ev() == 3) {
			starCount3++;
		} else if (ordVO.getOrd_ev() == 2) {
			starCount2++;
		} else if (ordVO.getOrd_ev() == 1) {
			starCount1++;
		}
	}
	
	pageContext.setAttribute("starCount", starCount);
	pageContext.setAttribute("starCount5", starCount5);
	pageContext.setAttribute("starCount4", starCount4);
	pageContext.setAttribute("starCount3", starCount3);
	pageContext.setAttribute("starCount2", starCount2);
	pageContext.setAttribute("starCount1", starCount1);
	
/* 	System.out.println("starCount " + starCount);
	System.out.println("starCount5 " + starCount5);
	System.out.println("starCount4 " + starCount4);
	System.out.println("starCount3 " + starCount3);
	System.out.println("starCount2 " + starCount2);
	System.out.println("starCount1 " + starCount1); */
%>

<div class="list-group-item" style="padding-top:5px; padding-bottom:5px">
	<div class="star-heading">棒透了</div>
	<img class="star-content" src="<%=request.getContextPath()%>/easyfood/front-end/class/store/images/stars-5.png" alt="star" align="right">
	<div class="star-counting">${starCount5/starCount*100} %</div>
</div>
<div class="list-group-item" style="padding-top:5px; padding-bottom:5px">
	<div class="star-heading">很好</div>
	<img class="star-content" src="<%=request.getContextPath()%>/easyfood/front-end/class/store/images/stars-4.png" alt="star" align="right">
	<div class="star-counting">${starCount4/starCount*100} %</div>
</div>
<div class="list-group-item" style="padding-top:5px; padding-bottom:5px">
	<div class="star-heading">一般</div>
	<img class="star-content" src="<%=request.getContextPath()%>/easyfood/front-end/class/store/images/stars-3.png" alt="star" align="right">
	<div class="star-counting">${starCount3/starCount*100} %</div>
</div>
<div class="list-group-item" style="padding-top:5px; padding-bottom:5px">
	<div class="star-heading">差勁</div>
	<img class="star-content" src="<%=request.getContextPath()%>/easyfood/front-end/class/store/images/stars-2.png" alt="star" align="right">
	<div class="star-counting">${starCount2/starCount*100} %</div>
</div>
<div class="list-group-item" style="padding-top:5px; padding-bottom:5px">
	<div class="star-heading">糟透了</div>
	<img class="star-content" src="<%=request.getContextPath()%>/easyfood/front-end/class/store/images/stars-1.png" alt="star" align="right">
	<div class="star-counting">${starCount1} %</div>
</div>