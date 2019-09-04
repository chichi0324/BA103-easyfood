var content;
window.onload = init; 
function init() {
    document.getElementById("county").onfocusout = trans;
    document.getElementById("city").onfocusout = trans;
    document.getElementById("addr").onfocusout = trans;
}

function trans() {
    content = $("#county").val() + $("#city").val() + $("#addr").val();
    delayedLoop();
}

function delayedLoop() {
    addressToLatLng(content);
    window.setTimeout(delayedLoop, 1500);
}

function addressToLatLng(addr) {
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({"address": addr}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            var lat = $("#lat").val();
            var lng = $("#lng").val();
            $("#lat").val(results[0].geometry.location.lat());
            $("#lng").val(results[0].geometry.location.lng());
        } 
    });
}