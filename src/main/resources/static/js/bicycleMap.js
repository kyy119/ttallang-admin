const urlParams = new URLSearchParams(window.location.search);
const bicycleId = urlParams.get("bicycleId");
var latitude = 0;
var longitude = 0;

// 지도 초기화 및 위치 표시 함수
function initMap(latitude, longitude) {
  var container = document.getElementById('map'); // 지도 표시할 div
  var options = {
    center: new kakao.maps.LatLng(latitude, longitude),
    level: 3 // 지도의 확대 레벨
  };

  // 지도 생성
  var map = new kakao.maps.Map(container, options);

  // 마커 생성 및 표시
  var markerPosition = new kakao.maps.LatLng(latitude, longitude);
  var marker = new kakao.maps.Marker({
    position: markerPosition,
    image: new kakao.maps.MarkerImage(
        "/images/markerStar.png", // 마커 이미지
        new kakao.maps.Size(50, 50) // 크기 조절
    )
  });
  marker.setMap(map);
}

$(document).ready(function () {
  $.ajax({
    url: "/admin/api/bicycle/" + bicycleId,
    method: "GET",
    success: function (data) {
      initMap(data.latitude, data.longitude);
    },
    error: function () {
      alert("Failed to fetch branch details.");
    }
  });
});