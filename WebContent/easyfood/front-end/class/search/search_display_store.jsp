<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.str.model.*"%>
<%@ page import="com.storecategory.model.*"%>
<%@ page import="com.tools.tools"%>
<%
	String mem_no = (String) session.getAttribute("mem_no");

	strService strSvc = new strService();
	List<StrVO> list = (List<StrVO>) request.getAttribute("strList");
//List<StrVO> list=strSvc.getAll();  

if(list==null){
	list= (List<StrVO>)strSvc.getAll();
}

	StocaService stoSvc=new StocaService();
	
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("stoSvc", stoSvc);
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/easyfood/front-end/class/search/css/search.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<script
	src="<%=request.getContextPath()%>/easyfood/front-end/class/search/js/search.js"></script>

</head>
<body>



	<!--第二列===================================================================-->

	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>


	<div class="col-xs-12 col-sm-6   map" id="map-canvas"
		style="width: 50%; height: 600px"></div>


	<div class="col-xs-12 col-sm-6">
  
    <div class="route">
		<!--  經度 -->
		<input id="lat" type="hidden" value="" size="15">
		<!-- 緯度 -->
		<input id="lng" type="hidden" value="" size="15">

    <a href="#" class="btn btn-success btn-block" role="button" id="food_route" style="display: none; " onclick="display_route()">美食導航</a>
    <a href="#" class="btn btn-success btn-block" role="button" id="display_store" style="display: none; " onclick="display_store()">回到店家列表</a>
    <a href="#" class="btn btn-default " role="button" id="walk" style="display: none; " onclick="model_walk()">走路</a>
    <a href="#" class="btn btn-default " role="button" id="drive" style="display: none;color: red; " onclick="model_drive()">開車</a>
    <a href="#" class="btn btn-default " role="button" id="transit" style="display: none; " onclick="model_transit()">大眾運輸</a>
    <div style="display: none; " id="route_detail"></div>
    </div>

    <div class="store">
		<%
			if (list.size() == 0) {
		%>
		<br>
		<div style="color: red" class="text-center">查無店家</div>
		<%
			} else {
		%>

		<c:forEach var="strVO" items="${list}" varStatus="s">
      <div class="store_select">

        <div class="row">
          <div class="col-xs-12 col-sm-4 store_img">

            <img src="<%=request.getContextPath()%>/tools/Mem_Red_Img?str_no=${strVO.str_no}" style="width:162px;height:110px"
              align="left" >
          </div>
          <div class="col-xs-12  col-sm-offset-1 col-sm-6">
            <h4>
              <b>${strVO.str_name}</b>
            </h4>
            <p>
              ${strVO.str_tel}<br>
              <b>${stoSvc.getOneStoca(strVO.stoca_no).stoca_name}</b><br>
              <input type="hidden" id="disAddr${s.index}" value="${strVO.str_cou}${strVO.str_city}${strVO.str_addr}">
              ${strVO.str_cou}${strVO.str_city}${strVO.str_addr}
            </p>
          </div>
        </div>


        <div align="right">
          


          <div class="row">
<div class="col-xs-12 col-sm-3">
<c:if test='${strVO.str_ship=="TRUE"}'>
<p style="color:red"><b>　含外送</b></p>
</c:if>
</div>


          　　
            <div class="col-xs-12 col-sm-offset-2 col-sm-2" >
              <a href="#" id="myDIV${s.index}" class="btn btn-primary" role="button"  style="display: none; " onclick="display_button()">顯示路徑</a>
            </div>

            <div class="col-xs-12 col-sm-2" >
              <FORM METHOD="get"
                ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do">
                <input type="submit" class="btn btn-danger" role="button"
                  value="進入店家" > <input type="hidden" name="str_no"
                  value="${strVO.str_no}"> <input type="hidden"
                  name="select" value="introduce"><input type="hidden"
                  name="action" value="enter_store_select">
              </FORM>
            </div>
            <div class="col-xs-12 col-sm-2">
              <FORM METHOD="get"
                ACTION="<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do">
                <input type="submit" class="btn btn-danger" role="button"
                  value="我要訂餐"> <input type="hidden" name="str_no"
                  value="${strVO.str_no}"> <input type="hidden" name="dcla_no"
                  value="all"><input type="hidden"
                  name="select" value="menu"><input type="hidden"
                  name="action" value="enter_store_select">
              </FORM>
            </div>
          </div>


        </div>
      </div>
		</c:forEach>
		<%
			}
		%>
    </div>
	</div>












