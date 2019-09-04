  var geocoder;
  var map, popup;

  function initialize() {
    geocoder = new google.maps.Geocoder();
    popup = new google.maps.InfoWindow();

  }


  function codeAddress() {
     initialize();
     initMap();
     geocoder = new google.maps.Geocoder();

    var address = document.getElementById("address").value;
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        map.setCenter(results[0].geometry.location);
    
//    document.getElementById("Latlng").value=results[0].geometry.location.toString();
    document.getElementById("lat").value=results[0].geometry.location.lat();
    document.getElementById("lng").value=results[0].geometry.location.lng();
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
    
    showAddress(results[0], marker);
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }

    // 設定 marker 的訊息泡泡
  function showAddress(result, marker) {
        var popupContent = result.formatted_address;
        popup.setContent(popupContent);
        popup.open(map, marker);
  }
  
  function getAddress() {
    var xPosition = new google.maps.LatLng(document.getElementById("lat").value, document.getElementById("lng").value)

        // 將經緯度透過 Google map Geocoder API 反查地址
        geocoder.geocode({
          'latLng': xPosition
        }, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results) {
                    document.getElementById("address").value=results[0].formatted_address;
                }
            } else {
                alert("Reverse Geocoding failed because: " + status);
            }
        });

  }



      function initMap() {
        var uluru = {lat: 25.054262, lng: 121.5511573};
        map = new google.maps.Map(document.getElementById('map-canvas'), {
          zoom: 15,
          center: uluru
        });

        var contentString = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h5 id="firstHeading" class="firstHeading">10號廚房</h5>'+
            '<div id="bodyContent">'+
            '<p><img src='+'images/storeIMG/STR_0001.jpg width='+'65px'+
            '</div>'+
            '</div>';

        var infowindow = new google.maps.InfoWindow({
          content: contentString,
          maxWidth: 200
        });
        var image = 'http://maps.google.com/mapfiles/kml/shapes/dining.png';
        var marker = new google.maps.Marker({
          position: uluru,
          map: map,
          icon: image,
          title: '10號廚房'
        });
        marker.addListener('mouseover', function() {
          infowindow.open(map, marker);
        });


        //====================================================
        var uluru1 = {lat: 25.042703, lng: 121.563851};

        var contentString1 = '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h5 id="firstHeading" class="firstHeading">印度皇宮</h5>'+
            '<div id="bodyContent">'+
            '<p><img src='+'images/storeIMG/STR_0002.jpg width='+'65px'+
            '</div>'+
            '</div>';

        var infowindow1 = new google.maps.InfoWindow({
          content: contentString1,
          maxWidth: 200
        });

        var marker1 = new google.maps.Marker({
          position: uluru1,
          map: map,
          icon: image,
          title: '印度皇宮'
        });
        marker1.addListener('mouseover', function() {
          infowindow1.open(map, marker1);
        });

      }
window.onload =codeAddress;
      // window.onload =initMap;