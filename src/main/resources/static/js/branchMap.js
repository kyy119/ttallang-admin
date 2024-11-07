// URL에서 branchId 가져오기
const urlParams = new URLSearchParams(window.location.search);
const branchId = urlParams.get("branchId");
var latitude = 0;
var longitude = 0;

// 지도 초기화 및 위치 표시 함수
function initMap(latitude, longitude) {
  var container = document.getElementById('map'); // 지도 표시할 div
  var options = {
    center: new kakao.maps.LatLng(latitude, longitude),
    level: 3 // 지도의 확대 레벨
  };
  var map = new kakao.maps.Map(container, options);
  var markerPosition = new kakao.maps.LatLng(latitude, longitude);
  var marker = new kakao.maps.Marker({
    position: markerPosition,
    image: new kakao.maps.MarkerImage(
        "/images/redSpot.png",
        new kakao.maps.Size(50, 50)
    )
  });
  marker.setMap(map);
}

// AJAX로 branchId에 해당하는 브랜치 정보 가져오기
$(document).ready(function () {
  $.ajax({
    url: "/admin/api/branches/" + branchId,
    method: "GET",
    success: function (data) {
      latitude = data.latitude;
      longitude = data.longitude;
      initMap(data.latitude, data.longitude);
      getBicycle(latitude, longitude);
    },
    error: function () {
      alert("Failed to fetch branch details.");
    }
  });

  $("#submitBtn").on("click", function (e) {
    e.preventDefault();
    var bicycleName = $("input[name='bicycleName']").val();
    $.ajax({
      type: "POST",
      url: "/admin/api/bicycle/add",
      data: {
        bicycleName: bicycleName,
        latitude: latitude,
        longitude: longitude
      },
      success: function (response) {
        if (response == "SUCCESS") {
          document.location.reload();
        } else {
          alert("이미 존재하는 자전거 이름 입니다.");
        }
      },
      error: function (xhr, status, error) {
        alert("Error: " + error);
      }
    });
  });
});

function getBicycle(latitude, longitude) {
  $.ajax({
    url: "/admin/api/bicycle/byLocation",
    method: "GET",
    data: {latitude: latitude, longitude: longitude},
    success: function (data) {
      var bicycleList = $("#bicycleList");
      bicycleList.empty();
      data.forEach(function (bicycle) {
        bicycleList.append("<li>" + bicycle.bicycleName + "</li>");
      });
    },
    error: function () {
      alert("Failed to fetch bicycles for the branch.");
    }
  });
}