<script>
 
var geocoder;
var map, popup;
var marker;
var popupContent;
var model_route='DRIVING';

  function codeAddress() {
    geocoder = new google.maps.Geocoder();
    popup = new google.maps.InfoWindow();


    map = new google.maps.Map(document.getElementById('map-canvas'), {
    center: {lat: 25.054262, lng: 121.5511573},
    zoom: 14
        });

     diningLocation();

    var address = document.getElementById("address").value;
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        map.setCenter(results[0].geometry.location);
    
//    document.getElementById("Latlng").value=results[0].geometry.location.toString();
    document.getElementById("lat").value=results[0].geometry.location.lat();
    document.getElementById("lng").value=results[0].geometry.location.lng();
            marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
    
    showAddress(results[0], marker);
      } else {
        //alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }

    // 設定 marker 的訊息泡泡
  function showAddress(result, marker) {
        popupContent = result.formatted_address;
        popup.setContent(popupContent);
        popup.open(map, marker);
  }
  
  function getAddress() {
    var xPosition = new google.maps.LatLng(document.getElementById("lat").value, document.getElementById("lng").value)

        // 將經緯度透過 Google map Geocoder API 反查地址
        geocoder.geocode({
          'latLng': xPosition
        }, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results) {
                    document.getElementById("address").value=results[0].formatted_address;
                }
            } else {
                alert("Reverse Geocoding failed because: " + status);
            }
        });

  }


//============================餐廳位置(叉子)=====================================

      function diningLocation() {

      <% for(int i=0;i<list.size();i++){ %>  
      var uluru<%=i%> = {lat: <%=list.get(i).getStr_lat() %>, lng: <%=list.get(i).getStr_long() %>};

      var contentString<%=i%> = '<div id="content">'+
          '<div id="siteNotice">'+
          '</div>'+
          '<h5 id="firstHeading" class="firstHeading"><%=list.get(i).getStr_name() %></h5>'+
          '<div id="bodyContent">'+
          '<p><a href='+'"<%=request.getContextPath()%>/easyfood/front-end/class/search/search.do?str_no=<%=list.get(i).getStr_no() %>&select=introduce&action=enter_store_select">'
          +'<img src='+'<%=request.getContextPath()%>/tools/Mem_Red_Img?str_no=<%=list.get(i).getStr_no() %> width='+'90px'+
          '</a></div>'+
          '</div>';

      var infowindow<%=i%> = new google.maps.InfoWindow({
        content: contentString<%=i%>,
        maxWidth: 200
      });
      var image = '<%=request.getContextPath()%>/easyfood/front-end/class/search/images/dining.png';
      var marker<%=i%> = new google.maps.Marker({
        position: uluru<%=i%>,
        map: map,
        icon: image,
        title: '<%=list.get(i).getStr_name() %>'
      });

      marker<%=i%>.addListener('mouseover', function() {
        infowindow<%=i%>.open(map, marker<%=i%>);
      });

      marker<%=i%>.addListener('mouseout', function() {
          infowindow<%=i%>.close(map, marker<%=i%>);
        });

      <%}%>


    }



//==============================================================

window.onload =codeAddress;



//顯示隱藏的顯示位置按鈕
function displayButton(myDIV){
    document.getElementById(myDIV).style.display = "";
}

//顯示隱藏的美食導航按鈕
function display_button(){
  document.getElementById("food_route").style.display = "";
}

//顯示隱藏的店家列表
function display_store(){
  document.getElementById("food_route").style.display = "";
  document.getElementById("display_store").style.display = "none";
  document.getElementById("walk").style.display = "none";
  document.getElementById("drive").style.display = "none";
  document.getElementById("transit").style.display = "none";
  document.getElementById("route_detail").style.display = "none";
  var elems = document.getElementsByClassName("store");
    elems[0].style.display = "";
}

//隱藏店家列表
function display_route(){
  var elems = document.getElementsByClassName("store");
    elems[0].style.display = "none";
     document.getElementById("food_route").style.display = "none";
     document.getElementById("display_store").style.display = "";
     document.getElementById("walk").style.display = "";
     document.getElementById("drive").style.display = "";
     document.getElementById("transit").style.display = "";
     document.getElementById("route_detail").style.display = "";
}


