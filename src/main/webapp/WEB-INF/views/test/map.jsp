<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <!-- Kakao Maps API -->
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1bfc76317e78b81b1a32b1be44269182"></script>
  <script>
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
    initMap();
  </script>
</head>
<body>
  <div id="map" style="width:100%;height:350px;"></div>
</body>
</html>
