$(document).ready(function () {
  loadAllBicycle();
  // 라디오 버튼 변경 시 이벤트 핸들러
  $('input[name="report"]').change(function () {
    var status = $(this).val();
    if (status == 'reported' || status == 'notReported') {
      loadReportList(status);
    } else {
      loadAllBicycle();
    }
  });

  //전체 자전거 로드
  function loadAllBicycle() {
    // Change table headers for all bicycles
    $('th').eq(0).text('자전거 고유번호');
    $('th').eq(1).text('수정'); // Update the second header to "수정"

    $.ajax({
      url: "/admin/api/bicycle/allBicycle",
      method: "GET",
      success: function (data) {
        var reportList = $("#reportList");
        reportList.empty(); // Clear existing list
        data.forEach(function (bicycle) {
          if (bicycle.bicycleStatus == '1') {
            reportList.append("<tr><td><a href='/admin/bicycle/map?bicycleId="
                + bicycle.bicycleId + "'> " + bicycle.bicycleName + "(활성화)"
                + "</a></td><td><a href='/admin/bicycle/edit?bicycleId="
                + bicycle.bicycleId + "'>수정</a></td></tr>");
          } else {
            reportList.append("<tr><td><a href='/admin/bicycle/map?bicycleId="
                + bicycle.bicycleId + "'> " + bicycle.bicycleName + "(비활성화)"
                + "</a></td><td><a href='/admin/bicycle/edit?bicycleId="
                + bicycle.bicycleId + "'>수정</a></td></tr>");
          }
        });
      },
      error: function () {
        alert("리스트를 불러오는 데 실패했습니다.");
      }
    });
  }

  // 리스트 로드 함수
  function loadReportList(status) {
    $.ajax({
      url: "/admin/api/bicycle/report",
      method: "GET",
      data: {reportStatus: status},
      success: function (data) {
        var reportList = $("#reportList");
        reportList.empty();
        data.forEach(function (bicycle) {
          if (status == 'reported') {
            reportList.append("<tr><td><a href='/admin/bicycle/map?bicycleId="
                + bicycle.bicycleId + "'> " + bicycle.bicycleName
                + "</a></td><td><a href='/admin/bicycle/reported?bicycleId="
                + bicycle.bicycleId + "'>신고</a></td></tr>");
          } else if (status == 'notReported') {
            reportList.append("<tr><td><a href='/admin/bicycle/map?bicycleId="
                + bicycle.bicycleId + "'> " + bicycle.bicycleName
                + "</a></td><td>미신고</td></tr>");
          }
        });
      },
      error: function () {
        alert("리스트를 불러오는 데 실패했습니다.");
      }
    });
  }
});