//============================我的位置=============================

      function myPosition() {
     


          map = new google.maps.Map(document.getElementById('map-canvas'), {
          center: {lat: 24.96842, lng: 121.1956658},
          zoom: 12
        });
      diningLocation();

        // Try HTML5 geolocation.
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };

            // showAddress(pos, marker);
            // infoWindow.setPosition(pos);
            
    document.getElementById("lat").value=position.coords.latitude;
    document.getElementById("lng").value=position.coords.longitude;
    getAddress();
        //------------------

        var contentString = '我的位置';

        var infowindow = new google.maps.InfoWindow({
          content: contentString,
          maxWidth: 200
        });

        marker = new google.maps.Marker({
          position: pos,
          map: map,
          title: '我的位置'
        });

        map.setCenter(pos);


          infowindow.open(map, marker);

        //------------------


            
          }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
          });
        } else {
          // Browser doesn't support Geolocation
          handleLocationError(false, infoWindow, map.getCenter());
          alert("無法取得我的位置!!");
        }
      }

      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                              'Error: The Geolocation service failed.' :
                              'Error: Your browser doesn\'t support geolocation.');
      }

//================================顯示路徑================================
var directionsService;
var directionsDisplay;
var arr;

     function myMap(){
        
        console.log("myMap:");
        myPosition();
        directionsService = new google.maps.DirectionsService();
        directionsDisplay = new google.maps.DirectionsRenderer({
        'map': map,
        'preserveViewport': true,
        'draggable': true
    }); 

        <% for(int i=0;i<list.size();i++){ %>  
           arr=["myDIV<%=i%>","disAddr<%=i%>"];
           displayButton(arr[0]);
           initMap2(arr,arr[1]);
        <%}%>
     }


      function initMap2(myArr,disAddr) {

        directionsDisplay.setMap(map);
        directionsDisplay.setPanel(document.getElementById('route_detail'));
        //--------------------------------------------------------------------------

        console.log("myDIV:"+myArr[0]);
        console.log("disAddr:"+disAddr);

        var onChangeHandler = function() {
          calculateAndDisplayRoute(directionsService, directionsDisplay , disAddr);
          arr=myArr;
        };
        document.getElementById(myArr[0]).addEventListener('click', onChangeHandler);
        //document.getElementById('myDIV').addEventListener('click', onChangeHandler);

      }

      function calculateAndDisplayRoute(directionsService, directionsDisplay , disAddr) {
   
        directionsService.route({
          origin: document.getElementById('address').value,
          destination: document.getElementById(disAddr).value,
          //destination: document.getElementById('disAddr').value,
          travelMode: model_route
        }, function(response, status) {
          var strTmp = "";
          if (status === 'OK') {
            directionsDisplay.setDirections(response);

                    var route = response.routes[0];
                    for (var i = 0; i < route.legs.length; i++) {
                        var routeSegment = i + 1;
                        strTmp += route.legs[i].distance.text;
                    }
                    //取得距離(正整數，公里)
                    var dist = parseInt(parseFloat(strTmp)).toString();
                    document.getElementById("distance").innerHTML="距離"+dist+"公里";
                    // alert("距離"+dist+"公里");


          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }


    //=======================導航模式=============================
function model_walk(){
model_route='WALKING';
calculateAndDisplayRoute(directionsService, directionsDisplay , arr[1]);  

 document.getElementById("walk").style.color = "red";
 document.getElementById("drive").style.color = "black";
 document.getElementById("transit").style.color = "black";
}

function model_drive(){
model_route='DRIVING';
calculateAndDisplayRoute(directionsService, directionsDisplay , arr[1]);
 
 document.getElementById("walk").style.color = "black";
 document.getElementById("drive").style.color = "red";
 document.getElementById("transit").style.color = "black";
}

function model_transit(){
model_route='TRANSIT'; 
calculateAndDisplayRoute(directionsService, directionsDisplay , arr[1]);
 
 document.getElementById("walk").style.color = "black";
 document.getElementById("drive").style.color = "black";
 document.getElementById("transit").style.color = "red";
}  
</script>






	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>